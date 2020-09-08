<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2019/12/24
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="pull-left">
        <div class="logo" id="sideLogo">
            <a href="#">
                <img class="full-logo" src="${pageContext.request.contextPath }/assets/images/logo-big.png" alt="SimDahs">
                <img class="small-logo" src="${pageContext.request.contextPath }/assets/images/logo-small.png" alt="SimDahs">
            </a>
        </div>
        <div class="hamburger sidebar-toggle">
            <span class="ti-menu"></span>
        </div>
    </div>

    <div class="pull-right p-r-15">
        <%--<ul>--%>
            <%--<li class="header-icon dib"><i class="ti-bell"></i>--%>
                <%--<div class="drop-down">--%>
                    <%--<div class="dropdown-content-heading">--%>
                        <%--<span class="text-left">Recent Notifications</span>--%>
                    <%--</div>--%>
                    <%--<div class="dropdown-content-body">--%>
                        <%--<ul>--%>
                            <%--<li>--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/3.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">Mr. Wolf</div>--%>
                                        <%--<div class="notification-text">5 members joined today </div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>

                            <%--<li>--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/3.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">Sofiya</div>--%>
                                        <%--<div class="notification-text">likes a photo of you</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>

                            <%--<li>--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/3.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">Richard</div>--%>
                                        <%--<div class="notification-text">Hi Teddy, Just wanted to let you ...</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>

                            <%--<li>--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/3.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">Sara Maggi</div>--%>
                                        <%--<div class="notification-text">Hi Teddy, Just wanted to let you ...</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li class="text-center">--%>
                                <%--<a href="#" class="more-link">See All</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</li>--%>
            <%--<li class="header-icon dib"><i class="ti-email"></i>--%>
                <%--<div class="drop-down">--%>
                    <%--<div class="dropdown-content-heading">--%>
                        <%--<span class="text-left">2 New Messages</span>--%>
                        <%--<a href="email.html"><i class="ti-pencil-alt pull-right"></i></a>--%>
                    <%--</div>--%>
                    <%--<div class="dropdown-content-body">--%>
                        <%--<ul>--%>
                            <%--<li class="notification-unread">--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/1.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">John Doe</div>--%>
                                        <%--<div class="notification-text">Hi Teddy, Just wanted to let you ...</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>

                            <%--<li class="notification-unread">--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/2.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">Sara Maggi</div>--%>
                                        <%--<div class="notification-text">Hi Teddy, Just wanted to let you ...</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>

                            <%--<li>--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/3.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">John Doe</div>--%>
                                        <%--<div class="notification-text">Hi Teddy, Just wanted to let you ...</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>

                            <%--<li>--%>
                                <%--<a href="#">--%>
                                    <%--<img class="pull-left m-r-10 avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/2.jpg" alt="" />--%>
                                    <%--<div class="notification-content">--%>
                                        <%--<small class="notification-timestamp pull-right">02:34 PM</small>--%>
                                        <%--<div class="notification-heading">Sara Maggi</div>--%>
                                        <%--<div class="notification-text">Hi Teddy, Just wanted to let you ...</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li class="text-center">--%>
                                <%--<a href="#" class="more-link">See All</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</li>--%>
            <%--<li class="header-icon dib chat-sidebar-icon"><i class="ti-comment"></i></li>--%>
            <li class="header-icon dib"><img class="avatar-img" src="${pageContext.request.contextPath }/assets/images/avatar/1.jpg" alt="" /> <span class="user-avatar">Wolf <i class="ti-angle-down f-s-10"></i></span>
                <div class="drop-down dropdown-profile">

                    <div class="dropdown-content-body">
                        <ul>
                            <li><a href="#"><i class="ti-power-off"></i> <span>Logout</span></a></li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
