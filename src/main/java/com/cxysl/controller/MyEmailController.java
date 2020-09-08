package com.cxysl.controller;

import com.bbt.common.util.DateUtil;
import com.cxysl.comm.ExcelReader;
import com.cxysl.comm.Page;
import com.cxysl.entity.*;
import com.cxysl.service.MyEmailQueryService;
import com.cxysl.service.MyEmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@CrossOrigin
@RestController
@Slf4j
public class MyEmailController {

    @Autowired
    private MyEmailQueryService myEmailQueryService;

    @Autowired
    private MyEmailService myEmailService;

    /**
     * 查
     * 登录验证
     *
     * 本地：      http://localhost:8081/cxysl_api/myEmailQueryLogin.get
     *
     * 服务器：      http://www.cxysl.cn/cxysl_api/myEmailQueryLogin.get
     *
         *              Lucky     Lucky609064894
     * @return
     */
    @GetMapping("/myEmailQueryLogin.get")
    public void myEmailQueryLogin(String account,String pwd,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> map = new ConcurrentHashMap<>();

        MyEmailQuery myEmailQuery = myEmailQueryService.myEmailQueryLogin(account,pwd);
        if (myEmailQuery==null){        //没找到，登录失败
            log.info("登录失败!");

        }else{
            System.out.println("登录成功!");
            request.setAttribute("account",myEmailQuery.getAccount());
            String a = null;
            this.queryMyEmailAll(1,a,request,response);
        }

    }

    /**
     *查
     *本地：      http://localhost:8081/cxysl_api/queryMyEmailBySenderName?senderName=test
     *
     * 服务器：      http://www.cxysl.cn/cxysl_api/queryMyEmailBySenderName?senderName=test
     *
     *根据 姓名 查找Email信息
     * @return
     */
    @GetMapping("/queryMyEmailBySenderName")
    public Map<String,Object> queryMyEmailBySenderName(String senderName){
        log.info("正在查找"+senderName+"给您发的所有邮件……");

        List<MyEmail> list = myEmailService.getMyEmailBySenderName(senderName);

        Map<String,Object> map = new ConcurrentHashMap<>();

        map.put("code",new Integer(10000));
        map.put("msg","查询成功!!!");
        map.put("data",list);
        return map;
    }

    /**
     *查             Lucky609064894
     *查找所有Email信息
     *      本地：     http://localhost:8081/cxysl_api/queryMyEmailAll/1
     *
     *      服务器：    http://www.cxysl.cn/cxysl_api/queryMyEmailAll
     * @return
     */
    @GetMapping("/queryMyEmailAll/{page}")
    public void queryMyEmailAll(@PathVariable("page") Integer pageid,String account1, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("queryMyEmailAll");
        Page<MyEmail> page = new Page<>();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
       page = myEmailService.getMyEmailAll(page);

        if(request.getAttribute("account")!=null &&!(request.getAttribute("account").toString().equals(""))){
            String account = (String) request.getAttribute("account");
            System.out.println("当前用户"+account);
            request.setAttribute("account",account);
        }else if(account1 != null){
            request.setAttribute("account",account1);
        }

        for (int i = 0; i < page.getArrys().size(); i++) {
            System.out.println(page.getArrys().get(i));
        }

        System.out.println("123456:\t\t"+request.getAttribute("account"));

        request.setAttribute("page",page);
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);   //请求转发到表单页面
    }

    /**
     *         http://localhost:8081/cxysl_api/downExcel.get
     *
     * @param response
     * @return
     */
    @GetMapping("/downExcel.get")
    public Map<String,Object> downExcel(HttpServletResponse response){

        System.out.println("downExcel.get");
        List<MyEmail> emails = myEmailService.downExcelMyEmailAll();
        for (MyEmail email: emails
             ) {
            System.out.println(email);
        }

//        String filename = "MyEmail_" + new Date().toString() + ".xlsx";
//        String path = filename;
        InputStream inputStream = null;
        ByteArrayOutputStream out = null;
        File file = null;

        String[] title = new String[]{"senderName", "senderEmail", "senderPhone", "message", "emailDate", "remark"};
        String msg = null;
        try {
        XSSFWorkbook wb = new XSSFWorkbook();
        ExcelReader.createExcel2007(wb,"促销活动管理", new String[] {"发件人姓名", "发件人邮箱", "发件人电话", "消息内容", "发件日期", "备注"}, title, emails);

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + "MyEmail_"+DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS)
                + ".xlsx");
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        msg = "导出Excel成功!!!";
        } catch (Exception e) {
            msg = "download csv io error !" + e.toString();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        }


        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("code",new Integer(10000));
        map.put("msg",msg);
        map.put("data",emails);
        return map;
    }

    /**
     * 增
     * 添加一条Email信息
     *
     * 本地：      http://localhost:8081/cxysl_api/addOneMyEmail

         {
         "senderName":"周星驰",
         "senderEmail":"zxc@qq.com",
         "senderPhone":"9527",
         "message":"恭喜发财，祝你事业有成！"
         }

     * @param
     * @return
     */
    @PostMapping("/addOneMyEmail")
    public Map<String,Object> add_Customer(HttpServletRequest request, HttpServletResponse response){
        System.out.println("添加一条Email信息……");
        String senderName =new String(request.getParameter("senderName"));
        String senderEmail =new String(request.getParameter("senderEmail"));
        String senderPhone =new String(request.getParameter("senderPhone"));
        String message =new String(request.getParameter("message"));

//        System.out.println(email);
        Date date = new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        MyEmail email = new MyEmail(null,senderName,senderEmail,senderPhone,message,sdf.format(date),null);
//        email.setEmailDate(sdf.format(date));

        System.out.println(email);
        int flag = myEmailService.add_MyEmail(email);
        log.info(email.toString());

        if(flag==1){
            System.out.println("注册成功");
        }

        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("code",new Integer(10000));
        map.put("msg","注册成功!!!");
        map.put("data",email);
        return map;
    }
}
