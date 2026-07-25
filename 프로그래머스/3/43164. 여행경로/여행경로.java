import java.util.*;
class Solution {
    Map<String, PriorityQueue<String>> map;
    int targetLength;
    List<String> ans;
    public String[] solution(String[][] tickets) {
        map = new HashMap<>();
        targetLength = tickets.length + 1;
        for (String[] t : tickets) {
            map.computeIfAbsent(t[0], k -> new PriorityQueue<>()).offer(t[1]);
        }
        List<String> path = new ArrayList<>();
        path.add("ICN");
        backtrack("ICN", path);
        return ans.toArray(new String[0]);
    }
    boolean backtrack(String current, List<String> path) {
        if (path.size() ==targetLength) {
            ans = new ArrayList<>(path);
            return true;
        }
        PriorityQueue<String> origin = map.get(current);
        if (origin == null) return false;
        PriorityQueue<String> copy = new PriorityQueue<>(origin);
        while (!copy.isEmpty()) {
            String polled = copy.poll();
            path.add(polled);
            origin.remove(polled);
            if (backtrack(polled, path)) return true;
            path.remove(path.size() - 1);
            origin.offer(polled);
        }
        return false;
    }
}