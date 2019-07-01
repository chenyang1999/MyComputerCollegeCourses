package ouc.javaweb.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session对象监听器
 * 
 * @author xiaodong
 *
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

	public MySessionListener() {
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Listener " + this.getClass().getName() + " - Session is created.");
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Listener " + this.getClass().getName() + " - Session is destroyed.");
	}

}
