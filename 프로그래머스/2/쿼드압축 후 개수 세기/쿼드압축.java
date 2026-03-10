import java.util.*;

class Solution {
    int cnt0 = 0;
    int cnt1 = 0;
    boolean[][] visited; // 이미 압축 완료된 칸을 마킹

    public int[] solution(int[][] arr) {
        int n = arr.length;
        visited = new boolean[n][n];
        // 1. 가장 큰 사이즈부터 줄여나가며 압축 (큰 영역을 우선적으로 먹음)
        // width는 n, n/2, n/4 ... 2 순서로 진행
        for (int width = n; width >= 2; width /= 2) {
            for (int i = 0; i < n; i += width) {
                for (int j = 0; j < n; j += width) {
                    
                    // 핵심: 해당 구역의 첫 칸이 이미 압축된 상태라면 이 영역은 볼 필요 없음
                    if (visited[i][j]) continue;

                    // 해당 width만큼의 정사각형이 모두 같은 숫자인지 확인
                    if (canCompress(arr, i, j, width)) {
                        if (arr[i][j] == 0) cnt0++;
                        else cnt1++;
                        
                        // 압축 성공했으므로 해당 영역 전체를 마킹하여 하위 검사에서 제외
                        markVisited(i, j, width);
                    }
                }
            }
        }
        // 2. width 2까지 다 돌았는데도 visited가 false인 애들은 압축이 안 된 1x1 단독 칸들임
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (arr[i][j] == 0) cnt0++;
                    else cnt1++;
                }
            }
        }

        // 문제 조건: [0의 개수, 1의 개수] 리턴
        return new int[] {cnt0, cnt1};
    }

    private boolean canCompress(int[][] arr, int r, int c, int width) {
        int val = arr[r][c];
        for (int i = r; i < r + width; i++) {
            for (int j = c; j < c + width; j++) {
                if (arr[i][j] != val) return false;
            }
        }
        return true;
    }

    private void markVisited(int r, int c, int width) {
        for (int i = r; i < r + width; i++) {
            for (int j = c; j < c + width; j++) {
                visited[i][j] = true;
            }
        }
    }
}
