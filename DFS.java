package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DFS {
	BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
	private String[] livetrafficdfs;
	private int s;

	public DFS(String[] temporary_dfs, int size) throws IOException {
		livetrafficdfs = new String[size];
		livetrafficdfs = temporary_dfs;
		s = size;
	}

	public void computeDFS() throws IOException {
		int flag = 0, random;
		StoreTree st12 = new StoreTree(livetrafficdfs, s);
		st12.createTree(); // create the tree with all input nodes
		String start_state_dfs = livetrafficdfs[1];
		String goal_state_dfs = livetrafficdfs[2];
		List<Node> dfsqueue = new ArrayList<Node>();
		List<Node> closed = new ArrayList<Node>();
		for (int i = 0; i < st12.tree.size(); i++)
			if (start_state_dfs.equals(st12.tree.get(i).root_node))
				dfsqueue.add(st12.tree.get(i));
		for (int j = 0; j < st12.tree.size(); j++)
			if (st12.tree.get(j).root_node.equals(dfsqueue.get(0).root_node))
				st12.tree.get(j).explored = true;
		while (flag == 0) {
			int g, temp = 0;
			Node a = dfsqueue.remove(0);
			if (a.root_node.equals(goal_state_dfs)) {
				List<Node> final_path = new ArrayList<Node>();
				finalPathdfs(a, st12, final_path);
				flag = 1;
				break;
			}
			for (int i = (a.neighbour.size() - 1); i >= 0; i--) {
				g = 1 + a.realcost;
				if (a.neighbour.get(i).explored == false) {
					dfsqueue.add(0, a.neighbour.get(i));
					a.neighbour.get(i).explored = true;
					a.neighbour.get(i).realcost = g;
					a.neighbour.get(i).previous = a;
				} else if ((a.neighbour.get(i).explored == true) && (closed.contains(a.neighbour)))
					random = 5;
				else {
					for (int k = 0; k < dfsqueue.size(); k++)
						if (dfsqueue.get(k).root_node.equals(a.neighbour.get(i).root_node))
							temp = k;
					if (g < dfsqueue.get(temp).realcost) {
						dfsqueue.remove(temp);
						dfsqueue.add(0, a.neighbour.get(i));
						a.neighbour.get(i).realcost = g;
						a.neighbour.get(i).previous = a;
					}
				}
			}
			closed.add(a);
		}
	}

	public void finalPathdfs(Node o, StoreTree st2, List<Node> final1) throws IOException {
		Node goal1 = o;
		StoreTree st3 = new StoreTree();
		st3 = st2;
		int i, stepcost = 0;

		if (goal1.previous != null) {
			final1.add(goal1);
			finalPathdfs(goal1.previous, st3, final1);
		} else {
			final1.add(goal1);
			for (i = (final1.size() - 1); i >= 0; i--) {
				out.write(final1.get(i).root_node + " " + stepcost);
				out.newLine();
				stepcost = stepcost + 1;
			}
			out.close();
		}
	}
}
