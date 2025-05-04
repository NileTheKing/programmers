import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        int length = rocks.length;
        int left = 1;
        int right = distance;
        int ans = 0;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int cnt = 0;
            int prev = 0;
            //System.out.println("left, mid, right: " + left + " " + mid + " " + right);
            for (int i = 0; i < length; i++) {
                if (rocks[i] - prev >= mid) { //각 거리를 계산. 이것이 현재 가정중인 mid보다 더 작으면 안됨
                    //바위 안지워도 됨.
                    //System.out.println(rocks[i] - prev + ", " + mid);
                    prev = rocks[i];
                }
                else {//만약 mid를 밀어내고 최솟값이 된다면 얘를 없애야함
                    cnt++;//바위 지움
                }
                
                // if (cnt > n) { //조기종료
                //     break;
                // }
            }
            
            //마지막 돌이랑 도착지점도 확인
            if (distance - prev < mid) {
                cnt++;
            }
            
            
            if (cnt > n) {//n보다 더 많은 돌을 지워야 mid를 거리의 최소로 만들 수 있었으므로
                //mid가 너무 크면 rocks[i] - prev < mid면 지우는데 mid가 너무 크면 계쏙지움
                right = mid - 1;
            }else {
                ans = mid;
                left = mid + 1;
            }
            
        }
        
        return ans;
    }
}
/**

parametric search

구하고자 하는 것: 거리의 최솟값.
거리의 최솟값을 low high로 설정
매번 반복
조건에 위배되면 탈출
low high 조정

2 11 14 17 21

2 11 14 25
2 9 3 11

*/