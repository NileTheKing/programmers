import java.util.*;
class Solution {
    int[] parent;
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);//간선 오름차순.
        
        int ans = 0;
        for (int[] c : costs) {
            int v1 = c[0];
            int v2 = c[1];
            int expense = c[2];
            
            int p1 = find(v1);
            int p2 = find(v2);
            
            if (p1 == p2) continue;
            union(p1, p2);
            ans += expense;
        }
        return ans;
    }
    public int find(int n) {
        if (parent[n] == n) return n;
        //경로압축
        int root = find(parent[n]);
        parent[n] = root;
        return root;
    }
    public void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        if (p1 == p2) return;
        parent[p1] = p2;
    }
}