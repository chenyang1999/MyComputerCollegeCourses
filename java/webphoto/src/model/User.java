package model;

import util.SecUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User
{
    //用于表示用户激活状态的静态常量
    public static final int ACTIVITED = 1;
    public static final int UNACTIVITED = 0;

	private int id;
	private String name;
	private String md5;
	private String regTime;
	private int active;

	public User() {}

    public User(String name, String password)
    {
        this.name = name;
        this.md5 = SecUtil.md5(password);
        this.regTime = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
        this.active = ACTIVITED;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMd5()
    {
        return md5;
    }

    public void setMd5(String md5)
    {
        this.md5 = md5;
    }

    public String getRegTime()
    {
        return regTime;
    }

    public void setRegTime(String regTime)
    {
        this.regTime = regTime;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", md5='" + md5 + '\'' +
                ", regTime='" + regTime + '\'' +
                ", active=" + active +
                '}';
    }

    public static void main(String[] args)
    {
        System.out.println(new User("sun", "123"));
    }
}
