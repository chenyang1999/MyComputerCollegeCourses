package servlet;

import dao.impl.AlbumDaoImpl;
import dao.impl.PhotoDaoImpl;
import dao.impl.UserDaoImpl;
import model.Album;
import model.Photo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AlbumServlet",urlPatterns = "/Album")
public class AlbumServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        try
        {
            Album album = new AlbumDaoImpl().findById(albumId);
            request.setAttribute("photoList",(ArrayList<Photo>) new PhotoDaoImpl().findByAlbumId(albumId));
            request.setAttribute("albumName",album.getName());
            request.setAttribute("creator",new UserDaoImpl().findById(album.getUserid()).getName());
            request.setAttribute("albumId",albumId);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/album/album.jsp").forward(request,response);
    }
}
