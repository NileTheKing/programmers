import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        Set<String> target = new HashSet<>();
        for (String g : gems) target.add(g);
        
        //substring. 근데 현재상태 보관할거 필요. hashmap으로 관리하다가 set이랑key비교
        Map<String, Integer> current = new HashMap<>();
        int l = 0;
        int[] ans = new int[2];
        int minLength = Integer.MAX_VALUE;
        for (int r = 0; r < gems.length; r++) {
            //일단 확장하고 줄일수있을떄까지 축소
            current.put(gems[r], current.getOrDefault(gems[r], 0) + 1);
            //축소 및 갱신
            while (current.keySet().size() == target.size()) {
                //지금 current의 keyset이랑 target이랑 동일한지확인
                //되면 갱신하고 줄임.
                //비교해야햐ㅏ는데 딸깍 가능?그냥해라
                if (r - l + 1 < minLength) {//갱신
                    ans[0] = l + 1;
                    ans[1] = r + 1;
                    minLength = r - l + 1;
                }
                //l 줄이기
                current.put(gems[l], current.get(gems[l])- 1);//줄이기
                if (current.get(gems[l]) == 0) current.remove(gems[l]);//감소
                l++;
            }
        }
        return ans;
    }
}