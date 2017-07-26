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
    <script type="text/javascript">
        var Validator = null;
        var op = "${op}";
        var isSubmiting = false;
        var introduceEditor = null;
        var fileId = 0;
        $(function () {
            $("#toptoolbar").ligerToolBar({
                items: [{line: true}, {
                    text: '保存',
                    click: h2y_save,
                    icon: 'save'
                }, {line: true}, {text: '刷新', click: h2y_refresh, icon: 'refresh'}]
            });
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            $("#ord").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false,value:${subject.ord}});
            //textarea文本编辑框字体属性设置器
            KindEditor.ready(function (K) {
                introduceEditor = K.create("#remark", {
                    uploadJson: '<%=basePath%>kindeditor/uploadJson.htm',
                    afterBlur: function () {
                        this.sync();
                    }
                });
            });
            var _mobileImgData = '${mobileImgData}';
            if (_mobileImgData != null && _mobileImgData != "null" && _mobileImgData != "") {
                var mobileImgData = eval("(" + _mobileImgData + ")");
                $("#mobileImgData").val(JSON.stringify(mobileImgData));
                $("#h2y_file_div_mobile").html("<img class=\"subject_img\" src=\"" + mobileImgData.url + "\">");
            }

        });

        /**
         * logo上传的图片回调
         * @param data
         */
        function h2y_fileUploadCallBack(data) {
            if (data == null) return;

            $(data).each(function () {
                //添加属性
                this['fileType'] = fileType;
                var fileData = JSON.stringify(this);
                $("#" + fileType + "ImgData").val(fileData);

                var tempurl = this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");

                $("#h2y_file_div_" + fileType).html("<img class=\"subject_img\" src=\"" + tempurl + "\">");
            });

        }

        function h2y_fileupload(type) {

            var fileTypeExts = "*.jpg;*.png;*.jpeg;*.gif";

            openFileUploadDialog({
                fileTypeExts: fileTypeExts,
                multi: true,
                fileSizeLimit: 300,
                uploadLimit: 1
            });
            fileType = type;
        }

        function h2y_save(){
            if (!Validator.form()) return;
            //同步富文本编辑框数据
            introduceEditor.sync();
            //queryString为form表单提交的值
            var queryString = $('#editform').serialize();
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;
            //data服务器返回数据
            $.post("business/subject/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                        if (jsonReturn.code == "1") {
                            alert(jsonReturn.msg);
                            var src = "<%=basePath%>business_subject_init_htm";
                            top.f_addTab("business_subject_init_htm", "轮播图列表", src);
                            top.f_getframe("business_subject_init_htm").h2y_refresh();
                            if(op =="modify"){
                                top.f_delTab("business_activity_add_htm_op_modify_id_${subject.id}");

                            }else{
                                top.f_delTab("business_subject_add_htm");
                            }

                        } else {
                            lert(jsonReturn.msg);
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
    <div>
        <input type="hidden" name="id"  id="id" value="${subject.id}"/>
        <input type="hidden" name="instId" id="instId" value="${subject.instId}"/>
        <input type="hidden" name="instCode" id="instCode" value="${subject.instCode}"/>
        <input type="hidden" name="unitId"  id="unitId" value="${subject.unitId}"/>
        <input type="hidden" name="unitCode" id="unitCode" value="${subject.unitCode}"/>
        <input type="hidden" name="userId" id="userId" value="${subject.userId}"/>
        <input type="hidden" name="type" id="type" value="${subject.type}"/>
        <input type="hidden" id="mobileImgData" name="mobileImgData" value="${mobileImgData}"/>
        <input type="hidden" id="weixinImgData" name="weixinImgData" value="${weixinImgData}"/>
        <input type="hidden" id="androidImgData" name="androidImgData" value="${androidImgData}"/>
        <input type="hidden" id="iosImgData" name="iosImgData" value="${iosImgData}"/>
        <input type="hidden" id="pcImgData" name="pcImgData" value="${pcImgData}"/>
    </div>
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">名称：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="subjectName" type="text" id="subjectName" class="h2y_input_long"
                       value="${subject.subjectName}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">URL：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="subjectUrl" type="text" id="subjectUrl" class="h2y_input_long"
                       value="${subject.subjectUrl}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">排序：</td>
            <td class="h2y_table_edit_td2">
                <input name="ord" type="text" id="ord" class="h2y_input_just"
                       value="${subject.ord}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">描述：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <textarea name="remark" id="remark"
                       class="h2y_editor_textarea">${subject.remark}</textarea>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                <input type="button" value="Mobile图片" class="button"
                       onclick="h2y_fileupload('mobile')"/>:
            </td>
            <td class="h2y_table_edit_td2">
                <div id="h2y_file_div_mobile"></div>
            </td>
<%--        <tr>
            <td class="h2y_table_label_td">
                <input type="button" value="上传logo" class="button" id="subjectLogoUploadBut"/>:
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="subjectlogo_div"></div>
            </td>
        </tr>--%>
    </table>
</form>
</body>
</html>