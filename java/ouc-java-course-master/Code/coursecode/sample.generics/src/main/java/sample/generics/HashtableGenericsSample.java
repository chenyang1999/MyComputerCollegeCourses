package sample.generics;

import java.util.Hashtable;

/**
 * Java泛型机制示例
 * 
 * @author xiaodong
 *
 */
public class HashtableGenericsSample {

	public static void main(String[] args) {
		Hashtable<Integer, Employee> ht = new Hashtable<Integer, Employee>(); // k,v泛型化

		/*
		 * 程序直接将int型数据当作映射“键”使用，是由于Java的自动封装机制——系统自动将int型数据值转换为Integer类型对象
		 */
		ht.put(new Integer(101), new Employee(101, "张三", 5000));
		ht.put(102, new Employee(102, "李四", 4800));

		Employee e = ht.get(102);

		e.showInfo();
	}
}
