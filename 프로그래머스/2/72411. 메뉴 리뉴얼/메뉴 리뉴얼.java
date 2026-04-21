import java.util.*;
class Solution {
    Map<String, Integer> candidates; //코스명, 주문횟
    public String[] solution(String[] orders, int[] course) {
        candidates = new HashMap<>();
        for (String o : orders) {
            // System.out.printf("====processing %s====\n", o);
            char[] chararray = o.toCharArray();
            Arrays.sort(chararray);
            String o2 = new String(chararray);
            getCourse(o2, 0, new StringBuilder());   
            // System.out.printf("====end of %s====\n",o);
        }
        
        
        //코스길이별로 pq만들어서 다 떄려박아
        List<String> ans = new ArrayList<>();
        for (int c : course) {
            int maxFreq = 0;
            for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                if (entry.getKey().length() == c) {
                    maxFreq = Math.max(maxFreq, entry.getValue());
                }
            }
            if (maxFreq < 2) continue;
            for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                if (entry.getKey().length() == c && entry.getValue() == maxFreq) ans.add(entry.getKey());
            }
        }
        
        Collections.sort(ans);
        return ans.toArray(new String[0]);
    }
    public void getCourse(String order, int idx, StringBuilder current) {
        //조합..
        if (idx == order.length() && current.length() >= 2) {
            String str = current.toString();
            candidates.put(str, candidates.getOrDefault(str, 0) + 1);
            // System.out.printf("<%s, %d>\n", str, candidates.get(str));
            return;
        }
        if (idx == order.length()) return;
        current.append(order.charAt(idx));
        getCourse(order, idx + 1, current);
        current.deleteCharAt(current.length() - 1);
        
        getCourse(order, idx + 1, current);
    }
}
/**
그 뭐시기
전체 경우의수랑 호출횟수알잖아
그러면 이제
길이가 2인 key 3인 key에서 가장 높은애가필요함..
*/