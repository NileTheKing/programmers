import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int totalUser = stages.length;
        int[] diff = new int[N + 1 + 1 + 1];// 스테이지번호 1인덱스라서 +1 그리고 조건N+1.누적합용 1도 필요함 바로아래땜시.
        for (int s : stages) {
            diff[1]++;
            diff[s + 1]--;//누적합
        }
        for (int i = 1; i < diff.length - 1; i++) {
            diff[i] += diff[i - 1];
        }
        //이제 값넣은 배열을가지고 이 기준에따라 diff를정렬
        //총 도달인원을 알잖아 그러면 거기에 남아있는 애 배열만드는거임
        double[] left = new double[diff.length];
        for (int i = 1; i <= N; i++) {//1스테이지부터 N스테이지(마지막스테이지)
            //마지막엔 남은인원은 음.N+1이면 다성공한거임
            if (diff[i] == 0) left[i] = 0.0;//divided by 0
            else left[i] = (double)(diff[i] - diff[i + 1]) / diff[i];
        }
        List<Node> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(new Node(i, left[i]));
        }
        list.sort((a,b) -> Double.compare(b.ratio, a.ratio) != 0 ? Double.compare(b.ratio, a.ratio): a.idx - b.idx);
        List<Integer> ans = new ArrayList<>();
        for (Node n : list) ans.add(n.idx);
        
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    class Node {
        int idx;
        double ratio;
        Node (int idx, double ratio) {
            this.idx = idx;
            this.ratio = ratio;
        }
    }
}
/**
스테이지갯수500개에대해서 스테이지 정렬
유저수스테이지정보 .. 2 * 10 ^ 5
n^2하면 안된다.

각 스테이지에 대해 분모구하는법은 1번스테이지기준 1이상인 애가 8있고 1이랑 동일한 애는 1명있음
2번은 이제 총 7명이 이상이고 3명이 동일
3번은 4명이 이상이고 ..2명이 동일
...

이게 딱 보니ㅏㄲ stage를 n^2하는건안되고.. 정렬을 하래ㅣ\
약간 투포인터 냄새가? 누적합이나 뭐 이런것의 냄시가.. 반복작업이 있지않음?
그러면 음.. 실패율을 만들어놓고 정렬떄리는 것의 냄새가 난다.
그렇담..미리 정보를 담아둬야해
한번스캔하면서 1이상몇명 2이상 몇명 3이상몇명을 다 담아두는것이야.
그러면 1스테이지 멈춘애는 1이상몇명 - 2이상몇명 1이상몇명 !
그러면 배열을 만들어서요 500개짜리
stage돌면서 500배열에서 ++연산하면 될듯한데요 아 근데 애매한게 그걸 매번N번
그러면 시간복잡도는 200000 * 500 * 500인데 .. 2 * 10^5 * 25 * 10^4 -> 10^9
안됨

좀더 생각을 해봆디ㅏ
스테이지가 500개 유저의 스테이지 정보 2 * 10^5개
원하는것은 스테이지별로 도달유저수를 알아야함. 이거 완성하면 껌인데
2라는건 1이랑 2에 마킹해둬야하고 어 누적합인가..
그니가 1에 1마킹 3에 -1
그리고 오
그렇게한다면 stage순회하면서 마킹을해 그러고나면 이제 스테이지마다 사람수를 알게됨 정확히..
그다음에 계산.
그러면 20만 + 500
*/