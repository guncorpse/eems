/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.service.implement;

import com.liuxiaobin.eems.service.IFcsUserService;
import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.persistent.IFcsUserPersistent;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.liuxiaobin.eems.entity.FcsUser;
import java.util.Collection;
import com.liuxiaobin.eems.search.FcsUserSearch;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.utilities.StringUtilities;

/**
 * 该类是以下对象操作的业务具休实现。
 * 用户
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Service("com.liuxiaobin.eems.FcsUserService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class FcsUserServiceImpl implements IFcsUserService {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(FcsUserServiceImpl.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.FcsUserPersistent")
  private IFcsUserPersistent fcsUserPersistent;

  @org.springframework.beans.factory.annotation.Autowired
  protected org.springframework.context.ApplicationEventPublisher publisher;
  // FcsUserPersistentImpl
  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveFcsUser(FcsUser fcsUser) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.saveFcsUser ");
      log.debug("parameter fcsUser is : " + fcsUser);
    }
    try {
      if (fcsUser == null || fcsUser.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
      }
      fcsUser.clearPrimaryKeyValue();
      fcsUser.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
      if (PropertyUtilities.asNullValue(fcsUser.getUserId())) {
        fcsUser.setUserId(StringUtilities.getUUID());
      }
      if (!fcsUserPersistent.isUnique(fcsUser)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { fcsUserPersistent.getNotUniqueErrorMessage(fcsUser)});
      }
      fcsUserPersistent.saveFcsUser(fcsUser);
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
  public void batchSaveFcsUser(Collection<FcsUser> fcsUsers) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.batchSaveFcsUser ");
      log.debug("parameter fcsUsers is : " + fcsUsers);
    }
    try {
      if (PropertyUtilities.isEmpty(fcsUsers)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUsers " );
      }
      for(FcsUser fcsUser : fcsUsers) {
        if(PropertyUtilities.isEmpty(fcsUser) || fcsUser.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
        }
        fcsUser.clearPrimaryKeyValue();
        fcsUser.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
        if (PropertyUtilities.asNullValue(fcsUser.getUserId())) {
          fcsUser.setUserId(StringUtilities.getUUID());
        }
        if (!fcsUserPersistent.isUnique(fcsUser)) {
          throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { fcsUserPersistent.getNotUniqueErrorMessage(fcsUser)});
        }
      }
      fcsUserPersistent.batchSaveFcsUser(fcsUsers);
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
  public void updateFcsUser(FcsUser fcsUser) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.updateFcsUser ");
      log.debug("parameter fcsUser is : " + fcsUser);
    }
    try {
      if (fcsUser == null || fcsUser.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
      }
      if (PropertyUtilities.asNullValue(fcsUser.getUserId())) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
      }
      FcsUser fcsUserOld = fcsUserPersistent.getFcsUserByPrimaryKey(fcsUser.getUserId());
      if (fcsUserOld == null) {
        throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      if (!fcsUserPersistent.isUnique(fcsUser)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { fcsUserPersistent.getNotUniqueErrorMessage(fcsUser)});
      }
      fcsUserPersistent.updateFcsUser(fcsUser);
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
  public void batchUpdateFcsUser(Collection<FcsUser> fcsUsers) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.batchUpdateFcsUser ");
      log.debug("parameter fcsUsers is : " + fcsUsers);
    }
    try {
      if (PropertyUtilities.isEmpty(fcsUsers)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUsers " );
      }
      fcsUserPersistent.isUnique(fcsUsers);
      for(FcsUser fcsUser : fcsUsers) {
        if(PropertyUtilities.isEmpty(fcsUser) || fcsUser.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
        }
        if (PropertyUtilities.asNullValue(fcsUser.getUserId())) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
        }
        FcsUser fcsUserOld = fcsUserPersistent.getFcsUserByPrimaryKey(fcsUser.getUserId());
        if (fcsUserOld == null) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        if (!fcsUserPersistent.isUnique(fcsUser)) {
          throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { fcsUserPersistent.getNotUniqueErrorMessage(fcsUser)});
        }
      }
      fcsUserPersistent.batchUpdateFcsUser(fcsUsers);
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
  public void removeFcsUser(FcsUser fcsUser) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.removeFcsUser ");
      log.debug("parameter fcsUser is : " + fcsUser);
    }
    try {
      if (fcsUser == null || fcsUser.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
      }
      java.util.Set<FcsUser> deleteFcsUsers = new java.util.LinkedHashSet<>();
      if (!PropertyUtilities.asNullValue(fcsUser.getUserId())) {
        FcsUser fcsUserOld = fcsUserPersistent.getFcsUserByPrimaryKey(fcsUser.getUserId());
        if (fcsUserOld == null ) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        deleteFcsUsers.add(fcsUserOld);
      } else {
        FcsUserSearch fcsUserSearch = new FcsUserSearch();
        fcsUserSearch.setEntity(fcsUser);
        deleteFcsUsers.addAll(fcsUserPersistent.searchFcsUser(fcsUserSearch));
      }
      if (PropertyUtilities.isNotEmpty(deleteFcsUsers)) {
        fcsUserPersistent.batchRemoveFcsUser(deleteFcsUsers);
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
  public void batchRemoveFcsUser(Collection<FcsUser> fcsUsers) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.batchRemoveFcsUser ");
      log.debug("parameter fcsUsers is : " + fcsUsers);
    }
    try {
      if (PropertyUtilities.isEmpty(fcsUsers)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUsers " );
      }
      java.util.Set<FcsUser> deleteFcsUsers = new java.util.LinkedHashSet<>();
      for(FcsUser fcsUser : fcsUsers) {
        if (!PropertyUtilities.asNullValue(fcsUser.getUserId())) {
          FcsUser fcsUserOld = fcsUserPersistent.getFcsUserByPrimaryKey(fcsUser.getUserId());
          if (fcsUserOld == null ) {
            throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          deleteFcsUsers.add(fcsUserOld);
        } else {
          FcsUserSearch fcsUserSearch = new FcsUserSearch();
          fcsUserSearch.setEntity(fcsUser);
          deleteFcsUsers.addAll(fcsUserPersistent.searchFcsUser(fcsUserSearch));
        }
      }
      if (PropertyUtilities.isNotEmpty(deleteFcsUsers)) {
        fcsUserPersistent.batchRemoveFcsUser(deleteFcsUsers);
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
  public FcsUser getFcsUserByPrimaryKey(java.lang.String userId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.getFcsUserByPrimaryKey ");
      log.debug("parameter userId is : " + userId);
    }
    try {
      if (PropertyUtilities.asNullValue(userId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
      }
      return fcsUserPersistent.getFcsUserByPrimaryKey(userId);
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
  public Long getCountFcsUser(FcsUserSearch fcsUserSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.getCountFcsUser ");
      log.debug("parameter fcsUserSearch is : " + fcsUserSearch);
    }
    try {
      return fcsUserPersistent.getCountFcsUser(fcsUserSearch);
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
  public Collection<FcsUser> getAllFcsUser() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.getAllFcsUser ");
    }
    try {
      return fcsUserPersistent.getAllFcsUser();
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
  public PageRange<FcsUser> paginationGetAllFcsUser(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.paginationGetAllFcsUser ");
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return fcsUserPersistent.paginationGetAllFcsUser(page);
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
  public Collection<FcsUser> searchFcsUser(FcsUserSearch fcsUserSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.searchFcsUser ");
      log.debug("parameter fcsUserSearch is : " + fcsUserSearch);
    }
    try {
      return fcsUserPersistent.searchFcsUser(fcsUserSearch);
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
  public PageRange<FcsUser> paginationSearchFcsUser(FcsUserSearch fcsUserSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserService.paginationSearchFcsUser ");
      log.debug("parameter fcsUserSearch is : " + fcsUserSearch);
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return fcsUserPersistent.paginationSearchFcsUser(fcsUserSearch, page);
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