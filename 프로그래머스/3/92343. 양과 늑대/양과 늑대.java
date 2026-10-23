import java.util.*;
class Solution {
    int max = 0;
    int[] info;
    List<List<Integer>> tree;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        tree = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int v1 = e[0]; //부모
            int v2 = e[1]; //자식
            tree.get(v1).add(v2);
        }
        backtrack(0, 0, 1, new HashSet<>());
        return max;
    }
    void backtrack(int current,int wolf, int sheep, Set<Integer> toVisit) {
        max = Math.max(sheep, max);
        toVisit.addAll(tree.get(current));
        for (int next : new ArrayList<>(toVisit)) {
            int nextInfo = info[next];
            int nsheep = sheep + (nextInfo == 0 ? 1 : 0);
            int nwolf = wolf + nextInfo;
            
            if (nsheep <= nwolf) continue; //지금안되니패스
            toVisit.remove(next);
            backtrack(next, nwolf, nsheep, toVisit);
            toVisit.add(next);
        }
        toVisit.removeAll(tree.get(current));
    }
}