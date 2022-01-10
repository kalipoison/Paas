package com.gohb.convert;

import com.gohb.params.bo.kube.*;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.bo.sys.*;
import com.gohb.params.dto.kube.*;
import com.gohb.params.dto.prod.ProdPropDTO;
import com.gohb.params.dto.prod.ProdPropValueDTO;
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

    public static KubeDeploymentDTO kubeDeploymentBOTOKubeDeploymentDTO(KubeDeploymentBO kubeDeploymentBO) {
        if (kubeDeploymentBO == null) {
            return null;
        }
        KubeDeploymentDTO kubeDeploymentDTO = new KubeDeploymentDTO();
        BeanUtils.copyProperties(kubeDeploymentBO, kubeDeploymentDTO);
        return kubeDeploymentDTO;
    }

    public static KubeDeploymentDetailDTO kubeDeploymentDetailBOTOKubeDeploymentDetailDTO(KubeDeploymentDetailBO kubeDeploymentDetailBO) {
        if (kubeDeploymentDetailBO == null) {
            return null;
        }
        KubeDeploymentDetailDTO kubeDeploymentDetailDTO = new KubeDeploymentDetailDTO();
        BeanUtils.copyProperties(kubeDeploymentDetailBO, kubeDeploymentDetailDTO);
        return kubeDeploymentDetailDTO;
    }

    public static KubeServiceDTO kubeServiceBOTOKubeServiceDTO(KubeServiceBO kubeServiceBO) {
        if (kubeServiceBO == null) {
            return null;
        }
        KubeServiceDTO kubeServiceDTO = new KubeServiceDTO();
        BeanUtils.copyProperties(kubeServiceBO, kubeServiceDTO);
        return kubeServiceDTO;
    }

    public static KubeServiceDetailDTO kubeServiceDetailBOTOKubeServiceDetailDTO(KubeServiceDetailBO kubeServiceDetailBO) {
        if (kubeServiceDetailBO == null) {
            return null;
        }
        KubeServiceDetailDTO kubeServiceDetailDTO = new KubeServiceDetailDTO();
        BeanUtils.copyProperties(kubeServiceDetailBO, kubeServiceDetailDTO);
        return kubeServiceDetailDTO;
    }


    public static ProdPropDTO prodPropBOTOProdPropDTO(ProdPropBO prodPropBO) {
        if (prodPropBO == null) {
            return null;
        }
        ProdPropDTO prodPropDTO = new ProdPropDTO();
        BeanUtils.copyProperties(prodPropBO, prodPropDTO);
        return prodPropDTO;
    }

    public static ProdPropValueDTO prodPropValueBOTOProdPropValueDTO(ProdPropValueBO prodPropValueBO) {
        if (prodPropValueBO == null) {
            return null;
        }
        ProdPropValueDTO prodPropValueDTO = new ProdPropValueDTO();
        BeanUtils.copyProperties(prodPropValueBO, prodPropValueDTO);
        return prodPropValueDTO;
    }

}
