package ouc.javaweb.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Session属性监听器
 * 
 * @author xiaodong
 *
 */
@WebListener
public class MySessionAttibuteListener implements HttpSessionAttributeListener {

	public MySessionAttibuteListener() {
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println(
				"Listener " + this.getClass().getName() + " says: " + arg0.getName() + " is added to session. ");
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println(
				"Listener " + this.getClass().getName() + " says: " + arg0.getName() + " is removed from session. ");
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	}

}
