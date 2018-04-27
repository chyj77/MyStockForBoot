var zdbDiv;

function getZdb(tabid) {
    // console.log("'maingridDfbStock'");
    var rootDiv = $('<div>', {'id': 'zdbStock' + tabid, 'class': 'l-tab-content-item'});
    var lhbDiv = $('<div>');
    var clearDiv = $('<div>', {'class': 'l-clear'});
    var zdbhDiv = $('<div>', {'style': 'padding-top:10px;overflow-y:scroll;padding-left:30px;float:left;width:98%;height:800px'});
    zdbDiv = $('<div>', {'style': 'padding-top:10px'});
    var maingridZfDiv = $('<div>', {
        'id': 'maingridZfbStock',
        'class': 'l-tab-content-item',
        'style': 'overflow-y:scroll;float:left;'
    });
    var maingridDfDiv = $('<div>', {
        'id': 'maingridDfbStock',
        'class': 'l-tab-content-item',
        'style': 'overflow-y:scroll;float:left;padding-left:30px;'
    });
    lhbDiv.prepend(clearDiv);
    zdbhDiv.append(maingridZfDiv);
    zdbhDiv.append(maingridDfDiv);
    lhbDiv.append(zdbDiv);
    lhbDiv.append(zdbhDiv)
    rootDiv.append(lhbDiv);
    $("div[tabid='" + tabid + "']").append(rootDiv);
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: '/spmm/zdt',
        success: function (data) {
            var resultData2 = JSON.parse(data);
            appendZdbData(resultData2);
        }
    });
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: '/spmm/zdfb'
    });
    var zfbData = {
        'code': '',
        'name': '',
        'settlement': '',
        'open': '',
        'trade': '',
        'changepercent': '',
        'turnoverratio': ''
    };
    var dfbData = {
        'code': '',
        'name': '',
        'settlement': '',
        'open': '',
        'trade': '',
        'changepercent': '',
        'turnoverratio': ''
    };

    var zfbDatas = {"Rows": zfbData, "Total": 100};
    var dfbDatas = {"Rows": dfbData, "Total": 100};
    maingridZfDiv.ligerGrid({
        columns: [
            {display: '股票代码', name: 'code', align: 'left', width: 60, minWidth: 50},
            {display: '股票名称', name: 'name', minWidth: 60},
            {display: '昨收', name: 'settlement', minWidth: 50},
            {
                display: '开盘', name: 'open', minWidth: 50, render: function (item, index, val, obj) {//item 行数据 index 行号 val 列值 obj grid对象
                    var open = parseFloat(item.open);
                    var settlement = parseFloat(item.settlement);
                    if (open > settlement)
                        return '<span style="color:red">' + item.open + '</span>';
                    else
                        return '<span style="color:green">' + item.open + '</span>';
                }
            },
            {
                display: '最新', name: 'trade', minWidth: 50, render: function (item) {
                    var trade = parseFloat(item.trade);
                    var settlement = parseFloat(item.settlement);
                    if (trade > settlement)
                        return '<span style="color:red">' + item.trade + '</span>';
                    else
                        return '<span style="color:green">' + item.trade + '</span>';
                }
            },
            {
                display: '涨幅', name: 'changepercent', minWidth: 50, render: function (item) {
                    return '<span style="color:red">' + item.changepercent + '%</span>';
                }
            },
            {
                display: '换手率', name: 'turnoverratio', minWidth: 50, render: function (item) {
                    return '<span style="color:red">' + item.turnoverratio + '%</span>';
                }
            }
        ], data: zfbDatas, pageSize: 100, rownumbers: true, width: 770, onSelectRow: function (item, rid, trRow) {
            // console.log(item);
            // console.log(rid);
            // console.log(trRow);
            $.ajax({
                type: 'GET',
                contentType: 'application/json',
                url: '/spmm/getStock?stockcode=' + item.code,
                success: function (data) {
                    if (data != undefined && data != '') {
                        var resultData3 = JSON.parse(data);
                        // console.log($(trRow).children(":first"));
                        $(trRow).ligerTip({content: resultData3.tag});
                        setTimeout(function () {
                            $(trRow).ligerHideTip();
                        }, 5000);
                    }
                }
            });
        }
    });
    maingridDfDiv.ligerGrid({
        columns: [
            {display: '股票代码', name: 'code', align: 'left', width: 60, minWidth: 50},
            {display: '股票名称', name: 'name', minWidth: 60},
            {display: '昨收', name: 'settlement', minWidth: 50},
            {
                display: '开盘', name: 'open', minWidth: 50, render: function (item) {
                    var open = parseFloat(item.open);
                    var settlement = parseFloat(item.settlement);
                    if (open > settlement)
                        return '<span style="color:red">' + item.open + '</span>';
                    else
                        return '<span style="color:green">' + item.open + '</span>';
                }
            },
            {
                display: '最新', name: 'trade', minWidth: 50, render: function (item) {
                    var trade = parseFloat(item.trade);
                    var settlement = parseFloat(item.settlement);
                    if (trade > settlement)
                        return '<span style="color:red">' + item.trade + '</span>';
                    else
                        return '<span style="color:green">' + item.trade + '</span>';
                }
            },
            {
                display: '跌幅', name: 'changepercent', minWidth: 50, render: function (item) {
                    return '<span style="color:darkgreen">' + item.changepercent + '%</span>';
                }
            },
            {
                display: '换手率', name: 'turnoverratio', minWidth: 50, render: function (item) {
                    return '<span style="color:darkgreen">' + item.turnoverratio + '%</span>';
                }
            }
        ], data: dfbDatas, pageSize: 100, rownumbers: true, width: 770, onSelectRow: function (item, rid, trRow) {
            // console.log(item);
            // console.log(rid);
            // console.log(trRow);
            $.ajax({
                type: 'GET',
                contentType: 'application/json',
                url: '/spmm/getStock?stockcode=' + item.code,
                success: function (data) {
                    if (data != undefined && data != '') {
                        var resultData3 = JSON.parse(data);
                        // console.log($(trRow).children(":first"));
                        $(trRow).ligerTip({content: resultData3.tag});
                        setTimeout(function () {
                            $(trRow).ligerHideTip();
                        }, 5000);
                    }
                }
            });
        }
    });
}

function appendZdbData(zdtData) {
    zdbDiv.empty();
    var zdtJson = zdtData;
    var ztzs = zdtJson["ztzs"];
    // console.log("ztzs = ",ztzs);
    var dtzs = zdtJson["dtzs"];
    var z8to10 = zdtJson["z8to10"];
    var z6to8 = zdtJson["z6to8"];
    var z4to6 = zdtJson["z4to6"];
    var z2to4 = zdtJson["z2to4"];
    var z0to2 = zdtJson["z0to2"];
    var d0to2 = zdtJson["d0to2"];
    var d2to4 = zdtJson["d2to4"];
    var d4to6 = zdtJson["d4to6"];
    var d6to8 = zdtJson["d6to8"];
    var d8to10 = zdtJson["d8to10"];
    var dnum = zdtJson["dnum"];
    var znum = zdtJson["znum"];
    var zdtspan = "<li style='padding-top:10px;'><span style='padding-left:30px;color:red'>涨停家数：<span style='text-decoration:underline;color:red''>" + ztzs + "</span></span>"
        + "<span style='padding-left:30px;color:darkgreen'>跌停家数：<span style='text-decoration:underline;color:darkgreen''>" + dtzs + "</span></span>"
        + "<span style='padding-left:30px;color:red'>上涨股票：<span style='text-decoration:underline;color:red''>" + znum + "</span></span>"
        + "<span style='padding-left:30px;color:darkgreen'>下跌股票：<span style='text-decoration:underline;color:darkgreen''>" + dnum + "</span></span></li>";
    var zspan = "<li style='padding-top:10px;'><span style='padding-left:30px;color:red'>涨8%-10%：<span style='text-decoration:underline;color:red''>" + z8to10 + "</span></span>"
        + "<span style='padding-left:30px;color:red'>涨6%-8%：<span style='text-decoration:underline;color:red''>" + z6to8 + "</span></span>"
        + "<span style='padding-left:30px;color:red'>涨4%-6%：<span style='text-decoration:underline;color:red''>" + z4to6 + "</span></span>"
        + "<span style='padding-left:30px;color:red'>涨2%-4%：<span style='text-decoration:underline;color:red''>" + z2to4 + "</span></span>"
        + "<span style='padding-left:30px;color:red'>涨0%-2%：<span style='text-decoration:underline;color:red''>" + z0to2 + "</span></span></li>";
    var dspan = "<li style='padding-top:10px;'><span style='padding-left:30px;color:darkgreen'>跌8%-10%：<span style='text-decoration:underline;color:darkgreen''>" + d8to10 + "</span></span>"
        + "<span style='padding-left:30px;color:darkgreen'>跌6%-8%：<span style='text-decoration:underline;color:darkgreen''>" + d6to8 + "</span></span>"
        + "<span style='padding-left:30px;color:darkgreen'>跌4%-6%：<span style='text-decoration:underline;color:darkgreen''>" + d4to6 + "</span></span>"
        + "<span style='padding-left:30px;color:darkgreen'>跌2%-4%：<span style='text-decoration:underline;color:darkgreen''>" + d2to4 + "</span></span>"
        + "<span style='padding-left:30px;color:darkgreen'>跌0%-2%：<span style='text-decoration:underline;color:darkgreen''>" + d0to2 + "</span></span></li>";
    zdbDiv.append(zdtspan);
    zdbDiv.append(zspan);
    zdbDiv.append(dspan);
}

function reloadMaingridZfDiv(zdfData) {
    var zfbDatas = {"Rows": zdfData, "Total": 100};
    var manager = $("#maingridZfbStock").ligerGetGridManager();
    manager.loadData(zfbDatas);
}

function reloadMaingridDfDiv(zdfData) {
    // $("#maingridDfbStock").empty();
    var dfbDatas = {"Rows": zdfData, "Total": 100};
    var manager = $("#maingridDfbStock").ligerGetGridManager();
    manager.loadData(dfbDatas);
}
