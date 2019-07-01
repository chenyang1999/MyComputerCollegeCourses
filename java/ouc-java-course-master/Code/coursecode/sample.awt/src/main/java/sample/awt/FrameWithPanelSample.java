package sample.awt;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

/**
 * AWT Panel示例
 * 
 * @author xiaodong
 *
 */
public class FrameWithPanelSample {

	public static void main(String args[]) {
		Frame f = new Frame("Frame with Panel");
		f.setLocation(100, 100);
		f.setSize(800, 600);
		f.setBackground(Color.black);
		f.setLayout(null); // 取消默认布局管理器
		
		Panel pan = new Panel();
		pan.setSize(400, 400);
		pan.setBackground(Color.magenta);
		pan.setLocation(40, 40);
		
		Panel pan1 = new Panel();
		pan1.setSize(100, 100);
		pan1.setBackground(Color.orange);
		
		pan1.setLocation(440, 440);
		
		f.add(pan);
		f.add(pan1);
		f.setVisible(true);
	}
}