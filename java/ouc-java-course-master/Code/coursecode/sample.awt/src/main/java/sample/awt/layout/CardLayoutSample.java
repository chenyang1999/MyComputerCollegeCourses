package sample.awt.layout;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;

/**
 * AWT卡片示例
 * 
 * @author xiaodong
 *
 */
public class CardLayoutSample {
	public static void main(String args[]) {
		Frame f = new Frame("Card Layout Sample");
		f.setSize(500, 400);
		CardLayout cl = new CardLayout();
		f.setLayout(cl);

		Button[] b = new Button[4];

		for (int i = 0; i < 4; i++) {
			b[i] = new Button("第" + i + "页");
			f.add(b[i], "page" + i);
		}
		b[0].setBackground(Color.green);
		b[1].setBackground(Color.blue);
		b[2].setBackground(Color.red);

		// f.pack();
		f.setVisible(true);

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cl.next(f);
		}
	}
}
