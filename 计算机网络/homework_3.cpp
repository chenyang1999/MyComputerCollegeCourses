//Problem Description
//省政府“畅通工程”的目标是使全省任何两个村庄间都可以实现公路交通（但不一定有直接的公路相连，只要能间接通过公路可达即可）。经过调查评估，得到的统计表中列出了有可能建设公路的若干条道路的成本。现请你编写程序，计算出全省畅通需要的最低成本。
//Input
//测试输入包含若干测试用例。每个测试用例的第1行给出评估的道路条数 N、村庄数目M ( < 100 )；随后的 N
//行对应村庄间道路的成本，每行给出一对正整数，分别是两个村庄的编号，以及此两村庄间道路的成本（也是正整数）。为简单起见，村庄从1到M编号。当N为0时，全部输入结束，相应的结果不要输出。
//Output
//对每个测试用例，在1行里输出全省畅通需要的最低成本。若统计数据不足以保证畅通，则输出“?”。
//Sample Input
//3 3
//1 2 1
//1 3 2
//2 3 4
//1 3
//2 3 2
//0 100
//Sample Output
//3
#include <cstdio>
#include <iostream>
#include <algorithm>
using namespace std;
#define INF 0x3f3f3f3f
#define maxn 1111
struct node
{
	int u,v,w;
}edge[maxn];
int n,m,fa[maxn];
int cmp(node a,node b)
{
	if(a.w!=b.w)return a.w<b.w;
	if(a.u!=b.u)return a.u<b.u;
	return a.v<b.v;
}
int find(int x)
{
	if(fa[x]==x) return x;
	return fa[x]=find(fa[x]);
}
int kruskal(int n,int m)
{
	for(int i=1;i<=n;i++)fa[i]=i;
	int ans=0,cnt=0;
	sort(edge,edge+m,cmp);
	for(int k=0;k<m;k++)
	{
		int x=find(edge[k].u),y=find(edge[k].v);
		if(x!=y)
		{
			cnt++;
			fa[x]=y;
			ans+=edge[k].w;
			if(cnt==n-1)return ans;
		}
	}
	return -1;
}
int main()
{
	cin>>m>>n;
	while (m!=0) 
	{
		for(int i=0;i<m;i++)scanf("%d%d%d",&edge[i].u,&edge[i].v,&edge[i].w);
		int ans=kruskal(n,m);
		if(ans==-1)cout<<"?"<<endl;
		else cout<<ans<<endl;
		cin>>m>>n;
	}
	return 0;
}