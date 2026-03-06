import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    int m, n;
    boolean[][] escape;
    public int solution(String[] storage, String[] requests) {
        m = storage.length;
        n = storage[0].length();
        char[][] board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i] = storage[i].toCharArray();
            }
        }
        
        escape = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <  n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    escape[i][j] = true;
                }
            } 
        }
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (escape[i][j]) {
        //             System.out.printf("T ");
        //         }else System.out.printf("F ");
        //     }
        //     System.out.println();
        // }
        
        
        for (String r : requests) {
            if (r.length() == 1) {
                lifter(board, r.charAt(0));
            }else {
                crane(board, r.charAt(0));
            }
            // System.out.println();
            // for (int i = 0; i < m; i++) {
            //     for (int j = 0; j < n; j++) {
            //         System.out.printf("%c ", board[i][j]);
            //     }
            // System.out.println();
            // }
            
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    cnt++;
                }
                    // System.out.printf("%c", board[i][j]);
                // }else System.out.printf(" ");
            }
            System.out.println();
        }
        return cnt;
    }
    public void lifter(char[][] board, char c) {
        //주변이 아무것도 없거나
        //$에 둘러쌓인 애들은 제거
        List<int[]> toRemove = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != c) continue; //아니면 볼필요없다
                //맞다면 조건 확인..주변에 탈출가능포지션있따면 or 가장자리라면
                //탈출가능한지 호출
                if (possible(board,i,j)) toRemove.add(new int[] {i,j});
            }
        }
        for (int[] pos : toRemove) {
        board[pos[0]][pos[1]] = '.';
        // 3. 지워진 곳이 외부와 연결되어 있다면 escape도 업데이트 해주면 좋음
        // (또는 다음 턴 시작 시 전체적으로 BFS 한 번 돌리는 게 가장 깔끔)
    }
    }
    public boolean possible(char[][] board, int r, int c) {
        
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r,c});
        boolean[][] visited = new boolean[m][n];
        visited[r][c] = true;
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            for (int[] d : directions) {
                int nr = polled[0] + d[0];
                int nc = polled[1] + d[1];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) return true; //겸 예외처리
                if (visited[nr][nc]) continue;
                if (board[nr][nc] != '.') continue;
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr,nc});
            
            }            
        }
        return false;
    }
    public void crane(char[][] board, char c) {
        for (int i = 0; i < m; i++) {
            for (int j= 0; j < n; j++) {
                if (board[i][j] == c) {
                    board[i][j] = '.';
                }
            }
        }
        
    }
}