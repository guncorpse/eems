package com.liuxiaobin.eems.persistent.implement;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Staff;
import com.liuxiaobin.eems.persistent.IStaffPersistent;
import com.liuxiaobin.eems.search.StaffSearch;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.nmxpsoft.base.commons.vo.Sort;
import com.nmxpsoft.base.persistent.jdbc.BasePersistent;
import com.nmxpsoft.base.security.SecurityContext;

/**
 * 该类是完成对数据库表EEMS_STAFF的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Repository("com.liuxiaobin.eems.StaffPersistent")
public class StaffPersistentImpl extends BasePersistent implements IStaffPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(StaffPersistentImpl.class);


  public static final String TABLE_NAME = "EEMS_STAFF";
  public static final String TABLE_ALIAS = "staff";

  public static final String COLUMN_STAFF_ID = "STAFF_ID";
  public static final String COLUMN_NAME = "NAME";
  public static final String COLUMN_SEX = "SEX";
  public static final String COLUMN_BASE_AMOUNT = "BASE_AMOUNT";
  public static final String COLUMN_ACCOUNT = "ACCOUNT";
  public static final String COLUMN_PASSWORD = "PASSWORD";
  public static final String COLUMN_PHONEN = "PHONEN";
  public static final String COLUMN_ADDRESS = "ADDRESS";
  public static final String COLUMN_WECHATN = "WECHATN";
  public static final String COLUMN_QQN = "QQN";
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
    COLUMNS.add(COLUMN_STAFF_ID);
    COLUMNS.add(COLUMN_NAME);
    COLUMNS.add(COLUMN_SEX);
    COLUMNS.add(COLUMN_ACCOUNT);
    COLUMNS.add(COLUMN_BASE_AMOUNT);
    COLUMNS.add(COLUMN_PASSWORD);
    COLUMNS.add(COLUMN_PHONEN);
    COLUMNS.add(COLUMN_ADDRESS);
    COLUMNS.add(COLUMN_WECHATN);
    COLUMNS.add(COLUMN_QQN);
    COLUMNS.add(COLUMN_CREATE_TIMESTAMPS);


    COLUMNS_PARAMETER.put(COLUMN_STAFF_ID , "staffId");
    COLUMNS_PARAMETER.put(COLUMN_NAME , "name");
    COLUMNS_PARAMETER.put(COLUMN_SEX , "sex");
    COLUMNS_PARAMETER.put(COLUMN_ACCOUNT , "account");
    COLUMNS_PARAMETER.put(COLUMN_BASE_AMOUNT , "baseAmount");
    COLUMNS_PARAMETER.put(COLUMN_PASSWORD , "password");
    COLUMNS_PARAMETER.put(COLUMN_PHONEN , "phonen");
    COLUMNS_PARAMETER.put(COLUMN_ADDRESS , "address");
    COLUMNS_PARAMETER.put(COLUMN_WECHATN , "wechatn");
    COLUMNS_PARAMETER.put(COLUMN_QQN , "qqn");
    COLUMNS_PARAMETER.put(COLUMN_CREATE_TIMESTAMPS , "createTimestamps");


    PRIMARY_KEY.add(COLUMN_STAFF_ID);

    KEY_SEARCH_COLUMNS.add(COLUMN_STAFF_ID);
    KEY_SEARCH_COLUMNS.add(COLUMN_NAME);
    KEY_SEARCH_COLUMNS.add(COLUMN_ACCOUNT);
    KEY_SEARCH_COLUMNS.add(COLUMN_PHONEN);
    KEY_SEARCH_COLUMNS.add(COLUMN_ADDRESS);
    KEY_SEARCH_COLUMNS.add(COLUMN_WECHATN);
    KEY_SEARCH_COLUMNS.add(COLUMN_QQN);
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
  public void saveStaff(Staff staff) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.saveStaff ");
      log.debug("parameter staff is : " + staff);
    }
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(staff));
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
  public void batchSaveStaff(Collection<Staff> staffs) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.batchSaveStaff ");
      log.debug("parameter staffs is : " + staffs);
    }
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(INSERT_SQL.toString(), SqlParameterSourceUtils.createBatch(staffs.toArray()));
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
  public void updateStaff(Staff staff) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.updateStaff ");
      log.debug("parameter staff is : " + staff);
    }
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(staff));
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
  public void batchUpdateStaff(Collection<Staff> staffs) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.batchUpdateStaff ");
      log.debug("parameter staffs is : " + staffs);
    }
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(UPDATE_SQL.toString(), SqlParameterSourceUtils.createBatch(staffs.toArray()));
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
  public void removeStaff(Staff staff) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.removeStaff ");
      log.debug("parameter staff is : " + staff);
    }
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("staffId", staff.getStaffId());
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
  public void batchRemoveStaff(Collection<Staff> staffs) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.batchRemoveStaff ");
      log.debug("parameter staffs is : " + staffs);
    }
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(DELETE_SQL_BY_PRIMARY_KEY.toString(), SqlParameterSourceUtils.createBatch(staffs.toArray()));
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
  public Staff getStaffByPrimaryKey(java.lang.String staffId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.getStaffByPrimaryKey ");
      log.debug("parameter staffId is : " + staffId);
    }
    try {
      if (PropertyUtilities.asNullValue(staffId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffId " );
      }
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_STAFF_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_STAFF_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue(COLUMNS_PARAMETER.get(COLUMN_STAFF_ID), staffId);
      Collection<Staff> staffList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(Staff.class));
      return (staffList != null && !staffList.isEmpty() && staffList.size() > 0) ? staffList.iterator().next() : null;
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
  public Long getCountStaff(StaffSearch staffSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.getCountStaff ");
      log.debug("parameter staffSearch is : " + staffSearch);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      if (staffSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, staffSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())) {
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          countSql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(staffSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          return this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
        }
      }
      return this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), new BeanPropertySqlParameterSource(staffSearch), Long.class);
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
  public Collection<Staff> getAllStaff() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.getAllStaff ");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          appendOrderBy(sql);
          return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(Staff.class));
        }
      }
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(Staff.class));
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
  public PageRange<Staff> paginationGetAllStaff(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.paginationGetAllStaff ");
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
      PageRange<Staff> pageRange = new PageRange<>(page);
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
            Collection<Staff> staffList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(Staff.class));
            pageRange.setDatas(staffList);
          }
          return pageRange;
        }
      }
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
        appendOrderBy(sql);
        Collection<Staff> staffList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(Staff.class));
        pageRange.setDatas(staffList);
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
  public Collection<Staff> searchStaff(StaffSearch staffSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.searchStaff ");
      log.debug("parameter staffSearch is : " + staffSearch);
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (staffSearch != null){
        buildObjectValueIsNotNullOfSql(sql, staffSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(staffSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          appendOrderBy(sql);
          return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(Staff.class));
        }
      }
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(staffSearch), BeanPropertyRowMapper.newInstance(Staff.class));
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
  public PageRange<Staff> paginationSearchStaff(StaffSearch staffSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffPersistent.paginationSearchStaff ");
      log.debug("parameter staffSearch is : " + staffSearch);
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
      PageRange<Staff> pageRange = new PageRange<Staff>(page);
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (staffSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, staffSearch);
        buildObjectValueIsNotNullOfSql(sql, staffSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          countSql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(staffSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
          pageRange.setTotalCount(count);
          if (count > 0) {
            SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
            appendOrderBy(sql);
            Collection<Staff> staffList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(Staff.class));
            pageRange.setDatas(staffList);
          }
          return pageRange;
        }
      }
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(),new BeanPropertySqlParameterSource(staffSearch), Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource paramSource = super.getPaginationParameter(new BeanPropertySqlParameterSource(staffSearch), page);
        appendOrderBy(sql);
        Collection<Staff> staffList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), paramSource, BeanPropertyRowMapper.newInstance(Staff.class));
        pageRange.setDatas(staffList);
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

  private final void buildObjectValueIsNotNullOfSql(StringBuilder sql, StaffSearch staffSearch) {
    if (!PropertyUtilities.asNullValue(staffSearch.getName())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_NAME).append(" = :name");
    }
    if (!PropertyUtilities.asNullValue(staffSearch.getAccount())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_ACCOUNT).append(" = :account");
    }
    if (!PropertyUtilities.asNullValue(staffSearch.getPassword())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_PASSWORD).append(" = :password");
    }
    if (!PropertyUtilities.asNullValue(staffSearch.getPhonen())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_PHONEN).append(" = :phonen");
    }
    if (!PropertyUtilities.asNullValue(staffSearch.getAddress())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_ADDRESS).append(" = :address");
    }
    if (!PropertyUtilities.asNullValue(staffSearch.getWechatn())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_WECHATN).append(" = :wechatn");
    }
    if (!PropertyUtilities.asNullValue(staffSearch.getQqn())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_QQN).append(" = :qqn");
    }
  }

  /**
   * {@inheritDoc}
   */
  public void isUnique(Collection<Staff> staffCollection) throws EemsException{
    java.util.Set<String> uniqueSet0 = new java.util.HashSet<String>();
    String value0 = null;
    for(Staff staff : staffCollection) {
      value0 = "";
      value0 += ("account:" + staff.getAccount());
      if (uniqueSet0.contains(value0)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { this.getNotUniqueErrorMessage(staff)});
      } else {
        uniqueSet0.add(value0);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean isUnique(Staff staff) {
    StringBuilder sql = new StringBuilder(COUNT_BASE_SQL).append(" AND ");
    sql.append('(');
    sql.append('(');
    sql.append(" ( ");
    sql.append(TABLE_ALIAS).append('.').append(COLUMN_PHONEN).append(" = ").append(':').append(COLUMNS_PARAMETER.get(COLUMN_PHONEN));
    sql.append(" OR ");
    sql.append(TABLE_ALIAS).append('.').append(COLUMN_PHONEN).append(" IS NULL ");
    sql.append(" ) ");
    sql.append(')');
    sql.append(" ) ");
    if (!PropertyUtilities.asNullValue(staff.getStaffId())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_STAFF_ID).append(" <> :").append(COLUMNS_PARAMETER.get(COLUMN_STAFF_ID));
    }
    long count = this.namedParameterJdbcTemplate.queryForObject(sql.toString(), new BeanPropertySqlParameterSource(staff), Long.class);
    return count < 1;
  }
    
  /**
   * {@inheritDoc}
   */
  public String getNotUniqueErrorMessage(Staff staff) {
    StringBuilder message = new StringBuilder("此手机号已经存在。");
    message.append('[');
    message.append("员工手机号").append(':').append(staff.getPhonen());
    message.append(']');
    return message.toString();
  }
}