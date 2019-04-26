package model;

import java.util.*;

import javafx.collections.ObservableList;

/**
 * A UGraph is a collection of unique UNodes that have UEdge's connecting the UNodes.
 * UGraph is owned by the 'Controller', UNodes and UEdges are created by the Controller via the UGraph.
 * @author jamesdryver 
 * 
 * 
*/
public class UGraph {
	
	// Map of Nodes
	private Map< Integer ,UNode> uNodes;
	
	
   /**
	* Empty Constructor a new UGraph. Initializes an empty Map to store unique nodes in.
    */
	public UGraph()
	{
		uNodes = new HashMap<Integer , UNode>();	
	}
		
	/**
	 * Adds a new UNode to the uNodes map.
	 * 
	 * @param id id of new UNode
	 * @param nodeName name of new UNode
	 * @return false if the id exists in the map already
	 */
	public boolean addNode(Integer id, String nodeName, ObservableList<String> atr)
	{
	    System.out.printf ("UGraph: Node %d added named %s\n", id, nodeName);
		if (uNodes.put( id, new UNode( id, nodeName, atr)) == null) { 
			return true;
		}
		
		return false;
	}
	
	
	public int size()
	{
		return uNodes.size();
	}
	
	/**
	 * Searches the map with the Key given.
	 * 
	 * @param nodeid key given to Map
	 * @return the key associated Node
	 */
	public UNode getNode(Integer nodeid)
	{
		return uNodes.get(nodeid);
	}
	
	/**
	 * Removes a specific Node from the map.
	 * 
	 * @param id key of the node to be deleted
	 */
	public void removeNode (int id)
	{
		uNodes.get(id).cleanEdges();
		uNodes.remove(id); //hard remove; See undo/redo/History.java
	}	
	
	/**
	 * Links two given UNodes with a directional UEdge.
	 * 
	 * @param n1 starting node 
	 * @param n2 ending node
	 * @param edge edge name
	 */
	public void linkSingle(UNode n1, UNode n2, String edge)
	{
		UEdge e = new UEdge(n1, n2, edge);
		n1.addOutEdge(e);
		n2.addInEdge(e);
	}

	/**
	 * 
	 * @return
	 */
    public Integer[] getAllNodes ()
    {
        Object[] temp = uNodes.keySet ().toArray ();
        Integer[] keys = new Integer[temp.length];
        System.arraycopy (temp, 0, keys, 0, temp.length);
        Arrays.sort (keys);
        return keys;
}

}
