package advanceProgrammingAssign1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WordDictionary {

	//create simple list to store words
	private ArrayList<String> words;
	//create hashing map structure to store words
	private HashMap<String, ArrayList<String>> wordList;
	//count number of words added
	private int wordCount = 0;
	
	public WordDictionary() {
		// TODO Auto-generated constructor stub
		wordList = new HashMap<String, ArrayList<String>>();
		words = new ArrayList<String>();
	}

	//helper function to print the map
	public void printHash() {
		if (wordList != null) {
			ArrayList<String> temp;
			Set<Map.Entry<String, ArrayList<String>>> entries = wordList.entrySet();
			for (Map.Entry<String, ArrayList<String>> entry: entries) {
			    System.out.print(entry.getKey() + " " + "[::");
			    temp = entry.getValue();
				    for (String index: temp) {
				    	System.out.print(" --> " + index);
				    }
				    System.out.print("-->::]\n");
			}			
		}
		else {
			 System.out.println("Word Dictionary does not exist.");
		}
	}

	//parse JSON and fill hash map
	public void populateDictionary(String filepath) {
		
		String newWord;
		
		try {
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(new FileReader(filepath));
			JsonObject obj = element.getAsJsonObject(); 
			Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
			for (Map.Entry<String, JsonElement> entry: entries) {
				
				//read word into String
			    newWord = entry.getKey();
			    words.add(newWord);
			    //iterate over word length
			    for(int i = 0; i < newWord.length(); i++) {
			    	//save original word
			    	String originalWord = newWord;
			    	// apply wild card operation
			    	char[] myNameChars = newWord.toCharArray();
			    	myNameChars[i] = '_';
			    	String tempKey = String.valueOf(myNameChars);
			    	// if it exists in map append to existing word bucket list
			    	if (wordList.containsKey(tempKey)) {
			    		ArrayList<String> existingNodeList = new ArrayList<String>();
			    		existingNodeList = wordList.get(tempKey);
			    		existingNodeList.add(originalWord);
			    		wordList.put(tempKey, existingNodeList);
			    	}
			    	// else make a new bucket
			    	else {
			    		ArrayList<String> newodeList = new ArrayList<String>();
			    		newodeList.add(originalWord);
			    		wordList.put(tempKey, newodeList);
			    	}
			    	newWord = originalWord;
			    }
			    setWordCount(getWordCount() + 1);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("The specified file could not be found.");
		}
	
	}

	
	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	
	public HashMap<String, ArrayList<String>> getWordList() {
		return wordList;
	}

	public void setWordList(HashMap<String, ArrayList<String>> wordList) {
		this.wordList = wordList;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}
	
}
