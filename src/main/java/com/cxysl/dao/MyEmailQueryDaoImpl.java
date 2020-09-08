package com.cxysl.dao;

import com.cxysl.entity.MyEmailQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MyEmailQueryDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 登录验证
     * @return
     */
    public MyEmailQuery myEmailQueryLogin(String account, String pwd) {
        String sql = "select * from my_email_query where account=? and pwd=?";
        RowMapper<MyEmailQuery> rowMapper = new BeanPropertyRowMapper<>(MyEmailQuery.class);
        MyEmailQuery u =null;
        try {
            u = jdbcTemplate.queryForObject(sql,rowMapper,account,pwd);
        }catch (EmptyResultDataAccessException e){
            System.out.println("111");
            return  null;
        }
        if (null==u){
            System.out.println("1111");
        }
        return u;
    }

}
