<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script type="text/javascript">

        $(function () {

            h2y_setSqlCondition();

            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
            $("#layout1").ligerLayout({
                leftWidth: 190,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });
            f_getList();
        });

        function f_getList() {

            var url_1 = "sys/log/getList.htm?op=grid";

            $("#listgrid").ligerGrid({
                columns: [${gridComluns}],
                url: url_1,
                parms: [],
                showTitle: false,
                dataAction: "server",
                sortName: "OPERATE_DATE",
                pageSize: 20,
                height: "100%",
                width: "100%",
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                onSelectRow: function (row, index, data) {
                    id = row.ID;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        function h2y_refresh() {
            document.location.reload();
        }

        function h2y_search() {

            var sqlWhere = h2y_getSqlCondition();
            var manager = $("#listgrid").ligerGetGridManager();
            manager.changePage("first");
            manager.setOptions({
                parms: [{name: "configQuery", value: sqlWhere}]
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

    <%--
     <div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
     --%>

    <div position="center" title="">
        <div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>