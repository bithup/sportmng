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
                {text: '添加', click: h2y_add, icon: 'add'},
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
            var parentId = $("#parentId").val();
            var url_1 = "sport/childVenue/getList.htm?parentId="+parentId;
            $("#listgrid").ligerGrid({
                columns: [
                    {display:'分场馆名称',name:'venueName',width:'120',align:'left'},
                    {display:'运动类型',name:'kindName',width:'120',align:'left'},
                    {display:'场馆编号',name:'venueNo',width:'120',align:'left'},
                    {display:'单价',name:'price',width:'120',align:'left'},
                    {display:'特惠价',name:'salesPrice',width:'120',align:'left'},
                    /*{display:'单位',name:'unit',width:'120',align:'left'},*/
                    {display:'最大容纳人数',name:'capacity',width:'120',align:'left'},
                    {display:'创建时间',name:'createDate',width:'120',align:'left'}
                ],
                url: url_1,
                parms: [
                    {name: "pid", value: id}, {name: "op", value: "grid"}
                ],
                showTitle: false,
                dataAction: "server",
                sortName: "ord",
                height: "100%",
                width: "100%",
                usePager: false,
                onSelectRow: function (row, index, data) {
                    //id = row.id;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        function h2y_add(){
            var src = "<%=basePath%>sport/childVenue/add.htm?op=add&parentId="+$("#parentId").val();
            top.f_addTab("sport_childVenue_add_htm_op_add_parentId_"+$("#parentId").val(), "添加分场馆", src);
        }

        function h2y_modify(){
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择分场馆');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            var src = "<%=basePath%>sport/childVenue/add.htm?op=modify&id="+rows[0].id;
            top.f_addTab("sport_childVenue_add_htm_op_modify_id_"+rows[0].id, "修改分场馆", src);
        }

        function h2y_delete(){
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择分场馆');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            if (!confirm("删除后不可恢复，确定删除此行吗？")) return;
            $.post("sport/childVenue/delete.htm?id=" + rows[0].id, function (data) {
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
    <input type="hidden " id="parentId" name="parentId" value="${parentId}"/>
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

