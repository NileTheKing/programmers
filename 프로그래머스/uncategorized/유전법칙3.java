class Solution {
    public String[] solution(int[][] queries) {
        String[] ans = new String[queries.length];
        
        int idx = 0;
        for (int[] q : queries) {
            ans[idx++] = helper(q[0], q[1]);
        }
        return ans;
    }
    public String helper(int gen, int nth) {
        //나의 유전형질을 찾는거를 부모유전형질 찾는거로 바꿔서 올라감
        //부모가RR이면 자기는 RR, rr이면 자긴 rr
        //부모가 RR 또는 rr이 아니라면 본인이 몇번째 자식이냐에 따라 갈림.
        if (gen == 1) return "Rr";
                
        String parent = helper(gen-1, ((nth - 1) / 4) + 1);
        if (parent.equals("RR")) return "RR";
        else if (parent.equals("rr")) return "rr";
        
        if (nth %4 == 1) {
            return "RR";
        }else if(nth % 4 == 0) return "rr";
        else {
            return "Rr";
        }
    }
}
/**
1그룹의 자식은 항상 RR
4그룹의 자식읜 항상 rr
    -> basecase
    
1세대면 항상 Rr
    -> basecase
    
부모가 RR아니고 rr도 아니다?그럼 부모가 Rr임.이때 경우는 몇번째 자식이냐
*/