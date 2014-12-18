package com.example.sqlitecachedemo.entity;

import java.util.List;

public class ListEntity {
	private List<DataEntity> data;

	public List<DataEntity> getData() {
		return data;
	}

	public void setData(List<DataEntity> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ListEntity [data=" + data + "]";
	}
	
}
