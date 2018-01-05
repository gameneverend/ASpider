package com.less.aspider.db;

import java.util.List;

public class Result<T> {

	public int currentPage;

	public int totalCount;

	public List<T> recordList;

	public int pageCount;

	public Result(int currentPage, int totalCount, List<T> recordList, int pageCount) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.recordList = recordList;
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "Result [currentPage=" + currentPage + ", recordList's length = " + recordList.size() + ", totalCount=" + totalCount + ", pageCount=" + pageCount + "]";
	}
}