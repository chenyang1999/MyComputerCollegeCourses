package dao;

import model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao
{
    public int insert(Comment comment) throws SQLException;
    public void delete(int id) throws SQLException;
    public List<Comment> findByPhotoId(int photoid) throws SQLException;
}
