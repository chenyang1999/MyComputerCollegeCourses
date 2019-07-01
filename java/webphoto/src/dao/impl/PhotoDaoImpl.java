package dao.impl;

import dao.PhotoDao;
import model.Photo;
import util.DatabaseUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoDaoImpl implements PhotoDao
{
    @Override
    public int insert(Photo photo) throws SQLException
    {
        String sql = "insert into photo (albumid, photopath) values(?, ?)";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,photo.getAlbumid());
        pstmt.setString(2,photo.getPhotopath());
        int result = pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
        return result;
    }

    @Override
    public void delete(int id) throws SQLException
    {
        String sql = "delete from photo where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
        DatabaseUtil.close(null, pstmt, conn);
    }

    @Override
    public Photo findById(int id) throws SQLException
    {
        String sql = "select * from photo where id = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet resultSet =  pstmt.executeQuery();
        Photo photo = null;
        if (resultSet.next())
        {
            photo = new Photo();
            photo.setId(resultSet.getInt(1));
            photo.setAlbumid(resultSet.getInt(2));
            photo.setPhotopath(resultSet.getString(3));
        }
        DatabaseUtil.close(null, pstmt, conn);
        return photo;
    }

    @Override
    public List<Photo> findByAlbumId(int albumId) throws SQLException
    {
        String sql = "select * from photo where albumid = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,albumId);
        ResultSet resultSet =  pstmt.executeQuery();
        ArrayList<Photo> photoList = new ArrayList<Photo>();
        while (resultSet.next())
        {
            Photo photo = new Photo();
            photo.setId(resultSet.getInt(1));
            photo.setAlbumid(resultSet.getInt(2));
            photo.setPhotopath(resultSet.getString(3));
            photoList.add(photo);
        }
        DatabaseUtil.close(null, pstmt, conn);
        return photoList;
    }

    public String findFirstPathOfAlbum(int albumId) throws SQLException
    {
        String sql = "select photopath from photo where albumid = ? limit 0,1";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,albumId);
        ResultSet resultSet =  pstmt.executeQuery();
        String photoPath = null;
        if (resultSet.next())
        {
            photoPath = resultSet.getString(1);
        }else
            photoPath = "/sources/img/album.jpg";
        DatabaseUtil.close(null, pstmt, conn);
        return photoPath;
    }

    public static void main(String[] args) throws SQLException
    {
        PhotoDao photoDao = new PhotoDaoImpl();
        System.out.println(((PhotoDaoImpl) photoDao).findFirstPathOfAlbum(25));
    }
}
