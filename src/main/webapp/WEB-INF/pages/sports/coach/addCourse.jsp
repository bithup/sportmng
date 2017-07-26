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
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-slide.min.js" type="text/javascript"></script>
    <style type="text/css">

    </style>

    <script type="text/javascript">
        var isSubmiting = false;
        var form = null;
        var op = "${op}";
        var fileId = 0;


        $(function () {

            $("#courseTime").datetimepicker({
                timeFormat: ''
            });

            $("#toptoolbar").ligerToolBar({items: [
                {line: true},
                {text: '保存', click: h2y_save, icon: 'save'},
                {line: true},
                {text: '刷新', click: h2y_refresh, icon: 'refresh'}
            ]});
            $('#startDate').timepicker({});
            $('#endDate').timepicker({});


            $("#price").ligerSpinner({type: 'float', height: 25, width: 200, isNegative: false});
            $("#price").val(${coachCourse.price});

            $("#salesPrice").ligerSpinner({type: 'float', height: 25, width: 200, isNegative: false});
            $("#salesPrice").val(${coachCourse.salesPrice});
            if(op=="modify"){
            }else{
            }

        });

        function h2y_save(){
            var queryString = $('#editform').serialize();

            var courseName = $("#courseName").val();
            var courseType = $("#courseType option:selected").val();
            var introduct = $("#introduct").val();
            var price = $("#price").val();
            var salesPrice = $("#salesPrice").val();
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();

            if(courseName == null || '' === courseName){
                alert("请填写课程名称!");
                return;
            }

            if(courseType == null || '' === courseType){
                alert("请选择课程类型!");
                return;
            }

            if(introduct == null || '' === introduct){
                alert("请填写课程介绍!");
                return;
            }

            if(price == null || '' === price){
                alert("请填写课程单价!");
                return;
            }

            if(salesPrice == null || '' === salesPrice){
                alert("请填写课程优惠价!");
                return;
            }

            //var reg = /^[0-9]d*.d*|0.d*[1-9]d*$/
            if(parseFloat(price)<=0){
                alert("单价格式不正确，需大于0!");
                return;
            }
            if(parseFloat(salesPrice)<=0){
                alert("课程优惠价格式不正确，需大于0!");
                return;
            }
            if(parseFloat(salesPrice)>parseFloat(price)){
                alert("课程优惠价不能大于原价!");
                return;
            }

            if(endDate == null || '' === endDate){
                alert("请填写课程结束时间!");
                return;
            }

            if(endDate<startDate){
                alert("结束时间不能早于开始时间!");
                return;
            }

            if(startDate == null || '' === startDate){
                alert("请填写课程开始时间!");
                return;
            }

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }

            isSubmiting = true;
            $.post("sport/coach/saveCourse.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);//getFrame是从哪个页面过来的,delTap是删除修改后的当前页面
                        if (top.f_getframe("sports_coach_showCourseList_htm_op_view_id_${coachCourse.coachId}") != null) {
                            top.f_getframe("sports_coach_showCourseList_htm_op_view_id_${coachCourse.coachId}").f_query();
                            top.f_delTab("sport_coach_addCourse_htm_op_modify_id_${coachCourse.id}");//修改的是当前的课程
                        }
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("sports_coach_showCourseList_htm_op_view_id_${coachCourse.coachId}") != null) {
                            top.f_getframe("sports_coach_showCourseList_htm_op_view_id_${coachCourse.coachId}").f_query();
                        }
                        top.f_delTab("sport_coach_addCourse_htm_op_add_id_${coachId}");
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
    <input type="hidden" name="coachId" id="coachId" value="${coachCourse.coachId}"/>
    <input type="hidden" name="id" id="id" value="${coachCourse.id}"/>
    <input name="op" type="hidden" id="op" value="${op}"/>
    <table class="h2y_table">
        <tr>
            <td class="h2y_table_label_td">
                课程名称：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="courseName" type="text" id="courseName" class="h2y_input_long"
                       value="${coachCourse.courseName}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                课程类型：
            </td>
            <td class="h2y_table_edit_td">
                <select name="courseType" id="courseType"
                        class="h2y_input_just">
                    <c:choose>
                        <c:when test="${coachCourse.courseType==null}">
                            <option value="0" selected>请选择</option>
                            <option id="1" value="1" >月</option>
                            <option id="2" value="2">季</option>
                            <option id="1" value="3" >年</option>

                        </c:when>
                        <c:otherwise>
                            <option id="1" value="1"
                                                                         <c:if test="${coachCourse.courseType=='1'}">selected</c:if>>月
                        </option>
                            <option id="2" value="2"
                                    <c:if test="${coachCourse.courseType=='2'}">selected</c:if>>季
                            </option>
                            <option id="3" value="3"
                                    <c:if test="${coachCourse.courseType=='3'}">selected</c:if>>年
                            </option>
                        </c:otherwise>
                    </c:choose>
                </select>

            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                课程介绍：
            </td>
            <td class="h2y_table_edit_td2" colspan="3">
                                <textarea style="height: 100px;width:400px" name="introduct" id="introduct"
                                >${coachCourse.introduct}</textarea>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">
                单价：
            </td>
            <td class="h2y_table_edit_td" id="memberIdtype">
                <input name="price" type="text" id="price" class="h2y_input_just"
                />
            </td>
            <td class="h2y_table_label_td">
                优惠价：
            </td>
            <td class="h2y_table_edit_td">
                <input name="salesPrice" type="text" id="salesPrice" class="h2y_input_just"
                />
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">
                课程创建时间：
            </td>
            <td class="h2y_table_edit_td" colspan="3">
                <input name="courseTime" type="text" id="courseTime" class="h2y_input_just"
                      <%--value="<fmt:formatDate value="${coachCourse.courseTime}"  pattern="yyyy-MM-dd "/>"--%>
                        value="${coachCourse.courseTime}"/>
            </td>

        </tr>
        <tr>
            <td class="h2y_table_label_td">开始时间:</td>
            <td class="h2y_table_edit_td2">
                <input name="startDate" type="text" id="startDate" class="h2y_input_just"
                       value="${coachCourse.startDate}"/>
            </td>

            <td class="h2y_table_label_td">结束时间:</td>
            <td class="h2y_table_edit_td2" colspan="3">
                <input name="endDate" type="text" id="endDate" class="h2y_input_just"
                       value="${coachCourse.endDate}"/>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
