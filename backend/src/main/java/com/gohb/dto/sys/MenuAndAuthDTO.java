package com.gohb.dto.sys;

import com.gohb.dto.sys.SysMenuDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 菜单权限
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuAndAuthDTO {

    private List<SysMenuDTO> menuList;

    private List<String> authorities;

}
