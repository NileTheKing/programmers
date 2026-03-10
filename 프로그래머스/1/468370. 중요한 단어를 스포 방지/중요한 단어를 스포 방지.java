import java.util.*;
class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
    
        Set<String> notImportant = new HashSet<>();
        int s = 0;
        while (s < message.length()) { //중요하지 않은 단어 찾기
            int t = s;
            while (t < message.length() && message.charAt(t) != ' ') {
                t++;
            } //멈춘순간 t는 공백좌표임
            //그러면 실제로 s부터 t-1까지가 단어의 인덱스
            //지금 보는 단어가 이미 안중요한 목록에 이씅면 무조건 안중요. 근데 없다면 스포방지인지 확인
            //맞다면 중요한거임..
            boolean toBeSaved = true;
            if (notImportant.contains(message.substring(s,t))) toBeSaved = false;
            else {
                for (int[] r : spoiler_ranges) { //해당 단어가 스포방지 단어인지 확인.이거면 일단
                //단어시작과 끝 그리고 spoiler_range.. 3 7 / 2 5라면?하나라도 포함인디.
                    if (isIn(s,t-1,r[0],r[1])) toBeSaved = false;
                }
            }
            if (toBeSaved) 
            {    
                //System.out.printf("%s saved\n", message.substring(s,t));
                notImportant.add(message.substring(s,t));
            }
            s = t + 1;
        }
        int cnt = 0;
        for (int[] spoiler : spoiler_ranges) {
            int l = spoiler[0];
            int r = spoiler[1];
            //System.out.printf("checking range [%d, %d]\n", l,r);
            while (l >= 0 && message.charAt(l) != ' ') {
                l--;//이제 공백만났을거야
            }
            while (r < message.length() && message.charAt(r) != ' ') {
                r++; //이제 공백만낫을거임
            }
            //System.out.printf("word check [%d, %d]\n", l+1,r);
            if (l + 1 > r) continue;
            String[] parts = message.substring(l+1, r).split(" ");
            for (String p : parts) {
                //System.out.printf("checking %s ", p);
                if (notImportant.contains(p)) continue; //얜 중요했떤 단어가 아님.
                else {//중요했떤 단어임. 캉누트하고.. 이제는 공개되었으니 세트에도 추가
                    notImportant.add(p);
                    cnt++;
                }
            }
        }
        return cnt;
        
        //spoiler_ranges 순회하면서 얘가 중요하지 않은애였는지.. 
        //그러고나서 기존 set에 추가.
    }
    public boolean isIn(int s1, int s2, int r1, int r2) {
        //r이 s를 포함. r1은 s1보다작고 r2는 s2보다큼
        if (s1 <= r2 && s2 >= r1) {
            return true;}
        
        return false;
    }
}
/**
단어들: 공백 구분
단어; 소문자와 숫자로만 구성
단어들이 여러개 있음. 스포방지 구간도 있음. 단어의 일부가 스포방지구간에 포함되어서 다 안가려지더라도 스포방지단어임
한 단어가 여러개 스포방지 구간에 들어갈수더있고, 스포방지구간하나에 여러 단어가 있을 수도 있따.
중요한단어: 스포방지단어 && 스포방지가 아닌 구간에 나오면 안됨(= 스포방지 구간에 걸쳐있ㄷ던 단어며, 그냥 쌩으로 노출되면 안됨. 걸려있으면 ok), 근데 공개되어버린 단어는 안됨. ^^ 여러단어가 동시에 공개된경우 왼ㅁ족부터<<이거 뭔소리인지는 모루곘음

어근데 스포방지 범위를 이제 키로 해가지고.. 거기에 해당하는 단어들을 가지고있어
range순회하면서 지워가지고 얘가 중요한 단어인지 확인?

그러면 일단..중요하지 않은 단어들을 보관하고 있는건 어떰? 이건 set
처음에 range에 포함되지 않는
is muzo here is a message는 중요하지 않지
그리고 here이 여기포함이니..

이거도 my number is and have your 이 중요X
o지웠는데 phone ->카운트1 이제 phone은 중요X?확인ㄱ 맞네 그러면 축





*/