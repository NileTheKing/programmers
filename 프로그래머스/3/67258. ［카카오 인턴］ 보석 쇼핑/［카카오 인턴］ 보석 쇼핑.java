import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        Set<String> all = new HashSet<>();
        for (String g : gems) all.add(g);
        
        int l = 0;
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        int min = gems.length + 1;
        int[] ans = new int[2];
        for(int i = 0; i < gems.length; i++) {
            int r = i; //r
            set.add(gems[r]);
            map.put(gems[r], map.getOrDefault(gems[r], 0) + 1);
            // System.out.printf("%d %d setsize %d\n", l+1, r+1, set.size());
            if (set.size() == all.size()) {
                //l줄이면서 체크
                // System.out.printf("entering to renew...\n%d %d setsize %d\n", l+1, r+1, set.size());
                while (l <= r) {
                    if (r - l + 1 < min) {
                        // System.out.printf("%d %d\n", l+1, r+1);
                        min = r - l + 1;
                        ans[0] = l + 1;
                        ans[1] = r + 1;
                    }
                    String lgem = gems[l];
                    map.put(lgem, map.get(lgem) - 1);
                    if (map.get(lgem) == 0) {
                        set.remove(lgem);
                        map.remove(lgem);
                    }
                    l++;
                    if (set.size() != all.size()) break;//이제 못줄임
                }
            }
        }
        return ans;
    }
}
/**gems 10^5. 즉 O(N^2) 안되고 
nlogn n중하나로..
투포인터쓰며노딜듯처음에 gems풀스캔해서 돌아다니고
gems를 투포인터로 순회하면서 연속구간이니까.. 5개 될떄가지 확장먼저하고
나중에 최대한 줄이는식으로 하면됨. 줄이기전에 매번 기록해놓고.. 안되면 다음턴에 또 늘리고..
그러면 음.. substring에 hashmap들고다녀야지. 자료구조가 필요하니까
지금 걸리는게 n^2잖아 그럴일은 없는데 이게 수학적으로
최악의 상황이 n(n - 1) / 2인데 l이 매번 0아니니까 ㄱㅊ.
근데 매번 set이랑 map비교하는 게 가능? 최악의경우 100000개 전부를 map의 key랑 비교해얗는데
하나 조회는 1이지만.. 풀스캔은 n이지.. 
...
이 상태를..음..흠..............
만약 계속 되는거만 들고다닌다면....
만약 초기값으로 딱 커트라인만 들고다녀봐..그러고 될떄까지 줄여봐
줄일때 0되면 안됨. 그게아니면 줄일수있음.
오?
자 다시.. 처음에 set풀스캔
그다음에 gems다시 스캔해서 되는순간을 찾아. 이거는 음..
n번 순회하며 매번 비교를한다면 
내부에서 1 2 3 4 5 6 .....최대 n 번 비교해야 초기값알 수 있는데??.. 
초기값을 안 상태로 윈도우 줄인다하더라도..문제가..

이게 슬라이딩윈도우에서 관점으로..뭐있었드라
일단 줄이면서 조건맞으럐ㄸ마다 갱신
일단 줄여야하고 조건맞으면 그떄가서 while밖에서 갱신..
 내가 뭔가에 홀려서 시간복잡도를 잘못계산해서 삽질중인가?
 그럼 음 set이랑 map둘다관리?? 줄일때는 map쓰고?
*/

