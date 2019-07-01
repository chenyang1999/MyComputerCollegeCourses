package sample.array;

/**
 * 二维数组示例
 * 
 * @author xiaodong
 *
 */
public class Array2DimSample {
	public static void main(String[] args) {
		int[][] a = { { 5, 7, 20 }, { 8, 11, 23 } };
		int[][] b = new int[4][];

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

		int[] c = { 1, 2, 3, 4 };
		a[1] = c;

		System.out.println("a数组所包含的元素为：");
		// 循环输出a数组的元素
		for (int i = 0, len = a.length; i < len; i++) {
			System.out.println(a[i]);
		}
		
		
		// b引用指向a引用的数组
		b = a;
		System.out.println(b[1]); // 值变了

	}
}
