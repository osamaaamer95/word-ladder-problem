package master;

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

	private HashMap<String, ArrayList<String>> wordList;
	private int wordCount = 0;
	
	public WordDictionary() {
		// TODO Auto-generated constructor stub
		wordList = new HashMap<String, ArrayList<String>>();
	}


	public HashMap<String, ArrayList<String>> getWordList() {
		return wordList;
	}


	public void setWordList(HashMap<String, ArrayList<String>> wordList) {
		this.wordList = wordList;
	}

	public void printHash() {
		if (wordList != null) {
			Set<Map.Entry<String, ArrayList<String>>> entries = wordList.entrySet();
			for (Map.Entry<String, ArrayList<String>> entry: entries) {
			    System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
		else {
			 System.out.println("Word Dictionary does not exist.");
		}
	}
	
	public void populateDictionary(String filepath) {
		
		String word;
		
		try {
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(new FileReader(filepath));
			JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
			Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
			for (Map.Entry<String, JsonElement> entry: entries) {
			    word = entry.getKey();
			    for(int i = 0; i < word.length(); i++) {
			    	String originalWord = word;
			    	char[] myNameChars = word.toCharArray();
			    	myNameChars[i] = '_';
			    	word = String.valueOf(myNameChars);
			    	if (wordList.containsKey(word)) {
			    		ArrayList<String> temp = new ArrayList<String>();
			    		temp = wordList.get(word);
			    		temp.add(originalWord);
			    		wordList.put(word, temp);
			    	}
			    	else {
			    		ArrayList<String> temp = new ArrayList<String>();
			    		temp.add(originalWord);
			    		wordList.put(word, temp);
			    	}
			    	word = originalWord;
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
	
}
