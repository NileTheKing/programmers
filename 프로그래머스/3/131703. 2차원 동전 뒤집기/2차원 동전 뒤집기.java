import java.util.*;
class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int m = beginning.length;
        int n = beginning[0].length;
        int min = Integer.MAX_VALUE;
        // 1 * 2^10 = 1024..
        for (int r = 0; r < (1 << m); r++) {
            Outter : for (int c = 0; c < (1 << n); c++) {
                //r은 행비트 켜짐 c는 열비트 켜짐(뒤집어짐)..1뒤집기 0안뒤집힌상태(초기상태)
                //r,c상태가지고 테이블풀스캔
                // System.out.printf("===start bit: (%d, %d)===\n",r,c);
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int start = beginning[i][j];
                        boolean switched = (((1 << i) & r) != 0) ^ (((1 << j) & c) != 0);
                        //true면 바꿈
                        //보완한거는 r c 둘다1이거나 둘다0이면 바뀜.
                        //둘중하나만 1이면 바뀜..xor
                        if (switched) start ^= 1;
                        // System.out.printf("at row %d flipped?: %b\n", i, ((1 << i) & r) != 0);
                        // System.out.printf("at col %d flipped?: %b\n", i, ((1 << i) & c) != 0);
                        
                        // System.out.printf("at (%d,%d) values:(%d %d)\n",i,j,start, target[i][j]);
                        if (start != target[i][j]) continue Outter;
                    }
                }//무사히 종료.r이랑 c 뒤집은거(1)몇개인지 카운트
                int cnt = 0;
                for (int i = 0; i < m; i++) {
                    if (((1 << i) & r) != 0) cnt++;
                }
                for (int i = 0; i < n; i++) {
                    if (((1 << i) & c) != 0) cnt++;
                }
                min = Math.min(min, cnt);
                // System.out.printf("===end bit: (%d, %d)===\n",r,c);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
/**
자 몇가지 결론을 내야한다
언제 뒤집냐? 음... 일단 게임을 자연스럽게 이해해야함
 2 4행을 뒤집은이유가 뭘까? 음...
 뒤집는 다는 거는 한 줄을 아예 뒤집는 다는 것이고..
 음..무슨 뭐 그리디같은게 있나?
시간복잡도 한 번 봐보자.
가로세로 10 10.. 근데 그렇다면.. 그냥 완탐 떄려버리면 무조건 구할 ㅅ ㅜ있지.
모든 경우의수(몇번행 뒤집었는지 몇번열 뒤집었는지) 모든 조합을 다 구할수있어
어떻게? 총 20비트있으면 가능. 20비트면 충분.. 백만되나.
0-9 10-19
그상태로 다 뒤집어졌는지.
이게 아니라면 백트래킹 완탐인데 매번 경우의수가 100개있음.. 그래서 안되나?
근데 백트래킹이나 비트마스킹 완탐이나 똑같아야하는거 아닌가?
아 근데 이게 순서가 관련이 없네
그래서 백트래킹으로 하면 상태를 들고다니는 거고
근데 그래도 100개의 경우의수가 있는거아니냐? 목표비트를 뭐 들고다니면서 할수도없고
그냥
최종상태에서 매번 검사하는게 낫지않음?
경우의수 백만개고.. 칸100개니까 딱 1억.

*/