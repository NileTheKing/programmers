import java.util.*;
class Solution {
    public long solution(String expression) {
        long max = 0;
        char[][] candidates = getCandidates();
        
        List<String> parsed = getParsedList(expression);
        for (char[] candi : candidates) { //우선순위시도
            //parse복사해서 candi순회하며 해당 연산자 계산.
            List<String> copied = new ArrayList<>(parsed);
            for (char operator : candi) {
                for (int i = 0; i < copied.size(); i++) {
                    if (copied.get(i).length() != 1) continue;
                    if (copied.get(i).charAt(0) != operator) continue;
                     //연산자
                    long n1 = Long.parseLong(copied.get(i - 1));
                    long n2 = Long.parseLong(copied.get(i + 1));
                    long number = getNumber(n1, n2, operator);
                    //100 - 200 * 300 - 500 + 20에서
                    //100 - 200이라치자.
                    copied.remove(i - 1);
                    copied.remove(i - 1);
                    copied.remove(i - 1); //다지움
                    copied.add(i - 1, String.valueOf(number));
                    //원래 i = 1이었음. 이제는 i그디로 봐야하는거아님?
                    i--;
                }
            }
            //모든 연산자 찾음.
            // for (String str : copied) System.out.println(str);
            // System.out.printf("=====\n");
            long val = calculate(copied);
            max = Math.max(val, max);
        }
        return max;
    }
    public long getNumber(long n1, long n2, char operator) {
        if (operator == '+') {
            return n1 + n2;
        }else if (operator == '-') {
            return n1 - n2;
        }else {
            return n1 * n2;
        }
    }
    
    public long calculate(List<String> list) {
        if (list.size() == 1) return Math.abs(Long.parseLong(list.get(0)));
        // if (list.size() != 3) System.out.printf("NOT 3\n");
        if (list.get(1).charAt(0) == '+') {
            return Math.abs(Long.parseLong(list.get(0)) + Long.parseLong(list.get(2)));
        }else if(list.get(1).charAt(0) == '-') {
            return Math.abs(Long.parseLong(list.get(0)) - Long.parseLong(list.get(2)));
        }else {
            return Math.abs(Long.parseLong(list.get(0)) * Long.parseLong(list.get(2)));
        }
        
    }
    public List<String> getParsedList(String expression) {
        List<String> res = new ArrayList<>();
        int ptr = 0;
        while (ptr < expression.length()) {
            int tmp = ptr + 1;
            while (tmp < expression.length() && Character.isDigit(expression.charAt(tmp))) {
                tmp++;
            }
            //이제 연산자임
            res.add(expression.substring(ptr, tmp));
            if (tmp < expression.length()) res.add(expression.substring(tmp, tmp + 1));
            ptr = tmp + 1;
        }
        return res;
    }
    public char[][] getCandidates() {
        return new char[][]{
            {'+','-','*'},
            {'+','*','-'},
            {'*','-','+'},
            {'*','+','-'},
            {'-','+','*'},
            {'-','*','+'}
        };
    }
}
/**
연산자3개존재. 우선순위 경우의수는 6개가능. bruteforce가능. 길이 100미만이니까..연산자가
operand + operator = 100
operator가 99가능? operand가1이니까 안되지
길이최대 대충50이려나.그러면 금방하지.. 100 * 6수준?

글우선수위정해두고
파싱해가지고 우선순위에맞는거 처리 -> 계산
비교
*/