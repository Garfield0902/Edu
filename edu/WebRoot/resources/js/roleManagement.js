var RoleManagement = function(){  
    this.init = function(){  
    	$(document).on('click','#checkAll',function(){
    		var checkFlag = $(this).prop("checked"); 
    	    $('input[name="checkbox"]').each(function() { 
    	        $(this).prop("checked", checkFlag); 
    	    }); 
        });
        $(document).on('click','#addBtn',function(){
        	$('#add').modal();
        	$('#addRoleManagementForm select,#addroleManagementForm input').attr('disabled',false);
        	$('#addModalTitle').text('添加角色');
        });
        $(document).on('click','.modify',function(){
        	$('#addModalTitle').text('修改角色');
        	$('#add').modal();
        	$('#roleManagementIdInput').val($(this).attr('id'));
        	$('#addRoleManagementForm select,#addRoleManagementForm input').attr('disabled',false);
        	roleManagement.selectroleManagementById($(this).attr('id'));
        });
        $(document).on('click','.view',function(){
        	$('#addModalTitle').text('查看角色');
        	$('#add').modal();
        	$('#addRoleManagementForm select,#addRoleManagementForm input').attr('disabled',true);
        	roleManagement.selectroleManagementById($(this).attr('id'));
        });
        $(document).on('click','#addRoleManagementBtn',function(){
        	$('#addRoleManagementForm').submit();
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
        	roleManagement.deleteDictionary();
        });
        $(document).on('click','#deleteWarning .close',function(){
        	 roleManagement.settingQuery(); 
        });
        $(document).on('click','.permission',function(){
        	var id = $(this).attr('id');
        	$('#permissionModal').modal();
        	
        	var setting = {
                    data: {
                        simpleData: {  
                            enable:true,  
                        }
                    },
                check:{
	                enable: true  
	            }
            };
        	var zNodes =[
        	                { name:"父节点1 - 展开", open:true,
        	                    children: [
        	                        { name:"父节点11 - 折叠",
        	                            children: [
        	                                { name:"叶子节点111"},
        	                                { name:"叶子节点112"},
        	                                { name:"叶子节点113"},
        	                                { name:"叶子节点114"}
        	                            ]},
        	                        { name:"父节点12 - 折叠",
        	                            children: [
        	                                { name:"叶子节点121"},
        	                                { name:"叶子节点122"},
        	                                { name:"叶子节点123"},
        	                                { name:"叶子节点124"}
        	                            ]}
        	                    ]}

        	            ]; 
            $.fn.zTree.init($("#permissionTree"), setting, zNodes);
        });

    };  
    //根据查询条件查询  
  //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        var url = 'roleManagement/getAllRoleManagement.do';
        var inquireData = roleManagement.acquireInquireData();  
        $.ajax({
            type: 'post',
            async: true,  
            url: url,
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
        		roleManagement.callback(result);
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
                xHtml += '<tr><td><input value="'+list[i].id+'" type="checkbox" name="checkbox"/></td>'+
		        '<td>'+ list[i].name +'</td>'+
		        '<td>'+ list[i].desc +'</td>'+
		        '<td><a class="permission" id="'+list[i].id+'" href="javascript:;">分配权限</a>'
                '</tr>';
            }  
            /*<a id="'+list[i].id+'" class="modify" href="javascript:;">修改</a>&nbsp;*/
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, roleManagement.clickPage,roleManagement.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        }
    }; 
      
    this.clickPage = function(page){  
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        roleManagement.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	roleManagement.settingQuery();
    }
    this.deleteDictionary = function(){
    	var para = '';
    	$('[name="checkbox"]:checked').each(function(){
    		para+=($(this).val())+',';
    	})
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'roleManagement/delete.do',  
            data: para,
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
    this.selectroleManagementById = function(id){
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'roleManagement/selectRoleManagementById.do',  
            data: id,
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
				$('#type').val(result.type);
				$('#value').val(result.value);
				$('#orderData').val(result.orderData);
				$('#des').val(result.des);
            }  
        });
    }
};  
var roleManagement; 
$(function(){  
    roleManagement = new RoleManagement();  
    roleManagement.init();  
    //默认显示查询结果  
    roleManagement.settingQuery();  
    $('#search').click(function(){
    	roleManagement.settingQuery();
    });
});
