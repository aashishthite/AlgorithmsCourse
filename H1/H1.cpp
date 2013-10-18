#include<iostream>

using namespace std;
void mergeSortedArrays(long long*, long long*, long long, long long, long long, long long*);

void mergeSort(long long *a, long long *b, long long low, long long high, long long* count) 
{
	//cout<<*count<<endl;
  	int mid;
  	if (low < high) 
	{
    		mid = (low + high) / 2;
    		mergeSort(a, b, low, mid, count);
    		mergeSort(a, b, mid + 1, high, count);
    		mergeSortedArrays(a, b, low, mid, high, count);
  	}
}

void mergeSortedArrays(long long *a, long long *b, long long low, long long mid, long long high, long long* count) 
{	/*
	for(long long ii = low; ii<=mid; ii++)
	{
		for(long long jj = mid+1;jj<=high; jj++)
		{
			if(a[ii]>a[jj])
				*count=*count+1;
			else 
				break;
		}
	}*/
  	long long h = low;
  	long long i = low;
  	long long j = mid + 1;

  	while ((h <= mid) && (j <= high)) 
	{
		
    		if (a[h] <= a[j]) 
		{
			//for(long long ii = mid+1; ii <= j; ++ii)
			{
				//if(a[h]>a[ii] && h<=mid) 
				{
					*count=*count+j-mid-1;
				}
			}
      			b[i] = a[h];
      			h++;
			
    		} 
		else 
		{
      			b[i] = a[j];
      			j++;
			//*count=*count+1;			
    		}
    		i++;
  	}
  	if (h > mid) 
	{
    		for (long long k = j; k <= high; k++) 
		{
      			b[i] = a[k];
      			i++;			
    		}
  	} 
	else 
	{
    		for (long long k = h; k <= mid; k++) 
		{
      			b[i] = a[k];
      			i++;
			
			//for(long long ii = mid+1; ii <= high; ++ii)
			{
				//if(a[k]>a[ii]) 
				{
					*count=*count+high-mid;
					//cout<<*count<<endl;
				}
			}
    		}
  	}

  
  	for (long long k = low; k <= high; k++) 
	{
    		a[k] = b[k];
  	}
}

int main(int argc, char** argv)
{
	long long n;
	while(cin>>n)
	{
		if(n>0)
		{
			//save the array
			//vector<long long> aa;
			long long * a = new long long[n];
			long long * b = new long long[n];
			for(int i = 0; i < n; ++i)
			{
				cin >> a[i];
			}			
			long long countSwaps = 0;
			mergeSort(a, b, 0, n-1, &countSwaps);
			cout << countSwaps << endl;
			delete[] a;
			delete[] b;	
		}
		else
		{
			break;
		}
	}
	return 0;
}




/*for(long long ii = 0; ii< n; ++ii)
			{
				for(long long jj = 0; jj< n-ii-1; ++jj)
				{
					long long a = ptr[jj], b = ptr[jj+1];
					if(a>b)
					{
						ptr[jj]=b;
						ptr[jj+1]=a;
						countSwaps++;						
					}
				}
			}*/
