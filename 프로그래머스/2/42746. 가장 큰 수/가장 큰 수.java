import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        //numbers 10^5.. n^2안된다
        //nlogn pq를사용..10보단 2먼저가 이득.. 어떻게 비교하냐?그냥붙여서하는거.
        //근데 앞에숫자가 없는디? 그냥 음.. 1붙여서하면되나
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            String ab = String.valueOf(a) + String.valueOf(b);
            String ba = String.valueOf(b) + String.valueOf(a);
            
            return ba.compareTo(ab);
            
        });
        for (int n : numbers) pq.offer(n);
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) sb.append(pq.poll());
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }
}