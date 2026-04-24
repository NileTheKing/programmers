import java.util.*;
class Solution {
    int cnt = 0;
    String[][] relation;
    List<Integer> candidates = new ArrayList<>(); // 전역으로
    public int solution(String[][] relation) {
        this.relation = relation;
        backtrack(0, 0);//TODO.초기상태 만약 1000 이라면! 어케추출하지?
        return cnt;
    }
    public void backtrack(int bit, int idx) {
        if (idx == relation[0].length) {
            if (bit == 0) return;
            // System.out.println(bit);
            //켜진 스위치가지고 해당 인덱스에 해당하는걸로 키를 만들어서 중복확인
            Set<String> dup = new HashSet<>();
            List<Integer> candiIdx = new ArrayList<>();
            for (int i = 0; i < relation[0].length; i++) {
                //0번인덱스부터 확인.
                if ((bit & (1 << i)) != 0) candiIdx.add(i);
            }
            //candi로 
            //이제 여기있는 인덱스가 그거임
            for (String[] r : relation) {
                StringBuilder key = new StringBuilder();
                for (int i : candiIdx) {
                    key.append(r[i]);
                }
                if (dup.contains(key.toString())) return;//안됨
                dup.add(key.toString()); //완성된키를 추가
            }
            //여기까지왔다면 최소성테스트
            //지금 bit이 전체 candidates에 포함되는지
            for (int c : candidates) {
                if ((c & bit) == c) return;
            }
            //통과
            // System.out.println("candidate: " + bit);
            candidates.add(bit);
            cnt++;
            return;
        }
        //bit조절
        int nextBit = bit | (1 << idx); //현재에다가 킨거임
        backtrack(bit, idx + 1); //안킴
        backtrack(nextBit, idx  + 1);
    }
}
/**
음.. 일단 겹치는게 있으면 안된다는것.. 해시를 써야겠지 중복성.
그러면 경우의수가 
엄청 여러개지
예제에선 속성이 4개니까 4C1 + 4C2 +  4C3 + 4C4.
완탐을 해보는게 낫지.
흠,.. 그러면 
컬럼은  8개까지있구만. ㅇㅋ. 그러면 백트래킹가능..선택여부를 boolean[]이나 뭐 비트로 가지고있으면되겠따. 비트를 매개변수로 들고다니거나 아니면 처음부터 그냥 브루트포스로 8개의 스위치 상태를 다 시도해보는 방법도 존재..이거는 사실상 똑같은건가?
나ㅓ는 해본방법으로 먼저 해보려고.
row는 20.
그러면 완성된 키가지고 유저별로 완성하고 겹치는순간 컷.

완성된 숫자가 3이야.. 이걸 들고다니는거지.
이걸 다시 스위치로 해서 어느 인덱스랑 일치하는지 알려면..
3을 이진화하면 추출어ㅔ해
자만
0 0
1 1
2 10
3 11
4 100
*/