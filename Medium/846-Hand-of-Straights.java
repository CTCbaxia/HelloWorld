/*
M
846. Hand of Straights
*/
/*
TreeMap
Bucket sort the cards
loop from the smallest card + find the W window and updates numbers left

Time: O(DlogD + DW) -- TreeMap to put distinct number 
Space: O(D)
*/
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int n : hand){
            map.put(n, map.getOrDefault(n, 0) + 1);
        } 
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > 0){//there is still number left
                int num = entry.getKey();
                int count = entry.getValue();
                for(int k = 0; k < W; k++){
                    if(map.getOrDefault(num + k, 0) < count) return false;
                    map.put(num + k, map.get(num + k) - count);
                }
            }
        }
        return true;
    }
}

/*
Sort + Two Pointers
Sort the whole array and make the W length list using two pointers
Time: O(nlogn)
Space: O(n)
*/
