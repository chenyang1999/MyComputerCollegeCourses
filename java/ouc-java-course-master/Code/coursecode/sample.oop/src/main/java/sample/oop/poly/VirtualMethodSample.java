package sample.oop.poly;

/**
 * 虚方法调用、造型和instanceof操作符示例
 * 
 * Person父类和Student子类，Student子类重写了Person父类提供的getInfo()方法
 * 
 * 虽然用Person父类类型的引用指向了Student子类的对象，
 * 但根据虚方法调用原则，系统依然会按运行时对象的真正类型来调用Student类型对象的getInfo()方法。
 * 
 * 对象造型
 * instanceof操作符
 * 
 * @author xiaodong
 *
 */
public class VirtualMethodSample {

	public static void main(String[] args) {
		Person p = new Student();
		p.setName("Wang Xiaodong");
		p.setAge(36);
		// p.setSchool("OUC"); // p无法调用setSchool方法

		if (p instanceof Student) {
			Student s = (Student) p;
			s.setSchool("OUC"); // p的能力被恢复，可以调用setSchool方法
		}

		System.out.println(p.getInfo()); // 虚方法，实际调用的是Student类型对象的getInfo()方法
	}
}
