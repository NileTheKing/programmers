class Solution {
    public int solution(int n, int a, int b) {
        int cnt = 1;
        while ((a-1) / 2 != (b-1) / 2) {
            cnt++;
            int aCurrentTeamNumber = (a - 1) / 2 + 1;
            int aSpare = (a - 1) % 2; //0 or 1;
            
            int bCurrentTeamNumber = (b - 1) / 2 + 1;
            int bSpare = (b - 1) % 2; //0 or 1;
            
            int aNextTeamNumber = (aCurrentTeamNumber - 1) / 2 + 1;
            a = (aNextTeamNumber - 1) * 2 + aSpare + 1;
            int bNextTeamNumber = (bCurrentTeamNumber - 1) / 2 + 1;
            b = (bNextTeamNumber - 1) * 2 + bSpare + 1;
            // System.out.printf("current nums:%d %d, current teams: %d %d, spare:%d %d, nextTeam Number: %d %d\n", a,b, aCurrentTeamNumber, bCurrentTeamNumber, aSpare, bSpare, aNextTeamNumber, bNextTeamNumber);
            // System.out.printf("next a b: %d %d\n", a, b);
            
        }
        return cnt;
    }
}