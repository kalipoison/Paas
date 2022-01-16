一套基于kubernetes开发的Paas系统。



paas-ind :  Paas 服务个人版，包含对kubernetes 的Pod 、Service、Deployment 等操作，同时基于RBAC实现权限控制，同时可统计站点访问量，以及远程接入终端



# 技术栈

后端： springboot + mybatis + kubernetes + springsecurity + jwt + websocket

前端： vue + echarts + element-ui + xterm



# 部署

导入paas_db.sql文件

## 前端部署

```javascript
npm install
npm run serve
```

## 后端

```java
java -jar xxx.jar
```





# 效果展示：

## 1.登录界面 



![image-20211224224437947](./pic/image-20211224224437947.png)



## 2. 系统管理

### 2.1  管理员列表

![image-20211227001705495](./pic/image-20211227001705495.png)

### 2.2 角色管理

![image-20211227001818169](./pic/image-20211227001818169.png)

### 2.3 菜单管理

![image-20211227001916615](./pic/image-20211227001916615.png)

### 2.4 日志管理

![image-20211227002001770](./pic/image-20211227002001770.png) 



# 3. 容器管理 

### 3.1 Node管理

![image-20211227002247371](./pic/image-20211227002247371.png)

3.2 Namespace 管理

![image-20211227002324099](./pic/image-20211227002324099.png)



3. 3 Deployment管理

![image-20211227002522291](./pic/image-20211227002522291.png)



3.4 Pod 管理

![image-20211227002558832](./pic/image-20211227002558832.png)



3.5 Service 管理

![image-20211227002631695](./pic/image-20211227002631695.png)



## 4. 仪表盘

### 4.1 访问量

![image-20211227002712839](./pic/image-20211227002712839.png)



# 5. 终端 

### 5.1 终端

![image-20211229052324284](./pic/image-20211229052324284.png)



## 6. 商品

### 6.1 产品

![image-20220116222913031](./pic/image-20220116222913031.png)

### 6.2 产品属性

![image-20220116223014184](./pic/image-20220116223014184.png)

![image-20220116223048552](./pic/image-20220116223048552.png)



### 6.3 SKU

![image-20220116223117541](./pic/image-20220116223117541.png)



## 7. 兑换券

### 7.1 兑换券

![image-20220116223159300](./pic/image-20220116223159300.png)

