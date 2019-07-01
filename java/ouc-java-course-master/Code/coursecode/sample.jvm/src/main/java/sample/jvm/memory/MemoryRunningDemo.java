package sample.jvm.memory;

public class MemoryRunningDemo {
	
	public MemoryRunningDemo() {
	}

	public void m1(int i) {
		i = 1234;
	}

	public void m2(BirthDate b) {
		b = new BirthDate(15, 6, 2010);
	}

	public void m3(BirthDate b) {
		b.setDay(18);
	}
	
	public static void main(String[] args) {
		int data = 9;
		System.out.println(data);
	
		BirthDate d1 = new BirthDate(22, 12, 1982);
		BirthDate d2 = new BirthDate(10, 10, 1958);
		
		MemoryRunningDemo test = new MemoryRunningDemo();
		test.m1(data);
		test.m2(d1);
		test.m3(d2);
	}
}