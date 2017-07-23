$(function(){
	$('#mainnav-menu').on('click','.menu-item>a',function(e){
		if($(this).parent().hasClass('active')){
			$(this).parent().removeClass('active');
			$(this).siblings('ul').removeClass('in');
		}else{
			$(this).parent().addClass('active');
			$(this).siblings('ul').addClass('in');
		}
	});
	$('#mainnav-menu').on('click','.menu-item li',function(e){
		$('#mainnav-menu').find('li a').removeClass('active');
		$(this).find('a').addClass('active');
	});
	$('#selectAll').on('click',function(){
		$("input[name='checkbox']").attr("checked",this.checked);
	})
})