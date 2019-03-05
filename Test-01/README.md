# 使用redis + lua 进行限流操作

使用两个Controller 来模拟需要限流的API 与无需限流的API

API 接口地址 | 作用
-----|-----
/lock | 加入限流锁的API 地址
/unlock | 未加入限流锁的API 地址

通过调用限流锁API 接口时，使用spring aop 进行切面化处理（也可使用Filter 进行拦截处理）</br>
当有请求进入时，连接redis 数据库，使用lua 脚本进行查询处理，获取是否限制结果。

项目默认限制为2 秒仅可调用1 次API 。