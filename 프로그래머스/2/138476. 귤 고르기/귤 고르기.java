import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {//카운팅. 크기,갯수
            map.put(t, map.getOrDefault(t,0) + 1);
        }
        
        List<Integer> sortedNumbers = new ArrayList<>(map.values());
        sortedNumbers.sort(Collections.reverseOrder());
        int cnt = 0;
        for (int i = 0; i < sortedNumbers.size(); i++) {
            cnt += sortedNumbers.get(i);
            if (cnt >= k) return i + 1;
        }
        return -1;
//         List<Integer> sorted = new ArrayList<>(map.values().sort(Collections.reverseOrder()));
        
//         int cnt = 0;
//         for (int i = 0; i < sorted.size(); i++) {
//             cnt += map.get(sorted.get(i));
//             if (cnt >= k) return i+1;
//         }
    }
}