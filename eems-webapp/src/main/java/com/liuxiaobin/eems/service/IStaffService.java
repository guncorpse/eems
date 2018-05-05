package com.liuxiaobin.eems.service;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Staff;
import com.liuxiaobin.eems.search.StaffSearch;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该接口是对以下对象操作的接口。
 * <b>员工</b>
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IStaffService {

  // StaffPersistentImpl
  /**
   * 保存单个员工对象。
   *
   * @param staff
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveStaff(Staff staff) throws EemsException;

  /**
   * 批量保存员工对象。
   *
   * @param staffs
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveStaff(Collection<Staff> staffs) throws EemsException;

  /**
   * 修改单个员工对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param staff
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateStaff(Staff staff) throws EemsException;

  /**
   * 批量修改员工对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param staffs
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateStaff(Collection<Staff> staffs) throws EemsException;

  /**
   * 删除员工对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   * 
   * @param staff
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeStaff(Staff staff) throws EemsException;

  /**
   * 批量删除员工对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   *
   * @param staffs
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveStaff(Collection<Staff> staffs) throws EemsException;

  /**
   * 根据主键查询员工对象，如果未找到将返回NULL。
   * 
   * @param staffId
   *            要查询的主键。
   * @return 查询到的对象如果没有查到返回null
   * @throws EemsException 运行出错会抛出该异常
   */
  public Staff getStaffByPrimaryKey(java.lang.String staffId) throws EemsException;

  /**
   * 根据条件进行查询员工对象个数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param staffSearch
   *            查询条件
   * @return 查询到的数据条数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountStaff(StaffSearch staffSearch) throws EemsException;

  /**
   * 查询所有员工对象。
   *
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Staff> getAllStaff() throws EemsException;

  /**
   * 分页查询员工对象。
   *
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Staff> paginationGetAllStaff(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询员工对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param staffSearch
   *            查询条件
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Staff> searchStaff(StaffSearch staffSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询员工对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param staffSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Staff> paginationSearchStaff(StaffSearch staffSearch, PageSerachParameters page) throws EemsException;

}