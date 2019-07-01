import java.util.Scanner;
public class diagsum {
	public static void main(String[] args) {
		double[][] d = new double[3][3];
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个三阶方阵:");
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				d[i][j] = sc.nextDouble();
			}
		}
		double sum = 0;
		for (int i = 0; i < 3; i++) {
			sum = sum + d[i][i];
		}
		System.out.println(sum);
	}
}