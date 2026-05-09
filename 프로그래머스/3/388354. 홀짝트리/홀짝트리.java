import java.util.*;
class Solution {
    List<Integer>[] tree;
    boolean[] visited;
    public int[] solution(int[] nodes, int[][] edges) {
        
        tree = new ArrayList[1000001];
        for (int n  : nodes) {
            tree[n] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);//역방향해야함?그치 일단연결해애ㅎ마
            tree[e[1]].add(e[0]);
        }
        int[] ans = new int[2];
        visited = new boolean[1000001];
        for (int n : nodes) {//얘 덩어리에 대해서..(트리) 얘가 정인지 역인지 확인.. 그래서 카운트하는 
            if (tree[n] == null) continue;
            if (visited[n]) continue;
            // System.out.printf("===%d cluster===\n", i);
            List<Integer> cluster = getCluster(n);
            int size = cluster.size();
            //정
            int parent = 0;
            int child = 0;//부모되거나 자식되거나..둘다되는건없다.
            for (int c : cluster) {
                int degree = tree[c].size();
                
                //부모가능?
                if (c % 2 == degree % 2) parent++;
                //자식가능?
                else child++;
            }
            if (parent >= 1 && child >= size - 1) ans[0]++;
            //역
            parent = 0;
            child = 0;//부모되거나 자식되거나..둘다되는건없다.
            for (int c : cluster) {
                int degree = tree[c].size();
                
                //부모가능?
                if (c % 2 != degree % 2) parent++;
                //자식가능?
                else child++;
            }
            if (parent >= 1 && child >= size - 1) ans[1]++;
        }
        return ans;
        
        
    }
    public List<Integer> getCluster(int n) {
        //bfs로 찾기
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        res.add(n);
        q.offer(n);
        visited[n] = true;
        while (!q.isEmpty()) {
            int polled = q.poll();
            if (tree[polled] == null) continue;
            for (int nei : tree[polled]) {
                if (visited[nei]) continue;
                visited[nei] = true;
                q.offer(nei);
                res.add(nei);
            }
        }
        return res;
    }
}
/**
7 9 11 역
7 부모가능
9 부모가능
11 부모가능인데..자식도가능??

*/