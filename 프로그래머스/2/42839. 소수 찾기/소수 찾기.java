import java.util.*;
class Solution {
    Set<Integer> visited;
    public int solution(String numbers) {
        visited = new HashSet<>();
        backtrack(numbers, 0, new StringBuilder(), new boolean[numbers.length()]);
        // for 
        return visited.size();
    }
    public void backtrack(String numbers, int idx, StringBuilder current, boolean[] used) {
        
            if (idx != 0 && isPrime(current)) {
                visited.add(Integer.parseInt(current.toString()));
                // System.out.printf("%d added\n", Integer.parseInt(current.toString()));
            }
        
        
        for (int i = 0; i < numbers.length(); i++) {
            if (used[i]) continue; //이미 쓴 인덱스
            current.append(numbers.charAt(i));
            used[i] = true;
            backtrack(numbers, i + 1, current,used);
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }
    public boolean isPrime(StringBuilder sb) {
        int num = Integer.parseInt(sb.toString());
        if (num == 1 || num == 0) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                // System.out.printf("%d not prime\n", num);
                return false;
            }
        }
        return true;
    }
}