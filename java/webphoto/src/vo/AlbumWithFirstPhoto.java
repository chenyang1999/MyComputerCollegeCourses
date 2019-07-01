package vo;

import model.Album;

public class AlbumWithFirstPhoto extends Album
{
    private String firstPhotoPath;

    public AlbumWithFirstPhoto(Album album, String firstPhotoPath)
    {
        setId(album.getId());
        setName(album.getName());
        setUserid(album.getUserid());
        this.firstPhotoPath = firstPhotoPath;
    }

    public String getFirstPhotoPath()
    {
        return firstPhotoPath;
    }

    public void setFirstPhotoPath(String firstPhotoPath)
    {
        this.firstPhotoPath = firstPhotoPath;
    }
}
