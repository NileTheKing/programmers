import java.util.*;
class Solution {
    int[] cell;
    String[] value;
    public String[] solution(String[] commands) {
        cell = new int[2501]; //n = 50*r + c;
        value = new String[2501];
        for (int i = 1; i <= 2500; i++) cell[i] = i;
        
        List<String> ans = new ArrayList<>();
        for (String com : commands) {
            // System.out.println("=== " + com + " ===");
            String[] parts = com.split(" ");
            // if (parts[0].equals("UPDATE") && !Character.isDigit(parts[1].charAt(0))) continue;
            switch (parts[0]) {
                case "UPDATE":
                    if (parts.length == 4) {
                        int r = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        int n = (r-1) * 50 + c;
                        update(n, parts[3]);
                        break;
                    }else {
                        updateValue(parts[1], parts[2]);
                        break;
                    }
                case "MERGE":
                    int r1 = Integer.parseInt(parts[1]);
                    int c1 = Integer.parseInt(parts[2]);
                    int r2 = Integer.parseInt(parts[3]);
                    int c2 = Integer.parseInt(parts[4]);
                    int n1 = (r1-1) * 50 + c1;
                    int n2 = (r2-1) * 50 + c2;
                    merge(n1, n2);
                    break;
                case "UNMERGE":{
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    int n = (r-1) * 50 + c;
                    unmerge(n);
                    break;}
                case "PRINT":{
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    int n = (r-1) * 50 + c;
                    ans.add(print(n));
                    break;}
            }
            // if (parts[0].equals("UPDATE") && !Character.isDigit(parts[1].charAt(0))) continue;
            // int temp1 = Integer.parseInt(parts[1]);
            // int temp2 = Integer.parseInt(parts[2]);
            // int cell = temp1 * 50 + temp2;
            // System.out.printf("cell %d\n", cell);
            // int p = find(cell);
            // String v = value[p];
            // System.out.printf("value: %s\n", v);
        }

        return ans.toArray(new String[0]);
        // return new String[0];
        
        
    }
    public void union(int n1, int n2) { 
        int p1 = find(n1);
        int p2 = find(n2);
        if (p1 != p2) {
            cell[p2] = p1; // p2 전체를 p1 밑으로!
        }
}
    public int find(int n) {// 셀n의 부모는?
        if (cell[n] == n) return n;
        return find(cell[n]);
    }
    public void update(int n, String str) {
        int p = find(n);
        value[p] = str;//값업데이트. 부모만. unmerge대비
    }
    public void updateValue(String str1, String str2) {
        for (int i = 1; i <= 2500; i++) {
            // 본인이 루트(부모)인 애들만 싹 다 뒤져서 값 변경
            if (cell[i] == i && value[i] != null && value[i].equals(str1)) {
                value[i] = str2;
            }
        }
    }
    public void merge (int n1, int n2) {
        if (n1 == n2) return;
        int p1 = find(n1);
        int p2 = find(n2);
        String v1 = value[p1];
        String v2 = value[p2];
        //n1, p1,v1이 우선
        //없으면 부몬느 2가됨
        if (v1 == null) { //v1이 없을때만 2로토잉ㄹ 둘다 null도 커버.둘다null이면노상관
            union(n2, n1); //n1을 n2아래로 > 참조인덱스변경.값은 안바꿔됨
            return;
        }
        union(n1, n2);//n1을 우선해서.
        
    }
    public void unmerge(int n) {
        int root = find(n);
        String tempValue = value[root];
        
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            if (find(i) == root) {
                list.add(i);
            }
        }
        for (int i  : list) {
            cell[i] = i;
            value[i] = null;
        }
        value[n] = tempValue;
    }
    public String print(int n) {
        return value[find(n)] == null ? "EMPTY" : value[find(n)];
    }
}