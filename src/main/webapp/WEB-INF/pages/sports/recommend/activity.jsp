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
            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
            $("#layout1").ligerLayout({
                leftWidth: 230,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });
            getList()
        });

        function getList(){
            var name = $("#name").val();
            var url_1 = "business/activity/getList.htm?isRecommend=1&activityName="+name;
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

        //添加热门
        function h2y_add(){
            openChildVenueSelectDialog();
        }

        //取消热门
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
            if (!confirm("确定取消热门吗？")) return;
            $.post("business/activity/update.htm?isRecommend=0&id=" + rows[0].id, function (data) {
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

        //打开活动商品选择
        function openChildVenueSelectDialog() {
            var src = "business/activity/getListSelect.htm";
            $.ligerDialog.open({
                name: "select_activity_dialog",
                title: "选择商品",
                height: 450,
                url: src,
                width: 700,
                showMax: true,
                showToggle: true,
                showMin: true,
                isResize: true,
                modal: true,
                checkbox:true,
                buttons: [
                    {
                        text: '确定', onclick: function (item, dialog) {
                        var data = $("#select_activity_dialog")[0].contentWindow.h2y_returnData();
                        if (data) {
                            h2y_ChildVenueSelectCallBack(data);
                            dialog.close();
                        }
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

        /**
         * 获取回调数据
         * @param data
         */
        function h2y_ChildVenueSelectCallBack(data) {
            if (data.length > 1) {
                alert("请选择单个活动");
                return;
            }
            var childVenue = data[0];
            $.post("business/activity/update.htm?isRecommend=1&id=" + childVenue.id, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    h2y_refresh()
                } else {
                    alert(jsonReturn.msg);
                }
            });
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
            活动名称:<input type="text " name="name" id="name"/>

            <%--开始时间：<input type="text " name="startTime" id="startTime" pattern="yyyy-MM-dd HH:mm:ss">
           结束时间：<input type="text " name="endTime" id="endTime" pattern="yyyy-MM-dd HH:mm:ss">
          开始时间:
             <input name="spreadStartDate" type="text" id="shop_spreadStartDate"  class="h2y_input_just"
                    value="<fmt:formatDate value="${nowdate}"  pattern="yyyy-MM-dd HH:mm"/>" />
             <input id="text" type="button" onclick="text()"/>--%>
        </div>

        <div id="listgrid"></div>
    </div>
</div>

</body>
</html>
