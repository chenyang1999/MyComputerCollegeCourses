package sample.advance.door;

abstract class Door {

	static {
		System.out.println("我是一个抽象的门");
	}

	abstract void open();

	abstract void close();
}
