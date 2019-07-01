package sample.generics;

public class Employee {
	private int id;
	private String name;
	private double salary;

	public Employee() {
	}

	public Employee(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public void showInfo() {
		System.out.println("The employee's info: \n\t" + id + "\t" + name + "\t" + salary);
	}
}
