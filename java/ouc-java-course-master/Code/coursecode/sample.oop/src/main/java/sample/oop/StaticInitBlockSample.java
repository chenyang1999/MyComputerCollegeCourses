package sample.oop;

/**
 * 计数器类
 * 
 * @author xiaodong
 *
 */
class Counter2 {

	public static int total = 100;
	
	static {
		System.out.println("-------------------------");
		System.out.println("发生在静态初始化块");
		System.out.println("-------------------------");
	}

	{
		System.out.println("*************************");
		System.out.println("发生在初始化块");
		System.out.println("*************************");
	}

	public Counter2() {
	}

	public Counter2(int a) {
		System.out.println("---执行构造方法体中语句---");
	}
}

/**
 * 初始化块示例
 * 
 * @author xiaodong
 *
 */
public class StaticInitBlockSample {

	public static void main(String[] args) {
		//System.out.println("Current total = " + Counter2.total);
		//System.out.println("Current total = " + Counter2.total);
		
		//注释掉上述两行再看执行情况
		new Counter2();
		new Counter2(10);
	}
}
