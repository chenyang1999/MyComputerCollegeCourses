package sample.exception;

/**
 * 处理Hello Exception代码中异常示例 
 * 
 * 此程序会产生数组越界异常 java.lang.ArrayIndexOutOfBoundsException
 * 
 * @author xiaodong
 */
public class HandleFirstExceptionSample {
	public static void main(String[] args) {
		String friends[] = { "Lisa", "Bily", "Kessy" }; // 数组长度为3

//		try {
			for (int i = 0; i < 5; i++) { // 遍历时出现数组越界，发生ArrayIndexOutOfBoundsException异常
				System.out.println(friends[i]);
			}
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("Index out of bounds");
//			e.printStackTrace();
//		}
		System.out.println("\n This is the end"); // 如果合理处理了异常，此语句执行
	}

}
