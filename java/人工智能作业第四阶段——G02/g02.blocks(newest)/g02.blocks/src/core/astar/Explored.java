package core.astar;
import java.util.HashSet;

import core.problem.State;

//The set that remembers every expanded node
public class Explored {
	
	public void insert(State state){
//		nodes.add(state);
		//Fix me
		nodes.add(state.hashCode());
	}
	
	public boolean contains(State state) {
//		return nodes.contains(state); //Fix me
		return nodes.contains(state.hashCode());
	}
	
//	ArrayList<State> nodes = new ArrayList<>();
	//Data Structures for Explored, implement it yourself.
	HashSet<Integer> nodes = new HashSet<>();
}
