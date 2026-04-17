import java.util.*;
import java.io.*;
public class Main {
    static List<int[]>[] map;
    static int V;
    static int E;
    static int S;
    static int[] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V  = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine());
        map = (List<int[]>[]) new List[V + 1];
        Arrays.setAll(map, i -> new ArrayList<>());
        for (int i = 0; i < E; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(line.nextToken());
            int v = Integer.parseInt(line.nextToken());
            int w = Integer.parseInt(line.nextToken());
            map[u].add(new int[] {v, w});
        }
        dist = new int[V + 1];
        solve();
        for (int i = 1; i <= V; i++){
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }

    }
    static void solve() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;// init 0 for self
        //s에서부터 모든지점까지 최소거리 구하기
        pq.offer(new int[] {S,0}); //시작지점
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int v = polled[0];
            int w = polled[1];
            if (dist[v] < w) continue;
            for (int[] nei : map[v]) {
                if (w + nei[1] < dist[nei[0]]) {
                    dist[nei[0]] = w + nei[1];
                    pq.offer(new int[] {nei[0], dist[nei[0]]});
                }
            }
        }
    }
}
