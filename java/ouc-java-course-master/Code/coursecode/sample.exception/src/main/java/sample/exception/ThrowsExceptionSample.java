package sample.exception;

import java.io.*;

/**
 * 抛出异常，由调用者处理异常的示例
 * 
 * @author xiaodong
 *
 */
public class ThrowsExceptionSample {

	/**
	 * 可能产生IOException，但本方法不直接处理，而是交给其调用者处理该异常 采用throws方式抛出异常到上层调用者
	 * 
	 * @throws IOException
	 */
	public void readFile() throws IOException { // 声明抛出IOException

		// 本方法代码会产生FileNotFoundException等IOException
		FileInputStream in = new FileInputStream("myfile.txt");
		int b;
		b = in.read();
		while (b != -1) {
			System.out.print((char) b);
			b = in.read();
		}
		in.close();
	}

	/**
	 * 在main方法中处理所调用方法readFile抛出的异常
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ThrowsExceptionSample t = new ThrowsExceptionSample();
		try { // 异常捕获
			t.readFile();
		} catch (IOException e) { // 异常处理
			System.out.println(e);
		}
	}
}
