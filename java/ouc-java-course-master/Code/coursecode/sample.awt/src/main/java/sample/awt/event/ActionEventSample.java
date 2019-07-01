package sample.awt.event;

import java.awt.*;
import java.awt.event.*;

/**
 * 事件监听示例
 * 
 * @author xiaodong
 *
 */
public class ActionEventSample {
	public static void main(String args[]) {
		Frame f = new Frame("ActionEvent Sample");
		f.setSize(400, 400);
		Button b = new Button("Press Me!");
		MyMonitor mm = new MyMonitor(); // 创建事件监听器
		b.addActionListener(mm); // 注册事件监听器

		f.add(b, BorderLayout.CENTER);
		// f.pack();
		f.setVisible(true);
	}
}

class MyMonitor implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		System.out.println("A button has been pressed!");
	}
}
