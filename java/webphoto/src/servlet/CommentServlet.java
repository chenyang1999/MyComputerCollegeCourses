package servlet;

import dao.impl.CommentDaoImpl;
import model.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CommentServlet", urlPatterns = "/Comment")
public class CommentServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Object userId = request.getSession().getAttribute("userid");
        if(userId != null)
        {
            int photoId = Integer.parseInt(request.getParameter("photoId"));
            Comment comment = new Comment();
            comment.setPhotoid(photoId);
            System.out.println(userId);
            comment.setUserid((int)userId);
            comment.setComment(request.getParameter("comment"));
            try
            {
                if(new CommentDaoImpl().insert(comment) == 1)
                {
                    response.sendRedirect(request.getContextPath() + "/Photo?photoId=" + photoId);
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }else
        {
            request.setAttribute("commentErrMsg","必须登录之后才能评论，请先登录");
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);
        }
    }
}
