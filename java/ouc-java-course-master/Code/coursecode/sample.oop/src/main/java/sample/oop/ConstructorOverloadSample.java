package sample.oop;

/**
 * 构造方法重载，没有无参构造方法示例
 * 
 * @author xiaodong
 *
 */
class Book {
	private String title;

	// Book类没有无参构造方法
	public Book(String title) {
		this.title = title;
	}
	
	public void showInfo() {
		System.out.println("书名为 " + title);
	}
}

class Novel extends Book {
	private String subject;
	
	public Novel(String title, String subject) {
		super(title); // 显式的调用父类有参数构造方法
		this.subject = subject;
	}
	
	@Override
	public void showInfo() {
		super.showInfo(); 
		System.out.println("该小说的主题为 " + subject);
	}
}

public class ConstructorOverloadSample {

	public static void main(String args[]) {
		
	}
}
