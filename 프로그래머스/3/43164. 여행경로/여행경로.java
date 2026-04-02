import java.util.*;
class Solution {
    Map<String, PriorityQueue<String>> map;
    List<String> ans;
    public String[] solution(String[][] tickets) {
        map = new HashMap<>();
        
        ans = new ArrayList<>();
        for (String[] t : tickets) {
            map.computeIfAbsent(t[0], k -> new PriorityQueue<>())
                .offer(t[1]);
            //leftOver.add(t[0] + t[1]);
        }
        backtrack("ICN", new ArrayList<>(), tickets.length);
        
        return ans.toArray(new String[0]);
    }
    public boolean backtrack(String current, List<String> path, int length) {
        path.add(current);
        if (path.size() == length + 1) { //티켓 다씀!성공.
            ans = new ArrayList<>(path);
            return true;
        }
        //path.add(current);
        PriorityQueue<String> original = map.get(current);
        if (original == null || original.isEmpty()) {
            path.remove(path.size() - 1);
            return false;
        }
        PriorityQueue<String> pq = new PriorityQueue<>(original);
        while (pq != null && !pq.isEmpty()) {
            String polled = pq.poll();
            map.get(current).remove(polled);
            if (backtrack(polled, path, length)) return true;
            map.get(current).offer(polled);
            //여기서 pq또 넣나..?또 넣으면 무한반복이고.. 안넣으면 복구가안되는ㄷ.
            //아니 복구할필요가 없지 안되는거니까. 걔가 안되면 뒤에서 찾아줄겨.
        }
        path.remove(path.size() - 1);
        return false;
    }
}