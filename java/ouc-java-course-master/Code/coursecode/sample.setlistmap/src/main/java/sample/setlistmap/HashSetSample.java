package sample.setlistmap;

import java.util.HashSet;
import java.util.Iterator;

/**
 * HashSet的赋值与遍历
 * 
 * @author xiaodong
 *
 */
public class HashSetSample {

	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(1);
		hs.add(5);
		hs.add(30);
		hs.add(21);
		hs.add(13);

		/*
		 * 通过迭代器
		 */
		Iterator<Integer> it = hs.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		
		
	}

}
