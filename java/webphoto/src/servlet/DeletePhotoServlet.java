package servlet;

import dao.impl.AlbumDaoImpl;
import dao.impl.PhotoDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeletePhotoServlet",urlPatterns = "/DeletePhoto")
public class DeletePhotoServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int photoId = Integer.parseInt(request.getParameter("photoId"));
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        System.out.println(albumId);
        try
        {
            new PhotoDaoImpl().delete(photoId);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/Album?albumId=" + albumId);
    }
}
