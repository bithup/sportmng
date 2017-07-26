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
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var introduceEditor = null;
        var fileId = 0;
        var kindsLevel = 0;
        var parentId = 0;
        var preCode = "${preCode}";
        var zoneLevel = 0;
        var parFix = "${parFix}";
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
            /*$("#activityPrice").ligerSpinner({type: 'float', height: 25, width: 150, isNegative: false,value:${activity.activityPrice}});*/
            $("#activityCount").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false, value:${activity.activityCount}});
            $("#ord").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false, value:${activity.ord}});
            $("#startDate").datetimepicker({});
            $("#endDate").datetimepicker({});
            $("#enrollDate").datetimepicker({});
            //textarea文本编辑框字体属性设置器
           KindEditor.ready(function (K) {
                introduceEditor = K.create("#declares", {
                    pasteType:1,
                    uploadJson: '<%=basePath%>kindeditor/uploadJson.htm',
                    afterBlur: function () {
                        this.sync();
                    }
                });
            });
            KindEditor.ready(function (K) {
                introduceEditor = K.create("#activityIntroduce", {
                    pasteType:1,
                    uploadJson: '<%=basePath%>kindeditor/uploadJson.htm',
                    afterBlur: function () {
                        this.sync();
                    }
                });
            });

            //图片显示
            if (op == "modify") {
                $("#tr_next").hide();
                var preCode_array = preCode.split("_");
                modifySelect(preCode_array);
                modifyVenueSelect(1,${activity.venueId});

                $(${fileDataListJson}).each(function () {
                    var json_str = "{\"id\":\"" + this.id + "\"}";
                    if (this.dataType == 1) {
                        $("#activitypic_div").append("<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"picData\"  value='" + json_str + "'/>" +
                                "<a id=\"file_lightbox_" + fileId + "\" class=\"activityPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\">" +
                                "<img  id=\"file_img_" + fileId + "\" class=\"activityPicImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>" +
                                "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                    } else {
                        if (this.ord == 2) {
                            $("#activitylogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                                    "<a class=\"activityLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"activityLogoImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                        } else {
                            $("#activitylogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>");
                        }
                    }
                    fileId++;
                });
                $(".activityPicImg_lightbox").lightBox();
                $(".activityLogoImg_lightbox").lightBox();

                var preFix_array = parFix.split("_");
                modifySelectZone(preFix_array);

                var all_options = document.getElementById("isFree").options;
                for (i=0; i<all_options.length; i++){
                    if (all_options[i].value == "${activity.isFree}")  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
                    {
                        all_options[i].selected = true;
                    }
                }

            }else {
                addSelect("0", "");
                addSelectzone("200000", "");//省市区三级分类选择
                venueSelect(1);
            }
            $("#activityLogoUploadBut").click(function () {
                openImageUploadDialog();
            });
            $("#activityPicUploadBut").click(function () {
                openFileUploadDialog({
                    fileTypeExts: "*.jpg;*.png;*.jpeg;*.gif",
                    multi: true
                });
            });

            $("#isFree").change(function(){
                var isFree = $("#isFree option:selected").val();
                if(isFree=='1'||isFree=='2'){
                    $("#activityPrice").attr("readonly",true);
                }else if(isFree=='0'){
                    $("#activityPrice").attr("readonly",false);
                }
            });
        });


        function addSelectzone(parentId, obj) {
            $.post("/business/activity/getListZone.htm?parentId=" + parentId, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (zoneLevel == 4) {
                    $("#zoneCode").val(parentId);
                }
                if (jsonReturn.length == 0) {
                    $("#zoneId").val(parentId);
                }
                var selectHtml = "";
                //遍历json字符串,把id和对应的菜单名字展示到下拉框
                $(jsonReturn).each(function () {
                    selectHtml += "<option value=\"" + this.id + "\">" + this.text + "</option>";
                });
                if (selectHtml != "") {
                    selectHtml = "<select id=\"zone_select_" + zoneLevel + "\" class=\"zone_select\">" +
                            "<option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#zone_td").append(selectHtml + "");
                    if (obj != "") {
                        h2y_do_changezone(obj.next());
                    } else {
                        h2y_do_changezone($("#zone_select_" + zoneLevel));
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
            $.post("/business/activity/getListZone.htm", {parentId: parentId2}, function (data) {
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
                    selectHtml = "<select id=\"zone_select_" + zoneLevel + "\" class=\"zone_select\"><option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#zone_td").append(selectHtml + "");
                    h2y_do_changezone($("#zone_select_" + zoneLevel));
                    if (zoneLevel <= preCode_array.length) {
                        modifySelectZone(preCode_array, zoneLevel);
                    }
                }
            });
        }

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
                            "<a class=\"activityLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" +tempurl + "\" title=\"" + this.fileName + "\">" +
                            "<img class=\"activityLogoImg\" src=\"common/image/view.htm?path=" + tempurl + "\"></a>";
                } else {
                    imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>";
                }
            });
            $("#activitylogo_div").html(imageHtml);
            $(".activityLogoImg_lightbox").lightBox();
        }
        function h2y_fileUploadCallBack(data) {
            if (data == null || data.length == 0) {
                return;
            }
            $(data).each(function () {
                var tempurl = this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");
                this['fileType'] = 1;
                var json_value = JSON.stringify(this);
                $("#activitypic_div").append("" +
                        "<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"picData\"  value='" + json_value + "'/>" +
                        "<a id=\"file_lightbox_" + fileId + "\" class=\"activityPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + tempurl + "\" title=\"" + this.fileName + "\">" +
                        "<img  id=\"file_img_" + fileId + "\" class=\"activityPicImg\" src=\"common/image/view.htm?path=" + tempurl + "\">" +
                        "</a>" +
                        "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                fileId++;
            });
            $(".activityPicImg_lightbox").lightBox();
        }
        function deletePic(fileId) {
            if (!confirm("您确定要删除文件吗?")) {
                return true;
            }
            $("#file_input_" + fileId).remove();
            $("#file_lightbox_" + fileId).remove();
            $("#file_img_" + fileId).remove();
            $("#file_delete_href_" + fileId).remove();
        }

        //运动分类
        function addSelect(parentId, obj) {
            $.post("business/kinds/getChildData.htm?parentId=" + parentId, function (data) {
                var jsonReturn = eval("(" + data + ")");
                $("#sportId").val(parentId);
                var selectHtml = "";
                $(jsonReturn).each(function () {
                    selectHtml += "<option value=\"" + this.id + "\">" + this.name + "</option>";
                });
                if (selectHtml != "") {
                    selectHtml = "<select id=\"sportType_select" + kindsLevel + "\" class=\"goodstype_selectgoodstype_select\">" +
                            "<option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#sportId_td").append(selectHtml + "");
                    if (obj != "") {
                        h2y_do_change(obj.next());
                    } else {
                        h2y_do_change($("#sportType_select" + kindsLevel));
                    }
                }
                kindsLevel++;
            });
        }
        function getTypeId(code) {
            var index = code.lastIndexOf("_");
            if (index == -1) return code;
            return code.substring(index + 1, code.length);
        }
        function h2y_do_change(obj) {
            obj.change(function () {
                $(this).nextAll().remove();
                addSelect(getTypeId(this.value), $(this));
            });
        }
        /**
         * 修改时获取运动分类
         * @param preCode_array
         */
        function modifySelect(preCode_array) {
            var parentId2 = kindsLevel == 0 ? 0 : preCode_array[kindsLevel - 1];
            $.post("business/kinds/getChildData.htm", {parentId:parentId2}, function (data) {
                kindsLevel++;
                var jsonReturn = eval("(" + data + ")");
                var selectHtml = "";
                $(jsonReturn).each(function () {
                    if (preCode_array[kindsLevel - 1] == this.id) {
                        selectHtml += "<option value=\"" + this.id + "\" selected=\"selected\">" + this.name + "</option>";
                    } else {
                        selectHtml += "<option value=\"" + this.id + "\">" + this.name + "</option>";
                    }
                });
                if (selectHtml != "") {
                    selectHtml = "<select id=\"sportType_select" + kindsLevel + "\" class=\"goodstype_selectgoodstype_select\"><option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#sportId_td").append(selectHtml + "");
                    h2y_do_change($("#sportType_select" + kindsLevel));
                    if (kindsLevel <= preCode_array.length) {
                        modifySelect(preCode_array, kindsLevel);
                    }
                }
            });
        }

        /**
         * 场馆分类
         *
         * */
        function venueSelect(instId) {
            $.post("/sport/venue/getVenue.htm?instId="+instId, function (data) {
                var jsonReturn = eval("(" + data + ")");
                var selectHtml = "<option value=\"0\"></option>";
                /*var selectHtml = "";*/
                $(jsonReturn).each(function (){
                    selectHtml += "<option value=\"" + this.id + "\">" + this.name + "</option>";
                });
                if (selectHtml != "") {
                    $("#venueId").append(selectHtml + "");
                }
            });
        }

        function modifyVenueSelect(instId,venueId){
            $.post("/sport/venue/getVenue.htm?instId="+instId, function (data) {
                var jsonReturn = eval("(" + data + ")");
                var selectHtml = "<option value=\"0\"></option>";
                //var selectHtml ="";
                $(jsonReturn).each(function (){
                    if(this.id == venueId){
                        selectHtml += "<option value=\"" + this.id + "\" selected=\"selected\">" + this.name + "</option>";
                    }else{
                        selectHtml += "<option value=\"" + this.id + "\">" + this.name + "</option>";
                    }
                });
                if (selectHtml != "") {
                    $("#venueId").append(selectHtml + "");
                }
            });
        }

        function h2y_save() {
            var activityName=$("#activityName").val();
            var activityOrganizer=$("#activityOrganizer").val();
            var activityCount=$("#activityCount").val();
            var zone = $("#zoneId").val();
            var activityAddress=$("#activityAddress").val();
            var sportId=$("#sportId").val();
            //var venueId=$("#venueId").val();
            var activityIntroduce=$("#activityIntroduce").val();
            var activityContacts=$("#activityContacts").val();
            var contactsPhone=$("#contactsPhone").val();
            var startDate=$("#startDate").val();
            var endDate=$("#endDate").val();
            var enrollDate=$("#enrollDate").val;
            var isFree = $("#isFree option:selected").val();
            var activityPrice = $("#activityPrice").val();

            if(null==activityName||""===activityName){
                alert("请填写活动名称");
                return;
            }
            if(null==zone||""===zone){
                alert("请选择活动所属区域");
                return;
            }
            if(null==activityAddress||""===activityAddress){
                alert("请填写活动详细地址");
                return;
            }
            if(null==sportId||""===sportId){
                alert("请选择活动类型");
                return;
            }
            if(null==activityIntroduce||""===activityIntroduce){
                alert("请填写活动介绍");
                return;
            }
            if(null==activityContacts||""===activityContacts){
                alert("请填写活动联系人");
                return;
            }
            if(null==contactsPhone||""===contactsPhone){
                alert("请填写联系电话");
                return;
            }
            if(null==startDate||""===startDate){
                alert("请选择活动开始时间");
                return;
            }

            if(null==isFree || ""==isFree){
                alert("请选择是否收费");
                return;
            }


            if(0==isFree ){
                if(null==activityPrice||""==activityPrice ||parseFloat(activityPrice)<=0){
                    alert("收费活动的价格需大于0！");
                    return;
                }
            }

            if(null==endDate||""===endDate){
                alert("请选择活动结束时间");
                return;
            }
            if(null==enrollDate||""===enrollDate){
                alert("请选择报名截止日期");
                return;
            }
            if (startDate>endDate){
                alert("开始时间不能迟于结束时间");
                return;
            }
            /*if (enrollDate>endDate){
                alert("报名截止日期不能迟于结束时间");
                return;
            }*/
            if(document.getElementById("activitylogo_div").innerHTML==''){
                alert("请上传logo");
                return;
            }
            if(document.getElementById("activitypic_div").innerHTML==''){
                alert("请上传图片");
                return;
            }

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
            $.post("business/activity/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        var src = "<%=basePath%>business_activity_init_htm";
                        top.f_addTab("business_activity_init_htm", "活动列表", src);
                        top.f_getframe("business_activity_init_htm").h2y_refresh();
                        top.f_delTab("business_activity_add_htm_op_modify_id_${activity.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        var src = "<%=basePath%>business_activity_init_htm";
                        top.f_addTab("business_activity_init_htm", "活动列表", src);
                        top.f_getframe("business_activity_init_htm").h2y_refresh();
                        top.f_delTab("business_activity_add");
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

<input type="hidden" name="parentId" value="${parentId}"/>
<form name="editform" method="post" action="" id="editform">
    <div>
        <input name="id" type="hidden" id="id" value="${activity.id}"/>
        <input name="unitId" type="hidden" id="unitId" value="${activity.unitId}"/>
        <input type="hidden" name="sportId" id="sportId" value="${activity.sportId}"/>
        <input type="hidden" name="data1" id="zoneId" value="${activity.data1}"/>
        <input type="hidden" name="data9" id="zoneCode" value="${activity.data9}"/>
        <input name="op" type="hidden" id="op" value="${op}"/>
    </div>
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">活动名称：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="activityName" type="text" id="activityName" class="h2y_input_long"
                       value="${activity.activityName}"/>
            </td>
        </tr>

        <tr>
           <%-- <td class="h2y_table_label_td">活动类型：</td>
            <td class="h2y_table_edit_td">
                <h2y:input name="activityType" id="activityType" type="radio" initoption="0,个人:1,团体" value="${activity.activityType}"/>
            </td>--%>
            <td class="h2y_table_label_td">活动主办方：</td>
            <td class="h2y_table_edit_td" colspan="3">
                <input name="activityOrganizer" type="text" id="activityOrganizer" class="h2y_input_just" value="${activity.activityOrganizer}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">活动人数：</td>
            <td class="h2y_table_edit_td">
                <input name="activityCount" type="text" id="activityCount" class="h2y_input_just"
                       value="${activity.activityCount}"/>
            </td>
            <td class="h2y_table_label_td">是否加入热门活动：</td>
            <td class="h2y_table_edit_td">
                <h2y:input name="isRecommend" id="isRecommend" type="radio" initoption="0,否:1,是" value="${activity.isRecommend}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                活动所属区域：
            </td>
            <td class="h2y_table_edit_td2" colspan="3" id="zone_td">
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">活动详细地址：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="activityAddress" type="text" id="activityAddress" class="h2y_input_long"
                       value="${activity.activityAddress}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">运动项：</td>
            <td class="h2y_table_edit_td" id="sportId_td">
            </td>
            <td class="h2y_table_label_td">场馆：</td>
            <td class="h2y_table_edit_td" >
                <select name="venueId" id="venueId">
                </select>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">收费方式：</td>
            <td class="h2y_table_edit_td" >
                <select name="isFree" id="isFree">
                    <option value="">请选择</option>
                    <option value="1">免费</option>
                    <option value="0">收费</option>
                    <option value="2">AA制</option>
                </select>
            </td>
             <td class="h2y_table_label_td">活动费用：</td>
             <td class="h2y_table_edit_td" >
                 <input name="activityPrice" type="text" id="activityPrice" class="h2y_input_just"
                        value="${activity.activityPrice}"/>
             </td>
         </tr>

         <tr>
             <td class="h2y_table_label_td">活动联系人：</td>
             <td class="h2y_table_edit_td" >
                 <input name="activityContacts" type="text" id="activityContacts" class="h2y_input_just"
                        value="${activity.activityContacts}"/>
             </td>
             <td class="h2y_table_label_td">联系人电话：</td>
             <td class="h2y_table_edit_td" >
                 <input name="contactsPhone" type="text" id="contactsPhone" class="h2y_input_just"
                        value="${activity.contactsPhone}"/>
             </td>
         </tr>

         <tr>
              <td class="h2y_table_label_td">活动开始时间：</td >
              <td class="h2y_table_edit_td">
                  <input name="startDate" type="text" id="startDate" class="h2y_input_just"
                         value="<fmt:formatDate value="${activity.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" pattern="yyyy-MM-dd HH:mm"/>
              </td>
              <td class="h2y_table_label_td">活动结束时间：</td>
              <td class="h2y_table_edit_td">
                  <input name="endDate" type="text" id="endDate" class="h2y_input_just"
                         value="<fmt:formatDate value="${activity.endDate}"  pattern="yyyy-MM-dd HH:mm:ss"/>" pattern="yyyy-MM-dd HH:mm"/>
              </td>
          </tr>

          <tr>
              <td class="h2y_table_label_td">报名截止时间：</td>
              <td class="h2y_table_edit_td">
                  <input name="enrollDate" type="text" id="enrollDate" class="h2y_input_just"
                         value="<fmt:formatDate value="${activity.enrollDate}"  pattern="yyyy-MM-dd HH:mm:ss"/>" pattern="yyyy-MM-dd HH:mm"/>
              </td>
              <td class="h2y_table_label_td">排序：</td>
              <td class="h2y_table_edit_td">
                  <input name="ord" type="text" id="ord" class="h2y_input_just"
                         value="${activity.ord}"/>
              </td>
          </tr>
            <tr>
                <td class="h2y_table_label_td">活动介绍：</td>
                <td class="h2y_table_edit_td2" colspan="3">
                        <textarea name="activityIntroduce" id="activityIntroduce"
                                  class="h2y_editor_textarea">${activity.activityIntroduce}</textarea>
                </td>
           </tr>
          <tr>
              <td class="h2y_table_label_td">活动申明：</td>
              <td class="h2y_table_edit_td2" colspan="3">
                  <textarea name="declares" id="declares"
                            class="h2y_editor_textarea">${activity.declares}</textarea>
              </td>
          </tr>

        <tr>
            <td class="h2y_table_label_td">
                <input type="button" value="上传logo" class="button" id="activityLogoUploadBut"/>:
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="activitylogo_div"></div>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                <input type="button" value="上传图片" class="button" id="activityPicUploadBut"/>：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="activitypic_div"></div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>