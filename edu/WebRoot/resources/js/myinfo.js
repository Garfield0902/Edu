$(function(){
    getMyInfo();
})

function getMyInfo(){
	$.ajax({
        type: 'post',
        async: true,  
        url: 'jsjbxx/getJsjbxxInfo.do',  
        dataType: "JSON",
        contentType:'application/json;charset=UTF-8',//关键是要加上这行
        traditional:true,//这使json格式的字符不会被转码
        success: function (result) {
        	if(!result.body){
        		alert(result.msg);
        		return;
        	}
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