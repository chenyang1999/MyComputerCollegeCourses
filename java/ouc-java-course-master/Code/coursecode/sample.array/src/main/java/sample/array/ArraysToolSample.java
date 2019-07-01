package sample.array;

import java.util.Arrays;

/**
 * Arrays工具类示例
 * 
 * @author xiaodong
 *
 */
public class ArraysToolSample {
	public static void main(String[] args) {

		/*
		 * 数组比较 equals
		 */
		String[] str1 = { "1", "2", "3" };
		String[] str2 = { "1", "2", new String("3") };
		System.out.println(Arrays.equals(str1, str2)); // 结果是true

		/*
		 * 数组排序 sort
		 */
		int[] score = { 79, 65, 93, 64, 88 };

		// 将数组转换为字符串
		String str = Arrays.toString(score);
		System.out.println("原数组为：" + str);

		Arrays.sort(score); // 作用是把一个数组按照有小到大进行排序，会改变原score而不是创建新对象

		// 将数组转换为字符串
		System.out.println("排序后数组为：" + Arrays.toString(score));

		/*
		 * 把数组中的所有元素替换成一个值 fill
		 */
		int[] num = { 1, 2, 3 };
		Arrays.fill(num, 6); // 参数1：数组对象；参数2：替换的值
		System.out.println(Arrays.toString(num)); // 打印结果：[6, 6, 6]

		/*
		 * 通过二分法查询元素值在数组中的下标 binarySearch
		 */
		char[] a = { 'a', 'b', 'c', 'd', 'e' };
		int i = Arrays.binarySearch(a, 'd');
		System.out.println(i); // 结果是：3

		char[] b = { 'e', 'a', 'c', 'b', 'd' };
		Arrays.sort(b);
		int j = Arrays.binarySearch(b, 'e');
		System.out.println(j); // 结果是：4

		/*
		 * 把数组内容复制到一个新数组中 copyOf
		 */
		int[] c = { 1, 2, 3 };
		int[] d = Arrays.copyOf(c, c.length + 2); // 参数1：原数组 参数2：新数组的长度
		System.out.println("原数组为：" + Arrays.toString(c));
		System.out.println("复制后的新数组为：" + Arrays.toString(d));
	}

}
