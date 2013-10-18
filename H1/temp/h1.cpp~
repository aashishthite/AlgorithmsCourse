#include<iostream>
#include<cstdio>
#include<algorithm>
using namespace std;
#define M 500005

unsigned long long w;
int num[M],snum[M];
void Merge(int low,int mid,int high);
void MergeSort(int low,int high);

int main()
{
    int n,i;
    //freopen("in.txt","r",stdin);

    while(scanf("%d",&n)&&n)
    {
        for(i=0;i<n;i++)
            scanf("%d",&num[i]);
        w=0;
        MergeSort(0,n-1);

        printf("%llu\n",w);
    }
    return 0;
}

void MergeSort(int low,int high)
{
    if(low<high)
    {
        int mid=(low+high)/2;
        MergeSort(low,mid);
        MergeSort(mid+1,high);
        Merge(low,mid,high);
    }
}

void Merge(int low,int mid,int high)
{
    int h,i,j,k;
    h=i=low,j=mid+1;

    while(h<=mid&&j<=high)
    {
        if(num[h]<=num[j])
        {
            snum[i++]=num[h++];
            w+=i-h;
        }
        else
        {
            snum[i++]=num[j++];
        }
    }
    if(h>mid)
        for(k=j;k<=high;k++)
            snum[i++]=num[k];
    else
        for(k=h;k<=mid;k++)
            snum[i++]=num[k],w+=i-k-1;

    for(k=low;k<=high;k++)
        num[k]=snum[k];
}
