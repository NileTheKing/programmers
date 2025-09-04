import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b -a); //내림차순
        for (int w :works) {
            pq.offer(w);
        }
        for (int i = 0; i < n; i++) {
            if (pq.isEmpty()) break;
            int workMax = pq.poll();
            if (workMax == 0) break;//일 다함
            if (workMax - 1 >= 1)
                pq.offer(workMax - 1);
        }
        long ans = 0;
        while (!pq.isEmpty()) {
            long polled = pq.poll();
            ans += polled * polled;
        }
        return ans;
    }
}/**
최대한 flat하게 만들어야 함.
가장 큰 애를 추적을 해서 처리하고 또 큰애꺼내서 처리. minHeap
*/