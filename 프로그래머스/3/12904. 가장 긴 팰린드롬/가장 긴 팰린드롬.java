import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            //짝수
            int l = i;
            int r = i + 1;
            
            while (l >= 0 && r < s.length()) {
                if (s.charAt(l) != s.charAt(r)) break;
                max = Math.max(r - l + 1, max);
                l--;
                r++;
            }
            //홀수
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < s.length()) {
                if (s.charAt(l) != s.charAt(r)) break;
                max = Math.max(r - l + 1, max);
                l--;
                r++;
            }
        }
        return max;
    }
}
/**

*/