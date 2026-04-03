class Solution {
    int cnt = 0;
    public int solution(int n) {
        boolean[][] grid = new boolean[n][n];
        backtrack(0, new boolean[n], grid);
        return cnt;
    }
    public  void backtrack(int idx, boolean[] usedCol, boolean[][] grid) {
        if (idx == grid.length) {
            cnt++;
            return;
        }
        // System.out.printf("idx: %d\n", idx);
        outter : for (int i = 0; i < grid.length; i++) {//지금idx행에서 고를 열고르기
            if (usedCol[i]) continue;
            //이전행의 대각선열이 있으면 이번경우는 할필요도 없다.
            
            for (int j = 1; j <= idx; j++) { 
                //현재 위치는 grid[idx][i]임.. 얘랑 대각선겹치는지.
                if ((idx - j >= 0 && i - j >= 0) && grid[idx-j][i-j]) continue outter;
                if ((idx - j >= 0 && i + j < grid.length) && grid[idx-j][i+j]) continue outter;
            }
            
            //드디어 조건 통과..
            usedCol[i] = true;
            grid[idx][i] = true;
            backtrack(idx + 1, usedCol, grid);
            grid[idx][i] = false;
            usedCol[i] = false;
        }
    }
}
/*
두냐 마냐..완탐해야지. 뭐 dp최적화..되나 흠..이건 몰겠음
n이12니까 완탐하면 2^144인데. 이거는 불가능. 가지치기를 해야하나.
해도 되는건가?매번 다 해야하는게 아니니까..
그떄 그 문제에서 배신뭐시기.. 2^500이어도 이게 중간에 가지치기가 됨..
*/