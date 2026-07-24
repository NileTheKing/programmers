import java.util.*;
class Solution {
    Set<Integer> primes = new HashSet<>();
    public int solution(String numbers) {
        backtrack(numbers, 0, new boolean[numbers.length()], new StringBuilder());
        // for (int p : primes) System.out.printf("%d ", p);
        return primes.size();
    }
    void backtrack(String numbers, int idx, boolean[] visited, StringBuilder sb) {
        if (isPrime(sb.toString())) primes.add(Integer.parseInt(sb.toString()));
        if (idx == numbers.length()) return;
        //순서고려해야해TODO
        //visited필요??
        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            sb.append(numbers.charAt(i));
            backtrack(numbers, idx + 1, visited, sb);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
            
        }
    }
    boolean isPrime(String n) {
        if (n == null) return false;
        if (n.length() == 0) return false;
        int num = Integer.parseInt(n);
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
/**
완탐을 조지면서.. set에 관리해서 마지막에 set크기하면될듯
*/