package sample.setlistmap;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * 代码示例：HashMap和Hashtable比较
 * 
 * @author xiaodong
 *
 */
public class MapSample {
	public static void main(String[] args) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put(null, null); // HashMap允许空键空值
		System.out.println(hm.get("abc")); // 当集合中不存在当前检索的“键”所对应的映射值时会返回null

		
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(null, null); // Hashtable元素的“键”和“值”均不允许为null
		System.out.println(ht.get("abc")); // 当集合中不存在当前检索的“键”所对应的映射值时会返回null
	}
}
