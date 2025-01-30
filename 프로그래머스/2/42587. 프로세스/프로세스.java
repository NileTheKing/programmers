import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        
        Deque<int[]> deq = new ArrayDeque<>();
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int cnt = 1;
        
        for (int i = 0; i < priorities.length; i++) {
            deq.offer(new int[] {i, priorities[i]}); //작업번호와 우선순위
            pq.offer(priorities[i]);
        }
        
        while (true && !pq.isEmpty()) {
            int[] current = deq.poll();
            //만일 현재 체크중인 deque의 작업이 우선순위가 밀린다면
            if (current[1]  < pq.peek()) {
                deq.offer(current);//덱에 재삽입
            }
            else {//만약 현재 체크중인게 같거나 크다면(불가능)
                pq.poll();//제거
                if (current[0] == location) break;
                cnt++;
            }
        }
        
        return cnt;
        
        
        
    }
}