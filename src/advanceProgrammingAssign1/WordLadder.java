package advanceProgrammingAssign1;

import java.util.Scanner;

public class WordLadder {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		System.out.println("Word Ladder Problem!\n");
		
		System.out.println("Parsing JSON into hashmap...");
		
		WordDictionary dict = new WordDictionary();
		WordGraph graph = new WordGraph();
		
		
		dict.populateDictionary("dictionary/testDictionary.json");
		dict.printHash();
		
		System.out.println("Parsing complete!\n");
		System.out.println("Ready to create graph...");
		System.out.println("Creating graph...");
		
		
		System.out.println("Generating graph...");
		
		graph.parseGraph(dict);

	    System.out.println("Processing complete.\n");
		System.out.print(dict.getWordCount() + " entries parsed.\n");

		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("Enter first word:");
		String word1 = scanner.nextLine();
		System.out.println("Enter second word:");
		String word2 = scanner.nextLine();

		graph.BFS(word1, word2);

	}

}
