import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            String ab = String.valueOf(a) + String.valueOf(b);
            String ba = String.valueOf(b) + String.valueOf(a);
            return ba.compareTo(ab);
        });
        for (int n : numbers) pq.offer(String.valueOf(n));
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) sb.append(pq.poll());
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }
}