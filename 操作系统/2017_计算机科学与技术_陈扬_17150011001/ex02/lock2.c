#include<stdio.h>
#include<sys/types.h>
#include<unistd.h>
int main()
{
	int	pid, i;
//	pid=fork();
//	printf("%d",pid);
	if((pid=fork())<0)
	{   
		printf("child fail create\n");
		return 0;
	}
	else if(pid==0)
	{        lockf(1,1,0); // 锁定标准输出设备(显示器)
                for(i=0;i<5;i++)
		     printf("This is child (pid=%d) process:b\n",getpid());
		lockf(1,0,0); // 解锁标准输出设备(显示器)
		return 0;
	}
    else
    {  lockf(1,1,0);
       for(i=0;i<5;i++)
           printf("Parent process:a\n");
       lockf(1,0,0);
    }
	return 0;
}
