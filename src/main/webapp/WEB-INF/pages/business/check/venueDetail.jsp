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
/*            $(${fileDataListJson}).each(function () {
                var json_str = "{\"id\":\"" + this.id + "\"}";
                if (this.ord == 0) {
                    $("#venuepic_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                            "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a></br>");
                } else {
                    $("#venuepic_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>");
                }
            });*/
        });

        function h2y_save(){
            var queryString = $('#editform').serialize();
            var isCheck = $(".isCheck").val();
            if(isCheck==0||isCheck==''){
                alert(isCheck);
                alert("请选择审核结果！");
                return;
            }
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;
            $.post("/sport/venue/checkVenue.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.success) {
                    alert(jsonReturn.msg);
                    var src = "<%=basePath%>business_check_checkVenueInit_htm";
                    top.f_addTab("business_check_checkVenueInit_htm", "场馆审核列表", src);
                    top.f_getframe("business_check_checkVenueInit_htm").h2y_refresh();
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
    <input type="hidden" name="id" value="${venue.id}" name="id">
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">场馆名称：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${venue.name}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">场馆所属区域：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${venue.zoneName}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">详细地址：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${venue.address}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">联系人：</td>
            <td class="h2y_table_edit_td" >
                ${venue.contact}
            </td>
            <td class="h2y_table_label_td">联系人性别：</td>
            <td class="h2y_table_edit_td">
                <c:if test="${venue.sex==1}">男</c:if>
                <c:if test="${venue.sex==2}">女</c:if>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">电话：</td>
            <td class="h2y_table_edit_td">
                ${venue.telephone}
            </td>
            <td class="h2y_table_label_td">手机：</td>
            <td class="h2y_table_edit_td">
                ${venue.mobile}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">场馆简介：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${venue.introduction}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">营业时间：</td>
            <td class="h2y_table_edit_td" >
                ${venue.businessTime}
            </td>
            <td class="h2y_table_label_td">营业执照编号：</td>
            <td class="h2y_table_edit_td" >
                ${venue.licenseNo}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">开始时间：</td >
            <td class="h2y_table_edit_td">
                ${venue.startTime}
            </td>
            <td class="h2y_table_label_td">结束时间：</td>
            <td class="h2y_table_edit_td">
                ${venue.endTime}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">法人代表：</td>
            <td class="h2y_table_edit_td" >
                ${venue.artificilPerson}
            </td>
            <td class="h2y_table_label_td">组成形式：</td>
            <td class="h2y_table_edit_td" >
                ${venue.organizationType}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">公交信息：</td>
            <td class="h2y_table_edit_td" >
                ${venue.busInfo}
            </td>
            <td class="h2y_table_label_td">地铁信息：</td>
            <td class="h2y_table_edit_td" >
                ${venue.subwayInfo}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">服务信息：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${venue.serviceInfo}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">硬件设施：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                ${venue.hardware}
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">是否可退款：</td>
            <td class="h2y_table_edit_td">
                <c:if test="${venue.isRefund==0}">否</c:if>
                <c:if test="${venue.isRefund==1}">是</c:if>
            </td>
            <td class="h2y_table_label_td">最晚退款时间：</td>
            <td class="h2y_table_edit_td">
                提前${venue.refundDeadline}小时
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">是否是热门场馆：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <c:if test="${venue.isRecommend==0}">否</c:if>
                <c:if test="${venue.isRecommend==1}">是</c:if>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">营业执照：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${venue.licenseUrl}">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">场馆主图：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${venue.pictureUrl}">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">场馆图片：</td>
            <td class="h2y_table_edit_td2" colspan="3" id="venuepic_div">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">身份证正面：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${venue.idCardFront}">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">身份证背面：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <img src="${venue.idCardBack}">
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
