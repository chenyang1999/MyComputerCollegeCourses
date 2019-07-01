package servlet;

import dao.CommentDao;
import dao.impl.CommentDaoImpl;
import dao.impl.PhotoDaoImpl;
import dao.impl.UserDaoImpl;
import model.Comment;
import model.Photo;
import vo.CommentWithUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "PhotoServlet", urlPatterns = "/Photo")
public class PhotoServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int photoId = Integer.parseInt(request.getParameter("photoId"));

        CommentDao commentDao = new CommentDaoImpl();
        try
        {
            ArrayList<Comment> commentArrayList = (ArrayList<Comment>) commentDao.findByPhotoId(photoId);
            ArrayList<CommentWithUser> comments = new ArrayList<CommentWithUser>();
            for (Comment comment : commentArrayList)
            {
                comments.add(new CommentWithUser(comment));
            }
            Photo photo = new PhotoDaoImpl().findById(photoId);
            request.setAttribute("photo", photo);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/album/photo.jsp").forward(request, response);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
