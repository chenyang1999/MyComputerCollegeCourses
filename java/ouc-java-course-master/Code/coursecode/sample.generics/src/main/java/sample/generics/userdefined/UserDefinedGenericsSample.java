package sample.generics.userdefined;

/**
 * 自定义泛型类示例
 * 
 * 每个具体的PersonG眼中的秘密不同，有的人的秘密是一串秘钥，有的人则把自己的存款看做秘密，所以由T来决定到底是什么类型吧
 * 
 * @author xiaodong
 *
 */
public class UserDefinedGenericsSample {

	public static void main(String[] args) {

		PersonG<String> p1 = new PersonG<String>(101); // 类型参数为String类型
		p1.setSecrecy("芝麻开门");
		String s = p1.getSecrecy();
		System.out.println(p1.getId() + "\t 密码是：" + s);

		PersonG<Double> p2 = new PersonG<Double>(102); // 类型参数为Double类型
		p2.setSecrecy(8700.85);
		double money = p2.getSecrecy();
		System.out.println(p2.getId() + "\t 秘密资金数额是：" + money);
	}
}
