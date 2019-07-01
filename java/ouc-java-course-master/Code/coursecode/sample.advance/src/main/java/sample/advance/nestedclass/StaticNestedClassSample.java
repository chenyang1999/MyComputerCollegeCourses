package sample.advance.nestedclass;

class A {
	public static int total = 0;

	public static class B {
		public void mb() {
			total = 100;
			System.out.println(total);
		}
	}
}

public class StaticNestedClassSample {
	public static void main(String[] args) {
		A.B b = new A.B();
		b.mb();
		//A.B.mb();
	}
}