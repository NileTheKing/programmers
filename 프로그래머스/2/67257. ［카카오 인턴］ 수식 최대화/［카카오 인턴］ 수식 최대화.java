import java.util.*;
class Solution {
    public long solution(String expression) {
        char[][] priority = getPriority();

        long max = 0;
        for (char[] p : priority) {//모든 경우의수(우선순위)
            //각 우선순위에 대해 계산해보기
            List<Long> operand = new ArrayList<>();
            List<Character> operator = new ArrayList<>();
            int ptr = 0;
            int length = expression.length();
            //ptr를 숫자아닌곳까지 한다음에 숫자분리
            while (ptr < length) {
                int tmp = ptr;
                while (tmp < length && Character.isDigit(expression.charAt(tmp))) tmp++;
                //tmp는 연산자
                operand.add(Long.parseLong(expression.substring(ptr, tmp)));
                if (tmp < length)operator.add(expression.charAt(tmp));
                ptr = tmp + 1;
            }
            
            for (char op : p) { //우선순위에 맞게 계산
                int size = operator.size();
                for (int i = 0; i < size; i++) {
                    if (operator.get(i) != op) continue;
                    long op1 = operand.get(i);
                    long op2 = operand.get(i + 1);
                    long val = calculate(op1, op2, op);
                    operator.remove(i);
                    operand.remove(i);
                    operand.remove(i);
                    operand.add(i, val);
                    //list가 변했으므로 조정
                    size--;
                    i--;
                }
            }
            //싹다 계산되고 하나남았음
            max = Math.max(Math.abs(operand.get(0)), max);
        }
        
        return max;
    }
    public char[][] getPriority() {
        char[][] res = new char[6][3];
        //* + -
        res[0] = new char[] {'*', '+', '-'};
        res[1] = new char[] {'*', '-', '+'};
        res[2] = new char[] {'+', '*', '-'};
        res[3] = new char[] {'+', '-', '*'};
        res[4] = new char[] {'-', '*', '+'};
        res[5] = new char[] {'-', '+', '*'};
        return res;
    }
    public long calculate(long op1, long op2, char operator) {
        switch(operator) {
            case '*':
                return op1 * op2;
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
        }
        return -1;
    }
}