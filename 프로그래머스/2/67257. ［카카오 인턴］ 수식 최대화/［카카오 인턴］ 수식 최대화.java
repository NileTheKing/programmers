import java.util.*;
class Solution {
    public long solution(String expression) {
        char[][] priorities = getPriorities();
        List<String> operands = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        parse(operands, operators, expression);
        
        //<--->above checked
        long ans = Integer.MIN_VALUE;
        for (char[] pri : priorities) {//각우선순위에맞게 테스트
            List<String> copiedOperands = new ArrayList<>(operands);
            List<String> copiedOperators = new ArrayList<>(operators);
            for (char op : pri) { //순서에 맞는애 처리
                int size = copiedOperators.size();
                for (int i = 0; i < size; i++) {
                    if (op != copiedOperators.get(i).charAt(0)) continue;
                    String num1 = copiedOperands.get(i);
                    String num2 = copiedOperands.get(i+1);
                    String calculated = calculate(num1, num2, op);
                    copiedOperators.remove(i);
                    copiedOperands.remove(i);
                    copiedOperands.remove(i);
                    copiedOperands.add(i, calculated);
                    i--;
                    size--;
                }
            }
            //연산 끝난애 계산
            long val = Math.abs(Long.parseLong(copiedOperands.get(0)));
            ans = Math.max(val, ans);
        }
        return ans;
    }
    String calculate(String n1, String n2, char op) {
        if (op == '+') return String.valueOf(Long.parseLong(n1) + Long.parseLong(n2));
        if (op == '*') return String.valueOf(Long.parseLong(n1) * Long.parseLong(n2));
        if (op == '-') return String.valueOf(Long.parseLong(n1) - Long.parseLong(n2));
        return "";
    } 
    void parse(List<String> operands, List<String> operators, String expression) {
        int ptr = 0;
        int length = expression.length();
        while (ptr < length) {
            int tmp = ptr + 1;
            //숫자아닌거(연산자)나올떄까지 증가
            while (tmp < length && 
                   Character.isDigit(expression.charAt(tmp))){
                tmp++;   
            }
            //ptr tmp substring
            operands.add(expression.substring(ptr, tmp));
            //tmp는 이제 연산자
            if (tmp < length) operators.add(String.valueOf(expression.charAt(tmp)));
            ptr = tmp + 1;
        }
        return;
    }
    char[][] getPriorities() {
        return new char[][] {
            {'*','+','-'},
            {'*','-','+'},
            {'+','*','-'},
            {'+','-','*'},
            {'-','+','*'},
            {'-','*','+'},
        };
    }
}