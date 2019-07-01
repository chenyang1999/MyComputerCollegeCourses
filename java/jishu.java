import java.util.Scanner;
import java.rmi.dgc.*;

	public class jishu {
		public static void main(String[] args) {
			System.out.println("input string:");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			int l = 0, d = 0, o = 0;
			for(int i=0; i<str.length(); i++){
				char c = str.charAt(i);
				if((c>='A' && c<='Z')||(c>='a' && c<='z')){
					l++;
				} 
else if(c>='0' && c<='9'){
					d++;
				}else o++;
			}
			System.out.println(str+"中:");
			System.out.println("字母有"+l+"个");
			System.out.println("数字有"+d+"个");
			System.out.println("其他字符有"+o+"个");
		}
	}

