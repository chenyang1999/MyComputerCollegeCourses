package model;

public class Comment
{
    int id;
    int photoid;
    int userid;

    public int getUserid()
    {
        return userid;
    }

    public void setUserid(int userid)
    {
        this.userid = userid;
    }

    String comment;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPhotoid()
    {
        return photoid;
    }

    public void setPhotoid(int photoid)
    {
        this.photoid = photoid;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    @Override
    public String toString()
    {
        return "Comment{" +
                "id=" + id +
                ", photoid=" + photoid +
                ", userid=" + userid +
                ", comment='" + comment + '\'' +
                '}';
    }
}
