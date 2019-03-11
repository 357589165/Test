# 高并发下修改变量测试


测试文件 | 内容概述 | 是否采用
--- | --- | ---
com.example.demo.test.IncrTest | 使用JDK 默认++ 操作控制变量更改 | 不可用
com.example.demo.test.AtomicTest | 使用JDK 提供的Atomic 类原子操作 | 高并发情况下也有最终值不正确性
com.example.demo.test.CASTest | 使用CAS 机制，实现Lock 接口进行线程操作 | 高并发情况下也可保持最终值的正确性

实际测试后高并发情况下可使用LockSupport 操作锁定进程并实现CAS 机制，即可保证数据的正确性。