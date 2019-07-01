import java.util.Scanner; 
import java.lang.String;
public class  jiequ{
	public static void main(String[] args) {
		System.out.println("input string:");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		System.out.println("begin and length");
		int start = sc.nextInt();
		int len = sc.nextInt();
while(start<=0 || len<=0 || start>str.length()||start+len>str.length()+1){
			System.out.println("输入非法，请重新输入:");
			start = sc.nextInt();
			len = sc.nextInt();
		}
		String str_cut = str.substring(start-1, start+len-1);
		System.out.println("截取后的新字符串为:"+str_cut);
	}
}
