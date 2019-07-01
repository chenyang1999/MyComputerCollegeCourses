//package ouc.cs.course.java.test.poke;

import java.util.Random;import java.util.*;


public class Poke {
	private static String[] suit = {"Heart", "Spade", "Diamond", "Club"};
	private static String[] face = {"A", "2","3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	public static String[] createCard(int number){
		String[] cards = new String[number*52];
		for(int i=0; i<number; i++){
			for(int j=0; j<52; j++){
				cards[i*52+j] = suit[((i*52+j)/13)%4] +face[(i*52+j)%13];
			}
		}
		return cards;
	}
	
	public static void display(String[] cards){
		for(int i=0; i<cards.length; i++){
			System.out.print(cards[i]+"   ");
			if((i+1) % 13 == 0)
				System.out.println();
		}
	}
	public static void shuffle(String[] cards){
		Random rand = new Random();
		int num = 0;
		for(int i=0; i<cards.length; i++){
			num =rand.nextInt(cards.length);
			String temp = cards[i];
			cards[i] = cards[num];
			cards[num] = temp;
		}
	}
	
	public static void distribute(String[] cards, int player){
		String[][] cardsOfPlayer = new String[player][cards.length/player+1];
		int index = 0;
		for(int i=0; i<cards.length; i++){
			cardsOfPlayer[i%player][index] = cards[i];
			if((i+1)%player==0)
				index++;
		}
		for(int i=0; i<player; i++){
			System.out.println("玩家"+(i+1)+":");
			int sum=0;
			for(int j=0; j<cardsOfPlayer[i].length; j++){
				if(cardsOfPlayer[i][j] != null){
					System.out.print(cardsOfPlayer[i][j]+" ");
					sum++;
				}
			}
			System.out.println("共"+sum+"张牌");
		}
	}
	public static void main(String[] args) {
	System.out.println("该扑克游戏需要几付扑克牌?");
	Scanner sc = new Scanner(System.in);
	int num = sc.nextInt();
	String[] cards = Poke.createCard(num);
	System.out.println("该扑克游戏有几个玩家?");
	Scanner sp = new Scanner(System.in);
	int per = sp.nextInt();
	System.out.println("\n显示所有的牌:");
	Poke.display(cards);
	Poke.shuffle(cards);
	System.out.println("\n显示分配给每个人的牌:");
	Poke.distribute(cards, per);
	 }
}
