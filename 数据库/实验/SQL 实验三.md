1. 设计教务系统,创建三个基本表,分别是 student 表,course 表和 SC 表.

    1. 创建 student 表:

    （1）在列级定义主码

    ```sql
    CREATE TABLE Student(Sno CHAR(20) PRIMARY KEY,
                         Sname CHAR(20) NOT NULL,
                         Ssex CHAR(2),
                         Sage SMALLINT,
                         Sdept CHAR(20)
    );
    ```

    其中 SNO 学号为主码,SNAME非 NULL 满足实体完整性.

    2. 建立 Course 表

    ```sql
    CREATE TABLE Course
    	(Cno CHAR(4) PRIMARY KEY,
    Cname CHAR(40),
    Cpno CHAR(4),
    Ccredit SMALLINT,
    FOREIGN KEY (Cpno) REFERENCES Course(Cno)
    );
    ```

    Cpno是外码,被参照表是Course,被参照列是Cno

    Cpno是表示先修课

    3. 创建 SC 表:

    ```sql
    CREATE TABLE SC
    ( Sno CHAR(20),
      Cno CHAR(4),
      Grade SMALLINT,
      PRIMARY KEY (Sno,Cno),
      FOREIGN KEY (Sno) REFERENCES Student(Sno),
      FOREIGN KEY (Cno)REFERENCES Course(Cno)
    );
    ```

    /* 主码由两个属性构成，必须作为表级完整性进行定义*/

    /* 表级完整性约束条件，Sno是外码，被参照表是Student */

    /* 表级完整性约束条件， Cno是外码，被参照表是Course */

    ![image-20200504160241697](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-080242.png)

2. 建立专业表DEPT，要求专业名称Dname列取值唯一且不能取空值，专业编号Deptno列为主码。

    ```sql
    CREATE TABLE DEPT
    (	Deptno NUMERIC(2),
    	Dname CHAR(9) UNIQUE NOT NULL,/*要求Dname列值唯一, 并且不能取空值*/
    	PRIMARY KEY (Deptno)
    );
    ```

    ![image-20200504160309493](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-080309.png)

3. insert student信息

    ```sql
    INSERT INTO student(Sno,sname,ssex,sage,sdept)
    VALUES(17150011001,'Marcus','MA',21,'CS');
    INSERT INTO student(Sno,sname,ssex,sage,sdept)
    VALUES(17150011002,'Mike','MA',21,'CS');
    INSERT INTO student(Sno,sname,ssex,sage,sdept)
    VALUES(17150011003,'JIX','FA',19,'EE');
    INSERT INTO student(Sno,sname,ssex,sage,sdept)
    VALUES(17150011004,'John','FA',22,'CS');
    ```

    ![image-20200504162134895](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-082135.png)

    insert course信息

    ```sql
    INSERT INTO course(Cno,Cname,Cpno,Ccredit)
    VALUES(2,'数学',null,2);
    INSERT INTO course(Cno,Cname,Cpno,Ccredit)
    VALUES(6,'数据处理',null,2);
    INSERT INTO course(Cno,Cname,Cpno,Ccredit)
    VALUES(7,'PASCAL',6,4);
    INSERT INTO course(Cno,Cname,Cpno,Ccredit)
    VALUES(5,'数据结构',7,3);
    INSERT INTO course(Cno,Cname,Cpno,Ccredit)
    VALUES(1,'数据库',5,4);
    INSERT INTO course(Cno,Cname,Cpno,Ccredit)
    VALUES(3,'信息系统',1,4);
    INSERT INTO course(Cno,Cname,Cpno,Ccredit)
    VALUES(4,'操作系统',6,3);
    ```

    ![image-20200504163611003](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-083611.png)

    INSERT SC 信息

    ```sql
    
    INSERT INTO SC(Sno,Cno,Grade)
    VALUES(17150011001, 1 , 92);
    INSERT INTO SC(Sno,Cno,Grade)
    VALUES(17150011001, 2 , 85);
    INSERT INTO SC(Sno,Cno,Grade)
    VALUES(17150011001, 3 , 88);
    INSERT INTO SC(Sno,Cno,Grade)
    VALUES(17150011002, 1 , 90);
    INSERT INTO SC(Sno,Cno,Grade)
    VALUES(17150011002, 2 , 90);
    INSERT INTO SC(Sno,Cno,Grade)
    VALUES(17150011002, 3 , 80);
    ```

    ![image-20200504163957076](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-083957.png)

4.  查询全体学生的详细记录

    ```sql
    SELECT Sno,Sname,Ssex,Sage,Sdept
    FROM Student;
    ```

    ![image-20200504200118792](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-120118.png)

5. 查全体学生的姓名及其出生年份

    ```sql
    SELECT Sname,2020-Sage
    FROM Student;
    ```

    ![image-20200504195957680](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-115957.png)

6. 查询计算机科学系全体学生的信息。

    ```
    SELECT *
    FROM Student
    WHERE Sdept=‘CS’;
    ```

    ![image-20200504200457047](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-120457.png)

7.  查询年龄在20~23岁（包括20岁和23岁）之间的学生 的姓名、系别和年龄

    ````
    SELECT Sname, Sdept, Sage
    FROM Student
    WHERE Sage BETWEEN 20 AND 23;
    ````

    ![image-20200504200549464](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-120550.png)

8.  查询计算机科学系（CS）、数学系（MA）和信息 系（IS）学生的姓名和性别。

    ```sql
    SELECT Sname, Ssex
    FROM Student
    WHERE Sdept IN ('CS','MA','IS' );
    ```

    ![image-20200504200717046](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-120717.png)

9. 查询所有 M 开头学生的姓名、学号和性别。

    ```sql
    SELECT Sname, Sno, Ssex
    FROM Student
    WHERE Sname LIKE 'M%';
    ```

    ![image-20200504201106788](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-121107.png)

10.  查询计算机系年龄在20岁以上的学生姓名。

    ```sql
    SELECT Sname
    FROM Student
    WHERE Sdept= 'CS' AND Sage<20;
    ```

    ![image-20200504201249189](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-121249.png)

11. 查询全体学生情况，查询结果按所在系的系号升序 排列，同一系中的学生按年龄降序排列。

    ```sql
    SELECT *
    FROM Student
    ORDER BY Sdept, Sage DESC;
    ```

    ![image-20200504201446308](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-121446.png)

12.  查询学生总人数。

    ```sql
    SELECT COUNT(*)
    FROM Student;
    ```

    ![image-20200504201545582](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-121545.png)

13. ] 查询学生17150011001选修课程的总学分数。

    ```sql
    SELECT SUM(Ccredit)
    FROM SC,Course
    WHERE Sno='201215012' AND SC.Cno=Course.Cno;
    ```

    ![image-20200504201626745](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-121627.png)

14. 求各个课程号及相应的选课人数。

    ```sql
    SELECT Cno,COUNT(Sno)
    FROM SC
    GROUP BY Cno;
    ```

    ![image-20200504201906539](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-121906.png)

15. 查询选修了3门及以上课程的学生学号。

    ```sql
    SELECT Sno
    FROM SC
    GROUP BY Sno HAVING COUNT(Cno) =>3;
    ```

    ![image-20200504202110986](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-122111.png)

16. 查询每个学生及其选修课程的情况

    ```sql
    SELECT Student.*, SC.*
    FROM Student, SC
    WHERE Student.Sno = SC.Sno;
    ```

    ![image-20200504202212661](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-122212.png)

17. ]查询每一门课的直接先修课的名称

    ```sql
    SELECT FIRST.Cname , SECOND.Cname
    FROM Course FIRST, Course SECOND
    WHERE FIRST.Cpno = SECOND.Cno;
    ```

    ![image-20200504202329923](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-122330.png)

18. 查询每个学生及其选修课程的情况

    ```sql
    SELECT Student.Sno, Sname, Cname, Grade
    FROM Student,SC,Course 
    WHERE Student.Sno = SC.Sno AND SC.Cno = Course.Cno;
    ```

    ![image-20200504202710631](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-122711.png)

19. 查询与“Marcus”在同一个系学习的学生。

    自身连接法:

    ```sql
    SELECT S1.Sno, S1.Sname,S1.Sdept
    FROM Student S1,Student S2
    WHERE S1.Sdept = S2.Sdept AND
    S2.Sname = 'Marcus';
    ```

    ![image-20200504202819348](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-122819.png)

    查询选修了课程名为“信息系统”的学生学号和姓名

    ```sql
    SELECT Student.Sno,Sname
    FROM Student,SC,Course
    WHERE Student.Sno = SC.Sno AND
    	SC.Cno = Course.Cno AND
    	Course.Cname='信息系统';
    ```

    ![image-20200504205610268](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-125610.png)

20.  查询非计算机科学系中比计算机科学系任意一个 学生年龄小的学生姓名和年龄

    ```sql
    SELECT Sname,Sage
    FROM Student
    WHERE Sage <(SELECT MAX(Sage)
              FROM Student
              WHERE Sdept= 'CS')
    	AND Sdept <> 'CS ';
    ```

    ![image-20200504212742839](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-132743.png)

21. 询所有选修了1号课程的学生姓名。

    ```sql
    SELECT Sname
    FROM Student
    WHERE EXISTS(SELECT *
           FROM SC
           WHERE Sno=Student.Sno AND Cno= ' 1 ');
    ```

    ![image-20200504212849358](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-132849.png)

22.  **查询选修了全部课程的学生姓名。**

    ```sql
    SELECT Sname
    FROM Student
    WHERE NOT EXISTS(SELECT *
               FROM Course
               WHERE NOT EXISTS(SELECT *
                                FROM SC
                                WHERE Sno= Student.Sno
                                AND Cno= Course.Cno)
    									);
    ```

    ![image-20200504213022445](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-133022.png)

    没有学生选了全部课

23.  查询选修了课程1或者选修了课程2的学生学号。

    ```sql
    SELECT Sno
    FROM SC
    WHERE Cno='1'
    
    UNION
    
    SELECT Sno
    FROM SC
    WHERE Cno= '2';
    ```

    
    
    ![image-20200504213301304](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-133301.png)
    
24. 查询计算机科学系的学生与年龄不大于19岁的学生 的交集。

    ```sql
    SELECT *
    FROM Student
    WHERE Sdept='CS'
    
    UNION ALL
    
    SELECT *
    FROM Student
    WHERE Sage<=19  
    ```

    ![image-20200504213725866](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-133726.png)

    和 Mooc 上的INTERSECT不同,mysql 用的是 UNION ALL

25. 查询计算机科学系的学生与年龄不大于19岁的学生的差集

    ````sql
    SELECT *
    FROM SC
    WHERE Sdept='CS'
    EXCEPT
    SELECT *
    FROM SC
    WHERE Sage<=19;
    ````

    ![image-20200504220358352](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-140358.png)

26. 建立CS系学生的视图。

    ```sql
    CREATE VIEW IS_Student
    AS
    SELECT Sno,Sname,Sage
    FROM Student
    WHERE Sdept= 'CS';
    ```

    ![image-20200504220524559](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-140525.png)

27.  定义一个反映学生出生年份的视图。

    ```sql
    CREATE VIEW BT_S(Sno,Sname,Sbirth)
    AS
    SELECT Sno,Sname,2020-Sage
    FROM Student;
    ```

    ![image-20200504220626046](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-140626.png)

28. 将学生的学号及平均成绩定义为一个视图

    [聚集函数]

    ```sql
    CREATE VIEW S_G(Sno,Gavg)
    AS
    SELECT Sno,AVG(Grade)
    FROM SC
    GROUP BY Sno;
    ```

    ![image-20200504220733273](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-140734.png)

29. 删除视图BT_S和IS_S1

    ```sql
    DROP VIEW BT_S;/*成功执行*/
    
    DROP VIEW IS_S1;/*拒绝执行*/
    DROP VIEW IS_S1 CASCADE;/*成功执行*/
    ```


30.  在CS系学生的视图中找出年龄小于20岁的学生。

    ```sql
    SELECT  Sno,Sage
    FROM IS_Student
    WHERE Sage<20;
    ```

    ![image-20200504220907280](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-140907.png) 我们可以看到 CS 没有学生年级小于 20.
    
31.  将信息系学生视图IS_Student中学号”17150011001”的学生姓名改为”老马”。

    ```sql
    UPDATE IS_Student
    SET Sname= '老马'
    WHERE Sno= '17150011001';
    ```

我们可以看到马卡斯的名字改成了老马

![image-20200504234022503](https://cy-1256894686.cos.ap-beijing.myqcloud.com/cy/2020-05-04-154022.png)

---

我实际上在 MYSQL 上做了不止 20 个最经典的实验,实际上还有很多经典的例子,我基本上把 MOOC 上数据库基础的所有例子都实现了一篇,正如老师所说的,数据库是一门实践性很强的课程,要多通过课下练习才能更好地掌握这门课,