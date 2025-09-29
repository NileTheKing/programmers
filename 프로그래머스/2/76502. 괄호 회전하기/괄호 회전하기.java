import java.util.*;
class Solution {
    public int solution(String s) {
        Map<Character, Character> map = new HashMap<>();
        Set<Character> open = new HashSet<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (char c : map.values()) {
            //System.out.printf("%c added\n", c);
            open.add(c);
        }
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) { //각 시작점
            //끝까지 순회
            //새로운스택
            Stack<Character> stk = new Stack<>();
            boolean available = true;
            for (int j = 0; j < s.length(); j++) {
                int current = (i + j) % s.length();// 012345이 있고 2부터시작이면 234501.
                char c = s.charAt(current);
                if (open.contains(c)) {//지ㅐ금 넣는게 여는괄호
                    stk.push(c);
                }else { //닫는괄호
                    if (stk.isEmpty()) {
                        available = false;
                        break; //불가능
                    }
                    char top = stk.peek();
                    if (top == map.get(c)) { //짝맞음
                        stk.pop();
                    }else {//짝안맞음
                        available = false;
                        break;
                    }
                }
            }
            //플래그가 가능하면 카운트늘리기
            if (available && stk.isEmpty()) cnt++;
        }
        return cnt;
    }
}