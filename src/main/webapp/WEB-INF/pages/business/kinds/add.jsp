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
        var btn = document.getElementById('projectLogoUploadBut');
        $(function () {
            /*btn.disabled = "disabled";*/
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
            //选择器
            $("#ord").ligerSpinner({type: 'int', height: 25, width: 194,value:${kinds.ord}});
            $("#projectLogoUploadBut").click(function () {
                openImageUploadDialog();
            });
            if (op == "modify") {
                $("#tr_next").hide();
                $(${fileDataListJson}).each(function () {
                    var json_str = "{\"id\":\"" + this.id + "\"}";
                    if (this.dataType != 1)  {
                        if (this.ord == 2&& this.dataType == 0 &&this.type==1) {
                            $("#kindlogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                                    "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                        }
                    }
                });
            }
        });

        /**
         * logo上传的图片回调
         * @param data
         */
        function h2y_imageUploadCallBack(data) {
            if (data == null || data.length == 0) {
                return;
            }
            var imageHtml = "";
            $(data).each(function (i) {
                this['fileType'] = 0;
                var json_str = JSON.stringify(this);
                var tempurl= this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");
                if (i == 1) {
                    imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                            "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" +tempurl + "\" title=\"" + this.fileName + "\">" +
                            "<img class=\"goodsLogoImg\" src=\"common/image/view.htm?path=" + tempurl + "\"></a>";
                } else {
                    imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>";
                }
            });
            $("#kindlogo_div").html(imageHtml);
        }

        function h2y_save() {
            var name = $("#name").val();
            var parentId = $("#parentId").val();
            if(name==null||name==""){
                alert("名称不能为空");
                return;
            }

            if(parentId!=null && parentId>0){
                if (document.getElementById("kindlogo_div").innerHTML == "") {
                    alert("请上传logo图片!");
                    return;
                }
            }

            if (!Validator.form()) return;
            //queryString为form表单提交的值
            var queryString = $('#editform').serialize();
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;
            //data服务器返回数据
            $.post("business/kinds/save.htm", queryString, function (data) {
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
                            /*$("#deptName").val("");
                            $("#deptShortName").val("");
                            $("#deptDesc").val("");*/
                            $("#kindlogo_div").html("");
                            $("input[type=reset]").trigger("click");
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
        <input type="hidden" name="id" value="${kinds.id}"/>
        <input type="hidden" name="nid" value="${kinds.nid}"/>
        <input type="hidden" name="op" value="${op}"/>
        <input type="hidden" name="instId" value="${kinds.instId}"/>
        <input type="hidden" name="instCode" value="${kinds.instCode}"/>
        <input type="hidden" id="parentId" name="parentId" value="${kinds.parentId}"/>
    </div>
    <table class="h2y_dialog_table" style="width: 450px;">
        <tr>
            <td class="h2y_table_label_td">运动名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="name" type="text" id="name" class="h2y_input_just" value="${kinds.name}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">排序:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="ord" type="text" id="ord" class="h2y_input_just" value="${kinds.ord}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                <input type="button" value="上传Logo" class="button" id="projectLogoUploadBut"/>:
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="kindlogo_div"></div>
            </td>
        </tr>
        <tr id="tr_next">
            <td class="h2y_table_label_td">下一步:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="next" id="next" type="radio" initoption="1,继续添加:0,返回列表" value="1"/>
            </td>
        </tr>
    </table>
    <input type="reset" name="reset" style="display: none;" />
</form>
</body>
</html>