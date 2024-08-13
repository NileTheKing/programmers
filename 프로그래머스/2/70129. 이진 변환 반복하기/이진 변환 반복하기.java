class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int zerocount = 0;
        int count = 0;
        String string = "1";
        
        while(!s.equals(string)){
            count++;
             for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '0'){
                    zerocount++;
                }
            }
            s = s.replace("0", "");
          
            int length  = s.length();
            s = Integer.toBinaryString(length);
        }
        
        answer[0] = count;
        answer[1] = zerocount;
        return answer;
    }
}