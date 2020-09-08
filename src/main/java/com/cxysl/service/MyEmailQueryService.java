package com.cxysl.service;

import com.cxysl.dao.MyEmailQueryDaoImpl;
import com.cxysl.entity.MyEmailQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class MyEmailQueryService {

    @Autowired
    private MyEmailQueryDaoImpl dao;

    /**
     * 登录验证
     * @return
     */
    public MyEmailQuery myEmailQueryLogin(String account, String pwd) {
        return dao.myEmailQueryLogin(account,pwd);
    }
}
