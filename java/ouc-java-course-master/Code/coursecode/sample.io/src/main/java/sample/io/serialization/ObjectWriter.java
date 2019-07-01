package sample.io.serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 将对象序列化输出至文件 （内存 -- 硬盘，临时 -- 持久化存储）
 * 
 * @author xiaodong
 *
 */
public class ObjectWriter {

	public static void main(String[] args) {

		try {
			// 打开文件输出流
			FileOutputStream fos = new FileOutputStream("dada.ser");
			// 将对象输出流和打开的文件输出流关联
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// 向对象输出流写入需要序列化的对象
			oos.writeObject(new Employee("Kevin", 35, "OUC CS"));
			oos.writeObject(new Employee("Wang Xiaodong", 35, "OUC CS"));

			oos.close();
		} catch (FileNotFoundException e) { // 处理相关IO异常
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}