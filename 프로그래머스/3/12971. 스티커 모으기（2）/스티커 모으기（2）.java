import java.util.*;
class Solution {
    public int solution(int sticker[]) {
        if (sticker.length == 1) return sticker[0];
        return Math.max(helper(Arrays.copyOfRange(sticker,0,sticker.length - 1)), helper(Arrays.copyOfRange(sticker, 1,sticker.length)));
    }
    public int helper(int[] sticker) {
        int n = sticker.length;
        
        if (n == 0) return 0; // 빈 배열이면 0 리턴
        if (n == 1) return sticker[0]; // 길이가 1이면 해당 값 리턴
        int[] dp = new int[n];
        dp[0] = sticker[0];
        dp[1] = Math.max(sticker[0], sticker[1]);
        //애매한게 두가지있음 .첫번쨰는 인덱스가 해당칸 포함인지
        //두번째는 dp[i]으 ㅣ값이 그 선택지를 고른 상황인지.
        //나는 경험에 의해 안다 굳이 두개의 상태를 관리안해도 되는것을
        //결국 for문에서는 이전상태골랐을때 상태랑 안골랐을떄+현재골랐을떄 할거라서.
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        return dp[n-1];
    }
}