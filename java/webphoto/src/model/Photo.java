package model;

public class Photo
{
    int id;
    int albumid;
    String photopath;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAlbumid()
    {
        return albumid;
    }

    public void setAlbumid(int albumid)
    {
        this.albumid = albumid;
    }

    public String getPhotopath()
    {
        return photopath;
    }

    public void setPhotopath(String photopath)
    {
        this.photopath = photopath;
    }

    @Override
    public String toString()
    {
        return "Photo{" +
                "id=" + id +
                ", albumid=" + albumid +
                ", photopath='" + photopath + '\'' +
                '}';
    }
}
