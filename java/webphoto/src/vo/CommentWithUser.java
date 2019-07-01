package vo;

import dao.impl.UserDaoImpl;
import model.Comment;

import java.sql.SQLException;

public class CommentWithUser extends Comment
{
    String username;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public CommentWithUser(Comment comment)
    {
        setId(comment.getId());
        setComment(comment.getComment());
        setPhotoid(comment.getPhotoid());
        setUserid(comment.getUserid());
        try
        {
            this.setUsername(new UserDaoImpl().findById(getUserid()).getName());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}