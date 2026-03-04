import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int converted_today = toDays(today);
        
        Map<Character, Integer> info = new HashMap<>();//약관종류와 유효 개월
        for (String t : terms) {
            String[] parts = t.split(" ");
            info.put(parts[0].charAt(0), Integer.parseInt(parts[1]));
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String datePart = parts[0];
            String termPart = parts[1];
            
            int date = toDays(datePart); //동의날짜
            char term = termPart.charAt(0); 
            
            int termDateInfo = info.get(term); //유효기간..
            //동의날 + 유효기간이 < 오늘인가?
            //System.out.printf("약관 %c는 %d까지 유효한데 오늘 %d임\n", term, date + termDateInfo * 28 - 1, converted_today);
            if (termDateInfo * 28 + date - 1< converted_today) {
                ans.add(i + 1);
            }
        }
        
        return ans.stream().mapToInt(i->i).toArray();
    }
    public int toDays (String s) {
        String[] parts = s.split("\\.");
        
        int ans = 0;
        return Integer.parseInt(parts[0]) * 28 * 12 + Integer.parseInt(parts[1]) * 28 + Integer.parseInt(parts[2]);
    }
}