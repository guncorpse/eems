/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.service;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.liuxiaobin.eems.entity.JcRecording;
import java.util.Collection;
import com.liuxiaobin.eems.search.JcRecordingSearch;

/**
 * 该接口是对以下对象操作的接口。
 * <b>奖惩记录</b>
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IJcRecordingService {

  // JcRecordingPersistentImpl
  /**
   * 保存单个奖惩记录对象。
   *
   * @param jcRecording
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveJcRecording(JcRecording jcRecording) throws EemsException;

  /**
   * 批量保存奖惩记录对象。
   *
   * @param jcRecordings
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException;

  /**
   * 修改单个奖惩记录对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param jcRecording
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateJcRecording(JcRecording jcRecording) throws EemsException;

  /**
   * 批量修改奖惩记录对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param jcRecordings
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateJcRecording(Collection<JcRecording> jcRecordings) throws EemsException;

  /**
   * 删除奖惩记录对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   * 
   * @param jcRecording
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeJcRecording(JcRecording jcRecording) throws EemsException;

  /**
   * 批量删除奖惩记录对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   *
   * @param jcRecordings
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException;

  /**
   * 根据主键查询奖惩记录对象，如果未找到将返回NULL。
   * 
   * @param recordingId
   *            要查询的主键。
   * @return 查询到的对象如果没有查到返回null
   * @throws EemsException 运行出错会抛出该异常
   */
  public JcRecording getJcRecordingByPrimaryKey(java.lang.String recordingId) throws EemsException;

  /**
   * 根据条件进行查询奖惩记录对象个数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param jcRecordingSearch
   *            查询条件
   * @return 查询到的数据条数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException;

  /**
   * 查询所有奖惩记录对象。
   *
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<JcRecording> getAllJcRecording() throws EemsException;

  /**
   * 分页查询奖惩记录对象。
   *
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<JcRecording> paginationGetAllJcRecording(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询奖惩记录对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param jcRecordingSearch
   *            查询条件
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<JcRecording> searchJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询奖惩记录对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param jcRecordingSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<JcRecording> paginationSearchJcRecording(JcRecordingSearch jcRecordingSearch, PageSerachParameters page) throws EemsException;

}