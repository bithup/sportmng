<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp"%>
    <script src="<%=uiPath%>lib/jquery-ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
    <script type="text/javascript">
        var id = 0;
        var order = 0;
        var type = 0;

        $(function () {
            $("#layout1").ligerLayout({
                leftWidth: 230,
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

            $("#startTime").timepicker();
            $("#endTime").timepicker();
        });

        function f_onSelect(node) {

            if (node == null || node.data == null || node.data.id == null)
                return;
            type = node.data.id;
            if(type == 1){
                $("#toptoolbar").ligerToolBar({items: [
                    {line: true},
                    {text: '查询', click: h2y_search, icon: 'search'},
                    {line: true},
                    {text: '退款', click: h2y_check, icon: 'check'},
                    {line: true},
                    {text: '刷新', click: h2y_refresh, icon: 'refresh'}
                ]});

                $("#conditiondiv").html("订单号:<input type=\"text \" name=\"orderNo\" id=\"orderNo\"/>"+
                       "用户名:<input type=\"text \" name=\"account\" id=\"account\"/>" +
                       "开始时间：<input type=\"text \" name=\"startTime\" id=\"startTime\" pattern=\"yyyy-MM-dd HH:mm:ss\"/>"+
                       "结束时间：<input type=\"text\" name=\"endTime\" id=\"endTime\" pattern=\"yyyy-MM-dd HH:mm:ss\"/>");
                       $("#startTime").timepicker();
                       $("#endTime").timepicker();

            }else if(type == 2){
                $("#toptoolbar").ligerToolBar({items: [
                    {line: true},
                    {text: '查询', click: h2y_search, icon: 'search'},
                    {line: true},
                    {text: '刷新', click: h2y_refresh, icon: 'refresh'}
                ]});
                $("#conditiondiv").html("订单号:<input type=\"text \" name=\"orderNo\" id=\"orderNo\"/>"+
                        "退款状态：<select id=\"status\" name=\"status\">"+
                        "<option value=\"\">请选择</option>"+
                        "<option value=\"0\">未处理</option>"+
                        "<option value=\"1\">退款成功</option>"+
                        "<option value=\"2\">退款失败</option>"+
                        "</select>"+
                        "开始时间：<input type=\"text \" name=\"startTime\" id=\"startTime\" pattern=\"yyyy-MM-dd HH:mm:ss\"/>"+
                        "结束时间：<input type=\"text\" name=\"endTime\" id=\"endTime\" pattern=\"yyyy-MM-dd HH:mm:ss\"/>");
                $("#startTime").timepicker();
                $("#endTime").timepicker();
            }
            f_getList(type)
        }

        function f_getList(type) {
            var orderNo = $("#orderNo").val();
            var type = type;
            var condation = "";
            if(type==1){
                var account = $("#account").val();
                var startTime = $("#startTime").val();
                var endTime = $("#endTime").val();
                condation = "?orderNo="+orderNo+"&account="+account+"&startTime="+startTime+"&endTime="+endTime+"&type="+type;
            }else if(type==2){
                var status = $("#status option:selected").val();
                var startTime = $("#startTime").val();
                var endTime = $("#endTime").val();
                condation="?orderNo="+orderNo+"&status="+status+"&type="+type+"&startTime="+startTime+"&endTime="+endTime;
            }
            var url_1 = "sport/refund/getList.htm"+condation;
            $("#listgrid").ligerGrid({
                columns: [
                    {display:'用户名',name:'account',width:'120',align:'left'},
                    {display:'订单编号',name:'orderNo',width:'120',align:'left'},
                    {display:'交易号',name:'tradeNo',width:'120',align:'left'},
                    {display:'商品名称',name:'goodsName',width:'180',align:'left'},
                    {display:'订单金额',name:'orderAmount',width:'120',align:'left'},
                    {display:'退款原因',name:'reason',width:'120',align:'left'},
                    {display:'申请时间',name:'createTime',width:'120',align:'left'},
                    {display:'订单状态',name:'status',width:'120',align:'left'}
                ],
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
                    h2y_goodsDetail();
                }
            });
        }

        function h2y_refresh() {
            document.location.reload();
        }

        function f_query() {
            var manager = $("#listgrid").ligerGetGridManager();
            manager.setOptions({
                parms: [{name:'type',value:type}]
            });
            manager.loadData(true);
        }

        function h2y_search() {
            f_getList(type);
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
            window.location.href="sport/refund/alipayRefundMoney.htm?id="+rows[0].id;
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
        <div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
         <%--   订单号:<input type="text " name="orderNo" id="orderNo"/>
            用户名:<input type="text " name="account" id="account"/>
            开始时间：<input type="text " name="startTime" id="startTime" pattern="yyyy-MM-dd HH:mm:ss"/>
            结束时间：<input type="text " name="endTime" id="endTime" pattern="yyyy-MM-dd HH:mm:ss"/>
            退款状态：<select id="status" name="status">
                            <option value="">请选择</option>
                            <option value="0">未处理</option>
                            <option value="1">退款成功</option>
                            <option value="2">退款失败</option>
                     </select>--%>
        </div>

        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>