import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = speeds.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            //100 - 93 + 1 - 1 / 1 ->7
            //100 - 30 + 30 - 1 / 30 -> 3
            //100 - 55 + 5 - 1 / 5 -> 9
        }
        //하고싶은것..
        //7 3 9에..
        //7 3묶어서 처리 9처리
        
        //[5,10,1,1,20,1]이을
        //[1,3,2]
        //
        int max = left[0];
        int cnt = 1;
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (left[i] <= max) {
                cnt++;
            }else {
                max = left[i];
                ans.add(cnt);
                cnt = 1;
            }
        }
        ans.add(cnt);
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
/**
음..순서가있따.
약간 뭐 그 스택이나 어디에쌓아두고..모노스택?암튼
7 3 9를 순회
7가지고 계속 순회 언제까지? 더 큰거나오면 멈춤
더작은거있으면.. 얘가 병목이라

*/