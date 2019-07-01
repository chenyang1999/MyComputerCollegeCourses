import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class TestRandom {

	/**
	 * 产生不大于n的n个随机数并存入集
	 * 
	 * @param n
	 *            随机数最大值与集大小
	 * @param set
	 *            随机数存储容器
	 */
	public static void randomSet(int n, HashSet<Integer> set) {
		Random rand = new Random();

		while (set.size() < n) {
			int num = rand.nextInt(n);
			set.add(num + 1);
		}
	}

	/**
	 * 产生不大于n的n个随机数并存入集
	 * 
	 * @param n
	 *            随机数最大值与集大小
	 * @param set
	 *            随机数存储容器
	 */
	public static void randomSet(int n, ArrayList<Integer> li) {
		Random rand = new Random();

		while (li.size() < n) {
			int num = rand.nextInt(n);
			if (!li.contains(num + 1)) {
				li.add(num + 1);
			}
		}
	}

	public static void main(String[] args) {

		HashSet<Integer> set = new HashSet<Integer>();
		ArrayList<Integer> li = new ArrayList<Integer>();
		randomSet(10, li);

		Iterator<Integer> it = li.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
