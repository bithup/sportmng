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

            var withdrawalsType='${withdrawalsRate.withdrawalsType}';
            if(withdrawalsType){
                $("input[name=allocationType][value="+withdrawalsType+"]").attr("checked",true);
            }
            if(withdrawalsType==1){
                $("#trProportion").css("display","table-row");
                $("#trOrderFee").css("display","none");
            } else{
                $("#trProportion").css("display","none");
                $("#trOrderFee").css("display","table-row");
            }
            $("input:radio[name=withdrawalsType]").change(function () {
                var withdrawalsType = $(this).val();
                if(withdrawalsType==1){
                    $("#trProportion").css("display","table-row");
                    $("#trOrderFee").css("display","none");
                }else{
                    $("#trProportion").css("display","none");
                    $("#trOrderFee").css("display","table-row");
                }
            });
            var op=$("#op").val();
            //操作是新增,设置佣金方式默认值
            if(op=="add"){
                $("input[name=withdrawalsType][value='1']").click();
            }
        });

        function h2y_save() {
            var withdrawalsType = $("input[name='withdrawalsType']:checked").val();
            var enableStatus = $("input[name='enableStatus']:checked").val();
            var withdrawalsPropor = $("#withdrawalsPropor").val();
            var withdrawalsMoney = $("#withdrawalsMoney").val();
            if(withdrawalsType==""){
                alert("请选择提现费计算方式");
                return;
            }
            if(withdrawalsType==1){
                if(withdrawalsPropor==""){
                    alert("请输入提现费比例");
                    return;
                }
            }
            if(withdrawalsType==2){
                if(withdrawalsMoney==""){
                    alert("请输入每笔提现费金额");
                    return;
                }
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
            $.post("sport/withdrawalsRate/save.htm", queryString, function (data) {
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
        <input type="hidden" name="id" value="${withdrawalsRate.id}"/>
        <input type="hidden" id="op"  name="op" value="${op}"/>
    </div>
    <table class="h2y_dialog_table" style="width: 560px;">
        <tr>
            <td class="h2y_table_label_td" style="width: 99px;">提现费计算方式:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3"  style="width: 460px;">
                <h2y:input name="withdrawalsType" id="withdrawalsType" type="radio" initoption="1,按百分比例:2,按每笔提现费金额" value="${withdrawalsRate.withdrawalsType}"/>
            </td>
        </tr>
        <tr id="trProportion" >
            <td class="h2y_table_label_td" style="width: 99px;">比例计算:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3"  style="width: 460px;">
                提现费占比例<input name="withdrawalsPropor" type="text" id="withdrawalsPropor" class="h2y_input_short" value="${withdrawalsRate.withdrawalsPropor}"/>%
            </td>
        </tr>
        <tr id="trOrderFee">
            <td class="h2y_table_label_td" style="width: 99px;">每笔提现金额:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3"  style="width: 460px;">
                每笔提现费<input name="withdrawalsMoney" type="text" id="withdrawalsMoney" class="h2y_input_short" value="${withdrawalsRate.withdrawalsMoney}"/>元
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td" style="width: 99px;">是否启用:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3"  style="width: 460px;">
                <h2y:input name="enableStatus" id="enableStatus" type="radio" initoption="1,启用:2,未启用" value="${withdrawalsRate.enableStatus}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>