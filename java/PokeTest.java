package ouc.cs.course.java.test.poke;

import java.util.Scanner;

public class PokeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("该游戏需要几副扑克牌？");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String[] cards = Poke.createCard(num);
		System.out.println("该扑克游戏有几个玩家？");
		int per = sc.nextInt();
		System.out.println("\n显示所有的牌:");
		Poke.display(cards);
		Poke.shuffle(cards);
		System.out.println("\n显示分配给每个人的牌:");
		Poke.distribute(cards, per);
		sc.close();
	}

}
