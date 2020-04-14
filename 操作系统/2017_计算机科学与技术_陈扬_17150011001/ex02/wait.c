#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include <stdio.h>
/*
WEXITSTATUS(stat_val) is a macro (so in fact it does not "return" something, but "evaluates" to something).

For how it works you might like to look it up in the headers (which should be #included via <sys/wait.h>) that come with the C-compiler you use.

The implementation of this macro might differ from one C-implementation to the other.

Please note, that this macro only gives a sane value, if the macro WIFEXITED(stat_val) gave you a value unequal to 0.

Verbatim from waitpid()'s POSIX specification:

	WEXITSTATUS(stat_val)

	If the value of WIFEXITED(stat_val) is non-zero, this macro evaluates to the low-order 8 bits of the status argument that the child process passed to _exit() or exit(), or the value the child process returned from main().
	The motivation behind adding up the return code(s?) of a particular program is only known to the code's author and the hopefully existing documentation.
*/
int main()
{
	pid_t pid;
	int status,i;
	if(fork()==0){
		printf("This is the child process .pid =%d\n",getpid());
		exit(5);
	}
	else{
		sleep(1);
		printf("This is the parent process ,wait for child...\n");
		pid=wait(&status);
		i=WEXITSTATUS(status);
		printf("child’s pid =%d .exit status=%d\n",pid,i);
	}
	return 0;	
}
