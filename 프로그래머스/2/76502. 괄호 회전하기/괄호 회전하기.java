import java.util.*;
class Solution {
    public int solution(String s) {
        int len = s.length();
        int cnt = 0;
        Outter : for(int i = 0; i < len; i++) { //i칸왼쪽으로했을떄 
            //시작인덱스가 i라는거임
            int start = i;
            Stack<Character> stk = new Stack<>();
            for (int j = 0; j < len; j++) {//횟수임.다읽어야함
                int currentIdx = (start + j) % len;
                char c = s.charAt(currentIdx);
                if (isOpening(c)) {
                    stk.push(c);
                }else {//한번에하나보는거임. 연쇄폭발없음.
                    if (!stk.isEmpty() && matches(stk.peek(), c)) {
                        stk.pop();
                    }else {
                        //아예안됨..포기.
                        continue Outter;//아예 다음 경우
                    }
                }
            }
            if (stk.isEmpty()) cnt++;// 다비어야함.
        }
        return cnt;
    }
    public boolean isOpening(char c) {
        if (c == '(' || c == '[' || c == '{') return true;
        else return false;
    }
    public boolean matches(char c1, char c2) {
        if (c1 == '(') {
            if (c2 == ')') return true;
        }else if (c1 == '[') {
            if (c2 == ']') return true;
        }else {
            if (c2 == '}') return true;
        }
        return false;
    }
}
/**
구현 브루트포스. 나머지쓰면되곘네 이건 회전이니.
그러면 이거를 음.. 미리만들어놓을필요도 딱히없음그냥 읽으면되지..근데
효율적으로 가능한가? 
회전할떄마다 시작칸을 하고 길이는 고정이고 매번 나머지를 써가지고 다음칸보면됨..
맞네. 그러면 그떄마다 스택쓰면됨
괄호..음 ㅇ거 스택인데 머릿속으로 자동이아니라 내가 머리써봐야함
여는거 들어와
닿는거야? 그럼 top이랑 맞아야함.
여는거넣어

ㅇㅋ
아 반직고나적인거 찾았음. 이거 그 연쇄작용떄문인데 택배큐 그거는넣을때마다 연쇄폭발시키는거고
이거는 안되면 걍 안되는거임.
*/