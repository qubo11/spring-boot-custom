package com.expect.custom.service.vo.component;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页model
 * 
 * @author 曲波
 */
public class PageVo<T> {

	private boolean result;
	private String message;
	private int pageSize = 20;
	private int currentPage = 1;// 当前页
	private long nextPage;// 下一页
	private long prePage;// 上一页
	private long totalCount = 0;// 总数
	private long pageCount = 0;// 总页数
	private boolean isHasNext = true;// 是否有下一页
	private int beginIndex;// 起始点
	private int endIndex;// 结束点
	private List<T> data;
	private int showIndexCount = 10;// 显示界面上面的索引数
	private List<Integer> showIndex;// 需要显示在界面上面的索引

	public PageVo() {
	}

	public PageVo(String currentPage) {
		int tmp = 1;
		if (currentPage == null || currentPage.trim().equals("")) {

		} else {
			tmp = Integer.valueOf(currentPage);
			if (tmp <= 0) {
				tmp = 1;
			}
		}
		this.currentPage = tmp;
	}

	public PageVo(int currentPage) {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBeginIndex() {
		beginIndex = (currentPage - 1) * pageSize;
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		endIndex = getBeginIndex() + pageSize;
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
		if (pageCount <= currentPage) {
			isHasNext = false;
		} else {
			isHasNext = true;
		}
		if (pageCount == 0) {
			pageCount = 1;
		}
		this.totalCount = totalCount;
		changePage();
		setIndex();
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public long getNextPage() {
		return nextPage;
	}

	public void setNextPage(long nextPage) {
		this.nextPage = nextPage;
	}

	public long getPrePage() {
		return prePage;
	}

	public void setPrePage(long prePage) {
		this.prePage = prePage;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	private void changePage() {
		nextPage = currentPage + 1;
		prePage = currentPage - 1;
		if (nextPage > pageCount) {
			nextPage = pageCount;
		}
		if (prePage < 1) {
			prePage = 1;
		}
	}

	private void setIndex() {
		showIndex = new ArrayList<>();
		// if (currentPage > showIndexCount) {
		// if (currentPage + showIndexCount > pageCount) {
		// for (int i = currentPage; i <= pageCount; i++) {
		// showIndex.add(i+"");
		// }
		// } else {
		// int half = showIndexCount / 2;
		// for (int i = currentPage - half; i <= currentPage + half; i++) {
		// showIndex.add(i+"");
		// }
		// }
		// } else {
		// if (pageCount < showIndexCount) {
		// for (int i = 1; i <= pageCount; i++) {
		// showIndex.add(i+"");
		// }
		// } else {
		// for (int i = 1; i <= showIndexCount; i++) {
		// showIndex.add(i+"");
		// }
		// }
		// }
		if (currentPage + showIndexCount < pageCount) {
			for (int i = currentPage; i <= currentPage + showIndexCount; i++) {
				showIndex.add(i);
			}
		} else {
			int start = (int) (pageCount - showIndexCount);
			if (start < 1) {
				start = 1;
			}
			for (int i = start; i <= pageCount; i++) {
				showIndex.add(i);
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isHasNext() {
		return isHasNext;
	}

	public void setHasNext(boolean isHasNext) {
		this.isHasNext = isHasNext;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getShowIndexCount() {
		return showIndexCount;
	}

	public void setShowIndexCount(int showIndexCount) {
		this.showIndexCount = showIndexCount;
	}

	public List<Integer> getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(List<Integer> showIndex) {
		this.showIndex = showIndex;
	}

	@Override
	public String toString() {
		return "PageModel [pageSize=" + pageSize + ", currentPage=" + currentPage + ", nextPage=" + nextPage
				+ ", prePage=" + prePage + ", totalCount=" + totalCount + ", pageCount=" + pageCount + ", isHasNext="
				+ isHasNext + ", beginIndex=" + beginIndex + ", endIndex=" + endIndex + "]";
	}

}
