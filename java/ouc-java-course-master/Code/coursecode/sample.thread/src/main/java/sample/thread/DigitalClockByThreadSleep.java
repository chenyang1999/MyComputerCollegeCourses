package sample.thread;

import java.awt.Font;
import javax.swing.*;

public class DigitalClockByThreadSleep {
	public static void main(String[] args) {
		JFrame jf = new JFrame("Clock");
		jf.setSize(500, 500);
		jf.setLocation(100, 100);

		JLabel clock = new JLabel("clock");
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setFont(new Font("Times New Roman", Font.BOLD, 200));

		jf.add(clock, "Center");

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		Thread t = new TimerThread(clock);
		t.start();

	}
}

class TimerThread extends Thread {
	private JLabel clock;
	private int i;

	public TimerThread(JLabel clock) {
		this.clock = clock;
		this.i = 1;
	}

	public void run() {
		while (true) {
			clock.setText(String.valueOf(i++));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}