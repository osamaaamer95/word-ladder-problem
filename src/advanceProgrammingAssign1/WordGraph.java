package advanceProgrammingAssign1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
	
	
	public void getDirections(String sourceNode, String destinationNode) {
		Map<String, Boolean> vis = new HashMap<String, Boolean>();

		Map<String, String> prev = new HashMap<String, String>();
		
		List directions = new LinkedList<String>();
		
		Queue q = new LinkedList<String>();
		
		String current = null;
//	    //find start node
	    for(String index : graph.vertexSet()){
	    	
    	    if (index.compareTo(sourceNode) == 0){
    	    	System.out.println("Starting Node Found: " + index);
    	    	current = index;
    	    }
    	}
	    
	    q.add(current);
	    
	    vis.put(current, true);
	    
	    while(!q.isEmpty()){
	        current = q.remove().toString();
	        if (current.compareTo(destinationNode) == 0){
	            break;
	        }else{
	        	String nextNode = null;
	        	for(DefaultEdge e : graph.edgesOf(current)){
	        		if (graph.getEdgeTarget(e).compareTo(current) == 0) {
	        			nextNode = graph.getEdgeSource(e);
	        		}
	        		else {
	        			nextNode = graph.getEdgeTarget(e);
	        		}
	            	if(!vis.containsKey(nextNode)){
	                    q.add(nextNode);
	                    vis.put(nextNode, true);
	                    prev.put(nextNode, current);
	                }
	            }
	        }
	    }
	    if (current.compareTo(destinationNode)!=0){
	        System.out.println("can't reach destination");
	    }
	    for(String node = destinationNode; node != null; node = prev.get(node)) {
	        directions.add(node);
	    }
	    System.out.println(directions.toString());

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
