import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int A_left = 0;
        int A_right = A.length - 1;
        int B_left = 0;
        int B_right = B.length - 1;
        Arrays.sort(A);
        Arrays.sort(B);
        int ans = 0;
        while (B_left <= B_right && A_left <= A_right) {
            
            if (B[B_left] > A[A_right]) {
                ans++;
                B_left++;
                A_right--;
            }else if(B[B_left] > A[A_left]) {
                ans++;
                B_left++;
                A_left++;
            }else {
                A_right--;
                B_left++;
            }
        }
        return ans;
    }
}
/**
완탐이 아니라면야. 길이가 10만이니 완탐불가지 당연
그럼 그리디지
B팀기준
버릴카드(점수낮은)애는 데려가는거 시도하고 점수높은애는 최대한 아끼는게 좋음.
그러면 최대최소나 큐같은걸 그냥 정렬해서 인덱스

A :1 3 5 7
B :2 2 6 8 ?? 2로 7잡기시도. 실패 -> 제일 작은거 시도 ->성공.. 결국 써야하는데 그러면 잡는데 쓰는게 낫다 왜냐하면 더 큰거잡고 
어차피 써야함. 그러면 2입장은 제일큰애잡기 제일작은애 잡기 중간애 잡기인데
제일큰애안되면 포기. 제일작은애잡기 일단 ㅇㅋ어차피 누군가는 얠 이겨야하니까 내가 이기는게낫지
중간거 희생한다 / 그럴바에 제일큰애 희생하지 
그럼 제일큰애잡기시도 제일작은애잡기시도 안되면 큰애 희생\
그럼 큰놈은 뭐하나?
===
3 5
6 8 ?? 

==
3
8
*/