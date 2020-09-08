package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.MyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyEmailDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from my_email";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }


    /**
     * 查
     * 根据ID查找Email信息
     * @param id
     * @return
     */
    public MyEmail getMyEmailOne(Integer id)
    {
        String sql = "select * from my_email where id=?";
        RowMapper<MyEmail> rowMapper = new BeanPropertyRowMapper<>(MyEmail.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    /**
     * 查
     * 根据 姓名 查找Email信息
     * @param senderName
     * @return
     */
    public List<MyEmail> getMyEmailBySenderName(String senderName)
    {
        String sql = "select * from my_email where sender_name=?";
        RowMapper<MyEmail> rowMapper = new BeanPropertyRowMapper<>(MyEmail.class);
        List<MyEmail> list = jdbcTemplate.query(sql,rowMapper,senderName);
        return list;
    }


    /**
     * 查出所有邮件
     * @return
     */
    public List<MyEmail> downExcelMyEmailAll()
    {
        String sql = "select * from my_email";
        RowMapper<MyEmail> rowMapper = new BeanPropertyRowMapper<>(MyEmail.class);
        List<MyEmail> list = jdbcTemplate.query(sql,rowMapper);
        return list;
    }




    /**
     * 查所有
     * @return
     */
    public Page<MyEmail> getMyEmailAll(Page<MyEmail> page)
    {
        page.setTotalCount(queryCount());
        String sql = "select * from my_email limit ?,?";

        RowMapper<MyEmail> rowMapper = new BeanPropertyRowMapper<>(MyEmail.class);
        List<MyEmail> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 增
     * 添加一条Email信息
     * @param email
     * @return
     */
    public int add_MyEmail(MyEmail email)
    {
        String sql = "insert into my_email values(null,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,email.getSenderName(),email.getSenderEmail(),
                email.getSenderPhone(),email.getMessage(),email.getEmailDate(),email.getRemark());
    }


}