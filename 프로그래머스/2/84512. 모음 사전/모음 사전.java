class Solution {
    private int cnt = 0;
    private String[] vowels = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        
        for (String vowel : vowels) {
            if(search(word, new StringBuilder(vowel))){
                return cnt;
            }
        }
        
        return cnt;
    }
    public boolean search(String word, StringBuilder current) {
        
        cnt++;
        
        if(current.toString().equals(word)) {
            return true;
        }
        if (current.length() >= 5) {
            return false;
        }
        
        for (String vowel : vowels) {
            current.append(vowel);// current: AAAA, vowel:A, E, I, O, U
            if(search(word, current)) {
                return true;
            }
            current.setLength(current.length() - 1);
        }
        
        return false;
    }

}
/**
그러면 숫자로 접근하는건아님. 
현재 추적중인(카운트 중)인 문자열과 대상을 비교해서 하나씩 계속 추적함
비교는 compareTo를 통해서 함
하나씩 어떻게 올리나?흠 오 그 
A
AA
AAA
AAAA
AAAAA
AAAAE
AAAAI
AAAAO
AAAAU
AAAE
AAAEA
AAAEE
AAAEI
AAAEO
AAAEU
AAAI
AAAIA
AAAIE
AAAII
AAAIO
AAAIU
AAAO
AAAOI
*/