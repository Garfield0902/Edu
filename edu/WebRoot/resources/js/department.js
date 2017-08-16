
var zTree =function(){
			this.querytree = function(){
				var url = 'department/getDeptList.do';
				var obj = {};
		        $.ajax({
		            type: 'post',  
		            async: true,  
		            url: url,  
		            data: JSON.stringify(obj),
		            dataType: "JSON",
		            contentType:'application/json;charset=UTF-8',//关键是要加上这行
		            traditional:true,//这使json格式的字符不会被转码
		            success: function (result) {
		            	ztree.flushtree(result.list);
		            }
		        });
			},
			this.flushtree = function(zNodes){
				setting = {
				        view: {
				            selectedMulti: false        //禁止多点选中  
				        },
				        data: {
				            simpleData: {
				                enable:true,
				                idKey: "id",
				                pIdKey: "pid",
				                rootPId: ""
				            }
				        },
				        key: {
				        	name : "dname",
				        },
				        callback: {
				            onClick: function(treeId, treeNode) {
				                var treeObj = $.fn.zTree.getZTreeObj(treeNode);
				                var selectedNode = treeObj.getSelectedNodes()[0];
				                $("#txtId").val(selectedNode.id);
				                $("#txtAddress").val(selectedNode.dname);
				//                querytree(selectedNode.id);
				                ztree.searchData(selectedNode.id);
				            }
				        }
				    },
				$(document).ready(function(){
		            var tree = $.fn.zTree.init($("#list"),setting, zNodes);
		            tree.expandAll(true);
		        });
			},
			this.inittree = function(){
				this.querytree();
			},
			this.searchData = function(id){
			}
}

function searchData1(id){
	//	查询相关组织下的子组织；
	var url = 'department/getDeptByPID.do';
	var obj = {};
	if(id && id!=null){
		obj.pid=id;
	}else{
		alert("请选择需要查询的部门！");
		return;
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
        	callBack(result.list);
        }
    });
    
    function callBack(list){
        var xHtml = '';  
        if (list.length == 0) {  
            xHtml += '<tr><td colspan="2">没有数据</td></tr>';  
            $('#dataList').html(xHtml);  
        } else {  
            for (var i = 0; i < list.length; i++) {  
            	var did = list[i].id;
            	var dname = list[i].dname;
            	var dcode = list[i].dcode;
            	var dtype = list[i].dtype;
            	var pid = list[i].pid;
            	var dorder = list[i].dorder;
            	
            	xHtml += '<tr><td><input type="checkbox" did="'+did+'" name="checkbox">'+ i +'</td>'+
		        '<td>'+ dname +'</td>'+
		        '<td>'+ dcode +'</td>'+
		        '<td>'+ dtype +'</td>'+
		        '<td>'+ pid +'</td>'+
		        '<td>'+ dorder +'</td>'+
		        '<td><button type="button" flag="deptmodify_" did="'+did+'" dname="'+dname+'" dcode="'+dcode+'" dtype="'+dtype+'" dpid="'+pid+'" dorder="'+dorder+'" class="btn btn-info"	data-toggle="modal" data-target="#modifydept">	<span>修改</span>	</button></td>' +
            	'</tr>';
//            	 /*<button type="button" flag="deptview_" did="'+did+'" dname="'+dname+'" dcode="'+dcode+'" dtype="'+dtype+'" dpid="'+pid+'" dorder="'+dorder+'" class="btn btn-info" data-toggle="modal" data-target="#viewdept"><span>查看</span></button>  */
            }
            $('#dataList').html(xHtml);
            
            //添加查看事件
//            $("#dataList  button[flag='deptview_']").each(function(){
//            	$(this).click(function(){
//                	$("#view_did").val($(this).attr("did"));
//                	$("#view_dname").val($(this).attr("dname"));
//                	$("#view_dcode").val($(this).attr("dcode"));
//                	$("#view_dtype").val($(this).attr("dtype"));
//                	$("#view_pid").val($(this).attr("pid"));
//                	$("#view_dorder").val($(this).attr("dorder"));
//            	});
//            });
            
            //添加修改事件
            $("#dataList  button[flag='deptmodify_']").each(function(){
            	$(this).click(function(){
                	$("#edit_did").val($(this).attr("did"));
                	$("#edit_dname").val($(this).attr("dname"));
                	$("#edit_dcode").val($(this).attr("dcode"));
                	$("#edit_dtype").val($(this).attr("dtype"));
                	$("#edit_pid").val($(this).attr("pid"));
                	$("#edit_dorder").val($(this).attr("dorder"));
            	});
            });
        }
    }
}

function adddept(){
	//校验要添加的部门信息
	//先校验，然后提交
	
	$("#adddept_form").submit();
}
function modifydept(){
	//先校验，然后提交
	
	$("#modifydept_form").submit();
}

function deletedepts(){
	var dids="";
	$("#dataList input[name='checkbox']:checkbox:checked").each(function(){
		dids +=$(this).attr("did")+",";
	});
	if(dids.length<=0){
		alert("请选择要删除的数据");
		return ;
	}
	var ids = dids.substring(0,dids.length-1);
	
	var url = "department/delDept.do";
	var data ={};
	data.ids = ids;
	$.post(url,data,function(data){
		console.log(data);
	},"json");//这里返回的类型有：json,html,xml,text
}


var ztree ;
$(function(){
	ztree = new zTree();
	ztree.inittree();
	ztree.searchData=searchData1;
	//默认查询一级节点的子几点，初始化右侧列表
	searchData1(1);
	
	//添加按钮事件
	$("#adddept_submit").click(adddept);
	$("#modifydept_submit").click(modifydept);
	$("#delete_btn").click(deletedepts);
});
