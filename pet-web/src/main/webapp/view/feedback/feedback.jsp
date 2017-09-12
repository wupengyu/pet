<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8"/>
    <title>问题反馈</title>
    <%--<link rel="stylesheet" href="../../accset/common/bootstrap-3.3.5/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet" href="../../accset/common/bootstrap-3.3.5/css/bootstrap-datetimepicker.min.css">--%>
    <%--<link rel="stylesheet" href="../../accset/common/bootstrap-3.3.5/css/bootstrap-theme.min.css">--%>
    <%--<link rel="stylesheet" href="../../accset/biz/feedback/css/feedback.css">--%>

    <%--<script src="../../accset/common/jquery/1.11.1/jquery.min.js"></script>--%>
    <%--<script src="../../accset/common/bootstrap-3.3.5/js/bootstrap.min.js"></script>--%>

    <%--<script src="../../accset/common/bootstrap-3.3.5/js/bootstrap-datetimepicker.min.js"></script>--%>
    <%--<script src="../../accset/biz/feedback/js/bootstrap-datetimepicker.zh-CN.js"></script>--%>
    <%--<script src="../../accset/common/jquery/1.12.0/plugs/jquery.twbsPagination.min.js"></script>--%>
    <%--<script src="../../accset/biz/feedback/js/feedback.js"></script>--%>

    <link rel="stylesheet" href="../../accset/biz/feedback/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../accset/biz/feedback/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../../accset/biz/feedback/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../../accset/biz/feedback/css/feedback.css">

    <%--<script src="../../accset/biz/feedback/js/jquery.min.js"></script>--%>
    <script src="../../accset/common/jquery-2.1.4.js"></script>
    <script src="../../accset/biz/feedback/js/bootstrap.min.js"></script>

    <script src="../../accset/biz/feedback/js/bootstrap-datetimepicker.min.js"></script>
    <script src="../../accset/biz/feedback/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="../../accset/biz/feedback/js/jquery.twbsPagination.min.js"></script>
    <script src="../../accset/biz/feedback/js/feedback.js"></script>
</head>
<body>
<div class="queryform">
    <form id="feedbackForm" action="/coros/feedback/list/find.do" method="POST">
        <input type="hidden" id="pageNum" name="page" value="${page.nowPage }">
        <table>
            <tr>
                <td><span>用户ID：</span><input name="userId" value="${model.userId }"/></td>
                <td><span>邮箱地址：</span><input name="email" value="${model.email }"/></td>
                <td><span>手机型号：</span><input name="mobile_name" value="${model.mobileName }"/></td>
                <td><span>手机版本：</span><input name="mobile_version" value="${model.mobileVersion }"/></td>
                <%--<td><span>硬件版本：</span><input name="hardware_version" value="${model.hardwareVersion }"/></td>--%>
            </tr>
            <tr>
                <td><span>设备型号：</span><input name="modelString" value="${model.modelString }"/></td>
                <td><span>固件&nbsp;&nbsp;ID：</span><input name="deviceId" value="${model.deviceId }"/></td>
                <td><span>固件版本：</span><input name="firmware_version" value="${model.firmwareVersion }"/></td>
                <td>
                    <button id="btnQuery">查询</button>
                </td>
            </tr>
        </table>

        <div class="dateselector control-group">
            <label class="control-label">上传时间：</label>
            <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                <input size="16" type="text" value="${model.startTime}" id="starttime" name="starttime" readonly>
                <span class="add-on"><i class="icon-remove"></i></span>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>
            <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                <input size="16" type="text" value="${model.endTime }" id="endtime" name="endtime" readonly>
                <span class="add-on"><i class="icon-remove"></i></span>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>
        </div>
        <input type="hidden" id="hi_status" name="status" value="${model.status }">
        <ul id="tabs_filter" class="nav nav-tabs nav-pills">
            <li>
                <a href="#" data-status="-1">全部</a>
            </li>
            <li class="active">
                <a href="#" data-status="0">新增</a>
            </li>
            <li>
                <a href="#" data-status="1">处理中</a>
            </li>
            <li>
                <a href="#" data-status="2">已解决</a>
            </li>
            <li>
                <a href="#" data-status="3">关闭</a>
            </li>
        </ul>
    </form>
</div>
<div class="feedback_content">
    <table class="table table-bordered feedback table-responsive">
        <thead>
        <tr>
            <th style="width:200px">设备信息</th>
            <th>问题描述</th>
            <th style="width:200px">手机信息</th>
            <th style="width:170px">问题状态</th>
            <th style="width:100px">时间</th>
            <th style="width:100px">其他</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dto" items="${resut}" varStatus="status">
        <tr>
            <td>
                <span>设备型号：${dto.modelString} </span>
                <span>固件ID：${dto.deviceId} </span>
                <span>固件版本：${dto.firmwareVersion} </span>
                    <%--<span>硬件版本：${dto.hardwareVersion}</span>--%>
            </td>
            <td style="width:300px">
                <a href="#" data-id="${dto.id}" class="link_desc" data-toggle="modal"
                   data-target="#myModal">${dto.description}</a>
            </td>
            <td>
                <span>手机号码：${dto.mobileNo} </span>
                <span>手机型号：${dto.mobileName} </span>
                <span>系统版本：${dto.mobileVersion} </span>
                <span>APP版本：${dto.appVersion}</span>
            </td>
            <td>
                <button data-id="${dto.id }" data-status="0" class="btn_status"
                        <c:if test="${dto.status==0}">disabled="disabled"</c:if>>新增
                </button>
                <button data-id="${dto.id }" data-status="1" class="btn_status"
                        <c:if test="${dto.status==1}">disabled="disabled"</c:if>>处理中
                </button>
                <button data-id="${dto.id }" data-status="2" class="btn_status"
                        <c:if test="${dto.status==2}">disabled="disabled"</c:if>>已解决
                </button>
                <button data-id="${dto.id }" data-status="3" class="btn_status"
                        <c:if test="${dto.status==3}">disabled="disabled"</c:if>>关闭
                </button>
            </td>
            <td>${dto.addTime}</td>
            <td>
                <a href="${dto.fileUrl}">日志下载</a><br>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<input type="hidden" id="totalPage" value="${page.totallyPage }">
<ul id="pagination" class="pagination-sm"></ul>
<!--
    作者：hudahua@yftech.com
    时间：2016-11-16
    描述：弹出层
-->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                问题状态：
                <button data-status="0" class="btn_status">新增</button>
                <button data-status="1" class="btn_status">处理中</button>
                <button data-status="2" class="btn_status">已解决</button>
                <button data-status="3" class="btn_status">关闭</button>
                <span id="dlg_time"></span>
            </div>
            <div class="modal-body">
                <table class="table table-bordered feedback table-responsive">
                    <tr>
                        <th colspan="2">问题描述</th>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <p id="dlg_desc"></p>
                        </td>
                    </tr>
                    <tr>
                        <th>设备信息</th>
                        <th>手机信息</th>
                    </tr>
                    <tr>
                        <td>
                            <p id="dlg_dev"></p>
                        </td>
                        <td>
                            <p id="dlg_phone"></p>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <textarea id="txtreply"></textarea>
                        </th>
                    </tr>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">发送</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
</body>
</html>