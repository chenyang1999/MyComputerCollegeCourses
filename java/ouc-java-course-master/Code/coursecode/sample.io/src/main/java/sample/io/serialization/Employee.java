package sample.io.serialization;

import java.io.Serializable;

/**
 * 支持序列化的对象类示例
 * 
 * @author xiaodong
 *
 */
public class Employee implements Serializable { // 需要实现Serializable接口

	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private String dept;

	public Employee() {

	}

	/**
	 * 构造方法 三个参数
	 * 
	 * @param name
	 * @param age
	 * @param dept
	 */
	public Employee(String name, int age, String dept) {
		this.name = name;
		this.age = age;
		this.dept = dept;
	}

	/**
	 * 显示Employee完整信息的方法
	 */
	public void showInfo() {
		System.out.println("Name: " + name + "\tAge: " + age + "\tDept: " + dept);
	}

}