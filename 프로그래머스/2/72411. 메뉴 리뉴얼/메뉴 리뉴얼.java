import java.util.*;
class Solution {
    Map<String, Integer> candidates = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
    
        for (String o : orders) {//일단 다 추가
            //o에서 조합고르기 combinations
            char[] chararray = o.toCharArray();
            Arrays.sort(chararray);
            o = new String(chararray);
            getCombination(o, 0, new StringBuilder());
            for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                // System.out.printf("%s %d\n", entry.getKey(), entry.getValue());
            // if (entry.getValue() < 2) candidates.remove(entry.getKey());
            }
            System.out.println();
            
        }
        //빈도수처리는 여기서
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            // System.out.printf("%s %d\n", entry.getKey(), entry.getValue());
            // if (entry.getValue() < 2) candidates.remove(entry.getKey());
        }
        //이제 다 2이상이니 course갯수길이맞는거만검사
        List<String> ans = new ArrayList<>();
        for (int c : course) {
            int maxFreq = 0;
            for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                if (entry.getKey().length() != c) continue;
                if (entry.getValue() < 2) continue;
                maxFreq = Math.max(maxFreq, entry.getValue());
            }
            List<String> added = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                if (entry.getKey().length() != c) continue;
                if (entry.getValue() < 2) continue;
                if (entry.getValue() == maxFreq) added.add(entry.getKey());
            }
            for (String s : added) ans.add(s);
            
        }
        ans.sort((a,b)->a.compareTo(b));
        return ans.toArray(new String[0]);
        
    }
    void getCombination(String order, int idx, StringBuilder sb) {
        
        if (idx >= order.length()) {
            if(sb.length() >= 2) { //최소 메뉴2개
                candidates.put(sb.toString(), candidates.getOrDefault(sb.toString(), 0) + 1);
            }
            return;
        }
        //고름
        sb.append(order.charAt(idx));
        getCombination(order, idx + 1, sb);
        //안고름
        sb.deleteCharAt(sb.length() - 1);
        getCombination(order, idx + 1, sb);
    }
}
/**

*/