import java.util.*;
class Solution {
    public int solution(int[] order) {
        Deque<Integer> stack = new ArrayDeque<>();
        int targetIdx = 0;
        int cnt = 0;
        //목표 타겟이 아닌 들어오는 박스에 초점을 둔다.
        for (int i = 1; i <= order.length; i++) { //박스가 온다
            stack.offerFirst(i);//일단넣기
            while (!stack.isEmpty() && stack.peek () == order[targetIdx]) {
                stack.pollFirst();
                targetIdx++;
                cnt++;
            }
        }
        return cnt;
    }
}