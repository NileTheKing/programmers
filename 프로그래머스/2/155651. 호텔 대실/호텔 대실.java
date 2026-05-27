import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int[][] reservation = new int[book_time.length][2];//시작시간끝시간
        int idx = 0;
        for (String[] b : book_time) {
            String start = b[0];
            String end = b[1];
            //끝시간은 +10
            int convertedStart = toMinute(b[0]);
            int convertedEnd = toMinute(b[1]) + 10;//청소시간
            // System.out.printf("start %d end %d\n", convertedStart, convertedEnd);
            reservation[idx++] = new int[] {convertedStart, convertedEnd};
        }
        //이거 이제 정렬해서? < 음..필요한거같음 직관적으로 왜냐면 음..깔끔해지기때문인데 필요없을수도있음 조건이 널널하네 예약 1000개면 근데 나 ㄴ할거
        //i = 0 즉 앞에서 부터 시작할거면.. 끝점정렬해야해 시작점정렬해야해?
        //지금 생각나는건 만약 끝점정렬이면 끝점이 ㅡㄲㅌ나기전에 어차피 한발쏴야한다 근데 다른 시작하는 애가 그 전에 있다면? 같이 맞출수있음.
        Arrays.sort(reservation, (o1, o2) -> o1[1] - o2[1]);
        int max = 0;
        for (int i = 0; i < reservation.length; i++) {
            int cnt = 1;
            for (int j = i + 1; j < reservation.length; j++) {
                if (reservation[i][1] > reservation[j][0]) cnt++;
            }
            // System.out.printf("idx %d, cnt %d\n", i, cnt);
            max = Math.max(cnt, max);
        }
        return max;
    
    }
    public int toMinute(String str) {
        String[] parts = str.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
/**
잠만이게 시작시각 종료시각 이렇게 되어있고 음.. 여기서 정렬하냐안하냐인데 흠..
시간차트이런거니까 뭐 앞점정렬 이런거 해야하나?
야 이거 시각화하니까 딱보이네 ㅋㅋ 제일 꼬치 많이 겹치는 부분이다 ㅋ 쉽다..이걸 왜몰랐지?어 근데 이거를
이제 끝나고 10분 더 하는걸로 바꿔야함 시작과 끝을
*/