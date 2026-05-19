import java.util.*;
class Solution {
    Map<String, Integer> courseCandidates;
    public String[] solution(String[] orders, int[] course) {
        courseCandidates = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (String o : orders) {
            //모든 조합을 구한다..길이2이상만 일단모아야함.미리필터링
            char[] ochar = o.toCharArray();
            Arrays.sort(ochar);
            String sortedO = new String(ochar);
            getCandidates(sortedO, 0, new StringBuilder());
        }
        for (Map.Entry<String, Integer> entry : courseCandidates.entrySet()) {
            // System.out.printf("%s,%d\n", entry.getKey(), entry.getValue());
        }
        //이제 원하는 길이별로-1 , 2번이상주문-2 2개조건으로 필터링 작업을 수행한다. 
        for (int c : course) {
            //길이가c여야함
            int max = 0;
            for (Map.Entry<String, Integer> entry : courseCandidates.entrySet()) {
                //길이c(key의 길이), value의값
                if (entry.getKey().length() == c && entry.getValue() >= 2) {
                    //추가하는데 사전오름차수는 여기 OR 마지막에.
                    max = Math.max(max, entry.getValue());
                }
            }
            // System.out.printf("===%d length, max: %d===\n", c, max);
            for (Map.Entry<String, Integer> entry : courseCandidates.entrySet()) {
                //길이c(key의 길이), value의값
                if (entry.getKey().length() == c && entry.getValue() == max) {
                    // System.out.printf("%s, %d..match the max %d\n", entry.getKey(), entry.getValue(), max);
                    ans.add(entry.getKey());
                }
            }
            
        }
        ans.sort((o1, o2) -> o1.compareTo(o2));
        return ans.toArray(new String[0]);
        // return new String[0];//debug
    }
    public void getCandidates(String order, int idx, StringBuilder current) {
        if (idx == order.length()) {
            if (current.length() >= 2) {
                String currentString = current.toString();
                courseCandidates.put(currentString, courseCandidates.getOrDefault(currentString, 0) + 1);
            }
            return;
        }
        current.append(order.charAt(idx));
        getCandidates(order, idx + 1, current);
        current.deleteCharAt(current.length() - 1);
        getCandidates(order, idx + 1, current);
        
    }
}