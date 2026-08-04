import java.util.*;
class Solution {
    int max = 0;
    int[] info;
    List<List<Integer>> tree;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        tree = new ArrayList<>();
        for (int i = 0; i < info.length; i++) tree.add(new ArrayList<>());
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            
            tree.get(v1).add(v2);
        }
        
        backtrack(0, 1, 0, new HashSet<>());
        return max;
    }
    void backtrack(int current, int sheep, int wolf, Set<Integer> toVisit) {
        //셀프추ㅏㄱ
        max = Math.max(sheep, max);
        toVisit.addAll(tree.get(current));
        
        //순회
        for (int nei : new HashSet<>(toVisit)) {
            int nwolf = wolf + info[nei];
            int nsheep = sheep + (info[nei] == 0 ? 1 : 0);
            
            if (nsheep <= nwolf) continue;
            
            toVisit.remove(nei);
            backtrack(nei, nsheep, nwolf, toVisit);
            toVisit.add(nei);
        }
        toVisit.removeAll(tree.get(current));
    }
}