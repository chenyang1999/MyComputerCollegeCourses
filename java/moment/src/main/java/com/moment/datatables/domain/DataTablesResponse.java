package com.moment.datatables.domain;
import java.util.List;

public class DataTablesResponse<T> {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private String error;
    private List<T> data;
    
    public DataTablesResponse(){
    	
    }
    public DataTablesResponse(String error){
    	this.error=error;
    }
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
		this.recordsFiltered=recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
