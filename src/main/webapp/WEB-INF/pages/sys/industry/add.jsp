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
        var op = "${op}";
        var form = null;

        $(function () {
            if (op == "modify") {
                $("#tr_next").hide();
            } else {
                $("input[type=radio][name='reverse1']:first").attr("checked", true);
            }

            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));

            $("#ord").ligerSpinner({type: 'int', height: 25, width: 194,value:${sysIndustry.ord}});
        });


        function h2y_save() {

            if (!Validator.form()) return;

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("sys/industry/save.htm", queryString, function (data) {

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
                        alert(ifnext);
                        if (ifnext == 1) {
                            $("#code").val("");
                            $("#name").val("");
                            $("#ord").val(parseInt($("#ord").val())+1);
                        } else {
                            //刷新父级页面
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
        <input type="hidden" name="id" value="${sysIndustry.id}"/>
        <input type="hidden" name="op" value="${op}"/>
    </div>
    <table class="h2y_dialog_table" style="width:400px;">

        <tr>
            <td class="h2y_table_label_td">编码:</td>
            <td class="h2y_dialog_table_edit_td" style="width: 300px;">
                <input name="code" type="text" id="code" class="h2y_input_just"
                       value="${sysIndustry.code}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td" style="width: 300px;">
                <input name="name" type="text" id="name" class="h2y_input_just"
                       value="${sysIndustry.name}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">排序:</td>
            <td class="h2y_dialog_table_edit_td" style="width: 300px;">
                <input name="ord" type="text" id="ord" value="${sysIndustry.ord}"/>
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