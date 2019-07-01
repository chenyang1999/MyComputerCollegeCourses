package sample.exception;

import java.io.FileInputStream;

/**
 * IOException示例
 * 
 * 注意：此段代码无法编译（可以去掉注释观察）
 * 
 * @author xiaodong
 *
 */
public class IOExceptionSample {
	public static void main(String[] args) {
		/*
		FileInputStream in = new FileInputStream("myfile.txt"); // 打开文件流
		int b;
		b = in.read(); // 按字节读文件
		
		while (b != -1) {
			System.out.print((char) b);
			b = in.read();
		}
		in.close(); // 关闭流
		*/


		// 上述代码无法编译：只要是有可能出现IOException的Java代码，在
	    // 编译时就会出错，而不会等到运行时才发生。
		// 异常处理：
		// FileNotFoundException
		// IOException

		
	}
}
