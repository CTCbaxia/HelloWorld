/*
M
1011. Capacity To Ship Packages Within D Days
*/
/*
Problem: find a num limit N, that each day carry no more than N, all weights can be assigned to a day in order
--> divide the weights into D parts evenly

Solution: Greedy
Sum all weights, record the max weight
    - least possible weight capacity = max single weight
    - max possible weight capacity = sum of all weights

Time: O(n) + O(n * logmn) m is 500 in this case: 1 <= weights[i] <= 500
Space: O(1)

*/
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int maxWeight = 0;
        int sumWeight = 0;
        for(int w : weights){
            maxWeight = Math.max(maxWeight, w);
            sumWeight += w;
        }
        int avgWeight = sumWeight / D + (sumWeight % D == 0 ? 0 : 1);//ceil the avg
        int minWeight = Math.max(maxWeight, avgWeight);
        
        //then the answer should be between minPossibleRes to sumWeight
        //can find the answer by increase PossibleRes by 1 and try to fit
        //to speed up, using Binary Search to find *largerOrEqual*
        int l = minWeight, r = sumWeight;
        while(l < r){
            int m = l + (r - l)/2;
            if(tryShip(weights, m, D)){//if this m can success
                r = m;//r holds the pending answer
            }else{
                l = m + 1;
            }
        }
        return r;
    }
    private boolean tryShip(int[] weights, int max, int D){
        int i = 0;
        while(i < weights.length && D-- > 0 ){//!!D-- > 0 rather than >=
            int sum = 0;
            while(i < weights.length && sum + weights[i] <= max){
                sum += weights[i];
                i++;
            }
            //here i is the one for the new start day
        }
        if(i == weights.length) return true;
        else return false;
    }
}
