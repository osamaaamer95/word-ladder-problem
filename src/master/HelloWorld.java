package master;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


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
		 // Get a set of the entries
	      Set set = map.entrySet();
	      
	      // Get an iterator
	      Iterator i = set.iterator();
	      
	      // Display elements
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
		System.out.print(count);
		
	
		
		
	}

}
