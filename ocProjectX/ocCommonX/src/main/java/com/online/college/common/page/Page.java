package com.online.college.common.page;

import java.util.List;

/**
 *
* @Description: 分页接口
* @author cmazxiaoma
* @date 2018-02-07 14:45
* @version V1.0
 */
public interface Page<E> extends Iterable<E> {

	/**
	 * 起始页号
	 * @return
	 */
	int getFirstPageNum();

	/**
	 * 终止页号
	 * @return
	 */
	int getLastPageNum();

    /**
     * 当前页号
     * @return
     */
    int getPageNum();

    /**
     * 分页大小
     * @return
     */
    int getPageSize();

    /**
     * 分页数据
     * @return
     */
    List<E> getItems();

    /**
	 * 获取总记录数
	 * @return
	 */
	int getItemsTotalCount();

	/**
	 * 获取总页数
	 */
	int getPageTotalCount();

    /**
	 * 是第一页
	 * @return
	 */
    boolean isFirstPage();

	/**
	 * 是最后一页
	 * @return
	 */
	boolean isLastPage();

    /**
     * 是否为空内容
     * @return
     */
    boolean isEmpty();

}


