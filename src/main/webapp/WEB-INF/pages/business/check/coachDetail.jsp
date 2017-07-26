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
                    $("#coachpic_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                            "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a></br>");
                } else {
                    $("#coachpic_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>");
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
            $.post("/sport/coach/checkCoach.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.success) {
                    alert(jsonReturn.msg);
                    var src = "<%=basePath%>business_check_checkCoachInit_htm";
                    top.f_addTab("business_check_checkCoachInit_htm", "场馆审核列表", src);
                    top.f_getframe("business_check_checkCoachInit_htm").h2y_refresh();
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
    <input type="hidden" name="id" value="${coach.id}" name="id">
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">教练名称：</td>
            <td class="h2y_table_edit_td" >
                ${coach.name}
            </td>
            <td class="h2y_table_label_td">性别：</td>
            <td class="h2y_table_edit_td">
                <c:if test="${coach.sex==1}">男</c:if>
                <c:if test="${coach.sex==2}">女</c:if>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">生日：</td>
            <td class="h2y_table_edit_td">
                <fmt:formatDate value="${coach.birthday}" pattern="yyyy-MM-dd"/>

            </td>
            <td class="h2y_table_label_td">身高：</td>
            <td class="h2y_table_edit_td">
                ${coach.height}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">体重：</td>
            <td class="h2y_table_edit_td" >
                ${coach.weight}
            </td>
            <td class="h2y_table_label_td">电话：</td>
            <td class="h2y_table_edit_td" >
                ${coach.telPhone}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">教学经历：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${coach.teachingCareer}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">常驻场馆id：</td>
            <td class="h2y_table_edit_td" >
                ${coach.venueId}
            </td>
            <td class="h2y_table_label_td">常驻场馆名称：</td>
            <td class="h2y_table_edit_td" >
                ${coach.venueName}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">运动类型：</td>
            <td class="h2y_table_edit_td" >
                ${coach.sportId}
            </td>
            <td class="h2y_table_label_td">可教时间：</td>
            <td class="h2y_table_edit_td" >
                ${coach.businessTime}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">教练简介：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${coach.intro}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">是否认证：</td>
            <td class="h2y_table_edit_td" >
                <c:if test="${coach.isTrue==0}">否</c:if>
                <c:if test="${coach.isTrue==1}">是</c:if>
            </td>
            <td class="h2y_table_label_td">是否是热门教练：</td>
            <td class="h2y_table_edit_td" >
                <c:if test="${coach.isRecommend==0}">否</c:if>
                <c:if test="${coach.isRecommend==1}">是</c:if>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">身份证正面：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${coach.idCardFront}">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">身份证背面：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${coach.idCardBack}">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">头像Logo ：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${coach.headPath}">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">教练图片：</td>
            <td class="h2y_table_edit_td2" colspan="3" id="coachpic_div">
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
