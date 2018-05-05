package com.liuxiaobin.eems.persistent;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Wage;
import com.liuxiaobin.eems.search.WageSearch;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该接口是完成对数据库表EEMS_WAGE的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IWagePersistent {
  public static final String THIS_OPERATE_TARGET = "com.liuxiaobin.eems.Wage";

  /**
   * 将对象保存到数据库 EEMS_WAGE 表中。
   *
   * @param wage
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveWage(Wage wage) throws EemsException;

  /**
   * 将对象集合保存到数据库 EEMS_WAGE 表中。
   *
   * @param wages
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveWage(Collection<Wage> wages) throws EemsException;

  /**
   * 修改数据库 EEMS_WAGE 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param wage
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateWage(Wage wage) throws EemsException;

  /**
   * 批量修改数据库 EEMS_WAGE 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param wages
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateWage(Collection<Wage> wages) throws EemsException;

  /**
   * 根据主键删除数据库 EEMS_WAGE 表的记录。
   * 
   * @param wage
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeWage(Wage wage) throws EemsException;

  /**
   * 根据主键批量删除数据库 EEMS_WAGE 表的记录。
   *
   * @param wages
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveWage(Collection<Wage> wages) throws EemsException;

  /**
   * 根据主键查询数据库 EEMS_WAGE 表中的记录，如果未找到将返回NULL。
   * 
   * @param wageId
   *            要查询的主键。
   * @return 根据主键查询到的对象，查询不到返回 null
   * @throws EemsException 运行出错会抛出该异常
   */
  public Wage getWageByPrimaryKey(java.lang.String wageId) throws EemsException;

  /**
   * 根据条件进行查询数据库 EEMS_WAGE 表的记录条数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param wageSearch
   *            查询条件
   * @return 返回查询到的记录个数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountWage(WageSearch wageSearch) throws EemsException;

  /**
   * 查询数据库 EEMS_WAGE 表的所有记录。
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Wage> getAllWage() throws EemsException;

  /**
   * 分页查询数据库 EEMS_WAGE 表的所有记录。。
   *
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Wage> paginationGetAllWage(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询数据库 EEMS_WAGE 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param wageSearch
   *            查询条件
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Wage> searchWage(WageSearch wageSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询数据库 EEMS_WAGE 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param wageSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Wage> paginationSearchWage(WageSearch wageSearch, PageSerachParameters page) throws EemsException;
}