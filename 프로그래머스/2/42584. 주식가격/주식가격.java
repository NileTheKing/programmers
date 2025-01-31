import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();//monostack
        
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.pop();
                //스택에서 나온 인덱스에 해당하는 가격에 해당하는 떨어지지 않은 시간을 입력.
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - 1 - stack.pop();
        }
        return answer;
    }
}