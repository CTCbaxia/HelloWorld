/*
E
849. Maximize Distance to Closest Person
*/
/*
Sliding Window: find the longest 0
mark start and end, since "There is at least one empty seat, and at least one person sitting",
seats[start] and seats[end] cannot all be 0
update for the longest distance

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxDistToClosest(int[] seats) {
        int end = 0;
        int max = 0;
        while(end < seats.length){
            if(seats[end] == 0){
                int start = end;
                while(end < seats.length && seats[end] == 0) end++;
                int dis = start == 0 || end == seats.length ? end - start : (end - start + 1 )/2;
                max = Math.max(max, dis);
            }

            end++;
        }
        
        return max;
    }
}
