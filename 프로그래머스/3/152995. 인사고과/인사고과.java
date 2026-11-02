import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int[] temp = scores[0];
        int wanhoSum = temp[0] + temp[1];
        Arrays.sort(scores, (o1,o2)-> o2[0] == o1[0] ? -(o2[0]+o2[1])+(o1[0]+o1[1]) : o2[0] - o1[0]);//앞숫자로정렬. 뒷숫자는갱신
        int max = scores[0][1];
        int cnt = 0;
        for (int[] s : scores) {
            //탈락
            if (s[1] < max) {
                if (s[0] == temp[0] && s[1] == temp[1]) return -1;
                continue;
            }
            //통과.
            if ((s[0]+s[1]) > wanhoSum) cnt++;
            max = Math.max(s[1],max);
            
        }
        
        return cnt+1;
    }
}
/**
[3,2] [3,2] [2,2] [2,1] [1,4]
*/