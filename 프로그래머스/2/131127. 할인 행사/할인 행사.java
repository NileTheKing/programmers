import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> toBuy = new HashMap<>();
        Map<String, Integer> current = new HashMap<>();
        
        //초기설정 구입할물건
        int n = number.length;
        for (int i = 0; i < n; i++) {
            toBuy.put(want[i], number[i]);
            //System.out.printf("adding to buylist %s %d\n", want[i], number[i]);
        }
        //초기 슬라이딩윈도우
        for (int i = 0; i < 10; i++) {
            current.put(discount[i], current.getOrDefault(discount[i], 0) + 1);
            //System.out.printf("refreshed %s %d \n", discount[i], current.get(discount[i]));
        }
        for (String product : current.keySet()) {
            //System.out.printf("current. %s %d\n", product, current.get(product));
        }
        //초기 해시맵들이 같은지 비교
        int cnt = 0;
        if (equals(toBuy, current)) cnt++;
        //슬라이딩윈도우하며 매번비교. 같으면 카운트
        //System.out.printf("until %d \n", discount.length - 1 - 10 - 1);
        for (int i = 1; i <= discount.length - 1; i++) {
            int r = i + 10 - 1;
            //System.out.printf("i is %d, removing %s by one and adding %s by one \n", i, discount[i-1], discount[r]);
            current.put(discount[i-1], current.get(discount[i-1]) - 1);
            if (r <= discount.length - 1)
                current.put(discount[r], current.getOrDefault(discount[r], 0) + 1);
            if (equals(toBuy, current)) cnt++;
        }
        return cnt;
    }
    public boolean equals(Map<String, Integer> map1, Map<String, Integer> map2) {
        for (String key : map1.keySet()) {
            if (map1.get(key) != map2.get(key)) {
                //System.out.printf("not equals because %s %d %d\n ", key, map1.get(key), map2.get(key));
                return false;            
            }
        }
        return true;
    }
}
/**
슬라이딩윈도우 해시맵
*/