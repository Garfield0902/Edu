var Announcement = function(){  
    this.init = function(){  
        $('#inquireBtn').unbind('click').bind('click', function() {  
//            $('#pageNo').val(1);// 每次查询都默认为打开第一页  
            announcement.settingQuery();  
        });  
    };  
    //根据查询条件查询  
    this.settingQuery = function(){  
        $('#pageBar').html('');
        $('#dataList').html("");
        
        var url = 'jsjbxx/getAllJsjbxx.do';
        var obj = announcement.acquireInquireData();
    	var zgh = $("#zgh").val();
    	var xm = $("#xm").val();
    	
    	obj["kaptcha"] = 1;
    	if(zgh && zgh.length>0){
    		obj["zgh"] = zgh.trim();
    	}
    	if(xm && xm.length>0){
    		obj["xm"] = xm.trim();
    	}
        
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
            	var zgh = list[i].zgh;
            	var xm = list[i].xm;
            	var xy = list[i].xy;
            	
            	xHtml += '<tr><td>'+ i +'</td>'+
		        '<td>'+ zgh +'</td>'+
		        '<td>'+ xm +'</td>'+
		        '<td>'+ xy +'</td>'+
		        '<td> <a href="javascript:;" flag="tjxf_" zgh="'+zgh+'" xm="'+xm+'" xy="'+xy+'" data-toggle="modal" data-target="#tianjiaxuefen">添加学分</a> &nbsp;&nbsp;<a href="javascript:;" flag="_showda_" zgh="'+zgh+'" xm="'+xm+'" xy="'+xy+'">培训档案</a> </td>'+
		        '</tr>';
            }
            $('#dataList').html(xHtml);
            
            // 给添加学分添加事件
            $("a[flag='tjxf_']").each(function(){
            	$(this).click(function(){
            		var zgh =$(this).attr("zgh");
            		var xm =$(this).attr("xm");
            		var xy =$(this).attr("xy");
            		$("#tjxf_xm").val(zgh);
            		$("#tjxf_zgh").val(xm);
            		$("#tjxf_xy").val(xy);
            	});
            })
            
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

function tjxfSubmit(){
	var E = window.wangEditor
	var editor = new E('#tjxf_hdnr')
	editor.create()
	
	$('#tjxf_save').click(function(){
		alert(editor.txt.html());
		$("#_hdnr_").val(editor.txt.html());
		$('#_tjxf_').submit();
	});
}


var announcement;
$(function(){
    announcement = new Announcement();
    announcement.init();
    //默认显示查询结果  
    announcement.settingQuery();
    $('#search').click(search);
    $('#datetimepicker').datetimepicker();
    $('.datetimepicker').css('width','300px');
   // tjxfSubmit();
    getMyInfo();
});

function search(){
	announcement.settingQuery();
}

function getMyInfo(){
	$.ajax({
        type: 'post',  
        async: true,  
        url: 'jsjbxx/getJsjbxxInfo.do',  
        dataType: "JSON",
        contentType:'application/json;charset=UTF-8',//关键是要加上这行
        traditional:true,//这使json格式的字符不会被转码
        success: function (result) {
        	result = result.body;
        	$('#jzg').text(result.zgh);
        	$('#name').text(result.xm);
        	$('#sex').text(result.xbm);
        	$('#job').text(result.xbm);
        	$('#xueli').text(result.xldm);
        	$('#date').text(result.rxsj);
        	$('#department').text(result.bm);
        	$('#phone').text(result.dwdh);
        	$('#mobile').text(result.sj);
        	$('#ruxiao').text(result.rxsj);
        	$('#prevJob').text(result.aa);
        	$('#email').text(result.dzxx);
        }  
    });
}
