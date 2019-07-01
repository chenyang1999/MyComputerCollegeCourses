package sample.exception;

/**
 * 虚拟机错误示例 设置过大的递归调用次数会导致栈内存溢出错误
 * 
 * @author xiaodong
 *
 */
public class VMErrorSample {

	/*
	 * 递归函数
	 */
	public int f(int n) {
		if (n <= 0) {
			return 0;
		}
		int k = n * this.f(n - 1);
		return k;
	}

	public static void main(String[] args) {
		VMErrorSample t = new VMErrorSample();
		t.f(100000); // 分别测试调用f方法1000次、100000次
	}

}
