package servlet;

import dao.AlbumDao;
import dao.impl.AlbumDaoImpl;
import model.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateAlbumServlet",urlPatterns = "/CreateAlbum")
public class CreateAlbumServlet extends HttpServlet
{
    AlbumDao albumDao = new AlbumDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String createAlbumName = request.getParameter("createAlbumName");
        if(createAlbumName == null || "".equals(createAlbumName = createAlbumName.trim()))
        {
            request.setAttribute("errMsg","相册名不能为空！");
            request.getRequestDispatcher("/MyAlbums").forward(request,response);
        }
        else
        {
            try
            {
                if(albumDao.findByName(createAlbumName) != null)
                {
                    request.setAttribute("errMsg","该相册已经被使用，不可重名！");
                    request.getRequestDispatcher("/MyAlbums").forward(request,response);
                }else
                {
                    Album album = new Album();
                    album .setName(createAlbumName);
                    album.setUserid((int)request.getSession().getAttribute("userid"));
                    albumDao.insert(album);
                    response.sendRedirect(request.getContextPath() + "/MyAlbums");
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
    }
}
