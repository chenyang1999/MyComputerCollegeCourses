package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter
{
    class EncodeRequest extends HttpServletRequestWrapper
    {
        private HttpServletRequest request = null;

        public EncodeRequest(HttpServletRequest request)
        {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name)
        {
            String val = this.request.getParameter(name);
            if (val == null)
            {
                return null;
            }
            try
            {
                val = new String(val.getBytes("iso-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e)
            {
                throw new RuntimeException(e);
            }
            return val;
        }
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        HttpServletRequest req = (HttpServletRequest) request;

//        if (req.getMethod().equals("GET"))
//        {
//            EncodeRequest encodeRequest = new EncodeRequest(req);
//            if(req.getRequestURI().equals("/webphoto/MyAlbums"))
//            {
//                System.out.println(encodeRequest.getParameter("creator"));
//            }
//            chain.doFilter(encodeRequest, response);
//        } else
//        {
//            chain.doFilter(request, response);
//        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
