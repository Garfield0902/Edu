var ActivityManagement = function(){  
    this.init = function(){  
    	$('#hdsj').datetimepicker();
    	$('#bmjzsj').datetimepicker();
    	$(document).on('click','#checkAll',function(){
    		var checkFlag = $(this).prop("checked"); 
    	    $('input[name="checkbox"]').each(function() { 
    	        $(this).prop("checked", checkFlag); 
    	    }); 
        });
    	$(document).on('click','#bmCheckAll',function(){
    		var checkFlag = $(this).prop("checked"); 
    	    $('input[name="bmCheckbox"]').each(function() { 
    	        $(this).prop("checked", checkFlag); 
    	    }); 
        });
        $(document).on('click','#addBtn',function(){
        	$('#tzggbt').val('');
        	$('#tzgghInput').val('');
        	$('#announcementContentInput').val('');
        	editor.txt.html('');
        	$('#add').modal();
        	$('#addModalTitle').text('添加活动');
        });
        $(document).on('click','#modify',function(){
        	if($('[name="checkbox"]:checked').length > 1){
        		$('#deleteWarningText').text('只能选择一个修改！');
        		$('#deleteWarning').modal();
        		return;
        	}
        	if($('[name="checkbox"]:checked').length === 0){
        		$('#deleteWarningText').text('至少选择一个修改！')
        		$('#deleteWarning').modal();
        		return;
        	}
        	$('#add').modal();
        	activityManagement.selectTrainingInfoById($('[name="checkbox"]:checked').val());
        	$('#hdidInput').val($('[name="checkbox"]:checked').val());
        	$('#addModalTitle').text('修改活动');
        });
        $(document).on('click','#bmDeleteBtn',function(){
        	if($('[name="bmCheckbox"]:checked').length === 0){
        		alert('至少选择一条数据!');
        		return;
        	}
        	$('#bmDelete').modal();
        });
        $(document).on('click','#confirmDelete',function(){
        	activityManagement.deleteAnnouncement();
        });
        $(document).on('click','#deleteWarning .close',function(){
        	 activityManagement.settingQuery(); 
        });
        $(document).on('click','#addActivityBtn',function(){
        	$('#activityContentInput').val(editor.txt.html());
        	$('#addActivity').submit();
        });
        $(document).on('click','.view',function(){
        	var id = $(this).attr('id');
        	activityManagement.selectTrainingInfoById(id,'view');
        	activityManagement.selectBMById(id);
        	$('#view').modal();
        });
        $(document).on('click','#bmSearch',function(){
        	activityManagement.selectBMById();
        });
        $(document).on('click','#bmConfirmDelete',function(){
        	activityManagement.deleteBmPeople();
        });
        
    };  
    //根据查询条件查询  
  //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');  
        var url = 'trainingInfo/getAllPxhd.do';
        var startDate = $('#startDate').val();
        var endDate = $('#endDate').val();
        var hdzt = $('#hdzt').val();
        var inquireData = activityManagement.acquireInquireData();  
        inquireData.startDate = startDate;
        inquireData.endDate = endDate;
        inquireData.hdzt = hdzt;
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify(inquireData),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
        		activityManagement.callback(result);  
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
                xHtml += '<tr><td><input value="'+list[i].hdid+'" type="checkbox" name="checkbox"/></td>'+
                '<td>'+ i +'</td>'+
		        '<td>'+ list[i].hdnf +'</td>'+
		        '<td>'+ list[i].hdzt +'</td>'+
		        '<td>'+ new Date(list[i].bmjzsj).toLocaleString() +'</td>'+
		        '<td>'+ new Date(list[i].hdsj).toLocaleString() +'</td>'+
		        '<td>'+ list[i].hddd +'</td>'+
		        '<td>'+ list[i].zjr +'</td>'+
		        '<td>'+ list[i].hdzzdw +'</td>'+
		        '<td>'+ list[i].zdcyrs +'</td>'+
		        '<td>'+ list[i].hdlx +'</td>'+
		        '<td>'+ list[i].bmzt +'</td>'+
		        '<td>'+ list[i].hdxf +'</td>'+
		        '<td>'+ list[i].bmzt +'</td>'+
		        '<td><a id="'+list[i].hdid+'" class="view" href="javascript:;">查看</a>&nbsp;<a href="javascript:;">导出</a>'
                '</tr>';
            }  
            $('#dataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, activityManagement.clickPage,activityManagement.setPageSize);
            $('.search-footer').html(pageBarStr);
            $('#home #pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        } 
    }; 
      
    this.clickPage = function(page){  
        $('#pageNo').val(page);// 修改为当前页,然后翻页查询  
        activityManagement.settingQuery();  
    };    
    this.setPageSize = function(pageSize){
    	$('#pageSize').val(pageSize);
    	activityManagement.settingQuery();
    }
    this.deleteAnnouncement = function(){
    	var para = '';
    	$('[name="checkbox"]:checked').each(function(){
    		para+=($(this).val())+',';
    	})
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'trainingInfo/delete.do',  
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
    this.selectTrainingInfoById = function(id,view){
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'trainingInfo/selectTrainingInfoById.do',  
            data: id,
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
    			if(view !== 'view'){
		    		$('#hdzt').val(result.hdzt);
		    		$('#hdnf').val(result.hdnf);
		    		$('#zjr').val(result.zjr);
		    		$('#hdsj').val(new Date(result.hdsj).toLocaleString());
		    		$('#bmjzsj').val(new Date(result.bmjzsj).toLocaleString());
		    		$('#hddd').val(result.hddd);
		    		$('#zdcyrs').val(result.zdcyrs);
		    		$('#hdjb').val(result.hdjb);
		    		$('#hdxf').val(result.hdxf);
		    		$('#hdlx').val(result.hdlx);
		    		$('#hdStatus').val(result.hdStatus);
		    		$('#hdzzdw').val(result.hdzzdw);
		    		editor.txt.html(result.hdnr);
    			}else{
    				$('#modalTime').text(new Date(result.hdsj).toLocaleString());
    				$('#ModalAddress').text(result.hddd);
    				$('#company').text(result.company);
    				$('#type').text(result.hdlx);
    				$('#number').text(result.dqcyrs);
    			}
            }  
        });
    }
    this.selectBMById = function(id){
    	var inquireData = activityManagement.bmAcquireInquireData();
    	inquireData.zgh = $('#modalZgh').val() === '' ? null : $('#modalZgh').val();
    	inquireData.hdid = id;
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'bmpjxx/getBmListByHdid.do',  
            data: JSON.stringify(inquireData),
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
    		activityManagement.bmCallback(result);  
            }  
        });
    }
    this.bmAcquireInquireData = function(){ 
        var data = {
        		pageSize:parseInt($('#bmPageSize').val()),  
                pageNo : $('#bmPageNo').val()
        };  
        return data;  
    };  
    this.bmCallback = function(showData) { 
    	var pageNoInput = '<input type="hidden" id="bmPageNo" value="'+showData.page.pageNo+'"/>';
    	var pageSizeInput = '<input type="hidden" id="bmPageSize" value="'+showData.page.pageSize+'"/>';
    	$('body').append($(pageNoInput)).append($(pageSizeInput));
        var xHtml = '';  
        var list = showData.list;
        if (list.length == 0) {  
            xHtml += '<tr><td colspan="10">没有数据</td></tr>';  
            $('#bMDataList').html(xHtml);  
        } else {  
            for (var i = 0; i < list.length; i++) {  
                xHtml += '<tr><td><input value="'+ list[i].id +'" type="checkbox" name="bmCheckbox"/></td>'+
                '<td>'+ i+1 +'</td>'+
		        '<td>'+ list[i].zgh +'</td>'+
		        '<td>'+ list[i].xm +'</td>'+
		        '<td>'+ list[i].xymc +'</td>'+
                '</tr>';
            }  
            $('#bMDataList').html(xHtml);  
            var pageBarStr = pageBar.pageInit(showData.page.totalPage, showData.page.pageNo,showData.page.totalCount, activityManagement.bmClickPage,activityManagement.bmSetPageSize);
            $('.bm-search-footer').html(pageBarStr);
            $('#pageSizeSelect option[value="'+$('#pageSize').val()+'"]').attr("selected", true);
        } 
    };
    this.bmClickPage = function(page){  
        $('#bmPageNo').val(page);// 修改为当前页,然后翻页查询  
        activityManagement.selectBMById();  
    };    
    this.bmSetPageSize = function(pageSize){
    	$('#bmPageSize').val(pageSize);
    	activityManagement.selectBMById();
    };
    this.deleteBmPeople = function(){
    	var para = '';
    	$('[name="bmCheckbox"]:checked').each(function(){
    		para+=($(this).val())+',';
    	})
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'trainingInfo/bmDelete.do',  
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
};  
var activityManagement; 
var E = window.wangEditor;
var editor = new E('#activityContent');
editor.create();
$(function(){  
    activityManagement = new ActivityManagement();  
    activityManagement.init();  
    //默认显示查询结果  
    activityManagement.settingQuery();  
    $('#search').click(function(){
    	activityManagement.settingQuery();
    });
});
