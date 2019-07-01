package ouc.javaweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录验证过滤器
 * 
 * @author xiaodong
 *
 */
@WebFilter(filterName = "LoginVerificationFilter", urlPatterns = { "/index/*" })
public class LoginVerificationFilter implements Filter {

	public LoginVerificationFilter() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		System.out
				.println(req.getServletPath().substring(1) + " have passed filter named " + this.getClass().getName());

		// 如果Session对象中保存了用户名，则认为用户已经登录；否则重定向到登录页面
		if (req.getSession().getAttribute("username") != null) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect("../user/login.html");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {

	}
}
