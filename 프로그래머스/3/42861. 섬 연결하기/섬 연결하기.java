import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        List<int[]>[] graph = new ArrayList[n];
        
        //초기화
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        //그래프 정보 추가
        for (int[] cost : costs) {
            int v1 = cost[0];
            int v2 = cost[1];
            
            graph[v1].add(new int[] {v2, cost[2]});
            graph[v2].add(new int[] {v1, cost[2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{0, 0});
        int count = -1;
        int expense = 0;
        boolean[] visited = new boolean[n];
        while (!pq.isEmpty()) {
            int[] bridge = pq.poll();
            
            if (visited[bridge[0]]) continue;
            visited[bridge[0]] = true;
            expense += bridge[1];
            count++;
            
            if (count == n - 1) break;
            
            for(int[] node : graph[bridge[0]]) {
                pq.offer(node);
            }
                
        }
        return expense;
    }
}

// import java.util.*;
// class Solution {
//     public int solution(int n, int[][] costs) {
//         List<int[]>[] graph = new ArrayList[n];
//         List<int[]> added = new ArrayList<>(); //쓴 다리 중복체크 방지
//         //출발,목적,가격
        
//         //그래프 초기화
//         for (int i = 0; i < n; i++) {
//             graph[i] = new ArrayList<>(); //연결지점과 비용
//         }
        
//         //연결
//         for(int[] cost : costs) {
//             int v1 = cost[0];
//             int v2 = cost[1];
//             int expense = cost[2];
            
//             // int[] connect = new int[2]; //{목적지, 가격}
//             // connect[0] = v2;//목적지
            
//             graph[v1].add(new int[]{v2, expense}); //해당 출발지에 추가
//             graph[v2].add(new int[]{v1, expense}); //반대도 연결
//         }
        
//         //이제 모든 연결이 완료. 그리디로 연결시키기
//         //방문 체크하면서 순회하면서 작은거 연결
//         for (int i = 0; i < n; i++) {
//             //노드의 연결 정보를 모두 불러와서 가장 싼 애 찾음
//             List<int[]> bridges = graph[i]; //i출발. 목적지 가격
//             int minCost = Integer.MAX_VALUE;
//             int destination = -1;
//             for (int[] bridge : bridges) {
//                 if (bridge[1] < minCost) {
//                     minCost = bridge[1]; //가격업데이트
//                     destination = bridge[0]; //목적지 저장
//                 }
//             }
//             //작은거 찾았으므로 중복방지
//             if (i < destination) {
//                 added.add(new int[]{i, destination, minCost}); //해시에 등록
//             }
            
//         }
        
//         //다리 순회하며 가격더하기
//         int answer = 0;
//         for(int[] bridge : added) {
//             answer += bridge[2];
//         }
    
//         return answer;
//     }
// }
/**
costs에는 [섬1, 섬2, 비용] 이런식으로 주르륵
방향성X

그래프로 옮겨서 그리디로 작은거만 골라서 연결시켜야함. 여기서 연결을 보장하려면 어떻게 해야하나?
그래프 -> 리스트로? List<List<Integer>> 이거보단 List<integer[]>이게 나을듯?
integer[]이게 여러개있는 거고 이게 각 연결을 대표

그렇다면 이제 최소 비용은 어떻게 추려낼까
사람뇌로 해보면 각 노드에 대해 작은거

최소 필요한 노드는 n - 1개 이잖아. 그거보다 부족하면 못잇는거고
그러면 그냥 다리들 중에 n - 1개 고르면 되는거 아님? 작은순으로
-> 그런데 그렇다면 연결이 안되는 경우도 있음.

일단 노드별로 다리정보 관리는 해야함 -> 가장 싼 거 골라야 하므로
방문표시도 필요함 -> 다리 비용 체크해야함. 이미 쓰인지
*/