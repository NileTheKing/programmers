import java.util.*;
class Solution {
    public int solution(int n, int k) {
        String tran = n_nary(n, k);
        String[] parts = String.valueOf(tran).split("0");
        int cnt = 0;
        for (String p : parts) {
            //p가 소수인지
            if (p.length() == 0) continue;
            if (isPrime(p)) cnt++;
        }
        return cnt;
    }
    public String n_nary(int n, int k) { 
        //n을 k진수로. 예를들어 2진수는 어케나오냐면..
        //30은 2진수로바꿀떄 -> 몫15 나머지0 --나누기-- 몫7 나머지1 --나누기-- 목3 나머지1 --나누기2-- 몫1 나머지1 --- 몫0 나머지 1-- 끝! 나눠지는애가 0이므로
        //근데 숫자를 어케 char로하지?
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int quotient = n / k;
            int remainder = n % k;
            char c = (char)(remainder + '0');
            sb.append(c);
            n = quotient;
        }
        sb.reverse();
        return sb.toString();
    }
    public boolean isPrime(String str) {
        long num = Long.parseLong(str);
        if (num == 1) return false;
        
        //num가지고 소수확인.. 
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
/**
1.변환 2.카운트. 카운트는 그냥 0으로 자르는거아님?split
변환은 쉽지.
*/