package g02.problem.blocks;

import core.problem.Action;
public class BlockAction extends Action {

    private String state;
    private int x1;
    private int x2;

    public BlockAction(int x1, int x2, String state) {
        super();
        this.x1 = x1;
        this.x2 = x2;
        this.state = state;
    }

    public final int getX1() {
        return this.x1;
    }

    public final int getX2() {
        return this.x2;
    }

    @Override
    public final void draw() {
    	
    }
}
