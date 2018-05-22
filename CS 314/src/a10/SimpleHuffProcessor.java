package a10;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.TreeMap;

public class SimpleHuffProcessor implements IHuffProcessor {
    //instance variables
    private IHuffViewer myViewer;
    private int readBit;
    private int writeBit;
    private HashMap<Integer, String> mapForTree;
    private int headFormat;
    private int[] myCount;
    private TreeNode peak;
    private int countTree;
    
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
    	//figure out that do we need to force compress the file
    	int count = readBit - writeBit;
    	if(count < 0 && !force){
    		myViewer.showError("Compressed file has " + Math.abs(count) 
    		+ "more bits than uncompressed file.\nSelect\"force compression\" option to compress.");
    		return -1;
    	}
    	//Initialize the wirteBit for preparing real writing
    	writeBit = 0;
    	String path ="";
    	BitOutputStream output = new BitOutputStream(out);
    	//Step 1, write down the magic number
    	output.writeBits(IHuffConstants.BITS_PER_INT, IHuffConstants.MAGIC_NUMBER);
    	writeBit += IHuffConstants.BITS_PER_INT;
    	//Step 2, write down the head format
    	output.writeBits(IHuffConstants.BITS_PER_INT, headFormat);
    	writeBit += BITS_PER_INT;
    	//Step 3, decide to use which way store the info
    	if(headFormat == IHuffConstants.STORE_COUNTS){
    		//write down the myCount array to store the info
    		for(int i = 0; i < IHuffConstants.ALPH_SIZE; i++){
    			output.writeBits(IHuffConstants.BITS_PER_INT, myCount[i]);
    			writeBit += IHuffConstants.BITS_PER_INT;
    		}
    	}
    	else if(headFormat == IHuffConstants.STORE_TREE){
    		//write down the first 32 bits of tree size
    		output.writeBits(IHuffConstants.BITS_PER_INT,countTree);
    		writeBit += IHuffConstants.BITS_PER_INT;
    		//store the tree
    		treeFormat(output, peak);
    	}
    	//Step 4, read the file again and write down the path
    	BitInputStream input = new BitInputStream(in);
    	int key = input.read();
    	while(key != -1){	
    		//get the character path
    		path = mapForTree.get(key);
    		writePath(path, output);
    		key = input.read();
    	}
    	input.close();
    	//Step 5, write down pseudo code 
    	String pseudoCode = mapForTree.get(IHuffConstants.PSEUDO_EOF);
    	writePath(pseudoCode, output);
    	output.close();
    	//the actual bit we already write
        return writeBit;
    }
    
    //helper method to read the path by file and map for tree
    private void writePath(String path, BitOutputStream output){
    	//read the each element path and write the path
    	for(int i = 0; i < path.length(); i++){
			if(path.charAt(i) == '0'){
				output.writeBits(1, 0);
				writeBit ++;
			}
			else if(path.charAt(i) == '1'){
				output.writeBits(1, 1);
				writeBit ++;
			}
		}
    }
    
    //helper method to store tree format in SCF(recursive)
    private void treeFormat(BitOutputStream out, TreeNode node){
    	//base case
    	if(node.isLeaf()){
    		//every leaf is 1 and count for 1
    		out.writeBits(1, 1);
    		writeBit ++;
    		//also record the value for the leaf
    		out.writeBits(BITS_PER_WORD+1, node.getValue());
    		writeBit+= IHuffConstants.BITS_PER_WORD + 1;
    	}
    	else{
    		//pre-order
    		//every non-leaf is 0 and count for 1 bit
    		out.writeBits(1, 0);
    		writeBit ++;
    		treeFormat(out,node.getLeft());
    		treeFormat(out, node.getRight());
    	}
    }
    
    //the read part to convert the frequency and value
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
    	//Set up the local variable and store the instance variables
        //Step 1, Initialize the all instance variable
        myCount = new int[IHuffConstants.ALPH_SIZE];
        headFormat = headerFormat;
        readBit = 0;
        writeBit = 0;
        countTree = 0;
        mapForTree = new HashMap<Integer, String>();
        PriorityQueue<TreeNode> result = new PriorityQueue<TreeNode>();
        int key=0;
        //Step 2, read from the text and record frequency
        //change the InputStream to BitInputStream
        BitInputStream input = new BitInputStream(in);
        //since we can not direct change the frequency in TreeNode
    	//so we need a map to read from the text and count the each character frequency
        TreeMap<Integer, Integer> record = new TreeMap<Integer, Integer>();
        //build up the map
        makerecord(key,input,record);
        //Step 3, put map into TreeNode and put the TreeNode into priorityQueue
        enqueue(result, record);
        //Step 4, combine the priority queue and merge into one tree
        combine(result);
        peak=result.peak();
        //Step 5, to record the path map and find few other info
        encodePath(peak, "");
        //Step 6, define the headerFormat
        if(headerFormat == IHuffConstants.STORE_COUNTS){
        	//predict the size if we compress the file by Store count
        	writeBit+= (IHuffConstants.ALPH_SIZE + 2)*IHuffConstants.BITS_PER_INT;	
        }
        else if(headerFormat == IHuffConstants.STORE_TREE){
        	//predict the size if we compress the file by Store tree
        	writeBit += 3*IHuffConstants.BITS_PER_INT;
        	writeBit += countTree;
        }
        //return the difference between before compress and after compress
        return readBit-writeBit;
    }
    
    //helper method to make records
    private void makerecord(int key, BitInputStream input,TreeMap<Integer, Integer> record) throws IOException{
    	//read the first 8 bits
        key=input.read();
        //read every bit and count
        while(key!=-1){
        	//count the reading bits
        	readBit += IHuffConstants.BITS_PER_WORD;
        	if(record.containsKey(key)){
        		record.put(key, record.get(key)+1);
        	}
        	else{
        		record.put(key, 1);
        	}
        	key=input.read();
        }
        //close the input
        input.close();
    }
    
    //helper method to record the path map(recursive)
    private void encodePath(TreeNode node, String path ){
    	//base case, find the leaf
    	if(node.isLeaf()){
    		mapForTree.put(node.getValue(), path);
    		//find the size for Store Tree
    		writeBit += path.length()*node.getFrequency();
    		//find the size for Store Count
    		countTree ++;
    		countTree += IHuffConstants.BITS_PER_WORD+1;
    	}
    	else{
    		//pre-order to count each branches
    		countTree++;
    		encodePath(node.getLeft(), path + "0");
    		encodePath(node.getRight(), path + "1");
    	}
    }

    //helper method to enqueue the map
    private void enqueue(PriorityQueue<TreeNode> result, TreeMap<Integer, Integer> record){
    	//add every element and count the frequency
    	for(int element :record.keySet()){
        	myCount[element] = record.get(element); 
         	result.add(new TreeNode(element, record.get(element)));
        }
    	//add the pseudo EOF in this priority queue
        result.add(new TreeNode(IHuffConstants.PSEUDO_EOF,1));
    }
    
    //helper method to merge the priority queue
    private void combine(PriorityQueue<TreeNode> result){
        //combine the priority queue
        while(result.size()!=1){
        	//remove the first two TreeNode and combine into a new TreeNode
			TreeNode remove1 = result.poll();
			TreeNode remove2 = result.poll();
			TreeNode temp = new TreeNode(remove1,remove1.getFrequency()+remove2.getFrequency(), remove2);
			//add into queue(will be sorted)
			result.add(temp);
		}
    }
    
    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    public int uncompress(InputStream in, OutputStream out) throws IOException {
    	//initialize the parameter and instance variable
    	writeBit = 0;
    	BitInputStream input = new BitInputStream(in);
    	BitOutputStream output = new BitOutputStream(out);
    	TreeNode node = null;
    	//Step 1, read the magic number
    	int magic = input.readBits(IHuffConstants.BITS_PER_INT); 
    	if (magic != IHuffConstants.MAGIC_NUMBER) {
    	    //show error when there is not fit magic number
    		myViewer.showError("Error reading compressed file. \n" 
    	    + "File did not start with the huff magic number.");
    	    //close the in and out
    	    input.close();
    	    output.close();
    	    return -1;
    	}
    	//Step 2, read head format
    	int headerFormat = input.readBits(IHuffConstants.BITS_PER_INT);
    	//decide which store format build up
    	if(headerFormat == IHuffConstants.STORE_COUNTS){
    		//read the myCount, store the character and frequency in map
    		TreeMap<Integer, Integer> record = new TreeMap<>();
    		for(int i = 0; i < IHuffConstants.ALPH_SIZE; i++){	
    			int freq = input.readBits(IHuffConstants.BITS_PER_INT);
    			if(freq > 0){
    				record.put(i,freq);
    			}
    		}
    		PriorityQueue<TreeNode> result = new PriorityQueue<TreeNode>();
    		//we can still use the helper method above, it does not matter myCount[]
    		//because we will not use myCount[] in uncompressing
    		enqueue(result,record);
    		//add the pseudo EOF in priority queue
    		result.add(new TreeNode(IHuffConstants.PSEUDO_EOF,1));
    		//combine the queue
    		combine(result);
    		node = result.peak();
    	}
    	//store tree
    	else if(headerFormat == IHuffConstants.STORE_TREE){
    		//read the tree size
    		input.readBits(IHuffConstants.BITS_PER_INT);
    		//build the tree by use recursive
    		node = buildTree(null, input, input.readBits(1));
    	}
    	//Step 3, read the tree
    	//store root
    	TreeNode myroot = node;
    	int bits = input.readBits(1);
    	//go through the tree until meet the pseudo EOF
    	while(!(node.isLeaf() && node.getValue() == IHuffConstants.PSEUDO_EOF)){
    		//show error when there is no pseudo value
    		if(bits == -1){
    			myViewer.showError("Error reading compressed file. \n" +
                        "unexpected end of input. No PSEUDO_EOF value.");
    			//stop the program and close the input and output
    			input.close();
    			output.close();
    			return -1;
    		}
    		//read tree
    		if(node.isLeaf()){
        		//write down the character
    			output.writeBits(IHuffConstants.BITS_PER_WORD, node.getValue());
    			writeBit += IHuffConstants.BITS_PER_WORD;
    			//go back to root
    			node = myroot;
    		}
        	//when it is non-leaf
    		else {
    			if(bits == 0){
    				node = node.getLeft();
    				bits = input.readBits(1);
    			}
    			else{
    				node = node.getRight();
    				bits = input.readBits(1);
    			}
    		}
    	}
    	//close the input and output
    	output.close();
    	input.close();
        return writeBit;
    }
    
    //helper method, recursive to build the tree in store tree
    private TreeNode buildTree(TreeNode temp, BitInputStream input, int bits) throws IOException {
    	//base case, when it is leaf
    	if(bits==1){
    		int leaf =input.readBits(9);
    		return temp = new TreeNode(leaf,0);
    	}
    	//recursive part
    	else{
    		return temp = new TreeNode(buildTree(null, input, input.readBits(1)), -1, buildTree(null, input, input.readBits(1)));
    	}
    }

    private void showString(String s){
        if(myViewer != null)
            myViewer.update(s);
    }
}
