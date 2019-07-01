package sample.commandline.filefilter;

import java.io.File;

/**
 * 文件过滤器示例
 * 
 * @author xiaodong
 *
 */
public class FileFilterSample {

	public static void main(String[] args) {
		FileFilterSample tff = new FileFilterSample();
		tff.dir("/Users/xiaodong/Temp", ".py");
	}

	public void dir(String path, String extension) {
		File directory = new File(path);
		MyFilter mf = new MyFilter(extension);
		File[] files = directory.listFiles();
		System.out.println("Path: \t" + path);
		System.out.print("File: ");
		
		for (File file : files) {
			if (mf.accept(file))
				System.out.println("\t" + file.getName());
		}
	}
}