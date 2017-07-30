var Home = function(){  
    this.init = function(){  
    	$('#datetimepicker').datetimepicker();
    	home.getPersonInfo();
    	home.getTrainingInfo();
    	home.getBmInfo();
    	home.trainarchives();
    };  
    this.getPersonInfo = function(){
    	$.ajax({
            type: 'post',  
            async: true,  
            url: 'jsjbxx/getJsjbxxInfo.do',  
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
            	result = result.body;
            	$('#zgh').text(result.zgh);
            	$('#xm').text(result.xm);
            	$('#zw').text(result.zcmc);
            	$('#sclogintime').text(result.rxsj);
            	$('#bm').text(result.bm);
            }  
        });
    }
    this.getTrainingInfo = function(){
    	var url = 'trainingInfo/getAllPxhd.do';  
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify({pageSize: 5, pageNo: 1}),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
        		var list = result.list;
        		var xHtml = '';
	        	for (var i = 0; i < list.length; i++) {  
	                var status = list[i].recordStatus === 1 ? '报名中' : '未报名';
	                xHtml += '<tr><td>'+ list[i].hdzt +'</td>'+
			        '<td>'+ new Date(list[i].hdsj).toLocaleString() +'</td>'+
			        '<td>'+ list[i].zdcyrs +'</td>'+
			        '<td>'+ status +'</td>'
	                xHtml += '</tr>';
	            }  
	            $('#trainingInfo').html(xHtml);
            }  
        });  
    }
    this.getBmInfo = function(){
    	 var url = 'trainingInfo/getAllBm.do';  
         $.ajax({
             type: 'post',  
             async: true,  
             url: url,  
             data: JSON.stringify({pageSize: 5, pageNo: 1}),
             dataType: "JSON",
             contentType:'application/json;charset=UTF-8',//关键是要加上这行
             traditional:true,//这使json格式的字符不会被转码
             success: function (result) {
	        	var list = result.list;
	        	var xHtml = '';
	        	for (var i = 0; i < list.length; i++) {
	                xHtml += '<tr><td>'+ list[i].hdzt +'</td>'+
			        '<td>'+ new Date(list[i].hdsj).toLocaleString() +'</td>'+
			        '<td>'+ list[i].hddd +'</td></tr>';
	            }  
	            $('#bmInfo').html(xHtml);  
             }  
         });
    }
    this.trainarchives = function(){
    	var url = 'bmpjxx/allArchives.do';
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify({pageSize: 5, pageNo: 1}),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
        		var list = result.list;
        		var xHtml = '';
	        	for (var i = 0; i < list.length; i++) {  
	            	var hdzt = list[i].hdzt;
	            	var hdsj = list[i].hdsj;
	            	var hdxf = list[i].hdxf;
	            	
	            	xHtml += '<tr><td>'+ hdzt +'</td>'+
			        '<td>'+ new Date(hdsj).toLocaleString() +'</td>'+
			        '<td>'+hdxf+'</td>'+
			        '</tr>';
	            }
	            $('#pxdaInfo').html(xHtml);
            }
        });
    }
    this.trainarchives = function(){
    	var url = 'announcement/getAllTzgg.do';  
        $.ajax({
            type: 'post',  
            async: true,  
            url: url,  
            data: JSON.stringify({pageSize: 5, pageNo: 1}),
            dataType: "JSON",
            contentType:'application/json;charset=UTF-8',//关键是要加上这行
            traditional:true,//这使json格式的字符不会被转码
            success: function (result) {
        		var list = result.list;
        		var xHtml = '';
	        	for (var i = 0; i < list.length; i++) {  
	                xHtml += '<tr>';  
	                xHtml += '<td>'+ list[i].tzggbt + '</td>';  
	                xHtml += '<td>'+ new Date(list[i].createAt).toLocaleString() + '</td>';  
	                xHtml += '</tr>';
	            }  
	            $('#tzggInfo').html(xHtml); 
            }  
        });
    }
};  
var home;  
$(function(){  
      
    home = new Home();  
    home.init();  
    //默认显示查询结果  
    
});
