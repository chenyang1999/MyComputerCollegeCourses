package filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginVerfyFilter", urlPatterns = {"/*"})
public class LoginVerfyFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/") + 1);
        if (uri.substring(uri.length() - 4).equals(".jsp")
                && !uri.equals("login.jsp") && !uri.equals("register.jsp"))
        {
            ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/Index");
        }
        if (request.getSession().getAttribute("userid") == null)
        {
            String[] forbidUris = {"CreateAlbum", "DeleteAlbum", "DeletePhoto", "RenameAlbum", "/Upload"};
            for (String forbidUri : forbidUris)
            {
                if (forbidUri.equals(uri))
                {
                    ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/Index");
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
