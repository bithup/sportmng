<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        $(function () {
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));

/*            var allocationType='${extensionAllocation.allocationType}';
            if(allocationType){
                $("input[name=allocationType][value="+allocationType+"]").attr("checked",true);
            }
            if(allocationType==1){
                $("#trProportion").css("display","table-row");
                $("#trOrderFee").css("display","none");
            }
            else{
                $("#trProportion").css("display","none");
                $("#trOrderFee").css("display","table-row");
            }
            $("input:radio[name=allocationType]").change(function () {
                var allocationType = $(this).val();
                if(allocationType==1){
                    $("#trProportion").css("display","table-row");
                    $("#trOrderFee").css("display","none");
                }
                else{
                    $("#trProportion").css("display","none");
                    $("#trOrderFee").css("display","table-row");
                }
            });*/
        });

        function h2y_save() {
            var proporLevelOne = $("#proporLevelOne").val();
            var orderLevelTwo = $("#orderLevelTwo").val();
            var orderPlatform = $("#orderPlatform").val();
            var enableStatus = $("input[name='enableStatus']:checked").val();
            if(proporLevelOne==""){
                alert("请输入一级推广比例");
                return;
            }
            if(orderLevelTwo==""){
                alert("请输入二级推广金额");
                return;
            }
            if(orderPlatform==""){
                alert("请输入平台所得金额");
                return;
            }
            if(enableStatus==null||enableStatus==""){
                alert("请选择启用状态");
                return;
            }
            if (!Validator.form()) return;
            var queryString = $('#editform').serialize();
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;
            //data服务器返回数据
            $.post("sport/extensionAllocation/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.h2y_refresh();
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.h2y_refresh();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }

    </script>
</head>

<body>
<form name="editform" method="post" action="" id="editform">
    <div>
        <input type="hidden" name="id" value="${extensionAllocation.id}"/>
        <input type="hidden" id="op"  name="op" value="${op}"/>
    </div>
    <table class="h2y_dialog_table" style="width: 560px;">
        <tr>
            <td class="h2y_table_label_td" style="width: 99px;">推广类型:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3"  style="width: 460px;">
                <c:if test="${op=='modify'}">
                    <c:if test="${extensionAllocation.extensionType==1}">
                        教练
                    </c:if>
                    <c:if test="${extensionAllocation.extensionType==2}">
                        活动
                    </c:if>
                    <c:if test="${extensionAllocation.extensionType==3}">
                        场馆
                    </c:if>
                </c:if>
                <c:if test="${op=='add'}">
                <select name="extensionType" id="extensionType">
                    <option value="1" <c:if test="${extensionAllocation.extensionType==1}">selected="selected"</c:if>>
                        教练</option>
                    <option value="2" <c:if test="${extensionAllocation.extensionType==2}">selected="selected"</c:if>>
                        活动</option>
                    <option value="3" <c:if test="${extensionAllocation.extensionType==3}">selected="selected"</c:if>>
                        场馆</option>
                </select>
                </c:if>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td" style="width: 99px;">一级推广:</td>
            <td class="h2y_dialog_table_edit_td" >
                占比例<input name="proporLevelOne" type="text" id="proporLevelOne" class="h2y_input_short" value="${extensionAllocation.proporLevelOne}"/>%
            </td>
            <td class="h2y_table_label_td" style="width: 99px;">二级推广:</td>
            <td class="h2y_dialog_table_edit_td">
                每单得<input name="orderLevelTwo" type="text" id="orderLevelTwo" class="h2y_input_short" value="${extensionAllocation.orderLevelTwo}"/>元
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td" style="width: 99px;">平台:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3"  style="width: 460px;">
                每单得<input name="orderPlatform" type="text" id="orderPlatform" class="h2y_input_short" value="${extensionAllocation.orderPlatform}"/>元
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td" style="width: 99px;">是否启用:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3"  style="width: 460px;">
                <h2y:input name="enableStatus" id="enableStatus" type="radio" initoption="1,启用:2,未启用" value="${extensionAllocation.enableStatus}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>