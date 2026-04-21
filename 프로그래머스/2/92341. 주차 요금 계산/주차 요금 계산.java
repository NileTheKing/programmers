import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> accum = new HashMap<>(); //<차번호,누적시간>
        Map<Integer, Integer> inTime = new HashMap<>(); //차번호, 입차시간(분으로)
        
        for (String r : records) {
            //입차면 입차
            //출차면 누적시간계산만.
            String[] parts = r.split(" ");
            int plate = Integer.parseInt(parts[1]);
            int time = toMinute(parts[0]);
            if (parts[2].equals("IN")) { //입차.. 같은번호X
                inTime.put(plate, time);
            }else {//출차. 누적시간계산. 주차목록엔 무조건있음.
                int in = inTime.get(plate);//이게무조건있다.
                //출차처리
                inTime.remove(plate);
                int timeDiff = time - in;
                accum.put(plate, accum.getOrDefault(plate, 0) + timeDiff);
            }
        }
        //출차안된거 누적시간계산
        for (int plate : inTime.keySet()) {
            accum.put(plate, accum.getOrDefault(plate, 0) + 23*60 + 59 - inTime.get(plate));//출차안된 번호판들의 누적시간을 계산. 출차시간은 2359라치고 
        }
        // for (int plate : accum.keySet()) {
        //     System.out.printf("(%d, %d)\n", plate, accum.get(plate));
        // }
        //요금계산(fees활용)
        List<Integer> ans = new ArrayList<>();
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        List<Integer> sortedPlates = new ArrayList<>(accum.keySet());
        sortedPlates.sort((a,b) -> a - b);
        for (int plate : sortedPlates) {
            //누적시간이 기본시간보다 작으면 기본요금만
            int carTotalTime = accum.get(plate);
            if (carTotalTime <= baseTime) {
                ans.add(baseFee);
            }else {
                int extra = ((carTotalTime - baseTime + unitTime - 1) / unitTime) * unitFee;
                ans.add(baseFee + extra);
            }
        }
        return ans.stream().mapToInt(i->i).toArray();
    }
    public int toMinute(String str) {
        String[] parts = str.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
/**
누적으로하는거임. 입차출차 하면 바로 하는게아니라 총 누적시간!
기본 + 추가(올림처리)

어떻게 풀것이냐?
일단 records를 돌아야지.돌면서 뭘하냐?
정리를해. 자료구조가 필요할것임. 그러면 자료구조엔 뭘저장해? 차량번호,누적시간, 입차시간
그러면 두개로 <차량번호, 누적시간> 이랑 <차량번호, 입차시간>.이걸 바탕으로
입차나오면 입차에 넣고.. 출차면 누적시간추가.
*/