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

    <script type="text/javascript">
        var isSubmiting = false;
        var form = null;
        var zoneLevel = 0;
        var op = "${op}";
        //文件上传
        var fileType = null;//文件上传的标识

        var parFix = "${parFix}";

        $(function () {
            $("#toptoolbar").ligerToolBar({
                items: [
                    {line: true},
                    {text: '保存', click: h2y_save, icon: 'save'},
                    {line: true},
                    {text: '刷新', click: h2y_refresh, icon: 'refresh'}
                ]
            });

            $("#idCard").change(function(){
                var idCard = $("#idCard").val();
                checkIdCard(idCard);
            });
            $("#account").change(function(){
                var account = $("#account").val();
                checkAccount(account);
            });

            if(op=="modify"){
                var preFix_array = parFix.split("_");
                modifySelectZone(preFix_array);

                $(${fileDataListJson}).each(function () {
                    var json_str = "{\"id\":\"" + this.id + "\"}";
                    if (this.type == 0 && this.ord==2 && this.dataType==0) {
                        $("#h2y_file_div_logoPic").append("<input type=\"hidden\" name=\"logoPicData\"  value='" + json_str + "'/>" +
                                "<a class=\"logoPicImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"logoPicImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                    } else {
                        $("#h2y_file_div_logoPic").append("<input type=\"hidden\" name=\"logoPicData\"  value='" + json_str + "'/>");
                    }
                });
                $(".logoPicImg_lightbox").lightBox();

            }else{
                addSelectzone("200000", "");
            }

        });

        function checkIdCard(idCard){
            var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
            if(!reg.test(idCard)){
                alert("请输入正确的身份证号");
                return false;
            }
        }

        function checkAccount(account){
            var reg = /^(13[0-9]{9})|(15[89][0-9]{8})$/
            if(!reg.test(account)){
                alert("请填写正确的手机号码！");
                return false;
            }
            $.post("sport/MemberUser/checkMember.htm?account="+account, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if(jsonReturn.success){
                    return true;
                }else {
                    alert(jsonReturn.msg);
                    return false;
                }
            });
        }

        function addSelectzone(parentId, obj) {
            $.post("sport/MemberUser/getListZone.htm?parentId=" + parentId, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (zoneLevel == 4) {
                    $("#zoneCode").val(parentId);
                }
                if (jsonReturn.length == 0) {
                    $("#data2").val(parentId);
                }
                var selectHtml = "";
                //遍历json字符串,把id和对应的菜单名字展示到下拉框
                $(jsonReturn).each(function () {
                    selectHtml += "<option value=\"" + this.id + "\">" + this.text + "</option>";
                });
                if (selectHtml != "") {
                    selectHtml = "<select id=\"teacherzone_select_" + zoneLevel + "\" class=\"teachertype_select\">" +
                            "<option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#zone_td").append(selectHtml + "");
                    if (obj != "") {
                        h2y_do_changezone(obj.next());
                    } else {
                        h2y_do_changezone($("#teacherzone_select_" + zoneLevel));
                    }
                }
                zoneLevel++;
            });
        }
        function h2y_do_changezone(obj) {
            obj.change(function () {
                $(this).nextAll().remove();
                addSelectzone(this.value, $(this));
            });
        }

        /**
         * 修改时获取场馆区域
         * @param
         */
        function modifySelectZone(preCode_array) {
            var parentId2 = zoneLevel == 0 ? 0 : preCode_array[zoneLevel - 1];
            $.post("sport/MemberUser/getListZone.htm", {parentId: parentId2}, function (data) {
                zoneLevel++;
                var jsonReturn = eval("(" + data + ")");
                var selectHtml = "";
                $(jsonReturn).each(function () {
                    if (preCode_array[zoneLevel - 1] == this.id) {
                        selectHtml += "<option value=\"" + this.id + "\" selected=\"selected\">" + this.text + "</option>";
                    } else {
                        selectHtml += "<option value=\"" + this.id + "\">" + this.text + "</option>";
                    }
                });

                if (selectHtml != "") {
                    selectHtml = "<select id=\"venuezone_select_" + zoneLevel + "\" class=\"venuezone_select\"><option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#zone_td").append(selectHtml + "");
                    h2y_do_changezone($("#venuezone_select_" + zoneLevel));
                    if (zoneLevel <= preCode_array.length) {
                        modifySelectZone(preCode_array, zoneLevel);
                    }
                }
            });
        }

        function h2y_save(){
            var account = $("#account").val();
            var idCard = $("#idCard").val();
            var idCardReg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
            if(idCard.length>0){
                if(!idCardReg.test(idCard)){
                    alert("请输入正确的身份证号");
                    return;
                }
            }
            if(account.length<1){
                alert("请输入用户名");
                return;
            }
            var telReg = /^(13[0-9]{9})|(15[89][0-9]{8})$/
            if(!telReg.test(account)){
                alert("请填写正确的用户名！");
                return;
            }
            if(op=="add"){
                $.post("sport/MemberUser/checkMember.htm?account="+account, function (data) {
                    var jsonReturn = eval("(" + data + ")");
                    if(jsonReturn.success){
                        return true;
                    }else {
                        alert(jsonReturn.msg);
                        return;
                    }
                });

            }

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }

            isSubmiting = true;

            $.post("sport/MemberUser/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code==1) {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("sport_MemberUser_init_htm") != null) {
                            top.f_getframe("sport_MemberUser_init_htm").f_query();
                            top.f_delTab("sport_MemberUser_add_htm_op_modify_id_${memberUser.id}");
                        }

                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code==1) {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("sport_MemberUser_init_htm") != null) {
                            top.f_getframe("sport_MemberUser_init_htm").f_query();
                            top.f_delTab("sport_MemberUser_add_htm_op_add");
                        }
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });

        }
        function h2y_refresh(){
            window.location.reload();
        }


        function h2y_fileupload(type) {

            var fileTypeExts = "*.jpg;*.png;*.jpeg;*.gif";

            openImageUploadDialog({
                fileTypeExts: fileTypeExts,
                multi: true,
                fileSizeLimit: 200,
                uploadLimit: 1
            });
            fileType = type;
        }


        function h2y_imageUploadCallBack(data) {

            if (data == null) return;
            var imageHtml = "";
            $(data).each(function (i) {

                //添加属性
                this['fileType'] = 0;
                var json_str = JSON.stringify(this);
                var tempurl = this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");
                if (i == 1) {
                    imageHtml += "<input type=\"hidden\" name=\""+fileType+"Data\"  value='" + json_str + "'/>" +
                            "<a class=\""+fileType+"Img_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + tempurl + "\" title=\"" + this.fileName + "\">" +
                            "<img class=\""+fileType+"\" src=\"common/image/view.htm?path=" +
                            tempurl + "\"></a>";
                } else {
                    imageHtml += "<input type=\"hidden\" name=\""+fileType+"Data\"  value='" + json_str + "'/>";
                }
            });
            $("#h2y_file_div_"+fileType+"").html(imageHtml);
            $("."+fileType+"Img_lightbox").lightBox();
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
<input type="hidden" name="parentId" value="${parentId}"/>
<form name="editform" method="post" action="" id="editform">
    <input type="hidden" name="id" value="${memberUser.id}"/>
    <input type="hidden" name="zoneCode" id="zoneCode" value="${memberUser.zoneCode}"/>
    <input type="hidden" name="zoneId" id="data2" value="${memberUser.zoneId}"/>
    <input type="hidden" id="op" name="op" value="${op}"/>
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">
                用户名(必填)：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="account" type="text" id="account" class="h2y_input_long" placeholder="手机号" value="${memberUser.account}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                昵称：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="nickName" type="text" id="nickName" class="h2y_input_long" value="${memberUser.nickName}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                真实姓名：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="realName" type="text" id="realName" class="h2y_input_long" value="${memberUser.realName}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                性别：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <h2y:input name="sex" id="sex" type="radio" initoption="2,女:1,男"
                           value="${memberUser.sex}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                身份证号：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="idCard" type="text" id="idCard" class="h2y_input_long" value="${memberUser.idCard}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                地区：
            </td>
            <td id="zone_td" class="h2y_table_edit_td2" colspan="3">
        </tr>
        <tr>
                <td class="h2y_table_label_td">
                    <input type="button" value="上传头像" class="button"
                           onclick="h2y_fileupload('logoPic')"/>:
                </td>
                <td class="h2y_table_edit_td2">
                    <div id="h2y_file_div_logoPic"></div>
                </td>
        </tr>
    </table>


</form>

</body>
</html>
