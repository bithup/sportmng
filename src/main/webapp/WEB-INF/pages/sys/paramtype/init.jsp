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
        var menuId = 0;
        var isSubmiting = false;
        $(function () {

            h2y_setSqlCondition();

            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

            $("#layout1").ligerLayout({
                leftWidth: 190,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });


            <%--
            $("#tree1").ligerTree({
                //远程加载，有时会加载不出来
                //url: "sys/paramtype/getList.htm?op=tree",
                data:${treedata},
                checkbox: false,
                nodeWidth: 120,
                onSelect: f_onSelect,
                idFieldName: "id",
                parentIDFieldName: "pid",
                textFieldName: "text"
            });
            --%>

            f_getList();
        });

        function f_getList() {

            <%-- 注意该处url可能不符合你的要求，请注意修改 --%>
            var url_1 = "sys/paramtype/getList.htm?op=grid";

            $("#listgrid").ligerGrid({
                columns: [${gridComluns}],
                url: url_1,
                parms: [],
                showTitle: false,
                dataAction: "server",
                sortName: "ORD",
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

        function f_onSelect(node) {
            menuId = node.data.id;
            f_query();
        }

        function h2y_add() {

            var src = "sys/paramtype/add.htm?op=add";

            $.ligerDialog.open({
                name: "sys_paramtype_add",
                title: "添加参数类型",
                height: 340,
                url: src,
                width: 700,
                showMax: true,
                showToggle: true,
                showMin: true,
                isResize: true,
                modal: true,
                buttons: [
                    {
                        text: '确定', onclick: function (item, dialog) {
                        f_getframe("sys_paramtype_add").h2y_save();
                    }
                    },
                    {
                        text: '取消', onclick: function (item, dialog) {
                        dialog.close();
                    }
                    }
                ]
            });
        }

        function h2y_modify() {

            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            <%--注意该处url可能不符合你的要求，请注意修改--%>
            var src = "<%=basePath%>sys/paramtype/add.htm?op=modify&id=" + rows[0].ID;

            $.ligerDialog.open({
                name: "sys_paramtype_modify",
                title: "修改参数类型",
                height: 340,
                url: src,
                width: 700,
                showMax: true,
                showToggle: true,
                showMin: true,
                isResize: true,
                modal: true,
                buttons: [
                    {
                        text: '确定', onclick: function (item, dialog) {
                        f_getframe("sys_paramtype_modify").h2y_save();
                    }
                    },
                    {
                        text: '取消', onclick: function (item, dialog) {
                        dialog.close();
                    }
                    }
                ]
            });

        }

        function h2y_delete() {

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

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("sys/paramtype/delete.htm?id=" + rows[0].ID, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    f_query();
                } else {
                    alert(jsonReturn.msg);
                }
            });
        }

        function h2y_refresh() {
            document.location.reload();
        }

        function f_query() {

            var manager = $("#listgrid").ligerGetGridManager();
            manager.loadData(true);
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