<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <%@ include file="../../include/top_Jcrop.jsp" %>
    <%@ include file="../../include/top_lightbox.jsp" %>
    <%@ include file="../../include/top_kindeditor.jsp" %>

    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-slide.min.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

    <style type="text/css">
        .recommendPicImg {
            width: auto;
            height: 240px;
        }
    </style>

    <script type="text/javascript">

        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        var fileId = 0;

        $(function () {

            $("#createDate").datetimepicker({});
            $("#toptoolbar").ligerToolBar({
                items: [
                    {line: true},
                    {text: '保存', click: h2y_save, icon: 'save'},
                    {line: true},
                    {text: '刷新', click: h2y_refresh, icon: 'refresh'}
                ]
            });
            if (op == "modify") {
                $("#tr_next").hide();
                $(${fileDataListJson}).each(function () {
                    var json_str = "{\"id\":\"" + this.id + "\"}";
                    if (this.dataType == 1) {
                        $("#recommendpic_div").append("<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"picData\"  value='" + json_str + "'/>" +
                                "<a id=\"file_lightbox_" + fileId + "\" class=\"recommendPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\">" +
                                "<img  id=\"file_img_" + fileId + "\" class=\"recommendPicImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>" +
                                "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                    } else {
                        if (this.ord == 2) {
                            $("#recommendlogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                                    "<a class=\"recommendLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"recommendLogoImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                        } else {
                            $("#recommendlogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>");
                        }
                    }
                    fileId++;
                });
                $(".recommendPicImg_lightbox").lightBox();
            }
            $("#activePicUploadBut").click(function () {

                openFileUploadDialog({
                    fileTypeExts: "*.jpg;*.png;*.jpeg;*.gif",
                    multi: true
                });
            });
        });

        function h2y_fileUploadCallBack(data) {
            if (data == null || data.length == 0) {
                return;
            }
            $(data).each(function () {

                this['fileType'] = 1;
                var json_value = JSON.stringify(this);
                var tempurl= this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");

                $("#recommendpic_div").append("" +
                        "<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"picData\"  value='" + json_value + "'/>" +
                        "<a id=\"file_lightbox_" + fileId + "\" class=\"recommendPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + tempurl + "\" title=\"" + this.fileName + "\">" +
                        "<img  id=\"file_img_" + fileId + "\" class=\"recommendPicImg\" src=\"common/image/view.htm?path=" + tempurl + "\">" +
                        "</a>" +
                        "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                fileId++;
            });

            $(".recommendPicImg_lightbox").lightBox();
        }

        function h2y_save() {

            var queryString = $('#editform').serialize();
            alert(queryString);
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }

            isSubmiting = true;

            //introduceEditor.sync();
            $.post("business/recommend/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_recommend_init_htm") != null) {
                            top.f_getframe("business_recommend_init_htm").f_query();
                            top.f_delTab("business_recommend_add_htm_op_modify_id_${recommend.id}");
                        }
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_recommend_init_htm") != null) {
                            top.f_getframe("business_recommend_init_htm").f_query();
                            top.f_delTab("business_recommend_add_htm_op_add_id");
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

    <input name="id" type="hidden" id="id" value="${recommend.id}"/>
    <input name="op" type="hidden" id="op" value="${op}"/>

    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">
                我有好创意：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="kind" type="text" id="kind" class="h2y_input_long"
                       value="${recommend.kind}" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                创意内容：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="context" type="text" id="context" class="h2y_input_long"
                       value="${recommend.context}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                发表时间：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="createDate" type="text" id="createDate" class="h2y_input_long"
                       value="<fmt:formatDate value='${recommend.createDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                回复：
            </td>
            <td class="h2y_table_edit_td" colspan="3">
                <input name="remart" type="text" id="remart" value="${recommend.remart}" class="h2y_input_long" />
            </td>

        </tr>

            <td class="h2y_table_edit_td2" colspan="3">
                <div id="recommendpic_div"></div>
            </td>
        </tr>

    </table>

</form>

</body>
</html>