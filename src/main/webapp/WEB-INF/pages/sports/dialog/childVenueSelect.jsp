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
            /*          $("#layout1").ligerLayout({
             leftWidth: 230,
             height: "100%",
             topHeight: 23,
             allowTopResize: false
             });*/
            f_getList();
        });
        function f_getList() {
            var venueName=$("#venueName").val();
            var url_1 = "sport/childVenue/getListRecommend.htm?isRecommend=0&venueName="+venueName;
            $("#listgrid").ligerGrid({
                columns: [
                    {display: "场馆名称", name: "venueName", width: 150, align: "left", type: "string"},
                    {display: "场馆类型", name: "kindsName", width: 100, align: "left", type: "string"},
                    {display: "所在区域", name: "zoneName", width: 150, align: "left", type: "string"},
                    {display: "详细地址", name: "address", width: 150, align: "left", type: "string"}
                ],
                url: url_1,
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
                    parent.h2y_activityGoodsSelectCallBack(h2y_returnData());
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
                /*                var i = 0;
                 var dataArray = new Array();
                 $(rows).each(function(){
                 alert(this.goods_name);
                 var obj = new Object();
                 obj['id'] = this.id;
                 obj['goods_name'] = this.goods_name;
                 obj['goods_number'] = this.goods_number;
                 obj['member_price'] = this.member_price;
                 obj['sell_price'] = this.sell_price;
                 dataArray[i]=obj;
                 i++;
                 });
                 return dataArray;*/
                return rows;
            }
        }

        function h2y_search() {

            f_getList();
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
                    <td align='right' width='80px'>场馆名称:&nbsp;</td>
                    <td colspan='1' align='left'><input style='width:100px;' id='venueName' name='venueName' type='text'
                                                        value=''/></td>
                    <td align='right' width='80px'>&nbsp;</td>
                    <td colspan='1' align='left'><input style='width:100px;height:30px' id='butFind' name='butFind' type='button'
                                                        value='搜索'onclick="h2y_search()"/></td>
                </tr>
            </table>
        </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>