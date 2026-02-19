import java.util.*;
class Solution {
    public int solution(int[] order) {
        Stack<Integer> belt = new Stack<>();
        int targetIdx = 0; // order.length되면 끝..중간에 안되는건 스킵임
        int box = 1;
        while (targetIdx < order.length) {
            if (!belt.isEmpty() && order[targetIdx] < belt.peek()) break;
            
            
            if (order[targetIdx] == box) {//기존 들어오는 컨테이너랑 매칭
                targetIdx++;
                box++;
            }else if (!belt.isEmpty() && order[targetIdx] == belt.peek()) {
                //보조  컨테이너 탑이랑 매칭
                belt.pop();
                targetIdx++;
            }else {//안되면 ..대신 보조벨트에는 넣고 다음
                belt.push(box++);
            }
            
        }
        return targetIdx;
    }
}
/**
1
2 1
3 2 1
2 1


언제 그만두고
언제는 벨트에라도 넣냐?

1 목표4
1 2 목표4
1  2 3 목표4
1 2 3 4 목표4
1 2 3 목표 3
1 2 목표 1
목표가 peek보다 작으면..

*/