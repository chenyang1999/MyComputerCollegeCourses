package sample.commandline;

/**
 * 命令行参数示例
 * 
 * @author xiaodong
 *
 */
public class CommandlineArgsSample {
	
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
}