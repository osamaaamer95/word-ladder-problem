package advanceProgrammingAssign1;

import java.util.Scanner;

public class WordLadder {
	
	
	public static void getAllPaths(WordDictionary dict) {
		for (String wordA: dict.getWords()) {
			for (String wordB: dict.getWords()) {
				if (wordA != wordB && wordA.length() == wordB.length()) {
					System.out.print("Path from " + wordA + " to " + wordB + "(in reverse): \n");
					WordGraph.getShortestPath(wordA.toUpperCase(), wordB.toUpperCase());
				}	
			}
		}
	}

	public static void main(String[] args) {
		
		String word1 = null, word2 = null;
		
		// TODO Auto-generated method stub
		System.out.println("Word Ladder Problem!\n");
		System.out.println("Parsing JSON into hashmap...");
		
		WordDictionary dict = new WordDictionary();
		WordGraph graph = new WordGraph();
		
		// parse JSON dictionary into has map
		dict.populateDictionary("dictionary/testDictionary.json");
		
		// uncomment to print the hash map
		//dict.printHash();
		
		System.out.println("Parsing complete!\n");
		System.out.println("Ready to create graph...");
		System.out.println("Creating graph...");
		System.out.println("Generating graph...");
		
		// parse hash map into undirected graph
		graph.parseGraph(dict);

	    System.out.println("Processing complete.\n");
		System.out.print(dict.getWordCount() + " entries parsed.\n");

		// get two words from user
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Enter first word:");
		word1 = scanner.nextLine();
		System.out.println("Enter second word:");
		word2 = scanner.nextLine();
		
		while (word1.length() != word2.length()) {
			System.out.println("Entered words must have same length. Try again...\n");
			System.out.println("Enter first word:");
			word1 = scanner.nextLine();
			System.out.println("Enter second word:");
			word2 = scanner.nextLine();
		}
		
		word1 = word1.toUpperCase();
		word2 = word2.toUpperCase();
		
		// get shortest word ladder
		WordGraph.getShortestPath(word1, word2);
		
		// get shortest paths between all words in dict
		getAllPaths(dict);

	}

}
