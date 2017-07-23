var TrainingInfo = function(){
    this.init = function(){  
    	$(document).on('click','.signUp',function(){
        	var url = 'trainingInfo/signUp.do';  
        	var id = $(this).attr('data-hdid');
            var inquireData = trainingInfo.acquireInquireData();  
            $.ajax({
                type: 'post',  
                async: true,  
                url: url,  
                data: JSON.stringify({hdid:id}),
                dataType: "JSON",
                contentType:'application/json;charset=UTF-8',//关键是要加上这行
                traditional:true,//这使json格式的字符不会被转码
                success: function (result) {
                    if(result ===1){
                    	$('#signUpModalText').text('报名成功');
                    	$('#signUpModal').modal();
                    	trainingInfo.settingQuery(); 
                    }else{
                    	$('#signUpModalText').text('报名失败');
                    	$('#signUpModal').modal();
                    }
                }  
            });
        });
    	$(document).on('click','.cancelSignUp',function(){
        	var url = 'trainingInfo/cancelSignUp.do';  
        	var id = $(this).attr('data-id');
            var inquireData = trainingInfo.acquireInquireData();  
            $.ajax({
                type: 'post',  
                async: true,  
                url: url,  
                data: id,
                contentType:'application/json;charset=UTF-8',//关键是要加上这行
                traditional:true,//这使json格式的字符不会被转码
                success: function (result) {
                    if(result ===1){
                    	$('#signUpModalText').text('取消成功');
                    	$('#signUpModal').modal();
                    	trainingInfo.settingQuery(); 
                    }else{
                    	$('#signUpModalText').text('取消失败');
                    	$('#signUpModal').modal();
                    }
                }  
            });
        })
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        var url = 'trainingInfo/getAllPxhd.do';  
        var inquireData = trainingInfo.acquireInquireData();  
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                trainingInfo.callback(result);  
            }  
        });  
      
    };  
    this.acquireInquireData = function(){ 
        var data = {
        		pageSize:parseInt($('#pageSize').val()),  
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
            xHtml += '<tr><td colspan="10">没有数据</td></tr>';  
            $('#dataList').html(xHtml);  
        } else {  
            for (var i = 0; i < list.length; i++) {  
                detailId = i;  
                var status = list[i].recordStatus === 1 ? '报名中' : '未报名';
                xHtml += '<tr><td>'+ i +'</td>'+
		        '<td>'+ list[i].hdzt +'</td>'+
		        '<td>'+ new Date(list[i].bmsj).toLocaleString() +'</td>'+
		        '<td>'+ new Date(list[i].hdsj).toLocaleString() +'</td>'+
		        '<td>'+ list[i].hddd +'</td>'+
		        '<td>'+ list[i].hdzzdw +'</td>'+
		        '<td>'+ list[i].zdcyrs +'</td>'+
		        '<td>'+ list[i].pjbz +'</td>'+
		        '<td>'+ status +'</td>'
		        if(list[i].recordStatus === 0){
		        	xHtml += '<td><a data-hdid="'+list[i].hdid+'" class="link signUp" href="javascript:;">报名</a></td>';
		        }else{
		        	xHtml += '<td></td>';
		        }
                xHtml += '</tr>';
            }  
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, trainingInfo.clickPage,trainingInfo.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#home #pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        } 
    };  
      
    this.clickPage = function(page){  
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        trainingInfo.settingQuery();  
    };  
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
        trainingInfo.settingQuery();
    }
    
  //根据查询条件查询  
    this.settingQueryBm = function(){  
        $('#pageBarBm').html('');  
        var url = 'trainingInfo/getAllBm.do';  
        var inquireData = trainingInfo.acquireInquireDataBm();  
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                trainingInfo.callbackBm(result);  
            }  
        });  
    };
    this.acquireInquireDataBm = function(){ 
        var data = {
        		pageSize:parseInt($('#pageSizeBm').val()),  
                pageNo : $('#pageNoBm').val()
        };  
        return data;  
    };
    this.callbackBm = function(showData) { 
    	var pageNoInput = '<input type="hidden" id="pageNoBm" value="'+showData.page.pageNo+'"/>';
    	var pageSizeInput = '<input type="hidden" id="pageSizeBm" value="'+showData.page.pageSize+'"/>';
    	$('body').append($(pageNoInput)).append($(pageSizeInput));
        var xHtml = '';  
        var list = showData.list;
        if (list.length == 0) {  
            xHtml += '<tr><td colspan="8">没有数据</td></tr>';  
            $('#dataListBm').html(xHtml);  
        } else {  
            for (var i = 0; i < list.length; i++) {  
                detailId = i;  
                xHtml += '<tr><td>'+ i +'</td>'+
		        '<td>'+ list[i].hdzt +'</td>'+
		        '<td>'+ list[i].bmjzsj +'</td>'+
		        '<td>'+ list[i].hdsj +'</td>'+
		        '<td>'+ list[i].hddd +'</td>'+
		        '<td>'+ list[i].hdzzdw +'</td>'+
		        '<td>'+ list[i].zdcyrs +'</td>'+
		        '<td><a data-id="'+list[i].id+'" class="link cancelSignUp" href="javascript:;">取消报名</a></td>'
                xHtml += '</tr>';
            }  
            $('#dataListBm').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, trainingInfo.clickPageBm,trainingInfo.setPageSizeBm);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        } 
    };
    this.clickPageBm = function(page){  
        $('#pageNoBm').val(page);// 修改为当前页,然后翻页查询  
        trainingInfo.settingQueryBm();  
    };  
    this.setPageSizeBm = function(pageSize){
    	$('#pageSizeBm').val(pageSize);
        trainingInfo.settingQueryBm();
    }
};  
var trainingInfo;  
$(function(){  
      
    trainingInfo = new TrainingInfo();  
    trainingInfo.init();  
    //默认显示查询结果  
    trainingInfo.settingQuery();  
    trainingInfo.settingQueryBm();
});
