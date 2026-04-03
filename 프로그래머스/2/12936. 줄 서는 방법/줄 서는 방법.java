import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        long[] factorial = new long[n + 1];
        List<Integer> numbers = new ArrayList<>();
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }
        
        k--; //0index
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            //한자리씩구하기
            long fact = factorial[n - 1 - i];
            int idx = (int)(k / fact);//그룹에 해당하는 숫자
            k %= fact; //다음에는 문제가 더 작아짐
            ans[i] = numbers.get(idx);
            numbers.remove(idx);//그룹에 해당하는 숫자(3) 씀.지우기
        }
        return ans;
    }
}
/**
규칙:
맨앞자리가 같은걸로 시작하는것들은 n-1팩토리얼만큼있다
그러면 5번째 순열구하려면
우선 첫자리는..몇번째 그룹이냐면
5번째니께... 3번쨰그룹이야. 근데 이거를 수학적으로 옮기면
그룹마다 몇개가 있는질 알아야 그룹수를 알 수 있찌
그룹마다 몇개있는지는 n-1팩토리얼. -> 2개
그래 그룹마다2개니까 5번째 순열의 첫번재 자리수 그룹은 5/2 + 1 그룹
이제 3으로 시작하는걸 알았음. 3으로 시작하는 그룹중에 가장 첫번쨰 그룹임. 왜?
5%2 가 1이니까. 
그루면 이제 3으로 시작하는애들 중 첫번쨰 그룹을 알면됨.
이제 각 그룹당 1팩토리얼이니가 각 그룹은 1묶음
첫번째 그룹이니까 3 1
마지막은 2. 이제 이거를 수학적으로 옮기면됨.
흠.....

*/