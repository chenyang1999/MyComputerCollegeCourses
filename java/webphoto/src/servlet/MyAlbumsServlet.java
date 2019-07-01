package servlet;

import dao.AlbumDao;
import dao.impl.AlbumDaoImpl;
import dao.impl.UserDaoImpl;
import vo.AlbumWithFirstPhoto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MyAlbumsServlet",urlPatterns = "/MyAlbums")
public class MyAlbumsServlet extends HttpServlet
{
    private AlbumDao albumDao = new AlbumDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //获取userid以判断用户是否登录
        Object userid = request.getSession().getAttribute("userid");
        String creator = request.getParameter("creator");

        //登录状态 访问的是个人相册页面
        if(userid != null)
        {
            if(creator == null || creator.equals(request.getSession().getAttribute("username")))
            {
                try
                {
                    request.setAttribute("albumCount", albumDao.findCountByUserId((int) userid));
                    ArrayList<AlbumWithFirstPhoto> albumList = (ArrayList<AlbumWithFirstPhoto>) albumDao.findByUserId((int) userid);
                    request.setAttribute("albumList", albumList);
                    request.setAttribute("owner", request.getSession().getAttribute("username"));
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/album/myalbums.jsp").forward(request, response);
            }
        }
        else //未登录状态
        {
            try
            {
                int creatorId = new UserDaoImpl().findByName(creator).getId();
                request.setAttribute("albumCount", albumDao.findCountByUserId(creatorId));
                request.setAttribute("albumList", albumDao.findByUserId(creatorId));
                request.setAttribute("owner",creator);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/album/myalbums.jsp").forward(request, response);
        }
    }
}
