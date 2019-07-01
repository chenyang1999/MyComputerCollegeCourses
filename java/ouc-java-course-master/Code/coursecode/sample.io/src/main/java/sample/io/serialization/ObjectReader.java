package sample.io.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 将对象从文件反序列化为对象 （硬盘 -- 内存，持久化存储 -- 活的内存对象）
 * 
 * @author xiaodong
 *
 */
public class ObjectReader {

	public static void main(String[] args) {

		try {
			// 打开文件输入流
			FileInputStream fis = new FileInputStream("dada.ser");
			// 将文件输入流关联到对象输入流（实现反序列化）
			ObjectInputStream ois = new ObjectInputStream(fis);

			// 将反序列化后的对象造型为 Employee
			Employee e1 = (Employee) ois.readObject();
			Employee e2 = (Employee) ois.readObject();

			// 通过调用两个对象的showInfo()方法，检验是否原来被序列化后的对象已经恢复本来面目
			e1.showInfo();
			e2.showInfo();

			ois.close();
			
		} catch (ClassNotFoundException e) { // 处理相关异常
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
