import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) { //1 2 3 4 5 6 7 8 9
            list.add(new HashSet<>());
        }
        
        list.get(1).add(N);// N하나 써서 만들 수 있는건 1밖에 없음
        
        //N의 갯수별로 만들 수 있는 숫자들 정하기
        //ex N 2개가지고 만들 수 있느 조합
        for (int i = 2; i < 9; i++) {
            Set<Integer> subList = list.get(i);
            //지금은 이제 N 2개로 만들 수 있는 조합들 보는것
            for (int j = 1; j < i; j++) {
                //두개로 나눠서 확인할거임. 모든 것은 두개의 조합으로 크게 볼 수 있음. 
                Set<Integer> firstSet = list.get(j);
                Set<Integer> secondSet = list.get(i - j); //N이 j개조합 + N이 i-j해서 i개에 대한 조합 갯수를 찾음
                for (int firstNum : firstSet) {
                    for (int secondNum : secondSet) {
                        subList.add(firstNum + secondNum);
                        subList.add(firstNum - secondNum);
                        subList.add(firstNum * secondNum);
                        if (secondNum != 0)
                        {
                            subList.add(firstNum / secondNum);
                        }
                    }
                }        
            }
            subList.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }
        for (int i = 1; i < 9; i++) {
                if (list.get(i).contains(number)) {
                    return i;
                }
        }
        return -1;
    }
}
/**
그니까 갯수가지고 접근한다 이겁니다
N을 1개가지고 만들 수 있는 조합
2개 3개. .... 9개 가지고 만들 수 있는 조합들을 함
얘네는 무조건 최소고 여기 목록에 있는 것은 만들 수 있고 최소인 것.
    
*/