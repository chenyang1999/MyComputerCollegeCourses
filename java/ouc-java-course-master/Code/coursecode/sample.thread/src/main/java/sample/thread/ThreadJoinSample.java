package sample.thread;

public class ThreadJoinSample {
	public static void main(String[] args) {
		JoinRunner r = new JoinRunner();
		Thread t = new Thread(r);
		t.start();

		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 50; i++) {
			System.out.println("主线程：" + i);
		}
	}
}

class JoinRunner implements Runnable {
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println("子线程：" + i);
		}
	}
}