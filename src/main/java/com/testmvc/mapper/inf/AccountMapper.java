package com.testmvc.mapper.inf;


import com.testmvc.domain.Account;

import java.util.List;

/**
 * The Interface AccountMapper.
 *
 */
public interface AccountMapper {

  Account getAccountByUsername(String username);

  Account getAccountByUsernameAndPassword(String username, String password);

  List<Account> getAccountList();

  void insertAccount(Account account);

  void insertProfile(Account account);

  void insertSignon(Account account);

  void updateAccount(Account account);

  void updateProfile(Account account);

  void updateSignon(Account account);

}
