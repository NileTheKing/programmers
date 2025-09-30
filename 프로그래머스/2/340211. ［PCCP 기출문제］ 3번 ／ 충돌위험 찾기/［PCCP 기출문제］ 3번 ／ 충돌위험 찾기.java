import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        Info[] infos = new Info[routes.length]; //로봇의 대수만큼 관리
        //초기값 넣기 1번 2번 3번 로봇..
        for (int i = 0; i < routes.length; i++) {
           infos[i] = new Info(points[routes[i][0]-1][0] - 1, points[routes[i][0]-1][1] - 1, 1, false); //초기위치,다음위치인덱스,도착여부
        }
        int ans = 0;
        while (true) {
            //다 도착했는지 확인
            boolean arrived = true;
            for (Info i : infos) {
                if (!i.hasArrived) arrived = false;
            }
            if (arrived) return ans;
            
            //모든 로봇이 현재 충돌위치인지 확인하기
                //각 좌표 모두 해시맵에 추가해서 2이상인거 카운트
            Map<Coord, Integer> crash = new HashMap<>();
            for (int i = 0; i < infos.length; i++) {
                Info info = infos[i];
                if (info.hasArrived) continue;
                int currentR = info.r;
                int currentC = info.c;
                Coord coord = new  Coord(currentR, currentC);
                crash.put(coord, crash.getOrDefault(coord, 0) + 1);
            }
            int cnt = 0;
            for (int val : crash.values()) {
                if (val >= 2) cnt++;
            }
            //충돌지점의 갯수 세서 더하a기
            ans += cnt;
            //모든 로봇 이동시키기
                //계산
            for (int i = 0; i < infos.length; i++) {
                Info info = infos[i];
                if (info.hasArrived) continue;
                int[] next = points[routes[i][info.index]-1];
                if (info.r == next[0]-1 && info.c == next[1]-1) {
                    info.index++;
                }
                if (info.index == routes[i].length) info.hasArrived = true;
            } 
                //업데이트
            for (int i = 0; i < infos.length; i++) {
                Info info = infos[i];
                if (info.hasArrived) continue;
                //다음위치로 업데이트. 이미도착한거는 안봄
                int[] next = points[routes[i][info.index]-1];
                if (info.r != next[0]-1) info.r = next[0]-1 > info.r ? info.r + 1 : info.r - 1;
                else if(info.c != next[1]-1) info.c = next[1]-1 > info.c ? info.c + 1 : info.c - 1;
            }
        }

    }
    public class Info {
        int r;
        int c;
        int index; //다음에 어디로 가야하는지
        boolean hasArrived;
        
        public Info(int r, int c,int index, boolean hasArrived) {
            this.r = r;
            this.c = c;
            this.index = index;
            this.hasArrived = hasArrived;
        }
    }
    public class Coord {
        int r;
        int c;
        
        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return coord.r == r && coord.c == c;
        }
        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
/**
시뮬레이션을 돌린다
4개의 정보가 들어있다
각 정보에는 위치정보가 담겨있다(예를 들어 현재위치, 목표지점 그리고 도착여부. 방문상태는 필요없다 최단거리니까)

1. 클래스를 정의한다 Info 클래스
2. 시뮬레이션을 돌리면서 한칸씩움직인다.
    -1. 매번 좌표를 비교해서 충돌위험이 있는지 확인한다.
*/