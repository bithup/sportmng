<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script type="text/javascript">

        var roleId = 0;
        $(function () {

            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

            $("#layout1").ligerLayout({
                leftWidth: 190,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });

            $("#tree1").ligerTree({
                data:${treedata},
                checkbox: false,
                isExpand: false,
                nodeWidth: 120,
                onSelect: f_onSelect,
                idFieldName: "id",
                parentIDFieldName: "pid",
                textFieldName: "text"
            });

            f_getList();
        });

        function f_getList() {

            var url_1 = "sys/role/getList.htm?listType=roleuser";

            $("#listgrid").ligerGrid({
                columns: [${gridComluns}],
                url: url_1,
                parms: [{name: "roleId", value: roleId}],
                showTitle: false,
                dataAction: "server",
                sortName: "ROLE_ORD",
                pageSize: 20,
                height: "100%",
                width: "100%",
                checkbox: true,
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                tree: {columnName: "NAME"},
                onSelectRow: function (row, index, data) {
                    id = row.ID;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        function f_onSelect(node) {

            if (node == null || node.data == null || node.data.id == null) return;
            roleId = node.data.id + "";
            roleId = roleId.replace(new RegExp("inst_|agents_|sys_|nor_", "gm"), "");
            f_query();
        }

        function h2y_refresh() {
            document.location.reload();
        }

        function f_query() {
            var manager = $("#listgrid").ligerGetGridManager();
            manager.setOptions({
                parms: [{name: "roleId", value: roleId}]
            });
            manager.loadData(true);
        }

        /**
         * 移入人员
         */
        function h2y_move() {

            if (roleId == 0) {
                alert("请选中对应的角色！");
                return;
            }

            openMixSelectDialog();
        }

        function h2y_remove() {

            var manager = $("#listgrid").ligerGetGridManager();

            var checkedRows = manager.getCheckedRows();

            if (checkedRows.length == 0) {
                alert("请选中要移除的用户！");
                return;
            }

            var privilegeIds = "";
            $(checkedRows).each(function () {
                if (privilegeIds == "") {
                    privilegeIds += this.privilege_id;
                } else {
                    privilegeIds += "," + this.privilege_id;
                }
            });

            var src = "sys/role/saveRoleUser.htm";
            $.post(src, {op: "remove", roleId: roleId, privilegeIds: privilegeIds}, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    f_query();
                } else {
                    alert(jsonReturn.msg);
                }
            });
        }

        function h2y_mixSelectCallBack(data) {
            var userData = "";
            $(data).each(function () {
                if (userData == "") {
                    userData = this.ID;
                } else {
                    userData += "," + this.ID;
                }
            });
            if (userData == "") return;
            var src = "sys/role/saveRoleUser.htm";
            $.post(src, {op: "add", roleId: roleId, userData: userData}, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    f_query();
                } else {
                    alert(jsonReturn.msg);
                }
            });
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

    <div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>

    <div position="center" title="">
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>