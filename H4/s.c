#include <stdio.h>
#define CST 2000000

int main () 
{
	long N,K,i,ii;

	long W[20];
	long Win[CST];
	int flag;

	while (scanf("%ld",&N)==1) {
		scanf("%ld",&K);

		for (i=K;i--;) 
		{
			scanf("%ld",&W[i]);
		}

		Win[0] = 1;
	for (i=1;i<N+1;i++) {
		flag = 0;

		for (ii=K;ii--;) {
			if (W[ii]<=i) {
				if (Win[i-W[ii]]) {
					flag=1;
					break;
				}
			}
		}

		if (flag) Win[i]=0; 
		else Win[i]=1;
	}

	if (Win[N]) printf("Ollie wins\n"); 
	else printf("Stan wins\n");

	}

	return 0;
}
