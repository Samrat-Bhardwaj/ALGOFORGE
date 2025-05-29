#include <iostream>
#include<unordered_map>
#include<unordered_set>
#include<vector>

using namespace std;

int main() {
    unordered_map<string,int> data;
    unordered_set<string> dataSet;
    data["India"]=100;     
    data["China"]=230;    
    data["England"]=1200;   
    
    cout<<data["China"]<<endl;
    
    if(data.find("India") == data.end()){
        cout<<"No such key"<<endl;
    } else {
        cout<<"This key was present"<<endl;
    }
    
    // vector<string> arr = {"1","2","3"};
    // for(auto e: arr){
    //     cout<<e<<endl;
    // }
    
    for(auto itr: data){
        cout<<itr.first<<": "<<itr.second<<endl;
    }

    dataSet.insert("India");
    dataSet.insert("India");
    dataSet.insert("India");
    dataSet.insert("China");



    return 0;
}