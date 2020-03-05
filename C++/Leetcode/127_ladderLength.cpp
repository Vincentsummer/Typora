//
// Created by vincent on 20-2-6.
//

#include <unordered_set>
#include "solution.h"

/**
 * 127. 单词接龙
 * 广度优先搜索
 * **/

namespace leetcode
{
    int res = INT32_MAX;

    bool canTransfrom(string word1, string word2)
    {
        int res = 0;
        if (word2 == "") return false;
        for (int i = 0; i < word1.size(); ++i){
            if (word1[i] != word2[i]) res++;
            if (res > 1) return false;
        }

        return res == 1;
    }

    /// 超时
    int Solution::ladderLength(string beginWord, string endWord, vector<string> &wordList)
    {
        unordered_set<string> visited;
        visited.insert(beginWord);
        queue<string> q;
        q.push(beginWord);

        int res = 1;
        while (!q.empty()){
            auto len = q.size();
            while(len--){
                string &t = q.front();
                q.pop();
                for (string word : wordList){
                    if (visited.count(word) && canTransfrom(t, word)){
                        if (word == endWord) return res+1;
                        visited.insert(word);
                        q.push(word);
                    }
                }
            }
            res++;
        }
        return 0;
    }

    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> dict(wordList.begin(),wordList.end());
        unordered_set<string> visited;
        visited.insert(beginWord);
        queue<string> q;
        q.push(beginWord);
        int res = 1;
        while(!q.empty()){
            int size=q.size();
            while(size--){
                auto t = q.front();
                q.pop();
                for(int i = 0; i < t.size(); i++){
                    string word = t;
                    for(auto ch = 'a'; ch <= 'z'; ch++){
                        word[i] = ch;
                        if(!dict.count(word) || visited.count(word)) continue;
                        if(word == endWord) return res+1;
                        visited.insert(word);
                        q.push(word);
                    }
                }
            }
            res++;
        }
        return 0;
    }
} // namesapce leetcode