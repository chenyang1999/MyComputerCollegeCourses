package bean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.Comparator;

public class MethodBean {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet res = null;
	private PreparedStatement ps = null;
	private String dbname = "webdb";
	private String username = "root";
	private String pass = "root";

	private int pageSize = 6;
	private int rowCount = 0;// 该值从数据库中查询
	private int pageCount = 0;// 该值是通过pageSize和pageCount

	// 关闭资源
	public void closeSource() {
		// 关闭打开的各种资源，这个很重要！！！
		try {
			if (res != null) {
				res.close();
				res = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if(ps != null){
				ps.close();
				ps = null;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	// 验证用户是否合法
	public int checkUser(String u, String p) {

		int reint = 0;
		try {
			conn = new ConnDB().getConnection(dbname, username, pass); 
			stmt = conn.createStatement();
			res = stmt.executeQuery("select user_pass from user_db "
					+ "where user_account = '"+ u + "'");

			// 根据结果判断
			if (res.next()) {
				// 用户名存在，判断密码是否正确
				reint = 1;
				if (res.getString(1).equals(p)) {
					// 用户名和密码合法
					reint = 2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSource();
		}
		return reint;
	}
	
	public UserBean icUserbean(String account) {
		UserBean ub = new UserBean();
		ub.setEmail(account);
		//连接数据库，补全userbean信息
		try {
			conn = new ConnDB().getConnection(dbname, username, pass); 
			stmt = conn.createStatement();
			res = stmt.executeQuery("select user_pass,nickname,"
					+ "user_picture,sign from user_db "
					+ "where user_account = '"+ account + "'");
			
			if(res.next()) {
				ub.setPassword(res.getString(1));
				ub.setNickname(res.getString(2));
				ub.setHead_path(res.getString(3));
				System.out.println("head_path: " + res.getString(3));
				ub.setSign(res.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSource();
		}
		
		return ub;
	}
	
	//收藏与取消
	public int click_like(int ope, String account, String path) {
		path = path.substring(8, path.length());
		
		int reint = 0;
		int uid = 0;
		int pid = 0;
		
		try {
			conn = new ConnDB().getConnection(dbname, username, pass); 
			stmt = conn.createStatement();
			res = stmt.executeQuery("select user_id from user_db "
					+ "where user_account = '"+ account + "'");
			if(res.next()) 
				uid = Integer.parseInt(res.getString(1));
			res = stmt.executeQuery("select pic_id from pic_db "
					+ "where pic_path = '"+ path + "'");
			if(res.next())
				pid = Integer.parseInt(res.getString(1));
			if(uid != 0 && pid != 0) {
				if(ope == 0) { //收藏
					stmt = conn.createStatement();
					res = stmt.executeQuery("select user_id from pic_user_relation "
							+ "where user_id = '"+ uid + "' "
							+ "and pic_id = '"+ pid + "'");
					if(res.next()) {
						reint = 2;
					} else {
						PreparedStatement pstmt;
						pstmt = conn.prepareStatement("insert into pic_user_relation "
								+ "values ('" + uid + "', '" + pid + "')");
						pstmt.execute();
						reint = 1;
					}
				} else if(ope == 1) { //取消
					PreparedStatement pstmt;
					pstmt = conn.prepareStatement("delete from pic_user_relation "
							+ "where user_id='" + uid + "' and pic_id='" 
							+ pid + "'");
					pstmt.execute();
				}
			}	
		} catch (Exception e) {
			//修改失败
			e.printStackTrace();
		} finally {
			closeSource();
		}
		
		return reint;
	}

	// 返回pageCount的值
	public int getPageCount() {
		try {
			conn = new ConnDB().getConnection(dbname, username, pass);
			// 创建Statement
			stmt = conn.createStatement();
			// 查询数据库
			res = stmt.executeQuery("select count(*) from login");

			if (res.next()) {
				rowCount = res.getInt(1);
			}

			// 计算pageCount,这里的算法很多，可以自己设计
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}
			// 计算pageCount

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSource();
		}
		return pageCount;
	}

	// 得到用户需要显示的用户信息（分页）
	public ArrayList<UserBean> getUserByPage(int pageNow) {
		ArrayList<UserBean> al = new ArrayList<UserBean>();

		try {
			conn = new ConnDB().getConnection(dbname, username, pass);
			// 创建Statement
			stmt = conn.createStatement();

			// 显示要查询的记录
			// where not in(select * from login limit "+pageSize*(pageNow-1)+")
			System.out.println(pageNow);
			res = stmt.executeQuery("select * from login limit "
					+ ((pageNow - 1) * pageSize) + "," + pageSize + "; ");

			// 开始将res封装到ArrayList
			while (res.next()) {
				UserBean userbean = new UserBean();

				userbean.setId(res.getInt(3));
				userbean.setNickname(res.getString(1));
				userbean.setPassword(res.getString(2));

				// 将userbean放到al
				al.add(userbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSource();
		}
		return al;
	}

	// 删除用户
	public boolean deleteUser(String user_id) {
		boolean bool = false;
		try {
			conn = new ConnDB().getConnection(dbname, username, pass);
			ps = conn.prepareStatement("delete from login where id = "
					+ user_id + "");
			int a = ps.executeUpdate();
			if (a == 1) {
				// 删除成功
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeSource();
		}
		return bool;
	}

	// 添加用户
	public String  addUser(String userName, String passWord) {
		String flag= "fail";
		System.out.println(userName+passWord);
		try {
			conn = new ConnDB().getConnection(dbname, username, pass);
		
			ps = conn
					.prepareStatement("insert into user_db(user_account,user_pass,nickname,user_picture,sign) values('"
							+ userName + "','" + passWord +"','"+" "+"','"+" "+"','"+" "+ "')");
			int i = ps.executeUpdate();
			if (i == 1) {
				flag = "suc";
							}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSource();
		}
		return flag;
	}

	// 修改用户
	public boolean updateUser(String username, String password, String id) {
		boolean bool = false;
		try {
			conn = new ConnDB().getConnection(dbname, username, pass);
			ps = conn.prepareStatement("update login set username='" + username
					+ "',password='" + password + "' where id = '" + id + "'");
			int i = ps.executeUpdate();
			if (i == 1) {
				bool = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSource();
		}
		return bool;
	}

	// 查询用户
	public ArrayList searchUser(String id, String username, String password) {
		ArrayList al = new ArrayList();
		UserBean userbean = new UserBean();
		try {
			conn = new ConnDB().getConnection(dbname, username, pass);
			
			ps = conn.prepareStatement("select * from login where id = '" + id
					+ "' or username like'%" + username
					+ "%' or password like'%" + password + "%'");
			res = ps.executeQuery();
			while (res.next()) {
				userbean.setId(res.getInt(3));
				userbean.setNickname(res.getString(1));
				userbean.setPassword(res.getString(2));

				al.add(userbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSource();
		}
		return al;
	}
	
	//注销用户
	public boolean cancleUser(String username,String password){
		boolean bool = false;
		try {
			conn = new ConnDB().getConnection(dbname, username, pass);
			ps = conn.prepareStatement("delete from login where username = '"+username+"' and password = '"+password+"'");
			int i = ps.executeUpdate();
			if(i == 1){
				bool = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeSource();
		}
		return bool;
	}
	//查询是否重复
		public String legalUserName(String userName) throws SQLException{
			String flag="1";
			conn = new ConnDB().getConnection(dbname, username, pass);
			stmt = conn.createStatement();
			res = stmt.executeQuery("select user_pass from user_db "
					+ "where user_account = '"+ userName + "'");
			if (res.next()) {
				
				//System.out.println("illegal");
				flag="0";
				
				}
			return flag;
		}
	
	
	//检索pic_db表返回三张图片路径
		public ArrayList<String> getThreeRandomPicture()
		{
			ArrayList<String> alString = new ArrayList<String>();
			try
			{
				conn = new ConnDB().getConnection(dbname, username, pass);
				stmt = conn.createStatement();
				// 查询数据库
				res = stmt.executeQuery("select count(*) from pic_db");
				int count = 0;
				if (res.next()) {
					count = res.getInt(1);
				}
				while(alString.size() < 3)
				{
					int selectNumber = (int)(Math.random() * (count) + 1);
					System.out.println(selectNumber);
					res = stmt.executeQuery("select * from pic_db where pic_id = '" + selectNumber + "'");
					while(res.next())
					{
						boolean bool = true;
						for(int i = 0; i < alString.size(); i++)
						{
							if(alString.get(i).equals(res.getString(5)))
							{
								bool = false;
							}
						}
						if(bool)
						{
							alString.add(res.getString(5));
						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally
			{
				this.closeSource();
			}
			return alString;
		}
		
		//返回Keyword列表
		public ArrayList<KeywordBean> returnKeywordList()
		{
			ArrayList<KeywordBean> keywordlist = new ArrayList<KeywordBean>();
			try
			{
				conn = new ConnDB().getConnection(dbname, username, pass);
				stmt = conn.createStatement();
				res = stmt.executeQuery("select * from keyword_db");
				while(res.next())
				{
					KeywordBean keywordbean = new KeywordBean();
					keywordbean.setId(res.getInt(1));
					keywordbean.setKeyword(res.getString(2));
					keywordlist.add(keywordbean);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally
			{
				this.closeSource();
			}
			return keywordlist;
		}

		public boolean isPicCollected(String account, String path){
			if("".equals(account) || account == null)
				return false;
			
			ArrayList<String> p = new ArrayList<String>();
			
			try {
				conn = new ConnDB().getConnection(dbname, username, pass);
				ps = conn.prepareStatement("SELECT pic_path FROM "
						+ "pic_user_relation INNER JOIN user_db "
						+ "ON pic_user_relation.user_id=user_db.user_id "
						+ "INNER JOIN pic_db ON pic_db.pic_id="
						+ "pic_user_relation.pic_id "
						+ "WHERE user_account = '" + account + "'");
				res = ps.executeQuery();
				while(res.next()){
					p.add(res.getString(1));
					//System.out.println(res.getString(1));
					
				
				}
				if(!p.isEmpty()) {
					for(int i = 0; i < p.size(); i++) {
						if(p.get(i).equals(path))
							return true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.closeSource();
			}

			return false;
		}
		//找到当前人物的收藏图片
		public ArrayList<PictureBean> getCollectedPicture(String useraccont){
			ArrayList<PictureBean> alPicture = new ArrayList<PictureBean>();
			conn = new ConnDB().getConnection(dbname, username, pass);
			try {
				ps = conn.prepareStatement("SELECT * FROM pic_db NATURAL JOIN pic_user_relation NATURAL JOIN user_db WHERE user_account='"+useraccont+"'");
				res = ps.executeQuery();
				while(res.next()){
					PictureBean picturebean = new PictureBean();
					picturebean.setPictureDpi(res.getString(4));
					picturebean.setPictureId(res.getInt(2));
					picturebean.setClicks(res.getInt(5));
					picturebean.setPath(res.getString(6));
					picturebean.setDescribe(res.getString(9));
					picturebean.setUploadName(res.getString(7));
					picturebean.setUploadTime(res.getString(8));
					alPicture.add(picturebean);
				}
				return alPicture;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		//发送邮件
		public void SendMail(String account)throws Exception{
			Properties prop = new Properties();
	        prop.setProperty("mail.host", "smtp.sohu.com");
	        prop.setProperty("mail.transport.protocol", "smtp");
	        prop.setProperty("mail.smtp.auth", "true");
	        //使用JavaMail发送邮件的5个步骤
	        //1、创建session
	        Session session = Session.getInstance(prop);
	        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
	        session.setDebug(true);
	        //2、通过session得到transport对象
	        Transport ts = session.getTransport();
	        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
	        ts.connect("smtp.sohu.com", "ruanri1401", "t1dCKyfJWiXC");
	        //4、创建邮件
	        Message message = createSimpleMail(session,account);
	        //5、发送邮件
	        message.saveChanges();
	        ts.sendMessage(message, message.getAllRecipients());
	        
	        ts.close();
		}
		public MimeMessage createSimpleMail(Session session,String account)
	            throws Exception {
	        //创建邮件对象
	        MimeMessage message = new MimeMessage(session);
	        //指明邮件的发件人
	        message.setFrom(new InternetAddress("ruanri1401@sohu.com"));
	        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(account));
	        //邮件的标题
	        message.setSubject("欢迎注册");
	        //邮件的文本内容
	        message.setContent("你好啊！", "text/html;charset=UTF-8");
	        //返回创建好的邮件对象
	        return message;
	    }
		//从相应的Keyword的picture的ArrayList中随机选择三张
		public ArrayList<PictureBean> getThreeRandomPictureByKeyword(String keyword)
		{
			ArrayList<PictureBean> alPicture = new ArrayList<PictureBean>();
			ArrayList<PictureBean> alPicture1 = new ArrayList<PictureBean>();
			ArrayList<RelationBean> relationList = new ArrayList<RelationBean>();
			int i = 0;
			try
			{
				conn = new ConnDB().getConnection(dbname, username, pass);
				stmt = conn.createStatement();
				res = stmt.executeQuery("select * from keyword_db where keywords= '" + keyword + "'");
				while(res.next())
				{
					i = res.getInt(1);
				}
				res = stmt.executeQuery("select * from pic_keyword_relation where key_id= '" + i + "'");
				while(res.next())
				{
					RelationBean relationbean = new RelationBean();
					relationbean.setKeywordId(res.getInt(2));
					relationbean.setPictureId(res.getInt(1));
					relationList.add(relationbean);
				}
				for(int j = 0; j < relationList.size(); j++)
				{
					res = stmt.executeQuery("select * from pic_db where pic_id= '" + relationList.get(j).getPictureId() + "'");
					while(res.next())
					{
						PictureBean picturebean = new PictureBean();
						picturebean.setPictureDpi(res.getString(3));
						picturebean.setPictureId(res.getInt(1));
						picturebean.setClicks(res.getInt(4));
						picturebean.setPath(res.getString(5));
						picturebean.setUploadName(res.getString(6));
						picturebean.setUploadTime(res.getString(7));
						picturebean.setDescribe(res.getString(8));
						alPicture.add(picturebean);
					}
				}
				while(alPicture1.size() < 3)
				{
					int selectNumber = (int)(Math.random() * (alPicture.size()));
					boolean bool = true;
					for(int j = 0; j < alPicture1.size(); j++)
					{
						if(alPicture1.get(j).equals(alPicture.get(selectNumber)))
						{
							bool = false;
						}
					}
					if(bool)
					{
						alPicture1.add(alPicture.get(selectNumber));
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally
			{
				this.closeSource();
			}
			return alPicture1;
		}

				//取得显示页数
				public int getPicturePageCount(ArrayList<PictureBean> alPicture)
				{
					
					rowCount = alPicture.size();
					if(rowCount % pageSize == 0)
					{
						pageCount = rowCount / pageSize;
					}else
					{
						pageCount = rowCount / pageSize + 1;
					}
					return pageCount;
				}
				//返回图片的信息（分页）
				public ArrayList<PictureBean> getPictureByPage(int pageNow, ArrayList<PictureBean> alPicture1)
				{
					ArrayList<PictureBean> alPicture2 = new ArrayList<PictureBean>();
					rowCount = alPicture1.size();
					if(rowCount % pageSize == 0)
					{
						pageCount = rowCount / pageSize;
					}else
					{
						pageCount = rowCount / pageSize + 1;
					}
					for(int i = 0; i < Math.min(pageSize, alPicture1.size() - (pageNow - 1) * pageSize); i++)
					{
						PictureBean picturebean = alPicture1.get(pageSize * (pageNow - 1) + i);
						alPicture2.add(picturebean);
					}
					return alPicture2;
				}
				
				public UserBean getUserbean(String account) {
					UserBean ub = new UserBean();
					ub.setEmail(account);
					//连接数据库，补全userbean信息
					try {
						conn = new ConnDB().getConnection(dbname, username, pass); 
						stmt = conn.createStatement();
						res = stmt.executeQuery("select user_pass,nickname,"
								+ "user_picture,sign,user_id from user_db "
								+ "where user_account = '"+ account + "'");
						
						if(res.next()) {
							ub.setPassword(res.getString(1));
							ub.setNickname(res.getString(2));
							ub.setHead_path(res.getString(3));
							ub.setSign(res.getString(4));
							ub.setId(res.getInt(5));
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						closeSource();
					}
					
					return ub;
				}
				//添加上传图片到数据库
				public void InsertPicture(String path,String dpi,long size,String time,String name){
					try {
						
						
						conn = new ConnDB().getConnection(dbname, username, pass);
					    String sql = "insert into pic_db(pic_size,pic_dpi,pic_favors,pic_path,upload_time,upload_name,pic_describe) values('"+size + "','" + dpi+ "','" +"0"+ "','" +path+"','"+time+"','"+name+"','"+" "+ "')";
					    System.out.println(sql);
						ps = conn.prepareStatement(sql);
						ps.executeUpdate();
						
		
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						this.closeSource();
					}
				}
				//插入到关键字-图片表
				public boolean InsertKeywordDB(String path, ArrayList<String> keywords) {
					int kid = 0;
					int pid = 0;
					
					
					try {
						for(int i = 0; i < keywords.size(); i++) {
							conn = new ConnDB().getConnection(dbname, username, pass);
							stmt = conn.createStatement();
							
							stmt = conn.createStatement();
							res = stmt.executeQuery("select pic_id from pic_db "
									+ "where pic_path = '" + path + "'");
							if(res.next())
								pid = res.getInt(1);
							
							res = stmt.executeQuery("select keywords_id from keyword_db "
									+ "where keywords = '" + keywords.get(i) + "'");
							if(res.next())
								kid = res.getInt(1);
							if(kid != 0) {
								PreparedStatement pstmt;
								pstmt = conn.prepareStatement("insert into "
										+ "pic_keyword_relation values ('" 
										+ pid + "', '" + kid + "')");
								pstmt.execute();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						this.closeSource();
					}
					return false;
				}
				//更新用户消息
				public void Updateinfo(String user_pic,String nickname,String single,String account){
					try {
						conn = new ConnDB().getConnection(dbname, username, pass);
						ps = conn.prepareStatement("update user_db set nickname ='" + nickname
								+ "',user_picture='" + user_pic +  "',sign='"+single+"' where user_account = '" + account + "'");
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						this.closeSource();
					}
				}
				//增加收藏数
				public boolean addClicks(String pic_path) {
					pic_path = pic_path.substring(8, pic_path.length());
					int click = 0;
					try
					{
						conn = new ConnDB().getConnection(dbname, username, pass);
						stmt = conn.createStatement();
						res = stmt.executeQuery("select pic_favors from pic_db "
								+ "where pic_path = '"+ pic_path + "'");
						if(res.next())
							click = res.getInt(1) + 1;
						PreparedStatement pstmt;
						pstmt = conn.prepareStatement("update pic_db set "
								+ "pic_favors = '" + click 
								+ "' where pic_path = '"+ pic_path + "'");
						pstmt.execute();
						return true;
					}catch(Exception e)
					{
						e.printStackTrace();
					}finally
					{
						this.closeSource();
					}
					return false;
				}
				//添加上传头像到数据库
				public void uploaduserPicture(String account,String user_picture){
					try {
						conn = new ConnDB().getConnection(dbname, username, pass);
					    String sql = "update user_db set "
								+ "user_picture = '" + user_picture 
								+ "' where user_account = '"+ account + "'";
					    
						ps = conn.prepareStatement(sql);
						ps.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						this.closeSource();
					}
				}
				//上传头像专用更新方法
				public void Updateinfoheadpic(String nickname,String single,String account){
					try {
						conn = new ConnDB().getConnection(dbname, username, pass);
						ps = conn.prepareStatement("update user_db set nickname ='" + nickname
								 +  "',sign='"+single+"' where user_account = '" + account + "'");
						ps.executeUpdate();
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						this.closeSource();
					}
				}
				//添加图片描述
				public void uploadPicturedes(String path,String des){
					try {
						conn = new ConnDB().getConnection(dbname, username, pass);
					    String sql = "update pic_db set "
								+ "pic_describe = '" + des 
								+ "' where pic_path = '"+ path + "'";
					    
						ps = conn.prepareStatement(sql);
						ps.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						this.closeSource();
					}
				}
				//从数据库中返回点击数最多的三张图片路径
				public ArrayList<PictureBean> getThreePictureOrderByClicks()
				{
					ArrayList<PictureBean> alPicture = new ArrayList<PictureBean>();
					try
					{
						conn = new ConnDB().getConnection(dbname, username, pass);
						stmt = conn.createStatement();
						res = stmt.executeQuery("SELECT * FROM pic_db ORDER BY pic_favors DESC LIMIT 3");
						while(res.next())
						{
							PictureBean picturebean = new PictureBean();
							picturebean.setPictureDpi(res.getString(3));
							picturebean.setPictureId(res.getInt(1));
							picturebean.setClicks(res.getInt(4));
							picturebean.setPath(res.getString(5));
							picturebean.setUploadName(res.getString(6));
							picturebean.setUploadTime(res.getString(7));
							picturebean.setDescribe(res.getString(8));
							alPicture.add(picturebean);
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}finally
					{
						this.closeSource();
					}
					return alPicture;
				}

				//通过keyword搜索图片方法
				public ArrayList<PictureBean> searchPicture(String keyword)
				{
					ArrayList<PictureBean> alPicture = new ArrayList<PictureBean>();
					ArrayList<KeywordBean> alKeyword = new ArrayList<KeywordBean>();;
					ArrayList<RelationBean> alRelation = new ArrayList<RelationBean>();
					try
					{
						conn = new ConnDB().getConnection(dbname, username, pass);
						ps = conn.prepareStatement("select * from keyword_db where keywords like '%" + keyword + "%'");
						System.out.println(keyword);
						res = ps.executeQuery();
						while(res.next())
						{
							KeywordBean keywordbean = new KeywordBean();
							keywordbean.setId(res.getInt(1));
							keywordbean.setKeyword(res.getString(2));
							
							alKeyword.add(keywordbean);
						}

						for(int i = 0; i < alKeyword.size(); i++)
						{
							int keyword_id = alKeyword.get(i).getId();
							ps = conn.prepareStatement("select * from pic_keyword_relation where key_id = '" + keyword_id + "'");
							res = ps.executeQuery();
							while(res.next())
							{
								RelationBean relationbean = new RelationBean();
								relationbean.setKeywordId(res.getInt(2));
								relationbean.setPictureId(res.getInt(1));
								
								alRelation.add(relationbean);
							}

						}
						for(int i = 0; i < alRelation.size(); i++)
						{
							System.out.println(alRelation.get(i).getPictureId());
							ps = conn.prepareStatement("select * from pic_db where pic_id = '" + alRelation.get(i).getPictureId() + "'");
							res = ps.executeQuery();
							while(res.next())
							{
								boolean bool = true;
								PictureBean picturebean = new PictureBean();
								picturebean.setPictureDpi(res.getString(3));
								picturebean.setPictureId(res.getInt(1));
								picturebean.setClicks(res.getInt(4));
								picturebean.setPath(res.getString(5));
								picturebean.setUploadName(res.getString(6));
								picturebean.setUploadTime(res.getString(7));
								picturebean.setDescribe(res.getString(8));
								
								for(int j = 0; j < alPicture.size(); j++)
								{
									if(alPicture.get(j).getPictureId() == res.getInt(1))
									{
										bool = false;
									}
								}
								if(bool)
								{
									alPicture.add(picturebean);
								}
							}

						}
						ps = conn.prepareStatement("select * from pic_db where pic_describe like '%" + keyword + "%'");
						res = ps.executeQuery();
						while(res.next())
						{
							boolean bool = true;
							PictureBean picturebean = new PictureBean();
							picturebean.setPictureDpi(res.getString(3));
							picturebean.setPictureId(res.getInt(1));
							picturebean.setClicks(res.getInt(4));
							picturebean.setPath(res.getString(5));
							picturebean.setUploadName(res.getString(6));
							picturebean.setUploadTime(res.getString(7));
							picturebean.setDescribe(res.getString(8));
							for(int j = 0; j < alPicture.size(); j++)
							{
								if(alPicture.get(j).getPictureId() == res.getInt(1))
								{
									bool = false;
								}
							}
							if(bool)
							{
								alPicture.add(picturebean);
							}
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}finally
					{
						this.closeSource();
					}
					
					Collections.sort(alPicture,new Comparator<PictureBean>(){
			            public int compare(PictureBean arg0, PictureBean arg1) {
			                return (String.valueOf(arg1.getClicks())).compareTo(String.valueOf(arg0.getClicks()));
			            }
			        });
					
					return alPicture;
				}
				//展示用户上传图片
				public ArrayList<PictureBean> showUploadPicture(String user_account)
				{
					ArrayList<PictureBean> alPicture = new ArrayList<PictureBean>();
					try
					{
						conn = new ConnDB().getConnection(dbname, username, pass);
						ps = conn.prepareStatement("select * from pic_db where upload_name = '" + user_account + "'");
						res = ps.executeQuery();
						while(res.next())
						{
							PictureBean picturebean = new PictureBean();
							picturebean.setPictureDpi(res.getString(3));
							picturebean.setPictureId(res.getInt(1));
							picturebean.setClicks(res.getInt(4));
							picturebean.setPath(res.getString(5));
							picturebean.setUploadName(res.getString(6));
							picturebean.setUploadTime(res.getString(7));
							picturebean.setDescribe(res.getString(8));
							alPicture.add(picturebean);
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}finally
					{
						this.closeSource();
					}
					return alPicture;
				}
				//传入用户的id以及图片id，进行收藏图片的删除操作，并返回一个布尔值，成功时为true
				public boolean deleteCollection(int userId, int pictureId)
				{
					boolean bool = false;
					try
					{
						conn = new ConnDB().getConnection(dbname, username, pass);
						ps = conn.prepareStatement("delete from pic_user_relation where user_id = '" + userId + "' and pic_id = '" + pictureId + "'");
						int a = ps.executeUpdate();
						if(a == 1)
						{
							System.out.println("操作成功！");
							bool = true;
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}finally
					{
						this.closeSource();
					}
					return bool;
				}
	//传入pic_path，得到图片的PictureBean
	public PictureBean getPictureFromPath(String picPath)
	{
		PictureBean picturebean = new PictureBean();
		try
		{
			conn = new ConnDB().getConnection(dbname, username, pass);
			ps = conn.prepareStatement("select * from pic_db where pic_path = '" + picPath + "'");
			res = ps.executeQuery();
			while(res.next())
			{
				picturebean.setPictureDpi(res.getString(3));
				picturebean.setPictureId(res.getInt(1));
				picturebean.setClicks(res.getInt(4));
				picturebean.setPath(res.getString(5));
				picturebean.setUploadName(res.getString(6));
				picturebean.setUploadTime(res.getString(7));
				picturebean.setDescribe(res.getString(8));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			this.closeSource();
		}
		return picturebean;
	}
}
