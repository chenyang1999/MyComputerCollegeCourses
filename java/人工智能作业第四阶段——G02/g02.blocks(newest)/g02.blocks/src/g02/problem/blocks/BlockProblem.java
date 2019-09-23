package g02.problem.blocks;

import java.util.ArrayList;

import core.problem.Problem;
import core.problem.Action;
import core.problem.State;

public class BlockProblem extends Problem {

	public static int SIZE;
    private int n;
    private int _n;
    private int type;
    public BlockProblem(int n, String init_state, String final_state, int i) {
        super();
        SIZE = n;
        this.n = n;
        this._n = (-~n) >> 1;
        this.setInitialState(new BlockState(init_state));
        this.setGoal(new BlockState(final_state));
        this.type = i;
    }

    @Override
    public final void simulateResult(State parent, Action action) {
        action.draw();
        result(parent, action).draw();
    }

    @Override
    public final void drawWorld() {
        this.getInitialState().draw(); 
    }

    @Override
    public final ArrayList<Action> Actions(State state) {
        ArrayList<Action> actions = new ArrayList<>();
        BlockState s = (BlockState) state;
        String state_s = s.getState();
        int step = 0;
        if(this.type == 0)
        	step = 3;
        else
        	step = _n+1;

        for (
                int j = 0, _j, space = state_s.indexOf("E"),
                length = state_s.length();
                j <= step;
                j++
        ) {
            _j = -~j; 
            if (space > j) { 
                actions.add(new BlockAction(
                        space - _j,
                        space,
                        state_s
                ));
            }
            if (space < length - _j) { 
                actions.add(new BlockAction(
                        space + _j,
                        space,
                        state_s
                ));
            }
        }

        return actions;
    }

    @Override
    public final int heuristic(State state) {
        return ((BlockState) state).heuristic();
    }

    @Override
    public final int stepCost(State parent, Action action) {
        BlockAction a = (BlockAction) action;
        int x = a.getX1() - a.getX2();
        return (x ^ (x >> 31)) - (x >> 31) - 1; 
    }

    @Override
    public final State result(State parent, Action action) {
        BlockState bs = (BlockState) parent;
        BlockAction ba = (BlockAction) action;
        int x1 = ba.getX1();
        int x2 = ba.getX2();
        char[] s = bs.getState().toCharArray();
        char tmp = s[x1];
        s[x1] = s[x2];
        s[x2] = tmp;
        return new BlockState(new String(s));
    }

    @Override
    public final boolean solvable() {
        return true;
    }
}
