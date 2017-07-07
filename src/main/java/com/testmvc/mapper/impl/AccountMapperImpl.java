package com.testmvc.mapper.impl;

import com.testmvc.domain.Account;
import com.testmvc.mapper.inf.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sidney on 2017/6/22.
 */
@Component
public class AccountMapperImpl implements AccountMapper {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public Account getAccountByUsername(String username) {
        return null;
    }

    @Override
    public Account getAccountByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public List<Account> getAccountList() {
        SqlSession session = sqlSessionFactory.openSession();
        List<Account> accountList = session.selectList("com.testmvc.mapper.inf.AccountMapper.getAccountList");
        return accountList;
    }

    @Override
    public void insertAccount(Account account) {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("com.testmvc.mapper.inf.AccountMapper.insertAccount", account);

    }

    @Override
    public void insertProfile(Account account) {

    }

    @Override
    public void insertSignon(Account account) {

    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void updateProfile(Account account) {

    }

    @Override
    public void updateSignon(Account account) {

    }
}
