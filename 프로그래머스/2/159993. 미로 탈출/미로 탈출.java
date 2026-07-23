import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public int solution(String[] maps) {
        //맵완성
        int m = maps.length;
        int n = maps[0].length();
        char[][] grid = new char[m][n];
        int startR = 0, startC = 0;
        int leverR = 0, leverC = 0;
        int exitR = 0, exitC = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maps[i].charAt(j);
                if (grid[i][j] == 'S') {
                    startR = i;
                    startC = j;
                }
                if (grid[i][j] == 'L') {
                    leverR = i;
                    leverC = j;
                }
                if (grid[i][j] == 'E') {
                    exitR = i;
                    exitC = j;
                }
            }
        }
        //레버까지
        //debug
        // System.out.printf("start:%d %d/ exit:%d %d/ lever:%d %d\n", startR,startC,exitR,exitC,leverR,leverC);
        boolean[][] visited = new boolean[m][n];
        visited[startR][startC] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR, startC});
        int cnt1 = 0;
        boolean flag1 = false;
        Outter : while (!q.isEmpty()) {
            // if (cnt1 >= 8) break;cdebug
            int size = q.size();
            cnt1++;
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                // System.out.printf("(%d, %d)\n",polled[0], polled[1]);
                for (int[] d : directions ) {
                    int nr = polled[0] + d[0];
                    int nc = polled[1] + d[1];
                    
                    //bound
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    //wall
                    if (grid[nr][nc] == 'X') continue;
                    //visited
                    if (visited[nr][nc]) continue;
                    //quit
                    if (nr == leverR && nc == leverC) {flag1 = true; break Outter;}
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        if (!flag1) return -1;
        
        visited = new boolean[m][n];
        visited[leverR][leverC] = true;
        q = new LinkedList<>();
        q.offer(new int[] {leverR, leverC});
        int cnt2 = 0;
        boolean flag2 = false;
        Outter : while (!q.isEmpty()) {
            // if (cnt1 >= 8) break;cdebug
            int size = q.size();
            cnt2++;
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                // System.out.printf("(%d, %d)\n",polled[0], polled[1]);
                for (int[] d : directions ) {
                    int nr = polled[0] + d[0];
                    int nc = polled[1] + d[1];
                    
                    //bound
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    //wall
                    if (grid[nr][nc] == 'X') continue;
                    //visited
                    if (visited[nr][nc]) continue;
                    //quit
                    if (nr == exitR && nc == exitC) {flag2 = true; break Outter;}
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        if (!flag2) return -1;
        
        return cnt1 + cnt2;
    }
}
/**
두번하면되네 레버까지하나 
레버에서 엑싯까지 하나.. 다익스트라 이런거도아니고 걍
bfs 두번
*/