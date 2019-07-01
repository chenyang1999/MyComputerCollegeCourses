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
 * HTTP请求头处理Servlet示例
 * 
 * @author xiaodong
 *
 */
@WebServlet("/http-request-header")
public class HttpRequestHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HttpRequestHeaderServlet() {
		super();
	}

	/**
	 * doGet方法
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取响应输出流
		PrintWriter resp = response.getWriter();

		// 将数据（字符序列）写入流
		resp.append("Web Application: ").append(request.getContextPath()).append("\n");

		String browser = request.getHeader("User-Agent"); // 获取浏览器的机器环境
		System.out.println(browser);
		int size = request.getIntHeader("Content-Length"); // 获取请求体长度
		System.out.println(size);
		long datetime = request.getDateHeader("If-Modified-Since"); // 获取浏览器缓存页面的修改时间
		System.out.println(datetime);

		resp.append("\nHTTP request headers includes: \n");

		// 获得当前HTTP请求的所有请求头信息，写入响应对象
		for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
			String headerName = (String) e.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println("Name: " + headerName + "\tValue: " + headerValue);
			resp.append("Name: ").append(headerName).append("\tValue: ").append(headerValue).append("\n");
		}
	}

	/**
	 * doPost方法
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
