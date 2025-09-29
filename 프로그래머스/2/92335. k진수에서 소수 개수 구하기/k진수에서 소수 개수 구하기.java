class Solution {
    public int solution(int n, int k) {
        String kary = convert(k, n);
        String[] splited = kary.split("0");
        int cnt = 0;
        for (String part : splited) {
            if (part.length() > 0 && isPrime(Long.parseLong(part))) {
                //System.out.printf("%s counts\n", part);    
                cnt++;
            }
        }
        return cnt;
        
    }
    public String convert(int k, int number) {
        if (number == 0) return "0";
        StringBuilder result = new StringBuilder();
        
        while (number > 0) {
            result.append(number % k);
            number /= k;
        }
        return result.reverse().toString();
    }
    public boolean isPrime(long number) {
        if (number < 2) return false;
        boolean isAvailable = true;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
/**
n진수 변경 코드
String으로 받아서 split
*/