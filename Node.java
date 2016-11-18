package assignment;
import java.util.*;
public class Node
{
	String root_node; 										//name of the node
	List<Node> neighbour = null;							// list of each neighbour
	List<Integer> cost = null;								//list of cost to each neighbour
	int realcost=0;
	Boolean explored = false;
	Node previous = null;
	int heuristic = 0;
	int f_value=0;
	public Node(String root_node)							
	{
		this.root_node = root_node;							//save name of the node
		this.neighbour = new ArrayList<>();					//create empty list of neighbours
		this.cost = new ArrayList<>();						//create empty list of neighbour cost
	}

	public void addNeighbour(Node x, int timecosttemp)
	{
		neighbour.add(x);									//add the neighbour to the node
		cost.add(timecosttemp);								//add cost to reach the neighbour
	}
	
}
