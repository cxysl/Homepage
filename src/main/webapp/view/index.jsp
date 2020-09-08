<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>My Email</title>

<%--CSS样式--%>
    <%@ include file="/include/link_css.jsp" %>
<%--CSS样式--%>
    <script>
        function forword(url) {
            window.location.replace(url);
        }
    </script>
</head>

<body>


<%--<%--%>
<%--    String account=(String)session.getAttribute("account");--%>
<%--    if(account==null)--%>
<%--    {--%>
<%--%>--%>
<%--<script>--%>
<%--    alert('尊敬的游客，由于您未登录，所以无法使用该平台，谢谢合作！');--%>
<%--    window.location.href('/cxysl_api/view/login.jsp');--%>
<%--</script>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>

<%--<%--%>
<%--    System.out.println("index.jsp:\t\t"+session.getAttribute("account"));--%>
<%--%>--%>
<%--<% if(session.getAttribute("account")==null || session.getAttribute("account").toString().equals("")){--%>

<%--    response.sendRedirect("/cxysl_api/view/login.jsp");--%>
<%--    return;--%>
<%--  }--%>
<%--%>--%>

<%--没有对象说明添加操作--%>
<c:if test="${empty account}">
    <%
        response.sendRedirect("/cxysl_api/view/login.jsp");
    %>
<%--    <script>--%>
<%--        window.location.href=" /cxysl_api/view/login.jsp";--%>
<%--    </script>--%>
</c:if>

<c:if test="${!empty account}">

    <%
        System.out.println("index:\t有账户\n\n\n");
    %>




    <div class="content-wrap">
        <div class="main">
            <div class="container-fluid">

            <%--仪表板    --%>
                <div class="row">
                    <div class="col-lg-8 p-0">
                        <div class="page-header">
                            <div class="page-title">
                                <h1 >
                                    <a href="https://www.cxysl.cn"><i class="ti-arrow-circle-left"></i> </a>
                                    个人主页
                                </h1>

                            </div>
                        </div>
                    </div>  <!-- /# column -->
                    <div class="col-lg-4 p-0">
                        <div class="page-header">
                            <div class="page-title">
                                <ol class="breadcrumb text-right">
                                </ol>
                            </div>
                        </div>
                    </div><!-- /# column -->
                </div><!-- /# row -->
            <%--仪表板    --%>

                <div class="main-content">



					<div class="row">
						<div class="col-lg-12">
                            <div class="card alert">
                                <div class="card-header">
                                    <h4 class="mb">My Email</h4>
									<div class="card-header-right-icon">
										<ul>
											<li class="card-close" data-dismiss="alert"><i class="ti-close"></i></li>
											<li class="card-option drop-menu"><i class="ti-settings" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" role="link"></i>
												<ul class="card-option-dropdown dropdown-menu">
                                                    <li><a href="/cxysl_api/downExcel.get"><i class="ti-pulse"></i>下载Excel</a></li>
                                                </ul>
											</li>
										</ul>
									</div>
                                </div>
                                <div class="card-body">
                                    <table class="table table-responsive table-hover ">
                                        <thead>
                                        <tr>
                                            <th style="text-align: center">发件人姓名</th>
                                            <th style="text-align: center">发件人邮箱</th>
                                            <th style="text-align: center">发件人电话</th>
                                            <th style="text-align: center">消息内容</th>
                                            <th style="text-align: center">发件日期</th>
                                            <th style="text-align: center">备注</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach var="p" items="${page.arrys}">
                                            <tr>
                                                <td style="text-align: center">${p.senderName}</td>
                                                <td style="text-align: center" class="color-success">${p.senderEmail}</td

                                                <td></td>
                                                <td style="text-align: center" class="color-success">${p.senderPhone}</td>
                                                <td style="text-align: center" class="color-success col-lg-6">${p.message}</td>
                                                <td style="text-align: center" class="color-success">${p.emailDate}</td>
                                                <td style="text-align: center" class="color-success">${p.remark}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <a href="/cxysl_api/queryMyEmailAll/${page.firstpage}?account1=${account}">首页</a>
                                    <a href="/cxysl_api/queryMyEmailAll/${page.prePage}?account1=${account}">上一页</a>
                                    <c:forEach var="index" items="${page.indexs}">
                                        &nbsp;&nbsp;<a href="/cxysl_api/queryMyEmailAll/${index}?account1=${account}">${index}</a>&nbsp;&nbsp;
                                    </c:forEach>
                                    <a href="/cxysl_api/queryMyEmailAll/${page.nextpage}?account1=${account}">下一页</a>
                                    <a href="/cxysl_api/queryMyEmailAll/${page.lastpage}?account1=${account}">尾页</a>

                                </div>
                            </div>
                        </div><!-- /# column -->
					</div><!-- /# row -->

                </div>
            </div><!-- /# container-fluid -->
        </div><!-- /# main -->
    </div><!-- /# content wrap -->
</c:if>
<%--JS样式--%>
<%@ include file="/include/link_js.jsp" %>
<%--JS样式--%>
	

</body>
</html>