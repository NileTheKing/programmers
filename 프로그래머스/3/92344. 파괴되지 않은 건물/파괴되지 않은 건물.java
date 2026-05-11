import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] diff = new int[board.length + 1][board[0].length + 1];
        for (int[] s : skill) {
            boolean positive = s[0] == 2 ? true : false;//2면 더하는..힐
            int degree = positive ? s[5] : -s[5];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            //r1 c1 +degree
            diff[r1][c1] += degree;
            //r1 c2+1 -degree
            diff[r1][c2+1] += -degree;
            //r2+1, c1 -degree
            diff[r2+1][c1] += -degree;
            //r2+1, c2+1  +degree
            diff[r2+1][c2+1] += degree;
        }
        for (int i = 0; i < diff.length; i++) {
            for (int j = 1; j < diff[0].length; j++) {
                //스위핑인데..가로스위핑
                diff[i][j] = diff[i][j - 1] + diff[i][j];
            }
        }
        for (int j = 0; j < diff[0].length; j++) {
            for (int i = 1; i < diff.length; i++) {
                diff[i][j] = diff[i][j] + diff[i - 1][j];
            }
        }
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += diff[i][j];
                if (board[i][j] > 0) ans++;
            }
        }
        
        return ans;
    }
}
