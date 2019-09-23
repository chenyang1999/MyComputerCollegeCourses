package core.astar;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;

public class AStar {
	
	public AStar(Problem problem) {
		super();
		this.problem = problem;
	}
	
	public Node childNode(Node parent, Action action) {
		State state = problem.result(parent.getState(), action);
		int pathCost = parent.getPathCost() + problem.stepCost(parent.getState(), action);
		int heuristic = problem.heuristic(state);
		return new Node(state, parent, action, pathCost, heuristic);
	}
	
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	public Node Search()
	{
		State initState = problem.getInitialState();
		Node node = new Node(initState, null, null, 0, problem.heuristic(initState));
		
		Fringe fringe = new Fringe();
		fringe.insert(node);
		
		Explored explored = new Explored();
		
		while (true) {
			
			if (fringe.isEmpty())	return null;	
			
			node = fringe.pop(); //choose the lowest-cost node in frontier
			if (problem.goalTest(node.getState())) return node;
			explored.insert(node.getState());
			
			for (Action action : problem.Actions(node.getState())) {
				Node child = childNode(node, action);
				if (!explored.contains(child.getState()) && !fringe.contains(child.getState())) {
					fringe.insert(child);
				}
				else {
					Node revisited = fringe.revisited(child.getState());
					if (revisited != null && revisited.evaluation() > child.evaluation()) {
						fringe.replace(revisited, child);	
					}
				}
			}
		}	
	}
	
	public void solution(Node node)
	{
		// Fix me
	}
	
	private Problem problem;
}
