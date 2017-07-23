var pageBar={  
    //pageCount 总页数， currentPage 当前页数  
    startIndex:0,  
    endIndex:0,  
    currentIndex:1,  
    deviation:5,  
    pageInit:function(pageCount,currentPage,totalCount,eventFun,eventPageSize){  
        //当前页面  
        pageBar.currentIndex =currentPage;  
        pageBar.clickPage=eventFun;  
        pageBar.setPageSize=eventPageSize;
        var htmlStr= '<ul id="pageBar" class="pagination">';  
        var back=pageBar.currentIndex-1;  
        if(pageBar.currentIndex > 1){  
            back=pageBar.currentIndex-1;  
            htmlStr +='<li class="paginate_button first" onclick="pageBar.clickPage(1)"><a href="javascript:;">首页</a></li>';  
            htmlStr +='<li class="paginate_button" onclick="pageBar.clickPage('+back+')"><a href="javascript:;">上一页</a></li>';  
        }else{  
            htmlStr +='<li class="paginate_button first disabled"><a href="javascript:;">首页</a></li>';  
            htmlStr +='<li class="paginate_button prev disabled"><a href="javascript:;">上一页</a></li>';  
        }  
        htmlStr+='<li class="paginate_button"><a href="javascript:;">当前第'+ currentPage +'页</a></li>';
        if(pageBar.currentIndex!=pageCount && pageCount > 0){  
            var nextPage=back;  
            nextPage= nextPage+2;  
            htmlStr += '<li class="paginate_button" onclick="pageBar.clickPage('+nextPage+')"><a href="javascript:;">下一页</a></li>';  
            htmlStr += '<li class="paginate_button" onclick="pageBar.clickPage('+pageCount+')"><a href="javascript:;">末页</a></li>';  
        }else{  
            htmlStr += '<li class="paginate_button disabled"><a href="javascript:;">下一页</a></li>';  
            htmlStr += '<li class="paginate_button disabled"><a href="javascript:;">末页</a></li>';
        };
        htmlStr += '<li class="paginate_button paginate-mt">&nbsp;&nbsp;&nbsp;总共'+ pageCount +'页,共'+totalCount+'条&nbsp;&nbsp;&nbsp;跳转到第<input class="paginate-input" onkeypress="pageBar.goPage(this)" type="text" value="' + currentPage +'"/>页,</li>';
        htmlStr += '<li class="paginate_button paginate-mt">每页显示条数: <select id="pageSizeSelect" onchange="pageBar.pageSize(this)"><option value="5">5</option><option value="10">10</option><option value="15">15</option><option value="20">20</option></select></li>'
        htmlStr += '</ul>';
        return htmlStr;  
    },  
    //跳转页面  
    goPage:function(obj){  
        if(event.keyCode == "13"){  
            var cruPage = $(obj).val();  
            pageBar.clickPage(cruPage);  
        }  
    },
    pageSize:function(obj){
    	var pageSize = $(obj).val();  
        pageBar.setPageSize(pageSize);
    },
    clickPage:function(){  
    	  
    },
    setPageSize:function(){
    	
    }
};