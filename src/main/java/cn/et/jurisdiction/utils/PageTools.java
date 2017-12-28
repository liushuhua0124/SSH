package cn.et.jurisdiction.utils;

import java.util.List;

/**
 * 分页参数
 * 
 * @author Administrator 2017年10月11日 下午3:35:55 easytop samlinzhang
 *         PageTools.java
 */
public class PageTools {
	/**
	 * 构造参数
	 * 
	 * @param curPage
	 *            页面传入的当前页
	 * @param totalCount
	 *            数据库查询的总记录数
	 * @param pageCount
	 *            每页显示的条数
	 */
	public PageTools(Integer curPage, Integer totalCount, Integer pageCount) {
		this.curPage = curPage;
		this.total = totalCount;
		this.pageCount = pageCount == null ? this.pageCount : pageCount;
		this.prePage = (curPage == 1 ? 1 : curPage - 1);
		this.totalPage = totalCount % this.pageCount == 0 ? totalCount / this.pageCount
				: totalCount / this.pageCount + 1;
		this.nextPage = (curPage == totalPage) ? totalPage : (curPage + 1);
		this.startIndex = (curPage - 1) * this.pageCount + 1;
		this.endIndex = curPage * this.pageCount;
	}

	/**
	 * 当前页（动态 由页面传递的）
	 */
	private Integer curPage;
	/**
	 * 每页显示条数
	 */
	private Integer pageCount = 10;
	/**
	 * 上一页 prePage=(curPage==1?1:curPage-1) 举例 2 -- 1 3 -- 2 1 -- 1
	 * 
	 */
	private Integer prePage;
	/**
	 * 下一页 举例：
	 * 
	 * nextPage=(curPage=totalPage)?totalPage:(curPage+1) curpage totalPage
	 * nextPage 1 3 2 2 3 3 3 3 3
	 */
	private Integer nextPage;
	/**
	 * 总页数 pageCount(每页显示的条数) total(总记录数) totalPage 10 20 2 10 21 3
	 * 
	 * totalPage=total%pageCount==0?total/pageCount:total/pageCount+1
	 * 
	 */
	private Integer totalPage;
	/**
	 * 总记录数（从数据库查询）
	 */
	private Integer total;
	/**
	 * 开始索引 curPage pageCount start-end 1 10 1-10 2 10 11-20
	 * (curPage-1)*pageCount+1 curPage*pageCount
	 */
	private Integer startIndex;
	/**
	 * 结束索引
	 */
	private Integer endIndex;

	/**
	 * 存储最终查询的数据
	 */
	private List rows;

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
