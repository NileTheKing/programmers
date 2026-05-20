import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> account = new HashMap<>();
        Map<String, String> parentMap = new HashMap<>();
        //추천인(부모) 정보
        for (int i = 0; i < referral.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            account.put(enroll[i], 0);
        }
        //seller amount 순회하면서 갱신
        for (int i = 0; i < seller.length; i++) {
            //각 판매건마다.. 수익발생하고 본인가지고 부모한테 쭈우욱올라가는거임..근데 이게 재귀적인거
            
            int total = 100 * amount[i];
            String whoSold = seller[i];
            while (true) {
                if (whoSold.equals("-")) break;
                int toGive = total * 1 / 10;
                int mine = total - toGive;
                String parent = parentMap.get(whoSold);
                //내몫
                account.put(whoSold, account.getOrDefault(whoSold, 0) + mine);
                //부모몫챙기기
                if (toGive < 1)  { //근데 부모몫없으면 내가
                    //내가가지고 
                    account.put(whoSold, account.getOrDefault(whoSold, 0) + toGive);
                    break;
                }else {
                    total = toGive;
                    whoSold = parent;
                }
            }
        }
        int[] ans = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            ans[i] = account.get(enroll[i]);
        }
        return ans;
        
    }
}
/**
노드 10000
enroll referral 길이도잉ㄹ
seller는 판매정보 100000.. amount도 길이동일. 두개세트로 판매정보
그러면.. 정산을 해가지고 enroll순서대로하면서 얼마씩 벌었느지 리턴..
그러면 자료구조에 담고있어야하고.. map하면되겠음.getordefault
그리고 부모를 알고있어야함..그래서 음 이거는 string기반으로ㅓ 접근해야하니까(인덱스아니고) 맵
*/