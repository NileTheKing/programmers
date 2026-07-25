import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> parentsMap = new HashMap<>();
        Map<String, Integer> wallet = new HashMap<>();
        //부모관계지정
        //referral 길이 enroll길이. referal[i]는 enrol[i]등록시킨사람
        for (int i = 0; i < enroll.length; i++) {
            parentsMap.put(enroll[i], referral[i]);
        }
        
        //seller길이=amount길이
        for (int i = 0; i < seller.length; i++) {
            String dealer = seller[i];
            int total = amount[i] * 100;
            // System.out.printf("==== i : %d====\n", i);
            //재귀
            //처음판애도 똑같이 재귀가능.대칭가능..초기값만 필요하지 대칭깰필요는없다
            //문제조건 절사한게 1원이면 자기가 다갖는대
            while (total > 0) {
                String parent = parentsMap.get(dealer);
                int toParent = total * 1 / 10;//1원절삭가능
                int mine = total - toParent;//부모가0원가져가면 다내거
                //정산
                // System.out.printf("dealer:%s, total:%d, parent:%s, toParent:%d, mine:%d\n", dealer, total, parent, toParent, mine);
                wallet.put(dealer, wallet.getOrDefault(dealer, 0) + mine);
                dealer = parent;
                total = toParent;
            }
        }
        int[] ans = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            if (wallet.get(name) == null) ans[i] = 0;
            else ans[i] = wallet.get(enroll[i]);
        }
        return ans;
    }
}