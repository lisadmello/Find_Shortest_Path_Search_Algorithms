package assignment;
import java.io.*;

import java.util.*;
public class BFS {
BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
private String[] livetrafficbfs;	
private int s;

public BFS(String[] temporary_bfs,int size) throws IOException{
	livetrafficbfs = new String[size];
	livetrafficbfs = temporary_bfs;
	s = size;
}

public void computeBFS() throws IOException
{
	int flag =0;
	StoreTree st1 = new StoreTree(livetrafficbfs,s);			//create an object of StoreTree
	st1.createTree();											// create the tree with all input nodes
	String start_state_bfs = livetrafficbfs[1];					//save start state
	String goal_state_bfs = livetrafficbfs[2];					//save end state
	List<Node> queue = new ArrayList<Node>();					//create the BFS queue
	for(int i=0;i<st1.tree.size();i++)										//add the start element into the queue
		if(start_state_bfs.equals(st1.tree.get(i).root_node))				
			queue.add(st1.tree.get(i));
	for(int j=0;j<st1.tree.size();j++)										//set the start node to be explored
		if(st1.tree.get(j).root_node.equals(queue.get(0).root_node))
			st1.tree.get(j).explored = true;
	while(flag == 0)
	{
		Node a = queue.remove(0);											// remove the first element
		if(a.root_node.equals(goal_state_bfs))								//if goal is found
		{
			List<Node> final_path = new ArrayList<Node>();
			finalPathbfs(a,st1,final_path);									//display path
			flag = 1;
			break;
		}
		else
		{
			for(int i=0;i<a.neighbour.size();i++)							//for each neighbour of current node
			{
					if(a.neighbour.get(i).explored.equals(false))			//if the neighbour has not been explored
					{
						queue.add(a.neighbour.get(i));						//add the neighbour to the queue
						a.neighbour.get(i).explored = true;					//mark the neighbour as explored
						a.neighbour.get(i).previous = a;					//store the parent of neighbour as current node
					}
			}
		}
	}
	if(flag==0)																//if goal not found
		System.out.println("NO GOAL!");
}

public void finalPathbfs(Node o,StoreTree st2,List<Node> final1) throws IOException
{
	Node goal1 = o;
	StoreTree st3 = new StoreTree();
	st3=st2;
	int temp,i,stepcost=0;
	
	temp = st2.tree.indexOf(goal1);
	if(goal1.previous!=null)								//until previous of node is not null
	{
		final1.add(goal1);									//add the node to the path
		finalPathbfs(goal1.previous,st3,final1);			//process to the parent of this node
	}	
	else
	{
		final1.add(goal1);									//add the final node in the list(start)
		for(i = (final1.size()-1);i>=0;i--)					//from last value till first
		{
		out.write(final1.get(i).root_node+" "+stepcost);
		out.newLine();
		stepcost=stepcost+1;
		}
		out.close();
	}
}
}
