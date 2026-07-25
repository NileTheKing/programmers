import java.util.*;
class Solution {
    int max = 0;
    Map<Integer, List<Integer>> childMap;
    int[] info;
    public int solution(int[] info, int[][] edges) {
        this.childMap = new HashMap<>();
        this.info = info;
        for (int i = 0; i < info.length; i++) childMap.put(i, new ArrayList<>());
        for (int[] e : edges) {
            childMap.get(e[0]).add(e[1]);
            //트리..단방향
        }

        Set<Integer> toVisit = new HashSet<>();
        toVisit.addAll(childMap.get(0));
        backtrack(1,0, toVisit);
        return max;
    }
    void backtrack(int sheep, int wolf, Set<Integer> toVisit) {
        max = Math.max(sheep, max);//매번갱신
        //백트래킹..완탐
        
        List<Integer> copy = new ArrayList<>(toVisit);
        for (int next : copy) {//next는 노드번호
            int nsheep = info[next] == 0 ? sheep + 1 : sheep;
            int nwolf = wolf + info[next];
            if (nwolf >= nsheep) continue;
            //방문
            toVisit.remove(next);
            toVisit.addAll(childMap.get(next));
            backtrack(nsheep, nwolf, toVisit);
            toVisit.add(next);
            toVisit.removeAll(childMap.get(next));
        }
        
    }
}
/**
완탐..매번갱신. 종료조건은 없어서 종료되거나 뭐 명시적으로 활용하거나...
*/