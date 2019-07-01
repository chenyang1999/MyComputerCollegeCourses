package sample.jvm.memory;

public class BirthDate {

	private int year, month, day;

	public BirthDate() {

	}

	public BirthDate(int day, int month, int year) {
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public void showInfo() {
		System.out.println("Birth date is " + year + "-" + "-" + month + "-" + day);
	}
}
