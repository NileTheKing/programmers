import java.util.*;
class Solution {
    Map<String, PriorityQueue<String>> map;
    List<String> ans;
    int targetSize;
    public String[] solution(String[][] tickets) {
        this.map = new HashMap<>();
        this.ans = new ArrayList<>();
        this.targetSize = tickets.length + 1;
        for (String[] t : tickets) {
            String v1 = t[0];
            String v2 = t[1];
            map.computeIfAbsent(v1, k -> new PriorityQueue<>()).offer(v2);
        }
        List<String> path = new ArrayList<>();
        path.add("ICN");
        backtrack("ICN", path);
        return ans.toArray(new String[0]);
    }
    boolean backtrack(String current, List<String> path) {
        if (path.size() == targetSize) {
            ans = new ArrayList<>(path);
            return true;
        }
        PriorityQueue<String> original = map.get(current);
        if (original == null || original.isEmpty()) return false;
        PriorityQueue<String> copy = new PriorityQueue<>(original);
        
        while (!copy.isEmpty()) {
            //original에서지우고, path추가
            String c = copy.poll();
            original.remove(c);
            path.add(c);
            //백트래킹
            if (backtrack(c, path)) return true;
            //다시추가,path제거
            original.offer(c);
            path.remove(path.size() - 1);
        }
        return false;
    }
}
/**
1티켓을 모두써서
2모두방문
3사전순
*/