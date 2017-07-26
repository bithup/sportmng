<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script type="text/javascript">
        var isSubmiting = false;
        $(function () {
            $("#toptoolbar").ligerToolBar({
                items: [{line: true}, {
                    text: '保存',
                    click: h2y_save,
                    icon: 'save'
                }, {line: true}, {text: '刷新', click: h2y_refresh, icon: 'refresh'}]
            });
        });

        function h2y_save(){
            var queryString = $('#editform').serialize();

            var drawStatus = document.getElementsByName("drawStatus");
            var drawStatusVal = 0;
            for(var i=0;i<drawStatus.length;i++){
                if(drawStatus[i].checked){
                    drawStatusVal = drawStatus[i].value;
                }
            }
            alert(drawStatusVal);
            if(drawStatusVal==0){
                alert("请选择是否已打款！");
                return;
            }

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }

            isSubmiting = true;
            $.post("sport/withdrawals/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    if (top.f_getframe("sport_withdrawals_init_htm") != null)
                        top.f_getframe("sport_withdrawals_init_htm").f_query();
                        top.f_delTab("sport_withdrawals_get_htm_id_${withdrawals.id}");
                } else {
                    alert(jsonReturn.msg);
                }
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
    <input name="id" type="hidden" id="id" value="${withdrawals.id}"/>
    <input name="memberId" type="hidden" id="memberId" value="${withdrawals.memberId}"/>
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">
               用户名：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="account" type="text" id="account" class="h2y_input_long"
                       value="${memberUser.account}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                提现到账号：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="drawAccount" type="text" id="drawAccount" class="h2y_input_long"
                       value="${withdrawals.drawAccount}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                姓名：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="name" type="text" id="name" class="h2y_input_long"
                       value="${withdrawals.name}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                手机号：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="mobile" type="text" id="mobile" class="h2y_input_long"
                       value="${withdrawals.mobile}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                身份证号：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="idCard" type="text" id="idCard" class="h2y_input_long"
                       value="${withdrawals.idCard}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
               提现金额：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="cashWithdrawalAmount" type="text" id="cashWithdrawalAmount" class="h2y_input_long"
                       value="${withdrawals.cashWithdrawalAmount}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
               手续费：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="fee" type="text" id="fee" class="h2y_input_long"
                       value="${withdrawals.fee}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                实提金额：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="realAmount" type="text" id="realAmount" class="h2y_input_long"
                       value="${withdrawals.realAmount}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                申请时间：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="createDate" type="text" id="createDate" class="h2y_input_long"
                value="<fmt:formatDate value="${withdrawals.createDate}"  pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                是否已打款：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <h2y:input name="drawStatus" id="drawStatus" type="radio" initoption="2,是:1,否"
                           value="${withdrawals.drawStatus}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
