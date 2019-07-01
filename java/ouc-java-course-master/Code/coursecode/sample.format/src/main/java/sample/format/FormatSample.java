package sample.format;

public class FormatSample {

	/**
	 * 格式化字符串，左对齐
	 * 
	 * c 要填充的字符 length 填充后字符串的总长度 content 要格式化的字符串
	 * 
	 */
	public String flushLeft(char c, long length, String content) {
		String str = "";
		String cs = "";
		if (content.length() > length) {
			str = content;
		} else {
			for (int i = 0; i < length - content.length(); i++) {
				cs = cs + c;
			}
		}
		str = content + cs;
		return str;
	}

	public static void main(String[] args) throws Exception {
		FormatSample test1 = new FormatSample();
		System.out.printf("%10s%20s%2s%s\n", "Number", "Type", " ", test1.flushLeft(' ', 10, "File Name"));
		System.out.println("------------------------------------------------");
		for (int i = 0; i <= 10; i++) {
			if (i % 2 == 0) {
				System.out.printf("%10s%20s%2s%s\n", i, "String", " ", test1.flushLeft(' ', 10, "FormatFormat格式化"));
			} else {
				System.out.printf("%10s%20s%2s%s\n", i, "StringString", " ", test1.flushLeft(' ', 10, "格式化"));
			}
		}
	}
}