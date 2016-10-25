package advanceProgrammingAssign1;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class WordGraph {
	private UndirectedGraph<String, DefaultEdge> graph;
		      
	public WordGraph() {
		// TODO Auto-generated constructor stub
		graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	}

	public UndirectedGraph<String, DefaultEdge> getGraph() {
		return graph;
	}

	public void setGraph(UndirectedGraph<String, DefaultEdge> graph) {
		this.graph = graph;
	}
	
	public void parseGraph(WordDictionary map) {
		Iterator it = map.getWordList().entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, ArrayList<String>> pair = (Map.Entry)it.next();

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
	        
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
	public void BFS(String start, String target) {
		try {
		    GraphIterator<String, DefaultEdge> iterator = 
		    		new BreadthFirstIterator<String, DefaultEdge>(graph, start);
			
			System.out.println();
			System.out.print("START");
			while (iterator.hasNext()) {
				String myword = iterator.next();
				
			  if (myword.compareTo(target) == 0) {
			  	System.out.print(" --> " + target + " FOUND!");
			  	break;
			  }
			  else {
			  	System.out.print(" --> " + myword);
			  }
			}
		}
		catch (IllegalArgumentException e) {
			System.out.print("Specified word does not exist in the tree.");
		}
		
	}
	
	public void displayGraph() {
		JGraph jgraph = new JGraph( new JGraphModelAdapter( graph ) );
		JFrame frame = new JFrame();
	    frame.getContentPane().add(jgraph);
	    frame.setTitle("Word Ladder Problem");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
}
