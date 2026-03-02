import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String s : gems) set.add(s);
        
        Map<String,Integer> map = new HashMap<>();
        int l= 0;
        int[] ans = new int[2];
        int min = gems.length+1;
        for (int r = 0; r < gems.length; r++) {
            map.put(gems[r], map.getOrDefault(gems[r],0) + 1);
            //shrink작업.
                //모든 상품을 가지고 있을 수 있는 제일 오른쪽의 l을 구함
                //고로 [l,r] 상품의 모든 걸 가ㅣㅈ고 ㅣㅇㅆ어야함
                //l위치에서 가능했으면 우선 값 갱신때리고 l올려..근데 안되면?뭐 상관은 없음..이미 갱신됨
            //근데 다음번에서 l의 위치가 좀 그시기한게아닌가 싶으면서도 음..map은 따로관리하니까
            while (map.keySet().size() == set.size()) {
                if (r - l + 1 < min) {
                    ans[0] = l+1;
                    ans[1] = r + 1;
                    min = r - l + 1;
                }
                map.put(gems[l], map.get(gems[l]) - 1);
                if (map.get(gems[l]) == 0) map.remove(gems[l]);
                l++;//이전 l은 버림.. 이제 현재l부터 가지고있음.
            }
        }
        return ans;
    }
}
/**
총 몇개의 종류가 있는지 알아야함

그다음에..two pointer로 처리.

근데 two pointer하기엔.. 언제 left를 shrink하는거임..? 흠..일단 될때까지
아니면 brute force로 한다면
10^5^2 일텐데 10^10 이ㅐ면 ..100,000,000이 10^8인데 100억..절대 안됨


r은 가고
l은 될떄까지 줄여
너무줄여버리면안됨.
??
*/
