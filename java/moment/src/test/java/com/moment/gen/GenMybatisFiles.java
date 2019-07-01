package com.moment.gen;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;


public class GenMybatisFiles {
	
	public static void gen(String configFile, String[] tableNames)
			throws IOException, XMLParserException,
			InvalidConfigurationException, SQLException, InterruptedException {
		// �����ļ�
		InputStream in = GenMybatisFiles.class.getResourceAsStream(configFile);

		// �������ö���
		List<String> warnings = new ArrayList<String>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(in);
		addTables(tableNames, config);

		// ��ʼ�� MyBatisGenerator
		boolean overwrite = true;
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);

		// ����2�����������ڸ����������
		ProgressCallback progressCallback = new NullProgressCallback() {
			public void startTask(String taskName) {
				System.out.println("start task:" + taskName);
			}

			public void done() {
				System.out.println("done");
			}
		};
		myBatisGenerator.generate(progressCallback);

		// �鿴����
		if (warnings.size() > 0) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("warnings:");
			for (String warning : warnings) {
				System.out.println(warning);
			}
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

	/**
	 * @param tableNames
	 * @param config
	 */
	private static void addTables(String[] tableNames, Configuration config) {
		config.getContexts();
		for (Context context:config.getContexts()){
			String contextId = context.getId();
			for (String tableName : tableNames) {
				if (isContextTable(tableName,contextId)){
					TableConfiguration tc = new TableConfiguration(new Context(null));
					tc.setTableName(tableName);
					tc.setDomainObjectName(tableName2VOName(tableName.substring(tableName.indexOf("_")+1)));
					tc.setConfiguredModelType("flat");
					context.addTableConfiguration(tc);
				}
				
			}
		}

	}

	private static boolean isContextTable(String tableName,String contextId){
		String tablePrefix = contextId.split("_")[0] ;//��contextid��_��ͷʱ���������б���
		return tableName.startsWith(tablePrefix);
	}
	/**
	 * ������
	 * ���룺 host__deploy_record
	 * ����� HostDeployRecord
	 *//*
	private static  String tableName2ObjectName(String table) {
		StringBuffer sb = new StringBuffer();
		String last = "_";
		table = table.toLowerCase();
		for (int i = 0; i < table.length(); i++) {
			Character c = table.charAt(i);
			String s = c.toString();
			if (!"_".equals(s)) {
				if ("_".equals(last)) {
					sb.append(s.toUpperCase());
				} else {
					sb.append(s);
				}
			}
			last = s;
		}
		return sb.toString();
	}*/
	
	private static String tableName2VOName(String source) {

		StringBuffer sb = new StringBuffer();
		int length = source.length();
		String lastStr = "";
		for (int i = 0; i < length; i++) {
			String str = source.substring(i, i + 1);
			if (i == 0) {
				sb.append(str.toUpperCase());
			} else if ("_".equals(str)) {
			} else {
				if ("_".equals(lastStr)) {
					sb.append(str.toUpperCase());
				} else {
					sb.append(str);
				}
			}
			lastStr = str;
		}

		return sb.append("VO").toString();
	}

	
}
