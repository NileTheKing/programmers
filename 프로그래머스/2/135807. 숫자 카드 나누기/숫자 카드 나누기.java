import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        //기준1로 최대값찾기
            //초기값은?음..시작값이 있어야하기때문에 초기설정다르게하지말고 걍 0부터
        Set<Integer> commonDivisorsA = new HashSet<>();
        for (int i = 2; i  <= arrayA[0]; i++) {//1억ㅇㅋ
            if (arrayA[0] % i == 0) commonDivisorsA.add(i);
        }
        List<Integer> initialDelA = new ArrayList<>();
        for (int c : commonDivisorsA) {
            if (arrayB[0] % c == 0) initialDelA.add(c);
        }
        for (int d : initialDelA) commonDivisorsA.remove(d);
        // for (int c : commonDivisors) System.out.println(c);
        for (int i =  1; i < arrayA.length; i++) {
            //commonDivisor랑 지금 보는 arryA숫자의 공배수 찾아서 list에더하기
            int numA = arrayA[i];
            int numB = arrayB[i];
            List<Integer> del = new ArrayList<>();
            for (int c : commonDivisorsA) {
                if (numA % c != 0 || numB % c == 0) del.add(c);
                // if (numB % c == 0) commonDivisorsA.remove(c); //B나누면컷
            }
            for (int d : del) commonDivisorsA.remove(d);
        }
        //기준2로 최댓값찾기
        Set<Integer> commonDivisorsB = new HashSet<>();
        for (int i = 2; i  <= arrayB[0]; i++) {//1억ㅇㅋ
            if (arrayB[0] % i == 0) commonDivisorsB.add(i);
        }
        List<Integer> initialDelB = new ArrayList<>();
        for (int c : commonDivisorsB) {
            if (arrayA[0] % c == 0) initialDelB.add(c);
        }
        for (int d : initialDelB) commonDivisorsB.remove(d);
        // for (int c : commonDivisors) System.out.println(c);
        for (int i =  1; i < arrayB.length; i++) {
            //commonDivisor랑 지금 보는 arryA숫자의 공배수 찾아서 list에더하기
            int numA = arrayA[i];
            int numB = arrayB[i];
            List<Integer> del = new ArrayList<>();
            for (int c : commonDivisorsB) {
                if (numA % c == 0 || numB % c != 0) del.add(c);//A나누면컷
                // if (numB % c != 0) commonDivisorsB.remove(c); //B못나누면컷
            }
            for (int d : del) commonDivisorsB.remove(d);
        }
        
        //
        //둘중 큰 것 리턴
        return Math.max(commonDivisorsA.isEmpty() ? 0 : Collections.max(commonDivisorsA), commonDivisorsB.isEmpty() ? 0 : Collections.max(commonDivisorsB));
    }
}
/**
철수카드들 500,000개.. 영희도
숫자는 1억이하롲 ㅓㄱ혀있다.
양의정수를 찾는건데.. 둘중하나면 그 숫자 리턴하면된다 근데 가장큰임
그러면? 1억까지 1씩 하는거는??음..아마터지지 왜냐면 영수500,000니까 카드가
고로 이분탐색으로 하는것일거다..
근데 음 AB 정렬해야하나? 모르곘다 하면 시작값 끝값찾기좋긴하거든?근데 굳이 뭐 그럴까싶은게
나누는거라 정렬이랑관련없어서1부터 1억다하면될듯
그러면 500,000 * logn이니까 8번이면됨.
그렇다면...하 어렵노 나눈다 나눗셈 나눗셈...음..
공배수도 떠오르고..
생각이안나네 그냥 수학문제아니냐

하나는 다 나눠야하고 하나는 다 나눌수없어야하는거임
그러면 아 후보군을 추려서 후보군 스캔하는건가?d
철수카드 스캔해가지고 공약수찾고..그 공약수랑 또 다음의 공약수를 찾고..
그래서 공약수가 나왔다면
예를들어 3번예시에서 arrayA의 경우 14 35의 공약수는 7.. 7과 119고 7
근데 음.. 최대공약수를해야하나 그냥 공약수 전체하면서 계속 늘려가야하나?
늘려가는게 맞겠죠..?왜냐면 공약수가 여러개있는데 최대가 된다는 보장이없어서요
아닌가? 예를들어 14가되었다쳐 그러면 14로나눠지는건 7로도 되지만 7로된다고 14가되는건아니니까
최대만찾는건아니구만.
*/