//
// Created by vincent on 20-2-27.
//

#include "solution.h"

/**
 * 559. N叉树的最大深度
 * 递归/DFS/BFS
 * **/

namespace leetcode
{
    /// 递归
    int Solution::maxDepth(Node *root)
    {
        if (!root) return 0;
        int m = 0;
        for (auto it : root->children)
            m = max(m, maxDepth(it));
        return ++m;
    }

    /// DFS迭代
    int maxDepth1(Node* root)
    {
        if (!root) return 0;
        stack<pair<Node*, int>> aux;
        aux.push(pair<Node*, int>(root, 1));
        int res = 1, m = 0;
        while (!aux.empty()){
            root = aux.top().first;
            m = aux.top().second;
            aux.pop();
            for (auto v : root->children)
                aux.push(pair<Node*, int>(v, m+1));
            res = max(res, m);
        }
        return res;
    }

    /// BFS迭代
    int maxDepth2(Node* root)
    {
        if (!root) return 0;
        queue<Node*> aux;
        aux.push(root);
        int res = 0;
        while (!aux.empty()){
            res++;
            for (auto i = aux.size(); i > 0; --i){
                root = aux.front();
                aux.pop();
                for (auto it : root->children)
                    aux.push(it);
            }
        }
        return res;
    }
} // namespace leetcode