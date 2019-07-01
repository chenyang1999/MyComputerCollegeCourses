package sample.advance.interfacesample;

/**
 * Person类
 * 
 * 该类是Animal的子类，会吃这个能力是与生俱来的
 * 同时该类通过接口又把自己的所学暴露了出来，可以跑、可以游泳
 * 
 * @author xiaodong
 *
 */
public class Person extends Animal implements Runner, Swimmer {
	public void run() {
		System.out.println("I am running, to the sea!");
	}

	public void swim() {
		System.out.println("I am swimming, to the island!");
	}

	public void eat() {
		System.out.println("I am eating!");
	}
}
