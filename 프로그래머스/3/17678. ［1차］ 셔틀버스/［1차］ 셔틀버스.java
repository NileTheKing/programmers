import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] timed_timetable = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            timed_timetable[i] = timize(timetable[i]);
        }
        Arrays.sort(timed_timetable);
        
        //뭐해야함?
        //마지막 버스시간 구하기. 그다음에? 남은 경쟁자중 m번째 사람보다 1분빠르게
        //-> 이전까지 사람들 처리하고 남은 경쟁자시작 인덱스필요!
        //순회하면서 lastBusTime에 경쟁자 수 찾기: 이전에 간사람은 패스
        int lastBusTime = 9 * 60 + (n - 1) * t;
        int time = 9 * 60;
        int tableIdx = 0;//처리중인
        int cnt = 0;
        while (time < lastBusTime) { //버스로 사람태우기
            //이번 버스(time)에 m명 태우기
            // System.out.printf("===time : %d===\n", time);
            while (tableIdx < timed_timetable.length &&
                   timed_timetable[tableIdx] <= time 
                   && cnt < m) {
                // System.out.printf("in\n");
                cnt++;
                tableIdx++;
            }
            //다음버스
            cnt = 0;
            time += t;
        }
        //이제 idx부터 경쟁on. idx부터 m번째 사람(idx + m - 1)의 시간 -1
        //만약 막차가 비었다?(사람들 다탓음) || 제일빠른사람이 개느림 -> 막차
        // System.out.printf("tableIdx: %d\n", tableIdx);
        if (tableIdx >= timed_timetable.length || 
            timed_timetable[tableIdx] > lastBusTime ||
           timed_timetable.length - tableIdx < m) {
            return stringify(lastBusTime);
        }
        else {//경쟁자중에 m번째. 근데 m번재가 과하게빨리나왔다?그냥막차
            int idx = tableIdx + (m - 1) >= timed_timetable.length ? timed_timetable.length - 1 : tableIdx + (m - 1);
            return stringify(timed_timetable[idx] - 1);
        }
    }
    int timize(String str) {
        String[] parts = str.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    String stringify(int n) {
        int hour = n / 60;
        int min = n % 60;
        return String.format("%02d:%02d",hour, min);
    }
}
/**
timetable은 정렬안되어있음. 사람수 2000명 정렬하는거 일단 ㄱㅊ
했다치면 ..1번 예씨기준. 왜9시냐면 10시는 왜안됨? 아 셔틀이 9시에 오고 1분마다 1개밖에안옴.그니까 타야지

그러면 정렬하고, 이게 막차면 타는건데.. 막차기준은? 일단 예시 더 보자.

2번예시
0800 0909 0910//0900 0910//막차인데 자리가없어(경쟁자가 많아서) 그러면?
0910타려고하는데(막차) 경쟁자가있음.. 남은 경쟁자중 m번째보다 1분 빠르게

3번보자
900 900 900 900// 900 901 // 901타려는데? 못탐.. 그러면 859잖어.으음
암튼 음..막차가 빈다?막차에 타면됨

4번
0001 0001 0001 0001 0001 // 0900 // 0000
막차 0900임. 경쟁자 5명. 5번쨰(0001) 보다 1분빠르게 -> 0000
5번
2359 // 0900//막차가 0900. 경쟁자?없음 막차
6번
2359 2359 2359.....//0900 1000 1100 1200 ... 1800// 1800타야하니까 1800에
막차 1800 경쟁자없음 (1800보다 빨리온사람) -> 막차
=

===고민
while을 돌아야하나? 막차되기전까지 시뮬? 흠........idx랑?
지금은 사람돌면서..막차시간 추적.
*/