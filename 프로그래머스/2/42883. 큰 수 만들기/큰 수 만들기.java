import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        Stack<Character> stack = new Stack<>();
        stack.push(number.charAt(0));
        int ogk = k; //k값 복사
        
        for (int i = 1; i < number.length(); i++) {
            char c = number.charAt(i);
            //앞에 더 작은 게 없으면 다 없앰
            while (!stack.isEmpty() && c > stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        
        return sb.reverse().substring(0, number.length() - ogk);
        
    }
}
/**
숫자 제거
작은거를 제거하면 좋고
큰거는 남겨놓으면 좋지
    뒤에 자릿수가 더 크면 지우면 됨
    계쏙 인접한거 비교할 수 있게 하면 좋을듯?
    

1924면 19에서 1지워야지. 최소 1보단 나으니까
그러면 924
그러고나서 924 92. 그다음 24. 2지우는게 이득 94

그렇다면 매번 지울떄마다 앞에서부터 비교. 
while (k) {
    for ()
}

1231234
    231234
    31234
    3234
*/