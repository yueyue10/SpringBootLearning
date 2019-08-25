package com.forezp.service;

import com.forezp.dao.AccountMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@Service
public class AccountService2 {

    @Autowired
    AccountMapper2 accountMapper2;

    @Transactional //这个注解相当于在方法没有异常的时候才会操作数据库，否则不会操作数据库
    public void transfer() throws RuntimeException{
        accountMapper2.update(90,1);//用户1减10块 用户2加10块
        accountMapper2.update(110,2);
        int i=1/0;
    }
}
