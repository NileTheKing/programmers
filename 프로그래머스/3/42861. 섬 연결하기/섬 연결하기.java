import java.util.*;
class Solution {
    private int[] parent;
    public int solution(int n, int[][] costs) {
        int ans = 0;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]); //간선 오름차순
        
        //싼 간선 costs순회하면서 greedy하게 합치면됨
        for (int[] c : costs) {
            int v1 = c[0];
            int v2 = c[1];
            int expense = c[2];//간선가격
            
            int p1 = find(v1);
            int p2 = find(v2);
            
            if (p1 == p2) continue;
            union(p1, p2);
            ans += expense;
        }
        return ans;
        
    }
    public int find(int n) {//노드찾기
        if (parent[n] == n) return n;
        else return parent[n] = find(parent[n]); //경로압축
    }
    public void union(int n1, int n2) {//n1을 n2에 합치기
        
        int p1 = find(n1);
        int p2 = find(n2);
        
        if (p1 != p2){
            parent[p1] = p2;
        }
    }
}