class Solution {
    int[] info;
    int best = 0; //최고점수
    int[] res;
    public int[] solution(int n, int[] info) {
        this.info = info;
        res = new int[11];//res[0] 은 10점 갯수..... res[10]은 0점갯수.d
        backtrack(new int[11], 0, n);
        return best == 0 ? new int[] {-1} : res;
    }
    public void backtrack(int[] current, int idx, int left) {
        if (idx == info.length || left == 0) {
            //남은 화살 0점에 다 떄려박고 계산
            // System.out.printf("========\n");
            for (int i = 0; i < left; i++) current[10]++;
            //current랑 info가지고 점수계산..하고 이겼는지 점수차이랑 갱신까지
            for (int i = 0; i < current.length; i++) {
                // System.out.printf("%d점 %d개\n", 10-i, current[i]);
            }
            calculate(current);
            for (int i = 0; i < left; i++) current[10]--;
            return;
        }
        //current[idx]에 0개부터 n개까지 넣기d
        for (int i = 0; i <= left; i++) {
            current[idx] = i;
            backtrack(current, idx + 1, left - i);
            current[idx] = 0;
        }
    }
    public void calculate(int[] current) {
        int apeach = 0;
        int lion = 0;
        for (int i = 0; i < current.length; i++) {
            //둘다0이면 패스
            if (current[i] == 0 && info[i] == 0) continue;
            
            if (current[i] > info[i]) {
                lion += (10 - i);
            }else {
                apeach += (10 - i);
            }
        }
        
        if (apeach >= lion) return; //점수 더낮음 패스
        
        if(lion - apeach > best) { //점수갱신
            res = current.clone();
            best = lion - apeach;
        }else if(lion - apeach == best){ //점수차 동일
            for (int i = 10; i >= 0; i--) {
                if (current[i] == res[i]) continue;
                
                if (current[i] > res[i]) res = current.clone();
                else {
                    break; //이건 갱신안함
                }
            }
        }
    }
}
/**
어피치 어드밴티지: 1
    점수에대해 화살갯수 동일 -> 어피치 점수 
    최종점수 도잉ㄹ -> 어피치 승
    
라이언입장에서 n발을 잘 맞추ㅕ서 최대한 이득을 봐야한다..
그러면 뭐 그리디 dp 완탐 등이있을거같은데 조건
n 10이면.. 

아무튼 이기는 경우의수(과녁판 결과)를 배열로 만들어서 return
점수차이가 가장 큰 경우를 return한다.점수차이가 동일하다면 낮은점수를 더 많이맞춘거..흠<< 이거 머지

자 경우의수 게싼법으로 돌아와서
가성비로 따진다면(기댓값 한발당) 안되는 반레는.. 발당 가격이지 이게 딱떨어지는게 아니라그럼
소숫점 계산을 안해줘서 그럼.

그러면 그냥 완탐떄리는게 나음

10개의 칸에다가 n발을 어떻게 나눌지하는거임.. 그래서 배열을 들고다니고
마지막에 10발 다 채워넣고 점수계산..갱신.
*/