import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        int answer = 0;
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int lastRock = 0;
            int rocksGotRid = 0;
            for (int rock : rocks) {
                if (rock - lastRock < mid) { //거리가 너무 작으면 안됨.
                    //그래서 지금거 지우고 계속 이어가야함.
                    rocksGotRid++;
                    if (rocksGotRid > n) break;
                } else { //거리가 예상 보다 크다면 
                    //현재 걸로 바꿈
                    lastRock = rock;
                }
                
                
            }
            
            if (distance - lastRock < mid) {
                rocksGotRid++;   
            }
            
            if (rocksGotRid > n) {
                //너무 많이 지웠다면 욕심이었으니까(거리를 너무 크게 잡음) 거리를 좀 줄임
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        
        return answer;
    }
}