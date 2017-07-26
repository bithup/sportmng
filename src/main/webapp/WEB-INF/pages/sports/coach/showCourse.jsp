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
            $("#toptoolbar").ligerToolBar({items: [
                {line: true},
                {text: '修改', click: h2y_modify, icon: 'modify'},
                {line: true},
                {text: '删除', click: h2y_delete, icon: 'delete'},
                {line: true},
                {text: '刷新', click: h2y_refresh, icon: 'refresh'}
            ]});
            $("#layout1").ligerLayout({
                leftWidth: 230,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });
            getList()
        });

        function getList(){
            var coachId = $("#coachId").val();
            var url_1 = "sport/coach/getCourseList.htm?coachId="+coachId;
            $("#listgrid").ligerGrid({
                columns: [
                    {display:'课程名称',name:'courseName',width:'120',align:'left'},
                    {display:'课程类型',name:'courseType',width:'120',align:'left'},
                    {display:'单价',name:'price',width:'120',align:'left'},
                    {display:'优惠价',name:'salesPrice',width:'120',align:'left'},
                    {display:'开始时间',name:'startDate',width:'120',align:'left'},
                    {display:'结束时间',name:'endDate',width:'120',align:'left'},
                    {display:'创建时间',name:'courseTime',width:'120',align:'left'}
                ],
                url: url_1,
                parms: [
                    {name: "pid", value: id}, {name: "op", value: "grid"}
                ],
                showTitle: false,
                dataAction: "server",
                sortName: "ord",
                pageSize: 20,
                showTime:true,
                height: "100%",
                width: "100%",
                usePager: true,
                enabledEdit: true,
                clickToEdit: true,
                checkbox:false,
                pageSizeOptions: [20, 30, 50, 100],
                onSelectRow: function (row, index, data) {
                    //id = row.id;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        function h2y_modify(){
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }

            var src = "<%=basePath%>sport/coach/addCourse.htm?op=modify&id="+rows[0].id;
            top.f_addTab("sport_coach_addCourse_htm_op_modify_id_"+rows[0].id, "修改教练课程", src);
        }

        function h2y_delete(){
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            if (!confirm("删除后不可恢复，确定删除此行吗？")) return;
            $.post("sport/coach/deleteCourse.htm?id=" + rows[0].id, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    h2y_refresh()
                } else {
                    alert(jsonReturn.msg);
                }
            });
        }

        function h2y_refresh(){
            window.location.reload();
        }

        function f_query() {
            var manager = $("#listgrid").ligerGetGridManager();
            manager.setOptions({
                parms: [
                    {name: "pid", value: id}, {name: "op", value: "grid"}
                ]
            });
            manager.loadData(true);
        }
    </script>
</head>
<body>
<div id="layout1" style="width: 100%">
    <input type="hidden " id="coachId" name="coachId" value="${coachId}"/>
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

