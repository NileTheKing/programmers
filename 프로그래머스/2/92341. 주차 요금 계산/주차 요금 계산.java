import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> inTime = new HashMap<>();
        Map<Integer, Integer> totalTime = new HashMap<>();
        for (int i = 0; i < records.length; i++) {
            String[] parts = records[i].split(" ");
            
            String time = parts[0];
            String[] timeParts = time.split(":");
            int toMinute = Integer.parseInt(timeParts[0]) * 60 + 
                    Integer.parseInt(timeParts[1]);
            
            int plate = Integer.parseInt(parts[1]);
            String inout = parts[2];
            
            if (inout.equals("IN")) {//입차
                inTime.put(plate, toMinute);
                
            }else{ //출차 -> 누적시간
                int timeDiff = toMinute - inTime.get(plate);
                inTime.remove(plate);
                totalTime.put(plate, totalTime.getOrDefault(plate, 0) + timeDiff);
                
            }
        }//out이 따로 안찍혔다면?
        
        int end = 23*60 + 59;
        for (int car : inTime.keySet()) {
            int diff = end - inTime.get(car);
            totalTime.put(car, totalTime.getOrDefault(car, 0) + diff);
        }
        
        List<Integer> keys = new ArrayList<>(totalTime.keySet());
        Collections.sort(keys);
        
        int[] ans = new int[keys.size()];
        int idx = 0;
        for (int num : keys) {
            int fee = calculate(totalTime.get(num), fees);
            ans[idx++] = fee;
        }
        return ans;
        
        
        
    }
    public int calculate(int timeStacked, int[] fees) {
        //올림처리
        //일단ㄷ 두개 뺴가지고 몇분차이인지 구한다
        int sum = 0; //<< 리턴할값
        int timeLeft = timeStacked - fees[0];
        sum += fees[1]; //기본요금
        
        if (timeLeft <= 0) return sum;
        sum += ((timeLeft + fees[2] - 1) / fees[2]) * fees[3];
        return sum;
    }
}
/**
그냥 조지면될듯한디 n^2으로 어차피 길이 1000이니까
10^6이면 귀엽지
아 근데 차량번호 작은 순으로 해야하노.
음.. map만들어두고.. key정렬해가지고 순회하면서 출력하면되겠네
*/