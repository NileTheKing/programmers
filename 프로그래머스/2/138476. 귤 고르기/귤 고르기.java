import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);   
        }
        List<Integer> values = new ArrayList<>(map.values());
        values.sort((a,b) -> b - a);
        
        int cnt = 0;
        int ans = 0;
        for (int val : values) {
            cnt += val;
            ans++;
            if (cnt >= k) {
                return ans;
            }
        }
        return -1;
    }
}
/**
1 1
2 2
3 2
4 1
5 2
*/