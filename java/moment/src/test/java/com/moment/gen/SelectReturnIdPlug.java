package com.moment.gen;

import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class SelectReturnIdPlug extends PluginAdapter {

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		// ����Զ�����
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		element.addAttribute(new Attribute("keyProperty", "id"));
		// TODO Auto-generated method stub
		return super.sqlMapInsertSelectiveElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		// ����Զ�����
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		element.addAttribute(new Attribute("keyProperty", "id"));

		// TODO Auto-generated method stub
		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}
	
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
		topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
		for (InnerClass clz : topLevelClass.getInnerClasses()) {
			clz.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
		}
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}
}
