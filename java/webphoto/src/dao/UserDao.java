package dao;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDao
{
    public int insert(User user) throws SQLException;

    public void rename(int id, String name) throws SQLException;

    public void rePassword(int id, String md5) throws SQLException;

    public void delete(int id) throws SQLException;

    public User findById(int id) throws SQLException;

    public User findByName(String name) throws SQLException;

    public List<User> findAll() throws SQLException;
}
