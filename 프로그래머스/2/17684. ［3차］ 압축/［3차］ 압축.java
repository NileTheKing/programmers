import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        //초기화
        for (int i = 0;i < 26; i++) {
            char alpha = (char)('A' + i);
            dict.put(String.valueOf(alpha), i + 1);
        }
        int dictIdx = 27;//다음 애는 27쓰면 된다
        //순회
        int idx = 0;//처리중인 인덱스
        int len = msg.length();
        List<Integer> ans = new ArrayList<>();
        while (idx < len) {
            //idx부터 처리..
            //사전에서 안나올떄까지 계속 밀어. 밀다보면 idx-1까지 단어 사전에서차족
            //idx에있는 단어까지 한단어로 해서 또 추가..반복
            int tmp = idx + 1;
            //tmp는..len까지 상관없는거아님?
            //일단..될떄까지잖아. 결국 tmp는 불가능한 숫자일수도..
            while (tmp <= len && dict.containsKey(msg.substring(idx, tmp))) {
                tmp++;
            }
            //idx0 tmp1이라쳐.. 근데 AA까지있다쳐. 그럼 tmp1일떄ok 2일때 ok.. 
            //이제tmp3인데...되는건 tmp2임..
            //이제 [idx, tmp)까지는 있음. tmp+1부터는 안됨
                //색인 추가
            //tmp의 최악은 len + 1임.. 길이3인데 4가리키는거..
            //어차피 substring으로 [idx, tmp) 볼건데..
            //tmp는 될때까
            ans.add(dict.get(msg.substring(idx, tmp - 1)));
            // w+c를 dictIdx로 추가하면됨
            if (tmp + 1 <= len) //tmp+1 5고 길이가 5라면..당연히되어야지. 끝인덱스가4니까 ..tmp+1가len까지면 문제X
            {
                String wc = msg.substring(idx, tmp);
                //0 1 2 3에서.. 01ok 02ok.. 03인데..
                dict.put(wc, dictIdx++);//새롭게 추가.
            }
            idx = tmp - 1; //이제 tmp부터처리.
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}

/**
대문자 1000글자인 msg가온다.
압축후 색인..단순구현이라 뭐..
구현을 어케하나? 자료구조 가지고있고.. 이거는 hashmap써야지.인덱스가지고있고.
그러고 msg를 포인터가지고 순회하면됨. 마지막처리인덱스 가지고있고 매번 최대찾고 사전추가 무한반복
종료조건 명확.
*/