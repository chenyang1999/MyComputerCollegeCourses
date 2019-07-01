package sample.exception;

/**
 * Hello Exception
 * 
 * 此程序会产生数组越界异常 java.lang.ArrayIndexOutOfBoundsException
 * 
 * @author xiaodong
 *
 */
public class FirstExceptionSample {
	public static void main(String[] args) {
		String friends[] = { "Lisa", "Bily", "Kessy" }; // 数组长度为3

		for (int i = 0; i < 5; i++) { // 遍历时出现数组越界，发生异常
			System.out.println(friends[i]);
		}
		System.out.println("\n This is the end"); // 因为没有恰当的异常处理代码，此语句不会执行
	}

}
