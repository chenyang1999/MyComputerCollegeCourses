package dao.impl;

import dao.CommentDao;
import model.Comment;
import model.User;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao
{
    @Override
    public int insert(Comment comment) throws SQLException
    {
        String sql = "insert into comment (photoid, userid,comment) values(?,?,?)";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, comment.getPhotoid());
        pstmt.setInt(2, comment.getUserid());
        pstmt.setString(3, comment.getComment());
        int result = pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
        return result;
    }

    @Override
    public void delete(int id) throws SQLException
    {
        String sql = "delete from comment where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
    }

    @Override
    public List<Comment> findByPhotoId(int photoid) throws SQLException
    {
        String sql = "select * from comment where photoid = ? order by id desc ";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, photoid);
        ResultSet resultSet = pstmt.executeQuery();
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        while (resultSet.next())
        {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt(1));
            comment.setPhotoid(resultSet.getInt(2));
            comment.setUserid(resultSet.getInt(3));
            comment.setComment(resultSet.getString(4));
            commentList.add(comment);
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return commentList;
    }

//    public static void main(String[] args) throws SQLException
//    {
//        class CommentWithUser extends Comment
//        {
//            String username;
//
//            public String getUsername()
//            {
//                return username;
//            }
//
//            public void setUsername(String username)
//            {
//                this.username = username;
//            }
//
//            CommentWithUser(Comment comment)
//            {
//                setId(comment.getId());
//                setComment(comment.getComment());
//                setPhotoid(comment.getPhotoid());
//                setUserid(comment.getUserid());
//                try
//                {
//                    this.setUsername(new UserDaoImpl().findById(getUserid()).getName());
//                } catch (SQLException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//getPhotopath
//            @Override
//            public String toString()
//            {
//
//                return super.toString()+"CommentWithUser{" +
//                        "username='" + username + '\'' +
//                        '}';
//            }
//        }
//        CommentDao commentDao = new CommentDaoImpl();
//        try
//        {
//            ArrayList<Comment> commentArrayList = (ArrayList<Comment>) commentDao.findByPhotoId(1);
//            ArrayList<CommentWithUser> comments = new ArrayList<CommentWithUser>();
//            for(Comment comment : commentArrayList)
//            {
//                comments.add(new CommentWithUser(comment));
//            }
//            for (CommentWithUser comment : comments)
//            {
//                System.out.println(comment);
//            }
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//    }
}
