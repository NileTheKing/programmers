import java.util.*;
class Solution {
    public int solution(int[] order) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        //초점을..들어오는 상자
        int len = order.length;
        int idx = 0; //order처리여부..queue대신
        for (int i = 1; i <= len; i++) {
            //일단스택넣기..똑같음.
            stack.offerFirst(i);
            while (idx < len && !stack.isEmpty() && order[idx] == stack.peekFirst()) {
                stack.pollFirst();
                idx++;
            }
        }
        return idx;
        // 4 3 1 2 5
        // 1스택에넣기 -> 1
        // 2스택에 넣기 -> 2 1
        //3 스택에넣기 -> 3 2 1
        //4스택에 넣기.. 어? 마즈니까 4.. 어 또 맞으니까 3.. 스택 2 1
        //5 스택에넣기..어 안맞네..끝.인데 종료해줘야함? 그러면 코드가 어떻게되지
    }
}
/**
컨베이어 입구[    ] 큐
보조벨트 입구 [ ) 스택.
종료조건은 원하는순서대로 못한다면..
종료

*/