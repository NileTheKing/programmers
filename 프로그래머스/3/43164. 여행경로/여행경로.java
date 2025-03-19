import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        // ArrayList를 사용하여 중복 티켓까지 모두 저장
        List<List<String>> left = new ArrayList<>();
        for (String[] ticket : tickets) {
            left.add(Arrays.asList(ticket[0], ticket[1]));
        }
        
        List<String> answer = dfs("ICN", left, new ArrayList<>());
        return answer.toArray(new String[0]);
    }
    
    public List<String> dfs(String current, List<List<String>> left, List<String> path) {
        path.add(current);
        // 모든 티켓을 사용했으면 경로를 반환 (경로 길이는 티켓 수 + 1)
        if (left.isEmpty()) {
            return new ArrayList<>(path);
        }
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        // 현재 공항에서 출발 가능한 티켓들의 도착지를 사전순으로 PQ에 추가
        for (List<String> ticket : left) {
            if (ticket.get(0).equals(current)) {
                pq.offer(ticket.get(1));
            }
        }
        
        while (!pq.isEmpty()) {
            String next = pq.poll();
            int index = -1;
            // left 리스트에서 (current, next) 티켓을 찾아 인덱스를 얻음
            for (int i = 0; i < left.size(); i++) {
                List<String> ticket = left.get(i);
                if (ticket.get(0).equals(current) && ticket.get(1).equals(next))                 {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                // 티켓 사용: 해당 티켓을 left에서 제거
                List<String> ticketToRemove = left.remove(index);
                List<String> result = dfs(next, left, path);
                if (!result.isEmpty()) {
                    return result;
                }
                // 백트래킹: 사용했던 티켓을 다시 추가 (복구)
                left.add(ticketToRemove);
            }
        }
        // 백트래킹: 현재 공항을 경로에서 제거
        path.remove(path.size() - 1);
        return new ArrayList<>();
    }
}
