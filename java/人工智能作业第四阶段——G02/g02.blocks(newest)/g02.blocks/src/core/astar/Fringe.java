package core.astar;

import java.util.*;

import core.problem.State;

//The set of all leaf nodes available for expansion at any given point
public class Fringe {
	//��Fringe��ȡ����ɢֵ��С�Ľڵ�
	public Node pop() {
		/*
		Node min = nodes.get(0);
		for (Node node : nodes)	{
			if (min.compareTo(node) > 0) min = node;
		}

		nodes.remove(min);
		return min; //Fix me
		*/
		return nodes.poll();
	}

	public void insert(Node node){
		//Fix me
		nodes.add(node);
		map.put(node.getState().hashCode(), node);
	}

	public boolean contains(State state) {
	    /*
		for (Node node : nodes)	{
			if (node.getState().equals(state)) return true;
		}
		return false;
		*/
	    return map.containsKey(state.hashCode());
	}

	public Node revisited(State state) {
	    /*
		for (Node node : nodes)	{
			if (node.getState().equals(state)) return node;
		}
		return null; //Fix me
		*/
	    return this.map.get(state.hashCode());
	}

	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}

	public void replace(Node from, Node to) {
		nodes.remove(from);
		nodes.add(to);
		map.put(to.getState().hashCode(), to);
		//Fix me
	}

	//Data Structures for Fringe, implement it yourself.
//	ArrayList<Node> nodes = new ArrayList<>();
	PriorityQueue<Node> nodes=new PriorityQueue<Node>(11, new Comparator<Node>() {

		public int compare(Node n1, Node n2) {
			if(n1.compareTo(n2) > 0){
				return 1;
			}

			return -1;
		}
	});

	HashMap<Integer, Node> map = new HashMap<>();
}
