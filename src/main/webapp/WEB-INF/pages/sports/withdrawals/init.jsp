<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
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
            getList()
            $("#startTime").timepicker();
            $("#endTime").timepicker();

        });

        function getList(){
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var drawStatus = 0;
            var param = "?startTime="+startTime+"&endTime="+endTime+"&drawStatus=0";
            var url_1 = "sport/withdrawals/getList.htm"+param;
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

        function h2y_search(){
            getList();
        }

        function h2y_refresh(){
            window.location.reload();
        }

        function h2y_add(){
            var page = $(".pcontrol").find("input").val();
            var pagesize = 20;
            var account = $("#account").val();
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var drawStatus = 0;
            var param = "?account="+account+"&startTime="+startTime+"&endTime="+endTime+"&page="+page+"&pagesize="+pagesize+"&drawStatus="+drawStatus;
            $.post("sport/withdrawals/buildTxt.htm"+param,function (data) {
                /*window.location.href="D:/dp/bootstrap/UI/save_path/提现记录";*/
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    var filePath = jsonReturn.filePath;
                    window.location.href="sport/withdrawals/downLoad.htm?filePath="+filePath;

                }
            });

        }

        function h2y_check(){
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            var src = "sport/withdrawals/get.htm?id="+rows[0].id;
            top.f_addTab("sport_withdrawals_get_htm_id_"+rows[0].id, "提现处理", src);

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
    <input type="hidden " id="status" name="status" value=""/>
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
            用户名： <input type="text" name="account" id="account"/>
            开始时间：<input type="text" name="startTime" id="startTime" pattern="yyyy-MM-dd HH:mm:ss"/>
            结束时间：<input type="text" name="endTime" id="endTime" pattern="yyyy-MM-dd HH:mm:ss"/>
        </div>

        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>
