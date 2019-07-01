package servlet;

import dao.impl.AlbumDaoImpl;
import vo.AlbumWithFirstPhoto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet",urlPatterns = "/Index")
public class IndexServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            ArrayList<AlbumWithFirstPhoto> albumList = (ArrayList<AlbumWithFirstPhoto>) new AlbumDaoImpl().findAll();
            request.setAttribute("albumList", albumList);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/index/index.jsp").forward(request, response);
    }
}
