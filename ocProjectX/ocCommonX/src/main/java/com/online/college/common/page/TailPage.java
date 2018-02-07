package com.online.college.common.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
* @Description: 分页
* @author cmazxiaoma
* @date 2018-02-07 14:46
* @version V1.0
 */
public class TailPage<E> extends AbstractPage<E> {

    protected int showPage = 10;//显示10个页码
	protected List<Integer> showNums = new ArrayList<Integer>();
	protected boolean showDot = true;
	public TailPage() {}

    /**
     * 构造函数，将一个已有的分页对象中的分页参数，设置给自己，items需独立设置
     * @param page
     * @param items
     */
    public TailPage(Page<E> page, Collection<E> items ,int itemsTotalCount) {
        this(page.getPageNum(), page.getPageSize(), itemsTotalCount , items);
    }

    public TailPage(int pageNum, int pageSize , int itemsTotalCount , Collection<E> items) {
    	this.setItemsTotalCount(itemsTotalCount);
        this.setPageNum(pageNum);
        this.setPageSize(pageSize);
        this.setItems(items);
        this.initShowNum();
    }

	public int getShowPage() {
		return showPage;
	}

	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}

	@Override
	public void setItemsTotalCount(int itemsTotalCount) {
		super.setItemsTotalCount(itemsTotalCount);
		initShowNum();
	}

	private void initShowNum(){
		int startIndex;
		int endIndex;
		if(pageNum - showPage/2 > 1){
			startIndex = pageNum-showPage/2;
			endIndex = pageNum + showPage/2 - 1;
			if(endIndex > pageTotalCount){
				endIndex = pageTotalCount;
				startIndex = endIndex - showPage + 1;
			}
		}else{
			startIndex = 1;
			endIndex = pageTotalCount<=showPage?pageTotalCount:showPage;
		}
		for(int i = startIndex; i <= endIndex ; i++){
			this.showNums.add(Integer.valueOf(i));
		}
		if(this.firstPage||this.lastPage){
			showDot = false;
		}else{
			if(showNums.size() > 0){
				if(showNums.get(showNums.size()-1) == this.pageTotalCount){
					showDot = false;
				}
			}
		}
	}

	public List<Integer> getShowNums() {
		return showNums;
	}

	public boolean getShowDot() {
		return showDot;
	}

	@Override
    public int getPageTotalCount(){
		return this.pageTotalCount;
	}
}

