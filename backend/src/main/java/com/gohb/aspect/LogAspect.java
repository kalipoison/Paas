//package com.gohb.aspect;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.gohb.anno.Log;
//import com.gohb.bo.SysLogBO;
//import com.gohb.bo.SysUserBO;
//import com.gohb.service.SysLogService;
//import com.gohb.service.SysUserService;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//
//
//
//@Aspect
//@Component
//public class LogAspect {
//
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Autowired
//    private SysLogService sysLogService;
//
//
//    /**
//     * 切面
//     *
//     * @param joinPoint
//     * @return
//     */
//    @Around(value = "@annotation(com.gohb.anno.Log)")
//    public Object myLogAspect(ProceedingJoinPoint joinPoint) {
//        // 记录用户的操作
//        Object result = null;
//        // 拿到方法名称 方法签名
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        String methodName = signature.getMethod().getName();
//        // 拿到注解
//        Log log = signature.getMethod().getAnnotation(Log.class);
//        // 拿到注解里面的属性
//        String operation = log.operation();
//        // 方法的实行时间
//        long start = System.currentTimeMillis();
//        // 执行方法
//        try {
//            result = joinPoint.proceed(joinPoint.getArgs());
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        long end = System.currentTimeMillis();
//        // 拿到当前用户名称
//        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        // 根据用户id查询用户的名称
//        SysUserBO sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUserBO>()
//                .select(SysUserBO::getUsername)
//                .eq(SysUserBO::getUserId, Long.valueOf(userId))
//        );
//        String username = sysUser.getUsername();
//        // 拿到ip
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        String ip = request.getRemoteHost();
//        // 组装对象 插入数据库
//        SysLogBO sysLog = SysLogBO.builder().username(username)
//                .ip(ip)
//                .method(methodName)
//                .params(joinPoint.getArgs() == null ? "" : JSON.toJSONString(joinPoint.getArgs()))
//                .operation(operation)
//                .time(end - start)
//                .createDate(new Date())
//                .build();
//        // 插入数据库
//        sysLogService.save(sysLog);
//        return result;
//    }
//
//
//}
//
//
