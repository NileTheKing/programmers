import java.util.*;
class Solution {
    char[] map;
    public String solution(int n, int t, int m, int p) {
        map = new char[16]; //0부터15까지 진법매핑
        for (int i = 0; i < 10; i++) {
            map[i] = (char)(i + '0'); //'0' '1'
        }
        map[10] = 'A';
        map[11] = 'B';
        map[12] = 'C';
        map[13] = 'D';
        map[14] = 'E';
        map[15] = 'F';
        StringBuilder numberList = new StringBuilder();
        for (int i = 0; i <= t * m; i++) {
            numberList.append(narize(i, n));
        }
        //숫자만들어졌으니까 이제 p턴마다(모듈로) 지번호 집으면됨
        StringBuilder ans = new StringBuilder();
        int cnt = 0;//구한 숫자갯수
        for (int i = 0; i < numberList.length(); i++) {
            //i번째 문자잉ㄹㄱ는중. 자기턴이면 읽어야하고..
            //System.out.printf("%c ", numberList.charAt(i));
            if (i % m + 1 == p) { //ex m4명있고 순서가 p2면 
                ans.append(numberList.charAt(i));
                cnt++;
            }
            if (cnt == t) return ans.toString();
        }
        return "";
        
    }
    String narize(int num, int n) {
        if (num == 0) return "0";
        //num을 n으로 ..
        //미리 0부터 15까지 매핑해놓으면됨.전역을
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int quotient = num / n;
            int remainder = num % n;
            sb.append(map[remainder]);
            num = quotient;
        }
        //System.out.printf("nary: %s\n", sb.toString());
        return sb.reverse().toString();
    }
}
/**
규칙..있겠냐 그냥 시뮬레이션이 더 빠름
다완성시켜서 하나씩 추가 ㄱ
진법n 구해야될t 게임인원m 순서p. 쉽네.

일단 음.......어디까지 완성을 하냐 이거잖아
그냥 일단 숫자 쭈르륵해놓고..돌아가면서 자기턴오면 추가하면됨
근데 진수가있으니까 흠... 흐ㅡ으으음
지금 카운트숫자를 진법변환해서 돌려주는 애가 필요함. 이거를 string으로해가지고..
*/