var Announcement = function(){  
    this.init = function(){  
        $('#inquireBtn').unbind('click').bind('click', function() {  
//            $('#pageNo').val(1);// 每次查询都默认为打开第一页  
            announcement.settingQuery();  
        });  
        
        $(document).on('click','.announcementContentLink',function(){
        	$('#announcementContent').modal();
        	announcement.selectAnnouncementById($(this).attr('id'));
        });
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        var url = 'announcement/getAllTzgg.do';  
        var inquireData = announcement.acquireInquireData();  
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
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
            	var din = list[i].tzggbz === 1 ? '[置顶]' : '';
                xHtml += '<tr>';  
                xHtml += '<td>'+din+'<a id="'+ list[i].tzggh +'" href="javascript:;" class="announcementContentLink">'+ list[i].tzggbt + '</a></td>';  
                xHtml += '<td width="20%">'+ new Date(list[i].createAt).toLocaleString() + '</td>';  
                xHtml += '</tr>';
                $('#announcementContentModalLabel').text(list[i].tzggbt);
                $('#announcementContentModalText').html(list[i].tzggnr);
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
    this.selectAnnouncementById = function(id){
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'announcement/selectAnnouncementById.do',  
            data: id,
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
    			$('#announcementContentModalText').html(result.tzggnr);
    			$('#announcementContentModalLabel').text(result.tzggbt);
    			//editor.txt.html(result.nr);
            }  
        });
    }
};  
var announcement;  
$(function(){  
      
    announcement = new Announcement();  
    announcement.init();  
    //默认显示查询结果  
    announcement.settingQuery();  
});
