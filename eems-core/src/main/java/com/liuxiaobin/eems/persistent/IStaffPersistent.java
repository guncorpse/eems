package com.liuxiaobin.eems.persistent;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Staff;
import com.liuxiaobin.eems.search.StaffSearch;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该接口是完成对数据库表EEMS_STAFF的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IStaffPersistent {
  public static final String THIS_OPERATE_TARGET = "com.liuxiaobin.eems.Staff";

  /**
   * 将对象保存到数据库 EEMS_STAFF 表中。
   *
   * @param staff
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveStaff(Staff staff) throws EemsException;

  /**
   * 将对象集合保存到数据库 EEMS_STAFF 表中。
   *
   * @param staffs
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveStaff(Collection<Staff> staffs) throws EemsException;

  /**
   * 修改数据库 EEMS_STAFF 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param staff
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateStaff(Staff staff) throws EemsException;

  /**
   * 批量修改数据库 EEMS_STAFF 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param staffs
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateStaff(Collection<Staff> staffs) throws EemsException;

  /**
   * 根据主键删除数据库 EEMS_STAFF 表的记录。
   * 
   * @param staff
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeStaff(Staff staff) throws EemsException;

  /**
   * 根据主键批量删除数据库 EEMS_STAFF 表的记录。
   *
   * @param staffs
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveStaff(Collection<Staff> staffs) throws EemsException;

  /**
   * 根据主键查询数据库 EEMS_STAFF 表中的记录，如果未找到将返回NULL。
   * 
   * @param staffId
   *            要查询的主键。
   * @return 根据主键查询到的对象，查询不到返回 null
   * @throws EemsException 运行出错会抛出该异常
   */
  public Staff getStaffByPrimaryKey(java.lang.String staffId) throws EemsException;

  /**
   * 根据条件进行查询数据库 EEMS_STAFF 表的记录条数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param staffSearch
   *            查询条件
   * @return 返回查询到的记录个数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountStaff(StaffSearch staffSearch) throws EemsException;

  /**
   * 查询数据库 EEMS_STAFF 表的所有记录。
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Staff> getAllStaff() throws EemsException;

  /**
   * 分页查询数据库 EEMS_STAFF 表的所有记录。。
   *
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Staff> paginationGetAllStaff(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询数据库 EEMS_STAFF 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param staffSearch
   *            查询条件
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<Staff> searchStaff(StaffSearch staffSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询数据库 EEMS_STAFF 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param staffSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<Staff> paginationSearchStaff(StaffSearch staffSearch, PageSerachParameters page) throws EemsException;

  /**
   * 判断员工对象集合当中是否有重复值。
   *
   * @param staffCollection
   *            要判断的员工对象集合。
   * @throws EemsException 如果有重复值将抛出该异常
   */
  public void isUnique(Collection<Staff> staffCollection) throws EemsException;

  /**
   * 判断员工对象是否唯一。
   *
   * @param staff
   *            要判断的员工对象。
   * @return true 代表当前对象是唯一的，false 代表当前对象不唯一
   */
  public boolean isUnique(Staff staff);

  /**
   * 获取员工对象不唯一的异常消息。
   *
   * @param staff
   *            不唯一的员工对象。
   * @return true 对象不唯一提示消息
   */
  public String getNotUniqueErrorMessage(Staff staff);
}