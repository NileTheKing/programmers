import java.util.*;
class Solution {
    List<String> ans = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        int ticketSize = tickets.length;
        for (String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], key -> new PriorityQueue<>())
                .offer(ticket[1]);
        }
        
        dfs(map, "ICN", ticketSize);
        
        return ans.toArray(new String[0]);
        
    }
    public boolean dfs(Map<String, PriorityQueue<String>> map, String current
                      , int ticketSize) {
        
        ans.add(current);
        if (ans.size() == ticketSize + 1) return true; //종료조건
        
        if (!map.containsKey(current) || map.get(current).isEmpty()) {
            ans.remove(ans.size() - 1); // backtrack
            return false;
        }

        PriorityQueue<String> pq = new PriorityQueue<>(map.get(current));
        
        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            map.get(current).remove(next);
            if (dfs(map, next, ticketSize)) return true;
            map.get(current).offer(next);
        }
        
        ans.remove(ans.size() - 1);
        return false;
        
    }
}