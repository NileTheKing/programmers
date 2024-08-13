class Solution {
    public String solution(String s) {
        
        String[] split;
        split = s.split(" ");
        
    
        
        int[] array = new int[split.length];
                  
        for(int i = 0; i < split.length; i++){
            array[i] = Integer.parseInt(split[i]);
        }

        
        int max = array[0];
        int min = array[0];
        
        for(int i = 0; i < array.length; i++){
            if(array[i] >= max)
                max = array[i];
            if(array[i] <= min)
                min = array[i];
        }
        
        String answer = "";
        answer += min;
        answer += ' ';
        answer += max;
        
        return answer;
    }
}