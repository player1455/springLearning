## 构造器
1. 构造器解耦是规定了一个同一规范，即是当使用构造器后便可以传入实现了依赖的子类与实现了依赖接口的类:相对而言,假如使用
new来进行创建实例，那么实例的依赖便被写死了,并且在调用类的函数时需要创建该类的依赖，耦合度大大增强


## AOP
1. 在本次示例中,(anno.RepeatLimit , AOP.RepeatLimitAop) 使用@Around来监控注释@RepeatLimit
首先会存入一个key进入redis,当其触发频繁刷新时,便会返回error
2. (anno.GlobalExceptionHanding,AOP.GlobalExceptionHanding) 通过注释来监听全局的异常处理