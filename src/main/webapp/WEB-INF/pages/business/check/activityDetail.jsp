<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <style type="text/css">
        .goods_pic {
            max-width: 400px;
            max-height: 300px;
        }
    </style>

    <script type="text/javascript">
        var isSubmiting = false;
        $(function () {
            $("#toptoolbar").ligerToolBar({
                items: [
                    {line: true},
                    {text: '保存', click: h2y_save, icon: 'save'},
                    {line: true},
                    {text: '刷新', click: h2y_refresh, icon: 'refresh'}
                ]
            });
            $(${fileDataListJson}).each(function () {
                var json_str = "{\"id\":\"" + this.id + "\"}";
                if (this.ord == 0) {
                    $("#activitypic_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                            "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a></br>");
                } else {
                    $("#activitypic_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>");
                }
            });
        });

        function h2y_save(){
            var queryString = $('#editform').serialize();
            var isCheck = $(".isCheck").val();
            if(isCheck==0||isCheck==''){
                alert("请选择审核结果！");
                return;
            }
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;
            $.post("/business/activity/checkActivity.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.success) {
                    alert(jsonReturn.msg);
                    var src = "<%=basePath%>business_check_checkActivityInit_htm";
                    top.f_addTab("business_check_checkActivityInit_htm", "活动审核列表", src);
                    top.f_getframe("business_check_checkActivityInit_htm").h2y_refresh();
                } else {
                    alert(jsonReturn.msg);
                }
                isSubmiting = false;
            });
        }
        function h2y_refresh(){
            document.location.reload();
        }
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
<form name="editform" method="post" action="" id="editform">
    <input type="hidden" name="id" value="${activity.id}" name="id">
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">活动名称：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${activity.activityName}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动类型：</td>
            <td class="h2y_table_edit_td">
                <c:if test="${activity.activityType==0}">个人</c:if>
                <c:if test="${activity.activityType==1}">团体</c:if>
            </td>
            <td class="h2y_table_label_td">活动主办方：</td>
            <td class="h2y_table_edit_td" >
                ${activity.activityOrganizer}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动人数：</td>
            <td class="h2y_table_edit_td">
                ${activity.activityCount}
            </td>
            <td class="h2y_table_label_td">是否加入热门活动：</td>
            <td class="h2y_table_edit_td">
                <c:if test="${activity.isRecommend==0}">否</c:if>
                <c:if test="${activity.isRecommend==1}">是</c:if>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动地点：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${activity.activityAddress}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">运动项：</td>
            <td class="h2y_table_edit_td" >
                ${kinds.name}
            </td>
            <td class="h2y_table_label_td">场馆：</td>
            <td class="h2y_table_edit_td" >
                ${venue.name}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动费用：</td>
            <td class="h2y_table_edit_td" >
                ${activity.activityPrice}
            </td>
            <td class="h2y_table_label_td">收费方式：</td>
            <td class="h2y_table_edit_td" >
                <c:if test="${activity.isFree==0}">收费</c:if>
                <c:if test="${activity.isFree==1}">免费</c:if>
                <c:if test="${activity.isFree==2}">AA制</c:if>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动联系人：</td>
            <td class="h2y_table_edit_td" >
                ${activity.activityContacts}
            </td>
            <td class="h2y_table_label_td">联系人电话：</td>
            <td class="h2y_table_edit_td" >
                ${activity.contactsPhone}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动开始时间：</td >
            <td class="h2y_table_edit_td">
                <fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td class="h2y_table_label_td">活动结束时间：</td>
            <td class="h2y_table_edit_td">
                <fmt:formatDate value="${activity.endDate}"  pattern="yyyy-MM-dd HH:mm"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">报名截止时间：</td>
            <td class="h2y_table_edit_td">
                <fmt:formatDate value="${activity.enrollDate}"  pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td class="h2y_table_label_td">排序：</td>
            <td class="h2y_table_edit_td">
                ${activity.ord}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动介绍：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${activity.activityIntroduce}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动申明：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${activity.declares}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动Logo：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${activity.activityPath}">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">活动图片：</td>
            <td class="h2y_table_edit_td2" colspan="3" id="activitypic_div">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
               审核结果：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <select name="isCheck" id="isCheck">
                    <option value="1">通过</option>
                    <option value="2">不通过</option>
                </select></br>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
