package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.MyEmailDaoImpl;
import com.cxysl.entity.MyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyEmailService {

    @Autowired
    private MyEmailDaoImpl dao;

    /**
     * 查
     * 根据ID查找Email信息
     * @param id
     * @return
     */
    public MyEmail getMyEmailOne(Integer id)
    {
        return dao.getMyEmailOne(id);
    }

    /**
     * 查
     * 根据 姓名 查找Email信息
     * @param senderName
     * @return
     */
    public List<MyEmail> getMyEmailBySenderName(String senderName)
    {
        return dao.getMyEmailBySenderName(senderName);
    }

    /**
     * 查出所有邮件
     * @return
     */
    public List<MyEmail> downExcelMyEmailAll(){
        return dao.downExcelMyEmailAll();
    }

    /**
     * 查所有
     * @return
     */
    public Page<MyEmail> getMyEmailAll(Page<MyEmail> page)
    {
        return dao.getMyEmailAll(page);
    }

    /**
     * 增
     * 添加一条Email信息
     * @param email
     * @return
     */
    public int add_MyEmail(MyEmail email)
    {
        return dao.add_MyEmail(email);
    }


}
