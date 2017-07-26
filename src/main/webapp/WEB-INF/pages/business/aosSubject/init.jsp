<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script type="text/javascript">
        var dataId = 0;
        var type = 0;
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
                delay: 2,
                onSelect: f_onSelect,
                idFieldName: "id",
                parentIDFieldName: "pid",
                textFieldName: "text"
            });
            f_getList();
        });

        function f_getList() {
            var url = "business/aosSubject/getList.htm";
            $("#listgrid").ligerGrid({
                columns: [${gridComluns}],
                url: url,
                parms: [{name:'dataType',value:'2'},{name:'dataId',value:dataId}],
                showTitle: false,
                dataAction: "server",
                sortName: "ord",
                pageSize: 20,
                height: "100%",
                width: "100%",
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                onSelectRow: function (row, index, data) {
                    id = row.id;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        function h2y_add() {
            if (dataId <1) {
                alert("请选择主题！");
                return;
            }
            var manager = $("#listgrid").ligerGetGridManager();
            if(manager.getData().length>0){
                alert("请先移除现有主题!");
                return;
            }
            var src = "business/aosSubject/add.htm?&dataId=" + dataId;
            top.f_addTab("business_aosSubject_add_htm_dataId_" + dataId, "轮播图主题添加", src);
        }

        /**
         * 修改
         */
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
            var src = "business/aosSubject/add.htm?id=" + rows[0].id;
            top.f_addTab("business_aosSubject_add_htm_id_" + rows[0].id, "轮播图主题修改", src);
        }

        /**
         * 删除
         */
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
            var url_1 = "business/aosSubject/delete.htm?id="+rows[0].id;
            $.post(url_1, function (data) {
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

        function f_onSelect(node) {
            if (node == null || node.data == null || node.data.id == null)
                return;
            type = node.data.pid;
            dataId = node.data.id;
            f_query();
        }

        function f_query() {
            var manager = $("#listgrid").ligerGetGridManager();
            manager.setOptions({
                parms: [{name:'dataId',value:dataId}]
            });
            manager.loadData(true);
        }

    </script>

</head>
<body>
<form id="editForm" method="post">
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
</form>
</body>
</html>