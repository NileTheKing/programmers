import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            String[] parts1 = transform(o1);
            String[] parts2 = transform(o2);
            String head1 = parts1[0].toLowerCase();
            String head2 = parts2[0].toLowerCase();
            int number1 = Integer.parseInt(parts1[1]);
            int number2 = Integer.parseInt(parts2[1]);
            String tail1 = parts1[2];
            String tail2 = parts2[2];
            //head는 사전순.. compareToignoreCase
            if (head1.compareTo(head2) != 0) {
                return head1.compareTo(head2); //순서앞
            }else if (number1 != number2) { //숫자앞
                return number1 - number2;
            }else { 
                return 0;//들어온순. << 이게 들어온순이어야하는데...항상 
            }
            //head같으면 숫자.. 숫자이넫             
        });
        return files;
    }
    public String[] transform(String str) {//0 1 2로 만들어서 준다.
        String[] res = new String[3];
        //number찾는다
        //number 시작 number끝
        //일단 시작
        int start = 0;
        while (start < str.length() && !(str.charAt(start) - '0' >= 0 && str.charAt(start) -'0' <= 9)) {
            start++;
        }
        //start부터 숫자임.
        //이제 최대 4번 이동할떄까지..한정 숫자면 계속.. 그니까 end - start가 4까진 ㅇㅋ
        int end = start + 1;
        //안될떄까지 옮길거임. 거리가 5이든가 숫자가 아니든가. 그러면 end -1] 까지가 숫자임
        while (true) { //조건복잡하니까 내부로 옮김
            if (end >= str.length() || end - start >= 5 || 
                str.charAt(end) - '0' < 0 || str.charAt(end) - '0' > 9 ) break;
            end++;
        }
        //이제 substring start,end하면 딱맞음.
        res[0] = str.substring(0, start); //head
        res[1] = str.substring(start, end); //number
        res[2] = str.substring(end, str.length());//tail
        //System.out.printf("===start %s===\n", str);
        //for (String r : res) System.out.printf("%s ", r);
        //System.out.printf("\n===end %s===\n",str);
        return res;
        
    }
}