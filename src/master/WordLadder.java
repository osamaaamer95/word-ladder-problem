package master;

import java.util.Scanner;

public class WordLadder {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		System.out.println("Word Ladder Problem!\n");
		
		System.out.println("Parsing JSON into hashmap...");
		
		WordDictionary dict = new WordDictionary();
		dict.populateDictionary("dictionary/testDictionary.json");
		dict.printHash();
		
		System.out.println("Parsing complete!\n");
		System.out.println("Ready to create graph...");
		System.out.println("Creating graph...");
		
		
		
//		
//		
//		UndirectedGraph<String, DefaultEdge> graph =
//			      new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
//		Iterator it = map.entrySet().iterator();
//		System.out.println("Generating graph...");
//	    while (it.hasNext()) {
//	        Map.Entry<String, ArrayList<String>> pair = (Map.Entry)it.next();
//
//	        ArrayList<String> words = pair.getValue();
//	        for (String myword1: words) {
//	        	   for(String myword2 : words) {
//	        	      if (myword1.compareTo(myword2) != 0){
//	        	    	  graph.addVertex(myword1);
//	        	    	  graph.addVertex(myword1);
//	        	    	  graph.addVertex(myword2);
//	        	    	  graph.addEdge(myword1, myword2);
//	        	      }
//	        	   }
//	        	
//	        	 }
//	        
//	       
//	        it.remove(); // avoids a ConcurrentModificationException
//	    }
//		
//	    System.out.println("Processing complete.\n");
//		System.out.print(count + " entries parsed.\n");

		Scanner scanner = new Scanner(System.in); 
		
//		JGraph jgraph = new JGraph( new JGraphModelAdapter( graph ) );
//		JFrame frame = new JFrame();
//	    frame.getContentPane().add(jgraph);
//	    frame.setTitle("Word Ladder Problem");
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.pack();
//	    frame.setVisible(true);
	    
//		System.out.println("Enter first word:");
//		String word1 = scanner.nextLine();
//		System.out.println("Enter second word:");
//		String word2 = scanner.nextLine();
		
	//	List<DefaultEdge> path = DijkstraShortestPath.findPathBetween(graph, 7, 2);
	//	System.out.println(path); // prints [(7 : 9), (9 : 3), (3 : 2)]
	    
//	    GraphIterator<String, DefaultEdge> iterator = 
//                new BreadthFirstIterator<String, DefaultEdge>(graph, word1);
//	
//	    Queue<String> myqueue;
//	    
//	    System.out.println();
//        while (iterator.hasNext()) {
//        	String myword = iterator.next();
//        	
//            if (myword.compareTo(word2) == 0) {
//            	System.out.print("--> " + word2);
//            	break;
//            }
//            else {
//            	System.out.print("-->" + myword);
//            }
//        }
	}

}
