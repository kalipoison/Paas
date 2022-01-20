package com.gohb.convert;

import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.client.UserProdBO;
import com.gohb.params.bo.kube.*;
import com.gohb.params.bo.notify.NotifyBO;
import com.gohb.params.bo.prod.*;
import com.gohb.params.bo.sys.*;
import com.gohb.params.bo.voucher.VoucherBO;
import com.gohb.params.dto.client.MyUserDTO;
import com.gohb.params.dto.client.UserProdDTO;
import com.gohb.params.dto.kube.*;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.params.dto.prod.*;
import com.gohb.params.dto.sys.*;
import com.gohb.params.dto.voucher.VoucherDTO;
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

    public static ProductDTO productBOTOProductDTO(ProductBO productBO) {
        if (productBO == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productBO, productDTO);
        productDTO.setCreateTime(DateTimeUtils.DateToString(productBO.getCreateTime()));
        productDTO.setUpdateTime(DateTimeUtils.DateToString(productBO.getUpdateTime()));
        productDTO.setPutawayTime(DateTimeUtils.DateToString(productBO.getPutawayTime()));
        return productDTO;
    }

    public static SkuDTO skuBoTOSkuDTO(SkuBO skuBO) {
        if (skuBO == null) {
            return null;
        }
        SkuDTO skuDTO = new SkuDTO();
        BeanUtils.copyProperties(skuBO, skuDTO);
        return skuDTO;
    }

    public static VoucherDTO VoucherBoTOVoucherDTO(VoucherBO voucherBO) {
        if (voucherBO == null) {
            return null;
        }
        VoucherDTO voucherDTO = new VoucherDTO();
        BeanUtils.copyProperties(voucherBO, voucherDTO);
        return voucherDTO;
    }

    public static NotifyDTO NotifyBoTONotifyDTO(NotifyBO notifyBO) {
        if (notifyBO == null) {
            return null;
        }
        NotifyDTO notifyDTO = new NotifyDTO();
        BeanUtils.copyProperties(notifyBO, notifyDTO);
        return notifyDTO;
    }

    public static MyUserDTO MyUserBoTOMyUserDTO(MyUserBO myUserBO) {
        if (myUserBO == null) {
            return null;
        }
        MyUserDTO myUserDTO = new MyUserDTO();
        BeanUtils.copyProperties(myUserBO, myUserDTO);
        return myUserDTO;
    }

    public static UserProdDTO UserProdBoTOUserProdDTO(UserProdBO userProdBO) {
        if (userProdBO == null) {
            return null;
        }
        UserProdDTO userProdDTO = new UserProdDTO();
        BeanUtils.copyProperties(userProdBO, userProdDTO);
        return userProdDTO;
    }

    public static OrderDTO OrderBoTOOrderDTO(OrderBO orderBO) {
        if (orderBO == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderBO, orderDTO);
        return orderDTO;
    }

}
