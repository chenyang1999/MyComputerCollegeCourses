package ouc.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP请求数据处理示例
 * 
 * @author xiaodong
 *
 */
@WebServlet("/http-request-data")
public class HttpRequestDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HttpRequestDataServlet() {
		super();
	}

	/**
	 * doGet方法，处理HTTP GET请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 解决中文响应乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");

		response.getWriter().println("当前URL参数message的值为： " + request.getParameter("message") + " 加一个期限，那就是 " + request.getParameter("years") + " 年");

		// 获取URL参数名为message的数据值
		System.out.println("当前URL参数message的值为： " + request.getParameter("message"));

	}

	/**
	 * doPost方法，处理HTTP POST请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		System.out.println("我收到的消息是： " + request.getParameter("message"));

		// 解决中文响应乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");

		// 获取表单提交的数据，表单数据走POST请求
		response.getWriter().println("我收到的消息是： " + request.getParameter("message"));
	}
}
