package sample.oop.singleton;

/**
 * 单例设计模式示例
 * 
 * @author xiaodong
 *
 */
public class Single {
	private String name;
	private static Single onlyone = new Single(); // 成员变量为static

	private Single() { // 构造方法为private限制
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Single getSingle() { // 单例获取方法为static
		return onlyone;
	}
}
