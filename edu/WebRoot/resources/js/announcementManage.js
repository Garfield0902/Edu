var AnnouncementManage = function(){  
    this.init = function(){  
        $(document).on('click','#addBtn',function(){
        	$('#tzggbt').val('');
        	$('#tzgghInput').val('');
        	$('#announcementContentInput').val('');
        	editor.txt.html('');
        	$('#add').modal();
        	$('#addModalTitle').text('添加通知公告');
        });
        $(document).on('click','#modify',function(){
        	$('#add').modal();
        	announcementManage.selectAnnouncementById($(this).attr('data-id'));
        	$('#tzgghInput').val($(this).attr('data-id'));
        	$('#addModalTitle').text('修改通知公告');
        });
        $(document).on('click','#deleteBtn',function(){
        	$('#delete').modal();
        	$('#addModalTitle').text('修改通知公告');
        	$('#confirmDelete').attr('data-id',$(this).attr('data-id'))
        });
        $(document).on('click','#confirmDelete',function(){
        	announcementManage.deleteAnnouncement($(this).attr('data-id'));
        });
        $(document).on('click','#deleteWarning .close',function(){
        	 announcementManage.settingQuery(); 
        });
        $(document).on('click','#addAnnouncementBtn',function(){
        	$('#announcementContentInput').val(editor.txt.html());
        	$('#addAnnouncement').submit();
        });
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        var url = 'announcement/getAllTzgg.do';  
        var inquireData = announcementManage.acquireInquireData();  
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                announcementManage.callback(result);  
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
            xHtml += '<tr><td colspan="3">没有数据</td></tr>';  
            $('#dataList').html(xHtml);  
        } else {  
        	for (var i = 0; i < list.length; i++) {  
        		var din = list[i].tzggbz === 1 ? '[置顶]' : '';
                xHtml += '<tr>';  
                xHtml += '<td>'+din+ list[i].tzggbt + '</td>';  
                xHtml += '<td width="20%">'+ new Date(list[i].createAt).toLocaleString() + '</td>';
                xHtml += '<td width="20%"><a id="modify" data-id="'+ list[i].tzggh +'" href="javascript:;">修改</a>&nbsp;<a data-id="'+ list[i].tzggh +'" id="deleteBtn" href="javascript:;">删除</a></td>';
                xHtml += '</tr>';
            } 
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, announcementManage.clickPage,announcementManage.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        } 
    };  
      
    this.clickPage = function(page){  
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        announcementManage.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	announcementManage.settingQuery();
    }
    this.deleteAnnouncement = function(id){
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'announcement/delete.do',  
            data: id,
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
            	$('#deleteWarning').modal();
                if(result === 1){
                	$('#deleteWarningText').text('删除成功！');
                }else{
                	$('#deleteWarningText').text('删除失败！');
                }
            }  
        });
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
    			$('#tzggbt').val(result.tzggbt);
    			editor.txt.html(result.nr);
    			if(result.tzggbz === 1){
    				$('[name="tzggbz"]').prop('checked',true);
    			}else{
    				$('[name="tzggbz"]').prop('checked',false);
    			}
            }  
        });
    }
};  
var announcementManage; 
var E = window.wangEditor;
var editor = new E('#announcementContent');
editor.create();
$(function(){  
    announcementManage = new AnnouncementManage();  
    announcementManage.init();  
    //默认显示查询结果  
    announcementManage.settingQuery();  
});
