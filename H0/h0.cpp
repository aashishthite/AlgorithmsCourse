#include<iostream>
#include<string>


using namespace std;

int main(int argc, char** argv)
{
	
	long long first, second;
	
	while(cin >> first >> second)
	{
		cout <<  ((second - first)>0?(second-first):(first-second)) <<endl;
	}
	return 0;
}
