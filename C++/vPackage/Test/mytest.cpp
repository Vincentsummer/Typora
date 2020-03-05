//
// Created by vincent on 20-2-26.
//

#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

int main()
{
    string s = "12";
    cout << isdigit(s[0]) << endl;
}
//
//int main()
//{
//    int n;
//    cin >> n;
//    vector<int> dp(n+1, 1);
//    for (int i = 5; i <= n; ++i){
//        dp[i] = dp[i-1] + dp[i-4];
//    }
//    cout <<  dp[n] << endl;
//    return 0;
//}
