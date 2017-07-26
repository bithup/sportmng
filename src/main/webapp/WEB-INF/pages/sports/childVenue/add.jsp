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
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

    <style type="text/css">
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
        var op = "${op}";
        var fileId = 0;
        //文件上传
        var fileType = null;//文件上传的标识

        var kindsLevel = 0;

        var preCode = "${preCode}";


        $(function () {
            $("#toptoolbar").ligerToolBar({items: [
                {line: true},
                {text: '保存', click: h2y_save, icon: 'save'},
                {line: true},
                {text: '刷新', click: h2y_refresh, icon: 'refresh'}
            ]});

            $("#ord").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false});
            $("#capacity").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false});
            $("#venueNo").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false});

            if(op=="modify"){
                $("#ord").val(${childVenue.ord});
                $("#capacity").val(${childVenue.capacity});
                $("#venueNo").val(${childVenue.venueNo});

                var preCode_array = preCode.split("_");
                modifySelect(preCode_array);

                $(${fileDataListJson}).each(function () {
                    var json_str = "{\"id\":\"" + this.id + "\"}";
                    if (this.dataType == 1) {

                        $("#div_venuePics").append("<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"venuePicsData\"  value='" + json_str + "'/>" +
                                "<a id=\"file_lightbox_" + fileId + "\" class=\"venuePicsImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\">" +
                                "<img  id=\"file_img_" + fileId + "\" class=\"venuePicsImg\" src=\"" + this.url + "\"></a>" +
                                "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                    } else {

                        if (this.ord == 2 && this.dataType == 0&&this.type==1) {
                            $("#div_venuePic").append("<input type=\"hidden\" name=\"venuePicData\"  value='" + json_str + "'/>" +
                                    "<a class=\"venuePicImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"venuePicImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                        } else {
                            $("#div_venuePic").append("<input type=\"hidden\" name=\"div_licensePicData\"  value='" + json_str + "'/>");
                        }
                    }
                    fileId++;
                });
                $(".venuePicsImg_lightbox").lightBox();
                $(".venuePicImg_lightbox").lightBox();

            }else{
                $("#parentId").val(${parentId});
                addSelect("0","");
            }

        });

        /**
         * 上传单张图片可剪裁
         * @param type
         */
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

        /**
         * 上传多张图片
         * @param type
         */
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

        /**
         * 上传单张图片可剪裁回调方法
         * @param type
         */
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

        /**
         * 上传多张图片回调方法
         * @param type
         */
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

        /**
         * 获取运动类型
         * @param parentId
         * @param obj
         */
        function addSelect(parentId, obj) {

            $.post("sport/childVenue/getListType.htm?parentId=" + parentId, function (data) {
                var jsonReturn = eval("(" + data + ")");

                $("#sportId").val(parentId);
                var selectHtml = "";
                $(jsonReturn).each(function () {
                    selectHtml += "<option value=\"" + this.id + "\">" + this.text + "</option>";
                });

                if (selectHtml != "") {
                    selectHtml = "<select id=\"kinds_select_" + kindsLevel + "\" class=\"kinds_select\">" +
                            "<option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#kinds_td").append(selectHtml + "");
                    if (obj != "") {
                        h2y_do_change(obj.next());
                    } else {
                        h2y_do_change($("#kinds_select_" + kindsLevel));
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
         * 修改时进入页面获取分类的值
         * @param typeCode_array
         */
        function modifySelect(preCode_array) {

            var parentId2 = kindsLevel == 0 ? 0 : preCode_array[kindsLevel - 1];
            $.post("business/kinds/getChildData.htm", {parentId: parentId2}, function (data) {
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
                    selectHtml = "<select id=\"kinds_select_" + kindsLevel + "\" class=\"kinds_select\"><option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#kinds_td").append(selectHtml + "");
                    h2y_do_change($("#kinds_select_" + kindsLevel));
                    if (kindsLevel <= preCode_array.length) {
                        modifySelect(preCode_array, kindsLevel);
                    }
                }
            });
        }

        function h2y_save(){
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }

            var venueName = $("#venueName").val();
            var sportId = $("#sportId").val();
            var venueNo = $("#venueNo").val();
            var price = $("#price").val();
            var salesPrice = $("#salesPrice").val();
            var unit = $("#unit").val();
            var capacity = $("#capacity").val();

            if(null==venueName||''===venueName){
                alert("请填写场馆名称");
                return;
            }
            if(null==sportId||''===sportId){
                alert("请选择运动类型");
                return;
            }
            if(null==venueNo||''===venueNo){
                alert("请填写场馆编号");
                return;
            }
            if(null==price||''===price){
                alert("请填写单价");
                return;
            }
            /*var reg = /^[0-9]d*.d*|0.d*[1-9]d*$/*/
            if(parseFloat(price) <= 0){
                alert("单价格式不正确，单价需大于0");
                return;
            }
            if(null==salesPrice||''===salesPrice){
                alert("请填写销售价格");
                return;
            }
            if(parseFloat(salesPrice) <= 0){
                alert("销售价格式不正确，为大于0的浮点数");
                return;
            }
            if(parseFloat(salesPrice)>parseFloat(price)){
                alert("销售价不能大于原价");
                return;
            }

            if(null==capacity||''===capacity){
                alert("请填写最大容纳人数");
                return;
            }
            if(document.getElementById("div_venuePic").innerHTML==''){
                alert("请上传场馆主图");
                return;
            }
            if(document.getElementById("div_venuePics").innerHTML==''){
                alert("请上传图片");
                return;
            }


            isSubmiting = true;
            $.post("sport/childVenue/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code > "0") {
                    alert(jsonReturn.msg);
                    if (top.f_getframe("sport_childVenue_add_htm_id_"+$("#parentId").val()) != null) {
                        top.f_getframe("sport_childVenue_add_htm_id_"+$("#parentId").val()).f_query();
                        if($("#op").val()=="modify"){
                            top.f_delTab("sport_childVenue_add_htm_op_modify_id_${childVenue.id}");
                        }else{
                            top.f_delTab("sport_childVenue_add_htm_op_add_parentId_"+$("#parentId").val());
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
    <form name="editform" method="post" action="" id="editform">
        <input type="hidden" name="id" id="id" value="${childVenue.id}"/>
        <input type="hidden" name="parentId" id="parentId" value="${childVenue.parentId}"/>
        <input type="hidden" name="sportId" id="sportId" value="${childVenue.sportId}"/>
        <input name="op" type="hidden" id="op" value="${op}"/>
        <table class="h2y_table">
            <tr>
                <td class="h2y_table_label_td">
                    分场馆名称：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="venueName" type="text" id="venueName" class="h2y_input_long"
                           value="${childVenue.venueName}"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    运动类型：
                </td>
                <td class="h2y_table_edit_td2" colspan="3" id="kinds_td">
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    编号：
                </td>
                <td class="h2y_table_edit_td2"  colspan="3">
                    <input name="venueNo" type="text" id="venueNo" class="h2y_input_long"
                           value="${childVenue.venueNo}"/>
                </td>
            </tr>
           <tr>
                <td class="h2y_table_label_td">
                    单价：
                </td>
                <td class="h2y_table_edit_td" >
                    <input name="price" type="text" id="price" class="h2y_input_just"
                           value="${childVenue.price}"/>
                </td>
                <td class="h2y_table_label_td">
                   特惠价：
                </td>
                <td class="h2y_table_edit_td" >
                    <input name="salesPrice" type="text" id="salesPrice" class="h2y_input_just"
                           value="${childVenue.salesPrice}"/>
                </td>
            </tr>
            <%-- <tr>
               <td class="h2y_table_label_td">
                   计量单位：
               </td>
                 <td class="h2y_table_edit_td2" colspan="3">
                     <input name="unit" type="text" id="unit" class="h2y_input_just"
                            value="${childVenue.unit}"/>
                 </td>
           </tr>--%>
            <tr>
                <td class="h2y_table_label_td">
                    场馆设施：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="facility" type="text" id="facility" class="h2y_input_long"
                           value="${childVenue.facility}" />
                </td>
            </tr>
            <tr>
                 <td class="h2y_table_label_td">
                     服务信息：
                 </td>
                 <td class="h2y_table_edit_td2" colspan="3">
                     <input name="serviceInfo" type="text" id="serviceInfo" class="h2y_input_long"
                            value="${childVenue.serviceInfo}"/>
                 </td>
             </tr>
            <tr>
                <td class="h2y_table_label_td">
                    提示信息：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="tips" type="text" id="tips" class="h2y_input_long"
                           value="${childVenue.tips}"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    排序：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="ord" type="text" id="ord" class="h2y_input_just"
                           value="${childVenue.ord}"/>
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
                    最大容纳人数：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <input name="capacity" type="text" id="capacity" class="h2y_input_long"
                           value="${childVenue.capacity}"/>
                </td>
            </tr>
            <tr>
                <td class="h2y_table_label_td">
                    是否热门场馆：
                </td>
                <td class="h2y_table_edit_td2" colspan="3">
                    <h2y:input name="isRecommend" id="isRecommend" type="radio" initoption="0,否:1,是"
                               value="${childVenue.isRecommend}"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
