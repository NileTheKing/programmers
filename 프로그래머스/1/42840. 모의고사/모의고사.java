import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] supo1 = {1,2,3,4,5};
        int[] supo2 = {2,1,2,3,2,4,2,5};
        int[] supo3 = {3,3,1,1,2,2,4,4,5,5};
        
        int[] cnts = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            if (supo1[i % supo1.length] == answers[i]) cnts[0]++;
            if (supo2[i % supo2.length] == answers[i]) cnts[1]++;
            if (supo3[i % supo3.length] == answers[i]) cnts[2]++;
        }
        
        int max  = Integer.MIN_VALUE;
        for (int cnt : cnts) {
            max = Math.max(cnt, max);
        }
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < cnts.length; i++) {
            if (cnts[i] >= max) answer.add(i+1);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}