# include<stdio.h>
# include<sys/types.h>
# include<unistd.h>

int main()
{
	int pid1,pid2;
	printf("I am father!\n");
	
	if ((pid1 = fork())<0)
	{
		printf("Child1 fail create!\n");
		return 1;
	}
	else if (pid1 == 0)
	{
	for (int i=1;i<=11500;i++)printf("");
		printf("I am son!\n");
		return 1;
	}
	if ((pid2 = fork())<0)
	{
		printf("Child2 fail create!\n");
		return 1;
	}
	else if (pid2 == 0)
	{	
		for (int i=1;i<=10000;i++)printf("");
		printf("I am daughter!\n");
		return 1;
	}
	for (int i=1;i<=100000;i++)printf("");
	return 0;
}
