package sample.setlistmap;

import java.util.ArrayList;

/**
 * ArrayList使用ensureCapacity优化性能 - 20180415
 * 
 */
public class ArrayListEnSureCapacitySample {

	public static void main(String[] args) {
		final int N = 100000;
		Object obj = new Object();

		ArrayList<Object> list1 = new ArrayList<Object>();

		/*
		 * 无ensureCapacity
		 */
		long start1 = System.currentTimeMillis();

		for (int j = 0; j < N; j++) {
			list1.add(obj);
		}

		System.out.println("Without ensureCapacity, consume " + (System.currentTimeMillis() - start1) + "ms");

		/*
		 * 有ensureCapacity
		 */
		ArrayList<Object> list2 = new ArrayList<Object>();
		long start2 = System.currentTimeMillis();

		// 显式的对底层数组进行扩容
		list2.ensureCapacity(N);

		for (int k = 0; k < N; k++) {
			list2.add(obj);
		}

		System.out.println("With ensureCapacity, consume " + (System.currentTimeMillis() - start2) + "ms");

	}
}