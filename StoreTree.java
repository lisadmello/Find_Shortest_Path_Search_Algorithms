package assignment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreTree {

	public List<Node> tree = new ArrayList<Node>();
	public List<String> added = new ArrayList<String>();
	private String[] livetraffic;
	private int s;
	public StoreTree()
	{
		int random = 10;
	}
	public StoreTree(String[] temporary_bfs, int size) throws IOException {
		livetraffic = new String[size];
		livetraffic = temporary_bfs;
		s = size;
	}

	public void createTree() throws IOException {
		int temp1 = 0, temp2 = 0,temp4=0, i,timecost;
		int temp3 = Integer.parseInt(livetraffic[3].trim());			// total number of lines of live traffic
		temp3 = 4 + temp3;										// index of last line of traffic
		for (i = 4; i < temp3;i++) {

			String[] split = livetraffic[i].split(" ");			//split each line of traffic by blank space
			timecost = Integer.parseInt(split[2].trim());				//store the cost of the path
				if (!(added.contains(split[0]))) {				//if the node is not present in the tree
				Node n1 = new Node(split[0]);					//create the node
				tree.add(n1);									//add node to the tree
				added.add(split[0]);							//add the node to list of nodes present in the tree
				temp1 = tree.size() - 1;						//get index of the node added in tree
			} else {												//if the node is already present in the tree
				for (int q = 0; q < tree.size(); q++) {				//from start of tree till end
					if (split[0].equals(tree.get(q).root_node)) {	//search for the node
						temp1 = q;									//get index of found node
					}
				}
			}
			if (!(added.contains(split[1]))) {					//if the node is not present in the tree
				Node n2 = new Node(split[1]);					//create the node
				tree.add(n2);									//add node to the tree
				added.add(split[1]);							//add the node to list of nodes present in the tree
				temp2 = tree.size() - 1;						//get index of the node added in tree
			} else {												//if the node is already present in the tree
				for (int l = 0; l < tree.size(); l++) {				//from start of tree till end
					if (split[1].equals(tree.get(l).root_node)) {	//search for the node
						temp2 = l;									//get index of found node
					}
				}
			}  
			tree.get(temp1).addNeighbour(tree.get(temp2), timecost);	//add node n2 as a neighbour of node n1
			split = null;
		}
		String[] splitter = livetraffic[i].split(" ");
		temp4 = Integer.parseInt(splitter[0]);
		i=i+1;
		temp4=temp4+i;
		for(int j = i; j < temp4 ; j++)
		{
			String[] split = livetraffic[j].split(" ");
			int value = Integer.parseInt(split[1]);
			for(int k=0;k<tree.size();k++)
				if(tree.get(k).root_node.equals(split[0]))
					tree.get(k).heuristic = value;
		}
	}
}