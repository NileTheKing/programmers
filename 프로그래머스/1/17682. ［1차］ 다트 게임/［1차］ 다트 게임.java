import java.util.*;
class Solution {
    public int solution(String dartResult) {
        int score = 0;
        int idx = 0;
        int prev = 0;
        while (idx < dartResult.length()) {
        
            int number = 0;
            if (idx + 1 < dartResult.length() && dartResult.charAt(idx + 1) - '0' >= 0 && dartResult.charAt(idx + 1) - '0' <= 10) {
                number = Integer.parseInt(dartResult.substring(idx, idx+2));
                idx = idx + 2;
            }else {//한자리
                number = Integer.parseInt(dartResult.substring(idx, idx +1));
                idx = idx + 1;
            }
            //보너스
            if (idx >= dartResult.length()) continue;
            char bonus = dartResult.charAt(idx);
            if (bonus == 'S') {
                number = number;//변경없음
            }else if(bonus == 'D') {
                number *= number;
            }else {
                int temp = number;
                number *= temp;
                number *= temp;
            }
            //지금 bonus파트에있음.
            if (idx + 1 < dartResult.length() && dartResult.charAt(idx + 1) - '0' >= 0 && dartResult.charAt(idx + 1) - '0' <= 10) {
                //optionpart보는데 숫자면?//아무것도안함
            }else if(idx + 1 < dartResult.length()) { // 스타상이면 점수더해주고 아차상이면 지금점수가 -가된다.
                //숫자파트가 아니다?
                char option = dartResult.charAt(idx + 1);
                idx++;
                if (option == '*') {
                    number *= 2;
                    score += prev;
                }else {
                    number *= -1;
                }
            }
            //이제처리
            score += number;
            prev = number; //number는 처리된값임..
            idx++;//여기서 다음이 무조건 숫자여야하는데.
        }
        return score;
    }
}
/**
[이해]
기회3번 던지고 해당 칸에는 점수와 s d t가있다. 각각 제곱
옵션?<< 모르겠는데 스타상이 있고 아차상이있따. 스타상 당첨시 이전점수,지금맞춘점수 2배... 아차상은 해당점수가 -가됨..
    스타상의 효과는 중첩가능. 그러면 4배로 적용 에제4
    슽상은 아차상이랑도 중첩가능... 이경우 예제5처럼 -2배
S D T는 점수마다하나씩 존재.. * # 는 동시에존재 X하나만 or 0개
총 점수. 단순히 구현하면된다.
문자열 3세트가 온대
아무튼 이거를 쪼개는거는 숫자로 쪼개야함.
예제1 -> 1S 2D* 3T임
예제2 -> 1D 2S# 10S.. <10있음주의..
예제4 계산 -> 1S* 2T* 3S인데 1 * 2 + 8 * 2 + 3..
예제5계산 -> 1D# 2S* 3S임 -1 * 2 + 2*2 + 3

의문은 제쳐두고 큰로직을 어떻게 구현할까
1.다음숫자나오기전까지 인덱스이동. 그다음에 숫자파트 보너스파트 옵션파트 본다.
이전 점수를 들고다녀서 옵션을 적용한다.
    스타상이면 이전숫자만큼 또 더하는거지 
        스타상 두개는? 잘적용됨. 이전숫자.

===
디버깅 특이사항 테케 2 3 6통과 1 4 5 7불통과.이유?
2 3 6은..몰라 일단 디버깅해보자
*/