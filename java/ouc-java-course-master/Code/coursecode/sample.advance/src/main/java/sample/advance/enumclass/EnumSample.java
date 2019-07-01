package sample.advance.enumclass;

/**
 * 枚举类型测试类
 * 
 * 与switch语句结合
 * 
 * @author xiaodong
 *
 */
public class EnumSample {
	public void work(Week day) {
		switch (day) {
		case MON:
		case TUE:
		case WED:
		case THU:
		case FRI:
			System.out.println("工作日，好好学习、天天向上！");
			break;
		case SAT:
			System.out.println("星期六，享受大自然去！");
			break;
		case SUN:
			System.out.println("星期日，做最喜欢做的事情，就是写代码！");
			break;
		default:
			System.out.println("你有没有搞错！");
		}
	}

	public static void main(String[] args) {
		/*
		 * 遍历枚举类型Week，以使用静态方法values()遍历枚举类型常量值
		 */
		Week[] days = Week.values();
		for (Week d : days) {
			System.out.print(d + " ");
		}
		System.out.println();

		EnumSample es = new EnumSample();
		es.work(Week.SUN);
	}

}