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
        var op = "${op}"
        var form = null;

        $(function () {
            if (op == "modify") {
                $("#tr_next").hide();
            }

            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            $("#memo").attr("validate", "{'maxlength':255}");
            $("#code").attr("validate", "{'maxlength':30,'required':true,'alnumex':true}");
            $("#name").attr("validate", "{'maxlength':30,'required':true}");
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));

            $("#ord").ligerSpinner({type: 'int', height: 25,value:${sysType.ord}});
        });


        function h2y_save() {

            if (!Validator.form()) return;

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("sys/type/save.htm", queryString, function (data) {

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
                        var ifnext = $("input:radio[name=next]:checked").val();
                        if (ifnext == 1) {

                            $("#code").val("");
                            $("#name").val("");
                            $("#memo").val("");
                            parent.f_query();
                        } else {
                            parent.h2y_refresh();
                            frameElement.dialog.close();
                        }
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
        <input type="hidden" name="id" value="${sysType.id}"/>
        <input type="hidden" name="parentId" value="${sysType.parentId}"/>
        <input type="hidden" name="parentNid" value="${sysType.parentNid}"/>
        <input type="hidden" name="type" value="${sysType.type}"/>
        <input type="hidden" name="status" value="${sysType.status}"/>
        <input type="hidden" name="op" value="${op}"/>
    </div>
    <table class="h2y_dialog_table" style="width: 450px;">

        <tr>
            <td class="h2y_table_label_td">编码:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="code" type="text" id="code" class="h2y_input_just" value="${sysType.code}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="name" type="text" id="name" class="h2y_input_just" value="${sysType.name}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">备注:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="remark" type="text" id="remark" class="h2y_input_just" value="${sysType.remark}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td" valign="top">排序:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="ord" type="text" id="ord" value="${sysType.ord}"/>
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