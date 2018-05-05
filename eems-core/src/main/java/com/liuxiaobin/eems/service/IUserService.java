package com.liuxiaobin.eems.service;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.search.UserSearch;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该接口是对以下对象操作的接口。
 * <b>用户</b>
 * @author liuxiaobin
 * @version 0.0.1
 */
public interface IUserService {

  // UserPersistentImpl
  /**
   * 保存单个用户对象。
   *
   * @param fcsUser
   *            要保存的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void saveUser(User fcsUser) throws EemsException;

  /**
   * 批量保存用户对象。
   *
   * @param fcsUsers
   *            要保存的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchSaveUser(Collection<User> fcsUsers) throws EemsException;

  /**
   * 修改单个用户对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param fcsUser
   *            要修改的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void updateUser(User fcsUser) throws EemsException;

  /**
   * 批量修改用户对象。
   * 根据主键，修改表中已有的记录，修改后的值取至于要修改对象属性值。
   *
   * @param fcsUsers
   *            要修改的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchUpdateUser(Collection<User> fcsUsers) throws EemsException;

  /**
   * 删除用户对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   * 
   * @param fcsUser
   *            要删除的对象。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void removeUser(User fcsUser) throws EemsException;

  /**
   * 批量删除用户对象。
   * 如果主键不为空，那么就根据主键删除表中的记录，否则根据转入对象的非空属性进行全完匹配进行删除。
   *
   * @param fcsUsers
   *            要删除的对象集合。
   * @throws EemsException 运行出错会抛出该异常
   */
  public void batchRemoveUser(Collection<User> fcsUsers) throws EemsException;

  /**
   * 根据主键查询用户对象，如果未找到将返回NULL。
   * 
   * @param Id
   *            要查询的主键。
   * @return 查询到的对象如果没有查到返回null
   * @throws EemsException 运行出错会抛出该异常
   */
  public User getUserByPrimaryKey(java.lang.String Id) throws EemsException;

  /**
   * 根据条件进行查询用户对象个数。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   * 
   * @param fcsUserSearch
   *            查询条件
   * @return 查询到的数据条数
   * @throws EemsException 运行出错会抛出该异常
   */
  public Long getCountUser(UserSearch fcsUserSearch) throws EemsException;

  /**
   * 查询所有用户对象。
   *
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<User> getAllUser() throws EemsException;

  /**
   * 分页查询用户对象。
   *
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<User> paginationGetAllUser(PageSerachParameters page) throws EemsException;

  /**
   * 根据条件进行查询用户对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param fcsUserSearch
   *            查询条件
   * @return 查询到的对象集合如果没有查到返回null或者空集合
   * @throws EemsException 运行出错会抛出该异常
   */
  public Collection<User> searchUser(UserSearch fcsUserSearch) throws EemsException;

  /**
   * 根据条件进行，分页查询用户对象。
   * 当查询条件对象当中的属性不为空将按该条件进行eq查询。
   *
   * @param fcsUserSearch
   *            查询条件
   * @param page
   *            分页参数。
   * @return 当前面的对象集合以及总数据量
   * @throws EemsException 运行出错会抛出该异常
   */
  public PageRange<User> paginationSearchUser(UserSearch fcsUserSearch, PageSerachParameters page) throws EemsException;

}