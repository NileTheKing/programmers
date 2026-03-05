import java.util.*;
class Solution {
    boolean[] visited = new boolean[1000001];
    public int[] solution(int[] nodes, int[][] edges) {
        
        List<Integer>[] adj = new ArrayList[1000001];
        for (int n : nodes) {
            adj[n] = new ArrayList<>();
        }
        for (int[] e : edges) {//양방향 연결
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        //=====이제 포레스트 구축 완료
        
        //각 포레스트 순회하면서 해당 포레스트의 노드를 루트로 설정(루트로 설정된 노드는 자식과 연결을 끊음)
        int odd = 0;
        int even = 0;
        for (int i = 0; i < 1000001; i++) { 
            if (adj[i] == null) continue; //아닌ㅇ ㅐ들은 패스
            if (visited[i]) continue; //이미 판별 끝난 애들
            //해당 포레스트의 모든 노드들을 저장..그다음에 이제 걔를 부모로 해가지고 되는지 판별

            List<Integer> candidates = findNodes(adj, i);
            //이제 트리 구성원들을 알았으니까..차수랑 숫자비교해가면서 홀짝트리가 될 수 있는지랑
            //역홀짝 트리가 될 수 있는지 확인
            int canBeChild = 0;
            int notChildRoot = 0;
            int canBeRoot = 0;
            
            for (int c  : candidates) {
                visited[c] = true;
                boolean root = false;
                boolean child = false;
                //순부모가능
                if ((c % 2 == 0 && adj[c].size() % 2 == 0) ||
                   (c % 2 == 1 && adj[c].size() % 2 == 1)) {
                    root = true;
                }
                
                //순자식가능
                if ((c % 2 == 0 && (adj[c].size() - 1) % 2 == 0) ||
                   (c % 2 == 1 && (adj[c].size() - 1) % 2 == 1)) {
                    child = true;
                }
                if (child) canBeChild++;
                if (root) canBeRoot++;
                if (!child && root) notChildRoot++;
            }
            if ((candidates.size() == canBeChild && canBeRoot > 0) ||
               (candidates.size() - 1 == canBeChild && notChildRoot == 1)) odd++;
            
            canBeChild = 0;
            notChildRoot = 0;
            canBeRoot = 0;
            for (int c  : candidates) {
                //역부모가능
                boolean root = false;
                boolean child = false;
                if ((c % 2 == 0 && adj[c].size() % 2 == 1) ||
                   (c % 2 == 1 && adj[c].size() % 2 == 0)) {
                    root = true;
                }
                
                //역자식가능
                if ((c % 2 == 0 && (adj[c].size() - 1) % 2 == 1) ||
                   (c % 2 == 1 && (adj[c].size() - 1) % 2 == 0)) {
                    child = true;
                }
                if (child) canBeChild++;
                if (!child && root) notChildRoot++;
                if (root) canBeRoot++;
            }
            if ((candidates.size() == canBeChild && canBeRoot > 0) ||
               (candidates.size() - 1 == canBeChild && notChildRoot == 1)) even++;
            
        }
        return new int[] {odd,even};
    }
    public List<Integer> findNodes(List<Integer>[] adj, int node) {
        
        List<Integer> res = new ArrayList<>();
        //res.add(node);
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;
        while (!q.isEmpty()) {
            int polled = q.poll();
            res.add(polled);
            if (adj[polled] == null) continue;
            for (int nei : adj[polled]) {
                if (visited[nei]) continue;//guard clause
                visited[nei] = true;
                q.offer(nei);
            }
        }
        return res;
    }

}
/**
*/