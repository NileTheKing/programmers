import java.util.*;
class Solution {
    List<Integer>[] trees;
    public int[] solution(int[] nodes, int[][] edges) {
        trees = new ArrayList[1000001];
        for (int n : nodes) trees[n] = new ArrayList<>();
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            trees[v1].add(v2);
            trees[v2].add(v1);
        }
        //forest완성..이제 그냥 풀스캔 때리면서..근데 그냥 node순회하면안됨?비어있는애들은 패ㅡㅅ
        boolean[] visited = new boolean[1000001];
        int[] ans = new int[2];
        for (int n : nodes) {
            if (visited[n]) continue;
            // System.out.printf("===start of (%d)===\n", n);
            List<Integer> cluster = getCluster(n);
            // System.out.printf("cluster size = %d\n", cluster.size());
            //정트리
            int root = 0;
            int child = 0;
            for (int c : cluster) {
                // System.out.printf("visited %d\n", c)
                visited[c] = true;
                if (c % 2 == 0) { //노드가 짝수
                    //자식수가 짝수면 부모가능
                    if (trees[c].size() % 2 == 0) root++;
                    else child++;
                    
                }else {//노드가 홀수
                    if (trees[c].size() % 2 == 1) root++;
                    else child++;
                }
            }
            if (root == 1) ans[0]++;
            //역트리
            root = 0;
            child = 0;
            for (int c : cluster) {
                if (c % 2 == 0) { //노드가 짝수
                    //자식수가 홀면 부모가능
                    if (trees[c].size() % 2 == 1) root++;
                    else child++;
                    
                }else {//노드가 홀수
                    if (trees[c].size() % 2 == 0) root++;
                    else child++;
                }
            }
            if (root == 1) ans[1]++;
        }
        return ans;
    }
    public List<Integer> getCluster(int n) {
        //bfs로 찾자
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        Set<Integer> visited = new HashSet<>();
        visited.add(n);
        List<Integer> res = new ArrayList<>();
        res.add(n);
        while (!q.isEmpty()) {
            int polled = q.poll();
            List<Integer> neis = trees[polled];
            if (neis == null) break;
            for (int nei : neis) {
                if (visited.contains(nei)) continue;
                visited.add(nei);
                q.offer(nei);
                res.add(nei);
            }
        }
        return res;
    }
    
}
/**
[문제이해]
정노드: 노드의 번호와 자식갯수가 정
역노드: 노드번호와 자식갯수가 역

홀짝트리: 정노드로만 이루어진 ㅋ트리
역홀짝트리: 역노드로만 이루어진 트리

포레스트를 순회하며 트리들 중에 홀짝트리가 될수있는 트리랑 역홀짝트리가 될 수 있는 트리 갯수구하기

어떻게하냐? 1. 모든경우의수를 시도.. 근데 여기서 생각해바야할게 브루트포스가 아닐수도 있는포인트.
루트가 되면 해당루트는 모든게 결정됨. 자식수 그대로 가져가고.. 루트의 자식들은 자식갯수가 어케되지 음..1개씩 줄어드는듯
기존에 1개였던앧르은 루트여야 1개를 가져가는거임

그니까 이거르를 다 확인해볼필요없이 루트가되면 자식수그대로 가져가는거고 루트가아니면 자식수가 -1임
약간의 flip성인거지 홀짝 상호배타성.
동시에 음. 해당트리가 되는지 아는지 보려면.. 
트리의 노드들은 부모가능 or 자식가능임 상호배타적임.. 여기서 이제 정트리되려면 부모되는애1에 자식가능한 애 n -1여야하는거아닌가?
부모만 되는애 2면 불가능인거지..
정리하면 노드를 순회하는데 각노드들은 부모만가능 or 자식만가능임. 둘다되는애는 없다.
하고나서 부모가능한애가 1개로 카운트 되어있으면 끝.

[조건]
노드 갯수 400,000
간선갯수 1,000,000

그러면 이제 음.. node갯수가 떨어져있을수있으니까 갯수도 꽤많고 -> List<>[] 사용.
이거 그 양방향 단방향은? 이게 위계가 있는거면 방향인데 뭐를 루트로 설정할지 모르기 떄문에 양방향 그래프로 가지는 게 맞음.
그거로 dfs/bfs해가지고 트리한다음에

해당트리를 이제 역트리인지 정트리인지 판단해야한다

*/