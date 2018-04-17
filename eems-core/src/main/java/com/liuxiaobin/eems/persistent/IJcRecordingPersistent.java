/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.persistent;

import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.liuxiaobin.eems.entity.JcRecording;
import com.liuxiaobin.eems.commons.exception.EemsException;
import java.util.Collection;
import com.liuxiaobin.eems.search.JcRecordingSearch;

/**
 * 该接口是完成对数据库表EEMS_JC_RECORDING的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IJcRecordingPersistent {
  public static final String THIS_OPERATE_TARGET = "com.liuxiaobin.eems.JcRecording";

  /**
   * 将对象保存到数据库 EEMS_JC_RECORDING 表中。
   *
   * @param jcRecording
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveJcRecording(JcRecording jcRecording) throws EemsException;

  /**
   * 将对象集合保存到数据库 EEMS_JC_RECORDING 表中。
   *
   * @param jcRecordings
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException;

  /**
   * 修改数据库 EEMS_JC_RECORDING 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param jcRecording
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateJcRecording(JcRecording jcRecording) throws EemsException;

  /**
   * 批量修改数据库 EEMS_JC_RECORDING 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param jcRecordings
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateJcRecording(Collection<JcRecording> jcRecordings) throws EemsException;

  /**
   * 根据主键删除数据库 EEMS_JC_RECORDING 表的记录。
   * 
   * @param jcRecording
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeJcRecording(JcRecording jcRecording) throws EemsException;

  /**
   * 根据主键批量删除数据库 EEMS_JC_RECORDING 表的记录。
   *
   * @param jcRecordings
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException;

  /**
   * 根据主键查询数据库 EEMS_JC_RECORDING 表中的记录，如果未找到将返回NULL。
   * 
   * @param recordingId
   *            要查询的主键。
   * @return 根据主键查询到的对象，查询不到返回 null
   * @throws EemsException 运行出错会抛出该异常
   */
  public JcRecording getJcRecordingByPrimaryKey(java.lang.String recordingId) throws EemsException;

  /**
   * 根据条件进行查询数据库 EEMS_JC_RECORDING 表的记录条数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param jcRecordingSearch
   *            查询条件
   * @return 返回查询到的记录个数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException;

  /**
   * 查询数据库 EEMS_JC_RECORDING 表的所有记录。
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<JcRecording> getAllJcRecording() throws EemsException;

  /**
   * 分页查询数据库 EEMS_JC_RECORDING 表的所有记录。。
   *
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<JcRecording> paginationGetAllJcRecording(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询数据库 EEMS_JC_RECORDING 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param jcRecordingSearch
   *            查询条件
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<JcRecording> searchJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询数据库 EEMS_JC_RECORDING 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param jcRecordingSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<JcRecording> paginationSearchJcRecording(JcRecordingSearch jcRecordingSearch, PageSerachParameters page) throws EemsException;
}