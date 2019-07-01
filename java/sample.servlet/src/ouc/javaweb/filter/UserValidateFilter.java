package ouc.javaweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
@WebFilter(filterName = "UserValidateFilter", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "EXCLUDED_PAGES", value = "login.html;login.jsp;bootstrap/4.1.3/css/bootstrap.min.css") })
		*/
public class UserValidateFilter implements Filter {

	private String excludedPages;
	private String[] excludedPageArray;

	public UserValidateFilter() {

	}

	public void init(FilterConfig config) throws ServletException {
		excludedPages = config.getInitParameter("EXCLUDED_PAGES");

		if (null != excludedPages && excludedPages.length() != 0) {
			excludedPageArray = excludedPages.split(String.valueOf(';'));
		}
	}

	public void destroy() {
		this.excludedPages = null;
		this.excludedPageArray = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		boolean isExcludedPage = false;

		System.out.println(req.getServletPath().substring(1) + " have passed filter named " + this.getClass());

		// 遍历例外URL数组，判断当前URL是否与例外页面相同
		for (String page : excludedPageArray) {
			if (req.getServletPath().substring(1).equals(page)) {
				System.out.println(page + ", you're excluded.");
				isExcludedPage = true;
				break;
			}
		}

		if (isExcludedPage || req.getSession().getAttribute("username") != null) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect("login.html");
		}
	}

}
