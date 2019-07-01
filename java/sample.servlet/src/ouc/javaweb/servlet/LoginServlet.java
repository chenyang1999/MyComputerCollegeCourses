package ouc.javaweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	/**
	 * 请同学完成代码<br>
	 * 要求：如果用户直接请求login，如果在Session对象中有属性username等于admin，则重定向到index.html<br>
	 * 否则重定向到login.html
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Code goes here.
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username != null) {
			response.sendRedirect("index/index.html");
		} else {
			response.sendRedirect("user/login.html");
		}
	}

	/**
	 * 请同学编写代码完成对login.html表单提交数据的处理<br>
	 * 要求：验证用户名是否为admin，密码是否为admin，如果通过验证<br>
	 * 则将username属性保存到session对象，然后重定向到index.html
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Code goes here.
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("admin".equals(username) && "admin".equals(password)) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect("index/index.html");
		} else {
			response.sendRedirect("user/login.html");
		}
	}

}
