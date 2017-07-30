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
	var url = location.pathname;
	$('#mainnav-menu .menu-item a').each(function(i,k){
		var href = $(this).attr('href');
		if(href.indexOf(url) > 0){
			$(this).parent().addClass('active');
			if(!$(this).parent().hasClass('menu-item')){
				$(this).parents('ul').siblings('a').trigger('click');
			}
		}
	})
})