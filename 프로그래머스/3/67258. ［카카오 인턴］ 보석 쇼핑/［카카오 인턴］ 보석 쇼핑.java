import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String g : gems) set.add(g);
        
        Map<String, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        int min = gems.length + 1;
        int l = 0;
        for (int i = 0; i < gems.length; i++) {
            // System.out.printf("===i : %d====\n", i);
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);//일단 늘림
            //만약 있으면 될떄까지 축소. 될떄마다 갱신
            while (l <= i && set.size() == map.keySet().size()) {
                // System.out.printf("l = %d\n", l);
                if (i - l + 1 < min) {
                    // System.out.printf("updated\n");
                    ans[0] = l + 1;
                    ans[1] = i + 1;
                    min = i - l + 1;
                }
                //l감소하고 0이면 아예삭제
                map.put(gems[l], map.getOrDefault(gems[l], 0) -1);
                if (map.get(gems[l]) <= 0) {
                    map.remove(gems[l]);
                }
                l++;
            }
        }
        return ans;
    }
}