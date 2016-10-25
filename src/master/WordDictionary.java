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

	private HashMap<String, ArrayList<WordNode>> wordList;
	private int wordCount = 0;
	
	public WordDictionary() {
		// TODO Auto-generated constructor stub
		wordList = new HashMap<String, ArrayList<WordNode>>();
	}


	public HashMap<String, ArrayList<WordNode>> getWordList() {
		return wordList;
	}


	public void setWordList(HashMap<String, ArrayList<WordNode>> wordList) {
		this.wordList = wordList;
	}

	public void printHash() {
		if (wordList != null) {
			ArrayList<WordNode> temp;
			Set<Map.Entry<String, ArrayList<WordNode>>> entries = wordList.entrySet();
			for (Map.Entry<String, ArrayList<WordNode>> entry: entries) {
			    System.out.print(entry.getKey() + " " + "[ ");
			    temp = entry.getValue();
				    for (WordNode index: temp) {
				    	System.out.print(index.getWord() + ", ");
				    }
				    System.out.print(" ]\n");
			}
			
		}
		else {
			 System.out.println("Word Dictionary does not exist.");
		}
	}
	
	public void populateDictionary(String filepath) {
		
		String newWord;
		
		try {
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(new FileReader(filepath));
			JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
			Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
			for (Map.Entry<String, JsonElement> entry: entries) {
				
				//read word into String
			    newWord = entry.getKey();
			    //iterate over word length
			    for(int i = 0; i < newWord.length(); i++) {
			    	//save original word
			    	String originalWord = newWord;
			    	// apply wildcard
			    	char[] myNameChars = newWord.toCharArray();
			    	myNameChars[i] = '_';
			    	String tempKey = String.valueOf(myNameChars);
			    	if (wordList.containsKey(tempKey)) {
			    		ArrayList<WordNode> existingNodeList = new ArrayList<WordNode>();
			    		existingNodeList = wordList.get(tempKey);
			    		existingNodeList.add(new WordNode(originalWord));
			    		wordList.put(tempKey, existingNodeList);
			    	}
			    	else {
			    		ArrayList<WordNode> newodeList = new ArrayList<WordNode>();
			    		newodeList.add(new WordNode(originalWord));
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
	
}
