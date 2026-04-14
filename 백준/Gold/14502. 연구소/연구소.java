import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] map;
    static int N, M;
    static int max;
    static List<Point> virus;
    static List<Point[]> combinations;
    static class Point {
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        virus = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new Point(i, j));
            }
        }
        max = Integer.MIN_VALUE;
        combinations = new ArrayList<>();
        getCombinations(0, new ArrayList<>());
        solve();
        System.out.println(max);
    }
    static void solve(){ 
        //매번 복사한 map에다가 조합적용해서 bfs호출->값갱신
        for (int i = 0; i < combinations.size(); i++) {//가능한3개ㄷ짜리 조합을 반복
            int[][] tempMap = deepCopy(map);
            for (Point p : combinations.get(i)) { //3개조합.
                tempMap[p.r][p.c] = 1; //세 지점을 벽세움
            }
            //bfs수행. 
            Queue<Point> q = new LinkedList<>();
            //visted필요한가?그다음턴에 또 업데이트할수도있잖어. 감염안되었는데 나중에 또 갑자기 될수도.
            //대신 q에는 감염된 애들만 가지고있으면되는데..이미 퍼뜨린애들은 visited하는게낫나?
            for (Point v : virus) q.add(v);
            while (!q.isEmpty()) {
                Point polled = q.poll();
                for (int[] d : directions) {
                    int nr = d[0]  + polled.r;
                    int nc = d[1] + polled.c;

                    if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                    if (tempMap[nr][nc] == 1) continue; //벽.
                    if (tempMap[nr][nc] == 2) continue; //이미바이러스
                    
                    tempMap[nr][nc] = 2; //사실상방문처리
                    q.offer(new Point(nr, nc));
                }
            }
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (tempMap[j][k] == 0) cnt++;
                }
            }
            max = Math.max(max, cnt);
        }
    }
    static int[][] deepCopy(int[][] map){
        int[][] res = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            res[i] = map[i].clone();
        }
        return res;
    }
    static void getCombinations(int idx,  List<Point> current) {
        if (current.size() == 3) {
            Point[] res = new Point[3];
            for (int i = 0; i < current.size(); i++) res[i] = new Point(current.get(i).r, current.get(i).c);
            combinations.add(res);
            return;
        }

        for (int i = idx; i < N * M; i++) {
            int r = i / N; //0일떄 0,0이어야하고 7일댸 1,0이어야함.
            int c  = i % N; 
            if (map[r][c] != 0) continue;
            current.add(new Point(r, c));
            getCombinations(i + 1, current);
            current.remove(current.size() - 1);
        }
    }   
}