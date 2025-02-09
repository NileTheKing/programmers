import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> apq = new PriorityQueue<>();//aescending.기본
        PriorityQueue<Integer> dpq = new PriorityQueue<>(Collections.reverseOrder());//최대힙.
        
        for (String cmd : operations) {
            if (cmd.equals("D 1") && !apq.isEmpty()) {
                int a = dpq.poll();
                apq.remove(a);//dpq에서 최댓값뽑고 그거를 apq에서도 삭제
                //System.out.println(a + " has been removed");
                
            }
            else if (cmd.equals("D -1") && !apq.isEmpty()) {
                int a = apq.poll();
                dpq.remove(a);//dpq에서 최댓값뽑고 그거를 dpq에서도 삭제
                //System.out.println(a + " has been removed");
            }
            else if (cmd.charAt(0) == 'I'){//큐에 주어진 숫자를 삽입
                StringBuilder sb = new StringBuilder();
                for (char c : cmd.toCharArray()) {//명령어에서 숫자만 가져오기
                    if (Character.isDigit(c) || c == '-') {
                        sb.append(c);
                    }
                }
                int num = Integer.parseInt(sb.toString());
                //System.out.println(num + " has been put");
                apq.offer(num);
                dpq.offer(num);
            }
        }
        
        // 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
        if (apq.isEmpty()) {
            return new int[] {0, 0};
        } else {
            int min = apq.poll();//최솟값 뽑기
            int max = dpq.poll();
            answer[0] = max;
            answer[1] = min;//최댓값 뽑기
            return answer;
        }
    
        
    }
}