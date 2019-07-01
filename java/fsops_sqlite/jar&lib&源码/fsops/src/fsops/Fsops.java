package fsops;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.sql.*;

public class Fsops {
	public static void main(String[] args) {
		//创建用户表
		Connection c = null;
		Statement stmt = null;
	    try {
	    	MyTools myTools = new MyTools(); 
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:d:/user.db");
	    	c.setAutoCommit(false);
	    	stmt = c.createStatement();
	    	String sql;
	    	String sta = "jdbc:sqlite:d:/user.db";
	    	String tableName = "USER";
	    	if(!myTools.tableIsExist(sta, tableName)){
			    sql = "CREATE TABLE USER"                        +
			                 "(ID INT PRIMARY   KEY         NOT NULL, "  +
			                 " USER             CHAR(50)    NOT NULL, " + 
			                 " PASSWORD         CHAR(50)    NOT NULL  )";
			    stmt.executeUpdate(sql);
			    sql = "INSERT INTO USER (ID, USER, PASSWORD) " +
		   			  "VALUES (1, 'testuser', 'testpassword');"; 
				stmt.executeUpdate(sql);
	    	}else{
		    	if(!myTools.dateIsExit(sta, tableName, "USER", "testuser")){
				    sql = "INSERT INTO USER (ID, USER, PASSWORD) " +
		   				  "VALUES (1, 'testuser', 'testpassword');"; 
				    stmt.executeUpdate(sql);
			    }
	    	}
	    	stmt.close();
		    c.commit();
		    c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		String cmd = args[0];
		if(cmd.equals("-logdb")){
			String url = args[1];
			String user = args[2];
			String password = args[3];
			String commend = args[4];
			SQLiteJDBC sJdbc = new SQLiteJDBC();
			sJdbc.link(url);
			sJdbc.creatTable(url, "FILESYSLOG");
			if(sJdbc.select("jdbc:sqlite:d:/user.db", "USER", user, password)){
				Operate operate = new Operate();
				File file;
				if(commend.equals("mkdir")){
					for(int i=5; i<args.length; i++){
						String path = operate.getPath(args[i]);
						file = new File(path);
						operate.mkdir(url, "FILESYSLOG", file);
					}
				}else if(commend.equals("nfile")){
					for(int i=5; i<args.length; i++){
						String path = operate.getPath(args[i]);
						file = new File(path);
						operate.nfile(url, "FILESYSLOG", file);
					}
				}else if(commend.equals("rm")){
					for(int i=5; i<args.length; i++){
						String path = operate.getPath(args[i]);
						file = new File(path);
						operate.rm(url, "FILESYSLOG", file);
					}
				}else if(commend.equals("ls")){
					for(int i=5; i<args.length; i++){
						String path = operate.getPath(args[i]);
						file = new File(path);
						operate.ls(url, "FILESYSLOG", file);
					}	
				}else if(commend.equals("help")){
					operate.help(url, "FILESYSLOG");
				}else if(commend.equals("self")){
					operate.self(url, "FILESYSLOG");
				}
				
			}else {
				System.out.println("用户名或密码错误！");
			}
		}else{
			System.out.println("操作不存在！！");
		}
	}
}
class Operate
{
	SQLiteJDBC sql = new SQLiteJDBC();
	public String getPath(String str){  //获取路径
		String path = "";
		String[] s = str.split("\\\\");
		for(int j=0; j<s.length; j++){
			if(j==s.length-1){
				path += s[j];
			}
			else{
				path = path + s[j]+"\\\\";
			}
		}
		return path;
	}
	public String getName(){          //获取主机名
		Properties properties = System.getProperties();
		return properties.getProperty("user.name");
	}
	public String getTime(){		//获取时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
	}
	public void mkdir(String sta, String tab, File file){     //创建目录
		String hostname = getName();
		String date = getTime();
		String [] s = {hostname, "mkdir", date};
		file.mkdir();
		sql.insert(sta, tab, s);
	}
	public void nfile(String sta, String tab, File file){    //创建文件
		String hostname = getName();
		String date = getTime();
		String [] s = {hostname, "nfile", date};
		try {
			String path = file.getPath();
			String[] s1 = path.split("\\\\");
			String newPath = "";
			for(int j=0; j<s1.length - 1; j++){
				if(j == s1.length - 2){
					newPath = newPath + s1[j];
				} else {
					newPath = newPath + s1[j]+"\\\\";
				}
			}
			File newFile = new File(newPath);
			if(newFile.exists()){
				file.createNewFile();
			} else {
				newFile.mkdir();
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		sql.insert(sta, tab, s);
	}
	public void rm(String sta, String tab, File file){       //删除目录或文件 调用deleteFile()函数
		if(file.exists()){
			String hostname = getName();
			String date = getTime();
			String [] s = {hostname, "rm", date};
			deleteFile(file);
			sql.insert(sta, tab, s);
		} else {
			System.out.println("文件或目录不存在！！");
		}
	}
	private void deleteFile(File file) {                 //循环删除非空路径
			if (file.exists()) {
			    if (file.isFile()) {
			    	file.delete();
			    } else if (file.isDirectory()) {
			    	File[] files = file.listFiles(); 
			    	for (int i = 0;i < files.length;i ++) {
			    		this.deleteFile(files[i]); 
			    	}  
			    	file.delete();
			    }  
		    } else {  
		    	System.out.println("所删除的文件不存在");  
		    }  
	}  
	public static String getCreateTime(File file){          //获取文件夹或文件创建时间，调用Windows的命令，固仅可在Windows上运行
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
	public void ls(String sta, String tab, File file){                //列出文件信息
		if(file.exists()){
			String hostname = getName();
			String date = getTime();
			String [] s = {hostname, "ls", date};
			String []str = file.list();
			System.out.println("Directory  " + file.getPath());
			System.out.println("------------------------");
			System.out.println("Name              Time  created             Time modified          Type");
			for (String string : str) {
				File f = new File(file.getPath() + "\\\\" +string);
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
			sql.insert(sta, tab, s);
		}else {
			System.out.println("文件或目录不存在！");
		}
	}
	public void help(String sta, String tab){             //帮助信息
		System.out.println("mkdir  创建目录");
		System.out.println("nfile  新建空文件");
		System.out.println("rm     删除文件或目录");
		System.out.println("mkdir  列表列出目录下所有文件和子目录");
		System.out.println("help   工具帮助");
		System.out.println("self   软件基本信息");
		String hostname = getName();
		String date = getTime();
		String [] s = {hostname, "help", date};
		sql.insert(sta, tab, s);
	}
	public void self(String sta, String tab){           //版本信息
		System.out.println("name    \"Java命令行文件操作程序\"");
		System.out.println("verson  \"fsops 2.0\" ");
		System.out.println("author   石晓晨");
		System.out.println("date    \"2018-11-11\"");
		String hostname = getName();
		String date = getTime();
		String [] s = {hostname, "self", date};
		sql.insert(sta, tab, s);
	}
}

class SQLiteJDBC{
	public void link(String s){             //创建连接
    	Connection c = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(s);
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	}
	public void creatTable(String sta, String tab){       //创建表
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection(sta);
		    stmt = c.createStatement();
		    MyTools myTools = new MyTools();
		    if(!myTools.tableIsExist(sta, tab)){
			    String sql = "CREATE TABLE "+   tab                     +
			                 " (ID INTEGER PRIMARY   KEY  AUTOINCREMENT       NOT NULL, "  +
			                 " HOSTNAME         TEXT        NOT NULL, " + 
			                 " OPERATION        CHAR(50)    NOT NULL, " + 
			                 " DATE             CHAR(50)    NOT NULL    );";
			    stmt.executeUpdate(sql);
		    }
		    stmt.close();
		    c.close();
		} catch ( Exception e ) {
		    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
	}
	public void insert(String sta, String tab, String s[]){     //插入操作
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(sta);
	    	c.setAutoCommit(false);

	    	stmt = c.createStatement();
	    	String sql = "INSERT INTO " + tab + " (HOSTNAME, OPERATION, DATE) " +
	    				 " VALUES ( '" + s[0] + "' , '" + s[1] + "' , '" + s[2] + "' );"; 
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	c.commit();
	    	c.close();
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	}
	public boolean select(String sta, String tab, String user, String password){    //查询操作
		Connection c = null;
	    Statement stmt = null;
	    boolean flag = false;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(sta);
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT USER, PASSWORD FROM "+ tab );
	      while (rs.next()) {
	         if(rs.getString(1).equals(user) && rs.getString(2).equals(password)){
	        	 flag = true;
	         }
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return flag;
	}
} 
class MyTools{
	public boolean tableIsExist(String sta, String tableName){    //判断当前表是否存在
		boolean flag = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			c = DriverManager.getConnection(sta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String find = "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='"+ tableName+ "';";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(find);
			while(rs.next())
			{
				if(rs.getInt(1)>0)
					flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public boolean dateIsExit(String sta, String tableName, String column, String data){   //判断当前数据是否存在
		boolean flag = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			c = DriverManager.getConnection(sta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String find = "SELECT count(*) FROM "+ tableName+ " WHERE " + column +" = '"+ data +"';";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(find);
			while(rs.next())
			{
				if(rs.getInt(1)>0)
					flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
