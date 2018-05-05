package com.liuxiaobin.eems.service;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.entity.Wage;

public interface IBusinessService {
  
  public User login(User user) throws EemsException;
  
  public Collection<Wage> clearWages() throws EemsException;

}
