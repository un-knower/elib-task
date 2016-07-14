package com.winxuan.service;

/**
 * 
 * @Description: 基础Service接口
 * @author: free
 * @date: 2015-7-7
 * @param <T>
 *            tag.
 * @version: v1.0
 */
public interface BaseService<T> {

	/**
	 * 
	 * @param t
	 *            泛型标识
	 * @Description: 保存操作
	 * @date: 2015-7-7
	 * @author: free
	 */
	T save(T entity);

	/**
	 * 
	 * @param t
	 *            泛型标识
	 * @Description: 修改操作
	 * @date: 2015-7-7
	 * @author: free
	 */
	T update(T t);

	/**
	 * 
	 * @param t
	 *            泛型标识
	 * @Description: 查询ID操作
	 * @date: 2015-7-7
	 * @author: free
	 */
	T findOne(Long id);

	/**
	 * @param
	 * @Description: 查询全部操作
	 * @date: 2015-7-7
	 * @author: free
	 */
	//Iterable<T> findAll();

	/**
	 * @param t
	 *            泛型标识
	 * @Description: 删除操作
	 * @date: 2015-7-7
	 * @author: free
	 */
	void delete(T entity);

}
