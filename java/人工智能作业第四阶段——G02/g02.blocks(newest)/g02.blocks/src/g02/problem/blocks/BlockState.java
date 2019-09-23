package g02.problem.blocks;

import core.problem.State;
public class BlockState extends State {

    private long id;
    public String state;
    private int heuristic;

    public BlockState() {
        super();
    }

    public BlockState(String state) {
        super();

        this.state = state;
        this.heuristic = 0;
        char[] state_c = this.state.toCharArray();
        int length = this.state.length();

        for (int i = 0, _length = ~-length; i < _length; i++) {
            if (state_c[i] == 'B') {
                for (int j = i + 1; j < length; j++) {
                    if (state_c[j] == 'W') {
                        // The less 'W' after 'B', the closer to the end.
                        this.heuristic++;
                    }
                }
            }
        }

        // Calculate a unique id for each state
        for (int i = 0, base = 0; i < length; i++, base++) {
            this.id += (int) state_c[i] * base;
        }
        this.id *= this.heuristic;
    }

    public long getId() {
        return this.id;
    }

    public final String getState() {
        return this.state;
    }

    public final int heuristic() {
        return this.heuristic;
    }

    @Override
    public final void draw() {
    	System.out.print(this.state + "\n");
    }

    @Override
    public final boolean equals(Object obj) {
        return this.id == ((BlockState)obj).id;
    }

    @Override
    public final int hashCode() {
        return (int) this.id;
    }
}
