import java.util.*;
class Solution {
    int[][] extension;
    int m, n;
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;
        int extendedLength = n + 2 * (m - 1);
        extension = new int[extendedLength][extendedLength];
        //패딩남기고 복사..
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                extension[i + m - 1][j + m - 1] = lock[i][j];
            }
        }
        //패딩후 lock옮겨진 extensioin map
        // for (int i = 0; i < extension.length; i++) {
        //     for (int j = 0; j < extension.length; j++) {
        //         System.out.printf("%d ", extension[i][j]);
        //     }
        //     System.out.println();
        // }
        int[][][] fourDirection = getFour(key);
        
        for (int i = 0; i < 4; i++) {
            int[][] trying = fourDirection[i];
            //비벼보기
            if (possible(trying, extension)) return true;
        }
        return false;
    }
    public int[][][] getFour(int[][] key) {
        int[][][] res = new int[4][key.length][key.length];
        res[0] = key;
        for (int i = 1; i < 4; i++) {
            res[i] = rotate(res[i-1]);
        }
        return res;
    }
    public int[][] rotate(int[][] key) {
        int n = key.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {//변환전행
            for (int j = 0; j < n; j++) {
                res[n - 1 - j][i] = key[i][j];
            }
        }
        return res;
    }
    public boolean possible(int[][] key, int[][] extension) {
        //extension 0,0부터 끝까지 홈맞추기. 조건:돌기돌기XX
        int cnt = 0;
        for (int i = m-1; i < m-1+n; i++) {
            for (int j = m-1; j < m-1+n; j++) {
                if (extension[i][j] == 0) cnt++;
            }
        }
        //cnt는 지워야할거임.
        for (int i = 0; i < extension.length - (m - 1); i++) {
            for (int j = 0; j < extension.length - (m - 1); j++) {
                //i,j를 시작점으로해서 키를 놓음.
                boolean thisTime = true;
                int thisCnt = cnt;
                for (int k = 0; k < key.length; k++) {
                    for (int l = 0; l < key.length; l++) {
                        //
                        if (key[k][l] == 1 && extension[i+k][j+l] == 1) thisTime = false;
                        if (extension[i+k][j+l] == 0 && key[k][l] == 1) {
                            if (i+k >= m- 1 && i + k < n + m - 1 &&
                               j+l >= m- 1 && j+l < n + m - 1)
                                thisCnt--;
                        }
                        
                    }
                }
                if (thisTime && thisCnt == 0) return true;
            }
        }
        return false;
    }
}
/**
쉽다.
N <= M
회전가능. 돌기끼리 겹치면안됨. 그대신 벗어나게 이동가능(바깥족영역은 ㄱㅊ)
//회전해서 다 비벼보고 반복. 4번 비벼보면 됨. 움직이는건 음. 구현해야지.
우선은 key를 상대좌표로 만들어서 회전가능한 4개상태만들어
긜고 비벼보는ㄷ ㅔ비비는거는 바깥쪽영역까지 고려해야 하니까 몇칸을 더 여유롭게 해서 움직여야함
몇칸?위로 길이-1만큼 아래로 길이-1만큼더 왼쪽으로 길이-1만큼 오른쪽으로 길이-1만ㅋ큼.\
상대좌표있는거있잖아 그거를 이제 확장된 lock에서 이제 배치시켜보는거지.

상대좌표 흠. 일단 lock은확장해서 만들었고 여기서 이제 회전한 4가지 경우의수 다
비벼보는거임. key자체가 애초에 지금 절대좌표로 되어있음.그래서
4가지 int[][][4]를 만들면된다.
4가지 상태으를  lock에다가 들고가면됨. 말도되고 코드도 무족너됨.구현ㅇ영역
*/