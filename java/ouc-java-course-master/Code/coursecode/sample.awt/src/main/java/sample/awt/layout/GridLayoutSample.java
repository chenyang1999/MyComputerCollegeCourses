package sample.awt.layout;

import java.awt.Frame;
import java.awt.Button;
import java.awt.GridLayout;

/**
 * AWT网格布局示例
 * @author xiaodong
 *
 */
public class GridLayoutSample {
	public static void main(String args[]) {
		Frame f = new Frame("Grid Layout Sample");
		f.setSize(400, 400);
		f.setLayout(new GridLayout(3, 2));

		Button b1 = new Button("b1");
		Button b2 = new Button("b2");
		Button b3 = new Button("b3");
		Button b4 = new Button("b4");
		Button b5 = new Button("b5");
		Button b6 = new Button("b6");

		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);

		f.pack(); // pack()方法是Window类中定义的，其功能是调整此窗口的大小，使之紧凑化以适合其中所包的组件的原始尺寸和布局
		f.setVisible(true);
	}
}
