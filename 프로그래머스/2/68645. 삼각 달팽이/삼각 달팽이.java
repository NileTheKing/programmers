import java.util.*;
class Solution {
    int[][] directions = {{1,0},{0,1},{-1,-1}};
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int limit = (n * n + n) / 2;
        int r = 0, c = 0, dir = 0;
        
        for (int i = 1; i <= limit; i++) {
            triangle[r][c] = i;
            
            int nr = r + directions[dir][0];
            int nc = c + directions[dir][1];
            if (nr >= n || nr < 0 || nc >= n || nc < 0 || triangle[nr][nc] != 0) {
                dir = (dir + 1) % 3;
                //다시계산
                nr = r + directions[dir][0];
                nc = c + directions[dir][1];
            }
            //업데이트
            r = nr;
            c = nc;
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (triangle[i][j] != 0) ans.add(triangle[i][j]);
            }
        }
        
        
        return ans.stream().mapToInt(i->i).toArray();
    }
}