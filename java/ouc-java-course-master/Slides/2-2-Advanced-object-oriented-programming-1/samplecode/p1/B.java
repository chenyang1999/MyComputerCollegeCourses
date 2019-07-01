package p1;

public class B extends A {
	public B() {
		super("Hello");
		System.out.println("I am B");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		B b = new B();
		System.out.println(b.showInfo());
	}

}