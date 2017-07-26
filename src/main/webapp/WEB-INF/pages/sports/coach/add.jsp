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

    <style type="text/css">
        .goodstype_select {
            border: 1px solid #aecaf0;
            height: 25px;
            line-height: 25px;
        }

        .goodsLogoImg {
            max-width: 600px;
        }

        .goodsPicImg {
            width: auto;
            height: 240px;
        }
    </style>
    <script src="<%=uiPath%>/common/js/common.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-slide.min.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
    <script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;

        var kindsLevel = 0;
        var goodsKindsId = "";
        var labelDataJson = null;

        var preCode = "${preCode}";

        var fileId = 0;
        var uploadType = null;

        var introduceEditor = null;

        var parFix = "${parFix}";
        var zoneLevel = 0;
        $(function () {
            $("#birthday").datetimepicker({
                yearOffset: 1,
                lang: 'ch',
                step: 5,
                /*startDate: date,*/
                format: 'd/m/Y',
                formatDate: 'Y/m/d',
                /*         minDate:'-1970/01/02', // yesterday is minimum date*/
                maxDate: '+1990/01/02' // and tommorow is maximum date calendar
            });
            KindEditor.ready(function (K) {
                introduceEditor = K.create("#intro", {
                    pasteType: 1,
                    uploadJson: '<%=basePath%>kindeditor/uploadJson.htm',
                    afterBlur: function () {
                        this.sync();
                    }
                });
            });

            $("#toptoolbar").ligerToolBar({
                items: [{line: true}, {
                    text: '保存',
                    click: h2y_save,
                    icon: 'save'
                }, {line: true}, {text: '刷新', click: h2y_refresh, icon: 'refresh'}]
            });

            $("#telPhone").change(function(){
                var account = $("#telPhone").val();
                checkAccount(account);
            });


            if (op == "modify") {
                $("#tr_next").hide();
                var preCode_array = preCode.split("_");
                modifySelect(preCode_array);

                var preFix_array = parFix.split("_");
                modifySelectZone(preFix_array);
                $(${fileDataListJson}).each(function () {

                    var json_str = "{\"id\":\"" + this.id + "\"}";
                    if (this.dataType == 1) {

                        $("#goodspic_div").append("<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"picData\"  value='" + json_str + "'/>" +
                                "<a id=\"file_lightbox_" + fileId + "\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\">" +
                                "<img  id=\"file_img_" + fileId + "\" class=\"goodsPicImg\" src=\"" + this.url + "\"></a>" +
                                "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                    } else {

                        if (this.ord == 2 && this.type == 2) {
                            $("#goodslogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/><a class=\"shopLogoImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"shopLogoImg\" src=\"common/image/view.htm?path=" + this.url + "\"></a>");
                        } else {
                            $("#goodslogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>");
                        }
                        if (this.type == 1 && this.ord == 2 && this.dataType == 0) {
                            $("#idCardFront_div").append("<input type=\"hidden\" name=\"logoIdFrontData\"  value='" + json_str + "'/>" +
                                    "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"goodsLogoImg\" src=\"" + this.url + "\"></a>");
                        } else {
                            $("#idCardFront_div").append("<input type=\"hidden\" name=\"logoIdFrontData\"  value='" + json_str + "'/>");
                        }
                        if (this.type == 3 && this.ord == 2 && this.dataType == 0) {
                            $("#idCardBack_div").append("<input type=\"hidden\" name=\"logoIdBackData\"  value='" + json_str + "'/>" +
                                    "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"" + this.url + "\" title=\"" + this.fileName + "\"><img class=\"goodsLogoImg\" src=\"" + this.url + "\"></a>");
                        } else {
                            $("#idCardBack_div").append("<input type=\"hidden\" name=\"logoIdBackData\"  value='" + json_str + "'/>");
                        }

                    }
                    fileId++;
                });
                $(".goodsPicImg_lightbox").lightBox();
                $(".goodsLogoImg_lightbox").lightBox();

            } else {
                //添加一级商品类型
                addSelect("0", "");

                addSelectzone("200000", "");//省市区三级分类选择
            }
            $("input[type=radio][name='isAllowGrade']").change(function () {
                if (this.value == 0) {
                    $("#limitGradeNumber").val('0');
                    $("#limitGradeNumber").attr("disabled", true);
                } else {
                    $("#limitGradeNumber").attr("disabled", false);
                }
            });
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));

            $("#height").ligerSpinner({type: 'float', height: 25, width: 200, isNegative: false});
            $("#height").val(${coach.height});
            $("#weight").ligerSpinner({type: 'float', height: 25, width: 200, isNegative: false});
            $("#weight").val(${coach.weight});

            //暂时设置为不可用
            $("[name='isMallVisible']:radio").attr("disabled", true);

            $("#spec").ligerSpinner({type: 'int', minValue: 1, height: 25, width: 150, isNegative: false});
            if (op == 'modify') {
                $("#spec").val(${goods.spec});
            } else {
                $("#spec").val(1);
            }
            $("#data7").ligerSpinner({type: 'int', height: 25, width: 150, isNegative: false, value:${coach.data7}});

            $("#selectLabelBut").click(function () {

                goodsKindsId = $("#goodstype_td select:last").val();
                if ("" == goodsKindsId || undefined == goodsKindsId) {
                    alert("请选择运动类型");
                    return;
                }
                $("#goodsKindsId").val(goodsKindsId);
                openLabelSelectDialog({kindsId: goodsKindsId});
            });


            $("#goodsPicUploadBut").click(function () {
                openFileUploadDialog({
                    fileTypeExts: "*.jpg;*.png;*.jpeg;*.gif",
                    multi: true
                });
            });

            $("#idCardFront").click(function () {
                openImageUploadDialogIdFront();
            });
            $("#idCardBack").click(function () {
                openImageUploadDialogIdBack();
            });
            $("#goodsLogoUploadBut").click(function () {
                openImageUploadDialog();
            });


            $("input[type=radio][name='isAllowGrade']").change(function () {
                if (this.value == 0) {
                    $("#isAllowGrade").val('0');
                    $("#isAllowGrade").attr("disabled", true);
                } else {
                    $("#isAllowGrade").attr("disabled", false);
                }
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
            $.post("/sport/coach/getListZone.htm?parentId=" + parentId, function (data) {
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
                    selectHtml = "<select id=\"coachzone_select_" + zoneLevel + "\" class=\"coachzone_select\">" +
                            "<option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#zone_td").append(selectHtml + "");
                    if (obj != "") {
                        h2y_do_changezone(obj.next());
                    } else {
                        h2y_do_changezone($("#coachzone_select_" + zoneLevel));
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
            $.post("/sport/coach/getListZone.htm", {parentId: parentId2}, function (data) {
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
                    selectHtml = "<select id=\"coachzone_select_" + zoneLevel + "\" class=\"coachzone_select\"><option value=\"\"> </option>" + selectHtml + "</select>";
                    $("#zone_td").append(selectHtml + "");
                    h2y_do_changezone($("#coachzone_select_" + zoneLevel));
                    if (zoneLevel <= preCode_array.length) {
                        modifySelectZone(preCode_array, zoneLevel);
                    }
                }
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
         * 获取分类
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
         *
         * 三级列表
         *
         * */
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


        function h2y_save() {

/*            alert($("#sex").val());
            alert($("#sex option:selected").val());*/
            if (!Validator.form()) return;

             var name = $("#name").val();
             var venueName = $("#venueName").val();
             var telPhone = $("#telPhone").val();
             var sportId = $("#sportId").val();
             var intro = $("#intro").val();
             var teachingCareer = $("#teachingCareer").val();
             var data2 = $("#data2").val();

            var telReg = /^1[3|5|7|8|][0-9]{9}$/

             if (name == null || '' === name) {
                 alert("教练名称不能为空!");
                 return;
             }

            if(data2 == null || ''===data2){
                alert("上课地址不能为空!");
                return;
            }

             if (venueName == null || '' === venueName) {
             alert("请填写常驻场馆!");
             return;
             }

             if (telPhone == null || '' === telPhone) {
                 alert("请填写联系电话!");
                 return;
             }else if(!telReg.test(telPhone)){
                 alert("请填写正确的手机号");
                 return;
             }

            if (sportId == null || '' === sportId) {
                alert("请选择运动类型!");
                return;
            }

             if (teachingCareer == null || '' === teachingCareer) {
             alert("请填写教学经历!");
             return;
             }

            if (intro == null || '' === intro) {
                alert("请填写个人简介!");
                return;
            }

             if (document.getElementById("idCardFront_div").innerHTML == "") {
             alert("请上传身份证正面照!");
             return;
             }

            if (document.getElementById("idCardBack_div").innerHTML == "") {
                alert("请上传身份证背面照!");
                return;
            }

            if (document.getElementById("goodslogo_div").innerHTML == "") {
                alert("请上传logo图片!");
                return;
            }

             if (document.getElementById("goodspic_div").innerHTML == "") {
             alert("请上传图片!");
             return;
             }


            //同步富文本编辑框数据
            introduceEditor.sync();

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }

            isSubmiting = true;
            $.post("sport/coach/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("sport_coach_init_htm") != null) {
                            top.f_getframe("sport_coach_init_htm").f_query();
                            top.f_delTab("sport_coach_add_htm_op_modify_id_${coach.id}");
                        }
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("sport_coach_init_htm") != null) {
                            top.f_getframe("sport_coach_init_htm").f_query();
                            top.f_delTab("sport_coach_add_htm_op_add_id");
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
        /**
         * 上传多张图片
         *
         * cq
         * */
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
                $("#goodspic_div").append("" +
                        "<input id=\"file_input_" + fileId + "\" type=\"hidden\" name=\"picData\"  value='" + json_value + "'/>" +
                        "<a id=\"file_lightbox_" + fileId + "\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"" + tempurl + "\" title=\"" + this.fileName + "\">" +
                        "<img  id=\"file_img_" + fileId + "\" class=\"goodsPicImg\" src=\"" + tempurl + "\">" +
                        "</a>" +
                        "<a id=\"file_delete_href_" + fileId + "\" href=\"javascript:deletePic('" + fileId + "');\">删除</a>");
                fileId++;
            });
            $(".goodsPicImg_lightbox").lightBox();
        }

        /**
         *
         * 上传身份证正面图片
         *
         * */
        function h2y_imageUploadCallBackIdFront(data) {

            if (data == null || data.length == 0) {
                return;
            }
            var imageHtml = "";
            /*每次添加的时候，都是添加三张图片*/
            $(data).each(function (i) {
                this['fileType'] = 0;
                var json_str = JSON.stringify(this);
                var tempurl = this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");
                if (i == 1) {
                    imageHtml += "<input type=\"hidden\" name=\"logoIdFrontData\"  value='" + json_str + "'/>" +
                            "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"" + tempurl + "\" title=\"" + this.fileName + "\">" +
                            "<img class=\"goodsLogoImg\" src=\"" +
                            tempurl + "\"></a>";
                } else {
                    imageHtml += "<input type=\"hidden\" name=\"logoIdFrontData\"  value='" + json_str + "'/>";
                }
            });
            $("#idCardFront_div").html(imageHtml);
            $(".goodsLogoImg_lightbox").lightBox();
        }
        /**
         *
         * 上传身份证背面图片
         *
         * */
        function h2y_imageUploadCallBackIdBack(data) {

            if (data == null || data.length == 0) {
                return;
            }
            var imageHtml = "";
            /*每次添加的时候，都是添加三张图片*/
            $(data).each(function (i) {
                this['fileType'] = 0;
                var json_str = JSON.stringify(this);
                var tempurl = this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");
                if (i == 1) {
                    imageHtml += "<input type=\"hidden\" name=\"logoIdBackData\"  value='" + json_str + "'/>" +
                            "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"" + tempurl + "\" title=\"" + this.fileName + "\">" +
                            "<img class=\"goodsLogoImg\" src=\"" +
                            tempurl + "\"></a>";
                } else {
                    imageHtml += "<input type=\"hidden\" name=\"logoIdBackData\"  value='" + json_str + "'/>";
                }
            });
            $("#idCardBack_div").html(imageHtml);
            $(".goodsLogoImg_lightbox").lightBox();
        }
        /**
         *
         * 上传单张图片
         *
         * */
        function h2y_imageUploadCallBack(data) {

            if (data == null || data.length == 0) {
                return;
            }
            var imageHtml = "";
            /*每次添加的时候，都是添加三张图片*/
            $(data).each(function (i) {
                this['fileType'] = 0;
                var json_str = JSON.stringify(this);
                var tempurl = this.url;
                var pattern = "/save_path";
                tempurl = tempurl.replace(new RegExp(pattern), "");
                if (i == 1) {
                    imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>" +
                            "<a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"" + tempurl + "\" title=\"" + this.fileName + "\">" +
                            "<img class=\"goodsLogoImg\" src=\"" +
                            tempurl + "\"></a>";
                } else {
                    imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='" + json_str + "'/>";
                }
            });
            $("#goodslogo_div").html(imageHtml);
            $(".goodsLogoImg_lightbox").lightBox();
        }
        function deletePic(fileId) {

            alert(fileId);
            if (!confirm("您确定要删除文件吗?")) {
                return true;
            }
            $("#file_input_" + fileId).remove();
            $("#file_lightbox_" + fileId).remove();
            $("#file_img_" + fileId).remove();
            $("#file_delete_href_" + fileId).remove();
        }

        function h2y_showCourse() {

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

    <input name="id" type="hidden" id="id" value="${coach.id}"/>
    <input name="sportId" type="hidden" id="sportId" value="${coach.sportId}"/>
    <input type="hidden" name="data1" id="zoneCode" value="${coach.data1}"/>
    <input type="hidden" name="data6" id="zoneId" value="${coach.data6}"/>
    <input name="op" type="hidden" id="op" value="${op}"/>

    <table class="h2y_table">

        <tr>
            <td class="h2y_table_label_td">
                教练名称：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="name" type="text" id="name" class="h2y_input_long"
                       value="${coach.name}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                所属区域：
            </td>
            <td class="h2y_table_edit_td2" colspan="3" id="zone_td">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                上课地址：
            </td>
            <td class="h2y_table_edit_td2" colspan="3" >
                <input name="data2" type="text" id="data2" class="h2y_input_long"
                       value="${coach.data2}"/>
            </td>
        </tr>

        <tr>

            <td class="h2y_table_label_td">
                常驻场馆名称：
            </td>
            <td class="h2y_table_edit_td" colspan="3">

                <input name="venueName" type="text" id="venueName" class="h2y_input_just"
                       value="${coach.venueName}"/><!-- 最大长度6位，验证配置 -->
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                性别：
            </td>
            <td class="h2y_table_edit_td">
                <select name="sex" id="sex"
                        class="h2y_input_just">
                    <c:choose>
                        <c:when test="${coach.sex==null}">
                            <option value="0" selected>请选择</option>
                            <option id="1" value="1">男</option>
                            <option id="2" value="2">女</option>
                        </c:when>
                        <c:otherwise>
                            <option id="1" value="1"
                                    <c:if test="${coach.sex=='1'}">selected</c:if>>男
                            </option>
                            <option id="2" value="2"
                                    <c:if test="${coach.sex=='2'}">selected</c:if>>女
                            </option>
                        </c:otherwise>
                    </c:choose>
                </select>

            </td>

            <td class="h2y_table_label_td">
                生日：
            </td>
            <td class="h2y_table_edit_td" colspan="3">
                <input name="birthday" type="text" id="birthday" class="h2y_input_just"
                       value="<fmt:formatDate value="${coach.birthday}"  pattern="yyyy-MM-dd "/>" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                身高：
            </td>
            <td class="h2y_table_edit_td" id="memberIdtype">
                <input name="height" type="text" id="height" class="h2y_input_just"
                />
            </td>
            <td class="h2y_table_label_td">
                体重：
            </td>
            <td class="h2y_table_edit_td">
                <input name="weight" type="text" id="weight" class="h2y_input_just"
                />
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                是否认证：
            </td>
            <td class="h2y_table_edit_td" colspan="3">
                <h2y:input name="isTrue" id="isTrue" type="radio" initoption="0,否:1,是"
                           value="${coach.isTrue}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                是否热门教练：
            </td>
            <td class="h2y_table_edit_td">
                <h2y:input name="isRecommend" id="isRecommend" type="radio" initoption="0,否:1,是"
                           value="${coach.isRecommend}"/>
            </td>

            <td class="h2y_table_label_td">
                联系电话：
            </td>
            <td class="h2y_table_edit_td">
                <input name="telPhone" type="text" id="telPhone" class="h2y_input_just"
                       value="${coach.telPhone}" placeholder="前台登录用户名，请认真填写"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                运动类型：
            </td>
            <td id="sportId_td" class="h2y_table_edit_td2" colspan="3">
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                营业时间：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                                <textarea style="height: 100px;width:700px" name="businessTime" id="businessTime"
                                >${coach.businessTime}</textarea>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">排序：</td>
            <td class="h2y_table_edit_td">
                <input name="data7" type="text" id="data7" value="${coach.data7}"/>
            </td>

        </tr>

        <tr>

            <td class="h2y_table_label_td">
                教学经历：
            </td>
            <td class="h2y_table_edit_td" colspan="3">
                <input name="teachingCareer" type="text" id="teachingCareer" class="h2y_input_just"
                       value="${coach.teachingCareer}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                个人简介：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <textarea name="intro" id="intro"
                          class="h2y_editor_textarea">${coach.intro}</textarea>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                <input type="button" name="idCardFront" value="身份证正面照" class="button" id="idCardFront"/>：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="idCardFront_div"></div>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                <input type="button" name="idCardBack" value="身份证背面照" class="button" id="idCardBack"/>：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="idCardBack_div"></div>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                <input type="button" name="logoPath" value="上传Logo" class="button" id="goodsLogoUploadBut"/>：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="goodslogo_div"></div>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                <input type="button" value="上传图片" class="button" id="goodsPicUploadBut"/>：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <div id="goodspic_div"></div>
            </td>
        </tr>

    </table>
</form>

</body>
</html>