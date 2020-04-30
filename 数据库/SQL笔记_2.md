# SQL笔记_2

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
