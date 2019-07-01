package sample.commandline;

import java.util.Properties;
import java.util.Enumeration;

/**
 * 系统属性示例
 * 
 * @author xiaodong
 *
 */
public class SystemPropertiesSample {
	
	public static void main(String[] args) {
		Properties ps = System.getProperties();
		ps.setProperty("myName", "myValue");
		
		Enumeration<?> pn = ps.propertyNames(); // 返回以Enumeration类型表示的所有可用系统属性的名称
		
		while (pn.hasMoreElements()) {
			String pName = (String) pn.nextElement();
			String pValue = ps.getProperty(pName);
			
			System.out.println(pName + " : " + pValue);
		}
	}
}