$(function () {
    $("#jqGrid").jqGrid({
        url: path+'sysRole/list',
        datatype: "json",
        colModel: [			
			{ label: '角色ID', name: 'roleId', width: 45, key: true, hidden:true },
			{ label: '角色名称', name: 'roleName', width: 50 },
			{ label: '备注', name: 'remark', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 140, formatter: function(value, options, row){
				return value!=null ?new Date(parseInt(value)): " ";
			}}                   
        ],
		viewrecords: true,
        height: 500,
        rowNum: 10,
		rowList : [10,20,30],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        multiboxonly: true,
        multiselectWidth:40,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order:"order",
            sidx:"sidx"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;
	
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			roleName: null
		},
		showList: true,
		title:null,
		role:{}
	},
	methods: {
		/**
		 * 查询
		 */
		query: function () {
			vm.reload();
		},
		/**
		 * 打开添加界面
		 */
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.role = {};
			vm.getMenuTree(null);
		},
		/**
		 * 打开更新界面
		 */
		update: function () {
			var roleId = getSelectedRow();
			if(roleId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
            vm.getMenuTree(roleId);
		},
		/**
		 * 删除
		 */
		del: function (event) {
			var roleIds = getSelectedRows();
			if(roleIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: path+"sysRole/delete",
				    data: JSON.stringify(roleIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		/**
		 * 查询角色信息
		 */
		getRole: function(roleId){
            $.get(path+"sysRole/info/"+roleId, function(r){
            	vm.role = r.role;
                
                //勾选角色所拥有的菜单
    			var menuIds = vm.role.menuIdList;
    			for(var i=0; i<menuIds.length; i++) {
    				var node = ztree.getNodeByParam("menuId", menuIds[i]);
    				ztree.checkNode(node, true, false);
    			}
    		});
		},
		/**
		 * 添加或更新
		 */
		saveOrUpdate: function (event) {
			//获取选择的菜单
			var nodes = ztree.getCheckedNodes(true);
			var menuIdList = new Array();
			for(var i=0; i<nodes.length; i++) {
				menuIdList.push(nodes[i].menuId);
			}
			vm.role.menuIdList = menuIdList;
			
			var url = vm.role.roleId == null ? path+"sysRole/save" : path+"sysRole/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.role),
			    success: function(r){
			    	if(r.code == 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		/**
		 * 加载菜单树
		 */
		getMenuTree: function(roleId) {
			$.get(path+"sysMenu/perms", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				//展开所有节点
				ztree.expandAll(true);
				
				if(roleId != null){
					vm.getRole(roleId);
				}
			});
	    },
	    /**
	     * 重新加载列表
	     */
	    reload: function (event) {
	    	vm.showList = true;
			//var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'roleName': vm.q.roleName},
                page:0
            }).trigger("reloadGrid");
		}
	}
});