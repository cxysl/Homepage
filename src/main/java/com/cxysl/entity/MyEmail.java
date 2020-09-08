package com.cxysl.entity;

public class MyEmail {

    private Integer id;        //ID
    private String senderName;        //发件人姓名
    private String senderEmail;      //发件人邮箱
    private String senderPhone;      //发件人电话
    private String message;       //消息内容
    private String emailDate;       //发件日期
    private String remark;     //备注

    public MyEmail() {
    }

    public MyEmail(Integer id, String senderName, String senderEmail, String senderPhone, String message, String emailDate, String remark) {
        this.id = id;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderPhone = senderPhone;
        this.message = message;
        this.emailDate = emailDate;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmailDate() {
        return emailDate;
    }

    public void setEmailDate(String emailDate) {
        this.emailDate = emailDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MyEmail{" +
                "id=" + id +
                ", senderName='" + senderName + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", senderPhone='" + senderPhone + '\'' +
                ", message='" + message + '\'' +
                ", emailDate='" + emailDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
