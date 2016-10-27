package advanceProgrammingAssign1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class WordGraph {
	
	// empty undirected graph structure
	private static UndirectedGraph<String, DefaultEdge> graph;
	private String maxChain;
	private static ArrayList<String> unsolvedWords;
	private static int chainLength;
		      
	public WordGraph() {
		// TODO Auto-generated constructor stub
		graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		setMaxChain(null);
		unsolvedWords = new ArrayList<String>();
		chainLength = 0;
	}

	// parse hash map into undirected graph
	public void parseGraph(WordDictionary map) {
		
		// get hash map iterator
		Iterator it = map.getWordList().entrySet().iterator();
	    while (it.hasNext()) {
	    	// get buckets
	        Map.Entry<String, ArrayList<String>> pair = (Map.Entry)it.next();
	        // loop to add an edge between those words that have one letter difference
	        ArrayList<String> words = pair.getValue();
	        for (String myword1: words) {
        	   for(String myword2 : words) {
        	      if (myword1.compareTo(myword2) != 0){
        	    	  graph.addVertex(myword1);
        	    	  graph.addVertex(myword1);
        	    	  graph.addVertex(myword2);
        	    	  graph.addEdge(myword1, myword2);
        	      }
        	   }        	
	        }        
        // avoids a ConcurrentModificationException
        it.remove();
	    }
	}
	
	// get shortest path between two words in tree
	public static void BFSShortestPath(String sourceWord, String destinationWord) throws FileNotFoundException {
		System.out.print("Path from " + sourceWord + " to " + destinationWord + ": ");
		Boolean found = false;
		PrintWriter writer = new PrintWriter("results.csv");
		
		// keep data structures for visited and previous words
		Map<String, Boolean> visitedWords = new HashMap<String, Boolean>();
		Map<String, String> previousWord = new HashMap<String, String>();
		
		// keep a linked list for shortest path
		List<String> shortestPath = new LinkedList<String>();
		
		// use a queue while traversing using BFS
		Queue<String> wordQueue = new LinkedList<String>();
		
		String currentWord = null;
		
	    //find start node in the graph
	    for(String index : graph.vertexSet()){
    	    if (index.compareTo(sourceWord) == 0){
    	    	//System.out.println("Starting Node Found: " + index);
    	    	currentWord = index;
    	    	found = true;
    	    }
    	}
	    
	    if (!found) {
	    	System.out.println("The starting word was not found in the graph.");
	    	return;
	    }
	    
	    // push currently viewed word in queue and mark visited
	    wordQueue.add(currentWord);
	    visitedWords.put(currentWord, true);
	    
	    //
	    while(!wordQueue.isEmpty()){
	    	//pop queue and inspect
	        currentWord = wordQueue.remove().toString();
	        if (currentWord.compareTo(destinationWord) == 0){
	            break;
	        } else {
	        	String nextNode = null;
	        	// retrieve neighbors of current word
	        	for(DefaultEdge e : graph.edgesOf(currentWord)){
	        		if (graph.getEdgeTarget(e).compareTo(currentWord) == 0) {
	        			nextNode = graph.getEdgeSource(e);
	        		}
	        		else {
	        			nextNode = graph.getEdgeTarget(e);
	        		}
	        		// check if we have already visited it, if not add to queue and mark visited
	            	if(!visitedWords.containsKey(nextNode)){
	                    wordQueue.add(nextNode);
	                    visitedWords.put(nextNode, true);
	                    // save previous word to trace path later
	                    previousWord.put(nextNode, currentWord);
	                }
	            }
	        }
	    }
	    // check if we reached our destination
	    if (currentWord.compareTo(destinationWord)!=0){
	        System.out.println("Couldn't reach the destination word.");
	        unsolvedWords.add(sourceWord);
	        return;
	    }
	    // retrieve list of previous nodes to trace path backwards
	    for(String node = destinationWord; node != null; node = previousWord.get(node)) {
	        shortestPath.add(node);
	    }
	    // print path from destination to source
	    System.out.print("[END] <-- ");
	    for (String path: shortestPath) {
	    	System.out.print(path + " <-- ");
	    	chainLength++;
	    }
	    System.out.print("[START]");
	    //System.out.println("\nLength: " + maxChainLength);
	    //writer.println("\n\"" + sourceWord + "->" + destinationWord + "\",\"" + chainLength + "\"");
		
		try
		{
		    String filename= "results.txt";
		    FileWriter fw = new FileWriter(filename,true);
		    fw.write("\"" + sourceWord + "->" + destinationWord + "\",\"" + chainLength + "\"\n");
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
		chainLength = 0;
	}
	
	// helper function to display graph visually
	public void displayGraph() {
		JGraph jgraph = new JGraph( new JGraphModelAdapter( graph ) );
		JFrame frame = new JFrame();
	    frame.getContentPane().add(jgraph);
	    frame.setTitle("Word Ladder Problem");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public UndirectedGraph<String, DefaultEdge> getGraph() {
		return graph;
	}

	public String getMaxChain() {
		return maxChain;
	}

	public void setMaxChain(String maxChain) {
		this.maxChain = maxChain;
	}

	public ArrayList<String> getUnsolvedWords() {
		return unsolvedWords;
	}

}
