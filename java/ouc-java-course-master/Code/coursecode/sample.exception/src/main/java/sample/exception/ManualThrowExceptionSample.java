package sample.exception;

import java.util.Scanner;

public class ManualThrowExceptionSample {
	private Scanner scan;

	/**
	 * 输入年龄，并判断年龄的合理性 采用人工抛出异常的方式，使用异常处理机制建立年龄合理性检查逻辑
	 * 
	 * 注意：代码仅用于演示。 异常处理机制并不是高效的操作，利用异常处理机制实现数据取值范围的检查并不太合适。
	 * 
	 * @return
	 */
	public int inputAge() {
		int result = -1;
		scan = new Scanner(System.in); // 对接标准输入流

		while (true) {
			try { // 试图捕获异常
				result = scan.nextInt(); // 阻塞程序，等待用户输入
				if (result < 0 || result > 130) { // 年龄取值范围判断
					Exception me = new Exception("You come from Mars? "); // 创建异常类对象，自定义message
					throw me; // 人工抛出异常对象
				}
				break;
			} catch (Exception e1) { // 处理异常
				System.out.println(e1.getMessage() + "Please input your age again: ");
				// scan.next(); 把未消耗的数据消耗掉，否则会导致无限循环问题
				continue; // 继续循环输入
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ManualThrowExceptionSample t = new ManualThrowExceptionSample();
		System.out.print("Please input your age: ");
		System.out.print("Your age: " + t.inputAge());
	}
}
