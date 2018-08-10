/*
EASY
217. Contains Duplicate
https://leetcode.com/problems/contains-duplicate/description/

TIME: 0810 - 1min
RESULT: 82% - 7ms
Notes: 

METHOD:

*/
class Solution {
    public boolean containsDuplicate(int[] nums) {
         Set<Integer> set = new HashSet<Integer>();
         for(int i : nums){
             if(!set.add(i))// if there is same
                 return true; 
         }
         return false;
    }
}


class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums){
            if(set.contains(i)) return true;
            else set.add(i);
        }
        return false;
    }
}