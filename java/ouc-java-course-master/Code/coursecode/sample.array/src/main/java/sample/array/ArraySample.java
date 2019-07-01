package sample.array;

/**
 * 一维数组示例
 * 
 * @author xiaodong
 *
 */
public class ArraySample {
	public static void main(String[] args) {
		int[] a = { 5, 7, 20 };
		int[] b = new int[4];
		
		System.out.println("a数组的长度为：" + a.length);
		System.out.println("b数组的长度为：" + b.length);
		
		System.out.println("a数组所包含的元素为：");
		// 循环输出a数组的元素
		for (int i = 0, len = a.length; i < len; i++) {
			System.out.println(a[i]);
		}

		System.out.println("b数组所包含的元素为：");
		// 循环输出b数组的元素
		for (int i = 0, len = b.length; i < len; i++) {
			System.out.println(b[i]);
		}

		// b引用指向a引用的数组
		b = a;
		System.out.println("b=a后，b数组的长度为：" + b.length);
		// 循环输出b数组的元素
		for (int i = 0, len = b.length; i < len; i++) {
			System.out.println(b[i]);
		}

		// 将a数组元素重新赋值
		a[1] = 11;
		
		System.out.println("b[1]元素值为：");
		System.out.println(b[1]); // 由于引用b数组对应值变化
	}
}
