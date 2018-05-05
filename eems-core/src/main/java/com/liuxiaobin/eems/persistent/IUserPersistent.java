package com.liuxiaobin.eems.persistent;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.search.UserSearch;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该接口是完成对数据库表FCS_USER的持久化申明，包括对该表的增、删、改、查等基本操作的接口定义。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IUserPersistent {
  public static final String THIS_OPERATE_TARGET = "com.liuxiaobin.eems.User";

  /**
   * 将对象保存到数据库 FCS_USER 表中。
   *
   * @param user
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveUser(User user) throws EemsException;

  /**
   * 将对象集合保存到数据库 FCS_USER 表中。
   *
   * @param users
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveUser(Collection<User> users) throws EemsException;

  /**
   * 修改数据库 FCS_USER 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param user
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateUser(User user) throws EemsException;

  /**
   * 批量修改数据库 FCS_USER 表的记录。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param users
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateUser(Collection<User> users) throws EemsException;

  /**
   * 根据主键删除数据库 FCS_USER 表的记录。
   * 
   * @param user
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeUser(User user) throws EemsException;

  /**
   * 根据主键批量删除数据库 FCS_USER 表的记录。
   *
   * @param users
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveUser(Collection<User> users) throws EemsException;

  /**
   * 根据主键查询数据库 FCS_USER 表中的记录，如果未找到将返回NULL。
   * 
   * @param userId
   *            要查询的主键。
   * @return 根据主键查询到的对象，查询不到返回 null
   * @throws EemsException 运行出错会抛出该异常
   */
  public User getUserByPrimaryKey(java.lang.String userId) throws EemsException;

  /**
   * 根据条件进行查询数据库 FCS_USER 表的记录条数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param userSearch
   *            查询条件
   * @return 返回查询到的记录个数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountUser(UserSearch userSearch) throws EemsException;

  /**
   * 查询数据库 FCS_USER 表的所有记录。
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<User> getAllUser() throws EemsException;

  /**
   * 分页查询数据库 FCS_USER 表的所有记录。。
   *
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<User> paginationGetAllUser(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询数据库 FCS_USER 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param userSearch
   *            查询条件
   * @return 返回所有查询到的对象集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<User> searchUser(UserSearch userSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询数据库 FCS_USER 表的记录。
   * 当查询条件对象当中的属性不为空将按该条件有效。
   *
   * @param userSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 返回当前页数据以及数据总条数等相关信息
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<User> paginationSearchUser(UserSearch userSearch, PageSerachParameters page) throws EemsException;

  /**
   * 判断用户对象集合当中是否有重复值。
   *
   * @param userCollection
   *            要判断的用户对象集合。
   * @throws EemsException 如果有重复值将抛出该异常
   */
  public void isUnique(Collection<User> userCollection) throws EemsException;

  /**
   * 判断用户对象是否唯一。
   *
   * @param user
   *            要判断的用户对象。
   * @return true 代表当前对象是唯一的，false 代表当前对象不唯一
   */
  public boolean isUnique(User user);

  /**
   * 获取用户对象不唯一的异常消息。
   *
   * @param user
   *            不唯一的用户对象。
   * @return true 对象不唯一提示消息
   */
  public String getNotUniqueErrorMessage(User user);
}