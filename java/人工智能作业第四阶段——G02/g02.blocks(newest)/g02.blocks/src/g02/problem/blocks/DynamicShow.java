package g02.problem.blocks;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import core.problem.*;
import core.astar.*;

public class DynamicShow extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int[] s =new int[BlockProblem.SIZE*2+1];    
	{int k;
	//初始化状态数组
	for( k = 0; k < BlockProblem.SIZE; k ++ )
		s[k] = 1;
	for( k = 0; k < BlockProblem.SIZE; k ++ )
		s[BlockProblem.SIZE+k] = -1;
	s[BlockProblem.SIZE+k] = 0;
	}	
	int i = 0;
	String drawStat="BBBBBBBBBBBBWWWWWWWWWWWWE";
	//Move move = new Move();
	//Status curStat1;
	//Status curStat2 = new Status(s,0);	
	private Ellipse2D ellipse;
	private Node result;
	
    public DynamicShow(Node result) {
    	
    	this.result = result;
        this.addMouseListener(new HitTestAdapter());
        
    }
    @SuppressWarnings("static-access")
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(50, 50, 50));
        
		g2d.setColor(Color.black);
        for (int n = 0;n < BlockProblem.SIZE*2+1;n++){
        	g2d.drawRect(15+n*50, 10, 50, 50);
        }
        
        for (int i = 0;i < BlockProblem.SIZE*2+1;i++){
			if (drawStat.charAt(i) == 'W'){
				g2d.setColor(Color.green);
			    g2d.fillOval(20+i*50, 15, 40, 40);
			}
			else if (drawStat.charAt(i) == 'B'){
				g2d.setColor(Color.blue);
				g2d.fillOval(20+i*50, 15, 40, 40);
			}
			else {
				g2d.setColor(Color.CYAN);						
				g2d.fillOval(20+i*50, 15, 40, 40);
			}
        }
        g2d.setColor(Color.darkGray);
        ellipse = new Ellipse2D.Float(20f+(BlockProblem.SIZE*2+1)*50f, 10f, 50f, 50f);
        g2d.fill(ellipse);
    }
    public static void main(String[] args) {
    	
    	Problem block = new BlockProblem(
                12,
                "BBBBBBBBBBBBWWWWWWWWWWWWE",
                "WWWWWWWWWWWWBBBBBBBBBBBBE",
                1
        );
        AStar a = new AStar(block);
        Node node = a.Search();
			
	        JFrame frame = new JFrame("Hit testing");
	        frame.add(new DynamicShow(node));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
    	
    }
    class HitTestAdapter extends MouseAdapter implements Runnable {
        private Thread ellipseAnimator;
        public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (ellipse.contains(x, y)) {
            ellipseAnimator = new Thread(this);
            ellipseAnimator.start();
            }
        }
        public void run() {
            do {
                repaint();    			
                drawStat = ((BlockState)result.getState()).state;
                result = result.getParent();
    			
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }while (result != null);
        }
    }
}