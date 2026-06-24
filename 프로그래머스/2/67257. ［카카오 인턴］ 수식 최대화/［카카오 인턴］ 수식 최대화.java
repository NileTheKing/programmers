import java.util.*;
class Solution {
    public long solution(String expression) {
        long max = 0;
        //6가지 경우의수 순회
        char[][] candidates = getCandidates();
        //list로 분리해야하나? 만들어야한다. 근데 어떻게?
        List<String> list = getList(expression);

        for (char[] c : candidates) {
            List<String> copy = new ArrayList<>(list);
            // System.out.printf("====try====\n");
            for(char operator : c) {
                //이제 copy를 순회해야함근데 길이가 바뀜..
                int size = copy.size();
                for (int i = 0; i < size; i++) {
                    if (copy.get(i).charAt(0) == operator) {
                        long val1 = Long.parseLong(copy.get(i - 1));
                        long val2 = Long.parseLong(copy.get(i + 1));
                        long calc = calc(val1, val2, operator);
                        copy.remove(i - 1);
                        copy.remove(i - 1);
                        copy.remove(i - 1);
                        copy.add(i - 1, String.valueOf(calc));
                        i = i - 1;
                        size -= 2;
                    }
                }
                // System.out.println(copy);
            }
            max = Math.max(max, Math.abs(Long.parseLong(copy.get(0))));
        }
        return max;
    }
    public long calc(long n1, long n2, char operator) {
        if (operator == '+') {
            return n1 + n2;
        }else if(operator == '*') {
            return n1 * n2;
        }else {
            return n1 - n2;
        }
    }
    public List<String> getList(String str) {
        int ptr = 0;
        List<String> res = new ArrayList<>();
        while (ptr < str.length()) {
            int tmp = ptr;
            while (tmp < str.length() && Character.isDigit(str.charAt(tmp))) {
                tmp++;
            }
            res.add(str.substring(ptr, tmp));
            if (tmp < str.length())
                res.add(String.valueOf(str.charAt(tmp)));
            ptr = tmp + 1;
        }
        return res;
    }
    public char[][] getCandidates() {
        return new char[][] {
            {'+','-','*'},
            {'+','*','-'},
            {'-','+','*'},
            {'-','*','+'},
            {'*','-','+'},
            {'*','+','-'}
        };
    }
}
/**
6개 배열(경우의수 3!)
expression순회하면서 6가지 경우의수를 다 한다. 각 경우에 대해 최댓값갱신
각 경우는 우선순위에 맞는 수식찾으면 계산을 한다.
시간복잡도 O(n * 6 * 3 * n) ->가능
*/