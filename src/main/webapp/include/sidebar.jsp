<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2019/12/24
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar sidebar-hide-to-small sidebar-shrink sidebar-gestures">
    <div class="nano">
        <div class="nano-content">
            <ul>
                <li class="label">业务操作</li>
                <%--<li class="active"><a href="/project_jdbc/queryOrderByPage/1"><i class="ti-home"></i> Orders </a></li>&lt;%&ndash;Dashboard&ndash;%&gt;--%>
                <li><a class="sidebar-sub-toggle"><i class="ti-home"></i> 订单管理 <span class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a href="/project_jdbc/queryOrderByPage/1"><i class="ti-view-list"></i>订单列表</a></li>
                        <li><a href="/project_jdbc/queryODByPage/1"><i class="ti-view-list"></i>订单明细</a></li>
                    </ul>
                </li>

                <li><a href="/project_jdbc/queryCustomerByPage/1"><i class="ti-user"></i> 客户信息表</a></li><%--Profile--%>


<%--表格 Here --%>
                <li><a class="sidebar-sub-toggle"><i class="ti-layout-grid4-alt"></i> 商品管理 <span class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a href="/project_jdbc/queryProByPage/1"><i class="ti-view-list"></i>商品完整信息表</a></li>
                        <li><a href="/project_jdbc/queryUpProByPage/1"><i class="ti-view-list"></i>已上架商品信息表</a></li>
                        <li><a href="/project_jdbc/queryUserByPage/1"><i class="ti-view-list"></i>管理员信息表</a></li>
                        <li><a href="/project_jdbc/queryTypeByPage/1"><i class="ti-view-list"></i>商品分类信息表</a></li>
                        <li><a href="/project_jdbc/queryPictureByPage/1"><i class="ti-view-list"></i>商品图片表</a></li>
                        <li><a href="/project_jdbc/queryPurByPage/1"><i class="ti-view-list"></i>商品采购表</a></li>
                    </ul>
                </li>
                <li><a class="sidebar-sub-toggle"><i class="ti-layout-sidebar-left"></i> 表单操作 <span class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath }/view/form-basic/Goods_Form.jsp"><i class="ti-marker-alt"></i>商品信息表单</a></li>
                        <li><a href="${pageContext.request.contextPath }/view/form-basic/User_Form.jsp"><i class="ti-marker-alt"></i>管理员操作表单</a></li>
                        <li><a href="${pageContext.request.contextPath }/view/form-basic/GoodsType.jsp"><i class="ti-marker-alt"></i>商品分类表单</a></li>
                        <li><a href="${pageContext.request.contextPath }/view/form-basic/goodsCount_Form.jsp"><i class="ti-marker-alt"></i>商品采购表单</a></li>
                        <li><a href="${pageContext.request.contextPath }/view/form-basic/GoodsPicture.jsp"><i class="ti-marker-alt"></i>商品图片表单</a></li>
                        <li><a href="${pageContext.request.contextPath }/view/form-basic/Customer.jsp"><i class="ti-marker-alt"></i>顾客操作表单</a></li>
                    </ul>
                </li>

                <li><a href="${pageContext.request.contextPath }/view/login.jsp"><i class="ti-close"></i> 退出 </a></li>

            </ul>
        </div>
    </div>
</div><!-- /# sidebar -->
