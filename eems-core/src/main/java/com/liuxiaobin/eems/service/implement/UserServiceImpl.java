/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.service.implement;

import com.liuxiaobin.eems.service.IUserService;
import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.persistent.IUserPersistent;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.liuxiaobin.eems.entity.User;
import java.util.Collection;
import com.liuxiaobin.eems.search.UserSearch;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.utilities.StringUtilities;

/**
 * 该类是以下对象操作的业务具休实现。
 * 用户
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Service("com.liuxiaobin.eems.UserService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class UserServiceImpl implements IUserService {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(UserServiceImpl.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.UserPersistent")
  private IUserPersistent userPersistent;

  @org.springframework.beans.factory.annotation.Autowired
  protected org.springframework.context.ApplicationEventPublisher publisher;
  // UserPersistentImpl
  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveUser(User user) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.saveUser ");
      log.debug("parameter user is : " + user);
    }
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      user.clearPrimaryKeyValue();
      user.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
      if (PropertyUtilities.asNullValue(user.getUserId())) {
        user.setUserId(StringUtilities.getUUID());
      }
      if (!userPersistent.isUnique(user)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { userPersistent.getNotUniqueErrorMessage(user)});
      }
      userPersistent.saveUser(user);
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
  public void batchSaveUser(Collection<User> users) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.batchSaveUser ");
      log.debug("parameter users is : " + users);
    }
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      for(User user : users) {
        if(PropertyUtilities.isEmpty(user) || user.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
        }
        user.clearPrimaryKeyValue();
        user.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
        if (PropertyUtilities.asNullValue(user.getUserId())) {
          user.setUserId(StringUtilities.getUUID());
        }
        if (!userPersistent.isUnique(user)) {
          throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { userPersistent.getNotUniqueErrorMessage(user)});
        }
      }
      userPersistent.batchSaveUser(users);
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
  public void updateUser(User user) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.updateUser ");
      log.debug("parameter user is : " + user);
    }
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      if (PropertyUtilities.asNullValue(user.getUserId())) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
      }
      User userOld = userPersistent.getUserByPrimaryKey(user.getUserId());
      if (userOld == null) {
        throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      if (!userPersistent.isUnique(user)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { userPersistent.getNotUniqueErrorMessage(user)});
      }
      userPersistent.updateUser(user);
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
  public void batchUpdateUser(Collection<User> users) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.batchUpdateUser ");
      log.debug("parameter users is : " + users);
    }
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      userPersistent.isUnique(users);
      for(User user : users) {
        if(PropertyUtilities.isEmpty(user) || user.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
        }
        if (PropertyUtilities.asNullValue(user.getUserId())) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
        }
        User userOld = userPersistent.getUserByPrimaryKey(user.getUserId());
        if (userOld == null) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        if (!userPersistent.isUnique(user)) {
          throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { userPersistent.getNotUniqueErrorMessage(user)});
        }
      }
      userPersistent.batchUpdateUser(users);
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
  public void removeUser(User user) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.removeUser ");
      log.debug("parameter user is : " + user);
    }
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      java.util.Set<User> deleteUsers = new java.util.LinkedHashSet<>();
      if (!PropertyUtilities.asNullValue(user.getUserId())) {
        User userOld = userPersistent.getUserByPrimaryKey(user.getUserId());
        if (userOld == null ) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        deleteUsers.add(userOld);
      } else {
        UserSearch userSearch = new UserSearch();
        userSearch.setEntity(user);
        deleteUsers.addAll(userPersistent.searchUser(userSearch));
      }
      if (PropertyUtilities.isNotEmpty(deleteUsers)) {
        userPersistent.batchRemoveUser(deleteUsers);
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
  public void batchRemoveUser(Collection<User> users) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.batchRemoveUser ");
      log.debug("parameter users is : " + users);
    }
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      java.util.Set<User> deleteUsers = new java.util.LinkedHashSet<>();
      for(User user : users) {
        if (!PropertyUtilities.asNullValue(user.getUserId())) {
          User userOld = userPersistent.getUserByPrimaryKey(user.getUserId());
          if (userOld == null ) {
            throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          deleteUsers.add(userOld);
        } else {
          UserSearch userSearch = new UserSearch();
          userSearch.setEntity(user);
          deleteUsers.addAll(userPersistent.searchUser(userSearch));
        }
      }
      if (PropertyUtilities.isNotEmpty(deleteUsers)) {
        userPersistent.batchRemoveUser(deleteUsers);
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
  public User getUserByPrimaryKey(java.lang.String userId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.getUserByPrimaryKey ");
      log.debug("parameter userId is : " + userId);
    }
    try {
      if (PropertyUtilities.asNullValue(userId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
      }
      return userPersistent.getUserByPrimaryKey(userId);
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
  public Long getCountUser(UserSearch userSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.getCountUser ");
      log.debug("parameter userSearch is : " + userSearch);
    }
    try {
      return userPersistent.getCountUser(userSearch);
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
  public Collection<User> getAllUser() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.getAllUser ");
    }
    try {
      return userPersistent.getAllUser();
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
  public PageRange<User> paginationGetAllUser(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.paginationGetAllUser ");
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return userPersistent.paginationGetAllUser(page);
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
  public Collection<User> searchUser(UserSearch userSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.searchUser ");
      log.debug("parameter userSearch is : " + userSearch);
    }
    try {
      return userPersistent.searchUser(userSearch);
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
  public PageRange<User> paginationSearchUser(UserSearch userSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserService.paginationSearchUser ");
      log.debug("parameter userSearch is : " + userSearch);
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return userPersistent.paginationSearchUser(userSearch, page);
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