import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        //정렬
        Arrays.sort(rocks);
        int start = 1;
        int end = distance;
        int answer = 0;
        
        //while
        while (start <= end) {
        int mid = start + (end - start) / 2;
        int rocksGotRid = 0;
        int lastRock = 0;
        
            for (int i = 0; i < rocks.length; i++)  {

                //돌을 확인하면서 지운다 가정하고 지울지 안지울지 정함
                if (rocks[i] - lastRock >= mid) { //큰거는 하용
                    lastRock = rocks[i];
                } else { //작으면 제거함
                    rocksGotRid++;
                    if (rocksGotRid > n) break; // 너무 많이 제거하면 중단
                }

            }
            if (distance - lastRock < mid) {
                rocksGotRid++;
            }
            //비교후 시도
            
            if (rocksGotRid > n) { //치워야할 돌이 너무 많았다면 욕심이었으니까 거리를 줄여라
                end = mid - 1;
            }
            else {
                answer = mid;
                start = mid + 1;
            }
        }
        
        return answer;
    }
}

/**
음..
일단 내 생각에는 정렬해놓고 생각하면 좋을듯
이거를 굳이 이진탐색으로 엮자면
2 11 14 17 21 25으로 만들어놓고(25는 부동)
인덱스를 2에관한 인덱스와 21에 관한 인덱스..아 결국 2랑 14를 제거하는 것이 제일 이득
거리의 최솟값이 가장 큰 것이 이득인 상황.
거리 기준으로 하면 2 9 3 3 4 4인 상황. 만약 9를지우면 그다음인 3이 9를 흡수해서 12가 만들어짐
그럼 n개 만큼 흡수시켜서 최소를 최대로 만들면 되겠네 => bullshit

이진탐색 할 수 있는 방법 찾아야지.
start mid end를 사용

===위 고민은 폐기
이분탐색을 무조건 인덱스 기준으로 할 필요는 없음
    :이전 문제도 그렇게 안풀었으니까 그렇네

그렇다면 거리를 기준으로 하는 것인데.. (왜냐하면 구하는 것이 거리의 최솟값임)
[2 11 14 17 21/ 25인 상황에서]
최대거리는 25-2인 23.
중간거리는 11임
이거를 충족시키는지 확인해보고 매번 거리를 갱신함. 그리고 탈출할 때 갯수를 리턴해야함
    (입국심사 문제에서 그랬듯이)


카운팅 로직을 구현해야하는데
일단 확인중인 길이가 있을 거 아니야
그래서 지우는 거 세면서 확인을 해봐야함
일단 갯수 상관없이 지워가면서 mid만큼의 거리를 만들 수 있는지 확인

*/