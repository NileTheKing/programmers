import java.util.*;
class Solution {
    List<List<Integer>> combs = new ArrayList<>();
    int min = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        getCombs(k,n, new ArrayList<>());
        // for (List<Integer> c : combs) {
        //     for (int i : c) System.out.printf("%d ", i);
        //     System.out.println();
        // }
        helper(reqs);
        
        
        return min;
    }
    public void helper(int[][] reqs) {
        for (int i = 0; i < combs.size(); i++) { //조합 덩어리 ex 113
            int total = 0;
            for (int m = 0; m < combs.get(i).size(); m++) { //각 창구
                int changooNumber = m+1;
                int wait = 0;
                PriorityQueue<Integer> pq = new PriorityQueue<>(); //끝나는시간
                for (int j = 0; j < combs.get(i).get(m); j++) pq.offer(0); //사람수만큼
                for (int[] r :reqs) {
                    if (r[2] != changooNumber) continue;
                    if (pq.peek() <= r[0]) { //안기다림
                        pq.poll();
                        pq.offer(r[0] + r[1]);
                    }else {
                        int polled = pq.poll();
                        //기다리는거니까.. 
                        wait += polled-r[0];
                        pq.offer(polled + r[1]);
                    }
                }
                total+=wait;
            }
            min = Math.min(total,min);
        }
        
    }
    public void getCombs(int k, int n, List<Integer> current) {
        if (k == 1) {
            current.add(n);
            combs.add(new ArrayList<>(current));
            current.remove(current.size() - 1);
            return;
        }
        
        for (int i = 1; i <= n - k + 1; i++) {
            current.add(i);
            getCombs(k-1, n-i, current);
            current.remove(current.size() - 1);
        }
    }
    
}