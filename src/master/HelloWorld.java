package master;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.*;

import com.google.gson.*;

public class HelloWorld {

	public static void main(String[] args) {
		
		HashMap<String, ArrayList<String>> map = new HashMap();
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		String word;
//		String bucket;
		int count = 0;
		
		Scanner scanner = new Scanner(System.in); 
		
		try {
		System.out.println("Parsing JSON Dictionary and generating bucket list...\n");
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(new FileReader("dictionary/dictionary.json"));
		JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
		for (Map.Entry<String, JsonElement> entry: entries) {
//		    System.out.println(entry.getKey());
		    word = entry.getKey();
		    for(int i = 0; i < word.length(); i++) {
		    	String originalWord = word;
		    	char[] myNameChars = word.toCharArray();
		    	myNameChars[i] = '_';
		    	word = String.valueOf(myNameChars);
//		    	System.out.println(word);
		    	if (map.containsKey(word)) {
		    		ArrayList<String> temp = new ArrayList<String>();
		    		temp = map.get(word);
		    		temp.add(originalWord);
		    		map.put(word, temp);
		    	}
		    	else {
		    		ArrayList<String> temp = new ArrayList<String>();
		    		temp.add(originalWord);
		    		map.put(word, temp);
		    	}
		    	word = originalWord;
	
		    }
		    count++;

		}

		}
		catch(FileNotFoundException e) {
			System.out.println("The specified file could not be found.");
		}

		UndirectedGraph<String, DefaultEdge> graph =
			      new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		Iterator it = map.entrySet().iterator();
		System.out.println("Generating graph...");
	    while (it.hasNext()) {
	        Map.Entry<String, ArrayList<String>> pair = (Map.Entry)it.next();

	        ArrayList<String> words = pair.getValue();
	        for (String myword1: words) {
	        	   for(String myword2 : words) {
	        	      if (myword1.compareTo(myword2) != 0){
	        	    	  graph.addVertex(myword1);
	        	    	  graph.addVertex(myword1);
	        	    	  graph.addVertex(myword2);
	        	    	  graph.addEdge(myword1, myword2);
	        	      }
	        	   }
	        	
	        	 }
	        
	       
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	    System.out.println("Processing complete.\n");
		System.out.print(count + " entries parsed.\n");

//		JGraph jgraph = new JGraph( new JGraphModelAdapter( graph ) );
//		JFrame frame = new JFrame();
//	    frame.getContentPane().add(jgraph);
//	    frame.setSize(800, 800);
//	    frame.setTitle("JGraphT Adapter to JGraph Demo");
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.pack();
//	    frame.setVisible(true);
	    
		System.out.println("Enter first word:");
		String word1 = scanner.nextLine();
		System.out.println("Enter second word:");
		String word2 = scanner.nextLine();
	    
	    GraphIterator<String, DefaultEdge> iterator = 
                new BreadthFirstIterator<String, DefaultEdge>(graph, word1);
	   System.out.println();
        while (iterator.hasNext()) {
        	String myword = iterator.next();
            if (myword.compareTo(word2) == 0) {
            	System.out.print("--> " + word2);
            	break;
            }
            else {
            	System.out.print("-->" + myword);
            }
        }
	}

}
