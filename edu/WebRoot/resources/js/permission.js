var Permission = function(){  
    this.init = function(){  
        $(document).on('click','#search',function(){
        	permission.settingQuery();
        });
        
        $(document).on('click','#addBtn',function(){
        	$('#add').modal();
        	$('#addPermissionForm select,#addPermissionForm input').attr('disabled',false);
        	$('#addModalTitle').text('添加权限');
        });
        $(document).on('click','#addPermissionBtn',function(){
        	$('#addPermissionForm').submit();
        });
        
        $(document).on('click','#deleteBtn',function(){
        	if($('[name=checkbox]:checked').length === 0){
        		$('#deleteWarningText').text('至少选择一个删除！');
        		$('#deleteWarning').modal();
        		return;
        	}
        	$('#delete').modal();
        })
        $(document).on('click','#confirmDelete',function(){
        	permission.deleteDictionary();
        });
        $(document).on('click','#deleteWarning .close',function(){
        	permission.settingQuery(); 
        });
        
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');
        $('#dataList').html('');
        var url = 'permission/getAllPermission.do';
        var inquireData = permission.acquireInquireData();
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
            	permission.callback(result);  
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
        if (!list || list.length == 0) {  
            xHtml += '<tr><td colspan="2">没有数据</td></tr>';  
            $('#dataList').html(xHtml);  
        } else {  
            for (var i = 0; i < list.length; i++) {  
                xHtml += '<tr><td><input value="'+list[i].id+'" type="checkbox" name="checkbox"/>'+(i+1)+'</td>';  
                xHtml += '<td>'+ list[i].token +'</td>';
                xHtml += '<td>'+ list[i].url + '</td>';
                xHtml += '</tr>';
            }
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, permission.clickPage,permission.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        }
    };  
      
    this.clickPage = function(page){
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        permission.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	permission.settingQuery();
    }
    this.deleteDictionary = function(){
    	var para = '';
    	$('[name="checkbox"]:checked').each(function(){
    		para+=($(this).val())+',';
    	})
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'permission/delete.do',
            data: para,
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
            	$('#deleteWarning').modal();
                if(result === 1){
                	$('#deleteWarningText').text('删除成功！');
                	window.location.reload();
                }else{
                	$('#deleteWarningText').text('删除失败！');
                }
            }  
        });
    }
};
var permission;  
$(function(){  
	permission = new Permission();  
	permission.init();  
    //默认显示查询结果  
	permission.settingQuery();  
});
