package assignment;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UCS {
	BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
	private String[] livetrafficucs;	
	private int s;

	public UCS(String[] temporary_ucs,int size) throws IOException{
		livetrafficucs = new String[size];
		livetrafficucs = temporary_ucs;
		s = size;
	}
	
public void computeUCS() throws IOException
{											 
	Node pass = null;
	int temp=0;
	int comparer;
	StoreTree st11 = new StoreTree(livetrafficucs,s);
	st11.createTree();
	String start_state_ucs = livetrafficucs[1];
	String goal_state_ucs = livetrafficucs[2];
	List<Node> open = new ArrayList<Node>();
	List<Node> closed = new ArrayList<Node>();
	for(int i=0;i<st11.tree.size();i++)
		if(start_state_ucs.equals(st11.tree.get(i).root_node))
			open.add(st11.tree.get(i));
	while(!(open.isEmpty()))
		{
			Node currnode = open.remove(0);
			if(currnode.root_node.equals(goal_state_ucs))
			{
				List<Node> final_path1 = new ArrayList<Node>();
				int m;
				for(m=0;m<st11.tree.size();m++)
					if(st11.tree.get(m).root_node.equals(goal_state_ucs))
						pass= st11.tree.get(m);
				
				finalPathucs(pass,st11,final_path1);
				break;
			}
			for(int i=0;i<currnode.neighbour.size();i++)
			{
					comparer = currnode.cost.get(i) + currnode.realcost;
					if(!(open.contains(currnode.neighbour.get(i))) && !(closed.contains(currnode.neighbour.get(i))))
					{
						open.add(currnode.neighbour.get(i));
						currnode.neighbour.get(i).realcost = comparer;
						currnode.neighbour.get(i).previous = currnode;
					}
					else if(open.contains(currnode.neighbour.get(i)))
					{
						for(int k=0;k<open.size();k++)
							if(open.get(k).root_node.equals(currnode.neighbour.get(i).root_node))
								temp = k;
						if(comparer < open.get(temp).realcost)
						{
							open.remove(temp);
							open.add(currnode.neighbour.get(i));
							currnode.neighbour.get(i).realcost = comparer;
							currnode.neighbour.get(i).previous = currnode;
						}
					}
						else if(closed.contains(currnode.neighbour.get(i)))
					{
							for(int l=0;l<closed.size();l++)
								if(closed.get(l).root_node.equals(currnode.neighbour.get(i).root_node))
									temp = l;
							if(comparer < closed.get(temp).realcost)
							{
								closed.remove(temp);
								open.add(currnode.neighbour.get(i));
								currnode.neighbour.get(i).realcost = comparer;
								currnode.neighbour.get(i).previous = currnode;
							}	
					}
			}
			closed.add(currnode);

			for(int i=0;i<open.size();i++)
				for(int j=1;j<(open.size()-i);j++)
					if(open.get(j).realcost < open.get(j-1).realcost)
					{
						Node tempa= open.get(j);
						Node tempb= open.get(j-1);
						open.set(j, tempb);
						open.set(j-1, tempa);
					}
		}
	}
public void finalPathucs(Node o,StoreTree st2,List<Node> final1) throws IOException
{
	Node goal1 = o;
	StoreTree st3 = new StoreTree();
	st3=st2;
	int i,cost1=0;
	if(goal1.previous!= null)
	{	
		final1.add(goal1);
		finalPathucs(goal1.previous,st3,final1);
	}	
	else
	{
		final1.add(goal1);
		for(i = (final1.size()-1);i>=0;i--)
		{
		out.write(final1.get(i).root_node+" "+cost1);
		out.newLine();
		if(i!=0)
		{
		for(int j = 0;j < final1.get(i).neighbour.size();j++)
			 if(final1.get(i).neighbour.get(j).root_node.equals(final1.get(i-1).root_node))
				cost1=cost1+final1.get(i).cost.get(j);
		}}
		out.close();
	}
}
}
