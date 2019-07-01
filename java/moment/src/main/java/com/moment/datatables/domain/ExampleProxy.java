package com.moment.datatables.domain;

import java.util.List;

import org.apache.commons.lang3.reflect.MethodUtils;

public class ExampleProxy<T> {
	private T target;

	public ExampleProxy(T target) {
		super();
		this.target = target;
	}
	
	public T getTarget(){
		return target;
	}
	public void setLimit(Integer limit) throws Exception{
		MethodUtils.invokeMethod(target, "setLimit",
				limit);
	}
	
	public void setOffset(Integer offset) throws Exception{
		MethodUtils.invokeMethod(target, "setOffset",
				offset);
	}
	
	public void setOrderByClause(String orderByClause) throws Exception{
		MethodUtils.invokeMethod(target, "setOrderByClause",
				orderByClause);
	}
	
	public String getOrderByClause() throws Exception{
		return (String)MethodUtils.invokeMethod(target, "getOrderByClause",
				null);
	}
	
	 public void setDistinct(boolean distinct) throws Exception{
		 MethodUtils.invokeMethod(target, "setDistinct",
				 distinct);
	 }
	 
	 public boolean isDistinct() throws Exception{
		 return (Boolean)MethodUtils.invokeMethod(target, "isDistinct",
					null);
	 }
	 
	 public Object or() throws Exception{
		 return MethodUtils.invokeMethod(target, "or",
					null);
	 }
	 
	 public void or(Object criteria) throws Exception{
		 MethodUtils.invokeMethod(target, "or",
				 criteria);
	 }
	 
	 public Object createCriteria() throws Exception{
		 return MethodUtils.invokeMethod(target, "createCriteria",
					null);
	 }
	 
	 
	 public List<Object> getOredCriteria() throws Exception{
		 return (List<Object>)MethodUtils.invokeMethod(target, "getOredCriteria",
					null);
	 }
	 
	 public void clear() throws Exception{
		 MethodUtils.invokeMethod(target, "clear",
				 null);
	 }
}
