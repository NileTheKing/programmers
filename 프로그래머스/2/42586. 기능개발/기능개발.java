import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();
        int length = progresses.length;
        int[] left = new int[length];
        for (int i = 0; i < length; i++) {
            left[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
        }
        int prev = left[0];
        int cnt = 1;
        for (int i = 1; i < length; i++) {
            if (left[i] <= prev) cnt++;
            else {
                ans.add(cnt);
                cnt = 1;
                prev = left[i];
            }
        }
        ans.add(cnt);
        return ans.stream().mapToInt(i->i).toArray();
    }
}