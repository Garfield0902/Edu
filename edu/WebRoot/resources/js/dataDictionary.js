var DataDictionary = function(){  
    this.init = function(){  
    	$(document).on('click','#checkAll',function(){
    		var checkFlag = $(this).prop("checked"); 
    	    $('input[name="checkbox"]').each(function() { 
    	        $(this).prop("checked", checkFlag); 
    	    }); 
        });
        $(document).on('click','#addDicType',function(){
        	$('#addDicTypeDialog').modal();
        	$('#addModalTitle').text('添加字典类型');
        });
        $(document).on('click','#addBtn',function(){
//        	$("#id").val("");
        	$('#addDataDictionaryForm')[0].reset();
        	$('#add').modal();
        	$('#addDataDictionaryForm select,#addDataDictionaryForm input').attr('disabled',false);
        	$('#addModalTitle').text('添加数据类型');
        });
        /*$(document).on('click','.modify',function(){
        	$('#addModalTitle').text('修改数据类型');
        	$('#add').modal();
        	$('#dataDictionaryIdInput').val($(this).attr('id'));
        	$('#addDataDictionaryForm select,#addDataDictionaryForm input').attr('disabled',false);
        	dataDictionary.selectDataDictionaryById($(this).attr('id'));
        });
        $(document).on('click','.view',function(){
        	$('#addModalTitle').text('查看数据类型');
        	$('#add').modal();
        	$('#addDataDictionaryForm select,#addDataDictionaryForm input').attr('disabled',true);
        	dataDictionary.selectDataDictionaryById($(this).attr('id'));
        });*/
        $(document).on('click','#addDictionaryTypeBtn',function(){
        	$('#addDictionaryTypeForm').submit();
        });
        
        $(document).on('click','#addDataDictionaryBtn',function(){
        	$('#addDataDictionaryForm').submit();
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
        	dataDictionary.deleteDictionary();
        });
        $(document).on('click','#deleteWarning .close',function(){
        	 dataDictionary.settingQuery(); 
        });
    };  
    //根据查询条件查询  
  //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        var url = 'dataDictionary/getAllDatadictionary.do';
        var type = $('#typeform').val();
        var inquireData = dataDictionary.acquireInquireData();  
        inquireData.type = type === '' ? null : type;
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
        		dataDictionary.callback(result);  
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
		        '<td>'+ list[i].type +'</td>'+
		        '<td>'+ list[i].value +'</td>'+
		        '<td>'+ list[i].orderData +'</td>'+
		        '<td>'+ list[i].des +'</td>'+
		        '<td><a flag="dictview_" id="'+list[i].id+'" class="view" href="javascript:;">查看</a>&nbsp;<a flag="dictmodify_" class="modify" did="'+list[i].id+'" dname="'+list[i].name+'" dvalue="'+list[i].value+'" dtype="'+list[i].type+'" dorderData="'+list[i].orderData+'" ddes="'+list[i].des+'" href="javascript:;">修改</a>'
                '</tr>';
            }
            $('#dataList').html(xHtml);  
            
          //添加查看事件
            $("#dataList  a[flag='dictview_']").each(function(){
            	$(this).click(function(){
            		
                	$("#view_did").val($(this).attr("did"));
                	$("#view_dname").val($(this).attr("dname"));
                	$("#view_dcode").val($(this).attr("dcode"));
                	$("#view_dtype").val($(this).attr("dtype"));
                	$("#view_pid").val($(this).attr("pid"));
                	$("#view_dorder").val($(this).attr("dorder"));
            	});
            });
            
            //添加修改事件
            $("#dataList  a[flag='dictmodify_']").each(function(){
            	$(this).click(function(){
            		$('#addDataDictionaryForm')[0].reset();
            		$("#id").val($(this).attr("did"));
                	$("#name").val($(this).attr("dname"));
                	$("#value").val($(this).attr("dvalue"));
                	$("#type").val($(this).attr("dtype"));
                	$("#orderData").val($(this).attr("dorderData"));
                	$("#des").val($(this).attr("ddes"));
                	$('#add').modal();
            	});
            });
            
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, dataDictionary.clickPage,dataDictionary.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        } 
    }; 
      
    this.clickPage = function(page){  
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        dataDictionary.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	dataDictionary.settingQuery();
    }
    this.deleteDictionary = function(){
    	var para = '';
    	$('[name="checkbox"]:checked').each(function(){
    		para+=($(this).val())+',';
    	})
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'dataDictionary/delete.do',  
            data: para,
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
            	console.log(result);
            	$('#deleteWarning').modal();
                if(result === 1){
                	$('#deleteWarningText').text('删除成功！');
                	location.reload();
                }else{
                	$('#deleteWarningText').text('删除失败！');
                }
            }  
        });
    }
    this.selectDataDictionaryById = function(id){
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'dataDictionary/selectDataDictionaryById.do',  
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
var dataDictionary; 
$(function(){  
    dataDictionary = new DataDictionary();  
    dataDictionary.init();  
    //默认显示查询结果  
    dataDictionary.settingQuery();  
    $('#search').click(function(){
    	dataDictionary.settingQuery();
    });
});
