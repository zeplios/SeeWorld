package edu.tju.ina.seeworld.vo;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	private List<Integer> pagelist = new ArrayList<Integer>();
	private int len = 0;
	private int pagesize = 0;
	private int currentpage = 1;
	private int pagenum = 0;

	public Pagination() {
	}

	public int getPagenum() {
		return pagenum;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public int getNext() {
		if (currentpage != pagenum) {
			return currentpage + 1;
		} else {
			return 1;
		}
	}

	public int getPre() {
		if (currentpage > 1) {
			return currentpage - 1;
		} else {
			return 1;
		}
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public List<Integer> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<Integer> pagelist) {
		this.pagelist = pagelist;
	}

	public void setPagelist() {
		pagelist.clear();
		pagenum = len / pagesize;
		if (len % pagesize != 0) {
			pagenum++;
		}
		if (pagenum == 0)
			pagenum = 1;
		if (pagenum > pagesize) {
			if (currentpage <= pagesize / 2) {
				for (int i = 1; i <= pagesize; i++) {
					pagelist.add(new Integer(i));
				}
			} else if (currentpage > pagesize / 2
					&& currentpage < pagenum - pagesize / 2 + 1) {
				for (int i = currentpage - pagesize / 2; i <= currentpage
						+ pagesize / 2; i++) {
					pagelist.add(new Integer(i));
				}
			} else {
				for (int i = pagenum - pagesize + 1; i <= pagenum; i++) {
					pagelist.add(new Integer(i));
				}
			}
		} else {
			for (int i = 1; i <= pagenum; i++) {
				pagelist.add(new Integer(i));
			}
		}

	}

	
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getStart() {
		return (currentpage - 1) * pagesize;
	}

}
