//生成菜单
var menuItem = Vue.extend({
	name: 'menu-item',
	props:{item:{}},
	//js === 和 == 的区别
	template: 
		[
	          '<li>',
	          '<a v-if="item.type == 0" href="javascript:;">',
	          '<i v-if="item.icon != null" :class="item.icon"></i>',
	          '<span>{{item.name}}</span>',
	          '<i class="fa fa-angle-left pull-right"></i>',
	          '</a>',
	          '<ul v-if="item.type == 0" class="treeview-menu">',
	          '<menu-item :item="item" :key="item.itemId"  v-for="item in item.childernList"></menu-item>',
	          '</ul>',
	          '<a v-if="item.type == 1" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
	          '</li>'
	].join('')
});

//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

//注册菜单组件
Vue.component('menu-item',menuItem);

var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{},
		menuList:{},
		main:"sys/main.html",
		password:'',
		newPassword:'',
        navTitle:"控制台",
       // router :new Router()
	},
	methods: {
		/**
		 * 获取用户菜单列表
		 */	
		getMenuList: function (event) {
			$.getJSON("sysMenu/user?_"+$.now(), function(r){
				vm.menuList = r.menuList;
			});
		},
		/**
		 * 获取用户信息
		 */
		getUser: function(){
			$.getJSON("sysUser/info?_"+$.now(), function(r){
				vm.user = r.user;
			});
		},
		/**
		 * 更新密码
		 */
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					var data = "password="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
					    url: "sysUser/password",
					    data: data,
					    dataType: "json",
					    success: function(result){
							if(result.code == 0){
								layer.close(index);
								layer.alert('修改成功', function(index){
									location.reload();
								});
							}else{
								layer.alert(result.msg);
							}
						}
					});
	            }
			});
		},
		/**
		 * 查看RESTFul API
		 */
		openAPI: function(){
//			vm.main="/project_d/swagger-ui.html#/";
//			vm.navTitle="API"
		}
	},
	
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	
	updated: function(){
		//路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}
});



function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 0){
			routerList(router, menu.childernList);
		}else if(menu.type == 1){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
	//页面导航路径
	var indexList=['#swagger-ui.html','#sys/main.html'];
	for(var index in indexList){
		router.add(indexList[index], function(){
			var url = window.location.hash;
			//替换iframe的url
		    vm.main = url.replace('#', '');
		    vm.navTitle = $("a[href='"+url+"']").text();
		});
	}
}
