package sample.setlistmap.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListSample {

	public static void main(String[] args) {
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		alist.add(1);
		alist.add(2);
		alist.add(3);
		alist.add(4);
		alist.add(5);
		alist.add(6);
		
		Collections.shuffle(alist);
		
		Iterator<Integer> it = alist.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
