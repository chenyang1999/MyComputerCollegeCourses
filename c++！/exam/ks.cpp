#include <iostream>
#include <cstdio>
#include <string>
#include <cstring>
#include <map>
//#include <bits/stdc++.h>
using namespace std;

int cp=0;//产品数量
int ac=1;
map<int,int >ys;//id 映射
map<int,int >fys;//id 反映射
map<int,string >date;//先对时间转换为绝对时间
map<string,int >fdate;//绝对时间转换为相对时间
class customer{
	public:
		void add(){
			int m, n, i;
			cout<<"Please input the account number:"<<endl;
			cin>>n;
			for(i = 0; code[i+1] != 0; i++) {
				if(n == code[i]){
					cout<<"The account has existed!"<<endl;
				    break;
				}
				else{
					cout<<"Please input the money:"<<endl;
					cin>>m;
					cout<<"Success!"<<endl;
					for(i = 0; code[i+1] != 0; i++){
						if(code[i] == 0){						
							code[i] = n;
							money[i] = m;
							break;
						}
					}
				}
		}
		}
		//加入一个产品
		void add(int id,float m){
			
			code[bj]=id;
			money[bj]=m;
			bj++;
		}
		void del(){
			int n, i, j;
			cout<<"Please input the number of account:"<<endl;
			cin>>n;
			 for(i = 0; code[i] != 0; i++) {
				if(n == code[i]){
					cout<<"The account has been deleted!"<<endl;
					for(j = i; code[j+1] != 0;j++)
					   { code[j] = code[j+1];
					  money[j] = money [j+1];}
				    break;
				}
				else 
				cout<<"Please input the right number!"<<endl;
			}
		}
		//在用户class中删除product并且返回投资金额
		float del(int id){
			for(int i=0;i<bj;i++)
			if(id==code[i]){
				code[i]=-1;
				float t=money[i];
				money[i]=0;
				return t;
			}
			return 0;
			}
		void dis(){
			cout<<"id="<<id<<endl;
			cout<<"name="<<name<<endl;
			cout<<"address="<<address<<endl;
			cout<<"emplyee="<<employee<<endl;
			cout<<"rank="<<rank<<endl;
			cout<<"idcard="<<card<<endl;
			cout<<"phone="<<phone<<endl;
			display();
		}
		void display(){
			int i;
			cout<<"The code and money is:"<<endl;
			for(i = 0; code[i] <bj; i++){
				if(money[i]!=0)
				cout<<code[i]<<"  "<<money[i]<<endl;
			}
		}
	void edit(int id,string name,string address,string employee,char rank,long long card,long long phone){
		this->id=id;
		this->name=name;
		this->address=address;
		this->employee=employee;
		this->rank=rank;
		this->card=card;
		this->phone=phone;
	}
	int get_id(){
		return id;
	}
	string get_name(){
		return  name;
	}
	char get_rank(){
		return rank;
	}
	int get_bj(){
		return bj;
	}
	int code[100];
	double money[100];
	private:
		//string account;
		int id;
		int bj;
		string name;
		string address;
		string employee;
		char rank;

		long long card;
		long long phone;
};
class product {
	public:
	product (){}
	product(int id,char fk,int mb,int me,int cb,int ce,float profit ){
		this->id=id;
		this->fk=fk;
		this->mb=mb;
		this->me=me;
		this->cb=cb;
		this->ce=ce;
		this->profit=profit;
	}
	void edit(int id,char fk,int mb,int me,int cb,int ce,float profit ){
			this->id=id;
			this->fk=fk;
			this->mb=mb;
			this->me=me;
			this->cb=cb;
			this->ce=ce;
			this->profit=profit;
		}
	int get_id(){
		return id;
	}
	void display(){
		cout<<"产品代码: "<<id<<endl;
		cout<<"风控等级: "<<fk<<endl;
		cout<<"募集启始时间，募集结束时间: "<<mb<<",   "<<me<<endl;
		cout<<"储蓄启始时间，储蓄结束时间: "<<cb<<",   "<<ce<<endl;
		cout<<"年化利润: "<<profit<<endl;

	}
	char get_fk(){
		return fk;
	}
	int get_mb(){
		return mb;
	}
	int get_me(){
			return me;
		}
	int get_cb(){
		return cb;
	}
	int get_ce(){
		return ce;
	}
	float get_profit(){
		return profit;
	}
	private:
		int id;//产品代码
		char fk;//风控等级
		int mb,me;//募集启始时间，募集结束时间
		int cb,ce;//储蓄启始时间，储蓄结束时间
		float profit;//利润
		
};
class Bank{
	public:
	int day;
	void dis(){
		cout<<"当前日期： "<<date[day]<<endl;
		cout<<"待还用户本金： "<<hb<<endl;
		cout<<"待换用户利息： "<<hl<<endl;
		cout<<"已收用户本金： "<<sb<<endl;
	}
	void edit_hb(float x){
		hb+=x;
	}
	void edit_hl(float x){
			hl+=x;
		}
	void edit_sb(float x){
			sb+=x;
		}
	private: float hb,hl,sb;//还本，还利，收本	
	};
Bank bank[1000];
product  p[100000];
customer c[10000];
void creat_customer (){
	int id;
	string name;
	string address;
	string employee;
	char rank;
	//int code[100];
	//double money[100];
	long long card;
	long long phone;
	cout<<"输入id，姓名，地址，工作，风控等级，身份证号码，电话号码： "<<endl;
	cin>>id>>name>>address>>employee >>rank>>card>>phone;
	if(c[id].get_id()==id){
		cout<<"this accounts has exist, you can't creat it"<<endl;
		return ;
	}
	c[id].edit(id,name,address,employee,rank,card,phone);
}
void creat_profit(){
		int id;//产品代码
		char fk;//风控等级
		int mb,me;//募集启始时间，募集结束时间
		int cb,ce;//储蓄启始时间，储蓄结束时间
		float profit;//利润
		cout<<"请输入产品代码,风控等级，募集启始时间，募集结束时间，储蓄启始时间，储蓄结束时间，年化利润："<<endl;
		cin>>id>>fk>>mb>>me>>cb>>ce>>profit;
		if(p[ys[id]].get_profit()!=0){
			cout<<"this product has exist, you can't creat it!"<<endl;
			return ;
		}
		ys[id]=cp;//id映射；
		fys[cp]=id;
		cp++;
	//	p[ys[id]].edit(id,fk,mb,me,cb,ce,profit);
		p[ys[id]].edit(id,fk,mb,me,cb,ce,profit);
		
		//product 
	}
void buy(int day){
	cout<<"购买id，产品id，购买金额"<<endl;
	int bid,pid;
	float m;
	cin>>bid>>pid>>m;
	pid=ys[pid];
//cout<<c[bid].get_rank()<<p[pid].get_fk()<<endl;
	if(c[bid].get_rank()<p[pid].get_fk()){
		cout<<"rank:can't buy it "<<endl;
		return ;
	}
	if(day<p[pid].get_mb()||day>p[pid].get_me()){
		cout<<"day:can't buy it "<<endl;
			return ;
		}
	c[bid].add(pid,m);
	for(int i=day;i<=p[pid].get_ce();i++)
	bank[i].edit_sb(m);
	for(int i=p[pid].get_ce()-3;i<=p[pid].get_ce();i++){
	bank[i].edit_hb(m);
	bank[i].edit_hl(m*p[pid].get_profit()*(p[pid].get_cb()-p[pid].get_ce()+1)/365);}
}

int day=1;
int convert(int year,int mouth,int day)
{
	int date=0,i,j=0;
	int a[12];
	int b[12]={31,29,31,30,31,30,31,31,30,31,30,31};
	int c[12]={31,28,31,30,31,30,31,31,30,31,30,31};

	if(((year%4==0)&&(year%100!=0))||(year%400==0))
	{
		for(j=0;j<12;j++)
		a[j]=b[j];
	}
	else
	{
		for(j=0;j<12;j++)
		a[j]=c[j];
	}
	j=0;
	date=(year-2018)*365;
	for(i=0;i<mouth-1;i++)
	{
		j=j+a[i];
	}
	date=date+j+day;
	return date;
}
void del(int bid,int pid,int day){
	pid=ys[pid];
	float m;
	for(int i=0;i<c[bid].get_bj();i++)if(pid==c[bid].code[i]){
		m=-c[bid].money[i];break;
	}
	if(m==0){cout<<"you haven't buy it !"<<endl;
	return ;}
	for(int i=day;i<=p[pid].get_ce();i++)
		bank[i].edit_sb(m);
		for(int i=p[pid].get_ce()-3;i<=p[pid].get_ce();i++){
		bank[i].edit_hb(m);
		bank[i].edit_hl(m*p[pid].get_profit()*(p[pid].get_cb()-p[pid].get_ce()+1)/365);}

}

int main(int argc, char *argv[]) {
	int d=1;
	int b[12]={31,29,31,30,31,30,31,31,30,31,30,31};
	for(int i=2018;i<=2020;i++)
	for (int j=1;j<=12;j++) {
		for(int k=1;k<=b[j];k++){
			date[convert(i,j,k)]=to_string(i)+":"+to_string(j)+":"+to_string(k);
			fdate[to_string(i)+":"+to_string(j)+":"+to_string(k)]=convert(i,j,k);
		}
	}
	
	for(int i=0;i<1000;i++)bank[i].day=i;
//product  p(1,'A',1,1,1,1,1);
//xxp.display();	
//c[1].edit(1, "cy","ls","xs", 'D', 440, 156);
//p[1].edit(1, 'A', 1, 2, 3, 5, 0.1);
//1 cy ls stu A 440 156
//1 A 1 2 3 5 0.2
	cout<<"welcome to use bank administrate system"<<endl;
	while(1){
		char st;
		cout<<"++++++++++++++++++++++++++++++++++"<<endl;
		cout<<"input A:creat a account"<<endl;
		cout<<"input B:buy a profot"<<endl;
		cout<<"input S:delete a profit "<<endl;
		cout<<"input C:creat a product"<<endl;
		cout<<"input D:display bank's information "<<endl;
		cout<<"input N:enter next day"<<endl;
		cin>>st;
		if(st=='A'){
			creat_customer();	
			ac++;
					}
		if(st=='B'){
			buy(day);
		}
		if(st=='C'){
			creat_profit();
		}
		if(st=='D'){
			cout<<"input B :display bank's information"<<endl;
			cout<<"input P :display product's information"<<endl;
			cout<<"input A :display accounts's information"<<endl;
			cin>>st;
			if(st=='B')bank[day].dis();
			if(st=='P')for(int i=0;i<cp;i++)p[i].display();
			if(st=='A')for(int i=1;i<ac;i++)c[i].dis();
		//	cout<<"++++++++++++++++++++++++++++++++++"<<endl;
			}
		if(st=='N'){
			day++;
		}
		if(st=='S'){
			cout<<"输入用户id，产品id";
			int bid,pid;
			cin>>bid>>pid;
			del(bid,pid,day);
		}
	}
	return 0;	
}