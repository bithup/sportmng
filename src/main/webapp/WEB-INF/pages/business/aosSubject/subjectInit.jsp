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
        var url_1="";
        var col=[];
        $(function () {
            f_getList();
        });
        function f_getList() {
            var isYes=$("#isYes").val();
            if (isYes==1){
                var url_1="sport/venue/getList.htm";
                col=[
                    {display: "场馆名称", name: "name", width: 200, align: "left", type: "string"},
                    {display: "场馆地址", name: "address", width: 120, align: "left", type: "string"},
                    {display: "营业时间", name: "businessTime", width: 120, align: "left", type: "string"}
                ];
            }else if (isYes==2){
                var url_1="sport/coach/getList.htm";
                col=[
                    {display: "教练名称", name: "name", width: 200, align: "left", type: "string"},
                    {display: "教练性别", name: "sex", width: 120, align: "left", type: "string"},
                    {display: "教练年龄", name: "age", width: 120, align: "left", type: "string"},
                    {display: "运动类型", name: "sportsType", width: 120, align: "left", type: "string"}
                ];
            }else if (isYes==3){
                var url_1="business/activity/getList.htm";
                col=[
                    {display: "活动名称", name: "activityName", width: 180, align: "left", type: "string"},
                    {display: "活动类型", name: "activityType", width: 120, align: "left", type: "string"},
                    {display: "活动地点", name: "activityAddress", width: 180, align: "left", type: "string"},
                    {display: "开始时间", name: "startDate", width: 120, align: "left", type: "string"},
                    {display: "结束时间", name: "endDate", width: 120, align: "left", type: "string"}
                ];
            }
            $("#listgrid").ligerGrid({
                url: url_1,
                columns:col,
                parms: [
                    {name: "op", value: "grid"}
                ],
                checkbox: false,
                checked: false,
                showTitle: false,
                dataAction: "server",
                sortName: "ord",
                pageSize: 20,
                height: "100%",
                width: "100%",
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                onSelectRow: function (row, index, data) {
                   // id = row.id;
                },
                onDblClickRow: function (row, index, data) {
                    parent.h2y_subjectSelectCallBack(h2y_returnData());
                    frameElement.dialog.close();
                }
            });
        }

        /**
         * 获取回调函数
         * @returns {*}
         */
        function h2y_returnData() {
            var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows.length < 1) {
                alert("请选择行!");
                return;
            } else {
                 return rows;
            }
        }
    </script>
</head>
<body>
<div id="layout1" style="width: 100%">

    <div position="left" style="display:none;height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
    <div position="center" title="">
        <div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
            <table id='id-querytable' class='css-querytable'>
                <tr height='25px'>
                    <input type="hidden" id="isYes" name="isYes" value='${isYes}'/>
                </tr>
            </table>
        </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>