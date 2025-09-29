import java.util.*;
class Solution {
    Map<Integer, Character> map = new HashMap<>();
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        map.put(10, 'A');
        map.put(11,'B');
        map.put(12,'C');
        map.put(13,'D');
        map.put(14,'E');
        map.put(15,'F');
        for (int i = 0; i < t * m; i++) {
            sb.append(convert(n, i));
            //System.out.printf(convert(n,i));
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int index = i * m + p - 1;
            //System.out.printf("checking idx %d\n", index);
            ans.append(sb.charAt(index));
        }
        return ans.toString();
        
    }
    //@Param n -> 진법
    public String convert(int n, int number) {
        if (number == 0) return "0";
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int remainder = number % n;
            if (remainder >= 10) {
                result.append(map.get(remainder));
            }else result.append(remainder);
            number /= n;
        }
        return result.reverse().toString();
    }
}
