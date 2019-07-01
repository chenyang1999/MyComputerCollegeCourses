package shiyan5;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fsops {
	public static void main(String[] args) {
		String cmd = args[0];
		for(int i=1; i<args.length; i++){
			String[] s = args[i].split("\\\\");
			String path = "";
			for(int j=0; j<s.length; j++){
				if(j==s.length-1){
					path += s[j];
				}
				else{
					path = path + s[j]+"\\\\";
				}
			}
			File file = new File(path);
			if(cmd.equals("mkdir")){
				file.mkdirs();
			}else if(cmd.equals("nfile")){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(cmd.equals("rm")){
				file.delete();
			}else if(cmd.equals("ls")){
				String []str = file.list();
				System.out.println("Directory  " + path);
				System.out.println("------------------------");
				System.out.println("Name              Time  created             Time modified          Type");
				for (String string : str) {
					File f = new File(path + "\\\\" +string);
					String flag = "";
					if(f.isFile()){
						flag = "f";
					}else if(f.isDirectory()){
						flag = "d";
					}
					String Cretime = getCreateTime(f);
					String Modtime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(f.lastModified()));
					System.out.println(string + "\t" +Cretime  + "\t" + Modtime + "\t" + flag);
				}
			}
		}
		if(cmd.equals("help")){
			System.out.println("mkdir  创建目录");
			System.out.println("nfile  新建空文�");
			System.out.println("rm     删除文件或目�");
			System.out.println("mkdir  列表列出目录下所有文件和子目�");
			System.out.println("help   工具帮助");
			System.out.println("self   软件基本信息");
		}else if(cmd.equals("self")){
			System.out.println("name    \"Java命令行文件操作程序\"" );
			System.out.println("verson  \"fsops 1.0\" ");
			System.out.println("author   陈扬");
			System.out.println("date    \"2018-10-28\"");
		}
	}
	public static String getCreateTime(File file){ 
		 String filePath = file.getPath();  
	     String strTime = null;  
	     try {  
	         Process p = Runtime.getRuntime().exec("cmd /C dir "           
	                 + filePath  
	                 + "/tc" );  
	         InputStream is = p.getInputStream();   
	         BufferedReader br = new BufferedReader(new InputStreamReader(is));             
	         String line;
	         int i = 0;
	         while((line = br.readLine()) != null){  
	        	 i++;
	             if(i == 6){  
	                 strTime = line.substring(0,17);  
	                 break;  
	             }                             
	          }   
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     }         
	     return strTime;
	}
}
