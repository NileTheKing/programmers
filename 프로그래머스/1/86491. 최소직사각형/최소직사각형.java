import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int width = Integer.MIN_VALUE;
        int height = Integer.MIN_VALUE;
        
        for (int[] size : sizes) {
            int tmp = size[0];
            if ( size[1] > size[0]) { //돌아가있으면 돌림
                size[0] = size[1];
                size[1] = tmp;
            }
            width = Math.max(width, size[0]);
            height = Math.max(height, size[1]);
        }
        answer = width * height;
        return answer;
    }
}