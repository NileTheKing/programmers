import java.util.*;
class Solution {
    int m,n;
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(int[][] land) {
        m = land.length;
        n = land[0].length;
        Map<Integer, Integer> indexSizeMap = new HashMap<>();
        int idx = 2;
        for (int i = 0; i < m; i++) { //인덱싱 및 유전크기 재기
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) { //아직 발견안된 유전이면 카운팅 시작
                    //System.out.printf("land[%d][%d] not done yet\n", i,j);
                    indexSizeMap.put(idx, bfs(land, i,j, idx));
                    idx++;
                }
            }
        }
        
        //열별로 탐색하면서 크기 더하면서 최댓값 추적
        int max = 0;
        for (int j = 0; j < n; j++) {
            int size = 0;
            Set<Integer> counted_in_col = new HashSet<>();
            for (int i = 0; i < m; i++) {
                if (land[i][j] == 0 || counted_in_col.contains(land[i][j])) continue;
                size += indexSizeMap.get(land[i][j]);
                counted_in_col.add(land[i][j]);
            }
            max = Math.max(size, max);
        }
        return max;
    }
    public int bfs(int[][] land, int r, int c, int idx) {
        //탐색하면서 idx로 변경하면서 카운팅.
        //boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        //visited[r][c] = true;
        land[r][c] = idx;
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            //land[polled[0]][polled[1]] = idx;
            //System.out.printf("land[%d][%d] = %d\n",polled[0], polled[1], idx);
            for (int[] d : directions) {
                int nr = polled[0]  + d[0];
                int nc = polled[1]  + d[1];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || land[nr][nc] != 1) {
                    //System.out.printf("%d,%d is not possible\n", nr, nc);
                    continue;
                }
                q.offer(new int[] {nr, nc});
                cnt++;
                land[nr][nc] = idx;
                //visited[nr][nc] = true;
            }
        }
        return cnt;
    }
}