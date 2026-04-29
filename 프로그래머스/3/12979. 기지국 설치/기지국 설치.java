import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        //stations 심지어 정렬이다.. ㅋ
        //일단 앞에 되게끔하고
        int coverWidth = 2 * w + 1;
        int cnt = 0;
        int lastCover = 0;
        for (int s : stations) {
            //System.out.printf("===start APT%d===\n", s);
            int coverFrom = s - w;//번 아파트부터 커버..이거 1index임
            //lastcover 다음부터 covefrom -1까지 숫자센다음에 계산
            //1 2
            int numApt = coverFrom - lastCover - 1;
            cnt += ((numApt + coverWidth - 1) / coverWidth);
            //2 + 3 - 1 / 3
            lastCover = s + w;
            //System.out.printf("put %d\n",((numApt + coverWidth - 1) / coverWidth));
            //System.out.printf("===end APT%d===\n", s);
        }
        int numApt = n - lastCover;
        cnt += ((numApt + coverWidth - 1) / coverWidth);
        return cnt;
    }
}
/**
N 2억..O(N)하거나 O(LOGN).
이제는 그냥 보이네이런류는 그냥.. 그 어차피해야하니까 그리디인거지..
1 2에 몇개필요? 1개

*/