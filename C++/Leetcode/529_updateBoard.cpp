//
// Created by vincent on 20-2-5.
//

#include "solution.h"

/**
 * 529. 扫雷游戏
 * 广度优先搜索(递归求解)
 * **/

namespace leetcode
{
    int m = 0, n = 0;

    bool isArea(int x, int y){
        return ((x >= 0 && x < m) && (y >= 0 && y < n));
    }

    void dps(vector<vector<char>> &board, vector<int> pos)
    {
        int mine = 0;

        for (int i = -1; i <= 1; ++i){
            for (int j = -1; j <= 1; ++j){
                int x = pos[0] + i, y = pos[1] + j;
                if ((i == 0 && j == 0) || !isArea(x, y))
                    continue;
                if (board[x][y] == 'M') mine++;
            }
        }

        if (mine == 0){
            board[pos[0]][pos[1]] = 'B';
            for (int i = -1; i <= 1; ++i){
                for (int j = -1; j <= 1; ++j){
                    int x = pos[0] + i, y = pos[1] + j;
                    if ((i == 0 && j == 0) || !isArea(x, y) || board[x][y] != 'E')
                        continue;

                    vector<int> newPos = {x, y};
                    dps(board, newPos);
                }
            }
        }
        else
            board[pos[0]][pos[1]] = (char) (mine + '0');
    }

    vector<vector<char>> Solution::updateBoard(vector<vector<char>> &board, vector<int> &click)
    {
        m = (int) board.size();
        n = (int) board[0].size();

        char *cur = &board[click[0]][click[1]];

        if (*cur == 'M')    *cur = 'X';
        else if (*cur == 'E')   dps(board, click);

        return board;
    }


} // namespace leetcode