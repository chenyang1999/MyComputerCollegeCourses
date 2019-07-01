package dao;

import model.Album;
import vo.AlbumWithFirstPhoto;

import java.sql.SQLException;
import java.util.List;

public interface AlbumDao
{
    public int insert(Album album) throws SQLException;

    public void rename(int id, String name) throws SQLException;

    public void delete(int id) throws SQLException;

    public Album findById(int id) throws SQLException;

    public Album findByName(String name) throws SQLException;

    public List<AlbumWithFirstPhoto> findByUserId(int userId) throws SQLException;

    public int findCountByUserId(int userId) throws SQLException;

    public List<AlbumWithFirstPhoto> findAll() throws SQLException;
}
