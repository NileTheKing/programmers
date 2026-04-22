import java.util.*;
class Solution {
    int m, n;
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int[] solution(String[] maps) {
        m = maps.length;
        n = maps[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maps[i].charAt(j);
            }
        }
        int idx = 0;
        int[][] mark = new int[m][n]; //1,2,3,...으로 묶음표기
        //이제 모든점을 순회..mark로 방문상태알수있음..0이면미방문.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'X' || mark[i][j] != 0) continue;
                idx++;//섬군집
                // System.out.printf("===start of cluster %d at (%d,%d)===\n", idx, i,j);
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[] {i, j});
                mark[i][j] = idx;//방문
                while (!q.isEmpty()) {
                    int[] polled = q.poll();
                    //System.out.printf("now at (%d,%d)\n", polled[0], polled[1]);
                    for (int[] d : directions) {
                        int nr = d[0] + polled[0];
                        int nc = d[1] + polled[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                        if (mark[nr][nc] != 0) continue; //방문
                        if (grid[nr][nc] == 'X') continue; //섬이아님
                        
                        mark[nr][nc] = idx;
                        // System.out.printf("marking (%d,%d) as %d\n", nr, nc, idx);
                        q.offer(new int[] {nr, nc});
                    }
                }
                // System.out.printf("===end of cluster %d at (%d,%d)===\n", idx, i,j);
                
            }
        }
        
        //이제 mark랑 grid같이 순회하면서 ..
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= idx; i++) { //1짜리군집합,,2짜리군집합 등.
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (mark[j][k] == i) cnt += (grid[j][k] - '0');
                }
            }
            ans.add(cnt);
        }
        
        ans.sort(null); //오름찻ㄴ
        return ans.size() == 0 ? new int[] {-1} : ans.stream().mapToInt(i->i).toArray();
    }
}
/**
석유시추같은 문제인데
*/