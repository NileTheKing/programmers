import java.util.*;
class Solution {
    public int solution(int storey) {
        int at = 0;//지금위치
        int cnt = 0; //사용갯수
        while (at != storey) {
            //이제 그리디하게 고르는겨..옵션들중에 제일 가까운거
            //그러면 머냐 [at + 옵션]중에!! storey랑 가장 가까운 숫자 찾는거임.
            at = getTheClosest(at, storey);
            cnt++;
        }
        return cnt;
    }
    public int getTheClosest(int start, int dest) {
        //start에서 dest로 가는 것중에 가장 가까운 곳
        int res = start;//가장가까운곳갱신
        int minDiff = Math.abs(start-  dest);
        if (start == dest) return start;
        //경우의수1 start < dest -> 1, 10, 100,1000 점프
        else if(start < dest) {
            //start + gap 이랑 dest랑 절댓값비교해서 가까운거 갱신<<답을위한거
            //언제까지하냐 근데 start + gap dest했는데 이전보다 멀어져..
            int currentDiffBest = dest - start;
            int unit = 1; //1 10 100
            while (true) {
                int afterPosition = start + unit;//점프한 위치
                if (Math.abs(dest - afterPosition) > currentDiffBest) {
                    break;
                }
                if (Math.abs(dest - afterPosition) < minDiff) {
                    res = start + unit;
                    minDiff = Math.abs(dest - afterPosition);
                }
                unit *= 10;//다음시도
            }
        }
        //경우의수2 start > dest -> -1, -10, -100 점프.
        else {
            int currentDiffBest = start - dest;
            int unit = -1; //1 10 100
            while (true) {
                int afterPosition = start + unit;//점프한 위치
                if (Math.abs(dest - afterPosition) > currentDiffBest) {
                    break;
                }
                if (Math.abs(dest - afterPosition) < minDiff) {
                    res = start + unit;
                    minDiff = Math.abs(dest - afterPosition);
                }
                unit *= 10;//다음시도
            }
        }
        return res;
        
        
    }
}
/**
코드 먼저 치지마시고
지금 이거 거꾸로 올수있다는게 중요한거야
그래서 가장 멀리서 올수있는게 2억에서 1억오는거고.
그러면 일단 dp의 기본은 초기화임. 그래서 일단 어디까지 초기화하나?
흐으으음.....................자 그럼 이거 일단 패스하시고 초기화했다쳐 추가로
그러고 나면 이제 1거리에있는 모든 방향으로 다 체크해서 업데이트하는거임..
근데 이게 애매한게 먼지아나.. 정방향이면 최적해가 되는데
역이면 최적이 안되는거겉기도
그러면 이 두개를 구분하는건 어떻게 생각해?
아 근데 이거도 모르겠는게.. 섞어서 가면 최적일수도있잖아.
주우욱가는게 아니니까.. 그러면 흐으으음;;
어 절댓값이니까 최대한 그리디하게 가는거 아닌교?
자 한 번 해보자
16이면우선 최선은 10으로 가는거고. 그다음은 20으로 가는거야
그다음에? 옵션은 10으로 가는거랑 19로가는건데 당연 19죠? 
오오오
자 2554해보자잉
1 100 1000 10000 .. 중에 최선? 1000
1000으로 가. 그다음 옵션 1000에서 +1 10 100 1000.... / -1 10 100 1000 10000.
그러면 베스트머야 목표? 2천이지유
그러면 2000에서 이제 다음은 3000가. 그다음에 이젠 100씩이겠지 2900 2800 2700 2600 그다음은ㅇ ㅣ제? 2500보단 2590이 더가까우니까 ㅋ  2580 ...
ㅇㅋ
그러면 정해보자. 층수에서 +들/- 들했을때 가장 가까운 greedy하게
비교해서 가는거입니다 가까운 거 찾는 함수만드는게..
*/