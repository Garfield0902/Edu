var EvaluationManagement = function(){  
    this.init = function(){  
        $(document).on('click','#search',function(){
        	evaluationManagement.settingQuery();
        });
        $(document).on('click','.view',function(){
        	$('#view').modal();
        	var hdid = $(this).attr('id');
        	$('#modalhdid').val(hdid);
        	evaluationManagement.getBmListByHdid(hdid);
        	evaluationManagement.selectTrainingInfoById(hdid);
        });
        $(document).on('click','#bmSearch',function(){
        	evaluationManagement.getBmListByHdid(id);
        });
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        var url = 'bmpjxx/getAllEvaluationManagement.do';  
        var inquireData = evaluationManagement.acquireInquireData();  
        inquireData.startDate = $('#startDate').val();
        inquireData.endDate = $('#endDate').val();
        //inquireData.hdzt = $('#hdzt').val();
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                evaluationManagement.callback(result);  
            }  
        });  
      
    };  
    this.acquireInquireData = function(){ 
        var data = {
        		pageSize: parseInt($('#pageSize').val()),
                pageNo : $('#pageNo').val()
        };  
        return data;  
    };    
  
    //返回查询结果  
    this.callback = function(showData) { 
    	var pageNoInput = '<input type="hidden" id="pageNo" value="'+showData.page.pageNo+'"/>';
    	var pageSizeInput = '<input type="hidden" id="pageSize" value="'+showData.page.pageSize+'"/>';
    	$('body').append($(pageNoInput)).append($(pageSizeInput));
        var xHtml = '';  
        var list = showData.list;
        if (list.length == 0) {  
            xHtml += '<tr><td colspan="2">没有数据</td></tr>';  
            $('#dataList').html(xHtml);  
        } else {  
        	for (var i = 0; i < list.length; i++) {  
        		var hdid = list[i].hdid;
            	var hdnf = list[i].hdnf;
            	var hdzt = list[i].hdzt;
            	var bmjzsj = list[i].bmjzsj;
            	var hdsj = list[i].hdsj;
            	var hdxf = list[i].hdxf;
            	var hddd = list[i].hddd;
            	var zjr = list[i].zjr;
            	var hdzzdw = list[i].hdzzdw;
            	var zdcyrs = list[i].zdcyrs;
            	var hdlx = list[i].hdlx;
            	var recordstatus = list[i].recordstatus;
            	var hdxf = list[i].hdxf;
            	xHtml += '<tr><td><input type="checkbox" name="checkbox" /></td>'+
            	'<td>'+ (i+1) +'</td>'+
		        '<td>'+ hdnf +'</td>'+
		        '<td>'+ hdzt +'</td>'+
		        '<td>'+ new Date(bmjzsj).toLocaleString() +'</td>'+
		        '<td>'+ hdsj +'</td>'+
		        '<td>'+hddd+'</td>'+
		        '<td>'+zjr+'</td>'+
		        '<td>'+hdzzdw+'</td>'+
		        '<td>'+zdcyrs+'</td>'+
		        '<td>'+hdlx+'</td>'+
		        '<td>'+recordstatus+'</td>'+
		        '<td>'+hdxf+'</td>'+
		        '<td><a id="'+'e5b441eae6074016abae806e04884b7f'+'" href="javascript:;" class="view">查看</a>&nbsp;<a href="javascript:;" class="export">导出</a></td>'
		        '</tr>';
            }  
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, evaluationManagement.clickPage,evaluationManagement.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        }
    };  
      
    this.clickPage = function(page){  
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        evaluationManagement.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	evaluationManagement.settingQuery();
    }
    this.getBmListByHdid= function(id){
    	$('#bmPageBar').html('');  
        var url = 'bmpjxx/getBmListByHdid.do';  
        var inquireData = evaluationManagement.bmAcquireInquireData();  
        inquireData.zgh = $('#modalZgh').val() === '' ? null : $('#modalZgh').val();
        inquireData.hdid = id;
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                evaluationManagement.bmCallback(result);  
            }  
        });
    }
    this.bmCallback = function(showData) { 
    	var pageNoInput = '<input type="hidden" id="bmPageNo" value="'+showData.page.pageNo+'"/>';
    	var pageSizeInput = '<input type="hidden" id="bmPageSize" value="'+showData.page.pageSize+'"/>';
    	$('body').append($(pageNoInput)).append($(pageSizeInput));
        var xHtml = '';  
        var list = showData.list;
        if (list.length == 0) {  
            xHtml += '<tr><td colspan="8">没有数据</td></tr>';  
            $('#bmDataList').html(xHtml);  
        } else {  
        	for (var i = 0; i < list.length; i++) {  
        		var zgh = list[i].zgh;
            	var xm = list[i].xm;
            	var xymc = list[i].xymc;
            	var tjxfsj = list[i].tjxfsj;
            	var hdxf = list[i].hdxf;
            	xHtml += '<tr><td><input type="checkbox" name="checkbox" /></td>'+
            	'<td>'+ (i+1) +'</td>'+
		        '<td>'+ zgh +'</td>'+
		        '<td>'+ xm +'</td>'+
		        '<td>'+ xymc +'</td>'+
		        '<td>'+ tjxfsj +'</td>'+
		        '<td>'+hdxf+'</td>'+
		        '<td><a href="javascript:;" class="view">修改</a></td>'
		        '</tr>';
            }  
            $('#bmDataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, evaluationManagement.bmClickPage,evaluationManagement.bmSetPageSize);
            $('.bm-search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        }
    }; 
    this.bmAcquireInquireData = function(){
    	var data = {
        		pageSize: parseInt($('#bmPageSize').val()),
                pageNo : $('#bmPageNo').val()
        };  
        return data;
    }
    this.bmClickPage = function(page){  
        $('#bmPageNo').val(page);// 修改为当前页,然后翻页查询  
        evaluationManagement.bmSettingQuery();  
    };    
    this.bMSetPageSize = function(pageSize){
    	$('#bmDageSize').val(pageSize);
    	evaluationManagement.bmSettingQuery();
    }
    this.selectTrainingInfoById = function(id){
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'trainingInfo/selectTrainingInfoById.do',  
            data: id,
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
	    		$('#modalTime').text(new Date(result.hdsj).toLocaleString());
				$('#ModalAddress').text(result.hddd);
				$('#company').text(result.company);
				$('#type').text(result.hdlx);
				$('#number').text(result.dqcyrs);
            }  
        });
    }
};  
var evaluationManagement;  
$(function(){  
      
    evaluationManagement = new EvaluationManagement();  
    evaluationManagement.init();  
    //默认显示查询结果  
    evaluationManagement.settingQuery();  
});
