package servlet;

import dao.impl.AlbumDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteAlbumServlet",urlPatterns = "/DeleteAlbum")
public class DeleteAlbumServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("albumId"));
        try
        {
            new AlbumDaoImpl().delete(id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/MyAlbums");
    }
}
