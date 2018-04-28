/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.persistent.implement;

import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.persistent.jdbc.BasePersistent;
import com.nmxpsoft.base.security.SecurityContext;
import com.nmxpsoft.base.commons.vo.Sort;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.persistent.IUserPersistent;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import com.liuxiaobin.eems.commons.exception.EemsException;
import java.util.Collection;
import com.liuxiaobin.eems.search.UserSearch;

/**
 * 该类是完成对数据库表FCS_USER的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Repository("com.liuxiaobin.eems.UserPersistent")
public class UserPersistentImpl extends BasePersistent implements IUserPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(UserPersistentImpl.class);


  public static final String TABLE_NAME = "EEMS_USER";
  public static final String TABLE_ALIAS = "user";

  public static final String COLUMN_USER_ID = "USER_ID";
  public static final String COLUMN_NICKNAME = "NICKNAME";
  public static final String COLUMN_ACCOUNT = "ACCOUNT";
  public static final String COLUMN_PASSWORD = "PASSWORD";
  public static final String COLUMN_CREATE_TIMESTAMPS = "CREATE_TIMESTAMPS";

  public static final LinkedHashSet<String> COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashSet<String> PRIMARY_KEY = new LinkedHashSet<>();
  public static final LinkedHashMap<String, String> COLUMNS_PARAMETER = new LinkedHashMap<>();

  private static final LinkedHashSet<String> NOT_INSERTABLE_COLUMNS = new LinkedHashSet<>();
  private static final LinkedHashSet<String> NOT_UPDATABLE_COLUMNS = new LinkedHashSet<>();

  public static final LinkedHashSet<String> KEY_SEARCH_COLUMNS = new LinkedHashSet<>();
  private static final LinkedHashSet<Sort> DEFAULT_SORT_LIST = new LinkedHashSet<>();

  private static final StringBuilder INSERT_SQL;
  private static final StringBuilder UPDATE_SQL;
  private static final StringBuilder DELETE_SQL_BY_PRIMARY_KEY;
  public static final StringBuilder SELECT_BASE_SQL;
  public static final StringBuilder COUNT_BASE_SQL;
  static {
    COLUMNS.add(COLUMN_USER_ID);
    COLUMNS.add(COLUMN_NICKNAME);
    COLUMNS.add(COLUMN_ACCOUNT);
    COLUMNS.add(COLUMN_PASSWORD);
    COLUMNS.add(COLUMN_CREATE_TIMESTAMPS);


    COLUMNS_PARAMETER.put(COLUMN_USER_ID , "userId");
    COLUMNS_PARAMETER.put(COLUMN_NICKNAME , "nickname");
    COLUMNS_PARAMETER.put(COLUMN_ACCOUNT , "account");
    COLUMNS_PARAMETER.put(COLUMN_PASSWORD , "password");
    COLUMNS_PARAMETER.put(COLUMN_CREATE_TIMESTAMPS , "createTimestamps");


    PRIMARY_KEY.add(COLUMN_USER_ID);

    KEY_SEARCH_COLUMNS.add(COLUMN_USER_ID);
    KEY_SEARCH_COLUMNS.add(COLUMN_NICKNAME);
    KEY_SEARCH_COLUMNS.add(COLUMN_ACCOUNT);
    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL_BY_PRIMARY_KEY = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_BASE_SQL = generateBaseCountSql(TABLE_NAME, PRIMARY_KEY, TABLE_ALIAS);
    DEFAULT_SORT_LIST.add(new Sort(COLUMNS_PARAMETER.get(COLUMN_CREATE_TIMESTAMPS), "DESC"));
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void saveUser(User user) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserPersistent.saveUser ");
      log.debug("parameter user is : " + user);
    }
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(user));
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
  public void batchSaveUser(Collection<User> users) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserPersistent.batchSaveUser ");
      log.debug("parameter users is : " + users);
    }
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(INSERT_SQL.toString(), SqlParameterSourceUtils.createBatch(users.toArray()));
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
  public void updateUser(User user) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserPersistent.updateUser ");
      log.debug("parameter user is : " + user);
    }
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(user));
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
  public void batchUpdateUser(Collection<User> users) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserPersistent.batchUpdateUser ");
      log.debug("parameter users is : " + users);
    }
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(UPDATE_SQL.toString(), SqlParameterSourceUtils.createBatch(users.toArray()));
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
  public void removeUser(User user) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserPersistent.removeUser ");
      log.debug("parameter user is : " + user);
    }
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("userId", user.getUserId());
      this.namedParameterJdbcTemplate.update(DELETE_SQL_BY_PRIMARY_KEY.toString(), paramSource);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
  public void batchRemoveUser(Collection<User> users) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserPersistent.batchRemoveUser ");
      log.debug("parameter users is : " + users);
    }
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(DELETE_SQL_BY_PRIMARY_KEY.toString(), SqlParameterSourceUtils.createBatch(users.toArray()));
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call UserPersistent.getUserByPrimaryKey ");
      log.debug("parameter userId is : " + userId);
    }
    try {
      if (PropertyUtilities.asNullValue(userId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
      }
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_USER_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_USER_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue(COLUMNS_PARAMETER.get(COLUMN_USER_ID), userId);
      Collection<User> userList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(User.class));
      return (userList != null && !userList.isEmpty() && userList.size() > 0) ? userList.iterator().next() : null;
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call UserPersistent.getCountUser ");
      log.debug("parameter userSearch is : " + userSearch);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      if (userSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, userSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())) {
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          countSql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(userSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          return this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
        }
      }
      return this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), new BeanPropertySqlParameterSource(userSearch), Long.class);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call UserPersistent.getAllUser ");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          appendOrderBy(sql);
          return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(User.class));
        }
      }
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(User.class));
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call UserPersistent.paginationGetAllUser ");
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      if (page.getFirstReslut() < 0) {
        throw EemsException.getException(EemsException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page.firstReslut ", " 大于等于0" });
      }
      if (page.getPageSize() < 1) {
        throw EemsException.getException(EemsException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page.maxReslut ", " 大于等于1" });
      }
      PageRange<User> pageRange = new PageRange<>(page);
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())) {
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          countSql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
          pageRange.setTotalCount(count);
          if (count > 0) {
            SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
            appendOrderBy(sql);
            Collection<User> userList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(User.class));
            pageRange.setDatas(userList);
          }
          return pageRange;
        }
      }
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
        appendOrderBy(sql);
        Collection<User> userList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(User.class));
        pageRange.setDatas(userList);
      }
      return pageRange;
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call UserPersistent.searchUser ");
      log.debug("parameter userSearch is : " + userSearch);
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (userSearch != null){
        buildObjectValueIsNotNullOfSql(sql, userSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(userSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          appendOrderBy(sql);
          return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(User.class));
        }
      }
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(userSearch), BeanPropertyRowMapper.newInstance(User.class));
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call UserPersistent.paginationSearchUser ");
      log.debug("parameter userSearch is : " + userSearch);
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      if (page.getFirstReslut() < 0) {
        throw EemsException.getException(EemsException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page.firstReslut ", " 大于等于0" });
      }
      if (page.getPageSize() < 1) {
        throw EemsException.getException(EemsException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page.maxReslut ", " 大于等于1" });
      }
      PageRange<User> pageRange = new PageRange<User>(page);
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (userSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, userSearch);
        buildObjectValueIsNotNullOfSql(sql, userSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          countSql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(userSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
          pageRange.setTotalCount(count);
          if (count > 0) {
            SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
            appendOrderBy(sql);
            Collection<User> userList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(User.class));
            pageRange.setDatas(userList);
          }
          return pageRange;
        }
      }
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(),new BeanPropertySqlParameterSource(userSearch), Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource paramSource = super.getPaginationParameter(new BeanPropertySqlParameterSource(userSearch), page);
        appendOrderBy(sql);
        Collection<User> userList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), paramSource, BeanPropertyRowMapper.newInstance(User.class));
        pageRange.setDatas(userList);
      }
      return pageRange;
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException( e, EemsException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }


  protected final void appendOrderBy(StringBuilder sql) {
    java.util.Set<Sort> sortList = new java.util.LinkedHashSet<>();
    if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())) {
      if (SecurityContext.getOperateInfo().getSortList() != null && !SecurityContext.getOperateInfo().getSortList().isEmpty()) {
        sortList.addAll(SecurityContext.getOperateInfo().getSortList());
      }
    }
    sortList.addAll(DEFAULT_SORT_LIST);
    if (sortList != null && !sortList.isEmpty()) {
      appendOrderBy(sql, COLUMNS_PARAMETER, sortList, TABLE_ALIAS);
    }
  }

  private final void buildObjectValueIsNotNullOfSql(StringBuilder sql, UserSearch userSearch) {
    if (!PropertyUtilities.asNullValue(userSearch.getNickname())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_NICKNAME).append(" = :nickname");
    }
    if (!PropertyUtilities.asNullValue(userSearch.getAccount())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_ACCOUNT).append(" = :account");
    }
    if (!PropertyUtilities.asNullValue(userSearch.getPassword())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_PASSWORD).append(" = :password");
    }
  }

  /**
   * {@inheritDoc}
   */
  public void isUnique(Collection<User> userCollection) throws EemsException{
    java.util.Set<String> uniqueSet0 = new java.util.HashSet<String>();
    String value0 = null;
    for(User user : userCollection) {
      value0 = "";
      value0 += ("account:" + user.getAccount());
      if (uniqueSet0.contains(value0)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { this.getNotUniqueErrorMessage(user)});
      } else {
        uniqueSet0.add(value0);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean isUnique(User user) {
    StringBuilder sql = new StringBuilder(COUNT_BASE_SQL).append(" AND ");
    sql.append('(');
    sql.append('(');
    sql.append(TABLE_ALIAS).append('.').append(COLUMN_ACCOUNT).append(" = ").append(':').append(COLUMNS_PARAMETER.get(COLUMN_ACCOUNT));
    sql.append(')');
    sql.append(" ) ");
    if (!PropertyUtilities.asNullValue(user.getUserId())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_USER_ID).append(" <> :").append(COLUMNS_PARAMETER.get(COLUMN_USER_ID));
    }
    long count = this.namedParameterJdbcTemplate.queryForObject(sql.toString(), new BeanPropertySqlParameterSource(user), Long.class);
    return count < 1;
  }
    
  /**
   * {@inheritDoc}
   */
  public String getNotUniqueErrorMessage(User user) {
    StringBuilder message = new StringBuilder("用户已经存在。");
    message.append('[');
    message.append("用户账号").append(':').append(user.getAccount());
    message.append(']');
    return message.toString();
  }
}