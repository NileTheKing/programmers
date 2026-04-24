import java.util.*;
class Solution {
    int[] best; //가입자, 매출
    int[][] users;
    int[] emoticons;
    int[] discountRate = {10,20,30,40};
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        best = new int[2];
        
        backtrack(0, new int[emoticons.length]); //TODO
        return best;
    }
    public void backtrack(int idx, int[] currentRate) {
        if (idx == emoticons.length) {//완성
            //할인율대로 시뮬레이션 이후 갱신
            int enroll = 0;//등록자
            int income = 0;//구매금액
            for (int[] u : users) {
                int paid = 0;//유저의 구매금액. 전환체크용
                for (int i = 0; i < emoticons.length; i++) {
                    //현재이모티콘 구매
                    int rate = discountRate[currentRate[i]];//i번째이모티콘
                    int discountedPrice = emoticons[i] * (100 - rate) / 100;
                    if (rate >= u[0]) paid += discountedPrice;//할인쎄면 삼
                    if (paid >= u[1]) {
                        //많이 사면 초기화해서 income에 0만큼더할거게씨
                        paid = 0;
                        enroll++;
                        break;//끝..임티더볼필요가 없음
                    }
                }
                income += paid;//0처리도됨. 가격반영
            }
            //enroll이 더 나으면 쌍으로 반영
            if (enroll > best[0]) {
                best[0] = enroll;
                best[1] = income;
            }else if(enroll == best[0]) {
                //같으면 비교
                if (income > best[1]) best[1] = income;
            }//나머진 ㅆㄹㄱ 바꾼거없음 ㅌㅌ
            
            return;
        }
        
        //현재 idx의 이모티콘 할인율을 픽스 idx 0 1 2 3쓰면
        currentRate[idx] = 0;
        backtrack(idx + 1, currentRate);
        //다른거로시도
        currentRate[idx] = 1;
        backtrack(idx + 1, currentRate);
        
        currentRate[idx] = 2;
        backtrack(idx + 1, currentRate);
        
        currentRate[idx] = 3;
        backtrack(idx + 1, currentRate);
    }
    
}
/**
직관적인 이해먼저
할인을 쎼게때리면 사겠지만 가입을 안할것
할인을 너무 약하게하면 아예 안살것..
사용자마다 기준도 다름. 그래서 각각 적당한 할인율을 해야 이제 구매를 하고 -> 전환하겠지
근데 애초에 가입여부가 빡빡한 사람은 안할거니까 적당히 구매만 유도해야할 수도 있음. 그러면 ! 할인율을 약하게 맞추면 .. 왜냐면 한며얘ㄸ문에 할인율 늘렸는데 더 널널한 애는 어차피 살건데 더 싸게 사는 걸수도있음. 핞명한테 맞춰서 희생했는데 가입조건 빡빡하면 ㅁ아함.
그래서 종합하면 뭐 상위호환ㅇ ㅣ런게 없어ㅓㅅ 완탐각임. 조건보자.
user n 100
emoticons m 7.
그냥 완탐 때려버려~
그러면 각 이모티콘별로 할인율을 완탐 . 각각 4개의 경우의 수가 있다..
그 경우에 대해 유저 100긁으니까..
할인 경우의수는 총 7^4 각각에 대해 100 -> 49^7 -> 343* 7 = 2401 * 100 0-> ez
근데 음 이거를 어케함?
할인율을 기준으로 할인율표를 다 고른다음에 마지막에 계산해서 갱신. 마지막에 리턴..어 어디서 본 바잇ㄱ
*/