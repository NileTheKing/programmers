import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation : operations) {
            char command = operation.charAt(0);
            int operand = Integer.parseInt(operation.substring(2, operation.length()));
            //System.out.println("opearnd = " + operand);
            switch (command) {
                case 'I': //I이면 삽입
                    minHeap.offer(operand);
                    maxHeap.offer(operand);
                    break;
                case 'D': //D이면 지우는건데 -1이냐 1이냐에 분기
                    if (operand == -1 && !minHeap.isEmpty()) {
                        int removed = minHeap.poll();
                        maxHeap.remove(removed);
                        break;
                    }
                    else if (operand == 1 && !maxHeap.isEmpty()){
                        int removed = maxHeap.poll();
                        minHeap.remove(removed);
                        break;
                    }
            }
        }
        
        return minHeap.isEmpty() ? new int[] {0, 0} : new int[] {maxHeap.poll(), minHeap.poll()};
    }
}
/**
우선순위 큐가 두개 필요할 것 같음.

operations순회하며 연산을 처리하고
    최대힙 최소힙 만들고
    
    최소힙  16
    최대힙 5643
    
    -45 -642 45 

리턴
*/