# 基础后端模板
简单的**毕设基础开发模板**，包含通用结果封装、用户菜单配置、系统用户发送信息、mybatis-plus代码生成器、用户登录模块。适用于小型、多表的单表增删改查的毕业设计项目。

基于**springboot2.x+Mysql+Mybatis-plus**

对mybatis-plus进行基础Controller的简单封装，直接继承即可完成单表增删改查、简单id联表分页和列表查询

```java
public class ExampleController extends CommonController<ExampleServiceImpl, Example> {

    public ExampleController(ExampleServiceImpl s) {
        super(s);
    }


}
```

实体联表注解
```java
    public class Example{

    // 需要联表查询的字段
    @JoinTable(tableName = "join_table_name", mode = 1, field = "id", voClass = JoinTableVO.class, joinClass = JoinTableEntiy.class)
    private String joinTableId;
}
```



