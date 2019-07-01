package dao;

import model.Photo;

import java.sql.SQLException;
import java.util.List;

public interface PhotoDao
{
    public int insert(Photo photo) throws SQLException;
    public void delete(int id) throws SQLException;
    public Photo findById(int id) throws SQLException;
    public List<Photo> findByAlbumId(int AlbumId) throws SQLException;
}
