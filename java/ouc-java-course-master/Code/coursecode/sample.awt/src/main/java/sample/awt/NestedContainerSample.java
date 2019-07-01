package sample.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;

/**
 * AWT容器嵌套示例
 * 
 * @author xiaodong
 *
 */
public class NestedContainerSample {

	public static void main(String args[]) {
		Frame f = new Frame("Nested Container");
		f.setSize(600, 400);

		Button b0 = new Button("display area");
		Panel p = new Panel();
		p.setLayout(new GridLayout(2, 2));
		Button b1 = new Button("1");
		Button b2 = new Button("2");
		Button b3 = new Button("3");
		Button b4 = new Button("4");
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		f.add(b0, "North");
		f.add(p, "Center");
		// f.pack();
		f.setVisible(true);
	}
}
