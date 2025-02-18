import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        
        int[] supo1 = {1, 2, 3, 4, 5};
        int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;
        
        for (int i = 0; i < answers.length; i++) {
            if (supo1[i % 5] == answers[i]) cnt1++;
            if (supo2[i % 8] == answers[i]) cnt2++;
            if (supo3[i % 10] == answers[i]) cnt3++;
        }
        
        
        //답구하기
        int max = Math.max(cnt1, Math.max(cnt2, cnt3));
        
        HashSet<Integer> ans = new HashSet<>();
        if (cnt1 == max) {
            ans.add(1);
        }
        if (cnt2 == max) {
            ans.add(2);
        }
        if (cnt3 == max) {
            ans.add(3);
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
/**
1번 1 2 3 4 5
2번 2 하나 1 2 3 4 5
3번 33 11 22 44 55
*/