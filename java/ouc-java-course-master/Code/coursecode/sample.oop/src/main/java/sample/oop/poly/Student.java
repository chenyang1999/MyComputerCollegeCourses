package sample.oop.poly;

public class Student extends Person {
	private String school;

	public Student() {
		super();
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + " School: " + getSchool();
	}
}
