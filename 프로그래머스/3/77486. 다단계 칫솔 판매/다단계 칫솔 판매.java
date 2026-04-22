import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Map<String, String> referralMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            referralMap.put(enroll[i], referral[i]);
            // System.out.printf("<%s,%s>\n", enroll[i], referral[i]);
        }
        
        Map<String, Integer> resMap = new HashMap<>();
        for (int i = 0; i < seller.length; i++) {
            //판매하고 반영
            int total = amount[i] * 100;
            // System.out.printf("=====%s sold %d======\n", seller[i], total);
            //판매액중 90%는 자기가 가짐.1080
            resMap.put(seller[i], resMap.getOrDefault(seller[i], 0) + total - (total / 10));
            //나머지 10%는 edward으 수익발생
            total /= 10; //edward의 수익발생120
            String current = seller[i];
            while (total >= 1) { //수익전달할게(total/10) 없으면 끝
                
                current = referralMap.get(current);//이제 edward
                resMap.put(current, resMap.getOrDefault(current, 0) + total -(total / 10));           
                //90%는 내가 먹음.
                //이제 위로 수익전달.. 120중에 12전달
                total /= 10;
            }
            resMap.put(current, resMap.getOrDefault(current, total));
            // for (Map.Entry<String, Integer> entry : resMap.entrySet()) {
            //     // System.out.printf("<%s, %d>\n", entry.getKey(), entry.getValue());
            // }
            // System.out.printf("=====end of %s sold %d=====\n", seller[i], total);
        }
        //resMap -> int[] res를 enroll돌면서.
        int[] res = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            if (!resMap.containsKey(enroll[i])) {
                res[i] = 0;
                continue;   
            }   
            String name = enroll[i];
            res[i] = resMap.get(name);
        }
        return res;
    }
}
