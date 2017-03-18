/**
 * 用户和菜单视图
 */
CREATE OR REPLACE VIEW v_sys_user_menu
AS
SELECT ur.user_id AS user_id, m.perms AS menu_perms, m.menu_id AS menu_id
FROM project_d.sys_user_role ur
LEFT JOIN sys_role_menu rm ON ur.role_id=rm.role_id
LEFT JOIN sys_menu m ON rm.menu_id=m.menu_id



/**
 * 菜单视图
 */
CREATE OR REPLACE VIEW V_SYS_MENU
AS
SELECT menu.* , m.name  AS parent_name
FROM project_d.sys_menu menu
LEFT JOIN sys_menu m ON menu.parent_id=m.menu_id

        
    