# 电商订单超时逻辑处理

使用`DelayQueue` 进行控制订单队列，使用`DelayQueue,take()` 的方式等待取出过期的订单实体，再进行订单关闭操作。

数据库配置信息在`application.yml` 中。

参数 | 属性值
----- | -------
url | jdbc:mysql://localhost:3306/springdata?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT
username | root
password | 1234
driver-class-name | com.mysql.cj.jdbc.Driver

数据表使用JPA 内置的hibernate 进行自动生成。

浏览器调用`http://localhost:8080/insert` 即可查看控制台输出信息