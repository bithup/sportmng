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
        .zone_code_select {
            border: 1px solid #aecaf0;
            height: 25px;
            line-height: 25px;
        }

        .licensePic {
            max-width: 400px;
        }

        .venuePic {
            max-width: 400px;
        }

        .venuePics {
            max-width: 400px;
        }
    </style>

    <script type="text/javascript">
        var isSubmiting = false;
        var form = null;
        var zoneLevel = 0;
        var op = "${op}";
        var parFix = "${parFix}";
        var startTime = "${venue.startTime}";
        var endTime = "${venue.endTime}";
        var fileId = 0;
        //文件上传
        var fileType = null;//文件上传的标识
        var introduceEditor = null;

        $(function () {
            $("#toptoolbar").ligerToolBar({items: [
                {line: true},
                {text: '保存', click: h2y_save, icon: 'save'},
                {line: true},
                {text: '刷新', click: h2y_refresh, icon: 'refresh'}
            ]});

            /*$("#startTime").datetimepicker({});
            $("#endTime").datetimepicker({});*/



            KindEditor.ready(function (K) {
                introduceEditor = K.create("#introduction", {
                    pasteType: 1,
                    uploadJson: '<%=basePath%>kindeditor/uploadJson.htm',
                    afterBlur: function () {
                        this.sync();
                    }
                });
            });

            $("#refundDeadline").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false});

            $("#mobile").change(function(){
                var account = $("#mobile").val();
                checkAccount(account);
            });

            if(op=="modify"){
                $("#refundDeadline").val(${venue.refundDeadline});

                var preFix_array = parFix.split("_");
                modifySelectZone(preFix_array);

                $(${fileDataListJson}).each(function () {
                    var json_str = "{\"id\":\"" + this.id + "\"}";
                    if (this.dataType == 1) {

                        $("#div_venuePics").append("<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"venuePicsData\"  value='" + json_str + "'/>" +
                                "<a id=\"file_lightbox_" + fileId + "\" class=\"venuePicsImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\">" +
                                "<img  id=\"file_img_" + fileId + "\" class=\"venuePicsImg\" src=\"" + this.url + "\"></a>" +
                                "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                    } else {

                        if (this.type == 1 && this.ord==2 && this.dataType==0) {
                            $("#div_licensePic").append("<input type=\"hidden\" name=\"licensePicData\"  value='" + json_str + "'/>" +
                                    "<a class=\"licensePicImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"licensePicImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                        } else {
                            $("#div_licensePic").append("<input type=\"hidden\" name=\"div_licensePicData\"  value='" + json_str + "'/>");
                        }

                        if (this.ord == 2 && this.type==2 ) {
                            $("#div_venuePic").append("<input type=\"hidden\" name=\"venuePicData\"  value='" + json_str + "'/>" +
                                    "<a class=\"venuePicImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"venuePicImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                        } else {
                            $("#div_venuePic").append("<input type=\"hidden\" name=\"venuePicData\"  value='" + json_str + "'/>");
                        }

                    }
                    fileId++;

                });
                $(".venuePics_lightbox").lightBox();
                $(".venuePic_lightbox").lightBox();
                $(".licensePic_lightbox").lightBox();


                var startoptions = '<option value=""></option>';
                var endoptions = '<option value=""></option>';
                for(var i=0;i<=23;i++){
                    if(startTime==i){
                        startoptions += '<option value="'+ i+'" selected="selected">'+ i+'点</option>';
                    }else{
                        startoptions += '<option value="'+ i+'">'+ i+'点</option>';
                    }
                    if(endTime==i){
                        endoptions += '<option value="'+ i+'" selected="selected">'+ i+'点</option>';
                    }else{
                        endoptions += '<option value="'+ i+'">'+ i+'点</option>';
                    }
                }
                $("#startTime").html(startoptions);
                $("#endTime").html(endoptions);

            }else{
                addSelectzone("200000", "");//省市区三级分类选择

                var startoptions = '<option value=""></option>';
                var endoptions = '<option value=""></option>';
                for(var i=0;i<=23;i++){
                    startoptions += '<option value="'+ i+'">'+ i+'点</option>';
                    endoptions += '<option value="'+ i+'">'+ i+'点</option>';
                }
                $("#startTime").html(startoptions);
                $("#endTime").html(endoptions);
            }


            $("#startTime").change(function(){
                var startTimeVal = $("#startTime option:selected").val();
                var options = '<option value=""></option>';
                for(var i = startTimeVal;i<24;i++){
                    if(endTime==i){
                        options += '<option value="'+ i+'" selected="selected">'+ i+'点</option>';
                    }else{
                        options += '<option value="'+ i+'">'+ i+'点</option>';
                    }
                }
                $("#endTime").html(options);

            });


        });



        function checkAccount(account){
            var reg = /^1[3|5|7|8|][0-9]{9}$/
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
            $.post("/sport/venue/getListZone.htm?parentId=" + parentId, function (data) {
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
                    selectHtml = "<select id=\"venuezone_select_" + zoneLevel + "\" class=\"venuezone_select\">" +
                            "<option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#zone_td").append(selectHtml + "");
                    if (obj != "") {
                        h2y_do_changezone(obj.next());
                    } else {
                        h2y_do_changezone($("#venuezone_select_" + zoneLevel));
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
            $.post("/sport/venue/getListZone.htm", {parentId: parentId2}, function (data) {
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

        function h2y_fileuploadMany(type) {

            var fileTypeExts = "*.jpg;*.png;*.jpeg;*.gif";

            openFileUploadDialog({
                fileTypeExts: fileTypeExts,
                multi: true,
                fileSizeLimit: 200,
                uploadLimit: 8
            });
            fileType = type;
        }

        function h2y_fileuploadLicense(type) {
            openImageUploadDialogLicense();
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
            $("#div_"+fileType+"").html(imageHtml);
            $("."+fileType+"Img_lightbox").lightBox();
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
                $("#div_"+fileType+"").append("" +
                        "<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\""+fileType+"Data\"  value='" + json_value + "'/>" +
                        "<a id=\"file_lightbox_" + fileId + "\" class=\""+fileType+"Img_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?path=" + tempurl + "\" title=\"" + this.fileName + "\">" +
                        "<img  id=\"file_img_" + fileId + "\" class=\""+fileType+"\" src=\"common/image/view.htm?path=" + tempurl + "\">" +
                        "</a>" +
                        "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                fileId++;
            });
            $("."+fileType+"Img_lightbox").lightBox();
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

        function h2y_save(){
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }

            var zoneId = $("#zoneId").val();
            var name = $("#name").val();
            var address = $("#address").val();
            var telephone = $("#telephone").val();
            var mobile = $("#mobile").val();
            var contact = $("#contact").val();
            var introduction = $("#introduction").val();
            var businessTime = $("#businessTime").val();
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var licenseNo = $("#licenseNo").val();
            var artificilPerson = $("#artificilPerson").val();

            var telReg = /^1[3|5|7|8|][0-9]{9}$/

            if(null==zoneId||''===zoneId){
                alert("请选择场馆所在区域");
                return;
            }
            if(null==name||''===name){
                alert("请填写场馆名称");
                return;
            }

            if(null==artificilPerson||''===artificilPerson){
                alert("请填写法人");
                return;
            }
            if(null==address||''===address){
                alert("请填写场馆详细地址");
                return;
            }
            if(null==telephone||''===telephone){
                alert("请填写场馆电话");
                return;
            }
            if(null==mobile||''===mobile){
                alert("请填写场馆手机号");
                return;
            }else if(!telReg.test(mobile)){
                    alert("请填写正确的手机号");
                    return;
            }

            if(null==contact||''===contact){
                alert("请填写场馆联系人");
                return;
            }
            if(null==introduction||''===introduction){
                alert("请填写场馆简介");
                return;
            }
           /* if(null==businessTime||''===businessTime){
                alert("请填写场馆营业时间");
                return;
            }*/
            if(null==startTime||''===startTime){
                alert("请填写开始时间");
                return;
            }
            if(null==endTime||''===endTime){
                alert("请填写结束时间");
                return;
            }
            if(null==licenseNo||''===licenseNo){
                alert("请填写营业执照号");
                return;
            }
            if(document.getElementById("div_licensePic").innerHTML==''){
                alert("请上传营业执照");
                return;
            }
            if(document.getElementById("div_venuePic").innerHTML==''){
                alert("请上传场馆主图");
                return;
            }

            isSubmiting = true;
            $.post("sport/venue/save.htm", queryString, function (data){
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1"){
                    alert(jsonReturn.msg);
                    if (top.f_getframe("sport_venue_init_htm") != null) {
                        top.f_getframe("sport_venue_init_htm").f_query();
                        if($("#op").val()=="modify"){
                            top.f_delTab("sport_venue_add_htm_op_modify_id_${venue.id}");
                        }else{
                            top.f_delTab("sport_venue_add_htm_op_add");
                        }
                    }
                } else {
                    alert(jsonReturn.msg);
                }
                isSubmiting = false;
            });

        }

        function h2y_refresh(){
            window.location.reload();
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
        <input type="hidden" name="id" id="id" value="${venue.id}"/>
        <input type="hidden" name="zoneCode" id="zoneCode" value="${venue.zoneCode}"/>
        <input type="hidden" name="zoneId" id="zoneId" value="${venue.zoneId}"/>
        <input name="op" type="hidden" id="op" value="${op}"/>
        <table class="h2y_table">
            <tr>
                <td class="h2y_table_label_td">
                    场馆名称：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="name" type="text" id="name" class="h2y_input_long"
                           value="${venue.name}"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    场馆所属区域：
                </td>
                <td class="h2y_table_edit_td2" colspan="3" id="zone_td">
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    详细地址：
                </td>
                <td class="h2y_table_edit_td2"  colspan="3">
                    <input name="address" type="text" id="address" class="h2y_input_long"
                           value="${venue.address}"/>
                </td>
            </tr>
           <tr>
                <td class="h2y_table_label_td">
                    联系人：
                </td>
                <td class="h2y_table_edit_td" >
                    <input name="contact" type="text" id="contact" class="h2y_input_just"
                           value="${venue.contact}"/>
                </td>
                <td class="h2y_table_label_td">
                    联系人性别：
                </td>
                <td class="h2y_table_edit_td" >
                    <h2y:input name="sex" id="sex" type="radio" initoption="0,男:1,女"
                               value="${venue.sex}"/>
                </td>
            </tr>
            <tr>
                 <td class="h2y_table_label_td">
                     联系电话：
                 </td>
                 <td class="h2y_table_edit_td" >
                     <input name="telephone" type="text" id="telephone" class="h2y_input_just"
                            value="${venue.telephone}"  />
                 </td>
                 <td class="h2y_table_label_td">
                     手机：
                 </td>
                 <td class="h2y_table_edit_td" >
                     <input name="mobile" type="text" id="mobile" class="h2y_input_just"
                            value="${venue.mobile}" placeholder="前台登录用户名，请认真填写"/>
                 </td>
             </tr>
             <tr>
               <td class="h2y_table_label_td">
                   场馆简介：
               </td>
               <td class="h2y_table_edit_td2" colspan="3">
                   <textarea name="introduction" id="introduction" class="h2y_editor_textarea">${venue.introduction}</textarea>
               </td>
           </tr>
           <%-- <tr>
                 <td class="h2y_table_label_td">
                     营业时间：
                 </td>
                 <td class="h2y_table_edit_td2" colspan="3">
                     <input name="businessTime" type="text" id="businessTime" class="h2y_input_long"
                            value="${venue.businessTime}"/>
                 </td>
             </tr>--%>
            <tr>
                <td class="h2y_table_label_td">
                    营业开始时间：
                </td>
                <td class="h2y_table_edit_td">
                    <%--<input name="startTime" type="text" id="startTime" class="h2y_input_just"
                           value="${venue.startTime}" />--%>
                        <select name="startTime" id="startTime">

                        </select>
                </td>
                <td class="h2y_table_label_td">
                    营业结束时间：
                </td>
                <td class="h2y_table_edit_td">
                    <%--<input name="endTime" type="text" id="endTime" class="h2y_input_just"
                           value="${venue.endTime}"/>--%>
                    <select name="endTime" id="endTime">

                    </select>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    营业执照编号：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="licenseNo" type="text" id="licenseNo" class="h2y_input_just"
                           value="${venue.licenseNo}"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    法人代表：
                </td>
                <td class="h2y_table_edit_td" >
                    <input name="artificilPerson" type="text" id="artificilPerson" class="h2y_input_just"
                           value="${venue.artificilPerson}"/>
                </td>
                <td class="h2y_table_label_td">
                    组成形式：
                </td>
                <td class="h2y_table_edit_td">
                    <input name="organizationType" type="text" id="organizationType" class="h2y_input_just"
                           value="${venue.organizationType}"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    <input type="button" value="营业执照" class="button"
                           onclick="h2y_fileuploadLicense('licensePic')"/>:
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <div id="div_licensePic"></div>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    <input type="button" value="上传logo" class="button"
                           onclick="h2y_fileupload('venuePic')"/>:
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <div id="div_venuePic"></div>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    <input type="button" value="上传实景图" class="button" onclick="h2y_fileuploadMany('venuePics')"/>：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <div id="div_venuePics"></div>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                   公交信息：
                </td>
                <td class="h2y_table_edit_td" colspan="3">
                    <input name="busInfo" type="text" id="busInfo" class="h2y_input_long"
                           value="${venue.busInfo}"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    地铁信息：
                </td>
                <td class="h2y_table_edit_td" colspan="3">
                    <input name="subwayInfo" type="text" id="subwayInfo" class="h2y_input_long"
                           value="${venue.subwayInfo}"/>
                </td>
            <tr>
                <td class="h2y_table_label_td">
                    服务信息：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="serviceInfo" type="text" id="serviceInfo" class="h2y_input_long"
                           value="${venue.serviceInfo}"  placeholder="请严格参照如下格式填写：停车位：免费停车；场馆卖品：饮食；…"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    硬件设施：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="hardware" type="text" id="hardware" class="h2y_input_long"
                           value="${venue.hardware}" placeholder="请严格参照如下格式填写：场地：塑胶场地；灯光：侧灯；…"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    是否可退款：
                </td>
                <td class="h2y_table_edit_td">
                    <h2y:input name="isRefund" id="isRefund" type="radio" initoption="0,否:1,是"
                               value="${venue.isRefund}"/>
                </td>
                <td class="h2y_table_label_td">
                    最晚退款时间：
                </td>
                <td class="h2y_table_edit_td">
                    提前<input name="refundDeadline" type="text" id="refundDeadline"
                           value="${venue.refundDeadline}"/>小时
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    是否热门场馆：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <h2y:input name="isRecommend" id="isRecommend" type="radio" initoption="0,否:1,是"
                               value="${venue.isRecommend}"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
