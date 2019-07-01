package sample.oop.poly;

/**
 * 多态性示例
 * 
 * @author xiaodong
 *
 */
public class PolymorphismSample {

	public void show(Person p) {
		System.out.println(p.getInfo());
	}
	
	public static void main(String[] args) {
		PolymorphismSample ps = new PolymorphismSample();
		Person p = new Person();
		ps.show(p);
		Student s = new Student();
		ps.show(s);
	}

}
