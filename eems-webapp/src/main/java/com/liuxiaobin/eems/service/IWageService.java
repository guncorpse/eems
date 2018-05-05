package com.liuxiaobin.eems.service;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Wage;
import com.liuxiaobin.eems.search.WageSearch;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该接口是对以下对象操作的接口。
 * <b>工资</b>
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IWageService {

  // WagePersistentImpl
  /**
   * 保存单个工资对象。
   *
   * @param wage
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveWage(Wage wage) throws EemsException;

  /**
   * 批量保存工资对象。
   *
   * @param wages
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveWage(Collection<Wage> wages) throws EemsException;

  /**
   * 修改单个工资对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param wage
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateWage(Wage wage) throws EemsException;

  /**
   * 批量修改工资对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param wages
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateWage(Collection<Wage> wages) throws EemsException;

  /**
   * 删除工资对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   * 
   * @param wage
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeWage(Wage wage) throws EemsException;

  /**
   * 批量删除工资对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   *
   * @param wages
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveWage(Collection<Wage> wages) throws EemsException;

  /**
   * 根据主键查询工资对象，如果未找到将返回NULL。
   * 
   * @param wageId
   *            要查询的主键。
   * @return 查询到的对象如果没有查到返回null
   * @throws EemsException 运行出错会抛出该异常
   */
  public Wage getWageByPrimaryKey(java.lang.String wageId) throws EemsException;

  /**
   * 根据条件进行查询工资对象个数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param wageSearch
   *            查询条件
   * @return 查询到的数据条数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountWage(WageSearch wageSearch) throws EemsException;

  /**
   * 查询所有工资对象。
   *
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Wage> getAllWage() throws EemsException;

  /**
   * 分页查询工资对象。
   *
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Wage> paginationGetAllWage(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询工资对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param wageSearch
   *            查询条件
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Wage> searchWage(WageSearch wageSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询工资对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param wageSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Wage> paginationSearchWage(WageSearch wageSearch, PageSerachParameters page) throws EemsException;

}