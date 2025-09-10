import java.util.*;

class Solution {
    public int solution(String s) {
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            boolean isValid = true;

            for (int j = 0; j < n; j++) {
                char c = s.charAt((i + j) % n);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    // 1. (버그 수정) 스택이 비어있는데 닫는 괄호가 나오면 실패
                    if (stack.isEmpty()) {
                        isValid = false;
                        break;
                    }

                    char top = stack.peek();
                    
                    // 2. (간결화) 짝이 맞는 경우를 하나로 묶어 처리
                    if ((c == ')' && top == '(') || 
                        (c == ']' && top == '[') || 
                        (c == '}' && top == '{')) {
                        stack.pop();
                    } else {
                        // 짝이 맞지 않으면 실패
                        isValid = false;
                        break;
                    }
                }
            }

            if (isValid && stack.isEmpty()) {
                count++;
            }
        }
        return count;
    }
}