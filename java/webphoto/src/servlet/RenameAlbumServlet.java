package servlet;

import dao.AlbumDao;
import dao.impl.AlbumDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RenameAlbumServlet", urlPatterns = "/RenameAlbum")
public class RenameAlbumServlet extends HttpServlet
{
    AlbumDao albumDao = new AlbumDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("albumId"));
        String renameAlbumName = request.getParameter("renameAlbumName");

        try
        {
            if (renameAlbumName == null || "".equals(renameAlbumName = renameAlbumName.trim()))
            {
                request.setAttribute("errMsg", "修改后的相册名不能为空");
                request.getRequestDispatcher("/MyAlbums").forward(request,response);
            } else
            {

                if (albumDao.findByName(renameAlbumName) != null)
                {
                    request.setAttribute("errMsg", "该相册名已经被使用，相册名不可重复！");
                    request.getRequestDispatcher("/MyAlbums").forward(request,response);
                } else
                {
                    albumDao.rename(id,renameAlbumName);
                    response.sendRedirect(request.getContextPath() + "/MyAlbums");
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
