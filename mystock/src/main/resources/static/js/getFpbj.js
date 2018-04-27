function getFpbj(tabid) {
    // console.log(tabid);
    var rootDiv  = $('<div>', {'id':'fpbj'+tabid,'class':'l-tab-content-item'});
    var p = $('<p>', {'style':'font-size:18px;font-weight:bold;float:left;display:inline;'});
    p.text("日期： ");
    var lhbDiv  = $('<div>', {'class':'l-tab-content-item','style':'overflow-y:scroll'});
    var rqText =  $('<input>', {'type':'text', 'id':'txt2'});
    var maingridZtfpDiv = $('<div>', {'id': 'maingridZtfp'});
    rootDiv.append(rqText);
    rqText.parent().style='float:left';
    rqText.parent().prepend(p);
    var date = new Date();
    date.setDate(date.getDate()-1);
    // console.log(date);
    rqText.ligerDateEditor({initValue: date.format('yyyy-MM-dd'),onChangeDate: function ()
        {
            console.log('日期:',rqText.val());
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
                    ], data: ztfpDatas, pageSize: 100, rownumbers: true
                });
            }
        }
    });
}