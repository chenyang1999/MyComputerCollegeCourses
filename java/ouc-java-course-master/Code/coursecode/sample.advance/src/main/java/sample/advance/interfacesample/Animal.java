package sample.advance.interfacesample;

/**
 * 抽象类Animal
 * 
 * 在Animal的抽象概念中，吃应该是Animal一定会有的特征概念，但是不同的Animal在定义吃上有非常大的差异
 * 所以规划了抽象的一个eat()方法，留待子类中去分化实现
 * 
 * @author xiaodong
 *
 */
abstract class Animal {
	public abstract void eat();
}