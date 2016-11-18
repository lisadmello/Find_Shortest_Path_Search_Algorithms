package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Astar {

	BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
	private String[] livetrafficastar;
	private int s;

	public Astar(String[] temporary_astar, int size) throws IOException {
		livetrafficastar = new String[size];
		livetrafficastar = temporary_astar;
		s = size;
	}

	public void computeAstar() throws IOException {
		Node pass = null;
		int comparer, temp = 0;
		StoreTree st13 = new StoreTree(livetrafficastar, s);
		st13.createTree();
		String start_state_astar = livetrafficastar[1];
		String goal_state_astar = livetrafficastar[2];
		List<Node> open = new ArrayList<Node>();
		List<Node> closed = new ArrayList<Node>();
		for (int i = 0; i < st13.tree.size(); i++)
			if (start_state_astar.equals(st13.tree.get(i).root_node))
				open.add(st13.tree.get(i));
		while (!(open.isEmpty())) {
			Node currnode = open.remove(0);
			if (currnode.root_node.equals(goal_state_astar)) {
				List<Node> final_path1 = new ArrayList<Node>();
				int m;
				for (m = 0; m < st13.tree.size(); m++)
					if (st13.tree.get(m).root_node.equals(goal_state_astar))
						pass = st13.tree.get(m);
				finalPathastar(pass, st13, final_path1);
				break;
			} else {
				for (int i = 0; i < currnode.neighbour.size(); i++) {
					comparer = currnode.cost.get(i) + currnode.realcost + currnode.neighbour.get(i).heuristic;
					if (!(open.contains(currnode.neighbour.get(i))) && !(closed.contains(currnode.neighbour.get(i)))) {
						open.add(currnode.neighbour.get(i));
						currnode.neighbour.get(i).realcost = currnode.cost.get(i) + currnode.realcost;
						currnode.neighbour.get(i).previous = currnode;
						currnode.neighbour.get(i).f_value = comparer;
					} else if (open.contains(currnode.neighbour.get(i))) {
						for (int k = 0; k < open.size(); k++)
							if (open.get(k).root_node.equals(currnode.neighbour.get(i).root_node))
								temp = k;
						if (comparer < open.get(temp).f_value) {
							open.remove(temp);
							open.add(currnode.neighbour.get(i));
							currnode.neighbour.get(i).realcost = currnode.cost.get(i) + currnode.realcost;
							currnode.neighbour.get(i).previous = currnode;
							currnode.neighbour.get(i).f_value = comparer;

						}
					} else if (closed.contains(currnode.neighbour.get(i))) {
						for (int l = 0; l < closed.size(); l++)
							if (closed.get(l).root_node.equals(currnode.neighbour.get(i).root_node))
								temp = l;
						if (comparer < closed.get(temp).f_value) {
							closed.remove(temp);
							open.add(currnode.neighbour.get(i));
							currnode.neighbour.get(i).realcost = currnode.cost.get(i) + currnode.realcost;
							currnode.neighbour.get(i).previous = currnode;
							currnode.neighbour.get(i).f_value = comparer;
						}
					}
				}

				closed.add(currnode);
				for (int i = 0; i < open.size(); i++)
					for (int j = 1; j < (open.size() - i); j++)
						if ((open.get(j).f_value) < (open.get(j - 1).f_value)) {
							Node tempa = open.get(j);
							Node tempb = open.get(j - 1);
							open.set(j, tempb);
							open.set(j - 1, tempa);
						}
			}
		}
	}

	public void finalPathastar(Node o, StoreTree st2, List<Node> final1) throws IOException {
		Node goal1 = o;
		StoreTree st3 = new StoreTree();
		st3 = st2;
		int i, cost1 = 0;
		if (goal1.previous != null) {
			final1.add(goal1);
			finalPathastar(goal1.previous, st3, final1);
		} else {
			final1.add(goal1);
			for (i = (final1.size() - 1); i >= 0; i--) {
				out.write(final1.get(i).root_node + " " + cost1);
				out.newLine();
				if (i != 0) {
					for (int j = 0; j < final1.get(i).neighbour.size(); j++)
						if (final1.get(i).neighbour.get(j).root_node.equals(final1.get(i - 1).root_node))
							cost1 = cost1 + final1.get(i).cost.get(j);
				}
			}
			out.close();
		}
	}
}