package dao.impl;

import dao.UserDao;
import model.User;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao
{
    @Override
    public int insert(User user) throws SQLException
    {
        String sql = "insert into user (name, md5, regTime, active) values(?,?,?,?)";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getMd5());
        pstmt.setString(3, user.getRegTime());
        pstmt.setInt(4, user.getActive());
        int result = pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
        return result;
    }

    @Override
    public void rename(int id, String name) throws SQLException
    {
        String sql = "update user set name = ? where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
    }

    @Override
    public void rePassword(int id, String md5) throws SQLException
    {
        String sql = "update user set md5 = ? where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, md5);
        pstmt.setInt(2,id);
        pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
    }

    @Override
    public void delete(int id) throws SQLException
    {
        String sql = "delete from user where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
    }

    @Override
    public User findById(int id) throws SQLException
    {
        String sql = "select * from user where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet resultSet = pstmt.executeQuery();
        User user = null;
        if (resultSet.next())
        {
            user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setMd5(resultSet.getString(3));
            user.setRegTime(resultSet.getString(4));
            user.setActive(resultSet.getInt(5));
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return user;
    }

    @Override
    public User findByName(String name) throws SQLException
    {
        String sql = "select * from user where name = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet resultSet = pstmt.executeQuery();
        User user = null;
        if (resultSet.next())
        {
            user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setMd5(resultSet.getString(3));
            user.setRegTime(resultSet.getString(4));
            user.setActive(resultSet.getInt(5));
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException
    {
        String sql = "select * from user";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        ArrayList<User> userList = new ArrayList<User>();
        while (resultSet.next())
        {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setMd5(resultSet.getString(3));
            user.setRegTime(resultSet.getString(4));
            user.setActive(resultSet.getInt(5));
            userList.add(user);
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return userList;
    }

//    public static void main(String[] args) throws SQLException
//    {
//        System.out.println(new UserDaoImpl().findByName("孙浩杰"));
//    }
}
