var SystemLog = function(){  
    this.init = function(){  
        $(document).on('click','#search',function(){
        	systemLog.settingQuery();
        });
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        $('#dataList').html('');
        var url = 'systemLog/getAllSystemLog.do';  
        var logtype = $('#logtype').val();
        var inquireData = systemLog.acquireInquireData();
        inquireData.logtype = logtype;
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                systemLog.callback(result);  
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
                xHtml += '<tr>';  
                xHtml += '<td>'+ list[i].logtype +'</td>';  
                xHtml += '<td>'+ list[i].createby + '</td>';
                xHtml += '<td>'+ new Date(list[i].createdate).toLocaleString() + '</td>';
                xHtml += '<td>'+ list[i].description + '</td>';
                xHtml += '</tr>';
            }  
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, systemLog.clickPage,systemLog.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        }
    };  
      
    this.clickPage = function(page){  
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        systemLog.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	systemLog.settingQuery();
    }
};  
var systemLog;  
$(function(){  
      
    systemLog = new SystemLog();  
    systemLog.init();  
    //默认显示查询结果  
    systemLog.settingQuery();  
});
