//
// Created by vincent on 20-2-2.
//

#ifndef CPP_SOLUTION_H
#define CPP_SOLUTION_H

#include <string>
#include <queue>
#include <stack>
#include <vector>
#include <map>
#include <set>
#include <unordered_map>
#include <algorithm>
#include <cmath>
#include <functional>
#include <mutex>
#include <condition_variable>
#include "../vPackage/vTree/vTree.h"
#include "../vPackage/vList/vList.h"
#include "../vPackage/vThread/vSemaphore.h"

using namespace std;
using namespace vincent;

namespace leetcode
{
    class MyCalendarTwo;
    class MyCircularQueue;
    class ZeroEvenOdd;
    class DiningPhilosophers;
    class StreamRank;

    class Solution {
    public:

        ListNode* swapPairs(ListNode* head);                // 24
        int removeDuplicates(vector<int>& nums);            // 26
        ListNode* deleteDuplicates(ListNode* head);         // 83
        vector<int> inorderTraversal(TreeNode* root);       // 94
        TreeNode* sortedArrayToBST(vector<int>& nums);      // 108
        bool hasPathSum(TreeNode* root, int sum);           // 112
        // 127
        int ladderLength(string beginWord, string endWord, vector<string>& wordList);
        ListNode *detectCycle(ListNode *head);              // 142
        vector<int> preorderTraversal(TreeNode* root);      // 144
        vector<int> postorderTraversal(TreeNode* root);     // 145
        ListNode* insertionSortList(ListNode* head);        // 147
        int evalRPN(vector<string>& tokens);                // 150
        int numSquares(int n);                              // 279
        bool isAdditiveNumber(string num);                  // 306
        bool isPowerOfFour(int num);                        // 342
        // 368
        vector<int> largestDivisibleSubset(vector<int>& nums);
        int pathSum(TreeNode* root, int sum);               // 437
        bool repeatedSubstringPattern(string s);            // 459
        int minMoves2(vector<int>& nums);                   // 462
        int findComplement(int num);                        // 476
        int largestPalindrome(int n);                       // 479
        // 529
        vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click);
        int maxDepth(Node* root);                           // 559
        int leastInterval(vector<char>& tasks, int n);      // 621
        MyCircularQueue myCircularQueue();                  // 622
        string predictPartyVictory(string senate);          // 649
        int minSteps(int n);                                // 650
        bool checkValidString(string s);                    // 678
        MyCalendarTwo myCalendarTwo();                      // 731
        bool rotateString(string A, string B);              // 796
        // 851
        vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet);
        string orderlyQueue(string S, int K);               // 899
        bool flipEquiv(TreeNode* root1, TreeNode* root2);   // 951
        ZeroEvenOdd zeroEvenOdd();                          // 1116
        // 1122
        vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2);
        int dayOfYear(string date);                         // 1154
        string removeDuplicates(string s, int k);           // 1209
        int getMaximumGold(vector<vector<int>>& grid);      // 1219
        DiningPhilosophers diningPhilosophers();            // 1226
        // 1235
        int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit);
        // 1252
        int oddCells(int n, int m, vector<vector<int>>& indices);
        // 1337
        vector<int> kWeakestRows(vector<vector<int>>& mat, int k);

        /// 面试题
        vector<string> permutation(string s);               // 38
        int strToInt(string str);                           // 67
        // 0401
        bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target);
        StreamRank streamRank();                            // 1010
        int add(int a, int b);                              // 1701

    };

    class MyCalendarTwo {
    public:
        MyCalendarTwo();
        bool book(int start, int end);

    private:
        typedef pair<int, int> interval;

        vector<interval> records;
        vector<interval> intersects;

        interval find_repeat(interval &i1, interval &i2);
        bool real_interval(interval &i);
    };

    class MyCircularQueue {
    public:
        MyCircularQueue(int k);
        ~MyCircularQueue();
        bool enQueue(int value);
        bool deQueue();
        int Front();
        int Rear();
        bool isEmpty();
        bool isFull();
    private:
        int front_;
        int rear_;
        int size_;
        bool isFull_;
        vector<int> queue_;
    };

    class ZeroEvenOdd {
    private:
        int n;
        int count;
        mutex mutex_;
        condition_variable cond_;
    public:
        ZeroEvenOdd(int n) : count(0), mutex_(), cond_() { this->n = n; }

        void zero(function<void(int)> printNumber);
        void even(function<void(int)> printNumber);
        void odd(function<void(int)> printNumber);
    };

    class DiningPhilosophers {
    public:
        DiningPhilosophers() {}

        void wantsToEat(int philosopher,
                        function<void()> pickLeftFork,
                        function<void()> pickRightFork,
                        function<void()> eat,
                        function<void()> putLeftFork,
                        function<void()> putRightFork);

    private:
        std::mutex lock[5];
        Semaphore guid;
    };

    class StreamRank {
    public:
        StreamRank() : number(SIZE, 0){}
        void track(int x);
        int getRankOfNumber(int x);
    private:
        const static int SIZE = 50000;
        vector<int> number;
    };
} // namespace leetcode

#endif //CPP_SOLUTION_H
