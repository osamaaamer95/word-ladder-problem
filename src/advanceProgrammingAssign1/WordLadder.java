package advanceProgrammingAssign1;

import java.util.Scanner;

public class WordLadder {
	
	private static String word1;
	private static String word2;
	
	public static void getUserInput() {
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
	}

	public static void getAllPaths(WordDictionary dict, WordGraph graph) {
		for (String wordA: dict.getWords()) {
			for (String wordB: dict.getWords()) {
				if (wordA != wordB && wordA.length() == wordB.length()) {
					System.out.print("Path from " + wordA + " to " + wordB + "(in reverse): \n");
					WordGraph.BFSShortestPath(wordA.toUpperCase(), wordB.toUpperCase());
				}	
			}
		}
		System.out.println("\n\nWords without a chain: " + graph.getUnsolvedWords().toString());
	}
	
	public static void getShortestPath(String word1, String word2) {
		word1 = word1.toUpperCase();
		word2 = word2.toUpperCase();
		WordGraph.BFSShortestPath(word1, word2);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 
		// TODO Auto-generated method stub
		System.out.println("Word Ladder Problem!\n");
		System.out.println("Parsing JSON into hashmap...");
		
		WordDictionary dict = new WordDictionary();
		WordGraph graph = new WordGraph();
		
		// parse JSON dictionary into has map
		dict.populateDictionary("dictionary/testDictionary.json");
		
		// uncomment to print the hash map
		dict.printHash();
		
		System.out.println("Parsing complete!\n");
		System.out.println("Ready to create graph...");
		System.out.println("Creating graph...");
		System.out.println("Generating graph...");
		
		// parse hash map into undirected graph
		graph.parseGraph(dict);

	    System.out.println("\nProcessing complete.");
		System.out.println(dict.getWordCount() + " entries parsed.\n");
		
		while(true) {
			System.out.println("\n1) Find shortest ladder between two words.");
			System.out.println("2) Calculate minimum ladders between all dictionary words.");
			System.out.println("3) Exit.\n");
			System.out.println("\nEnter choice: ");
			String choice = scanner.nextLine();
			switch(choice) {
			   case "1" :
			      // Statements
					// get two words from user
					getUserInput();
					// get shortest word ladder
					getShortestPath(word1, word2);
			      break; 
			   
			   case "2" :
			      // Statements
					// get shortest paths between all words in dict
					getAllPaths(dict, graph);
			      break; 
			   
			   case "3" :
				      // Statements
				      return; // exit
				     
			   default : // Optional
			      // Statements
				   System.out.println("\nYou entered an invalid value.\n");
			}
		}
	}

}
