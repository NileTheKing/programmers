import java.util.*;
class Solution {
    public int solution(int[] citations) {
        
        Arrays.sort(citations);//정렬
        int max = 0;
        int number = citations.length; //얘보다 인용수 큰 논문들 수
        for (int i = 0; i < citations.length; i++) {
            //현재순회중인 인용수하고 걔보다 인용수많은 논문갯수비교
            if (number <= citations[i]) {
                max = number;
                break;
            }
            number--;
        }
        //순회하며 찾기. 0부터 확인해나감
        
        return max;
        
    }
}
/**
3 0 6 1 5 이게 각 논문별 인용횟수
5편 중 h인덱스가 3이라는 것은
h번이상 인용된 논문의 수가 3개이고 이것이 3으로 최고일떄.
일단 정렬
0 1 3 5 6
0번이상 5개. 나머지 0번이하 -> H일단 0
1번이상 1편이상 (4개이기때문). 나머지(1개) 1번 이하. -> H는 1..
3번이상 3개 나머지(3개) 3번이하 -> H는 3
5번이상 2개 나머지 



5 6 7 8
5번이상 4개.. 뒤로는 확인도X.
만약 4개 논문이 있어. 그렇다면 7 8 9 10이면 일단 4부터시작
근데 1 2 3 4면? 
5 6 7 8 9
*/