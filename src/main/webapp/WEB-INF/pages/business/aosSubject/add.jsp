<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
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
        var introduceEditor = null;
        var op = "${op}";
        $(function () {
            $("#toptoolbar").ligerToolBar({
                items: [{line: true}, {
                    text: '保存',
                    click: h2y_save,
                    icon: 'save'
                }, {line: true}, {text: '刷新', click: h2y_refresh, icon: 'refresh'}]
            });
            //验证信息
            ${validationRules};
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            $("#startDate").datetimepicker({});
            $("#endDate").datetimepicker({});
            //textarea文本编辑框字体属性设置器
            KindEditor.ready(function (K) {
                introduceEditor = K.create("#remark", {
                    uploadJson: '<%=basePath%>kindeditor/uploadJson.htm',
                    afterBlur: function () {
                        this.sync();
                    }
                });
            });
        });

        function selectSubject() {
            var isYes = $("#isYes").val();
            if (isYes==""||isYes==null){
                alert("请选择类型");
                return;
            }
            openSubject(isYes);
        }
        /**
         * 获取回调数据
         * @param data
         */
        function h2y_subjectSelectCallBack(data) {
            if (data.length > 1) {
                alert("请选择单个商品");
                return;
            }
            var subject = data[0];
            if(subject.activityName){
                $("#subjectId").val(subject.id);
                $("#subjectName").val(subject.activityName);
                $("#logoPath").val(subject.activityPath);
                $("#logoRealPath").val(subject.activityRealPath);
                return;
            }
            if (subject.pictureUrl){
                $("#subjectId").val(subject.id);
                $("#subjectName").val(subject.name);
                $("#logoPath").val(subject.pictureUrl);
                $("#logoRealPath").val(subject.picRealPath);
                return;
            }
            if (subject.headPath){
                $("#subjectId").val(subject.id);
                $("#subjectName").val(subject.name);
                $("#logoPath").val(subject.headPath);
                $("#logoRealPath").val(subject.headRealPath);
                return;
            }
        }

        function h2y_save() {
            var subjectName = $("#subjectName").val();
            $("#dataType").val($("#isYes option:selected").val());
            if (subjectName==""||subjectName==null){
                alert("请输入名称！");
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
            $.post("business/aosSubject/save.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    var src = "<%=basePath%>business_aosSubject_init_htm";
                    top.f_addTab("business_aosSubject_init_htm", "轮播图列表", src);
                    top.f_getframe("business_aosSubject_init_htm").h2y_refresh();
                } else {
                    lert(jsonReturn.msg);
                }
                isSubmiting = false;
            });
        }

        /**
         * 下拉框单击让名称文本变空
         */
        function danji(){
            $("#subjectName").val("");
        }

        function h2y_refresh() {
            document.location.reload();
        }

    </script>
    <style type="text/css">
        body {
            font-size: 12px;
        }
    </style>

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
        <input type="hidden" id="id" name="id" value="${aosSubject.id}"/>
        <input type="hidden" id="subjectId" name="subjectId" value="${aosSubject.subjectId}"/>
        <input type="hidden" id="dataId" name="dataId" value="${aosSubject.dataId}"/>
        <input type="hidden" id="logoPath" name="logoPath" value="${aosSubject.logoPath}"/>
        <input type="hidden" id="logoRealPath" name="logoRealPath" value="${aosSubject.logoRealPath}"/>
        <input type="hidden" id="dataType" name="dataType" value="${aosSubject.dataType}"/>
    </div>
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">
                <select name="isYes" id="isYes" onclick="danji()">
                    <option value="0">选择类型</option>
                    <option value="1" <c:if test="${aosSubject.dataType==1}">selected="selected"</c:if>>场馆</option>
                    <option value="2" <c:if test="${aosSubject.dataType==2}">selected="selected"</c:if>>教练</option>
                    <option value="3" <c:if test="${aosSubject.dataType==3}">selected="selected"</c:if>>活动</option>
                </select>
                <input type="button" value="选择主题" class="button" onclick="selectSubject()"/>:
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="subjectName" type="text" id="subjectName" class="h2y_input_long"
                       value="${aosSubject.subjectName}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">是否加入主题：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <h2y:input name="isSubject" id="isSubject" type="radio" initoption="0,否:1,是" value="${aosSubject.isSubject}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">开始时间:</td>
            <td class="h2y_table_edit_td2">
                <input name="startDate" type="text" id="startDate" class="h2y_input_just"
                       value="<fmt:formatDate value="${aosSubject.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
            </td>
            <td class="h2y_table_label_td">结束时间:</td>
            <td class="h2y_table_edit_td2">
                <input name="endDate" type="text" id="endDate" class="h2y_input_just"
                       value="<fmt:formatDate value="${aosSubject.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">描述：</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <textarea name="remark" id="remark"
                       class="h2y_editor_textarea">${aosSubject.remark}</textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>