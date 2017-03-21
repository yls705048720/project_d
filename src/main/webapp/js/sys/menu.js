
/**
 * 加载菜单列表
 */
$(function () {
    $("#jqGrid").jqGrid({
        url:path+"sysMenu/list",
        //data:menuList_;
        datatype: "json",
        colModel: [			
			{ label: '菜单ID', name: 'menuId', width: 40, key: true, hidden:true },
			{ label: '菜单名称', name: 'name', width: 60 },
			{ label: '上级菜单', name: 'parentName', width: 60, align:'center' },
			{ label: '菜单图标', name: 'icon', width: 50, align:'center',  formatter: function(value, options, row){
				return value == null ? '' : '<i class="'+value+' fa-lg"></i>';
			}},
			{ label: '菜单URL', name: 'url', width: 60 },
			{ label: '授权标识', name: 'perms', width: 200 },
			{ label: '类型', name: 'type', width: 30,align:'center' ,formatter: function(value, options, row){
				if(value == 0){
					return '<span class="label label-primary">目录</span>';
				}
				if(value == 1){
					return '<span class="label label-success">菜单</span>';
				}
				if(value == 2){
					return '<span class="label label-warning">按钮</span>';
				}
			}},
			{ label: '排序号', name: 'orderNum', width: 30, align: 'center'}                   
        ],
		viewrecords: true,
        height: 560,
        rowNum: 15,
		rowList : [15,30,45],
        rownumbers: true, 
        rownumWidth: 45, 
        autowidth:true,
        multiselect: true,
        multiboxonly: true,
        multiselectWidth:40,
       // cellEdit: true,
        //分页组件
        pager: "#jqGridPager",
        caption: '',
        //json的格式
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        //向后台传输的数据
        prmNames : {
            page:"page", 
            rows:"row", 
            order: "order",
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
	}
};
/**
 * zTree
 */
var ztree;

var vm = new Vue({
	el:'#app',
	data:{
		showList: true,
		title: null,
		menu:{
			parentName:null,
			parentId:0,
			type:1,
			orderNum:0
		}
	},
	methods: {
		/**
		 * 加载树形菜单 使用zTree插件
		 */
		getMenu: function(menuId){
			$.get(path+"sysMenu/select", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);
				
				vm.menu.parentName = node.name;
			})
		},
		/**
		 * 显示添加菜单页面
		 */
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.menu = {parentName:null,parentId:0,type:1,orderNum:0};
			vm.getMenu();
		},
		/**
		 * 显示更新页面
		 */
		update: function (event) {
			var menuId = getSelectedRow();
			if(menuId == null){
				return ;
			}
			//获取菜单详细信息
			$.get(path+"sysMenu/info/"+menuId, function(r){
				vm.showList = false;
                vm.title = "修改";
                vm.menu = r.menu;
            });
			
			vm.getMenu();
		},
		/**
		 * 删除菜单
		 */
		del: function (event) {
			var menuIds = getSelectedRows();
			if(menuIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: path+"sysMenu/delete",
				    data: JSON.stringify(menuIds),
				    success: function(r){
				    	if(r.code == 0){
							alert('操作成功', function(index){
							  // location.reload();
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		/**
		 * 更新或修改
		 */
		saveOrUpdate: function (event) {
			var url = vm.menu.menuId == null ? path+"sysMenu/save" : path+"sysMenu/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.menu),
			    success: function(r){
			    	if(r.code == 0){
						alert('操作成功', function(index){
							vm.reload();
							//location.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		menuTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择菜单",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#menuLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单
					vm.menu.parentId = node[0].menuId;
					vm.menu.parentName = node[0].name;
					
					layer.close(index);
	            }
			});
		},
		/**
		 * 重新加载表格
		 */
		reload: function (event) {
			vm.showList = true;
			//var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
             //   page:0
            }).trigger("reloadGrid");
		}
	}
});