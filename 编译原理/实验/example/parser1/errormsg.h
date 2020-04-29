extern bool EM_anyErrors;

void EM_newline(void);

extern int EM_tokPos;

void EM_error(int, string,...);

void EM_reset(string filename, string output);

void EM_yydebug();

