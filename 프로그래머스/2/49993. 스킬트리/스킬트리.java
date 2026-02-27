import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        //각 스킬마다 필요한 선행스킬 목록들 저장함
        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 1; i < skill.length(); i++) {
            Set<Character> added = new HashSet<>();
            for (int j = 0; j < i; j++) {
                added.add(skill.charAt(j));
            }
            map.put(skill.charAt(i), added);
        }
        
        int cnt = 0;
        Outter : for (String s : skill_trees) {
            //System.out.printf("checking %s\n", s);
            Set<Character> current = new HashSet<>();
            for (char c  :s.toCharArray()) {
                //c는 관리해야하는 건지?
                //맞다면 set에 그 목록이 다 있는지
                if (!map.containsKey(c)) {
                    //System.out.printf("skilltreee %s doesnt need to check %c\n", s,c);
                    current.add(c);
                    continue; //해당스킬트리의 다음 스킬검사  
                } 
                else {
                    //c가 필요한 애들 다 불러와서 set이 가지고 있는지
                    for (char pre : map.get(c)) {
                        if (!current.contains(pre)) {
                            //System.out.printf("at skilltree %s, you need %c but no\n", s, pre);
                            continue Outter; 
                        }
                    }
                    current.add(c);
                }
            } //모든 스킬을 찍을 수 있으니 가능한 스킬트리
            //System.out.printf("this one good %s\n", s);
            cnt++;
        }
        return cnt;
    }
}
/**
트리/그래프 만들기

skill_trees순회
새로운 스킬찍을때마다
1. 검사해야 하는건지 아닌지 검사 검사해야하는 목록 map 의 keyset
2. 기존 에 존재하는 애들이랑 연관관계 검사
B를 찍는데 의존관계 있음. 그러면 이제 B의 에서 부모쪽으로 가봄. 근데 걔가 관리중인 set에 존재한다? 그럼 ok
안되는 경우를 생각해보면.. B배우는데 B부모중인 C가 부모에 있어야하는데 없음
BDA같은 경우는 B배우려고 하는데 set에 C가없네.
그니까 안되는경우는 1. 배우려고 하는 스킬의 이전 스킬이 set에 포함되어있찌 않으면 안됨


CBADF의 경우에는 C넣는데 ..
그러고 B넣는데 B넣으려고 보니까 B는 스킬트리가 있어야하는 거임. 그래서 B선행으로 C를 배워야하는데 C가 있네
A는 ㄱ냥넣고
D넣으려고 보니까 D의 선행은 cb인데 set에 둘다있음

그니까 이제 넣을떄마다 선행스킬이 뭐있는지 찾고 set에 그게 다 있으면 ok 아니면 실패


skilltress순회
    현재 테스트 중인 set관리
    하나씩 순회
        1. 얘가 선행스킬 필요X ->패스
        2. 필요X -> 선행스킬들 찾아오기.. 자료구조는?그래프나 트리? 음.. 일단 그거 다 찾아가지고 set에 있는지 확인

*/