import java.util.*;
class Solution {
    public int solution(int N, int number) {
        
        List<Set<Integer>> dp = new ArrayList<>();
        
        //초기화
        for (int i = 0; i < 9; i++) {//9개 만들면 마지막 인덱스가 8
            dp.add(new HashSet<>());
        }
        for (int i = 1; i <= 8; i++) {
            int repeated = Integer.parseInt(String.valueOf(N).repeat(i));
            dp.get(i).add(repeated);
        }
        //순회하며 모든 조합만들기
        for (int i = 2; i <= 8; i++) { //i는 N갯수
            Set<Integer> currentSet = dp.get(i);
            for (int j = 1; j < i; j++) {
                //i개인 수로 만들 수 있는 조합은. 예를 들어 4라치자.
                //또 i-j + j 으로 나타낼 수 있음. 예를 들면 j가 1이면 i -j인 3이이서
                // 1+3, 2+2로 할 수 있음
                Set<Integer> a = dp.get(j);
                Set<Integer> b = dp.get(i - j);
                for (int aa : a) {
                    for (int bb : b) {
                        currentSet.add(aa + bb);
                        currentSet.add(aa - bb);
                        currentSet.add(aa * bb);
                        if (bb != 0) currentSet.add(aa / bb);
                    }
                }
            }
        }
        for (int i = 1; i <= 8; i++) {
            if (dp.get(i).contains(number)) return i;
        }
        
        return -1;
        
        
    }
}
/**
dp를 이용해서 풀기
그러면은 bottom up/ top-down
if number = 12 N=5
1 2 3 4 5 6 7 8 9 10 11 12
bottom-up 
12를 만드느는 조합. 1개로 2개로 3개로 ..... 
1개는 A
2개 A+B
3개 A+B+C
4개 A+B+C+D
결국 A+B형식으로 표현 가능

5 1개로 만들 수 있는 숫자 만들고
5 2개로 만들 수 있는 숫자 만들고.
5 3개로 만들 수 있는 숫자 만들고 (위에서 만든 조합과 5를 사칙연산 한 결과를 추가)
5 4개 (5 3개로 만들수 있는 숫자와 5 사칙연산한 겨우우ㅢ 수 추가)
...여기서 12나오면 스탑.




*/