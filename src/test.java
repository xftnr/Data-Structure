import java.util.*;
import java.util.TreeMap;
 

public class test {

	
	
    public static void main(String[] args) {
    	List<String> a = new ArrayList<String>();
    	a.add("a");
    	a.add("b");
    	List<String> b =a;
    	b.add("c");
    	b.add("t");
    	System.out.println(a.indexOf("a"));
    }
}