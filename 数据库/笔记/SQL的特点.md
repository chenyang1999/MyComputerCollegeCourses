（1）SQL的特点。

(1)SQL的特点:综合统一.高度非过程化,面向集合操作方式,以同一种语法提供多种使用方,方便嵌入,语言简洁易用

（2）基本表、视图的概念。

基本表独立存在,SQL 中一个关系对应一个基本表,一个或多个基本表对应一个储存文件,一个表可以带多个索引

视图是从一个或多个基本表上导出的虚拟表,使用与基本表基本一样

（3）SQL 数据定义语句：模式的定义与删除。 

对数据库中各种对象进行创建的过程叫做数据定义

创建:CREATE SCHEMA

删除:DROP SCHEMA

- 级联 CASCADE
- 限制 RESTICT更加安全

（4）SQL 数据定义语句：基本表的定义。 

关系数据库的核心,CREATE TABLE <表名>(<列明><类型>);

实体完整性,参照完整性

（5）SQL 数据定义语句：删除与修改。 

增加:ADD TABLE

修改:ALTER TABLE 

删除表:DROP TABLE,

- 级联 CASCADE
- 限制 RESTICT更加安全

（6）SQL 数据定义语句：索引建立与删除。

索引由关系数据库自动创建,常见的有 B+和散列哈希索引

数据库或者表的属主可以创建

有数据库管理系统自动完成维护

关系数据库管理系统会自动选择适合的路径存储,用户不用显式地选择索引

- 创建:CREATE [UNIQUE [CLUSTER] INDEX<索引名>![image-20200317112728126](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-03-17-032728.png)

- 修改:ALTER INDEX<旧索引名>RENAME TO<新索引名>
- ![image-20200317112817169](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-03-17-032817.png)

- 删除:DROP INDEX<索引名>;

    ![image-20200317112844825](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-03-17-032847.png)