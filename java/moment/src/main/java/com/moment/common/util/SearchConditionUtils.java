package com.moment.common.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moment.common.domain.PageVO;
import com.moment.datatables.domain.Column;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.ExampleProxy;
import com.moment.datatables.domain.Order;


public class SearchConditionUtils {
	/**
	 * 封装排序字段
	 * 
	 * @param columns
	 * @param orders
	 * @return
	 */
	public static String getOrderString(List<Column> columns, List<Order> orders) {
		StringBuilder strbuf = new StringBuilder("");
		if (orders != null) {
			for (int i = 0; i < orders.size(); i++) {
				Order order = orders.get(i);
				strbuf.append(columns.get(order.getColumn()).getData())
						.append(" ").append(order.getDir());
				if (i < orders.size() - 1) {
					strbuf.append(",");
				}
			}
		}
		return strbuf.toString();
	}

	/**
	 * 必填查询条件判断
	 */
	public static void validSearchCondition(Map<String, Object> searchColumns,
			String... condition) {
		for (String key : condition) {
			if (searchColumns.get(key) == null
					|| "".equals(searchColumns.get(key))) {
				new RuntimeException("search column " + key + "is empty");
			}
		}
	}

	public static <T> void wrapperAndCondition(T example,
			DataTablesRequest request) throws Throwable {
		// 注入查询条件
		ExampleProxy<T> exampleProxy = new ExampleProxy<T>(example);
		// 设置分页参数
		exampleProxy.setOffset(request.getStart());
		exampleProxy.setLimit(request.getLength());

		// 创建标准查询条件
		Object criteria = exampleProxy.createCriteria();

		HashMap<String, Method> criteriaMethodMap = new HashMap<String, Method>();
		Method[] criteriaMethodList = criteria.getClass().getMethods();
		for (Method method : criteriaMethodList) {
			criteriaMethodMap.put(method.getName(), method);
		}

		// 封装查询条件
		setCondition(criteria, criteriaMethodMap, request.getSearchColumns());
		// 封装排序条件
		String orderByClause = SearchConditionUtils.getOrderString(
				request.getColumns(), request.getOrder());
		if (!"".equals(orderByClause)) {
			exampleProxy.setOrderByClause(orderByClause);
		}

	}

	public static <T> void wrapperAndCondition(T example, PageVO pagevo,
			Map<String, Object> conditionMap) throws Throwable {
		// 注入查询条件
		ExampleProxy<T> exampleProxy = new ExampleProxy<T>(example);
		// 设置分页参数
		exampleProxy.setOffset(pagevo.getOffset());
		exampleProxy.setLimit(pagevo.getPageSize());

		// 创建标准查询条件
		Object criteria = exampleProxy.createCriteria();

		HashMap<String, Method> criteriaMethodMap = new HashMap<String, Method>();
		Method[] criteriaMethodList = criteria.getClass().getMethods();
		for (Method method : criteriaMethodList) {
			criteriaMethodMap.put(method.getName(), method);
		}

		// 封装查询条件
		setCondition(criteria, criteriaMethodMap, conditionMap);

	}

	private static void setCondition(Object criteria,
			HashMap<String, Method> criteriaMethodMap,
			Map<String, Object> conditionMap) throws Throwable {
		for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
			Method method = criteriaMethodMap.get("and" + entry.getKey());// 加上and
			if (method != null) {
				Object value = entry.getValue();
				if (value != null && !"".equals(value)) {
					if (entry.getKey().contains("Like")) {
						method.invoke(criteria, entry.getValue() + "%");
					} else {
						method.invoke(criteria, entry.getValue());
					}
				}
			}
		}
	}
}