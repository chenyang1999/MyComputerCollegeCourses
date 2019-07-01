package sample.swing;

import java.awt.event.*;
import javax.swing.*;

/**
 * Swing示例
 * 
 * @author xiaodong
 *
 */
public class SwingSample implements ActionListener {

	public SwingSample() {
	}

	public void createUI() {
		JFrame jf = new JFrame("Swing Sample");

		JMenuBar jmb = new JMenuBar();
		JMenu menu_file = new JMenu("File");
		JMenu menu_help = new JMenu("Help");
		JMenuItem mi_new = new JMenuItem("New");
		JMenuItem mi_open = new JMenuItem("Open");
		JMenuItem mi_save = new JMenuItem("Save");
		mi_new.addActionListener(this);
		mi_open.addActionListener(this);
		mi_save.addActionListener(this);
		mi_new.setMnemonic('N');
		mi_open.setMnemonic('O');
		mi_save.setMnemonic('S');
		menu_file.setMnemonic('F');
		menu_help.setMnemonic('h');
		menu_file.add(mi_new);
		menu_file.add(mi_open);
		menu_file.add(mi_save);
		jmb.add(menu_file);
		jmb.add(menu_help);

		JToolBar jtb = new JToolBar();
		JButton button_new = new JButton("NEW");
		JButton button_open = new JButton("OPEN");
		JButton button_save = new JButton("SAVE");
		JButton button_error = new JButton("ERROR");

		button_new.setActionCommand("New");
		button_open.setActionCommand("Open");
		button_save.setActionCommand("Save");
		button_error.setActionCommand("Error");

		button_new.setToolTipText("Create a new file");
		button_open.setToolTipText("Open the file");
		button_save.setToolTipText("Save the file");
		button_new.addActionListener(this);
		button_open.addActionListener(this);
		button_save.addActionListener(this);
		button_error.addActionListener(this);

		jtb.add(button_new);
		jtb.add(button_open);
		jtb.add(button_save);
		jtb.add(button_error);

		JPanel jp = new JPanel();
		JButton button_start = new JButton("Start");
		JButton button_stop = new JButton("Stop");
		button_start.addActionListener(this);
		button_stop.addActionListener(this);

		jp.add(button_start);
		jp.add(button_stop);

		jf.setJMenuBar(jmb);
		jf.add(jtb, "North");
		jf.add(jp, "South");

		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();

		System.out.print(s);
		if (s.equals("Error")) {
			JOptionPane.showMessageDialog(null, "这是一个错误提示对话框", "错误提示", JOptionPane.ERROR_MESSAGE);
		} else if (s.equals("Confirm Quit")) {
			int result = JOptionPane.showConfirmDialog(null, "真的要退出程序么?", "请确认退出", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		} else if (s.equals("Warning")) {
			Object[] options = { "继续", "撤销" };
			int result = JOptionPane.showOptionDialog(null, "本操作可能导致数据丢失", "警告", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (result == 0)
				System.out.println("继续操作");
		} else if (s.equals("Input")) {
			String name = JOptionPane.showInputDialog("请输入姓名:");
			if (name != null)
				System.out.println("输入的姓名为" + name);
		} else if (s.equals("Choice")) {
			Object[] possibleValues = { "体育", "政治", "经济", "文化" };
			Object selectedValue = JOptionPane.showInputDialog(null, "Choice one", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
			String result = (String) selectedValue;
			if (result != null)
				System.out.println("你的选择是:" + result);
		}
	}

	public static void main(String[] args) {
		new SwingSample().createUI();
	}
}