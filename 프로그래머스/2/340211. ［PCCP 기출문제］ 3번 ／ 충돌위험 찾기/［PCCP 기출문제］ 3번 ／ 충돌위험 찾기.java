import java.util.*;
class Solution {

    public int solution(int[][] points, int[][] routes) {
        int numOfRobots=  routes.length;
        int ans = 0;
        
        Status[] arr = new Status[numOfRobots];//로봇 갯수만큼 상태 관리
        int idx = 0;//1번로봇 
        for (int[] route : routes) {
            Status info = new Status();
            info.pos = points[route[0] - 1].clone();
            info.routeIdx = 0;
            info.route = route;
            if (route.length > 1) {
                info.destination = points[route[1] - 1].clone();
                info.hasArrived = false;
            } else {
                info.destination = info.pos.clone(); // 경로가 하나뿐이면 시작과 동시에 도착
                info.hasArrived = true;
            }
            arr[idx++] = info;
        }

        // 초기(0초) 위치에서 겹치는 경우 세기
        Map<String, Integer> initMap = new HashMap<>();
        for (Status info : arr) {
            if (info.hasArrived) continue; // 이미 끝난 로봇은 제외
            String strPos = info.pos[0] + "," + info.pos[1];
            int prev = initMap.getOrDefault(strPos, 0);
            if (prev == 1) ans++; // 같은 칸에 두 번째 로봇이 들어오는 순간만 +1
            initMap.put(strPos, prev + 1);
        }

        while (true) {
            boolean anyActive = false;

            // 이동 수행
            for (Status info : arr) {
                if (info.hasArrived) continue;

                // 현재 목적지에 이미 서있다면 다음 목적지로 갱신 또는 완료 처리
                if (Arrays.equals(info.pos, info.destination)) {
                    info.routeIdx++; // 이제 routeIdx는 '현재 위치' 인덱스가 됨
                    // 마지막 포인트에 도달했으면 완료 처리
                    if (info.routeIdx >= info.route.length - 1) {
                        info.hasArrived = true;
                        continue;
                    } else {
                        // 다음 목적지는 routeIdx + 1 를 참조해야 함
                        info.destination = points[ info.route[ info.routeIdx + 1 ] - 1 ].clone();
                    }
                }
                
                // 한 칸 이동 (r 먼저, 그 다음 c)
                if (info.pos[0] != info.destination[0]) {
                    info.pos[0] += Integer.compare(info.destination[0], info.pos[0]);
                } else if (info.pos[1] != info.destination[1]) {
                    info.pos[1] += Integer.compare(info.destination[1], info.pos[1]);
                }
            }

            // 이동 후, "다음 턴에도 남아있을(도착하지 않은) 로봇들" 만 카운트
            Map<String, Integer> temp = new HashMap<>();
            for (Status info : arr) {
                if (info.hasArrived) continue; // 도착했으면 더 이상 카운트 대상 아님
                anyActive = true;
                String strPos = info.pos[0] + "," + info.pos[1];
                int prev = temp.getOrDefault(strPos, 0);
                if (prev == 1) ans++; // prev==1 인 순간에만 +1 (==2가 되는 순간)
                temp.put(strPos, prev + 1);
            }

            // 모든 로봇이 완료되어 더 이상 움직이지 않는다면 종료
            if (!anyActive) return ans;
        }             
    }

    public class Status {
        int[] pos;
        int routeIdx = 0; // 현재 route의 인덱스 (pos가 route[routeIdx]에 해당)
        int[] route; // 경로 포인트 번호들
        int[] destination; // 현재 목표 좌표 (points[...] 형태)
        boolean hasArrived;
    }
}
