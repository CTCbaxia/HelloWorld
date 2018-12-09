/*
HARD
403. Frog Jump

TIME: 
RESULT: 
*/
/*
Map + Set (kind of BFS/DP)
Map 来存每一个 position 所有可能走的 step 数，并看是否能reach到某一个节点，
可以的话就更新该节点能够走的step数。
如果一个节点没有能走的步数，就略过

Time: O(n^2)
Space: O(n^2) hashmap存的只可能会到n^2
*/
class Solution {
    public boolean canCross(int[] stones){
         int len = stones.length;
         Map<Integer, Set<Integer>> map = new HashMap<>();

         for(int i = 0; i < len; i++){
             map.put(stones[i], new HashSet<Integer>());
         }
         map.get(stones[0]).add(1);

         for(int i = 0; i < len; i++){
             if(map.get(stones[i]).size() == 0) continue;//no way to get there

             Set<Integer> steps = map.get(stones[i]);
             for(int step : steps){
                 int des = stones[i] + step;
                 if(des == stones[len - 1]) return true;//if we arrive at end, return
                 if(!map.containsKey(des)) continue;
                 else{
                     if(step - 1 > 0) map.get(des).add(step - 1);
                     map.get(des).add(step);
                     map.get(des).add(step + 1);
                 }
             }
         }
         return false;
    }       
}