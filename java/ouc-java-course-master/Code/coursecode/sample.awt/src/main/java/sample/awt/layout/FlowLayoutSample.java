package sample.awt.layout;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;

/**
 * AWT流式布局示例
 * 
 * @author xiaodong
 *
 */
public class FlowLayoutSample {
	public static void main(String args[]) {
		Frame f = new Frame("Flow Layout Sample");
		f.setLayout(new FlowLayout());
		
		Button button1 = new Button("Ok");
		Button button2 = new Button("Open");
		Button button3 = new Button("Close");
		Button button4 = new Button("Info");

		f.add(button1);
		f.add(button2);
		f.add(button3);
		f.add(button4);

		f.setSize(400, 200);
		f.setVisible(true);
	}
}
