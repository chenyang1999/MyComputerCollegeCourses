package sample.awt.event;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * AWT适配器使用示例
 * 
 * @author xiaodong
 *
 */
public class EventAdapterSample {

	public static void main(String[] args) {
		Frame f = new Frame("AWT Event Adapter Sample");
		f.setSize(400, 400);
		Label label = new Label();
		label.setText("这次你终于可以把我正常关掉了！");
		f.add(label, BorderLayout.CENTER);

		// --------------------------------------------------
		//MyAdapter m = new MyAdapter();
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		// --------------------------------------------------
		f.setVisible(true);
	}
}

/**
 * 自定义适配器
 * 
 * @author xiaodong
 *
 */

class MyAdapter extends WindowAdapter {

	/*
	 * 仅重写所需要的windowClosing方法
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(1);
	}
}
