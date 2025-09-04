import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int lA = 0;
        int rA = A.length - 1;
        int lB = 0;
        int rB = B.length - 1;
        
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            if (B[lB] > A[rA]) {
                lB++;
                rA--;
                cnt++;
            }else if(B[lB] > A[lA]) {
                lB++;
                lA++;
                cnt++;
            }else {
                lB++;
                rA--;
            }
        }
        return cnt;
    }
}
// import java.util.*;
// class Solution {
//     public int solution(int[] A, int[] B) {
        
//         int n = A.length;
//         PriorityQueue<Integer> minpqA = new PriorityQueue<>();
//         PriorityQueue<Integer> maxpqA = new PriorityQueue<>(Collections.reverseOrder());
//         PriorityQueue<Integer> minpqB = new PriorityQueue<>();
//         PriorityQueue<Integer> maxpqB = new PriorityQueue<>(Collections.reverseOrder());
//         for (int i = 0; i < n; i++) {
//             minpqA.offer(A[i]);
//             maxpqA.offer(A[i]);
//             minpqB.offer(B[i]);
//             maxpqB.offer(B[i]);
//         }
        
//         int cnt = 0;
//         for (int i = 0; i < n; i++) {
//             int minA = minpqA.peek();
//             int maxA = maxpqA.peek();
//             int minB = minpqB.peek();
//             int maxB = maxpqB.peek();
            
//             if (minB > maxA) {
//                 int polledB = minpqB.poll();
//                 maxpqB.remove(polledB);
//                 int polledA = maxpqA.poll();
//                 minpqB.remove(polledA);
//                 cnt++;
//             }else if (minB > minA){
//                 int polledB = minpqB.poll();
//                 maxpqB.remove(polledB);
//                 int polledA = minpqA.poll();
//                 maxpqA.remove(polledA);
//                 cnt++;
//             }else { //무승부 처리 어떻게?. 무승부를 하는거랑 지는거랑 큰차이인가?무승부 할바에 지는게 낫지않나.
//                 //만약 B의 최소랑 A의 최대가 같다면 이미 처리될거고
//                 //B의 최소랑 A의 최소랑 같다면  어차피 B는 이길 애 없음. 그니까 가장 큰 애 데리고 가는게 낫나? 근데 일단
//                 //B의 최소가 A의최대보단 일단 작음. 
//                 //이제 남은경우는 minB가 minA보다 작은거랑 같은거임.
//                 //작으면 당연히 제일 큰애랑 붙어야하지
//                 //같아도 큰애랑 붙는게 낫지?
//                 int polledB = minpqB.poll();
//                 maxpqB.remove(polledB);
//                 int polledA = maxpqA.poll();
//                 minpqA.remove(polledA);
//             }
//         }
//         return cnt;
//     }
// }
// /**
// 이길땐 최소한으로 이기고
// 질 때는 아예 크게 지는게 좋음.
// 최솟값을 관리하고 최댓값도 관리해
// 음 근데 이길지 질지 어떻게 알까?
// 느낌상 최소힙 최대힙 관리하는건데
// A팀 네번째 팀원을 이기고 8로
// A팀 1 3 5 7
// B팀 2 2 6 8

// 1턴  
//     A: 1 3 5 7
//     B: 2 2 6 8
    
//     A1 B2
// 2턴
//     A: 3 5 7
//     B팀 2 6 8
    
//     B의 최소가 A최대 못이김. 
//         -> B최소가 A최소 못이김
//             -> B최소를 A최대랑 희생
    
// 3턴
//     A: 3 5
//     B: 6 8
//     B의최소 6이 3희생 8도 5랑 희생/.
    
// */