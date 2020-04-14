# include<stdio.h>
# include<sys/types.h>
# include<unistd.h>

int main()
{
	int pid1,pid2;
	printf("I am grandfather!PID1=%d,PID2=%d\n",pid1,pid2);
	if ((pid1 = fork())<0)
	{	
		printf("father fail create!PID1=%d,PID2=%d\n",pid1,pid2);
		return 0;
	}
	else if (pid1 == 0)
		{	
			
			if ((pid2 = fork())<0)
			{
				printf("son fail create!PID1=%d,PID2=%d\n",pid1,pid2);
				return 0;
			}
			else if (pid2 == 0)
				{
					printf("I am grandson!PID1=%d,PID2=%d\n",pid1,pid2);
					
					return 0;
				}
				else
				{
					printf("I am father!PID1=%d,PID2=%d\n",pid1,pid2);
					return 0;
				}
		}
	for (int i=1;i<=10000;i++)printf("");
//		printf("nothing to do:%d\n",i);

	return 0;
}