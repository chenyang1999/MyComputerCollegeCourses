# include <stdio.h>
# include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
int main()
	{
	int i,j,fd[2];
	char S[100];
	pipe(fd);
	if ((i=fork())==0)
	{
		sprintf(S,"Child Process 1 is sending a message!\n");
		write(fd[1],S,50);
		sleep(3);
		return 0;
	}
	if ((j=fork())==0)
	{
		sprintf(S,"Child Process 2 is sending a message!\n");
		write(fd[1],S,50);
		sleep(2);
		return 0;
	}
	else{
		wait(0);
		read(fd[0],S,50);
		printf("%s",S);
		read(fd[0],S,50);
		printf("%s",S);
		return 0;
	}
	return 0;
}
