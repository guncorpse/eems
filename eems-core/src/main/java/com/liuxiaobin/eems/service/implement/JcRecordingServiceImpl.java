/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.service.implement;

import com.liuxiaobin.eems.service.IJcRecordingService;
import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.persistent.IJcRecordingPersistent;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.liuxiaobin.eems.entity.JcRecording;
import java.util.Collection;
import com.liuxiaobin.eems.search.JcRecordingSearch;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.utilities.StringUtilities;

/**
 * 该类是以下对象操作的业务具休实现。
 * 奖惩记录
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Service("com.liuxiaobin.eems.JcRecordingService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class JcRecordingServiceImpl implements IJcRecordingService {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(JcRecordingServiceImpl.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.JcRecordingPersistent")
  private IJcRecordingPersistent jcRecordingPersistent;

  @org.springframework.beans.factory.annotation.Autowired
  protected org.springframework.context.ApplicationEventPublisher publisher;
  // JcRecordingPersistentImpl
  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveJcRecording(JcRecording jcRecording) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.saveJcRecording ");
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      jcRecording.clearPrimaryKeyValue();
      jcRecording.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
      if (PropertyUtilities.asNullValue(jcRecording.getRecordingId())) {
        jcRecording.setRecordingId(StringUtilities.getUUID());
      }
      jcRecordingPersistent.saveJcRecording(jcRecording);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSaveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.batchSaveJcRecording ");
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      for(JcRecording jcRecording : jcRecordings) {
        if(PropertyUtilities.isEmpty(jcRecording) || jcRecording.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
        }
        jcRecording.clearPrimaryKeyValue();
        jcRecording.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
        if (PropertyUtilities.asNullValue(jcRecording.getRecordingId())) {
          jcRecording.setRecordingId(StringUtilities.getUUID());
        }
      }
      jcRecordingPersistent.batchSaveJcRecording(jcRecordings);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updateJcRecording(JcRecording jcRecording) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.updateJcRecording ");
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      if (PropertyUtilities.asNullValue(jcRecording.getRecordingId())) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " recordingId " );
      }
      JcRecording jcRecordingOld = jcRecordingPersistent.getJcRecordingByPrimaryKey(jcRecording.getRecordingId());
      if (jcRecordingOld == null) {
        throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      jcRecordingPersistent.updateJcRecording(jcRecording);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdateJcRecording(Collection<JcRecording> jcRecordings) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.batchUpdateJcRecording ");
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      for(JcRecording jcRecording : jcRecordings) {
        if(PropertyUtilities.isEmpty(jcRecording) || jcRecording.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
        }
        if (PropertyUtilities.asNullValue(jcRecording.getRecordingId())) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " recordingId " );
        }
        JcRecording jcRecordingOld = jcRecordingPersistent.getJcRecordingByPrimaryKey(jcRecording.getRecordingId());
        if (jcRecordingOld == null) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
      }
      jcRecordingPersistent.batchUpdateJcRecording(jcRecordings);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removeJcRecording(JcRecording jcRecording) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.removeJcRecording ");
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      java.util.Set<JcRecording> deleteJcRecordings = new java.util.LinkedHashSet<>();
      if (!PropertyUtilities.asNullValue(jcRecording.getRecordingId())) {
        JcRecording jcRecordingOld = jcRecordingPersistent.getJcRecordingByPrimaryKey(jcRecording.getRecordingId());
        if (jcRecordingOld == null ) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        deleteJcRecordings.add(jcRecordingOld);
      } else {
        JcRecordingSearch jcRecordingSearch = new JcRecordingSearch();
        jcRecordingSearch.setEntity(jcRecording);
        deleteJcRecordings.addAll(jcRecordingPersistent.searchJcRecording(jcRecordingSearch));
      }
      if (PropertyUtilities.isNotEmpty(deleteJcRecordings)) {
        jcRecordingPersistent.batchRemoveJcRecording(deleteJcRecordings);
      }
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemoveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.batchRemoveJcRecording ");
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      java.util.Set<JcRecording> deleteJcRecordings = new java.util.LinkedHashSet<>();
      for(JcRecording jcRecording : jcRecordings) {
        if (!PropertyUtilities.asNullValue(jcRecording.getRecordingId())) {
          JcRecording jcRecordingOld = jcRecordingPersistent.getJcRecordingByPrimaryKey(jcRecording.getRecordingId());
          if (jcRecordingOld == null ) {
            throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          deleteJcRecordings.add(jcRecordingOld);
        } else {
          JcRecordingSearch jcRecordingSearch = new JcRecordingSearch();
          jcRecordingSearch.setEntity(jcRecording);
          deleteJcRecordings.addAll(jcRecordingPersistent.searchJcRecording(jcRecordingSearch));
        }
      }
      if (PropertyUtilities.isNotEmpty(deleteJcRecordings)) {
        jcRecordingPersistent.batchRemoveJcRecording(deleteJcRecordings);
      }
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JcRecording getJcRecordingByPrimaryKey(java.lang.String recordingId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.getJcRecordingByPrimaryKey ");
      log.debug("parameter recordingId is : " + recordingId);
    }
    try {
      if (PropertyUtilities.asNullValue(recordingId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " recordingId " );
      }
      return jcRecordingPersistent.getJcRecordingByPrimaryKey(recordingId);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long getCountJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.getCountJcRecording ");
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
    }
    try {
      return jcRecordingPersistent.getCountJcRecording(jcRecordingSearch);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<JcRecording> getAllJcRecording() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.getAllJcRecording ");
    }
    try {
      return jcRecordingPersistent.getAllJcRecording();
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PageRange<JcRecording> paginationGetAllJcRecording(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.paginationGetAllJcRecording ");
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return jcRecordingPersistent.paginationGetAllJcRecording(page);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<JcRecording> searchJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.searchJcRecording ");
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
    }
    try {
      return jcRecordingPersistent.searchJcRecording(jcRecordingSearch);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PageRange<JcRecording> paginationSearchJcRecording(JcRecordingSearch jcRecordingSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingService.paginationSearchJcRecording ");
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return jcRecordingPersistent.paginationSearchJcRecording(jcRecordingSearch, page);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

}