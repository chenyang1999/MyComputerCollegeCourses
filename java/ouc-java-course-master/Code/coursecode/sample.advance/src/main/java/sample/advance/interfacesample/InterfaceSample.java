package sample.advance.interfacesample;

/**
 * 接口测试示例
 * 
 * @author xiaodong
 *
 */
public class InterfaceSample {

	public static void main(String args[]) {
		InterfaceSample t = new InterfaceSample();
		Person p = new Person();
		t.m1(p);
		t.m2(p);
		t.m3(p);
	}

	/* 多态性体现
	 * 以下三个方法要求传入的引用类型均不同，但一个Person类型的引用变量均可以作为以下方法的参数
	 * 说明Person类型即是一个Runner、也是一个Swimmer，同时还是个Animal
	 */
	public void m1(Runner f) {
		f.run();
	}

	public void m2(Swimmer s) {
		s.swim();
	}

	public void m3(Animal a) {
		a.eat();
	}
}