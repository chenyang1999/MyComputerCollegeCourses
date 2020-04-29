/* 本文件定义各个数据结构的打印操作 */

#include <stdio.h>
#include "ast.h"
#include "prast.h" /* function prototype */


static void indent(FILE *out, int d);
static void pr_id(FILE *out, a_id id, int d);
static void pr_op(FILE *out, a_op d);
static void pr_bop(FILE *out, a_bop d);
static void pr_ty(FILE *out, ttype d);
static void pr_exp(FILE *out, a_exp v, int d);
static void pr_bexp(FILE * out, a_bexp v, int d);
static void pr_stm(FILE *out, a_stm v, int d); 
static void pr_stmList(FILE *out, a_stm_list v, int d);
static void pr_varList(FILE *out, a_var_list v, int d);
static void pr_decList(FILE *out, a_dec_list v, int d);
static void pr_dec(FILE *out, a_dec v, int d);

/* 打印长度为d的空格 */
static void indent(FILE *out, int d) {
 int i;
 for (i = 0; i <= d; i++) fprintf(out, " ");
}

/* 打印id */
static void pr_id(FILE *out, a_id id, int d) {
	indent(out, d);
	fprintf(out, "VAR(%s)", id->val); 
}


static char str_op[][10] = {
   "PLUS", "MINUS", "TIMES", "DIVIDE" 
};

static char str_bop[][10] = {
   "EQUAL", "NOTEQUAL", "LESSTHAN", "LESSEQ", "GREATTHAN", "GREATEQ"
};

static char str_ty[][10] = {
   "INTEGER", "REAL"
};

/* 打印二元算术运算操作符 */ 
static void pr_op(FILE *out, a_op d) {
  fprintf(out, "%s", str_op[d]);
}

/* 打印二元布尔运算操作符 */
static void pr_bop(FILE *out, a_bop d){
	fprintf(out, "%s", str_bop[d]);
}

/* 打印类型 */
static void pr_ty(FILE *out, ttype d){
	fprintf(out, "%s", str_ty[d]);
}

/* 打印算术表达式 */
static void pr_exp(FILE *out, a_exp v, int d) {
 indent(out, d);
 switch (v->kind) {
 case A_varExp:
   fprintf(out, "varExp(\n"); pr_id(out, v->exp.var, d+1); 
   fprintf(out, "%s", ")");
   break;
 case A_intExp:
   fprintf(out, "intExp(%d)", v->exp.ival);
   break;
 case A_realExp:
   fprintf(out, "realExp(%f)", v->exp.fval);
   break;
 case A_opExp:
   fprintf(out, "opExp(\n");
   indent(out, d+1); pr_op(out, v->exp.biopExp.op); fprintf(out, ",\n"); 
   pr_exp(out, v->exp.biopExp.left, d+1); fprintf(out, ",\n"); 
   pr_exp(out, v->exp.biopExp.right, d+1); fprintf(out, ")");
   break;
  default:
   assert(0); 
	}
}	

/* 打印布尔表达式 */
static void pr_bexp(FILE * out, a_bexp v, int d){
	indent(out, d);
	fprintf(out, "BExp(\n");
  indent(out, d+1); pr_bop(out, v->bexp.bop); fprintf(out, ",\n"); 
  pr_exp(out, v->bexp.left, d+1); fprintf(out, ",\n"); 
  pr_exp(out, v->bexp.right, d+1); fprintf(out, ")");
} 	 	 	

/* 打印语句 */
static void pr_stm(FILE *out, a_stm v, int d) {
	indent(out, d);
 	switch (v->kind) {
 	case A_assign:
  	fprintf(out, "assignStm(\n");
   	pr_id(out, v->stm.assign.var, d+1); fprintf(out, ",\n");
   	pr_exp(out, v->stm.assign.exp, d+1); fprintf(out, ")");
   	break;
 	case A_if:
   	fprintf(out, "ifStm(\n");
   	pr_bexp(out, v->stm.iff.b, d+1); fprintf(out, ",\n");
   	pr_stm(out, v->stm.iff.s1, d+1);
   	if (v->stm.iff.s2) { /* else is optional */
      fprintf(out, ",\n");
      pr_stm(out, v->stm.iff.s2, d+1);
   	}
   	fprintf(out, ")");
   	break;
 	case A_while:
   	fprintf(out, "whileStm(\n");
   	pr_bexp(out, v->stm.whilee.b, d+1); fprintf(out, ",\n");
   	pr_stm(out, v->stm.whilee.s, d+1); fprintf(out, ")\n");
   	break;
 	case A_seq:
 		fprintf(out, "seqStm(\n");
   	pr_stmList(out, v->stm.seq, d+1); fprintf(out, ")");
 		break;
 	default:
   	assert(0); 
	}
}

/* 打印语句列表 */
static void pr_stmList(FILE *out, a_stm_list v, int d) {
	indent(out, d);
 	if (v) {
   	fprintf(out, "stmList(\n"); 
   	pr_stm(out, v->head, d+1); fprintf(out, ",\n");
   	pr_stmList(out, v->tail, d+1);
   	fprintf(out, ")");
 	} else fprintf(out, "stmList()"); 
}

/* 打印变量列表 */
static void pr_varList(FILE *out, a_var_list v, int d) {
 	indent(out, d);
 	if (v) {
   	fprintf(out, "varList(\n"); 
   	pr_id(out, v->head, d+1); fprintf(out, ",\n");
   	pr_varList(out, v->tail, d+1);
   	fprintf(out, ")");
 	} else fprintf(out, "varList()"); 

}

/* 打印变量声明列表 */
static void pr_decList(FILE *out, a_dec_list v, int d) {
 	indent(out, d);
 	if (v) {
   	fprintf(out, "decList(\n"); 
   	pr_dec(out, v->head, d+1); fprintf(out, ",\n");
   	pr_decList(out, v->tail, d+1);
   	fprintf(out, ")");
 	} else fprintf(out, "decList()"); 

}

/* 打印变量声明 */
static void pr_dec(FILE *out, a_dec v, int d) {
 	indent(out, d);
  fprintf(out, "varDec(\n");
  indent(out, d+1);
  fprintf(out, "TType(");
  pr_ty(out, v->type); 
  fprintf(out, ")"); fprintf(out, ",\n");
  pr_varList(out, v->varlist, d+1);
  fprintf(out, ")");
}


/* 打印程序数据结构 */
void pr_prog(FILE *out, a_prog v){
	if(v){
		fprintf(out, "Prog(\n");
		indent(out, 1); fprintf(out, "ProgName(%s)\n", v->name);
		pr_decList(out, v->declist, 1);
		fprintf(out, ",\n");
		pr_stmList(out, v->stmlist, 1);
		fprintf(out, ")");
	} else fprintf(out, "Prog()");
}
