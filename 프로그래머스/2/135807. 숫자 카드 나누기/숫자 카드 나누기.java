class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = getGCD(gcdA, arrayA[i]);
            gcdB = getGCD(gcdB, arrayB[i]); 
        }
        boolean ableA = true;
        for (int b : arrayB) {
            if (b % gcdA == 0) {
                ableA = false;
                break;
            }
        }
        boolean ableB = true;
        for (int a : arrayA) {
            if (a % gcdB == 0) {
                ableB = false;
                break;
            }
        }
        int candidateA = ableA ? gcdA : 0;
        int candidateB = ableB ? gcdB : 0;
        return Math.max(candidateA, candidateB);
        
    }
    public int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}