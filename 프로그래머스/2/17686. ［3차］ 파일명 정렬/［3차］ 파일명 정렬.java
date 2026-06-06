import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            String[] t1 = getTokens(o1);
            String[] t2 = getTokens(o2);
            String head1 = t1[0].toLowerCase();
            String head2 = t2[0].toLowerCase();
            int number1 = Integer.parseInt(t1[1]);
            int number2 = Integer.parseInt(t2[1]);
            
            if (head1.compareTo(head2) != 0) {
                return head1.compareTo(head2);
            }else {
                if (number1 == number2) {
                    return 0;
                }else {
                    return number1 - number2;
                }
            }
        });
        return files;
    }
    public String[] getTokens(String file) {
        int l = 0;
        int r = 0;
        String[] tokens = new String[3];
        //head
        while (l < file.length() && !Character.isDigit(file.charAt(l))) {
            l++;
        }
        tokens[0] = file.substring(0, l);
        //number
        r = l;
        //최대5글자
        while (r - l + 1 <= 5 && r < file.length() && Character.isDigit(file.charAt(r))) {
            r++;
        }
        tokens[1] = file.substring(l,r);
        //tail
        tokens[2] = file.substring(r, file.length());
        //debug
        // System.out.printf("===%s===\n", file);
        // for(String t : tokens) System.out.println(t);
        return tokens;
    }
}