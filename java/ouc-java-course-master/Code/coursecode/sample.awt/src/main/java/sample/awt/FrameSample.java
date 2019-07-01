package sample.awt;

import java.awt.Frame;

/**
 * 第一个AWT窗口程序：窗口顶级停靠的Frame
 * 
 * @author xiaodong
 *
 */
public class FrameSample {

	public static void main(String args[]) {
		Frame f = new Frame("Hello Java AWT");
		f.setLocation(100, 100);
		f.setSize(800, 600);
		f.setVisible(true);
	}
}
