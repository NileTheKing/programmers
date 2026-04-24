import java.util.*;
class Solution {
    int maxDiff = Integer.MIN_VALUE;
    int[] bestLion;
    public int[] solution(int n, int[] info) {
        bestLion = new int[info.length];
        backtrack(0, n, 0, info, new int[info.length]);//TODO
        
        return maxDiff > 1 ? bestLion : new int[] {-1};
    }
    public void backtrack(int idx, int n, int numArrow, int[] info, int[] currentLion) {

        if (idx == info.length) {
            //점수차이 계산
            //화살남았으면 다 씍
            currentLion[10] += (n - numArrow);//backtrack!
            
            int lionScore = 0;
            int apeachScore = 0;
            for (int i = 0; i <= 10; i++) {
                if (info[i] == currentLion[i] && info[i] == 0) continue;
                if (info[i] >= currentLion[i]) apeachScore += (10 - i);
                else lionScore += (10 - i);
            }
            int diff = lionScore - apeachScore;
            if (diff > maxDiff) {
                maxDiff = diff;
                bestLion = currentLion.clone();
            }else if (diff == maxDiff) {
                for (int i = 10; i >= 0; i--) {
                    if (bestLion[i] < currentLion[i]) {
                        bestLion = currentLion.clone();
                        break;
                    }
                    else if (bestLion[i] > currentLion[i]) {
                        break;
                    }
                }
            }
            currentLion[10] -= (n - numArrow);//backtrack!
            return;
        }
        //화살남았으면 이기기 //n른 전체 화살갯수
        if (n - numArrow >= info[idx] + 1) {
            currentLion[idx] = info[idx] + 1;
            backtrack(idx + 1, n, numArrow + currentLion[idx], info, currentLion);
            currentLion[idx] = 0;//복구
        }
        //그냥지기(가만히)
        backtrack(idx + 1, n, numArrow, info, currentLion);
    }
    
}
/**
어우 머리가 마른다
지금 암튼.. 그 종료조건을.. 인덱스 탐방 다한걸로 하는게..
탐방다하면 남은거 다 0점에 꼴박
지금은 갯수로 하는데 .. 갯수도 당연힞 ㅗ건이지만
갯수대로하면..음. 문제가있나
*/