<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp"%>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
    <script type="text/javascript">
        var id = 0;
        var order = 0;
        $(function () {
            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
            $("#layout1").ligerLayout({
                leftWidth: 230,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });
            $("#startTime").timepicker();
            $("#endTime").timepicker();
            f_getList();
        });
        function f_getList() {
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var url_1 = "sport/shortMessage/getList.htm?startTime="+startTime+"&endTime="+endTime;
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

        function  text()
        {   var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.rows;

            for (var i = 0, l = rows.length; i < l; i++) {
                rows[i].Permit = true;
            }
            manager.reRender();
        }

        function h2y_search(){
            f_getList();
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
        <div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
            开始时间：<input type="text " name="startTime" id="startTime" pattern="yyyy-MM-dd HH:mm:ss">
            结束时间：<input type="text " name="endTime" id="endTime" pattern="yyyy-MM-dd HH:mm:ss">
        </div>

        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>