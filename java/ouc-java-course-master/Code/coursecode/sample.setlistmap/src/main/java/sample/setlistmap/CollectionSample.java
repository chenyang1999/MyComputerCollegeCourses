package sample.setlistmap;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionSample {

	public static void main(String[] args) {

		/*
		 * 数组的声明和创建
		 */
		String[] myArray1 = { "Java", "is", "my", "favourite", "programming", "language" };
		String[] myArray2 = new String[6];

		for (int i = 0; i < 6; i++) {
			myArray2[i] = myArray1[i];
		}

		// System.out.println(myArray2[5]);

		/*
		 * ArrayList的声明和创建
		 */
		ArrayList<String> myArrayList = new ArrayList<String>();

		myArrayList.add("China");
		myArrayList.add("USA");
		myArrayList.add("Japan");
		myArrayList.add("Korea");

		/*
		 * 迭代器
		 */
		Iterator<String> it = myArrayList.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}