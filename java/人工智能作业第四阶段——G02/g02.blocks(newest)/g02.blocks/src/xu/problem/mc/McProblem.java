package xu.problem.mc;

import java.util.ArrayList;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;

public class McProblem extends Problem {

	@Override
	public State result(State parent, Action action) {
		int m = ((McState) parent).getM();
		int c = ((McState) parent).getC();
		
		int m1 = ((McAction) action).getM(); 
		int c1 = ((McAction) action).getC();
		int d = ((McAction) action).getD();
		
		if (d == 1) {	//从左岸划到右岸
			return new McState(m - m1, c - c1, 0).setSize(size); //左岸人数减少，船到了右岸
		}
		else {	//从右岸划到左岸
			return new McState(m + m1, c + c1, 1).setSize(size); //左岸人数增多，船到了左岸
		}
	}

	@Override
	public int stepCost(State parent, Action action) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int heuristic(State state) {		
		// TODO Auto-generated method stub
		
		McState s = (McState) state;
		return s.heuristic();
	}

	//左右岸，船上的任意情况下，传教士野人个数分别为m和c时，是否安全
	private boolean isSafe(int m, int c) {
		return m == 0 || m >= c;
	}
	
	@Override
	public ArrayList<Action> Actions(State state) {
		// TODO Auto-generated method stub
		
		ArrayList<Action> actions = new ArrayList<>();
		
		int m = ((McState) state).getM();
		int c = ((McState) state).getC();
		int b = ((McState) state).getB();	//在左岸还是右岸？
		
		//如果船在右岸，计算出右岸的人数
		if (b == 0) {
			m = size - m;
			c = size - c;
		}
		
		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= c; j++) {
				if (i + j > 0 && i + j <= k && isSafe(i, j) && 
					isSafe(m - i, c - j) && 
					isSafe(size - m + i, size - c + j))
				{
					McAction action = new McAction(i, j, b); //划船方向，从所在岸划向对岸
					actions.add(action);
				}
			}
		
		return actions;
	}

	@Override
	public void drawWorld() {
		// TODO Auto-generated method stub
		this.getInitialState().draw();
	}

	@Override
	public void simulateResult(State parent, Action action) {
		// TODO Auto-generated method stub
	
		State child = result(parent, action);
		action.draw();
		child.draw();

	}

	public McProblem(McState initialState, McState goal, int size, int k) {
		super(initialState, goal);
		this.size = size;
		this.k = k;
	}
	
	public McProblem(int size, int k) {
		super(new McState(size, size, 1).setSize(size), new McState(0, 0, 0).setSize(size));
		this.size = size;
		this.k = k;
	}

	private int size;	//传教士和野人的个数，问题的规模
	private int k;		//船上可载人数的上限
	
	@Override
	public boolean solvable() {
		// TODO Auto-generated method stub
		return true;
	}
}
