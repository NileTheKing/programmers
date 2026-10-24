import java.util.*;
class Solution {
    int[] info;
    List<List<Integer>> tree;
    int max = 0;
    Set<Integer> toVisit;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.toVisit = new HashSet<>();
        //필요정보만들기..순회할떄 편하게
        tree = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree.get(v1).add(v2);
        }
        //완탐해야해.백트래킹. 상태를 들고다니자
        backtrack(0,1,0); //
        return max;
    }
    public void backtrack(int current, int sheep, int wolf) {
        //본인이 추가하쇼 왜냐면 초기상태귀찮
        toVisit.addAll(tree.get(current));
        //순회
        max = Math.max(max, sheep);
        
        for (int next : new HashSet<>(toVisit)) {
            int nsheep = sheep + (info[next] == 0 ? 1 : 0);
            int nwolf = wolf + info[next];
            
            //미리 걸러주기
            if (nsheep <= nwolf) continue;//hit the road dude
            //가기
            toVisit.remove(next);
            backtrack(next, nsheep, nwolf);
            toVisit.add(next);
        }
        toVisit.removeAll(tree.get(current));
    }
    
}