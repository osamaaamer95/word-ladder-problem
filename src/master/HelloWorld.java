package master;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.google.gson.*;


public class HelloWorld {

	public static void main(String[] args) {
		
		HashMap<String, ArrayList<String>> map = new HashMap();
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		String word;
//		String bucket;
		int count = 0;
		
		try {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(new FileReader("dictionary/testDictionary.json"));
		JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
		for (Map.Entry<String, JsonElement> entry: entries) {
		    System.out.println(entry.getKey());
		    word = entry.getKey();
		    for(int i = 0; i < word.length(); i++) {
		    	String originalWord = word;
		    	char[] myNameChars = word.toCharArray();
		    	myNameChars[i] = '_';
		    	word = String.valueOf(myNameChars);
		    	System.out.println(word);
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
			
		}

		UndirectedGraph<String, DefaultEdge> graph =
			      new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, ArrayList<String>> pair = (Map.Entry)it.next();

	        ArrayList<String> words = pair.getValue();
	        for (String word1: words) {
	        	   for(String word2 : words) {
	        	      if (word1.compareTo(word2) != 0){
	        	    	  graph.addVertex(word1);
	        	    	  graph.addVertex(word2);
	        	    	  graph.addEdge(word1, word2);
	        	      }
	        	   }
	        	
	        	 }
	        
	       
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
		System.out.print(count);

		JGraph jgraph = new JGraph( new JGraphModelAdapter( graph ) );
		JFrame frame = new JFrame();
	    frame.getContentPane().add(jgraph);
	    frame.setTitle("JGraphT Adapter to JGraph Demo");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}

}
