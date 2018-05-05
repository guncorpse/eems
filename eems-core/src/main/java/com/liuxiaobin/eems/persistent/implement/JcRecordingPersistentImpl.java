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
import com.liuxiaobin.eems.entity.JcRecording;
import com.liuxiaobin.eems.persistent.IJcRecordingPersistent;
import com.liuxiaobin.eems.search.JcRecordingSearch;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.nmxpsoft.base.commons.vo.Sort;
import com.nmxpsoft.base.persistent.jdbc.BasePersistent;
import com.nmxpsoft.base.security.SecurityContext;

/**
 * 该类是完成对数据库表EEMS_JC_RECORDING的持久化实现，包括对该表的增、删、改、查等基本操作的的具休实现。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Repository("com.liuxiaobin.eems.JcRecordingPersistent")
public class JcRecordingPersistentImpl extends BasePersistent implements IJcRecordingPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(JcRecordingPersistentImpl.class);


  public static final String TABLE_NAME = "EEMS_JC_RECORDING";
  public static final String TABLE_ALIAS = "jcRecording";

  public static final String COLUMN_RECORDING_ID = "RECORDING_ID";
  public static final String COLUMN_STAFF_ID = "STAFF_ID";
  public static final String COLUMN_JC_TYPE = "JC_TYPE";
  public static final String COLUMN_AMOUNT = "AMOUNT";
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

  public static final LinkedHashSet<String> KEY_SEARCH_COLUMNS = new LinkedHashSet<>();
  private static final LinkedHashSet<Sort> DEFAULT_SORT_LIST = new LinkedHashSet<>();

  private static final StringBuilder INSERT_SQL;
  private static final StringBuilder UPDATE_SQL;
  private static final StringBuilder DELETE_SQL_BY_PRIMARY_KEY;
  public static final StringBuilder SELECT_BASE_SQL;
  public static final StringBuilder COUNT_BASE_SQL;
  static {
    COLUMNS.add(COLUMN_RECORDING_ID);
    COLUMNS.add(COLUMN_STAFF_ID);
    COLUMNS.add(COLUMN_JC_TYPE);
    COLUMNS.add(COLUMN_AMOUNT);
    COLUMNS.add(COLUMN_CREATE_TIMESTAMPS);
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_STAFF_NAME) ;
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_STAFF_ACCOUNT) ;


    COLUMNS_PARAMETER.put(COLUMN_RECORDING_ID , "recordingId");
    COLUMNS_PARAMETER.put(COLUMN_STAFF_ID , "staffId");
    COLUMNS_PARAMETER.put(COLUMN_JC_TYPE , "jcType");
    COLUMNS_PARAMETER.put(COLUMN_AMOUNT , "amount");
    COLUMNS_PARAMETER.put(COLUMN_CREATE_TIMESTAMPS , "createTimestamps");


    PRIMARY_KEY.add(COLUMN_RECORDING_ID);

    NOT_INSERTABLE_COLUMNS.addAll(VIRTUAL_COLUMNS);
    NOT_UPDATABLE_COLUMNS.addAll(VIRTUAL_COLUMNS);
    KEY_SEARCH_COLUMNS.add(COLUMN_RECORDING_ID);
    KEY_SEARCH_COLUMNS.add(COLUMN_STAFF_ID);
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
  public void saveJcRecording(JcRecording jcRecording) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.saveJcRecording ");
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      this.namedParameterJdbcTemplate.update(INSERT_SQL.toString(), new BeanPropertySqlParameterSource(jcRecording));
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
  public void batchSaveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.batchSaveJcRecording ");
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(INSERT_SQL.toString(), SqlParameterSourceUtils.createBatch(jcRecordings.toArray()));
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
  public void updateJcRecording(JcRecording jcRecording) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.updateJcRecording ");
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      this.namedParameterJdbcTemplate.update(UPDATE_SQL.toString(), new BeanPropertySqlParameterSource(jcRecording));
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
  public void batchUpdateJcRecording(Collection<JcRecording> jcRecordings) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.batchUpdateJcRecording ");
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(UPDATE_SQL.toString(), SqlParameterSourceUtils.createBatch(jcRecordings.toArray()));
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
  public void removeJcRecording(JcRecording jcRecording) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.removeJcRecording ");
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue("recordingId", jcRecording.getRecordingId());
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
  public void batchRemoveJcRecording(Collection<JcRecording> jcRecordings) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.batchRemoveJcRecording ");
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      this.namedParameterJdbcTemplate.batchUpdate(DELETE_SQL_BY_PRIMARY_KEY.toString(), SqlParameterSourceUtils.createBatch(jcRecordings.toArray()));
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
  public JcRecording getJcRecordingByPrimaryKey(java.lang.String recordingId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.getJcRecordingByPrimaryKey ");
      log.debug("parameter recordingId is : " + recordingId);
    }
    try {
      if (PropertyUtilities.asNullValue(recordingId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " recordingId " );
      }
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_RECORDING_ID).append(" = :").append(COLUMNS_PARAMETER.get(COLUMN_RECORDING_ID));
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      paramSource.addValue(COLUMNS_PARAMETER.get(COLUMN_RECORDING_ID), recordingId);
      Collection<JcRecording> jcRecordingList = this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
      return (jcRecordingList != null && !jcRecordingList.isEmpty() && jcRecordingList.size() > 0) ? jcRecordingList.iterator().next() : null;
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
  public Long getCountJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.getCountJcRecording ");
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      if (jcRecordingSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, jcRecordingSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())) {
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          countSql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(jcRecordingSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          return this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
        }
      }
      return this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), new BeanPropertySqlParameterSource(jcRecordingSearch), Long.class);
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
  public Collection<JcRecording> getAllJcRecording() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.getAllJcRecording ");
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      MapSqlParameterSource paramSource = new MapSqlParameterSource();
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          appendOrderBy(sql);
          return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
        }
      }
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
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
  public PageRange<JcRecording> paginationGetAllJcRecording(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.paginationGetAllJcRecording ");
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
      PageRange<JcRecording> pageRange = new PageRange<>(page);
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
            Collection<JcRecording> jcRecordingList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
            pageRange.setDatas(jcRecordingList);
          }
          return pageRange;
        }
      }
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
        appendOrderBy(sql);
        Collection<JcRecording> jcRecordingList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
        pageRange.setDatas(jcRecordingList);
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
  public Collection<JcRecording> searchJcRecording(JcRecordingSearch jcRecordingSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.searchJcRecording ");
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
    }
    try {
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (jcRecordingSearch != null){
        buildObjectValueIsNotNullOfSql(sql, jcRecordingSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(jcRecordingSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          appendOrderBy(sql);
          return this.namedParameterJdbcTemplate.query(sql.toString(), paramSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
        }
      }
      appendOrderBy(sql);
      return this.namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(jcRecordingSearch), BeanPropertyRowMapper.newInstance(JcRecording.class));
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
  public PageRange<JcRecording> paginationSearchJcRecording(JcRecordingSearch jcRecordingSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingPersistent.paginationSearchJcRecording ");
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
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
      PageRange<JcRecording> pageRange = new PageRange<JcRecording>(page);
      StringBuilder countSql = new StringBuilder(COUNT_BASE_SQL);
      StringBuilder sql = new StringBuilder(SELECT_BASE_SQL);
      if (jcRecordingSearch != null){
        buildObjectValueIsNotNullOfSql(countSql, jcRecordingSearch);
        buildObjectValueIsNotNullOfSql(sql, jcRecordingSearch);
      }
      if (SecurityContext.getOperateInfo() != null && THIS_OPERATE_TARGET.equals(SecurityContext.getOperateInfo().getOperateTarget())){
        if (SecurityContext.getOperateInfo().getSearchKey() != null && !SecurityContext.getOperateInfo().getSearchKey().trim().isEmpty()) {
          countSql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          sql.append(" AND ").append(generateKeySearchWhereSql(KEY_SEARCH_COLUMNS, TABLE_ALIAS));
          MapSqlParameterSource paramSource = getMapSqlParameterSource(new BeanPropertySqlParameterSource(jcRecordingSearch));
          paramSource.addValue(SEARCH_KEY_PARAMETER, getLikeValue(SecurityContext.getOperateInfo().getSearchKey().trim()));
          Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(), paramSource, Long.class);
          pageRange.setTotalCount(count);
          if (count > 0) {
            SqlParameterSource pageParamSource = super.getPaginationParameter(paramSource, page);
            appendOrderBy(sql);
            Collection<JcRecording> jcRecordingList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), pageParamSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
            pageRange.setDatas(jcRecordingList);
          }
          return pageRange;
        }
      }
      Long count = this.namedParameterJdbcTemplate.queryForObject(countSql.toString(),new BeanPropertySqlParameterSource(jcRecordingSearch), Long.class);
      pageRange.setTotalCount(count);
      if (count > 0) {
        SqlParameterSource paramSource = super.getPaginationParameter(new BeanPropertySqlParameterSource(jcRecordingSearch), page);
        appendOrderBy(sql);
        Collection<JcRecording> jcRecordingList = this.namedParameterJdbcTemplate.query(super.getPaginationBySimpleSql(sql).toString(), paramSource, BeanPropertyRowMapper.newInstance(JcRecording.class));
        pageRange.setDatas(jcRecordingList);
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

  private final void buildObjectValueIsNotNullOfSql(StringBuilder sql, JcRecordingSearch jcRecordingSearch) {
    if (!PropertyUtilities.asNullValue(jcRecordingSearch.getStaffId())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_STAFF_ID).append(" = :staffId");
    }
    if (!PropertyUtilities.asNullValue(jcRecordingSearch.getJcType())) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_JC_TYPE).append(" = :jcType");
    }
    if (jcRecordingSearch.getAmount() != null) {
      sql.append(" AND ").append(TABLE_ALIAS).append('.').append(COLUMN_AMOUNT).append(" = :amount");
    }
  }
}