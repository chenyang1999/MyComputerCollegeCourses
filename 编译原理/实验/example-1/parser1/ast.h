/* 定义抽象语法树（分析树）的数据结构 */

#include "util.h"

typedef int a_pos; //a_pos为源文件某记号的位置类型，报错用。

typedef struct a_exp_ * a_exp;
typedef struct a_bexp_ * a_bexp;
typedef struct a_id_ * a_id;
typedef struct a_stm_ * a_stm;
typedef struct a_stm_list_ * a_stm_list;
typedef struct a_dec_ * a_dec;
typedef struct a_dec_list_ * a_dec_list;
typedef struct a_var_list_ * a_var_list;
typedef struct a_prog_ * a_prog;

/* 变量类型有整型T_int和实型T_real两种 */
typedef enum{T_int, T_real} ttype;

/* 二元算术运算有四种：+ - * / */
typedef enum {A_plusOp, A_minusOp, A_timesOp, A_divideOp} a_op;

/* 二元布尔运算六种：=, <>, <, <=, >, >= */
typedef enum {A_eqOp, A_neqOp, A_ltOp, A_leOp, A_gtOp, A_geOp} a_bop;


/* id的数据结构，pos为位置，val为id的名字 */
struct a_id_ {
  a_pos pos;
  string val;
};

/* 算术表达式数据结构。
 * kind为表达式的类型，分为变量、整型数、实型数和二元表达式
 */
struct a_exp_ {
  enum {A_varExp, A_intExp, A_realExp, A_opExp} kind;
  a_pos pos;
  union {
    struct {
      a_op op;
      a_exp left;
      a_exp right;
    }biopExp; //二元运算表达式：left op right
    a_id var; //变量表达式
    int ival; //整型数表达式
    double fval;//实型数表达式
  }exp;
};

/* 布尔表达式数据结构 */
struct a_bexp_{
	a_pos pos;
	struct {
    a_bop bop;
    a_exp left;
    a_exp right;
  } bexp;
};

/* 语句数据结构。
 * 类型分为赋值语句、if语句、while语句、顺序语句（语句序列） 
 */
struct a_stm_ {
  enum {A_assign, A_if, A_while, A_seq} kind;
  a_pos pos;
  union {
    struct a_assign_stm_ {
      a_id var;
      a_exp exp;
    } assign;//赋值语句：var = exp;
    struct a_if_stm_ {
      a_bexp b;
      a_stm s1;
      a_stm s2;
    } iff;// if语句：if b then s1 else s2;
    struct a_while_stm_ {
      a_bexp b;
      a_stm s;
    } whilee;// while语句：while b do s;
    a_stm_list seq;//顺序语句：是若干顺序的语句的一个列表。
  } stm;
};

/* 语句列表数据结构 */
struct a_stm_list_ {
  a_stm head;
  a_stm_list tail;
};

/* 变量列表数据结构 */
struct a_var_list_ {
  a_id head;
  a_var_list tail;
};

/* 变量声明数据结构 。
 * 变量类型为type，变量列表为varlist。
 * 例：声明var a, b, c : INTEGER;
 *     其中a,b,c组成varlist，type为T_int（表示INTEGER）。
 */
struct a_dec_ {
  ttype type;
  a_pos pos;
  a_var_list varlist;
};

/* 变量声明列表数据结构 */
struct a_dec_list_ {
  a_dec head;
  a_dec_list tail;
};

/* 程序数据结构。
 * name为程序名，declist为程序变量声明列表 */
struct a_prog_ {
  char * name;
  a_pos pos;
  a_dec_list declist;
  a_stm_list stmlist;
};

/* 下面的函数是各个语法结构的构造函数 */
a_id A_Id(a_pos pos, string val);
a_exp A_IntExp(a_pos pos, int ival);
a_exp A_RealExp(a_pos pos, double fval);
a_exp A_VarExp(a_pos pos, a_id var);
a_exp A_OpExp(a_pos pos, a_op op, a_exp left, a_exp right);
a_bexp A_BExp(a_pos pos, a_bop bop, a_exp left, a_exp right);
a_stm A_Assign (a_pos pos, a_id var, a_exp exp);
a_stm A_If(a_pos pos, a_bexp b, a_stm s1, a_stm s2);
a_stm A_While(a_pos pos, a_bexp b, a_stm s);
a_stm A_Seq(a_pos pos, a_stm_list sl);
a_stm_list A_StmList(a_stm s, a_stm_list sl);
a_var_list A_VarList(a_id v, a_var_list vl);
a_dec A_VarDec(a_pos pos, a_var_list vl, ttype t);
a_dec_list A_DecList(a_dec vd, a_dec_list vdl);
a_prog A_Prog (a_pos pos, char * name, a_dec_list dl, a_stm_list sl);

