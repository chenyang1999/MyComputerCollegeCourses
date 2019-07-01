package dao.impl;

import dao.AlbumDao;
import model.Album;
import util.DatabaseUtil;
import vo.AlbumWithFirstPhoto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl implements AlbumDao
{
    @Override
    public int insert(Album album) throws SQLException
    {
        String sql = "insert into album (userid, name) values(?,?)";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, album.getUserid());
        pstmt.setString(2, album.getName());
        int result = pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
        return result;
    }

    @Override
    public void rename(int id, String name) throws SQLException
    {
        String sql = "update album set name = ? where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
    }

    @Override
    public void delete(int id) throws SQLException
    {
        String sql = "delete from album where id = ? and delete from photo where albumid = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
    }

    @Override
    public Album findById(int id) throws SQLException
    {
        String sql = "select * from album where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet resultSet = pstmt.executeQuery();
        Album album = null;
        if (resultSet.next())
        {
            album = new Album();
            album.setId(resultSet.getInt(1));
            album.setUserid(resultSet.getInt(2));
            album.setName(resultSet.getString(3));
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return album;
    }

    @Override
    public Album findByName(String name) throws SQLException
    {
        String sql = "select * from album where name = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet resultSet = pstmt.executeQuery();
        Album album = null;
        if (resultSet.next())
        {
            album = new Album();
            album.setId(resultSet.getInt(1));
            album.setUserid(resultSet.getInt(2));
            album.setName(resultSet.getString(3));
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return album;
    }

    @Override
    public List<AlbumWithFirstPhoto> findByUserId(int userId) throws SQLException
    {
        String sql = "select * from album where userid = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userId);
        ResultSet resultSet = pstmt.executeQuery();
        ArrayList<AlbumWithFirstPhoto> albumList = new ArrayList<AlbumWithFirstPhoto>();
        while (resultSet.next())
        {
            Album album = new Album();
            album.setId(resultSet.getInt(1));
            album.setUserid(resultSet.getInt(2));
            album.setName(resultSet.getString(3));
            albumList.add(new AlbumWithFirstPhoto(album,
                    new PhotoDaoImpl().findFirstPathOfAlbum(album.getId())));
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return albumList;
    }

    @Override
    public int findCountByUserId(int userId) throws SQLException
    {
        String sql = "select count(*) from album where userid = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userId);
        ResultSet resultSet = pstmt.executeQuery();
        int result = 0;
        if (resultSet.next())
        {
            result = resultSet.getInt(1);
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return result;
    }

    @Override
    public List<AlbumWithFirstPhoto> findAll() throws SQLException
    {
        String sql = "select * from album";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        ArrayList<AlbumWithFirstPhoto> albumList = new ArrayList<AlbumWithFirstPhoto>();
        while (resultSet.next())
        {
            Album album = new Album();
            album.setId(resultSet.getInt(1));
            album.setUserid(resultSet.getInt(2));
            album.setName(resultSet.getString(3));
            albumList.add(new AlbumWithFirstPhoto(album,
                    new PhotoDaoImpl().findFirstPathOfAlbum(album.getId())));
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return albumList;
    }

    public List<Album> findFirst() throws SQLException
    {
        String sql = "select * from album limit 0,6";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        ArrayList<Album> albumList = new ArrayList<Album>();
        while (resultSet.next())
        {
            Album album = new Album();
            album.setId(resultSet.getInt(1));
            album.setUserid(resultSet.getInt(2));
            album.setName(resultSet.getString(3));
            albumList.add(album);
        }
        DatabaseUtil.close(resultSet, pstmt, conn);
        return albumList;
    }

//    public static void main(String[] args)
//    {
//        try
//        {
//            System.out.println(new AlbumDaoImpl().findFirst());
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) throws SQLException
//    {
//        AlbumDao albumDao = new AlbumDaoImpl();
//        Album album = new Album();
//        album.setName("sun's album");
//        album.setUserid(1);
//        System.out.println(albumDao.insert(album));
//
//        albumDao.rename(1,"sunshine's album");
//
//        albumDao.delete(2);
//
//        System.out.println(albumDao.findById(3));
//        System.out.println(albumDao.findByUserId(1));
//        System.out.println(albumDao.findCountByUserId(1));
//    }
}
