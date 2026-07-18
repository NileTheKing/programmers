import java.util.*;
class Solution {
    List<String> ans = new ArrayList<>();
    int targetLength = 0;
    Map<String, PriorityQueue<String>> map;
    public String[] solution(String[][] tickets) {
        this.targetLength = tickets.length + 1;
        this.map = new HashMap<>();
        // System.out.printf("targetLength : %d\n", targetLength);
        for (String[] t : tickets) {
            String v1 = t[0];
            String v2 = t[1];
            map.computeIfAbsent(v1, k -> new PriorityQueue<>()).offer(v2);
        }
        List<String> list = new ArrayList<>();
        list.add("ICN");
        backtrack("ICN", list);
        return ans.toArray(new String[0]);
    }
    public boolean backtrack(String current, List<String> path) {
        if (path.size() == targetLength) {
            // System.out.printf("===reached end===");
            // for (String p : path) System.out.printf("%s ", p);
            System.out.println();
            ans = new ArrayList<>(path);
            return true;
        }
        PriorityQueue<String> original = map.get(current);
        if (original == null || original.size() == 0) return false;
        
        PriorityQueue<String> copy = new PriorityQueue<>(original);
        while (!copy.isEmpty()) {
            String polled = copy.poll();
            path.add(polled);
            map.get(current).remove(polled);
            if (backtrack(polled, path)) return true;
            path.remove(path.size() - 1);
            map.get(current).offer(polled);
        }
        return false;
    }
}
/**
그래프 백트래킹

<출발, 도착[]>

*/