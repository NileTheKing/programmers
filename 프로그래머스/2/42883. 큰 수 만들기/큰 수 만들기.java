import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int cut = number.length() - k;
        
        for (int i = 0; i < number.length(); i++) {
            char num = number.charAt(i);
            //이전꺼랑 비교하는 로직을 스택으로. 앞에꺼 대체가능한지 확인
            while (!stack.isEmpty() && k > 0 && num > stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(num);
        }
        
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        return sb.reverse().substring(0, cut);
    }
}