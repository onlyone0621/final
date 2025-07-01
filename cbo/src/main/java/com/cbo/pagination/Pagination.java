package com.cbo.pagination;

public class Pagination {
	static public String makePaging(String pageName, int maxRows, int rows, int pages, int curPage) {
		int maxPage = maxRows / rows + 1;
		if (maxRows % rows == 0) {
			maxPage--;
			
		}
		
		int userGroup = curPage / pages;
		
		if (curPage % pages == 0) {
			userGroup--;
		}
		
		StringBuffer sb = new StringBuffer();
		
		if (userGroup != 0) {
			sb.append("<a href='");
			sb.append(pageName);
			sb.append("?curPage=");
			
			int temp = (userGroup - 1) * pages + pages;
			
			sb.append(temp);
			sb.append("'>&lt;&lt;</a>");
		}
		
		int begin = userGroup * pages + 1;
		int end = userGroup * pages + pages;
		if (end > maxPage) {
			end = maxPage;
		}
		
		for (int i = begin; i <= end; i++) {
			sb.append("&nbsp;&nbsp;<a href='");
			sb.append(pageName);
			sb.append("?curPage=");
			sb.append(i);
			sb.append("'>");
			sb.append(i);
			sb.append("</a>");
		}
		// if (maxPage / pages - (maxPage % pages == 0 ? 1 : 0) != userGroup)
		
		if ((userGroup + 1) * pages + 1 <= maxPage) {
			sb.append("&nbsp;&nbsp;<a href='");
			sb.append(pageName);
			sb.append("?curPage=");
			
			int next = (userGroup + 1) * pages + 1;
			sb.append(next);
			sb.append("'>&gt;&gt;</a>");
		}
		
		return sb.toString();
	}
}
