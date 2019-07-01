package model;

public class Album
{
    int id;
    int userid;
    String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserid()
    {
        return userid;
    }

    public void setUserid(int userid)
    {
        this.userid = userid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Album{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                '}';
    }
}
