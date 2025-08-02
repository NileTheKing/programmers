import java.util.*;
class Solution {
    Node head; // -1번
    Node tail; // n + 1번
    Node current;
    Stack<Node> deleted;
    public String solution(int n, int k, String[] cmd) {
        
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head; // head->firstNode -> .... -> lastNode -> tail -> head and so on
        deleted = new Stack<>();
        Node iter = head;
        for (int i = 0; i < n; i++) { //초기값 대입
            Node node = new Node(i);
            node.prev = iter;
            iter.next = node;
            iter = node;
        }
        iter.next = tail;
        tail.prev = iter;
        
        //현재 위치 설정 k
        iter = head;
        for (int i = 0; i < k + 1; i++) { //k가 2면 head->0->1->2 3번 움직여야함
            iter = iter.next;
        }
        current = iter; //current 설정 완료
        

        
        for (String str : cmd) {
            char c = str.charAt(0);
            //String number = str.replaceAll("[^\\d]", "");

            switch (c) {
                case 'U': 
                    moveUp(Integer.parseInt(str.substring(2,str.length())));//todo
                    break;
                case 'D':
                    moveDown(Integer.parseInt(str.substring(2,str.length())));
                    break;
                case 'C':
                    delete();//todo
                    break;
                case 'Z':
                    rollBack();//todo
                    break;
            }
        }
        //명령 수행 후 비교.. 어떻게?
        //비교해가지고 없는 key를 X로 마킹해서 리턴
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!deleted.isEmpty()) {
            sb.setCharAt(deleted.pop().key, 'X');
        }
        
        return sb.toString();
    }
    class Node {
        int key;
        //String val;
        Node next;
        Node prev;
        
        Node(int key) {
            this.key = key;
        }
    }
    public void moveUp(int step) {
        for (int i = 0; i < step; i++) {
            if (current.prev == head) break;
            current = current.prev;
        }
        
    }
    public void moveDown(int step) {
        for (int i = 0; i < step; i++) {
            if (current.next == tail) break;
            current = current.next;
        }
    }
    public void delete() {
        Node toDelete = current;
        deleted.push(toDelete);
        
        Node prev = toDelete.prev;
        Node next = toDelete.next;
        
        prev.next = next;
        next.prev = prev;
    
        
        // current 위치 갱신
        // 다음 노드가 tail이면 이전 노드로 이동, 아니면 다음 노드로 이동
        if (next == tail) {
            current = prev;
        } else {
            current = next;
        }
    }
    public void rollBack() {
        if (deleted.isEmpty()) return;
        Node back = deleted.pop();
        
        back.prev.next = back;
        back.next.prev = back;
    }
}