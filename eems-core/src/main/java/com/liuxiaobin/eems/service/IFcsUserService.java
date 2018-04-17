/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.service;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.liuxiaobin.eems.entity.FcsUser;
import java.util.Collection;
import com.liuxiaobin.eems.search.FcsUserSearch;

/**
 * 该接口是对以下对象操作的接口。
 * <b>用户</b>
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IFcsUserService {

  // FcsUserPersistentImpl
  /**
   * 保存单个用户对象。
   *
   * @param fcsUser
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveFcsUser(FcsUser fcsUser) throws EemsException;

  /**
   * 批量保存用户对象。
   *
   * @param fcsUsers
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveFcsUser(Collection<FcsUser> fcsUsers) throws EemsException;

  /**
   * 修改单个用户对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param fcsUser
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateFcsUser(FcsUser fcsUser) throws EemsException;

  /**
   * 批量修改用户对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param fcsUsers
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateFcsUser(Collection<FcsUser> fcsUsers) throws EemsException;

  /**
   * 删除用户对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   * 
   * @param fcsUser
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeFcsUser(FcsUser fcsUser) throws EemsException;

  /**
   * 批量删除用户对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   *
   * @param fcsUsers
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveFcsUser(Collection<FcsUser> fcsUsers) throws EemsException;

  /**
   * 根据主键查询用户对象，如果未找到将返回NULL。
   * 
   * @param userId
   *            要查询的主键。
   * @return 查询到的对象如果没有查到返回null
   * @throws EemsException 运行出错会抛出该异常
   */
  public FcsUser getFcsUserByPrimaryKey(java.lang.String userId) throws EemsException;

  /**
   * 根据条件进行查询用户对象个数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param fcsUserSearch
   *            查询条件
   * @return 查询到的数据条数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountFcsUser(FcsUserSearch fcsUserSearch) throws EemsException;

  /**
   * 查询所有用户对象。
   *
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<FcsUser> getAllFcsUser() throws EemsException;

  /**
   * 分页查询用户对象。
   *
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<FcsUser> paginationGetAllFcsUser(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询用户对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param fcsUserSearch
   *            查询条件
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<FcsUser> searchFcsUser(FcsUserSearch fcsUserSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询用户对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param fcsUserSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<FcsUser> paginationSearchFcsUser(FcsUserSearch fcsUserSearch, PageSerachParameters page) throws EemsException;

}