package sample.setlistmap;

public class AutoBoxAndUnboxSample {

	public static void main(String[] args) {

		/*
		 * 在Java SE5之前，如果要生成一个数值为10的Integer对象，必须使用以下方式
		 */
		Integer j = new Integer(10);
		
		/*
		 * Java SE5开始就提供了自动装箱的特性
		 */
		Integer i = 100;
		
		int n = i;

	}

}
