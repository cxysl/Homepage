<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2019/12/24
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>老程 Email</title>

    <%--CSS样式--%>
    <%@ include file="/include/link_css.jsp" %>
    <%--CSS样式--%>
</head>

<%
    System.out.println("登录页面");
%>

<body class="bg-primary">

<div class="unix-login">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="login-content">
                    <div class="login-logo">
                        <a href="index.html"><span>My Email</span></a>
                    </div>
                    <div class="login-form">
                        <h4>老程登录</h4>
<!-- form -->           <form action="/cxysl_api/myEmailQueryLogin.get" method="get">
                            <div class="form-group">
                                <label>账号</label>
                                <input type="text" name="account" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label>密码</label>
                                <input type="password" name="pwd" class="form-control" placeholder="Password">
                            </div>

                            <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">登录</button>

                            <div class="register-link m-t-15 text-center">
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>