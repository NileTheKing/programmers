import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        
        Arrays.sort(files, (o1, o2) -> {
            String[] parts1 = seperate(o1);
            String[] parts2 = seperate(o2);
            
            int head = compareHead(parts1[0], parts2[0]);
            int number = compareNumber(parts1[1], parts2[1]);
            
            if (head != 0) {
                return head;
            }else if(number != 0) {
                return number;
            }else {
                return 0;
            }
            
        });
        return files;
    }
    
    public String[] seperate(String s) {
        
        String[] parts = new String[3];
        int length = s.length();
        //head는 숫자나오기전 문자
        int start = 0;
        int end = 1; //최소1글자
        while (end < length-1 && !Character.isDigit(s.charAt(end))) {
            end++;
        }            
        parts[0] = s.substring(start,end);//end가 안될떈 어차피 그거 빠짐
        //number는 연속된 숫자.최대5.
        //숫자인것들은 최대5개까지 세어버린다.
        start = end;
        while (end < length && Character.isDigit(s.charAt(end)) && (end-start + 1) <= 5)
        {
            //참이라면 계속 늘림
            end++;
            //근데 너무 멀리왔디(5) 그럼 멈춘다.
        }
        parts[1] = s.substring(start, end);        
        //tail은 나머지
        parts[2] = s.substring(end, length);
        
        // System.out.println("printing " + s);
        // for (String p : parts) {
        //     System.out.printf("%s ``", p);
        // }
        System.out.println();
        return parts;
    }
    public int compareHead(String s1, String s2) {
        String lower1 = s1.toLowerCase();
        String lower2 = s2.toLowerCase();
        return lower1.compareTo(lower2);
    }
    public int compareNumber(String s1, String s2) {
        return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
}