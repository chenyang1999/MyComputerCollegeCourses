/* 本文件给出抽象语法树上各数据结构的构造函数，后续实验需要修改该文件的一些函数 */

#include "ast.h"




/* make id node */
a_id A_Id(a_pos pos, string val){
  a_id ret = checked_malloc(sizeof(*ret));
  ret->pos = pos;
  ret->val = val;
  return ret;
}


/* make exp node from integer */
a_exp A_IntExp(a_pos pos, int ival){
  a_exp ret = checked_malloc(sizeof(*ret));
  ret->kind = A_intExp;
  ret->pos = pos;
  ret->exp.ival = ival;
  return ret;
}

/* make exp node from real */
a_exp A_RealExp(a_pos pos, double fval){
  a_exp ret = checked_malloc(sizeof(*ret));
  //请完善该函数
  return ret;
}

/* make exp node from id node */
a_exp A_VarExp(a_pos pos, a_id var){
  a_exp ret = checked_malloc(sizeof(*ret));
  //请完善该函数
  return ret;
}

/* make binary operands exp node */
a_exp A_OpExp(a_pos pos, a_op op, a_exp left, a_exp right){
  a_exp ret = checked_malloc(sizeof(*ret));
  //请完善该函数
  return ret;
}

/* make boolean exp node */
a_bexp A_BExp(a_pos pos, a_bop bop, a_exp left, a_exp right){
  a_bexp ret = checked_malloc(sizeof(*ret));
  //请完善该函数
  return ret;
}

/* make assign statement */
a_stm A_Assign (a_pos pos, a_id var, a_exp exp){
  a_stm ret = checked_malloc(sizeof(*ret));
  ret->kind = A_assign;
  ret->pos = pos;
  ret->stm.assign.var = var;
  ret->stm.assign.exp = exp;
  return ret;
}

/* make if statement */
a_stm A_If(a_pos pos, a_bexp b, a_stm s1, a_stm s2){
  a_stm ret = checked_malloc(sizeof(*ret));
  //请完善该函数
  return ret;
}

/* make while statement */
a_stm A_While(a_pos pos, a_bexp b, a_stm s){
  a_stm ret = checked_malloc(sizeof(*ret));
  //请完善该函数
  return ret;
}

/* make sequence statement */
a_stm A_Seq(a_pos pos, a_stm_list sl){
  a_stm ret = checked_malloc(sizeof(*ret));
  //请完善该函数
  return ret; 
}

/* make statement list */
a_stm_list A_StmList(a_stm s, a_stm_list sl){
  a_stm_list ret = checked_malloc(sizeof(*ret));
  ret->head = s;
  ret->tail = sl;
  return ret;
}

/* make var list */
a_var_list A_VarList(a_id v, a_var_list vl){
  a_var_list ret = checked_malloc(sizeof(*ret));
  ret->head = v;
  ret->tail = vl;
  return ret;
}

/* make variable declaration node */
a_dec A_VarDec(a_pos pos, a_var_list vl, ttype t){
  a_dec ret = checked_malloc(sizeof(*ret));
  ret->type = t;
  ret->pos = pos;
  ret->varlist = vl;
  return ret;
}

/* make variable declaration list */
a_dec_list A_DecList(a_dec vd, a_dec_list vdl){
  a_dec_list ret = checked_malloc(sizeof(*ret));
  ret->head = vd;
  ret->tail = vdl;
  return ret;
}

/* make program node */
a_prog A_Prog (a_pos pos, char * name, a_dec_list dl, a_stm_list sl){
  a_prog ret = checked_malloc(sizeof(*ret));
  ret->name = name;
  ret->pos =pos;
  ret->declist = dl;
  ret->stmlist = sl;
  return ret;
}
