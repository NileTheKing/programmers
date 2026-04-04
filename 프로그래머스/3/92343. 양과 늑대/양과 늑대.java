import java.util.*;
class Solution {
    int cnt = 0;
    List<List<Integer>> map;
    int[] info;
    public int solution(int[] info, int[][] edges) {
        map = new ArrayList<>();
        this.info = info;
        // 자식 리스트 초기화
        for (int i = 0; i < info.length; i++) {
            map.add(new ArrayList<>());
        }
        
        // 단방향으로만 저장
        for (int[] e : edges) {
            map.get(e[0]).add(e[1]);
        }
        Set<Integer> candidates = new HashSet<>();
        candidates.addAll(map.get(0));
        backtrack(candidates, 1, 0);
        
        return cnt;
    }
    public void backtrack(Set<Integer> candidates, int sheep, int wolf) {
        cnt = Math.max(cnt, sheep);
        
        for (int next : new ArrayList<>(candidates)) {
            int newSheep = sheep + (info[next] == 0 ? 1 : 0);
            int newWolf = wolf + (info[next] == 1 ? 1 : 0);
            
            if (newWolf >= newSheep) continue;
            candidates.remove(next);
            candidates.addAll(map.get(next));
            backtrack(candidates, newSheep, newWolf);
            candidates.add(next);
            candidates.removeAll(map.get(next));
        }
    }
}
/**
dfs로 푼다쳐봐 근데 문제조건상 돌아올 수 있어. 
아니 이게 종료조건이

*/