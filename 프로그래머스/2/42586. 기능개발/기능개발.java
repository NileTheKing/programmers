import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();
        
        int length = progresses.length;
        int[] days = new int[length];
        for (int i = 0; i < length; i++) {
            days[i] = (int)Math.ceil((double)(100-progresses[i]) / speeds[i]);
        }
        
        //days: {7,3,9}
        int max = days[0];
        int cnt = 1;
        for (int i = 1; i < days.length; i++) {
            if (days[i] <= max) {//한번에 배포가능
                cnt++;
            }else {//이젠 안된다. 처리
                ans.add(cnt);
                cnt = 1;//초기화
                max = days[i];
            }
        }
        ans.add(cnt);
        
        return ans.stream().mapToInt(i->i).toArray();
    }
}