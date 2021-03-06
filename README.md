## 更新日志
[更新日志](/change-log.md)

## 图片
![image](screen/屏幕快照\%202018-02-21\%2022.21.52.png)

![image](screen/屏幕快照\%202018-02-21\%2022.22.19.png)

![image](screen/屏幕快照\%202018-02-21\%2022.22.54.png)

![image](screen/屏幕快照\%202018-02-21\%2022.23.12.png)

![image](screen/屏幕快照\%202018-02-21\%2022.41.36.png)

![image](screen/屏幕快照\%202018-02-21\%2022.25.11.png)

## 细节
### 后端
- Spring Security(权限控制)
- quartz(任务调度)
- swagger(API 文档)
- mongodb(数据库)
- thymeleaf(模版引擎)
- WebSocket(站内消息)
- ..

### 前端
- AdminLTE-2.4.2
- LayIM(聊天UI, 未提交)
- Layer(弹窗组件)
- EChart(图表)
- PJAX
- datatables(数据表格)
- webuploader(百度上传组件)
- ..

## 模块
- 消息系统
- 评论系统
- 文件系统
- 用户系统
- 内容管理
- 日志系统
- ..


## 初始化运行

1.编辑`application.yml`做如下修改:
```yaml
# 设置加载的配置文件
spring:
  profiles:
    active: docker
```

2. 打包运行 jar
```bash
mvn clean
mvn package -Dmaven.test.skip=true
```

3. 运行 Docker
```bash
docker-compose up -d
```