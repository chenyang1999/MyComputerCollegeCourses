```c++
#include <cstdio> 
#include <iostream>
#include <cstdlib>
 typedef struct PolyNode *Polynomial;
 struct PolyNode{
		int coef;
		int expon;
		Polynomial link;
 };

 void Attach(int c,int e,Polynomial *pRear) //pRear是指针的指针 
 {
		Polynomial P;

		P=(Polynomial)malloc(sizeof(struct PolyNode));
		P->coef=c; //对新结点赋值 
		P->expon=e;
		P->link=NULL;
		(*pRear)->link=P; //修改pRear的值 
		*pRear=P;
 }
 Polynomial ReadPoly()
 {
		Polynomial P,Rear,t;
		int c,e,N;

		 scanf("%d",&N);
		 P=(Polynomial)malloc(sizeof(struct PolyNode)); //链表头空结点 
		 P->link=NULL;
		 Rear=P;
		while(N--){
				scanf("%d%d",&c,&e);
				Attach(c,e,&Rear); //将当前项插入多项式尾部 

		 }
		 t=P;P=P->link;free(t); //删除临时生成的头结点 
		 return P;
 }

 Polynomial Add(Polynomial P1,Polynomial P2)
 {
		Polynomial front,rear,temp;
		int sum;
		rear=(Polynomial)malloc(sizeof(struct PolyNode));
		front=rear; /*由front记录结果多项式链表头结点*/
		while(P1&&P2){ /*当两个多项式都有非零项待处理时*/ 
				if(P1->expon==P2->expon){
						sum=P1->coef+P2->coef;
						if(sum){
						Attach(sum,P1->expon,&rear);
						P1=P1->link;
						P2=P2->link;

						}else{
								P1=P1->link;
								P2=P2->link;

						 }
				 }else if(P1->expon>P2->expon){
						Attach(P1->coef,P1->expon,&rear);
						P1=P1->link;
				 }else{
						Attach(P2->coef,P2->expon,&rear);
						P2=P2->link;
				 }
		 }
		 /*将未处理完的另一个多项式的所有节点依次复制到结果多项式中去*/ 
		 while(P1){
				Attach(P1->coef,P1->expon,&rear);
				P1=P1->link;
		 }
		 while(P2){
				Attach(P2->coef,P2->expon,&rear); 
				P2=P2->link;
		 }
		rear->link=NULL;
		temp=front;
		front=front->link; /*令front指向结果多项式第一个非零项*/ 
		free(temp);        /*释放临时空表头结点*/ 
		return front;
	} 

/*采用逐项插入的方法，将P1当前项乘以P2的当前项，并插入到结果多项式中，关键是要找到插入的位置*/  
/*初始结果多项式由P1第一项乘以P2各项获得*/ 
Polynomial Mult(Polynomial P1,Polynomial P2)
{
		Polynomial P,Rear,t1,t2,t;
		int c,e;

		if(!P1||!P2){
				return NULL;
		}
		t1=P1;t2=P2;
		P=(Polynomial)malloc(sizeof(struct PolyNode));P->link=NULL;
		Rear=P;
		while(t2){ //P1第一项乘以P2各项得到初始结果多项式 
				Attach(t1->coef*t2->coef,t1->expon+t2->expon,&Rear);

				t2=t2->link;
		}
		t1=t1->link;
		while(t1){
				t2=P2;Rear=P;

				while(t2){
						e=t1->expon+t2->expon;
						c=t1->coef*t2->coef;
						while(Rear->link&&Rear->link->expon>e)
						Rear=Rear->link;
						if(Rear->link&&Rear->link->expon==e){
								if(Rear->link->coef+c)
								 Rear->link->coef+=c;
								else{
										t=Rear->link;
										Rear->link=t->link;
										free(t);
								}
						}
						else{
								t=(Polynomial)malloc(sizeof(struct PolyNode));
								t->coef=c;
								t->expon=e;
								t->link=Rear->link;
								Rear->link=t;
								Rear=Rear->link;
						}
						t2=t2->link;
						//printf("%d,%d\n",Rear->coef,Rear->expon);


				}
				t1=t1->link; 
		}
		t2=P;P=P->link;free(t2);
		return P;

}

 void PrintPoly(Polynomial P)
 {
		int flag=0;  //辅助调整输出格式用 
		if(!P){
				printf("0 0\n");
				 return;
		 } 
		 while(P){
				if(!flag)
				 flag=1;
				else 
					printf(" ");
					printf("%d %d",P->coef,P->expon);
					P=P->link;
		 }
 } 
 int main()
 {
		Polynomial P1,P2,PS,PP;
		P1=ReadPoly();

		P2=ReadPoly();

		PP=Mult(P1,P2);
		PrintPoly(PP);
		printf("\n");
//		PS=Add(P1,P2);
//		PrintPoly(PS); 

		return 0;
 }

/* 测试数据
4 3 5 2 4 3 3 6 1
3 2 5 3 4 4 3
*/
```



分别输入多项式 A 和多项式 B 有多少个项数n1,n2

N1 后面跟着n1对点,分别表示 X 的项数和系数

如何输出两个多项式相乘的项数和系数

```
4 3 5 2 4 3 3 6 1
3 2 5 3 4 4 3
6 10 13 9 24 8 17 7 24 6 18 5 24 4

```

