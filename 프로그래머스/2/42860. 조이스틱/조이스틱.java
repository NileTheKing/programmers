class Solution {
    public int solution(String name) {
        int alphabet = 0;
        int length = name.length();
        int cursor = length - 1;
        
        for (int i = 0; i < length; i++) {
            //해당 문자 알파벳 바꾸는데 걸리는 횟수
            int count = Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            alphabet += count;
            //A건너 뛰고 다음 위치
            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            
            //다음 위치로 가는데 최소거리 그리디
            cursor = Math.min(cursor, 
                              Math.min(i + i + length - next, i + length - next + length - next));
            
        }
        return alphabet + cursor;
    }
}
/**
카운트를 두개로 나눠서 생각
어차피 A가 아닌 거는 바꿔야하므로 그것을 카운트하는 alphabet
그리고 커서 옮기는 횟수를 추적하는 cursor

커서는 어떻게 구하나? 순서대로 움직이면 이득이 아닐수도 있음
그래서 매 자리마다 최소 거리를 트래킹(그리디)
*/