<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp"%>
    <script type="text/javascript">

    </script>
</head>
<body>
<div position="top">
    <table width="100%" class="my_toptoolbar_td">
        <tr>
            <td id="my_toptoolbar_td">
                <div class="l-toolbar">&nbsp;${mname}</div>
            </td>
            <td align="right" width="50%">
                <div id="toptoolbar"></div>
            </td>
        </tr>
    </table>
</div>
<div position="center">
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">
                订单编号：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${order.orderNo}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                订单状态：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <c:if test="${order.orderStatus==0}">待付款</c:if>
                <c:if test="${order.orderStatus==1}">待开始</c:if>
                <c:if test="${order.orderStatus==2}">待评价</c:if>
                <c:if test="${order.orderStatus==3}">已完成</c:if>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                商品名称：
            </td>
            <td class="h2y_table_edit_td2" >
                ${order.goodsName}
            </td>
            <td class="h2y_table_label_td">
                商品总价：
            </td>
            <td class="h2y_table_edit_td2">
                ${order.orderAmount}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                收货人：
            </td>
            <td class="h2y_table_edit_td2" >
                ${order.contact}
            </td>
            <td class="h2y_table_label_td">
                联系方式：
            </td>
            <td class="h2y_table_edit_td2" >
                ${order.telephone}
            </td>
        </tr>
    </table>
</div>
</body>
</html>
