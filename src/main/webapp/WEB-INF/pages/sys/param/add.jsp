<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        var op = "${op}"
        var form = null;
        var unitId = "${unitId}";
        $(function () {

            if (op == "modify") {
                $("#tr_next").hide();
            }

            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));

            $("#ord").ligerSpinner({type: 'int', height: 25});

            if (unitId != "1") {
                $("#paramsCode").attr("disabled", true);
            }
        });

        function h2y_save() {

            if (!Validator.form()) return;

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("sys/param/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {

                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        var ifnext = $("input:radio[name=next]:checked").val();
                        if (ifnext == 1) {
                            $("#paramsCode").val("");
                            $("#paramsValue").val("");
                            $("#memo").val("");
                        } else {
                            parent.f_query();
                            frameElement.dialog.close();
                        }
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }

        function h2y_refresh() {
            document.location.reload();
        }
    </script>
</head>

<body>

<form name="editform" method="post" action="" id="editform">

    <input name="id" type="hidden" id="id" value="${sysParam.id}"/>
    <input name="typeId" type="hidden" id="typeId" value="${sysParam.typeId}"/>
    <input name="op" type="hidden" id="op" value="${op}"/>

    <table class="h2y_dialog_table">

        <tr>
            <td class="h2y_table_label_td">
                编码：
            </td>
            <td class="h2y_dialog_table_edit_td">
                <input name="paramsCode" type="text" id="paramsCode" class="h2y_input_just"
                       value="${sysParam.paramsCode}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                值：
            </td>
            <td class="h2y_dialog_table_edit_td">
                <input name="paramsValue" type="text" id="paramsValue" class="h2y_input_just"
                       value="${sysParam.paramsValue}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                排序：
            </td>
            <td class="h2y_dialog_table_edit_td">
                <input name="ord" type="text" id="ord" value="${sysParam.ord}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                备注：
            </td>
            <td class="h2y_dialog_table_edit_td">
                <input name="memo" type="text" id="memo" class="h2y_dialog_input_long"
                       value="${sysParam.memo}"/>
            </td>
        </tr>

        <tr id="tr_next">
            <td class="h2y_table_label_td">下一步:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="next" id="next" type="radio" initoption="1,继续添加:0,返回列表" value="1"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>