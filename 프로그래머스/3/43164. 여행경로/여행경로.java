import java.util.*;
class Solution {
    Map<String, PriorityQueue<String>> ticket;
    int targetLength;
    List<String> ans = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        this.ticket = new HashMap<>();
        targetLength = tickets.length + 1;
        for (String[] t : tickets) {
            String v1 = t[0];
            String v2 = t[1];
            ticket.computeIfAbsent(v1, k -> new PriorityQueue<>()).add(v2);
        }
        List<String> path = new ArrayList<>();
        path.add("ICN");
        backtrack("ICN", path);
        
        return ans.toArray(new String[0]);
    }
    
    boolean backtrack(String current, List<String> path) {
        if (path.size() == targetLength) {
            ans = new ArrayList<>(path);
            return true;
        }
        PriorityQueue<String> original = ticket.get(current);
        if (original == null || original.isEmpty()) return false;
        PriorityQueue<String> copy = new PriorityQueue<>(original);
        while (!copy.isEmpty()) {
            String polled = copy.poll();
            path.add(polled);
            original.remove(polled);
            if (backtrack(polled, path)) return true;
            path.remove(path.size() - 1);
            original.offer(polled);
        }
        return false;
    }
}