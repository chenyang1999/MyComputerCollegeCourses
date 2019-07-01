#include <iostream>
#include <string>
using namespace std;
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
		void del(){
			int n, i, j;
			cout<<"Please input the number of account:"<<endl;
			cin>>n;
			for(i = 0; code[i] != 0; i++) {
				if(n == code[i]){
					cout<<"The account has been deleted!"<<endl;
					for(j = i; code[j+1] != 0;j++)
					    code[j] = code[j+1];
				    break;
				}
				else 
				cout<<"Please input the right number!"<<endl;
			}
		}
		void display(){
			int i;
			cout<<"The code and money is:"<<endl;
			for(i = 0; code[i] != 0; i++){
				cout<<code[i]<<"  "<<money[i]<<endl;
			}
		}
	private:
		string account;
		string name;
		string address;
		string employee;
		char rank;
		int code[100];
		double money[100];
		long long card;
		long long phone;
};
int main(){
	return 0;
}