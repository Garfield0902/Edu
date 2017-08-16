<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<nav id="mainnav-container" class="index_fixed">
                <!--<div id="mainnav" style=" position: relative; overflow: hidden;">-->
                <div id="mainnav" class="nano scrollable has-scrollbar" style="border-top: 1px solid rgba(255,255,255, .3);">
                    <div class="nano-content" tabindex="0" style="right: -17px;">
                        <ul id="mainnav-menu" class="list-group" style="margin-top:20px;">
                            <li class="menu-item">
                                <a href="<%= basePath %>homePage.do"">
                                    <span class="menu-title" data-lang="main_customer_title">首页</span>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="<%= basePath %>announcement/announcementPage.do">
                                    <span class="menu-title" data-lang="main_customer_title">通知公告</span>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="<%= basePath %>trainingInfo/trainingInfoPage.do">
                                    <span class="menu-title" data-lang="main_customer_title">培训信息</span>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="<%= basePath %>bmpjxx/archives.do">
                                    <span class="menu-title" data-lang="main_customer_title">培训档案</span>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="<%= basePath %>/jsjbxx/myInfo.do">
                                    <span class="menu-title" data-lang="main_customer_title">个人信息</span>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="<%= basePath %>announcement/announcementManagePage.do">
                                    <span class="menu-title" data-lang="main_customer_title">通知管理</span>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="javascript:;">
                                    <span class="menu-title" data-lang="main_customer_title">培训管理</span>
                                    <i class="arrow"></i>
                                </a>
                                <ul class="collapse">
                                    <li><a href="<%= basePath %>trainingInfo/activityManagementPage.do">活动管理</a></li>
                                    <li><a href="<%= basePath %>bmpjxx/evaluationManagementPage.do">评价管理</a></li>
                                    <li><a href="<%= basePath %>jsjbxx/getAllJsjbxxPage.do">教师管理</a></li>
                                </ul>
                            </li>
                            <li class="menu-item">
                                <a href="<%= basePath %>analysis/analysisPage.do">
                                    <span class="menu-title" data-lang="main_customer_title">综合分析</span>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="javascript:;">
                                    <span class="menu-title" data-lang="main_customer_title">系统管理</span>
                                    <i class="arrow"></i>
                                </a>
                                <ul class="collapse">
                                    <li><a href="<%= basePath %>department/info.do">组织架构</a></li>
                                    <li><a href="<%= basePath %>user/getAllUserPage.do">用户管理</a></li>
                                    <li><a href="<%= basePath %>roleManagement/roleManagementPage.do">角色管理</a></li>
                                    <li><a href="<%= basePath %>permission/getAllPermissionPage.do">权限管理</a></li>
                                    <li><a href="<%= basePath %>dataDictionary/dataDictionaryPage.do">数据字典</a></li>
                                    <li><a href="<%= basePath %>systemLog/systemLogPage.do">操作日志</a></li>
                                    <li><a href="<%= basePath %>backupManagement/backupManagementPage.do">备份管理</a></li>
                                    <li><a href="<%= basePath %>systemInfoPage.do">系统信息</a></li>
                                </ul>
                            </li>
                        </ul>

                    </div>
                    
                </div>
            </nav>