import java.util.*;
class Solution {
    Map<String, PriorityQueue<String>> map;
    List<String> ans;
    int targetLength;
    public String[] solution(String[][] tickets) {
        map = new HashMap<>();
        ans = new ArrayList<>();
        targetLength = tickets.length;
        for (String[] t : tickets) {
            map.computeIfAbsent(t[0], k -> new PriorityQueue<>()).offer(t[1]);
        }
        List<String> path = new ArrayList<>();
        backtrack("ICN", path);
        return ans.toArray(new String[0]);
    }
    public boolean backtrack(String current, List<String> path) {
        path.add(current);
        if (path.size() == targetLength + 1) {
            ans = new ArrayList<>(path);
            return true;
        }
        
        PriorityQueue<String> original = map.get(current);
        if (original == null) {
            path.remove(path.size() - 1);
            return false;
        }
        PriorityQueue<String> copiedPq = new PriorityQueue<>(original);
        while (!copiedPq.isEmpty()) {
            String polled = copiedPq.poll();
            map.get(current).remove(polled);
            if (backtrack(polled, path)) return true;
            map.get(current).offer(polled);
        }
        path.remove(path.size() - 1);
        return false;
    }
}