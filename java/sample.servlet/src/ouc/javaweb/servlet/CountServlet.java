package ouc.javaweb.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 累计计数的Servlet示例
 * 
 * @author xiaodong
 *
 */
@WebServlet("/count")
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 0;

	public CountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServletContext sc = this.getServletContext();
		System.out.println(sc.getServerInfo());
		
		HttpSession hs = request.getSession();
		hs.setMaxInactiveInterval(20);
		System.out.println(hs.getId());
		
		count += 1;

		/*
		 * 演示 Servlet 的单例模式，可以通过多个浏览器访问查看累计计数
		 * 
		 * 问题：1.如果我就想看我自己调用了多少次呢？ 2.这个Servlet的实例到底能活多久呢？
		 */
		Cookie cookie = new Cookie("COUNT", String.valueOf(count));
		//cookie.setMaxAge(10);
		
		response.addCookie(cookie);
		
		response.getWriter()
				.append("I, an instance of class ServletCount, with code " + this.hashCode() + " is invoked ")
				.append(String.valueOf(count)).append(" times in my life. \n")
				.append("I am working in the session with id " + request.getSession().getId() + ". \n")
				.append("Don't forget, my name is " + this.getServletName())
				.append(" in " + sc.getAttribute(this.getServletName()) + " cycles. ");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
		ServletContext sc = this.getServletContext();

		if (sc.getAttribute(this.getServletName()) == null) {
			sc.setAttribute(this.getServletName(), 1);
		} else {
			sc.setAttribute(this.getServletName(), (Integer) sc.getAttribute(this.getServletName()) + 1);
		}

	}
}
