package sample.oop.singleton;

/**
 * 单例设计模式测试方法示例
 * 
 * @author xiaodong
 *
 */
public class TestSingle {

	public static void md() {
		Single s2 = Single.getSingle(); // 获取实例
		System.out.println("当前实例的Hash值为 " + s2.hashCode());
		System.out.println(s2.getName()); // 确实只有一个
	}

	public static void main(String args[]) {
		Single s1 = Single.getSingle(); // 获取实例（有且仅有一个）
		System.out.println("当前实例的Hash值为 " + s1.hashCode());
		s1.setName("Wang Xiaodong"); 
		md();
	}
}