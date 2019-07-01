package sample.generics;

import java.util.Date;
import java.util.Vector;

/**
 * Java泛型机制示例
 * 
 * @author xiaodong
 *
 */
@SuppressWarnings(value = { "unchecked" }) // 关闭编译提示或警告信息
public class VectorGenericsSample {

	public static void main(String[] args) {
		/*
		 * 使用无泛型机制的容器Vector
		 */
		
		Vector v = new Vector();
		v.addElement(new Person("Tom", 18)); // 放入容器的元素作为Object类型对待
		Person p = (Person) v.elementAt(0); // 从容器中取出元素时需要进行造型恢复元素的本来面目
		p.showInfo();

		/*
		 * 使用泛型化的Vector
		 */
		Vector<String> nv = new Vector<String>();
		nv.addElement("Kevin");
		nv.addElement("Nancy");
		// nv.addElement(new Date()); // 编译时出错
		// nv.addElement(new Integer(300)); // 编译时出错

		for (int i = 0; i < nv.size(); i++) {
			String name = nv.elementAt(i); // 并不需要进行强制类型转换
			System.out.println("The name is " + name);
		}
	}
}
