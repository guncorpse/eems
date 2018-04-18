/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.persistent;

import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.commons.exception.EemsException;
import java.util.Collection;
import com.liuxiaobin.eems.search.FcsUserSearch;

/**
 * 该接口是完成对数据库表FCS_USER的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IUserPersistent {
  public static final String THIS_OPERATE_TARGET = "com.liuxiaobin.eems.FcsUser";

  /**
   * 将对象保存到数据库 FCS_USER 表中。
   *
   * @param fcsUser
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveFcsUser(User fcsUser) throws EemsException;

  /**
   * 将对象集合保存到数据库 FCS_USER 表中。
   *
   * @param fcsUsers
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveFcsUser(Collection<User> fcsUsers) throws EemsException;

  /**
   * 修改数据库 FCS_USER 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param fcsUser
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateFcsUser(User fcsUser) throws EemsException;

  /**
   * 批量修改数据库 FCS_USER 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param fcsUsers
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateFcsUser(Collection<User> fcsUsers) throws EemsException;

  /**
   * 根据主键删除数据库 FCS_USER 表的记录。
   * 
   * @param fcsUser
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeFcsUser(User fcsUser) throws EemsException;

  /**
   * 根据主键批量删除数据库 FCS_USER 表的记录。
   *
   * @param fcsUsers
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveFcsUser(Collection<User> fcsUsers) throws EemsException;

  /**
   * 根据主键查询数据库 FCS_USER 表中的记录，如果未找到将返回NULL。
   * 
   * @param userId
   *            要查询的主键。
   * @return 根据主键查询到的对象，查询不到返回 null
   * @throws EemsException 运行出错会抛出该异常
   */
  public User getFcsUserByPrimaryKey(java.lang.String userId) throws EemsException;

  /**
   * 根据条件进行查询数据库 FCS_USER 表的记录条数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param fcsUserSearch
   *            查询条件
   * @return 返回查询到的记录个数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountFcsUser(FcsUserSearch fcsUserSearch) throws EemsException;

  /**
   * 查询数据库 FCS_USER 表的所有记录。
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<User> getAllFcsUser() throws EemsException;

  /**
   * 分页查询数据库 FCS_USER 表的所有记录。。
   *
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<User> paginationGetAllFcsUser(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询数据库 FCS_USER 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param fcsUserSearch
   *            查询条件
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<User> searchFcsUser(FcsUserSearch fcsUserSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询数据库 FCS_USER 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param fcsUserSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<User> paginationSearchFcsUser(FcsUserSearch fcsUserSearch, PageSerachParameters page) throws EemsException;

  /**
   * 判断用户对象集合当中是否有重复值。
   *
   * @param fcsUserCollection
   *            要判断的用户对象集合。
   * @throws EemsException 如果有重复值将抛出该异常
   */
  public void isUnique(Collection<User> fcsUserCollection) throws EemsException;

  /**
   * 判断用户对象是否唯一。
   *
   * @param fcsUser
   *            要判断的用户对象。
   * @return true 代表当前对象是唯一的，false 代表当前对象不唯一
   */
  public boolean isUnique(User fcsUser);

  /**
   * 获取用户对象不唯一的异常消息。
   *
   * @param fcsUser
   *            不唯一的用户对象。
   * @return true 对象不唯一提示消息
   */
  public String getNotUniqueErrorMessage(User fcsUser);
}