var Announcement = function(){  
    this.init = function(){  
        $('#inquireBtn').unbind('click').bind('click', function() {  
            announcement.settingQuery();  
        });  
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');
        $('#dataList').html("");
        var url = 'bmpjxx/allArchives.do';
        var obj = announcement.acquireInquireData();
        
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(obj),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                announcement.callback(result);  
            }
        });
    };  
    
    this.acquireInquireData = function(){ 
        var data = {
        		pageSize:parseInt($('#pageSize').val()),  
                pageNo : $('#pageNo').val()
        };  
        var searchType = $("input[name='searchType']:checked").val();
        data.searchType=searchType;
        if(searchType=='nd'){
        	var pjnf = $("select[name=pjnf]").val();
        	data.pjnf=pjnf;
        }
		if(searchType=='qj'){
			var time_start = $("input[name=time_start]").val();
			var time_end = $("input[name=time_end]").val();
			data.time_start=time_start;
			data.time_end=time_end;
		}
		if(searchType=='zw'){
			var rzzgmcm= $("select[name=rzzgmcm]").val();
			data.rzzgmcm=rzzgmcm;
		}
        return data;  
    };
  
    //返回查询结果  
    this.callback = function(showData) { 
    	var pageNoInput = '<input type="hidden" id="pageNo" value="'+showData.page.pageNo+'"/>';
    	var pageSizeInput = '<input type="hidden" id="pageSize" value="'+showData.page.pageSize+'"/>';
    	$('body').append($(pageNoInput)).append($(pageSizeInput));
        var xHtml = '';  
        var list = showData.list;
        if (!list||list.length == 0) {  
            xHtml += '<tr><td colspan="2">没有数据</td></tr>';  
            $('#dataList').html(xHtml);  
        } else {  
            for (var i = 0; i < list.length; i++) {  
            	var hdnf = list[i].hdnf;
            	var hdzt = list[i].hdzt;
            	var hdsj = list[i].hdsj;
            	var hdzzdw = list[i].hdzzdw;
            	var hdxf = list[i].hdxf;
            	
            	xHtml += '<tr><td>'+ i +'</td>'+
		        '<td>'+ hdnf +'</td>'+
		        '<td>'+ hdzt +'</td>'+
		        '<td>'+ new Date(hdsj).toLocaleString() +'</td>'+
		        '<td>'+ hdzzdw +'</td>'+
		        '<td>null</td>'+
		        '<td>'+hdxf+'</td>'+
		        '</tr>';
            }
            $('#dataList').html(xHtml);
            
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, announcement.clickPage,announcement.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        }
    };
    
    this.clickPage = function(page){
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        announcement.settingQuery();
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	announcement.settingQuery();
    }
};

var announcement;
$(function(){
    announcement = new Announcement();
    announcement.init();
    //默认显示查询结果  
    announcement.settingQuery();
    $("#search").click(search);
    $('#time_start').datetimepicker();
    $('#time_end').datetimepicker();
    $('.datetimepicker').css('width','500px');
});

function search(){
	announcement.settingQuery();
}
