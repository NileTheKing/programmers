import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> accum = new HashMap<>();//누적시간
        Map<String, Integer> parked = new HashMap<>(); //입차한 차들..<번호,시각>
        
        //accum 완성
        for (String r : records) {
            String[] parts = r.split(" ");
            int time = toMinute(parts[0]);
            String plate = parts[1];
            String cmd = parts[2];
            
            //cmd가 in이면 입차. 중복차량없으니 그냥입차하면됨. 입치map에 시각찍으면됨
            if (cmd.equals("IN")) {
                parked.put(plate, time);
            }
            //out이면 누적시간추가하고 set에서 제거. 누적시간은 입차한게 set가 map이여ㅑㅎ
            else {
                //in목록에서 지금시각time이랑 거기서얻은거랑 차이구하고 accum에 추가.in제거
                int diff = time - parked.get(plate);
                accum.put(plate, accum.getOrDefault(plate, 0) + diff);//시간 계산
                parked.remove(plate);//입차목록에서 제거
            }
        }
        //parked남은애들 2359로 계산
        for (Map.Entry<String, Integer> entry : parked.entrySet()) {
            int end = toMinute("23:59");
            int diff = end - entry.getValue();
            accum.put(entry.getKey(), accum.getOrDefault(entry.getKey(), 0) + diff);
        }
        //accum 돌면서 fees가지고 계싼
        List<Integer> ans = new ArrayList<>();
        List<String> keys = new ArrayList<>(accum.keySet());
        keys.sort(null);
        for (String key : keys) {
            int basetime = fees[0];
            int defaultfee = fees[1];
            int unittime = fees[2];
            int unitcost = fees[3];
            
            int carParkedFor = accum.get(key);//해당 차 전체 주차시간
            int leftover = carParkedFor - basetime < 0 ? 0 : carParkedFor - basetime;
            int total = defaultfee + (leftover + unittime - 1) / unittime * unitcost;
            ans.add(total);
        }
        
        return ans.stream().mapToInt(i->i).toArray();
        
    }
    public int toMinute(String str) {
        String[] parts = str.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}

/**
[문제이해]
fees에는 요금정보: 기본시간 기본요금 단위시간 단위요금
records는 정보.. 입차시각 번호 내역

차량별로 누적시간구해서 정산.
[조건]
1. 출차없으면 23:59기준
2. 차량번호 작은순으로 리턴
*/