package sample.oop;

/**
 * 方法重载示例
 * 
 * @author xiaodong
 *
 */
public class MethodOverloadSample {

	private String title;
	
	/*
	 * 构造方法的重载
	 * 
	 * 方法名称相同且与类名一致，参数列表必须不同
	 */
	public MethodOverloadSample() {
		System.out.println("当前调用的是无参构造方法");
	}
	
	public MethodOverloadSample(String title) {
		System.out.println("当前调用的是有一个字符串类型参数的构造方法，参数为：" + title);
		this.title = title;
	}
	
	/*
	 * 普通方法的重载
	 * 
	 * 方法名称相同，参数列表必须不同
	 */
	public void display() {
		System.out.println("输出构造方法的参数: " + this.title);
	}
	
	public void display(int i) {
		System.out.println("输出整数: " + i);
	}

	public void display(double d) {
		System.out.println("输出符点数: " + d);
	}

	public void display(String s) {
		System.out.println("输出文本: " + s);
	}
	
	public int display(String s, String b) {
		System.out.println("输出文本: " + s);
		return 1;
	}

	public static void main(String args[]) {
		MethodOverloadSample t = new MethodOverloadSample();
		t.display(3);
		t.display(3.14);
		t.display("Hello, welcome!");
		
		MethodOverloadSample m = new MethodOverloadSample("构造方法重载测试");
		m.display();
		
	}
}
