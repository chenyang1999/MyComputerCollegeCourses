package sample.oop;

/**
 * 计数器类
 * 
 * @author xiaodong
 *
 */
class Counter {
	public String name = "计数器"; // 非静态属性
	public static int total = 0; // 静态属性

	public Counter() {
		total++;
	}

	// 静态方法
	public static int getTotalCount() {
		return total;
	}
}

/**
 * 静态属性示例
 * 
 * @author xiaodong
 *
 */
public class StaticMemberAndMethodSample {

	public static void main(String[] args) {
		int times = 0;

		while (times < 9000) {
			new Counter();
			System.out.println("当前为计数器的第 " + Counter.total + " 次实例化");
			times = Counter.total;
		}

		// System.out.println("\n计数器被实例化了 " + Counter.total + " 次");
		System.out.println("\n计数器被实例化了 " + Counter.getTotalCount() + " 次");
	}
}
