import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int[][] reservation = new int[book_time.length][2];//시작시간끝시간
        int idx = 0;
        for (String[] b : book_time) {
            String start = b[0];
            String end = b[1];
            
            int convertedStart = toMinute(b[0]);
            int convertedEnd = toMinute(b[1]) + 10;//청소시간

            reservation[idx++] = new int[] {convertedStart, convertedEnd};
        }

        Arrays.sort(reservation, (o1, o2) -> o1[1] - o2[1]);
        int max = 0;
        for (int i = 0; i < reservation.length; i++) {
            int cnt = 1;
            for (int j = i + 1; j < reservation.length; j++) {
                if (reservation[i][1] > reservation[j][0]) cnt++;
            }
            max = Math.max(cnt, max);
        }
        return max;
    
    }
    public int toMinute(String str) {
        String[] parts = str.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}