import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(String[] maps) {
        //grid
        int m = maps.length;
        int n = maps[0].length();
        char[][] grid = new char[m][n];
        //시작지점찾기
        //레버찾기
        //목표찾기 복사
        int[] start = new int[2];
        int[] exit = new int[2];
        int[] lever = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maps[i].charAt(j);
                if (grid[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }else if (grid[i][j] == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }else if (grid[i][j] == 'E') {
                    exit[0] = i;
                    exit[1] = j;
                }
            }
        }
        // for (char[] g : grid) System.out.println(Arrays.toString(g));
        // System.out.printf("start: (%,d%d)\n", start[0], start[1]);
        // System.out.printf("lever: (%,d%d)\n", lever[0], lever[1]);
        // System.out.printf("exit: (%,d%d)\n", exit[0], exit[1]);
        
        //레버까지
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {start[0], start[1]});
        visited[start[0]][start[1]] = true;
        int step = 0;
        boolean found = false;
        Outter : while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                // System.out.printf("start (%d, %d)\n", polled[0], polled[1]);
                for (int[] d : directions) {
                    // System.out.printf("directions..\n");
                    int nr = polled[0] + d[0];
                    int nc = polled[1] + d[1];
            
                    //예외처리
                    // System.out.printf("checking bound\n");
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    // System.out.printf("checking visit\n");
                    if (visited[nr][nc]) continue;
                    // System.out.printf("checking X\n");
                    if (grid[nr][nc] == 'X') continue;
                    // System.out.printf("passed all\n");
                    //종료
                    if (nr == lever[0] && nc == lever[1]) {
                        // System.out.printf("found lever\n");
                        found = true;
                        break Outter;
                    }
                    // System.out.printf("offering\n");
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        if (!found) return -1;
        //레버에서 출구까지
        //visited새로
        visited = new boolean[m][n];
        q = new LinkedList<>();
        visited[lever[0]][lever[1]] = true;
        q.offer(new int[] {lever[0], lever[1]});
        int step2 = 0;
        int cnt = 0;
        int limit = m * n;
        Out : while (!q.isEmpty() && cnt <= limit) {
            step2++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                // System.out.printf("At (%d, %d)\n", polled[0], polled[1]);
                for (int[] d : directions) {
                    int nr = polled[0] + d[0];
                    int nc = polled[1] + d[1];
                    
                    //예외처리
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    if (visited[nr][nc]) continue;
                    if (grid[nr][nc] == 'X') continue;
                    
                    //종료
                    if (nr == exit[0] && nc == exit[1]) {
                        return step + step2;
                    }
                    
                    visited[nr][nc] = true;
                    cnt++;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        // System.out.printf("step1, step2, cnt : %d, %d, %d\n", step, step2, cnt);
        return -1;
        
    }
}
/**
100 * 100
*/