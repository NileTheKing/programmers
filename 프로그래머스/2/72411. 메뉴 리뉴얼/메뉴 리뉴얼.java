import java.util.*;
class Solution {
    Map<String, Integer> candidates = new HashMap<>();//주문 목록으로 부터 추출한 가능성있는 <조합, 등장횟수>
    public String[] solution(String[] orders, int[] course) {
        //메누순회
        for (String o : orders) {
            //각메뉴들을 정렬후, 가능성 만들기.
            char[] tmp = o.toCharArray();
            Arrays.sort(tmp);
            String sortedOrder = new String(tmp);
            // System.out.printf("===Trying %s===\n", sortedOrder);
            calculateCombo(sortedOrder, new StringBuilder(), 0);//TODO
        }
        List<String> ans = new ArrayList<>();
        // System.out.println("completed candidates");
        // System.out.println(candidates);
        for (int c : course) {
            //candidatse순회하면서 c인거만 추가.
            //c는 메뉴구성갯수임.
            // System.out.printf("===코스길이 %d시도중===\n", c);
            int max = 0;
            for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                if (entry.getKey().length() != c) continue;
                // ans.add(entry.getKey());
                max = Math.max(max, entry.getValue());
            }
            // System.out.printf("최대반복: %d\n", max);
            if (max < 2) continue;
            for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                if (entry.getKey().length() != c || entry.getValue() != max) continue;
                // System.out.printf("max: %d, menu: %s, frequency: %d\n", max , entry.getKey(),entry.getValue());
                ans.add(entry.getKey());
            }
        }
        ans.sort(null);
        return ans.toArray(new String[0]);
    }
    public void calculateCombo(String str, StringBuilder current, int idx) {
        if (idx == str.length()) {
            if (current.length() < 2) return;
            candidates.put(current.toString(), candidates.getOrDefault(current.toString(), 0) + 1);
            // System.out.printf("candidates: <%s, %d>\n", current.toString(), candidates.get(current.toString()));
            return;
        }
    
        current.append(str.charAt(idx));
        calculateCombo(str, current, idx + 1);
        current.deleteCharAt(current.length() - 1);
        calculateCombo(str, current, idx + 1);
        return;
    }
}