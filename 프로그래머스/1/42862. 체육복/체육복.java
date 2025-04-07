import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
            
        //있는지 없는지 비교해야하므로 자료구조 필요. 리스트는 삭제가 오래걸리고 그러므로 해시사용
        Set<Integer> reserveSet = new HashSet<>();
        Set<Integer> lostSet = new HashSet<>();
        for (int r : reserve) {
            reserveSet.add(r);
        }
        for (int l : lost) {
            lostSet.add(l);
        }
        
        Set<Integer> intersections = new HashSet<>(reserveSet);
        intersections.retainAll(lostSet);
        for (int i : intersections) {
            reserveSet.remove(i);
            lostSet.remove(i);
        }
        
        //진짜 없던 애들을 가려야함
        for (int loser : new TreeSet<>(lostSet)) {
            int front = loser - 1;
            int behind = loser + 1;
            
            if (reserveSet.contains(front)) {
                reserveSet.remove(front);
                lostSet.remove(loser);
            }
            else if (reserveSet.contains(behind)) {
                reserveSet.remove(behind);
                lostSet.remove(loser);
            }
        }
        return n - lostSet.size();
    }
}

/**
어떻게 빌려주는 것이 가장 이득이냐?
    완탐
    그리디: 빌려줄 수 있는데 안빌려주는 경우가 더 나은 해가 있는 경우에는 그리디 사용불가. 그것이 아니면 이용 무조건 가능이지.

    1 2 3 4 5학생이 있고 2,4는 없고 1,3,5가 예비가 있다면
    1은 2한테 빌려주고 3은 4에게 빌려준다면 모두가 수업들을 수 있음
    
    1 2 3 4 5 학생이있고 2,4가 없고 3이 예비
    3은 일단 앞에놈 빌려주면 ㅇㅋ
    
    그러면 앞에서 안빌려주고 뒤에다가 아껴서 빌려주면 이득인 경우가 있냐 이건데
    어차피 앞뒤로밖에 못빌려주는 조건이 있어서 빌려줄 수 있으면 빌려줘야함. 만약 아꼈는데
    다음놈이 필요없으면 기회를 날리는것이잖아
    수학적..

*/