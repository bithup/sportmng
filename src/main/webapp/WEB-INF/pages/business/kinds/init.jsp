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
        var is_last = 0;
        var code = "";
        $(function () {
            //　 添加，删除按钮
            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
            //布局模式
            $("#layout1").ligerLayout({
                leftWidth: 300,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });
            //分类1这个名称
            $("#tree1").ligerTree({
                data:${treedata},
                checkbox: false,
                delay: 2,
                nodeWidth: 120,
                onSelect: f_onSelect,
                //id 字段名
                idFieldName: "id",
                //父节点字段名
                parentIDFieldName: "pid",
                //文本字段名
                textFieldName: "text"
            });
            f_getList();
        });

        function f_getList() {
            var url_1 = "business/kinds/getList.htm";
            //显示的是名称，编码等列
            $("#listgrid").ligerGrid({
                columns: [${gridComluns}],
                url: url_1,
                parms: [
                    {name: "pid", value: id}, {name: "op", value: "grid"}
                ],
                showTitle: false,
                dataAction: "server",
                sortName: "id",
                pageSize: 20,
                height: "100%",
                width: "100%",
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                onSelectRow: function (row, index, data) {
                    //id = row.ID;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        function f_html(row) {
            return "";
        }

        function f_onSelect(node) {
            if (node == null || node.data == null || node.data.id == null) return;
            id = node.data.id;
            var treeManager = $("#tree1").ligerGetTreeManager();
            var level = node.data.level;
            var hasChild = node.data.children ? true : false;
            if (level!=4 && !hasChild) {
                $.ajax({
                    type: "POST",
                    url: "business/kinds/getChildTreeData.htm?op=tree",
                    data: {kindsId: id},
                    success: function (jsonData) {
                        var nodes = eval("(" + jsonData + ")");
                        treeManager.append(node.target, nodes);
                        f_query();
                    }
                });
            }else{
                f_query();
            }
        }

        function h2y_add() {
            var treemanager = $("#tree1").ligerGetTreeManager();
            //被选中的节点
            var treeNote = treemanager.getSelected();
            if (treeNote == null || treeNote.length == 0) {
                alert("请选择父级！");
                return;
            }
            $.ajax({
                type: "POST",
                url: "business/kinds/getLevel.htm",
                data: {id: id},
                success: function (_jsonData) {
                    var jsonData = eval("(" + _jsonData + ")");
                    if (jsonData.code == 1) {
                       h2y_add2(jsonData);
                    } else {
                        alert("超过三级不能再添加!");
                        return;
                    }
                }
            });
        }

        function h2y_add2(){
            var src = "<%=basePath%>business/kinds/add.htm?op=add&parentId=" + id;
            $.ligerDialog.open({
                name: "sys_kind_add",
                title: "添加分类",
                height: 340,
                url: src,
                width: 700,
                showMax: true,
                showToggle: true,
                showMin: true,
                isResize: true,
                modal: true,
                buttons: [
                    {   //点击确定触发事件
                        text: '确定', onclick: function (item, dialog) {
                        f_getframe("sys_kind_add").h2y_save();
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
            var src = "<%=basePath%>business/kinds/add.htm?op=modify&id=" + rows[0].id;
            $.ligerDialog.open({
                name: "sys_kinds_modify",
                title: "分类修改",
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
                        f_getframe("sys_kinds_modify").h2y_save();
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
            }//一次只能删除一行。
            $.post("business/kinds/delete.htm?id=" + rows[0].id, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    h2y_refresh()
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