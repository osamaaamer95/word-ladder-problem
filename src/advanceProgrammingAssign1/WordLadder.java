package advanceProgrammingAssign1;

import java.util.Scanner;

public class WordLadder {

	public static void main(String[] args) {
		
		String word1 = null, word2 = null;
		
		// TODO Auto-generated method stub
		System.out.println("Word Ladder Problem!\n");
		System.out.println("Parsing JSON into hashmap...");
		
		WordDictionary dict = new WordDictionary();
		WordGraph graph = new WordGraph();
		
		// parse JSON dictionary into has map
		dict.populateDictionary("dictionary/dictionary.json");
		
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
		
		graph.getShortestPath(word1, word2);

	}

}
