import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int left = 0;
        int right = people.length -1;
        Arrays.sort(people);
        int count  = 0;
        while(left <= right){
            if(people[left] + people [right] <= limit){
                count++;
                left++;
                right--;
            }
            else{
                count++;
                right--;
            }
        }
        
        return count;
    }
}


