## 构造器
1. 构造器解耦是规定了一个同一规范，即是当使用构造器后便可以传入实现了依赖的子类与实现了依赖接口的类:相对而言,假如使用
new来进行创建实例，那么实例的依赖便被写死了,并且在调用类的函数时需要创建该类的依赖，耦合度大大增强


## AOP
1. 在本次示例中,(anno.RepeatLimit , AOP.RepeatLimitAop) 使用@Around来监控注释@RepeatLimit
首先会存入一个key进入redis,当其触发频繁刷新时,便会返回error
2. (anno.GlobalExceptionHanding,AOP.GlobalExceptionHandingAOP) 通过注释来监听全局的异常处理

## 26/3/23
## @Configuration的测试配置
1. 写一个配置类(Config.shopConfig),这个配置类的作用是当配置文件中product.ready==True时，创建一个bean。
2. @ConfigurationProperties(prefix = "product")中绑定了配置文件中的product


## 26/3/24
## jdbc
1. jdbc是java设定的接口规范 mysql的驱动是mysql对jdbc的实现
2. 连接池是管理一堆的jdbc

## 26/3/25
## jdbc
1. 使用jdbc来查询数据库(productRepository)，在实现ApplicationRunner的接口来使得spring初始化后就执行查询操作(Ruuner.PrinterRunner)
2. 若使用原生spring那么则需要手动引入bean

## 事务管理
1. spring的事务是通过实现DataSourceTransactionManager实现了PlatformTransactionManager接口完成的,
主要的回归,提交等代码是实现在DataSourceTransactionManager中的。

## 面向接口编程
以事务管理为例,PlatformTransactionManager是继承TransactionManager的接口(TransactionManager本身为空接口,仅仅用于标记，就像为文件命名一样),
若有其他的事务代码要接入spring,那么只需要实现PlatformTransactionManager接口便可以无缝接入,相当于接口是规定了代码实现的规则,而具体的实现交由实现了接口的类来完成。
当然,例如DataSourceTransactionManager在实现PlatformTransactionManager接口并不会直接实现的,而是会继承一个实现该接口的抽象类，再让子类去继承这个抽象类。
这便是spring的设计之一，"固定流程"与"具体实现"的分离。

## CGLIB 代理 (代理：就是一个包装类,在原有的类上做了一些增强，比如引入了事务的逻辑)
可为你的类自动生成一个子类,在事务中，是为了让类获取到事务的逻辑。也就是生成一个内部有着事务功能的子类，在不影响源码的情况下实现事务的功能。创建代理这一功能其实是aop思想的体现。

## @Transactional
@Transactional 是一个 AOP 通知,通过@Transactional的注释，会对有这个注释的类或方法，调用CGLIB 代理来为该类或方法的子类添加事务的逻辑，使得spring可以对其使用事务