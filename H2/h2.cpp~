#include<iostream>
#include<vector>
#include<math.h>
#include<stack>
#include<iomanip>
using namespace std;
typedef struct{
	long long x,y,pop;
} island;
typedef struct{
	double maxWeight;
	long long num;
}node;
void kruskal(double** w, long long n, vector<island>& islands,long long groupno)
{
	long long ne=0,u,v,a,b;
	double min;
	long long * p = new long long[n+1];
	double ** spanTree = new double* [n+1];
	for(long long i=1; i<=n; i++)
		spanTree[i] = new double[n+1];
	for(long long i = 1; i<=n; i++)
	for(long long j = 1; j<=n; j++)
	{
		spanTree[i][j]=99999999999999;
	}
	for(long long i = 1; i<=n; ++i)
		p[i] = 0;
	while(ne<n-1)
	{
		min = 99999999999999;//1.0/0.0;//
		for(long long i = 1; i <= n; i++)
		for(long long j = 1; j <= n; j++)
		{
			if(w[i][j] < min)
			{
				min = w[i][j];
				u=a=i;
				v=b=j;
			}
		}
		while(p[u])
			u = p[u];
		while(p[v])
			v = p[v];
		if(u!=v)
		{
			ne++;
			//sum += min;
			p[v] = u;
			spanTree[a][b] = spanTree[b][a] = min;
		}
		w[a][b] = w[b][a] = 99999999999999;//1.0/0.0;//
		//cout<<w[a][b]<<endl;
	}
	
	stack<node> myStack;
	node root;
	root.maxWeight = 0;
	root.num = 1;
	myStack.push(root);
	double averageTime = 0;
	bool * visited = new bool[n+1]; 
	for(int ii = 1; ii<= n; ii++)
		visited[ii] = false;
	while(!myStack.empty())
	{
		//cout<<myStack.size()<<endl;	

		node thisone = myStack.top();
		myStack.pop();
		averageTime += thisone.maxWeight * islands[thisone.num].pop;
		visited[thisone.num] = true;
		for(long long i = 1; i <= n; i++)
		{
			if(spanTree[thisone.num][i]<99999999999999 && visited[i]==false)
			{
				node temp;
				temp.num = i;
				if(spanTree[thisone.num][i] > thisone.maxWeight)
				{
					temp.maxWeight = spanTree[thisone.num][i];	
				} 
				else
				{
					temp.maxWeight = thisone.maxWeight;
				}
				myStack.push(temp);
			}
		}
	}
	long long pop = 0;
	for(long long i = 1; i <=n ; i++)
	{
		pop += islands[i].pop;
	}
	//cout<<pop<<endl;
	averageTime = averageTime /(double) pop;
	cout<<"Island Group: "<<groupno<< " Average "<<setprecision(2)<<fixed<< averageTime <<"\n"<<endl;
	
	for(long long ii = 1; ii <=n; ii++)
	{
		delete[] spanTree[ii];
	}
	delete[] spanTree;
	delete[] p;
	delete[] visited;
}
int main(int argc, char** argv)
{
	long long n;
	long long groupno=0;
	while(cin>>n)
	{	
		groupno++;
		if(n>0)
		{
			//save the array
			vector<island> group;
			for(long long i = 0; i <= n; ++i)
			{
				island temp;
			
				if(i>0)
				{
					cin >> temp.x >> temp.y >> temp.pop;
				}
				else
				{
					temp.x = 0;
					temp.y = 0;
					temp.pop = 0;
				}
				group.push_back(temp);
				
			}			
			double ** w = new double *[n+1];
			for(long long i = 1; i <= n; i++)
			{
				w[i] = new double[n+1];	
			}	
			for(long long i = 1; i <= n; i++)
			{
				for(long long j = 1; j <= n; j++)
				{
					if(i == j)
						w[i][j] = 99999999999999;//1.0/0.0;//
					else if(i<j)
					{
						w[i][j] = sqrt((group[i].x-group[j].x)*(group[i].x-group[j].x)+(group[i].y-group[j].y)*(group[i].y-group[j].y));
					}
					else
					{
						w[i][j] = w[j][i];
					
					}
				}
			}
			kruskal(w,n,group,groupno);
			for(long long ii = 1; ii <= n; ii++)
			{
				delete[] w[ii];
			}
			delete[] w;
		}
		else
		{
			break;
		}
	}
	return 0;
}
