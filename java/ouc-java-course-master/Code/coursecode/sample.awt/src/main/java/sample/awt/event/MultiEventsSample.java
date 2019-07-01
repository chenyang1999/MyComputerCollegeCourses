package sample.awt.event;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * AWT多事件监听器示例
 * 
 * @author xiaodong
 *
 */
public class MultiEventsSample extends Frame implements MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private Label label;

	public MultiEventsSample() {
		super("ActionEvent Sample");
		this.setSize(600, 400);
		label = new Label();
		label.setBackground(Color.YELLOW);
		this.add(label, BorderLayout.NORTH);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		String s = "鼠标移动位置 (" + e.getX() + ", " + e.getY() + ")";
		System.out.println(s);
		label.setText(s);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		label.setText("鼠标进入窗体");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		label.setText("鼠标按下");
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		label.setText("鼠标离开窗体");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		String s = "当前鼠标位置 (" + e.getX() + ", " + e.getY() + ")";
		System.out.println(s);
		label.setText(s);
	}

	public static void main(String[] args) {
		MultiEventsSample mes = new MultiEventsSample();
		mes.setVisible(true);
	}
}