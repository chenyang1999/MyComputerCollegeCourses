package sample.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Swing JTable示例
 * 
 * @author xiaodong
 *
 */
public class JTableSample {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Table␣Example");

		Object data[][] = { { 1, "张三", "男", "18", "010.82607080" }, { 2, "李四", "女", "24", "010.82607080" },
				{ 3, "王五", "男", "30", "010.82607080" }, // ... ...
		};

		String columnNames[] = { "编号", "姓名", "性别", "年龄", "电话" };
		JTable table = new JTable(data, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane pane = new JScrollPane(table);
		myFrame.add("Center", pane);
		myFrame.setSize(450, 250);
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		myFrame.setVisible(true);
	}
}