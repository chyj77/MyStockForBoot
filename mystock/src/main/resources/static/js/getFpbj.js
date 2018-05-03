var win3;
function getFpbj(tabid) {
    // console.log(tabid);
    win3 = $.ligerDialog.open({ title:"一季度超预期",url:"", width: 800,height:600,modal:true });
    win3.hide();
    var rootDiv  = $('<div>', {'id':'fpbj'+tabid,'class':'l-tab-content-item'});
    var p = $('<p>', {'style':'font-size:18px;font-weight:bold;float:left;display:inline;'});
    p.text("日期： ");
    var p1 = $('<a>', {'style':'font-size:12px;font-weight:bold;float:right;display:inline;color:red','onClick':'showJrj()'});
    p1.text("2018年第一季度业绩超预期");
    var lhbDiv  = $('<div>', {'class':'l-tab-content-item','style':'overflow-y:scroll'});
    var rqText =  $('<input>', {'type':'text', 'id':'txt2'});
    var maingridZtfpDiv = $('<div>', {'id': 'maingridZtfp'});
    rootDiv.append(rqText);
    rqText.parent().style='float:left';
    rqText.parent().prepend(p1);
    rqText.parent().prepend(p);
    var date = new Date();
    date.setDate(date.getDate()-1);
    // console.log(date);
    rqText.ligerDateEditor({initValue: date.format('yyyy-MM-dd'),onChangeDate: function ()
        {
            // console.log('日期:',rqText.val());
            lhbDiv.empty();
            executeZtfpShow(rqText.val(),tabid);
            lhbDiv.append(maingridZtfpDiv);
        }});

    // console.log(rqText);
    // console.log(rqText.val());
    lhbDiv.append(maingridZtfpDiv);
    rootDiv.append(lhbDiv);
    $("div[tabid='"+tabid+"']").append(rootDiv);
    var day = rqText.val();
    executeZtfpShow(day,tabid);
}
function executeZtfpShow(day,tabid) {

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: '/ztfp/index',
        data:{'day':day},
        success: function (data) {
            // console.log(data);
            var jsonstr = JSON.parse(data);
            // console.log(jsonstr);
            if (jsonstr.length > 0) {
                var rq = jsonstr[0].rq;
                // var p = $('<p>', {'style':'font-size:18px;font-weight:bold'});
                // p.text("日期： "+rq);
                // lhbDiv.append(p);
                var ztfpData = jsonstr[0].data;
                var ztfpDatas = {"Rows": ztfpData, "Total": 100};
                // console.log(jsonstr['rq']);
                $("#maingridZtfp").ligerGrid({
                    height: '95%',
                    columns: [
                        {
                            display: '证券代码',
                            name: 'stockcode', minWidth: 90, editor: {type: 'text'}
                        },
                        {
                            display: '证券名称',
                            name: 'stockname', minWidth: 90, editor: {type: 'text'}
                        },
                        {
                            display: '现价',
                            name: 'nowprice', minWidth: 90, editor: {type: 'text'}
                        },
                        {
                            display: '涨停时间',
                            name: 'ztsj', minWidth: 90, editor: {type: 'text'}
                        },
                        {
                            display: '涨停明细',
                            name: 'ztmx', minWidth: 90, editor: {type: 'text'}
                        },
                        {
                            display: '涨停天数',
                            name: 'ztts', minWidth: 90, editor: {type: 'text'}
                        },
                        {
                            display: '涨停概念',
                            name: 'ztgn', minWidth: 90, editor: {type: 'text'}
                        }
                    ], data: ztfpDatas, pageSize: 100, rownumbers: true, enabledEdit: true,onReload: ztfpFresh,
                    toolbar: {
                        items: [
                            {text: '保存', click: doSaveFpbj, icon: 'save'}
                        ]
                    }
                });
            }
        }
    });
}
function showJrj() {
    if(win3) {
        win3.show();
        var rootDiv = $(".l-dialog-content.l-dialog-content-noimage");
        var maingridJrjDiv = $('<div>', {'id': 'maingridJrj'});
        var lhbDiv  = $('<div>', {'class':'l-tab-content-item'});
        lhbDiv.append(maingridJrjDiv);
        rootDiv.append(lhbDiv);
        // console.log("'maingridJrj'");
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '/ztfp/jrj',
            data:{'day':'2018-04'},
            success: function (data) {
                // console.log(data);
                var jsonstr = JSON.parse(data);
                // console.log(jsonstr);
                if (jsonstr.length > 0) {
                    var rq = jsonstr[0].rq;
                    var jrjData = jsonstr[0].data;
                    var jrjDatas = {"Rows": jrjData, "Total": 100};
                    // console.log(jrjDatas);
                    maingridJrjDiv.ligerGrid({
                        height: '95%',
                        columns: [
                            {
                                display: '证券名称',
                                name: 'stockname', minWidth: 90, editor: {type: 'text'}
                            },
                            {
                                display: '预告增长',
                                name: 'top', minWidth: 90, editor: {type: 'text'}
                            },
                            {
                                display: '一季报',
                                name: 'quarter1', minWidth: 90, editor: {type: 'text'}
                            },
                            {
                                display: '超预期',
                                name: 'beyond', minWidth: 90, editor: {type: 'text'}
                            },
                            {
                                display: '动态市盈率',
                                name: 'dtsyl', minWidth: 90, editor: {type: 'text'},
                                render:function (item,index,val,cell) {
                                    if(parseFloat(val)<40){
                                        return '<span style="color:red">' + val + '</span>';
                                    }else{
                                        return val;
                                    }

                                }
                            }
                        ], data: jrjDatas, pageSize: 100, rownumbers: true
                    });
                }
            }
        });
    }
}
function doSaveFpbj(item) {
    $("#pageloading").show();
    var manager = $("#maingridZtfp").ligerGetGridManager();
    var rows = manager.rows;

    var rq = $("#txt2").val();
    var param = {"rq":rq,"data":rows};
    console.log(param);
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: '/ztfp/doSaveZtfp',
        data: JSON.stringify(param),
        success: function (data) {
            alert(data);
        }
    });
    $("#pageloading").hide();
}
function ztfpFresh() {
    // console.log("刷新");
    var rq = $("#txt2").val();
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: '/ztfp/index',
        data:{'day':rq},
        success: function (data) {
            var resultData = JSON.parse(data);
            $("#maingridZtfp").loadData(resultData);
            // console.log("刷新完成");
        }
    });
}