var User = function(){  
    this.init = function(){  
        $(document).on('click','#search',function(){
        	user.settingQuery();
        });
        
        $(document).on('click','#addBtn',function(){
        	$('#add').modal();
        	$('#addUserForm select,#addUserForm input').attr('disabled',false);
        	$('#addModalTitle').text('添加用户');
        });
        $(document).on('click','#addUserBtn',function(){
        	$('#addUserForm').submit();
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
        	user.deleteDictionary();
        });
        $(document).on('click','#deleteWarning .close',function(){
        	 user.settingQuery(); 
        });
        
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');
        $('#dataList').html('');
        var url = 'user/getAllUser.do';
        var name = $('#search_name').val();
        var status = $('#search_status').val();
        var inquireData = user.acquireInquireData();
        if(name && name.length>0){
        	inquireData.name = name;
        }
        if(status&&status.length>0){
        	inquireData.status = status;
        }
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
                user.callback(result);  
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
                xHtml += '<tr><td><input value="'+list[i].id+'" type="checkbox" name="checkbox"/></td>';  
                xHtml += '<td>'+ list[i].name +'</td>';  
                xHtml += '<td>'+ list[i].status + '</td>';
                xHtml += '<td>'+ new Date(list[i].createdate).toLocaleString() + '</td>';
                xHtml += '<td>权限</td>';
                xHtml += '</tr>';
            }
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, user.clickPage,user.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        }
    };  
      
    this.clickPage = function(page){
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        user.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	user.settingQuery();
    }
    this.deleteDictionary = function(){
    	var para = '';
    	$('[name="checkbox"]:checked').each(function(){
    		para+=($(this).val())+',';
    	})
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'user/delete.do',  
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
var user;  
$(function(){  
    user = new User();  
    user.init();  
    //默认显示查询结果  
    user.settingQuery();  
});
