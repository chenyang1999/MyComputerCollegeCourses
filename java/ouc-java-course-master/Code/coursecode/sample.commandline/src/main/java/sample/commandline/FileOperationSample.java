package sample.commandline;

import java.io.File;

public class FileOperationSample {

	/**
	 * Java文件操作示例
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * 采用系统属性获取当前用户HOME目录
		 */
		File directory = new File(System.getProperty("user.home") + File.separator + "nobibi"); // Java将目录也当作文件处理

		directory.deleteOnExit(); // 注册该目录虚拟机退出时删除

		File fop = new File(directory, "just_show_me_the_code.txt");

		System.out.println("文件的绝对路径为：" + fop.getAbsolutePath());
		System.out.println("文件的父路径为：" + fop.getParent());

		/*
		 * 测试操作，判读目录是否存在，如果不存在则创建
		 */
		if (directory.exists()) {
			System.out.println("目录 " + directory.getName() + " 已存在");
		} else {
			System.out.println("目录 " + directory.getName() + " 不存在，即刻创建该目录...");
			if (directory.mkdirs()) { // 创建目录，包括其不存在的父目录
				System.out.println("目录 " + directory.getName() + " 创建成功");
			} else {
				System.out.println("目录 " + directory.getName() + " 无法创建");
			}
		}

		/*
		 * 测试操作，判读文件是否存在
		 */
		if (fop.exists()) {
			System.out.println("文件 " + fop.getName() + " 已存在");
		} else {
			System.out.println("文件 " + fop.getName() + " 不存在");
		}

		/*
		 * 
		 */
	}

}
