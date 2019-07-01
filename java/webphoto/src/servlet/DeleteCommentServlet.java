package servlet;

import dao.CommentDao;
import dao.impl.CommentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteCommentServlet",urlPatterns = "/DeleteComment")
public class DeleteCommentServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        try
        {
            new CommentDaoImpl().delete(commentId);
            response.sendRedirect(request.getContextPath() + "/Photo?photoId=" + request.getParameter("photoId"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
