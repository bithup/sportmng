<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script type="text/javascript">
        var id = 0;
        $(function () {
            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
            $("#layout1").ligerLayout({
                leftWidth: 230,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });
            f_getList();
        });

        function f_getList() {
            var url_1 = "sport/coach/getList.htm";
            $("#listgrid").ligerGrid({
                columns: [${gridComluns}],
                url: url_1,
                parms: [
                    {name: "pid", value: id}, {name: "op", value: "grid"}
                ],
                showTitle: false,
                dataAction: "server",
                sortName: "ord",
                pageSize: 20,
                height: "100%",
                width: "100%",
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                onSelectRow: function (row, index, data) {
                    //id = row.id;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        function h2y_add() {
            var src = "<%=basePath%>sport/coach/add.htm?op=add";
            top.f_addTab("sport_coach_add_htm_op_add_id", "教练添加", src);
        }

        function h2y_modify() {
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();

            if (rows == null || rows.length == 0) {
                alert('请选择教练');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            var src = "<%=basePath%>sport/coach/add.htm?op=modify&id=" + rows[0].id;
            top.f_addTab("sport_coach_add_htm_op_modify_id_" + rows[0].id, "教练信息修改", src);
        }

        function h2y_delete() {
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择教练');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            if (!confirm("删除后不可恢复，确定删除此行吗？")) return;
            $.post("sport/coach/delete.htm?id=" + rows[0].id, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    h2y_refresh()
                } else {
                    alert(jsonReturn.msg);
                }
            });
        }

        /**
         *
         * 查看课表
         */
        function h2y_showCourse(){
            var manager= $("#listgrid").ligerGetGridManager();
            var rows= manager.getCheckedRows();
            if(rows==null||rows==0){
                alert("请选择教练");
                return;
            }else if(rows.length>1){
                alert("请选择单行记录");
                return;
            }  else{
                var src = "<%=basePath%>sport/coach/showCourseList.htm?id="+rows[0].id;
                top.f_addTab("sports_coach_showCourseList_htm_op_view_id_"+ rows[0].id, "查看课程列表", src);
            }
        }

        /**
         * 教练课程添加
         *
         */
        function h2y_addCourse() {
            //获取一个ligerUI表格管理对象
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();//得到被选中的行数
            if (rows == null || rows.length == 0) {
                alert("请选择教练!");
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }

            var src = "<%=basePath%>sport/coach/addCourse.htm?op=add&coachId=" + rows[0].id;
            top.f_addTab("sport_coach_addCourse_htm_op_add_id_" + rows[0].id, "课程列表添加", src);

        }

        function h2y_refresh() {
            document.location.reload();
        }

        function f_query() {
            var manager = $("#listgrid").ligerGetGridManager();
            manager.setOptions({
                parms: []
            });
            manager.loadData(true);
        }
    </script>
</head>
<body>
<div id="layout1" style="width: 100%">

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
    <div position="center" title="">
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>