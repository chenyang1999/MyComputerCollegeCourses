# 门以上课程SQL笔记

> MARCUS_YANG

[toc]

## SQL基本概念

SQL（Structured Query Language）结构化查询语言，是关系数据库的标准语言特点:

```sql
SELECT Sno, Grade
FROM SC
WHERE Cno= '3'
ORDER BY Grade DESC;
//从表 SC 中选取学号 Sno 和成绩 Grade 满足条件课程号 Cno=“3”按照成绩 Gradel 降序排列
```

- 综合统一:

    - 集数据定义语言（DDL），数据操纵语言（DML），数据控制语言（DCL）功能于一体。
    - 定义和修改、删除关系模式，定义和删除视图，插入数据，建立数据库;
    - 对数据进行增删改查
    - 数据库的重构和维护
    - 数据库安全性、完整性控制，以及事务控制
    - 嵌入式和动态SQL定义

- 高度非过程化:SQL 只要提出“做什么”，无须了解存取路径。

- 面向集合的操作方式:

    - 非关系数据模型采用面向记录的操作方式，操作对象是一条记录
    - 采用集合操作方式
    - 操作对象、查找结果可以是元组的集合
    - 一次插入、删除、更新操作的对象可以是元组的集合

- 以同一种语法结构提供多种使用方式:独立使用和嵌入高级语言中

- 语言简洁，易学易用

    ![image-20200427164730733](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-27-084731.png)

- SQL支持关系数据库三级模式结构:

    ![image-20200427164758252](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-27-084758.png)

    - 基本表:
        - 本身独立存在的表
        - SQL 中一个关系就对应一个基本表
        - 一个（或多个）基本表对应一个存储文件
        - 一个表可以带若干索引
    - 存储文件:
        - 逻辑结构组成了关系数据库的内模式
        - 物理结构对用户来说是隐蔽的
    - 视图:
        - 用户可以在视图上再定义视图
        - 视图是一个虚表
        - 数据库中只存放视图的定义而不存放视图对应的数据
        - 从一个或几个基本表导出的表

### 贯彻这门课的三个样例表:

student table:

| SNO 学号    | Sname 姓名 | Ssex 性别 | Sage 年龄 | Sdept 系 |
| ----------- | ---------- | --------- | --------- | -------- |
| 17150011001 | Marcus     | MA        | 21        | CS       |
|             |            |           |           |          |
|             |            |           |           |          |
|             |            |           |           |          |
|             |            |           |           |          |

Course表

| Cno课程号 | Cname 课程名 | Cpno 先修课 | Ccredit 学分 |
| --------- | ------------ | ----------- | ------------ |
| 1         | 数据库       | 5           | 4            |
| 2         | 数学         | NULL        | 2            |
| 3         | 信息系统     | 1           | 4            |
| 4         | 操作系统     | 6           | 3            |
| 5         | 数据结构     | 7           | 4            |
| 6         | 数据处理     | NULL        | 2            |
| 7         | PASCAL       | 6           | 4            |

学生课程表SC

| Sno 学号    | Cno 课程号 | Grade 成绩 |
| ----------- | ---------- | ---------- |
| 17150011001 | 1          | 100        |
|             |            |            |
|             |            |            |
|             |            |            |

---

## 数据定义

SQL 的数据定义功能：定义各种数据库的“对象”

- 模式定义
- 表定义
- 视图定义
- 索引定义

- [ ] ![image-20200427170046459](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-27-090046.png)

### 定义模式:

example: 定义模式实际上定义了一个命名空间（或者说目录）

> [例3.1] 为用户WANG定义一个学生-课程模式S-T
>
> CREATE SCHEMA “S-T” AUTHORIZATION WANG;
>
> [例3.2] CREATE SCHEMA AUTHORIZATION WANG;
>
> 该语句没有指定<模式名>，<模式名>隐含为<用户名> 

- 在 CREATE SCHEMA 中可以接受 CREATETABLE CREATE VIEW 和 GRANT 子句。

- > [例3.3]为用户ZHANG创建了一个模式TEST，并且在其中定义一个表 TAB1
    >
    > ```sql
    > CREATE SCHEMA TEST AUTHORIZATION ZHANG
    > CREATE TABLE TAB1(COL1 SMALLINT,
    >                   COL2 INT,
    >                   COL3 CHAR(20),
    >                   COL4 NUMERIC(10,3),
    >                   COL5 DECIMAL(5,2)
    > );
    > ```

### 删除模式

> DROP SCHEMA <模式名><CASCADE|RESTRICT>
>
> - CASCADE删除模式的同时把该模式中所有的数据库对象全部删除
> - RESTRICT如果该模式中定义了下属的数据库对象（如表、视图等），则拒绝该删除语句的执行。仅当该模式中没有任何下属的对象时才能执行。

> [例3.4] DROP SCHEMA ZHANG CASCADE;
>
> 删除模式ZHANG
>
> 同时该模式中定义的表TAB1也被删除

## 基本表的定义

![image-20200427170638306](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-27-090638.png)

- 列级完整性约束条件:涉及相应属性列的完整性约束条件
- 表级完整性约束条件:涉及一个或多个属性列的完整性约束条件
- 如果完整性约束条件涉及到该表的多个属性列，则必须定义在表级上。

> [例3.5] 建立“学生”表Student。学号是主码，姓名取值唯一。
>
> ```sql
> CREATE TABLE Student
> 	(Sno CHAR(9) PRIMARY KEY,
> 	/* 列级完整性约束条件,Sno是主码*/
>    Sname CHAR(20) UNIQUE,
>    Ssex CHAR(2),
>    Sage SMALLINT,
>    Sdept CHAR(20)
> );
> ```
>
> ---
>
> [例3.6 ] 建立一个“课程”表Course
>
> ```sql
> CREATE TABLE Course
> 	(Cno CHAR(4) PRIMARY KEY,
>    Cname CHAR(40),
>    Cpno CHAR(4),/*先修课*/
>    Ccredit SMALLINT，
>    FOREIGN KEY (Cpno) REFERENCES Course(Cno)/*Cpno是外码,被参照表是Course,被参照列是Cno*/
> );
> ```
>
> ---
>
> [例3.7] 建立一个学生选课表SC
>
> ```sql
> CREATE TABLE SC
> 	(Sno CHAR(9),
>    Cno CHAR(4),
>    Grade SMALLINT，
>    PRIMARY KEY (Sno,Cno),/* 主码由两个属性构成，必须作为表级完整性进行定义*/
>    FOREIGN KEY (Sno) REFERENCES Student(Sno),/* 表级完整性约束条件，Sno是外码，被参照表是Student */
>    FOREIGN KEY (Cno)REFERENCES Course(Cno)/* 表级完整性约束条件， Cno是外码，被参照表是Course*/
> );
> ```
>
> 

### 数据类型

关系模型中“域”的概念:

MYSQL:https://www.runoob.com/mysql/mysql-data-types.html

> 作为SQL标准的扩展，MySQL也支持整数类型TINYINT、MEDIUMINT和BIGINT。下面的表显示了需要的每个整数类型的存储和范围。
>
> | 类型         | 大小                                     | 范围（有符号）                                               | 范围（无符号）                                               | 用途            |
> | :----------- | :--------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- | :-------------- |
> | TINYINT      | 1 byte                                   | (-128，127)                                                  | (0，255)                                                     | 小整数值        |
> | SMALLINT     | 2 bytes                                  | (-32 768，32 767)                                            | (0，65 535)                                                  | 大整数值        |
> | MEDIUMINT    | 3 bytes                                  | (-8 388 608，8 388 607)                                      | (0，16 777 215)                                              | 大整数值        |
> | INT或INTEGER | 4 bytes                                  | (-2 147 483 648，2 147 483 647)                              | (0，4 294 967 295)                                           | 大整数值        |
> | BIGINT       | 8 bytes                                  | (-9,223,372,036,854,775,808，9 223 372 036 854 775 807)      | (0，18 446 744 073 709 551 615)                              | 极大整数值      |
> | FLOAT        | 4 bytes                                  | (-3.402 823 466 E+38，-1.175 494 351 E-38)，0，(1.175 494 351 E-38，3.402 823 466 351 E+38) | 0，(1.175 494 351 E-38，3.402 823 466 E+38)                  | 单精度 浮点数值 |
> | DOUBLE       | 8 bytes                                  | (-1.797 693 134 862 315 7 E+308，-2.225 073 858 507 201 4 E-308)，0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308) | 0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308) | 双精度 浮点数值 |
> | DECIMAL      | 对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2 | 依赖于M和D的值                                               | 依赖于M和D的值                                               | 小数值          |
>
> ------
>
> ## 日期和时间类型
>
> 表示时间值的日期和时间类型为DATETIME、DATE、TIMESTAMP、TIME和YEAR。
>
> 每个时间类型有一个有效值范围和一个"零"值，当指定不合法的MySQL不能表示的值时使用"零"值。
>
> TIMESTAMP类型有专有的自动更新特性，将在后面描述。
>
> | 类型      | 大小 ( bytes) | 范围                                                         | 格式                | 用途                     |
> | :-------- | :------------ | :----------------------------------------------------------- | :------------------ | :----------------------- |
> | DATE      | 3             | 1000-01-01/9999-12-31                                        | YYYY-MM-DD          | 日期值                   |
> | TIME      | 3             | '-838:59:59'/'838:59:59'                                     | HH:MM:SS            | 时间值或持续时间         |
> | YEAR      | 1             | 1901/2155                                                    | YYYY                | 年份值                   |
> | DATETIME  | 8             | 1000-01-01 00:00:00/9999-12-31 23:59:59                      | YYYY-MM-DD HH:MM:SS | 混合日期和时间值         |
> | TIMESTAMP | 4             | 1970-01-01 00:00:00/2038结束时间是第 **2147483647** 秒，北京时间 **2038-1-19 11:14:07**，格林尼治时间 2038年1月19日 凌晨 03:14:07 | YYYYMMDD HHMMSS     | 混合日期和时间值，时间戳 |
>
> ------
>
> ## 字符串类型
>
> 字符串类型指CHAR、VARCHAR、BINARY、VARBINARY、BLOB、TEXT、ENUM和SET。该节描述了这些类型如何工作以及如何在查询中使用这些类型。
>
> | 类型       | 大小                  | 用途                            |
> | :--------- | :-------------------- | :------------------------------ |
> | CHAR       | 0-255 bytes           | 定长字符串                      |
> | VARCHAR    | 0-65535 bytes         | 变长字符串                      |
> | TINYBLOB   | 0-255 bytes           | 不超过 255 个字符的二进制字符串 |
> | TINYTEXT   | 0-255 bytes           | 短文本字符串                    |
> | BLOB       | 0-65 535 bytes        | 二进制形式的长文本数据          |
> | TEXT       | 0-65 535 bytes        | 长文本数据                      |
> | MEDIUMBLOB | 0-16 777 215 bytes    | 二进制形式的中等长度文本数据    |
> | MEDIUMTEXT | 0-16 777 215 bytes    | 中等长度文本数据                |
> | LONGBLOB   | 0-4 294 967 295 bytes | 二进制形式的极大文本数据        |
> | LONGTEXT   | 0-4 294 967 295 bytes | 极大文本数据                    |
>
> **注意**：char(n) 和 varchar(n) 中括号中 n 代表字符的个数，并不代表字节个数，比如 CHAR(30) 就可以存储 30 个字符。

### 修改基本表

- ALTER TABLE <表名>

    - [ADD[COLUMN] <新列名> <数据类型> [ 完整性约束 ] ]
    - [ADD <表级完整性约束>]
    - [DROP [ COLUMN ] <列名> [CASCADE| RESTRICT] ]
    - [DROP CONSTRAINT<完整性约束名>[ RESTRICT | CASCADE ] ]
    - [ALTER COLUMN <列名><数据类型> ] ;
    - ADD子句用于增加新列、新的列级完整性约束条件和新的表级完整性约束条件
    - DROP COLUMN子句用于删除表中的列
        - CASCAD:则自动删除引用了该列的其他对象
        - RESTRICT:如果该列引用了其他对象,则关系数据库解决删除
    - DROP CONSTRAINT子句用于删除指定的完整性约束条件
    - ALTER COLUMN子句用于修改原有的列定义，包括修改列名和数据类型

- > [例3.8] 向Student表增加“入学时间”列，其数据类型为日期型
    >
    > ALTER TABLE Student ADD S_entrance DATE;

- > [例3.9] 将年龄的数据类型由字符型（假设原来的数据类型是字符型）改为整数。
    >
    > ALTER TABLE Student ALTER COLUMN Sage INT;
    >
    > **[例3.10] 增加课程名称必须取唯一值的约束条件。**
    >
    > ALTER TABLE Course ADD UNIQUE(Cname);

### 删除基本表

- DROP TABLE <表名>［RESTRICT| CASCADE］;

    - RESTRICT：删除表是有限制的。
    - CASCADE：删除该表没有限制。

- > [例3.11] 删除Student表
    >
    > DROP TABLE Student CASCADE;

    ![image-20200428105343298](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-28-025343.png)

### 索引

常见的索引:

- 顺序文件上的索引
- B+树索引
- 散列（hash）索引
- 位图索引

#### 创建索引

> CREATE [UNIQUE] [CLUSTER] INDEX <索引名>
>
> ON <表名>(<列名>[<次序>][,<列名>[<次序>] ]…);
>
> - 升序：ASC(默认)
> - 降序:DESC
>
> ---
>
> [例3.13] 为学生-课程数据库中的Student，Course，SC三个 表建立索引。Student表按学号升序建唯一索引，Course表 按课程号升序建唯一索引 ， SC 表按学号升序和课程号降序 建唯一索引
>
> ```sql
> CREATE UNIQUE INDEX Stusno ON Student(Sno);
> CREATE UNIQUE INDEX Coucno ON Course(Cno);
> CREATE UNIQUE INDEX SCno ON SC(Sno ASC,Cno DESC);
> ```

#### 修改索引

> ALTER INDEX<旧索引名>RENAME TO<新索引名>
>
> 例 3.149 将 SC 表的 SCno 索引名改为 Scsno 
>
> ALTER INDEX Scno RENAME TO Scsno

#### 删除索引

> DROP INDEX <索引名>;
>
> [例3.15] 删除Student表的Stusname索引
>
> DROP INDEX Stusname;

---

## 数据查询_1

### 单表查询

一共有 6 个子句

![image-20200429182651626](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-29-102651.png)



- SELECT选择基本表的行和列 ,初始状态下不去重复,
    - DISTINC 关键字可以去重
- Group BY-HAVING (聚集函数)
- order  by  排序

---
#### SELECT子句

> [例3.16] 查询全体学生的学号与姓名。
>
> ```sql
> SELECT Sno,Sname
> FROM Student;
> ```
>
> [例3.18] 查询全体学生的详细记录
>
> ```sql
> SELECT Sno,Sname,Ssex,Sage,Sdept
> FROM Student;
> 或
> SELECT *
> FROM Student;
> ```
>
> 

#### 查询计算表达式:

> [例3.19] 查全体学生的姓名及其出生年份
>
> ```sql
> SELECT Sname,2020-Sage
> FROM Student;
> ```
>
> [例3.20] 查询全体学生的姓名、出生年份和所在的院系，要 求用小写字母表示系名。
>
> ```sql
> SELECT Sname,'Year of Birth: ',2014-Sage,LOWER(Sdept)
> FROM Student;
> ```
>
> 使用列别名改变查询结果的列标题:
>
> ```sql
> SELECT Sname NAME,'Year of Birth:' BIRTH,2014-Sage BIRTHDAY,LOWER(Sdept) DEPARTMENT
> FROM Student;
> ```
>
> [例3.21] 查询选修了课程的学生学号。
>
> ```sql
> SELECT DISTINCT Sno
> FROM SC;
> ```
>
> 

![image-20200429183507845](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-29-103508.png)

> [例3.22] 查询计算机科学系全体学生的名单。
>
> ```
> SELECT Sname
> FROM Student
> WHERE Sdept=‘CS’;
> ```
>
> [例3.23]查询所有年龄在20岁以下的学生姓名及其年龄。
>
> ````
> SELECT Sname,Sage
> FROM Student
> WHERE Sage < 20;
> ````
>
> [例3.24]查询考试成绩有不及格的学生的学号。
>
> ````
> SELECT DISTINCT Sno
> FROM SC
> WHERE Grade<60;
> ````
>
> [例3.25] 查询年龄在20~23岁（包括20岁和23岁）之间的学生 的姓名、系别和年龄
>
> ````
> SELECT Sname, Sdept, Sage
> FROM Student
> WHERE Sage BETWEEN 20 AND 23;
> ````
>
> [例3.26] 查询年龄不在20~23岁之间的学生姓名、系别和年龄
>
> ```sql
> SELECT Sname, Sdept, Sage
> FROM Student
> WHERE Sage NOT BETWEEN 20 AND 23;
> ```
>
> [例3.27]查询计算机科学系（CS）、数学系（MA）和信息 系（IS）学生的姓名和性别。
>
> ```sql
> SELECT Sname, Ssex
> FROM Student
> WHERE Sdept IN ('CS','MA','IS' );
> ```
>
> [ 例 3.28] 查询既不是计算机科学系 、 数学系 ， 也不是信息系 的学生的姓名和性别。
>
> ```sql
> SELECT Sname, Ssex
> FROM Student
> WHERE Sdept NOT IN ('CS','MA’,'IS' );
> ```
>
> 

#### 字符串匹配:

%:如a%b表示以a开头，以b结尾的任意长度的字符串

_:a_b表示以a开头，以b结尾的长度为3的任意字符串

> [例3.29] 查询学号为201215121的学生的详细情况。
>
> ```sql
>SELECT *
> FROM Student
>WHERE Sno LIKE ‘201215121';//WHERE Sno = ' 201215121 ';
> ```
> 
> [例3.30] 查询所有刘某学生的姓名、学号和性别。
> 
> ```sql
>SELECT Sname, Sno, Ssex
> FROM Student
>WHERE Sname LIKE '刘%';
> ```
> 
> [例3.31] 查询姓"欧阳"且全名为三个汉字的学生的姓名。
> 
> ```sql
>SELECT Sname
> FROM Student
>WHERE Sname LIKE '欧阳__';
> ```
> 
> [例3.32] 查询名字中第2个字为"阳"字的学生的姓名和学号。
> 
> ```sql
>SELECT Sname，Sno
> FROM Student
>WHERE Sname LIKE '__阳%';
> ```
> 
> [例3.33] 查询所有不姓刘的学生姓名、学号和性别。
> 
> ```sql
>SELECT Sname, Sno, Ssex
> FROM Student
>WHERE Sname NOT LIKE '刘%';
> ```
> 
> [例3.34] 查询DB_Design课程的课程号和学分。
> 
> ```sql
>SELECT Cno，Ccredit
> FROM Course
>WHERE Cname LIKE 'DB\_Design' ESCAPE '\ ' ;
> ```
> 
> `\_`转义表示`_`
> 
> [例3.35] 查询以"DB_"开头，且倒数第3个字符为 i的课程的 详细情况。
>
> ```sql
>SELECT *
> FROM Course
>WHERE Cname LIKE 'DB\_%i_ _' ESCAPE '\ ' ;
> ```
> 
> [例3.36] 某些学生选修课程后没有参加考试，所以有选课记录，但没 有考试成绩。查询缺少成绩的学生的学号和相应 的课程号。
> 
> ```sql
>SELECT Sno，Cno
> FROM SC
>WHERE Grade IS NULL
> ```
> 
> [例3.37] 查所有有成绩的学生学号和课程号。
> 
> ```sql
>SELECT Sno，Cno
> FROM SC
>WHERE Grade IS NOT NULL;
> ```
> 
> [例3.38] 查询计算机系年龄在20岁以下的学生姓名。
> 
> ```sql
>SELECT Sname
> FROM Student
>WHERE Sdept= 'CS' AND Sage<20;
> ```
> 
> [例3.27] 查询计算机科学系（CS）、数学系（MA）和信息系 （IS）学生的姓名和性别。
> 
> ```sql
>SELECT Sname, Ssex
> FROM Student
>WHERE Sdept IN ('CS','MA’,'IS' );
> ```
> 
> [ 例 3.28] 查询既不是计算机科学系 、 数学系 ， 也不是信息系 的学生的姓名和性别。
> 
> ```sql
>SELECT Sname, Ssex
> FROM Student
>WHERE Sdept NOT IN ('IS','MA’,'CS' );
> ```
> 
> [例3.29] 查询学号为201215121的学生的详细情况。
> 
> ```sql
>SELECT *
> FROM Student
>WHERE Sno LIKE ‘201215121';
> ```
> 
> [例3.30] 查询所有姓刘学生的姓名、学号和性别。
> 
> ```sql
>SELECT Sname, Sno, Ssex
> FROM Student
>WHERE Sname LIKE '刘%';
> ```
> 
> [例3.31] 查询姓"欧阳"且全名为三个汉字的学生的姓名。
> 
> ```sql
>SELECT Sname
> FROM Student
>WHERE Sname LIKE '欧阳__';
> ```
> 
> [例3.32] 查询名字中第2个字为"阳"字的学生的姓名和学号。
> 
> ```sql
>SELECT Sname，Sno
> FROM Student
>WHERE Sname LIKE '__阳%';
> ```
> 
> [例3.33] 查询所有不姓刘的学生姓名、学号和性别。
> 
> ```sql
>SELECT Sname, Sno, Ssex
> FROM Student
>WHERE Sname NOT LIKE '刘%';
> ```
> 
> [例3.34] 查询DB_Design课程的课程号和学分。
> 
> ```sql
>SELECT Cno，Ccredit
> FROM Course
>WHERE Cname LIKE 'DB\_Design' ESCAPE '\ ' ;
> ```
> 
> [例3.35] 查询以"DB_"开头，且倒数第3个字符为 i的课程的 详细情况。
> 
> ```sql
>SELECT *
> FROM Course
>WHERE Cname LIKE 'DB\_%i_ _' ESCAPE '\ ' ;
> ```
> 
> [例3.36] 某些学生选修课程后没有参加考试，所以有选课记 录，但没 有考试成绩。查询缺少成绩的学生的学号和相应 的课程号。
> 
> ```sql
>SELECT Sno，Cno
> FROM SC
>WHERE Grade IS NULL //IS”不能用“=”代替
> ```
> 
> [例3.37] 查所有有成绩的学生学号和课程号。
> 
> ```sql
>SELECT Sno，Cno
> FROM SC
>WHERE Grade IS NOT NULL;
> ```
> 
> [例3.38] 查询计算机系年龄在20岁以下的学生姓名。
> 
> ```sql
>SELECT Sname
> FROM Student
>WHERE Sdept= 'CS' AND Sage<20;
> ```
> 
> 

#### ORDER BY 子句

可以按一个或多个属性列排序

升序：ASC；降序：DESC；缺省值为升序


> [例3.39]查询选修了3号课程的学生的学号及其成绩，查询结 果按分数降序排列。
>
> ```sql
> SELECT Sno, Grade
> FROM SC
> WHERE Cno= ' 3 '
> ORDER BY Grade DESC;
> ```
>
> [例3.40]查询全体学生情况，查询结果按所在系的系号升序 排列，同一系中的学生按年龄降序排列。
>
> ```sql
> SELECT *
> FROM Student
> ORDER BY Sdept, Sage DESC;
> ```
>
> 

#### GROUP BY

Group By分组聚合是我们在使用过程中使用最多的SQL之一（另外一个使用最多的估计就是JOIN了吧），为了让大家详细了解Group By的计算执行过程，我们下面使用一张图来详细讲解一下。



我们的数据就是左表，有name和score两列，我们要求每个name下，score的最大值，技术术语是：根据每个name进行分组，根据max函数进行聚合（我们和别人交流的时候，记得要这样子说）。

Group by语句先会根据每个name进行分组，把每个name对应的score都放到一个地方，如中间的表格所示。例如，name为b的行，有三个对应的score，其他类推，每个数据库都会做好这一步的准备，也就是Group By操作执行之后，这个中间的数据体就会存在了，它等待着我们调用聚合函数去统计它。

然后，我们的聚合函数要调用的是：max，也就是从一堆数据中，取出最大值的函数。聚合函数还有其他值，例如和最大值max对应的最小值min，求和sum等等，所有这些聚合函数，都是针对一个数组进行处理的，所谓的数据，就是b里面那三个值。

yes，有了中间那个数据体，我们就可以非常简单地理解所谓的聚合函数了。OK，聚合函数执行完成后，把返回的值交给分组的字段，组合成一行记录，数据库等待所有的分组都执行完成，把数据组合起来，返回给我们。

这个就是整个分组聚合的过程，如果你觉得还是不能理解，那么在本文下面回复吧。



**什么是分组查询？**将查询结果按照1个或多个字段进行分组，字段值相同的为一组
其实就是按照某一列进行分类**分组使用****SELECT gender from employee GROUP BY gender;**

根据gender字段来分组，gender字段的全部值只有两个('男'和'女'),所以分为了两组
当group by单独使用时，只显示出每一组的第一条记录
所以group by单独使用时的实际意义不大![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122028.jpg)

对这个表进行GROUP BY操作`SELECT * FROM employee;`![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122037.jpg)

只显示了每一组第一条记录男生的第一个人是张三 女生的第一个人是王五![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122034.jpg)

所以说GROUP BY 一般不单独使用
**一般来说 你按照什么分组 就查询什么东西****比如：

select department from employee group by department;

![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122031.jpg)

查询结果如上。**分完组然后呢？如何对分组后的结果进行查询？关键字：group_concat()**将职员表 按照部门分组 查询每个部门职员的姓名

`select department,group_concat(name) from employee group by department;`

结果如下：

![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122033.jpg)

GROUP BY + 聚合函数for example:**将职员表 按照部门分组 查询每个部门职员的薪水 和 **薪水总数*`select department,group_concat(salary),sum(salary) from employee group by department;`*

![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122035.jpg)

查询每个部门的名称 以及 每个部门的人数`select department,group_concat(name),count(*) from employee group by department;`查询每个部门的部门名称 以及 每个部门工资大于1500的人数PS：**先把大于1500的人查出来 再做分组**`select department,group_concat(salary),count(*) from employee group by department;`**group by + having**用来分组**查询后**制定一些条件来输出查询结果having的作用和where一样，但having只能用于group by查询工资总和大于9000的部门名称以及工资和`SELECT department,GROUP_CONCAT(salary),SUM(salary) FROM employee GROUP BY department HAVING SUM(salary) > 9000;`having和where的区别having是在分组后对数据进行过滤
where是在分组前对数据进行过滤
having后面可以使用分组函数（统计函数)
where后面不可以使用分组函数
where是对分组前记录的条件，如果某行记录没有满足where子句的条件，那么这行记录不会参加分组；而having是对分组后数据的约束
查询工资和大于2000的 工资总和大于6000的部门名称以及工资和
`SELECT department,GROUP_CONCAT(salary).SUM(salary) FROM employee WHERE salary > 2000 GROUP BY department HAVING SUNM(salary) > 9000 ORDER BY SUM(salary) DESC; //降序排列 `
**书写顺序**

![img](https://pic3.zhimg.com/v2-166d3cee10b01a68e90af2a5d5ed9866_b.jpg)**Limit的使用**

![img](https://pic3.zhimg.com/v2-cc55920af6e230cfe251bf53dd35a9c6_b.jpg)

SELECT * FROM employee LIMIT 3,3;

从第三行开始 取三条数据PS：行是从0开始数的**![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122028.jpg)![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122036.jpg)**limit可以用于分页操作**![img](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-17-122032.jpg)

#### 聚集函数

- DISTINCT 去重
- ALL 所有(默认)

- 统计元组个数COUNT(*)
- 统计一列中值的个数COUNT([DISTINCT|ALL] <列名>)
- 计算一列值的总和SUM([DISTINCT|ALL] <列名>)
- 计算一列值的平均值AVG([DISTINCT|ALL] <列名>)
- 求一列中的最大值和最小值:
    - MAX([DISTINCT|ALL] <列名>)
    - MIN([DISTINCT|ALL] <列名>)

> [例3.41] 查询学生总人数。
>
> ```sql
> SELECT COUNT(*)
> FROM Student;
> ```
>
> [例3.42] 查询选修了课程的学生人数。
>
> ```sql
> SELECT COUNT(DISTINCT Sno)
> FROM SC;
> ```
>
> [例3.43] 计算1号课程的学生平均成绩。
>
> ```sql
> SELECT AVG(Grade)
> FROM SC
> WHERE Cno= ' 1 ';
> ```
>
> [例3.44] 查询选修1号课程的学生最高分数。
>
> ```sql
> SELECT MAX(Grade)
> FROM SC
> WHERE Cno='1';
> ```
>
> [例3.45 ] 查询学生201215012选修课程的总学分数。
>
> ```sql
> SELECT SUM(Ccredit)
> FROM SC,Course
> WHERE Sno='201215012' AND SC.Cno=Course.Cno;
> ```
>
> 

GROUP BY 子句:细化聚集函数的作用对象

> [例3.46] 求各个课程号及相应的选课人数。
>
> ```sql
> SELECT Cno，COUNT(Sno)
> FROM SC
> GROUP BY Cno;
> ```
>
> ![image-20200429201446526](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-29-121446.png)
>
> [例3.47] 查询选修了3门以上课程的学生学号。
>
> ```sql
> SELECT Sno
> FROM SC
> GROUP BY Sno HAVING COUNT(Cno) >3;
> ```
>
> [例3.48 ]查询平均成绩大于等于90分的学生学号和平均成绩
>
> ```sql
> SELECT Sno, AVG(Grade)
> FROM SC
> GROUP BY Sno HAVING AVG(Grade)>=90;
> ```
>
> 

HAVING 短语与 WHERE 子句的区别：

- 作用对象不同

- WHERE 子句作用于基表或视图，从中选择满足条件的元组
-  HAVING 短语作用于组，从中选择满足条件的组

### 综合练习

> [练习1] 列出计算机系姓刘的同学的信息，按照学号大小 排序
>
> ```sql
> SELECT *
> FROM Student
> WHERE Sdept=‘CS’ AND Sname LIKE ‘刘%’
> ORDER BY Sno;
> ```
>
> [练习2] 按系并区分男女统计各系学生的人数、并按照人 数降序排序
>
> ```sql
> SELECT Sdept, Ssex,COUNT(Sno)
> FROM Student
> GROUP BY Sdept,Ssex
> ORDER BY COUNT(Sno) DESC;
> ```

## 数据连接

不像关系代数中“连接”是用一个特殊符号来表达的， 在 SQL中“连接”是用“连接条件”来表达的。

一般格式：`[<表名1>.]<列名1> <比较运算符> [<表名2>.]<列名2>`

- 等值连接
- 自身连接
- 外连接
- 多表连接

### 等值连接

连接运算符为 “=”

> [例 3.49] 查询每个学生及其选修课程的情况
>
> ```sql
> SELECT Student.*, SC.*
> FROM Student, SC
> WHERE Student.Sno = SC.Sno;
> ```
>
> ![image-20200429204612064](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-29-124612.png)

- 嵌套循环法（NESTED-LOOP）
- 排序合并法（SORT-MERGE）
- 索引连接（INDEX-JOIN）

> [例 3.51 ]查询选修2号课程且成绩在90分以上的所有学生的学号和姓名。
>
> ```sql
> SELECT Student.Sno, Sname
> FROM Student, SC
> WHERE Student.Sno=SC.Sno AND
> SC.Cno=' 2 ' AND SC.Grade>90;
> ```

### 自身连接

自身连接：一个表与其自己进行连接，是一种特殊的连接

!!!**需要给表起别名以示区别**

!!!**由于所有属性名都是同名属性，因此必须使用别名前缀**

> [例 3.52]查询每一门课的直接先修课的名称
>
> ```sql
> SELECT FIRST.Cname ， SECOND.Cname
> FROM Course FIRST, Course SECOND
> WHERE FIRST.Cpno = SECOND.Cno;
> ```
>
> ![image-20200429212436768](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-29-132437.png)
>
> ![image-20200429212452269](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-29-132453.png)

### 外连接

- 外连接操作以指定表为连接主体，将主体表中不满足连 接条件的元组一并输出
- 普通连接操作只输出满足连接条件的元组

> [例 3.49] 查询每个学生及其选修课程的情况
>
> ```sql
> SELECT Student.Sno,Sname,Ssex,Sage,Sdept,Cno,Grade
> FROM Student LEFT OUT JOIN SC ON
> (Student.Sno=SC.Sno);
> //或
> SELECT Student.Sno,Sname,Ssex,Sage,Sdept,Cno,Grade
> FROM Student,SC
> WHERE Student.Sno（+）=SC.Sno;
> ```
>
> 

### 多表连接

> [例3.54]查询每个学生的学号、姓名、选修的课程名及成绩
>
> ```sql
> SELECT Student.Sno, Sname, Cname, Grade
> FROM Student,SC,Course /*多表连接*/
> WHERE Student.Sno = SC.Sno AND SC.Cno = Course.Cno;
> ```

### 嵌套查询

#### 不相关子查询：

子查询的查询条件不依赖于父查询

由里向外逐层处理。即每个子查询在上一级查询处理之前求解，子查询的结果用于建立其父查询的查找条件。

#### 相关子查询：

子查询的查询条件依赖于父查询

#### 带 IN 谓词

> [例 3.55] 查询与“刘晨”在同一个系学习的学生。
>
> 分步法:
>
> ① 确定“刘晨”所在系名
>
> ```sql
> SELECT Sdept
> FROM Student
> WHERE Sname= ' 刘晨 ';
> ```
>
> ② 查找所有在CS系学习的学生。
>
> ```sql
> SELECT Sno, Sname, Sdept
> FROM  Student
> WHERE Sdept= ' CS ';
> ```
>
> ---
>
> 将第一步查询嵌入到第二步查询的条件中:
>
> ```sql
> SELECT Sno, Sname, Sdept
> FROM Student
> WHERE Sdept IN
> 	(SELECT Sdept
> FROM Student
> WHERE Sname= '刘晨'
> );
> 
> ```
>
> ---
>
> 自身连接法:
>
> ```sql
> SELECT S1.Sno, S1.Sname,S1.Sdept
> FROM Student S1,Student S2
> WHERE S1.Sdept = S2.Sdept AND
> S2.Sname = '刘晨';
> ```
>
> ---
>
> [例 3.56]查询选修了课程名为“信息系统”的学生学号和姓名
>
> ```sql
> SELECT Sno,Sname  //③ 最后在Student关系中取出Sno和Sname
> FROM Student
> WHERE Sno IN
> 	(SELECT Sno //② 然后在SC关系中找出选修了3号课程的学生学号
> 	 FROM SC
> WHERE Cno IN
> 		(SELECT Cno //① 首先在Course关系中找出“信息系统”的课程号，为3号
>   	FROM Course
>   	WHERE Cname= '信息系统')
> );
> ```
>
> 或:
>
> ```sql
> SELECT Sno,Sname
> FROM Student,SC,Course
> WHERE Student.Sno = SC.Sno AND
> 	SC.Cno = Course.Cno AND
> 	Course.Cname='信息系统';
> ```
>
> 

#### 带有比较运算符的子查询

>  [例 3.55] 查询与“刘晨”在同一个系学习的学生。
>
>  ```sql
>  SELECT Sno,Sname,Sdept
>  FROM Student
>  WHERE Sdept=
>  	(SELECT Sdept/*由于一个学生只可能在一个系学习 , 用=代替*/
>    FROM Student
>    WHERE Sname= '刘晨');
>  ```
>
>  [例 3.57 ]找出每个学生超过他选修课程平均成绩的课程号。(自身嵌套的例子)
>
>  ```sql
>  SELECT Sno, Cno
>  FROM SC x
>  WHERE Grade >=(SELECT AVG（Grade）
>  							 FROM SC y
>                 WHERE y.Sno=x.Sno);
>  ```
>
>  

#### 带有 ANY (SOME）或 ALL 谓词的子査询

- `> ANY 大于子查询结果中的某个值`
- `> =ANY 大于等于子査询结果中的某个值`
- `!=(或<>) ANY 不等于子査询结果中的某个值`
- `!=(或<>) ALL 不等于子查询结果中的任何一个值`
- ![image-20200430001129003](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-04-29-161129.png)

> [例 3.58] 查询非计算机科学系中比计算机科学系任意一个 学生年龄小的学生姓名和年龄
>
> ```sql
> SELECT Sname,Sage
> FROM Student
> WHERE Sage < ANY (SELECT Sage
>                   FROM Student
>                   WHERE Sdept= 'CS')
> 	AND Sdept <> 'CS' ;/*父查询块中的条件 */
> ```
>
> 或者使用聚集函数
>
> ```sql
> SELECT Sname,Sage
> FROM Student
> WHERE Sage <(SELECT MAX（Sage）
>              FROM Student
>              WHERE Sdept= 'CS')
> 	AND Sdept <> 'CS ';
> ```
>
> [例 3.59] 查询非计算机科学系中比计算机科学系所有学 生年龄都小的学生姓名及年龄。
>
> ```sql
> SELECT Sname,Sage
> FROM Student
> WHERE Sage < ALL(SELECT Sage
>                  FROM Student
>                  WHERE Sdept= 'CS')
> 	AND Sdept <> 'CS’;
> ```
>
> ```sql
> SELECT Sname,Sage
> FROM Student
> WHERE Sage <(SELECT MIN(Sage)
>              FROM Student
>              WHERE Sdept= 'CS')
> 	AND Sdept <>'CS';
> ```

#### 带有EXISTS谓词的子查询

- 带有EXISTS谓词的子查询产生逻辑真值“true”或逻辑假值“false”。

- 由 EXISTS引出的子查询，其目标列表达式通常都用 `*`

- 可以把带有全称量词的谓词转换为等价的带有存在量词的谓词：

    $$
        (\forall x) P \equiv \neg(\exists x(\neg P))
    $$
- 可以利用谓词演算将逻辑蕴涵谓词等价转换为：
    $$
    p \rightarrow q \equiv \neg p \vee q
    $$

> [例 3.60]查询所有选修了1号课程的学生姓名。
>
> ```sql
> SELECT Sname
> FROM Student
> WHERE EXISTS(SELECT *
>           FROM SC
>           WHERE Sno=Student.Sno AND Cno= ' 1 ');
> ```
>
> [例 3.61] 查询没有选修1号课程的学生姓名。
>
> ```sql
> SELECT Sname
> FROM Student
> WHERE NOT EXISTS(SELECT *
>           FROM SC
>           WHERE Sno=Student.Sno AND Cno= ' 1 ');
> ```
>
> [例 3.55]查询与“刘晨”在同一个系学习的学生。
>
> ```sql
> SELECT Sno,Sname,Sdept
> FROM Student S1
> WHERE EXISTS(SELECT *
>           FROM Student S2
>           WHERE S2.Sdept = S1.Sdept 
>           AND S2.Sname = '刘晨');
> ```
>
> **[例 3.62] 查询选修了全部课程的学生姓名。**
>
> ```sql
> SELECT Sname
> FROM Student
> WHERE NOT EXISTS(SELECT *
>               FROM Course
>               WHERE NOT EXISTS(SELECT *
>                                FROM SC
>                                WHERE Sno= Student.Sno
>                                AND Cno= Course.Cno)
> 									);
> ```
>
> **[例 3.63]查询至少选修了学生17150011001选修的全部课程的 学生号码。**
>
> 用逻辑蕴涵表达该查询：
>
> 用P表示谓词 “学生201215122选修了课程y”
>
> 用q表示谓词 “学生x选修了课程y”
>
> 则上述查询为: $(\forall y) p \rightarrow q$对
> $$
> \begin{aligned}(\forall y) & p \rightarrow q \equiv \neg(\exists y(\neg(p \rightarrow q))\\ & \equiv \neg(\exists y(\neg(\neg p \vee q))) \\ & \equiv \neg \exists y(p \wedge \neg q) \end{aligned}
> $$
>
> ```sql
> SELECT DISTINCT SCX.Sno
> FROM SC SCX
> WHERE NOT EXISTS(SELECT *
>                  FROM SC SCY
>                  WHERE SCY.Sno = '17150011001' AND
>                  NOT EXISTS(SELECT *
>                             FROM SC SCZ
>                             WHERE SCZ.Sno=SCX.Sno 
>                             AND SCZ.Cno=SCY.Cno));
> ```
>
> 

## 数据查询_2_

### 集合查询

- 并操作UNION
    - UNION：将多个查询结果合并起来时，系统自动去掉重复元组(系统默认)
    - UNION ALL：将多个查询结果合并起来时，保留重复元组
- 交操作INTERSECT
- 差操作EXCEPT

!参加集合操作的各查询结果的列数必须相同;对应 项的数据类型也必须相同

> [例 3.64] 查询计算机科学系的学生及年龄不大于19岁的学生。
>
> ```sql
> SELECT *
> FROM Student
> WHERE Sdept= 'CS'
> 
> UNION
> 
> SELECT *
> FROM Student
> WHERE Sage<=19;
> ```
>
> [例 3.65] 查询选修了课程1或者选修了课程2的学生。
>
> ```sql
> SELECT Sno
> FROM SC
> WHERE Cno=' 1 '
> 
> UNION
> 
> SELECT Sno
> FROM SC
> WHERE Cno= ' 2 ';
> ```
>
> [例3.66] 查询计算机科学系的学生与年龄不大于19岁的学生 的交集。
>
> ```sql
> SELECT *
> FROM Student
> WHERE Sdept='CS'
> 
> INTERSECT
> 
> SELECT *
> FROM Student
> WHERE Sage<=19
> ```
>
> [例 3.66] 实际上就是查询计算机科学系中年龄不大 于19岁的学生。
>
> ```sql
> SELECT *
> FROM Student
> WHERE Sdept= 'CS' AND Sage<=19;
> ```
>
> [例 3.67]查询既选修了课程1又选修了课程2的学生。
>
> ```sql
> SELECT Sno
> FROM SC
> WHERE Cno=' 1 '
> 
> INTERSECT
> 
> SELECT Sno
> FROM SC
> WHERE Cno='2 ';
> ```
>
> ```sql
> SELECT Sno
> FROM SC
> WHERE Cno=' 1 ' AND Sno IN(SELECT Sno
>                            FROM SC
>                            WHERE Cno=' 2 ');
> ```
>
> ```sql
> //下面是一个典型的错误例子
> SELECT Sno
> FROM SC
> WHERE Cno='1' and Cno='2'
> ```
>
> [例 3.68] 查询计算机科学系的学生与年龄不大于19岁的 学生的差集。
>
> ```sql
> SELECT *
> FROM Student
> WHERE Sdept='CS'
> 
> EXCEPT
> 
> SELECT *
> FROM Student
> WHERE Sage <=19;
> ```
>
> ```sql
> SELECT *
> FROM Student
> WHERE Sdept= 'CS' AND Sage>19;
> ```

## 数据更新

### 插入数据

#### 插入元组

`INSERT INTO <表名> [(<属性列1>[,<属性列2 >…)] `

`VALUES (<常量1> [,<常量2>]… );`



##### INTO 子句

四种情况:

- 属性列的顺序可与表定义中的顺序不一致

- 指定要插入数据的表名及属性列
- 没有指定属性列：表示要插入的是一条完整的元组，且 属性列属性与表定义中的顺序一致
- 指定部分属性列：插入的元组在其余属性列上取空值

##### VALUES子句

提供的值必须与INTO子句匹配

> [例3.69]将一个新学生元组（学号：201215128;姓名：陈冬; 性别：男;所在系：IS;年龄：18岁）插入到Student表中。
>
> ```sql
> INSERT INTO Student (Sno,Sname,Ssex,Sdept,Sage)
> VALUES ('17150011001','陈扬','男','CS',21);
> ```
>
> [例3.71] 插入一条选课记录（ '200215128','1 '）。
>
> ```sql
> INSERT INTO SC(Sno,Cno)
> VALUES ('201215128 ',' 1 ');
> ```
>
> 或:
>
> ```sql
> INSERT INTO SC
> VALUES (' 201215128 ',' 1 ',NULL);
> ```
>
> [例3.70]将学生张成民的信息插入到Student表中。
>
> ```sql
> INSERT INTO Student
> VALUES ('201215126','张成民','男’,18,'CS');
> ```
>
> 在 student 后没有定义每个元素的意义,就必须和表一一对应



#### 插入子查询结果

`INSERT INTO <表名> [(<属性列1> [,<属性列2>… )]子查询;`

> [例3.72] 对每一个系，求学生的平均年龄，并把结果存入数据库
>
> 建表:
>
> ```sql
> CREATE TABLE Dept_age(Sdept CHAR(15)/*系名*/
>                       Avg_age SMALLINT);/*学生平均年龄*/
> ```
>
> 插入数据:
>
> ```sql
> INSERT INTO Dept_age(Sdept,Avg_age)
> 	SELECT Sdept，AVG(Sage)
> 	FROM Student
> 	GROUP BY Sdept;
> ```
>
> 插入数据的时候会检测完整性:
>
> - 实体完整性
> - 参照完整性
> - 用户定义的完整性
>     - NOT NULL约束
>     - UNIQUE约束
>     - 值域约束



### 修改数据

`UPDATE <表名>`

`SET <列名>=<表达式>[,<列名>=<表达式>]…`

`[WHERE <条件>];`

三种修改方式:

- 修改某一个元组的值
- 修改多个元组的值
- 带子查询的修改语句

> [例3.73] 将学生201215121的年龄改为22岁
>
> ```sql
> UPDATE Student
> SET Sage=22
> WHERE Sno=' 201215121 ';
> ```
>
> **[例3.74] 将所有学生的年龄增加1岁。**
>
> ```sql
> UPDATE Student
> SET Sage= Sage+1;
> ```
>
> [例3.75] 将计算机科学系全体学生的成绩置零。
>
> ```sql
> UPDATE SC
> SET Grade=0
> WHERE Sno IN(SELETE Sno
>              FROM Student
>              WHERE Sdept= 'CS' );
> ```
>
> 更新数据同样会检测完整性约束

### 删除数据

`DELETE FROM <表名>`

`[WHERE <条件>];`

**删除指定表中满足WHERE子句条件的元组,无该子句将会删除表中的全部元组**

三种删除方式:

- 删除某一个元组的值
- 删除多个元组的值
- 带子查询的删除语句

> [例3.76] 删除学号为201215128的学生记录。
>
> ```sql
> DELETE FROM Student
> WHERE Sno= 201215128 ';
> ```
>
> [例3.77] 删除所有的学生选课记录。
>
> ```sql
> DELETE FROM SC;
> ```
>
> [例3.78] 删除计算机科学系所有学生的选课记录。
>
> ```sql
> DELETE
> FROM SC
> WHERE Sno IN(SELETE Sno
>              FROM Student
>              WHERE Sdept= 'CS') ;
> ```
>
> 

## 空值的处理

空值不等于 NULL,表示本来应该有一个值,但是现在未知

判断一个属性的值是否为空值，用IS NULL或IS NOT NULL来表示。

>[例 3.81]找出漏填了性别或者年龄信息的记录
>
>```sql
>SELECT *
>FROM Student
>WHERE Ssex IS NULL OR Sage IS NULL ；
>```
>
>

约束条件:

- 有NOT NULL约束条件的不能取空值
- 加了UNIQUE限制的属性不能取空值
- 码属性不能取空值

> [例3.82] 找出选修1号课程的不及格的学生。
>
> ```sql
> SELECT Sno
> FROM SC
> WHERE Grade < 60 AND Cno='1';
> ```
>
> [例 3.83] 选出选修1号课程的不及格的学生以及缺考的学生。
>
> ```sql
> SELECT Sno
> FROM SC
> WHERE Cno='1' AND (Grade<60 OR Grade IS NULL);
> ```

## 视图view

视图是定义在基本表之上的虚表,是从一个或几个基本表（或视图）导出的表

- 只存放视图的定义，不存放视图对应的数据
- **基表中的数据发生变化，从视图中查询出的数据也随之改变**

### 视图的定义

`CREATE VIEW`

`<视图名> [(<列名> [,<列名>]…)]`

`AS <子查询>`

`[WITH CHECK OPTION];`

- WITH CHECK OPTION:子查询中的条件表达式约束
- 子查询可以是任意的SELECT语句

- 关系数据库管理系统执行 CREATE VIEW 语句时只是把视图定义存入数据字典，并不执行其中的SELECT 语句。

- 对视图查询时，按视图的定义从基本表中将数据查出。
- 若一个视图是从单个基本表导出的，并且只是去掉了基本表的某些行和某些列，但保留了主码，我们称这类视图为**行列子集视图**。

> [例3.84] 建立信息系学生的视图。
>
> ```sql
> CREATE VIEW IS_Student
> AS
> SELECT Sno,Sname,Sage
> FROM Student
> WHERE Sdept= 'IS';
> ```
>
> [例3.85]建立信息系学生的视图，并要求进行修改和插入 操作时仍需保证该视图只有信息系的学生 。
>
> ```sql
> CREATE VIEW IS_Student
> AS
> SELECT Sno,Sname,Sage
> FROM Student
> WHERE Sdept= 'IS'
> //对该视图进行插入、修改和删除操作会自动加上Sdept='IS'的条件。
> WITH CHECK OPTION;
> ```
>
> [例3.86] 建立信息系选修了1号课程的学生的视图（包括 学号、姓名、成绩）
>
> ```sql
> CREATE VIEW IS_S1(Sno,Sname,Grade)
> AS
> SELECT Student.Sno,Sname,Grade
> FROM Student,SC
> WHERE Sdept= 'IS' AND
> 		Student.Sno=SC.Sno AND
> 		SC.Cno= '1';
> ```
>
> [例3.87] 建立信息系选修了1号课程且成绩在90分以上的学生的视图。
>
> 在视图上定义视图(套娃)
>
> ```sql
> CREATE VIEW IS_S2
> AS
> SELECT Sno,Sname,Grade
> FROM IS_S1
> WHERE Grade>=90;
> ```
>
> [例3.88] 定义一个反映学生出生年份的视图。
>
> ```sql
> CREATE VIEW BT_S(Sno,Sname,Sbirth)
> AS
> SELECT Sno,Sname,2014-Sage
> FROM Student;
> ```
>
> [例3.89] 将学生的学号及平均成绩定义为一个视图
>
> [聚集函数]
>
> ```sql
> CREAT VIEW S_G(Sno,Gavg)
> AS
> SELECT Sno,AVG(Grade)
> FROM SC
> GROUP BY Sno;
> ```
>
> [例3.90]将Student表中所有女生记录定义为一个视图
>
> ```sql
> CREATE VIEW F_Student(F_Sno,name,sex,age,dept)
> AS
> SELECT *
> FROM Student
> WHERE Ssex=‘女’;
> //* 有可能导致在后面修改 student 表的时候视图出错
> ```

### 删除视图

`DROP VIEW <视图名>[CASCADE];`

- 该语句从数据字典中删除指定的视图定义
- 如果该视图上还导出了其他视图，**使用CASCADE级联删除语句**
- 删除基本表时需要DROP由该表导出的视图

> [例3.91 ] 删除视图BT_S和IS_S1
>
> ```sql
> DROP VIEW BT_S;/*成功执行*/
> 
> DROP VIEW IS_S1;/*拒绝执行*/
> DROP VIEW IS_S1 CASCADE;/*成功执行*/
> ```

### 查询视图

- 查询视图与查询基本表相同

> [例3.92] 在信息系学生的视图中找出年龄小于20岁的学生。
>
> ```sql
> SELECT  Sno,Sage
> FROM IS_Student
> WHERE Sage<20;
> ```
>
> [例3.93] 查询选修了1号课程的信息系学生
>
> ```sql
> SELECT IS_Student.Sno,Sname
> FROM IS_Student,SC
> WHERE IS_Student.Sno =SC.Sno AND SC.Cno= '1';
> ```
>
> [例3.94]在S_G视图中查询平均成绩在90分以上的学生学号 和平均成绩
>
> ```sql
> CREATE VIEW S_G(Sno,Gavg)
> AS
> SELECT Sno,AVG(Grade)
> FROM SC
> GROUP BY Sno;
> ```
>
> ```sql
> SELECT *
> FROM S_G
> WHERE Gavg>=90;
> ```
>
> 或
>
> ````sql
> SELECT *
> FROM (SELECT Sno,AVG(Grade)
>       FROM SC
>       GROUP BY Sno) 
>       AS 
>       S_G(Sno,Gavg)
> WHERE Gavg>=90;
> ````
>
> 

### 更新视图

> [例3.95] 将信息系学生视图IS_Student中学号”201215122”的学生姓名改为”刘辰”。
>
> ```sql
> UPDATE IS_Student
> SET Sname= '刘辰'
> WHERE Sno= ' 201215122 ';
> ```
>
> ```sql
> UPDATE Student
> SET Sname= '刘辰'
> WHERE Sno= ' 201215122 ' AND Sdept= 'IS';
> ```
>
> [例3.96] 向信息系学生视图IS_Student中插入一个新的学生记录，其中学号为”201215129”，姓名为”赵新”，年龄 为20岁
>
> ```sql
> INSERT
> INTO IS_Student
> VALUES(‘201215129’,’赵新’,20);
> ```
>
> ```sql
> INSERT
> INTO Student(Sno,Sname,Sage,Sdept)
> VALUES('200215129','赵新',20,'IS' );
> ```
>
> [例3.97]删除信息系学生视图IS_Student中学号为”201215129”的记录
>
> ```sql
> DELETE
> FROM IS_Student
> WHERE Sno= ' 201215129 ';
> ```
>
> ```sql
> DELETE
> FROM Student
> WHERE Sno= ' 201215129 ' AND Sdept= 'IS';
> ```
>
> 注意!聚集函数的视图在修改的时候聚集函数那一列是不可更新的
>
> ```sql
> UPDATE S_G
> SET Gavg=90
> WHERE Sno= ‘201215121’;
> //以上为反例
> ```

- 允许对行列子集视图进行更新
- 两个以上基本表导出视图原则上也不允许更新
- 若视图的字段来自集函数，则此视图不允许更新。
- 若视图定义中含有 GROUP BY 子句，则此视图不允许更新。
- 若视图定义中含有 DISTINCT 短语，则此视图不允许更新 。
- 若视图定义中有嵌套查询，并且内层查询的子句中涉及的表也是FROM导出该视图的基本表，则此视图不允许更新









 