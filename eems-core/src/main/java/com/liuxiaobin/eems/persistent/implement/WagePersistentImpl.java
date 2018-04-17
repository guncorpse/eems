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
import com.liuxiaobin.eems.entity.Wage;
import com.liuxiaobin.eems.persistent.IWagePersistent;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import com.liuxiaobin.eems.commons.exception.EemsException;
import java.util.Collection;
import com.liuxiaobin.eems.search.WageSearch;

/**
 * 该类是完成对数据库表EEMS_WAGE的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Repository("com.liuxiaobin.eems.WagePersistent")
public class WagePersistentImpl extends BasePersistent implements IWagePersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(WagePersistentImpl.class);


  public static final String TABLE_NAME = "EEMS_WAGE";
  public static final String TABLE_ALIAS = "wage";

  public static final String COLUMN_WAGE_ID = "WAGE_ID";
  public static final String COLUMN_STAFF_ID = "STAFF_ID";
  public static final String COLUMN_BASE_AMOUNT = "BASE_AMOUNT";
  public static final String COLUMN_J_COUNT = "J_COUNT";
  public static final String COLUMN_C_COUNT = "C_COUNT";
  public static final String COLUMN_J_AMOUNT = "J_AMOUNT";
  public static final String COLUMN_C_AMOUNT = "C_AMOUNT";
  public static final String COLUMN_FINALLY_AMOUNT = "FINALLY_AMOUNT";
  public static final String COLUMN_CREATE_TIMESTAMPS = "CREATE_TIMESTAMPS";
  public static final String VIRTUAL_COLUMN_STAFF_NAME = new StringBuilder("(SELECT ").append(StaffPersistentImpl.COLUMN_NAME)
    .append(" FROM ").append(StaffPersistentImpl.TABLE_NAME).append(" AS ").append(StaffPersistentImpl.TABLE_ALIAS)
    .append(" WHERE ").append(StaffPersistentImpl.TABLE_ALIAS).append('.').append(StaffPersistentImpl.COLUMN_STAFF_ID)
    .append(" = ").append(TABLE_ALIAS).append('.').append(COLUMN_STAFF_ID).append(") AS ").append("STAFF_NAME").toString();
  public static final String VIRTUAL_COLUMN_STAFF_ACCOUNT = new StringBuilder("(SELECT ").append(StaffPersistentImpl.COLUMN_ACCOUNT)
    .append(" FROM ").append(StaffPersistentImpl.TABLE_NAME).append(" AS ").append(StaffPersistentImpl.TABLE_ALIAS)
    .append(" WHERE ").append(StaffPersistentImpl.TABLE_ALIAS).append('.').append(StaffPersistentImpl.COLUMN_STAFF_ID)
    .append(" = ").append(TABLE_ALIAS).append('.').append(COLUMN_STAFF_ID).append(") AS ").append("STAFF_ACCOUNT").toString();

  public static final LinkedHashSet<String> COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashSet<String> VIRTUAL_COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashSet<String> PRIMARY_KEY = new LinkedHashSet<>();
  public static final LinkedHashMap<String, String> COLUMNS_PARAMETER = new LinkedHashMap<>();

  private static final LinkedHashSet<String> NOT_INSERTABLE_COLUMNS = new LinkedHashSet<>();
  private static final LinkedHashSet<String> NOT_UPDATABLE_COLUMNS = new LinkedHashSet<>();
  private static final LinkedHashSet<Sort> DEFAULT_SORT_LIST = new LinkedHashSet<>();

  private static final StringBuilder INSERT_SQL;
  private static final StringBuilder UPDATE_SQL;
  private static final StringBuilder DELETE_SQL_BY_PRIMARY_KEY;
  public static final StringBuilder SELECT_BASE_SQL;
  public static final StringBuilder COUNT_BASE_SQL;
  static {
    COLUMNS.add(COLUMN_WAGE_ID);
    COLUMNS.add(COLUMN_STAFF_ID);
    COLUMNS.add(COLUMN_BASE_AMOUNT);
    COLUMNS.add(COLUMN_J_COUNT);
    COLUMNS.add(COLUMN_C_COUNT);
    COLUMNS.add(COLUMN_J_AMOUNT);
    COLUMNS.add(COLUMN_C_AMOUNT);
    COLUMNS.add(COLUMN_FINALLY_AMOUNT);
    COLUMNS.add(COLUMN_CREATE_TIMESTAMPS);
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_STAFF_NAME) ;
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_STAFF_ACCOUNT) ;


    COLUMNS_PARAMETER.put(COLUMN_WAGE_ID , "wageId");
    COLUMNS_PARAMETER.put(COLUMN_STAFF_ID , "staffId");
    COLUMNS_PARAMETER.put(COLUMN_BASE_AMOUNT , "baseAmount");
    COLUMNS_PARAMETER.put(COLUMN_J_COUNT , "jCount");
    COLUMNS_PARAMETER.put(COLUMN_C_COUNT , "cCount");
    COLUMNS_PARAMETER.put(COLUMN_J_AMOUNT , "jAmount");
    COLUMNS_PARAMETER.put(COLUMN_C_AMOUNT , "cAmount");
    COLUMNS_PARAMETER.put(COLUMN_FINALLY_AMOUNT , "finallyAmount");
    COLUMNS_PARAMETER.put(COLUMN_CREATE_TIMESTAMPS , "createTimestamps");


    PRIMARY_KEY.add(COLUMN_WAGE_ID);

    NOT_INSERTABLE_COLUMNS.addAll(VIRTUAL_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(VIRTUAL_COLUMNS);
    INSERT_SQL = generateInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, NOT_INSERTABLE_COLUMNS);
    UPDATE_SQL = generateUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, NOT_UPDATABLE_COLUMNS);
    DELETE_SQL_BY_PRIMARY_KEY = generateDeleteSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_BASE_SQL = generateBaseSelectSql(TABLE_NAME, COLUMNS, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_BASE_SQL = generateBaseCountSql(TABLE_NAME, PRIMARY_KEY, TABLE_ALIAS);
    DEFAULT_SORT_LIST.add(new Sort(COLUMNS_PARAMETER.get(COLUMN_CREATE_TIMESTAMPS), "DESC"));
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void saveWage(Wage wage) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.saveWage ");
      log.debug("parameter wage is : " + wage);
    }
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(wage));
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
  public void batchSaveWage(Collection<Wage> wages) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.batchSaveWage ");
      log.debug("parameter wages is : " + wages);
    }
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(INSERT_SQL.toString(), SqlParameterSourceUtils.createBatch(wages.toArray()));
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
  public void updateWage(Wage wage) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.updateWage ");
      log.debug("parameter wage is : " + wage);
    }
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(wage));
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
  public void batchUpdateWage(Collection<Wage> wages) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.batchUpdateWage ");
      log.debug("parameter wages is : " + wages);
    }
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(UPDATE_SQL.toString(), SqlParameterSourceUtils.createBatch(wages.toArray()));
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
  public void removeWage(Wage wage) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.removeWage ");
      log.debug("parameter wage is : " + wage);
    }
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("wageId", wage.getWageId());
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
  public void batchRemoveWage(Collection<Wage> wages) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.batchRemoveWage ");
      log.debug("parameter wages is : " + wages);
    }
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(DELETE_SQL_BY_PRIMARY_KEY.toString(), SqlParameterSourceUtils.createBatch(wages.toArray()));
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
  public Wage getWageByPrimaryKey(java.lang.String wageId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.getWageByPrimaryKey ");
      log.debug("parameter wageId is : " + wageId);
    }
    try {
      if (PropertyUtilities.asNullValue(wageId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wageId " );
      }
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_WAGE_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_WAGE_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue(COLUMNS_PARAMETER.get(COLUMN_WAGE_ID), wageId);
      Collection<Wage> wageList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(Wage.class));
      return (wageList != null && !wageList.isEmpty() && wageList.size() > 0) ? wageList.iterator().next() : null;
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
  public Long getCountWage(WageSearch wageSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.getCountWage ");
      log.debug("parameter wageSearch is : " + wageSearch);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      if (wageSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, wageSearch);
      }
      return this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), new BeanPropertySqlParameterSource(wageSearch), Long.class);
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
  public Collection<Wage> getAllWage() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.getAllWage ");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(Wage.class));
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
  public PageRange<Wage> paginationGetAllWage(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.paginationGetAllWage ");
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
      PageRange<Wage> pageRange = new PageRange<>(page);
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
        appendOrderBy(sql);
        Collection<Wage> wageList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(Wage.class));
        pageRange.setDatas(wageList);
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
  public Collection<Wage> searchWage(WageSearch wageSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.searchWage ");
      log.debug("parameter wageSearch is : " + wageSearch);
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (wageSearch != null){
        buildObjectValueIsNotNullOfSql(sql, wageSearch);
      }
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(wageSearch), BeanPropertyRowMapper.newInstance(Wage.class));
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
  public PageRange<Wage> paginationSearchWage(WageSearch wageSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WagePersistent.paginationSearchWage ");
      log.debug("parameter wageSearch is : " + wageSearch);
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
      PageRange<Wage> pageRange = new PageRange<Wage>(page);
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (wageSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, wageSearch);
        buildObjectValueIsNotNullOfSql(sql, wageSearch);
      }
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(),new BeanPropertySqlParameterSource(wageSearch), Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource paramSource = super.getPaginationParameter(new BeanPropertySqlParameterSource(wageSearch), page);
        appendOrderBy(sql);
        Collection<Wage> wageList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), paramSource, BeanPropertyRowMapper.newInstance(Wage.class));
        pageRange.setDatas(wageList);
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

  private final void buildObjectValueIsNotNullOfSql(StringBuilder sql, WageSearch wageSearch) {
    if (!PropertyUtilities.asNullValue(wageSearch.getStaffId())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_STAFF_ID).append(" = :staffId");
    }
    if (wageSearch.getBaseAmount() != null) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_BASE_AMOUNT).append(" = :baseAmount");
    }
    if (wageSearch.getJCount() != null) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_J_COUNT).append(" = :jCount");
    }
    if (wageSearch.getCCount() != null) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_C_COUNT).append(" = :cCount");
    }
    if (wageSearch.getJAmount() != null) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_J_AMOUNT).append(" = :jAmount");
    }
    if (wageSearch.getCAmount() != null) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_C_AMOUNT).append(" = :cAmount");
    }
    if (wageSearch.getFinallyAmount() != null) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_FINALLY_AMOUNT).append(" = :finallyAmount");
    }
  }
}