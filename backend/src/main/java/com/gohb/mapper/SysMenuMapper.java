package com.gohb.mapper;

import com.gohb.bo.sys.SysMenuBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @description 针对表【sys_menu(菜单管理)】的数据库操作Mapper
*/
public interface SysMenuMapper extends BaseMapper<SysMenuBO> {

    @Select(" SELECT t1.* from sys_menu t1 join sys_role_menu t2 on(t1.menu_id =t2.menu_id) join sys_user_role t3 on (t2.role_id=t3.role_id) where t3.user_id = #{userId} and (t1.type = 0 or t1.type = 1)  ")
    List<SysMenuBO> findMenuByUserId(@Param("userId") Long userId);

}




