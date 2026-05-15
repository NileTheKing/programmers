import java.util.*;
class Solution {
    public String solution(String p) {
        if (p.equals("")) return "";
        // System.out.printf("===p : %s===\n", p);
        //u랑 v로분리.
        //stack으로 갯수 같아지는순간 끊어
        int idx = getUIndex(p);
        String u = p.substring(0, idx);
        String v = p.substring(idx, p.length());//nullable
        // System.out.printf("u: %s, v : %s\n", u, v);
        //u가 올바른
        if (isGood(u)) {
            String res = solution(v);
            // System.out.printf("good,returning the result : %s\n", u + res);
            return u + res;
        }
        //올바르지않은
        else {
            // 4-1. 빈문자자열 (
            // 4-2. 문자열 v에대해 재귀적으로 수행한결과를 붙인다
            // 4-3. )을 붙인다
            // 4-4. u의 첫번째문자와 마지막문자를 제거하고 나머지문자열의 괄호방향을 뒤집어서 뒤에붙인다
            // 4-5. 생성된 문자열을 반환한다.
            String res = solution(v);
            String delAndRev = deleteAndReverse(u);
            // System.out.printf("not good,returning the result : %s\n", "(" + res + ")" + delAndRev);
            // System.out.printf("notgood extra info.. %s\n",delAndRev);
            return "(" + res + ")" + delAndRev;
        }
    }
    public int getUIndex(String str) {
        int openCount = 0;
        int closeCount = 0;
        if (str.charAt(0) == '(') openCount++;
        else closeCount++;
        int idx = 1;
        while (idx < str.length() && openCount != closeCount) {
            if (str.charAt(idx) == '(') openCount++;
            else closeCount++;
            idx++;
        }
        //idx-1]까지 u
        return idx;
    }
    public boolean isGood(String str) {
        //stack으로 짝맞아야한다.. 여닫이가되어야함 )( 는안돼
        //질문.. 초기값넣어야하나?
        if (str == null) return false;//혹시몰라서. 원래는 null아님
        if (str.charAt(0) == ')') return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        stack.offerFirst('(');//닫는거아니였음
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i); 
            if (c == '(') { //여는거면 일단넣어
                stack.offerFirst(c);
            }else {
                if (!stack.isEmpty() && stack.peek() != '(') return false;
                else {
                    stack.pollFirst();//하나씩하는거라 연쇄폭발없다.
                }
            }
        }
        return true;
    }
    public String deleteAndReverse(String str) {
        if (str.length() < 2) {
            // System.out.printf("deleteAndReverse length shortage\n");
            return "";
        }
        String deleted = str.substring(1, str.length() - 1);
        // System.out.printf("debug.. delandrev\n");
        // System.out.printf("before aftercut %s %s\n", str, deleted);
        StringBuilder sb = new StringBuilder();
        for (char c : deleted.toCharArray()) {
            char reversed = c == '(' ? ')' : '(';
            sb.append(reversed);
        }
        // System.out.printf("after rev %s\n", sb.toString());
        return sb.toString();
    }
}
/**
갯수ok
    and 짝 -> 균형
단순구현이야 참고해라

1. 빈문자열반환
    2. w를 두개로 분리 u v. u는 균형잡힌. v공백o
        3. u가 올바르면 v에대해 실행
            3-1 u에방금 v에대해 실행한걸 붙인 후 반환.
        4. u가 올바른문자열이 아님
            4-1. 빈문자자열 (
            4-2. 문자열 v에대해 재귀적으로 수행한결과를 붙인다
            4-3. )을 붙인다
            4-4. u의 첫번째문자와 마지막문자를 제거하고 나머지문자열의 괄호방향을 뒤집어서 뒤에붙인다
            4-5. 생성된 문자열을 반환한다.

초기 ()))((().. u () 나머지 ))((() u가 올바르니까 3. 그러면 ()(())()
    ))((() -> u ))((, v ()이잖아 그러면 결과는 (()) ()
        u () v 공백인데 쉽게 ()인걸리턴
    
이해했따.
그러면.. 이게 재귀가 있는데 ..어케구성하냐 이거지.
*/