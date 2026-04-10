import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            
            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) break;

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            // 0 0 종료 조건
            if (N == 0 && M == 0) break;

            char[][] map = new char[M][N];
            int startR = 0; int startC = 0;
            List<int[]> stains = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                String row = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = row.charAt(j);
                    if (map[i][j] == 'o') {
                        startR = i;
                        startC = j;
                    }
                    if (map[i][j] == '*') {
                        stains.add(new int[] {i, j});
                    }
                }
            }
            
            // 더러운 칸을 구분하기 위해 번호를 매김 (원래 'i'로 하셨던 부분)
            for (int i = 0; i < stains.size(); i++) {
                int[] got = stains.get(i);
                map[got[0]][got[1]] = (char)('0' + i);
            }

            System.out.println(solve(M, N, map, startR, startC, stains.size()));
        }
    }

    static int solve(int M, int N, char[][] map, int startR, int startC, int stainCount) {
        if (stainCount == 0) return 0; // 청소할 게 없으면 0

        Queue<Info> q = new LinkedList<>();
        int target = (1 << stainCount) - 1; // 모든 비트가 1인 상태
        boolean[][][] visited = new boolean[M][N][1 << stainCount];
        
        q.offer(new Info(startR, startC, 0, 0));
        visited[startR][startC][0] = true;

        while (!q.isEmpty()) {
            Info polled = q.poll();
            for (int[] d : directions) {
                int nr = d[0] + polled.r;
                int nc = d[1] + polled.c;

                //bound
                if (nr < 0|| nr >= M || nc < 0 || nc >= N) continue;
                //가구
                if (map[nr][nc] == 'x') continue;
                
                //더러운칸인지 선처리.
                int nextDoneCell = polled.doneCell;
                if (map[nr][nc] >= '0' && map[nr][nc] < (char)('0' + stainCount)) { //더러운칸
                    //map은그대로 두고 그 doneCell처리
                    nextDoneCell = nextDoneCell | (1 << (map[nr][nc] - '0'));
                }
                
                //종료조건확인(다지움)
                if (nextDoneCell == target) return polled.steps + 1;
                
                //방문확인
                if (visited[nr][nc][nextDoneCell]) continue;
                
                //재입력
                visited[nr][nc][nextDoneCell] = true;
                q.offer(new Info(nr, nc, polled.steps + 1, nextDoneCell));
            }
        }

        return -1;
    }

    static class Info  {
        int r,  c,  steps,  doneCell;
        Info (int r, int c, int steps, int doneCell) {
            this.r = r;
            this.c = c;
            this.steps = steps;
            this.doneCell = doneCell;
        }
    }
}
