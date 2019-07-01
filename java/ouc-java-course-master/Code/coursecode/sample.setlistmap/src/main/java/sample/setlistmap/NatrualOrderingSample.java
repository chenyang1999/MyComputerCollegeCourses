package sample.setlistmap;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * 在TreeSet上自然排序
 * 
 */
@SuppressWarnings("rawtypes")
class Person implements java.lang.Comparable {

	private int id;
	private String name;
	private int age;

	public Person() {
	}

	public Person(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(Object o) {
		Person p = (Person) o;
		return this.id - p.getId();
	}

	@Override
	public boolean equals(Object o) {
		boolean flag = false;
		if (o instanceof Person) {
			if (this.id == ((Person) o).id) {
				flag = true;
			}
		}

		return flag;
	}

	@Override
	public String toString() {
		return "Id: " + this.getId() + "\tName: " + this.getName() + "\tAge: " + this.getAge();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

public class NatrualOrderingSample {
	public static void main(String[] args) {
		TreeSet<Person> ts = new TreeSet<Person>();

		ts.add(new Person(1003, "Bob", 15));
		ts.add(new Person(1008, "Alice", 25));
		ts.add(new Person(1001, "Kevin", 30));
		ts.add(new Person(1010, "Mark", 20));
		Iterator<Person> it = ts.iterator();

		while (it.hasNext()) {
			Person emplyee = it.next();
			System.out.println(emplyee);
		}
	}
}
