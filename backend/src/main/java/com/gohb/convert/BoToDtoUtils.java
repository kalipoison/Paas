package com.gohb.convert;

import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.params.bo.kube.KubeNodeBO;
import com.gohb.params.bo.kube.KubePodBO;
import com.gohb.params.bo.kube.KubePodDetailBO;
import com.gohb.params.bo.sys.*;
import com.gohb.params.dto.kube.KubeNamespaceDTO;
import com.gohb.params.dto.kube.KubeNodeDTO;
import com.gohb.params.dto.kube.KubePodDTO;
import com.gohb.params.dto.kube.KubePodDetailDTO;
import com.gohb.params.dto.sys.*;
import org.springframework.beans.BeanUtils;

/**
 * BO 转换为 DTO
 */
public class BoToDtoUtils {

    /**
     * sysLogBO -> sysLogDTO
     * @param sysLogBO
     * @return
     */
    public static SysLogDTO sysLogBOTOSysLogDTO(SysLogBO sysLogBO) {
        if (sysLogBO == null) {
            return null;
        }
        SysLogDTO sysLogDTO = new SysLogDTO();
        BeanUtils.copyProperties(sysLogBO, sysLogDTO);
        return sysLogDTO;
    }

    /**
     * sysMenuBO -> sysMenuDTO
     * @param sysMenuBO
     * @return
     */
    public static SysMenuDTO sysMenuBOTOSysMenuDTO(SysMenuBO sysMenuBO) {
        if (sysMenuBO == null) {
            return null;
        }
        SysMenuDTO sysMenuDTO = new SysMenuDTO();
        BeanUtils.copyProperties(sysMenuBO, sysMenuDTO);
        return sysMenuDTO;
    }

    /**
     * sysRoleBO -> sysRoleDTO
     * @param sysRoleBO
     * @return
     */
    public static SysRoleDTO sysRoleBOTOSysRoleDTO(SysRoleBO sysRoleBO) {
        if (sysRoleBO == null) {
            return null;
        }
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        BeanUtils.copyProperties(sysRoleBO, sysRoleDTO);
        return sysRoleDTO;
    }

    /**
     * sysRoleMenuBO -> sysRoleMenuDTO
     * @param sysRoleMenuBO
     * @return
     */
    public static SysRoleMenuDTO sysRoleMenuBOTOSysRoleMenuDTO(SysRoleMenuBO sysRoleMenuBO) {
        if (sysRoleMenuBO == null) {
            return null;
        }
        SysRoleMenuDTO sysRoleMenuDTO = new SysRoleMenuDTO();
        BeanUtils.copyProperties(sysRoleMenuBO, sysRoleMenuDTO);
        return sysRoleMenuDTO;
    }

    /**
     * sysUserBO -> sysUserDTO
     * @param sysUserBO
     * @return
     */
    public static SysUserDTO sysUserBOTOSysUserDTO(SysUserBO sysUserBO) {
        if (sysUserBO == null) {
            return null;
        }
        SysUserDTO sysUserDTO = new SysUserDTO();
        BeanUtils.copyProperties(sysUserBO, sysUserDTO);
        return sysUserDTO;
    }

    /**
     * sysUserRoleBO -> sysUserRoleDTO
     * @param sysUserRoleBO
     * @return
     */
    public static SysUserRoleDTO sysUserRoleBOTOSysUserRoleDTO(SysUserRoleBO sysUserRoleBO) {
        if (sysUserRoleBO == null) {
            return null;
        }
        SysUserRoleDTO sysUserRoleDTO = new SysUserRoleDTO();
        BeanUtils.copyProperties(sysUserRoleBO, sysUserRoleDTO);
        return sysUserRoleDTO;
    }


    public static KubeNodeDTO kubeNodeBOTOKubeNodeDTO(KubeNodeBO kubeNodeBO) {
        if (kubeNodeBO == null) {
            return null;
        }
        KubeNodeDTO kubeNodeDTO = new KubeNodeDTO();
        BeanUtils.copyProperties(kubeNodeBO, kubeNodeDTO);
        return kubeNodeDTO;
    }

    public static KubeNamespaceDTO kubeNamespaceBOTOkubeNamespaceDTO(KubeNamespaceBO kubeNamespaceBO) {
        if (kubeNamespaceBO == null) {
            return null;
        }
        KubeNamespaceDTO kubeNamespaceDTO = new KubeNamespaceDTO();
        BeanUtils.copyProperties(kubeNamespaceBO, kubeNamespaceDTO);
        return kubeNamespaceDTO;
    }



    public static KubePodDTO kubePodBOTOKubePodDTO(KubePodBO kubePodBO) {
        if (kubePodBO == null) {
            return null;
        }
        KubePodDTO kubePodDTO = new KubePodDTO();
        BeanUtils.copyProperties(kubePodBO, kubePodDTO);
        return kubePodDTO;
    }

    public static KubePodDetailDTO kubePodDetailBOTOKubePodDetailDTO(KubePodDetailBO kubePodDetailBO) {
        if (kubePodDetailBO == null) {
            return null;
        }
        KubePodDetailDTO kubePodDetailDTO = new KubePodDetailDTO();
        BeanUtils.copyProperties(kubePodDetailBO, kubePodDetailDTO);
        return kubePodDetailDTO;
    }



}
