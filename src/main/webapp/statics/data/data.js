var menuList=
{
	"menuList":[{"menuId":"1","parentId":"0","name":"系统管理","url":null,"perms":null,"type":"0","icon":"fa fa-cog","orderNum":"0",
		 "childernList":[
		 {"menuId":"2","parentId":"1","name":"管理员列表","url":"sys/user.html","perms":null,"type":"1","icon":"fa fa-user","orderNum":"1","childernList":null,"open":false},
		 {"menuId":"3","parentId":"1","name":"角色管理","url":"sys/role.html","perms":null,"type":"1","icon":"fa fa-user-secret","orderNum":"2","childernList":null,"open":false},
		 {"menuId":"4","parentId":"1","name":"菜单管理","url":"sys/menu.html","perms":null,"type":"1","icon":"fa fa-th-list","orderNum":"3","childernList":null,"open":false},
		 {"menuId":"5","parentId":"1","name":"SQL监控","url":"druid/sql.html","perms":null,"type":"1","icon":"fa fa-bug","orderNum":"4","childernList":null,"open":false},
		 {"menuId":"6","parentId":"1","name":"定时任务","url":"sys/schedule.html","perms":null,"type":"1","icon":"fa fa-tasks","orderNum":"5","childernList":null,"open":false},
		 {"menuId":"27","parentId":"1","name":"参数管理","url":"sys/config.html","perms":"sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete","type":"1","icon":"fa fa-sun-o","orderNum":"6","childernList":null,"open":false}
	    ]
	,"open":false}],
	"code":0};
var userInfo=
{
	"code":0,
	"user":{
		"userId":"1",
		"username":"admin",
		"password":"8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
		"email":"root@renren.io",
		"mobile":"13612345678",
		"status":"1",
		"createTime":1478833871000,
		"roleIdList":null
		}
}
var menuList=
{
		"code":0,
		"page":{
						"currPage":1,
						"totalPage":2,
						"totalCount":27,
						"list":[
								        {"menuId":"1","parentId":"0","name":"系统管理","url":null,"perms":null,"type":"0","icon":"fa fa-cog","orderNum":"0","parentName":null},
								        {"menuId":"10","parentId":"6","name":"删除","url":null,"perms":"sys:schedule:delete","type":"2","icon":null,"orderNum":"0","parentName":"定时任务"},
								        {"menuId":"11","parentId":"6","name":"暂停","url":null,"perms":"sys:schedule:pause","type":"2","icon":null,"orderNum":"0","parentName":"定时任务"},
								        {"menuId":"12","parentId":"6","name":"恢复","url":null,"perms":"sys:schedule:resume","type":"2","icon":null,"orderNum":"0","parentName":"定时任务"},
								        {"menuId":"13","parentId":"6","name":"立即执行","url":null,"perms":"sys:schedule:run","type":"2","icon":null,"orderNum":"0","parentName":"定时任务"},
								        {"menuId":"14","parentId":"6","name":"日志列表","url":null,"perms":"sys:schedule:log","type":"2","icon":null,"orderNum":"0","parentName":"定时任务"},
								        {"menuId":"15","parentId":"2","name":"查看","url":null,"perms":"sys:user:list,sys:user:info","type":"2","icon":null,"orderNum":"0","parentName":"管理员列表"},
								        {"menuId":"16","parentId":"2","name":"新增","url":null,"perms":"sys:user:save,sys:role:select","type":"2","icon":null,"orderNum":"0","parentName":"管理员列表"},
								        {"menuId":"17","parentId":"2","name":"修改","url":null,"perms":"sys:user:update,sys:role:select","type":"2","icon":null,"orderNum":"0","parentName":"管理员列表"},
								        {"menuId":"18","parentId":"2","name":"删除","url":null,"perms":"sys:user:delete","type":"2","icon":null,"orderNum":"0","parentName":"管理员列表"},
								        {"menuId":"19","parentId":"3","name":"查看","url":null,"perms":"sys:role:list,sys:role:info","type":"2","icon":null,"orderNum":"0","parentName":"角色管理"},
								        {"menuId":"2","parentId":"1","name":"管理员列表","url":"sys/user.html","perms":null,"type":"1","icon":"fa fa-user","orderNum":"1","parentName":"系统管理"},
								        {"menuId":"20","parentId":"3","name":"新增","url":null,"perms":"sys:role:save,sys:menu:perms","type":"2","icon":null,"orderNum":"0","parentName":"角色管理"},
								        {"menuId":"21","parentId":"3","name":"修改","url":null,"perms":"sys:role:update,sys:menu:perms","type":"2","icon":null,"orderNum":"0","parentName":"角色管理"},
								        {"menuId":"22","parentId":"3","name":"删除","url":null,"perms":"sys:role:delete","type":"2","icon":null,"orderNum":"0","parentName":"角色管理"}
						        ]
					}
}


