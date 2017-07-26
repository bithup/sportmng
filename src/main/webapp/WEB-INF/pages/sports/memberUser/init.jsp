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
    <%@ include file="../../include/top_kindeditor.jsp" %>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-slide.min.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
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
            getList();
        });
        function getList() {
            var account = $("#account").val();
            var realName = $("#realName").val();
            var idCard = $("#idCard").val();
            var url_1 = "sport/MemberUser/getList.htm?account=" + account + "&realName=" + realName + "&idCard=" + idCard;
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

        function h2y_add() {
            var src = "sport/MemberUser/add.htm?op=add";
            top.f_addTab("sport_MemberUser_add_htm_op_add", "添加会员", src);
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
            var src = "sport/MemberUser/add.htm?op=modify&id=" + rows[0].id;
            top.f_addTab("sport_MemberUser_add_htm_op_modify_id_" + rows[0].id, "修改会员信息", src);

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
            $.post("sport/MemberUser/save.htm?status=-1&id=" + rows[0].id, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == 1) {
                    alert(jsonReturn.msg);
                    h2y_refresh();
                } else {
                    alert(jsonReturn.msg);
                }
            });

        }

        function h2y_search() {
            getList();
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
        <div id="search">
            用户名：<input type="text " name="account" id="account"/>
            真实姓名：<input type="text " name="realName" id="realName"/>
            身份证号：<input type="text " name="idCard" id="idCard"/>
        </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>
