#include <iostream>

using namespace std;

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

int main()
{
	int year,mouth,day,date=0;
	cin >> year >> mouth >> day;
	date=convert(year,mouth,day);
	cout << date;

	return 0;
}