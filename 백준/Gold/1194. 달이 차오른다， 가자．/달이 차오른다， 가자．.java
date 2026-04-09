import java.util.*;
import java.io.*;


public class Main {
    static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] map = new char[M][N];
        int startR = 0, startC = 0;
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0') {
                    startR = i; startC = j;
                }
            }
        }

        System.out.println(solve(N,M,startR,startC,map));

    }
    static int solve(int N, int M, int startR, int startC,char[][] map) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startR, startC, 0, 0));
        boolean[][][] visited = new boolean[M][N][64];
        visited[startR][startC][0] = true;
        
        while (!q.isEmpty()) {
            Node polled = q.poll();
            for (int[] d : directions) {
                int nr = polled.r + d[0];
                int nc = polled.c + d[1];
                if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                if (map[nr][nc] == '#') continue;

                int nextKeys = polled.keys;
                char cell = map[nr][nc];

                if (cell >= 'a' && cell <= 'f') {
                    nextKeys = nextKeys | (1 << cell - 'a');
                }
                if (cell >= 'A' && cell <= 'F') {
                    if ((nextKeys & (1 << (cell - 'A'))) == 0) continue;
                }
                
                if (visited[nr][nc][nextKeys]) continue;
    
                //도착확인
                if (map[nr][nc] == '1') return polled.dist + 1;
                //key
                
                visited[nr][nc][nextKeys] = true;
                q.offer(new Node(nr, nc, polled.dist + 1, nextKeys));

            }
        }
        return -1;
    }
    static class Node {
        int r, c, dist, keys;

        Node (int r, int c, int dist, int keys) {
            this.r =r ;
            this.c = c;
            this.dist = dist;
            this.keys = keys;
        }
    }
}