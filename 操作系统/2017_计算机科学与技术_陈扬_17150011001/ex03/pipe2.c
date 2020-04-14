#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
int main( void )
{
	int filedes[2];
	char buf[80];
	pid_t pid;
	
	pipe( filedes );
	
	if ( (pid=fork()) > 0 )
	{
		printf( "This is in the father process,here write a string to the pipe.\n" );
		char s[] = "Hello world , this is write by pipe.\n";
		write( filedes[1], s, sizeof(s) );
//		close( filedes[0] );
//		close( filedes[1] );
	}
	else
	{
		printf( "This is in the child process,here read a string from the pipe.\n" );
		read( filedes[0], buf, sizeof(buf) );
		printf( "%s\n", buf );
//		close( filedes[0] );
//		close( filedes[1] );
	}
	
	waitpid( pid, NULL, 0 );    
	return 0;
}
