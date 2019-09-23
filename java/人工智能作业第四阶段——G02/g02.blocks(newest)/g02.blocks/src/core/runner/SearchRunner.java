package core.runner;

import core.astar.AStar;
import core.astar.Node;
import core.problem.Problem;
import g02.problem.blocks.BlockProblem;

public class SearchRunner {

    public SearchRunner() {
    }

    public static void main(String[] args) {
      testBlock(); // Sliding Block 
    }

    public static void process(Problem problem) {
        long start_time = System.currentTimeMillis();
        AStar a = new AStar(problem);
        Node node = a.Search();
        System.out.println(node.getPathCost());
        long end_time = System.currentTimeMillis();
        backTrace(node);

        long total_time = end_time - start_time;
        System.out.print(String.format( "%dms (%fs)", total_time, total_time / 1000.0));
    }

    public static void testBlock() {

//        Problem block = new BlockProblem(
//                9,
//                "BBBBBBBBBWWWWWWWWWE",
//                "WWWWWWWWWBBBBBBBBBE"
//                1
//        );
    

//        Problem block = new BlockProblem(
//                12,
//                "BBBBBBBBBBBBWWWWWWWWWWWWE",
//                "WWWWWWWWWWWWBBBBBBBBBBBBE"
//    	          0
//        );
      
//经典类型（最多只能条约两个将牌）
//        Problem block = new BlockProblem(
//                20,
//                "BBBBBBBBBBBBBBBBBBBBWWWWWWWWWWWWWWWWWWWWE",
//                "WWWWWWWWWWWWWWWWWWWWBBBBBBBBBBBBBBBBBBBBE",
//                0
//        );
    	
//变种（可以跳跃（n+1）/2个将牌）
        Problem block = new BlockProblem(
                20,
                "BBBBBBBBBBBBBBBBBBBBWWWWWWWWWWWWWWWWWWWWE",
                "WWWWWWWWWWWWWWWWWWWWBBBBBBBBBBBBBBBBBBBBE",
                1
        );

    
        process(block);
    }

    public static void backTrace(Node node) {
        int steps = 1;
        node.getState().draw();
        node.getAction().draw();

        while (node.getParent() != null) {
            node = node.getParent();
            node.draw();
            if (node.getAction() != null) {
                node.getAction().draw();
            }
            steps++;
        }
        System.out.print(String.valueOf(steps)+"   ");
    }
}
