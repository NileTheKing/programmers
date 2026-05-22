import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();
        int[] left = new int[speeds.length];
        for (int i = 0; i < speeds.length; i++) {
            left[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            // System.out.println(left[i]);
        }
        int prev = left[0];
        int cnt = 1;
        for (int i = 1; i < left.length; i++) {
            if (left[i] <= prev) {
                cnt++;
            }else { //싹다 추가
                ans.add(cnt);
                cnt = 1;
                prev = left[i];
            }
        }
        ans.add(cnt);
        return ans.stream().mapToInt(i->i).toArray();
    }
}
/**
a / b 하면 올림안되니께
a + b - 1 / b
7 3 9
인데.. 같거나작으면 계속 묶음
5 10 1 1 20 1
1 3 2

*/