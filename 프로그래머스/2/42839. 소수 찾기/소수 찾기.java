import java.util.*;
class Solution {
    HashSet<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        boolean[] visited = new boolean[numbers.length()];
        backtrack(visited, 0, new ArrayList<>(), numbers);
        return set.size();
    }
    
    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    //방문표시, 찾는 중인 길이, 현재 완성, 보기
    public void backtrack(boolean[] visited, int depth, ArrayList<Integer> current, String numbers) {
        
        int num = 0;
        for (Integer i : current) {
            num = num * 10 + i.intValue();
        }
        if (isPrime(num)) {
            set.add(num);   
        }
    
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]){
                visited[i] = true;
                int newadd = numbers.charAt(i) - '0';
                current.add(newadd);
                backtrack(visited, depth + 1, current, numbers);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
            
        }
        
    }
}