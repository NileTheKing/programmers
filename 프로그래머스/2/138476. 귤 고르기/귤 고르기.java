import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();//크기, 갯수
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        //크기는 필요가 없으니까 이제 갯수를 List로 따로 모아서 정렬한다음 하면 되네
        List<Integer> values = new ArrayList<>(map.values());
        values.sort((a, b) -> b - a); //내림차순 정렬
        
        int cnt = 0;
        int ans = 0;
        for (int num : values) {
            ans++;
            cnt += num;
            if (cnt >= k) return ans;
        }
        return -1;
    }
}
//import java.util.*;
// class Solution {
//     public int solution(int k, int[] tangerine) {
//         Map<Integer, Integer> map = new HashMap<>();//크기, 갯수
//         for (int t : tangerine) {
//             map.put(t, map.getOrDefault(t, 0) + 1);
//         }
//         //크기는 필요가 없으니까 이제 갯수를 List로 따로 모아서 정렬한다음 하면 되네
//         List<Integer> values = new ArrayList<>(map.values());
//         values.sort((a, b) -> a - b); //내림차순 정렬
        
//         //1개조합으로 가능
//         for (int i = 0; i < values.size(); i++) {
//             //System.out.printf("list: %d, target: %d", values.get(i), k);
//             if (values.get(i) >= k) return 1;
//         }
//         //i는 크기의 종류. 운안좋으면 8개 다른거 해야함. i 는 윈도우 크기.
//         for (int i = 2; i < k; i++) {
//             //list 순회. 내림차순임 얜
//             //슬라이딩윈도우 ㄱㄱ
//             //기본세팅한다음 움직이기
//             //System.out.printf("====start of checking %d kinds=====\n", i);
//             int windowSum = 0;
//             for (int j = 0; j < i; j++) {//윈도우 크기 i
//                 windowSum += values.get(j);
//                 //System.out.printf("adding %d at index %d.\n", values.get(j), j);
//             }
//             //System.out.printf("starting window sum: %d\n", windowSum);
//             //이젠 윈도우로 탐색. 현재 i는 2라 치고. window[0]이랑 window[1]있음.
//             //j는 1부터시작해서 끝인덱스 -1까지
//             //2는 됐고 i가 1이라면?흠.. 그냥 1따로 뺴자 ㅋ
//             for (int j = 1; j <= values.size() - i; j++) {
//                 //System.out.printf("j == %d\n", j);
//                 windowSum -= values.get(j - 1);
//                 windowSum += values.get(j + i  - 1);
//                 //System.out.printf("adding %d and substracting %d, ", values.get(j + i - 1), values.get(j -1));
//                 //System.out.printf("at index j = %d, current windowSum:%d at %d kinds\n", j, windowSum, i);
//                 //System.out.printf("sliding window loop. current starting window index: %d and it's working until j is %d\n", j, values.size() - i);
//                 if (windowSum >= k) return i;
//                 //if (windowSum > k) break;
//             }
//             //System.out.printf("====end of checking %d kinds=====\n", i);
            
//         }
//         return -1;
        
//     }
// }
// /**
// 뭐 크기가 큰 게 중요한 게 아니고 종류가 작아야함
// 그렇다면 해시맵으로 ㅋ크기와 빈도를 파악하는거지
// 그다음에 k개 담을건데 이거를 n개의 종류로 만들건데
// 1번째 크기의 갯수 2번째.. n번쨰 갯수 다헤ㅐ서 k개 만들거임.
// 그렇다면 밸류값더해서 k되는 것 중에 밸류수를 가장 적게 하는 것이 답이네.
// 일단 해시에 담고.. 순회? 해보자.
// 그다음에 1개로 되는지 2개로 되는지 확인인데
// 정렬하고 하면 편할듯? 갯수별로.
// */