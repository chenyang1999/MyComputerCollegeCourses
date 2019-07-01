package sample.awt.layout;

import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.Button;

/**
 * AWT边界布局示例
 * 
 * @author xiaodong
 *
 */
public class BorderLayoutSample {
	public static void main(String args[]) {
		Frame f = new Frame("Border Layout Sample");
		Button bn = new Button("BN");
		Button bs = new Button("BS");
		Button bw = new Button("BW");
		Button be = new Button("BE");
		Button bc = new Button("BC");
		
		f.add(bn, "North");  // 等同于f.add(bn, BorderLayout.NORTH)
		f.add(bs, "South");
		f.add(bw, "West");
		f.add(be, "East");
		f.add(bc, "Center");
		
		f.setSize(600, 400);
		f.setVisible(true);
	}
}