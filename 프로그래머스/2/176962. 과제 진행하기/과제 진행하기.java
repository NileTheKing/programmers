import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] ans = new String[plans.length];
        int ansIdx = 0;

        Stack<String[]> leftWork = new Stack<>();
        // 1. String[]의 1번 인덱스(시간)를 기준으로 오름차순 정렬
        PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> a[1].compareTo(b[1]));
        for (String[] p : plans) {
            pq.offer(p);
        }

        while (!pq.isEmpty()) {
            String[] work = pq.poll();
            String[] parts = work[1].split(":");
            int t = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
            int time_taken = Integer.parseInt(work[2]);

            // 2. 다음 과제가 있는 경우
            if (!pq.isEmpty()) {
                String[] next_work = pq.peek();
                String[] next_parts = next_work[1].split(":");
                int next_t = Integer.parseInt(next_parts[0]) * 60 + Integer.parseInt(next_parts[1]);

                if (t + time_taken > next_t) {
                    // 다음 과제 시작 전까지 다 못 끝낸 경우
                    int done = next_t - t;
                    leftWork.push(new String[] {work[0], String.valueOf(time_taken - done)});
                } else {
                    // 다 끝낸 경우
                    ans[ansIdx++] = work[0];
                    int currentTime = t + time_taken;
                    // 남은 시간 동안 스택 작업 처리
                    while (!leftWork.isEmpty() && currentTime < next_t) {
                        String[] left = leftWork.pop();
                        int left_time = Integer.parseInt(left[1]);
                        
                        if (currentTime + left_time <= next_t) {
                            currentTime += left_time;
                            ans[ansIdx++] = left[0];
                        } else {
                            // 스택 작업도 다 못 끝내면 다시 남은 시간 계산해서 push
                            int canDo = next_t - currentTime;
                            leftWork.push(new String[] {left[0], String.valueOf(left_time - canDo)});
                            break; // 시간 다 씀
                        }
                    }
                }
            } 
            // 3. 마지막 과제인 경우 (pq가 비었을 때)
            else {
                ans[ansIdx++] = work[0];
                while (!leftWork.isEmpty()) {
                    ans[ansIdx++] = leftWork.pop()[0];
                }
            }
        }
        return ans;
    }
}