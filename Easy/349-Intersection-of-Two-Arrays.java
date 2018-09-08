/*
EASY
349. Intersection of Two Arrays
https://leetcode.com/problems/intersection-of-two-arrays/description/

TIME: 0907 - 30min
RESULT: 90% - 3ms

*/
/*
SOLUTION 1: Sort
TIME: 0907 - 30min
RESULT: 90% - 3ms
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Queue<Integer> q = new LinkedList<Integer>();
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                q.offer(nums1[i]);
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j]) i++;
            else j++;
           
            while(i > 0 && i < nums1.length && nums1[i] == nums1[i - 1]) i++;
            while(j > 0 && j < nums2.length && nums2[j] == nums2[j - 1]) j++;
        }
        int n = 0;
        int[] result = new int[q.size()];
        while(!q.isEmpty()){
            result[n++] = q.poll();
        }
        return result;
    }
}

/*
SOLUTION 0: HashSet
TIME: 0907 - 10min
RESULT: 9% - 10ms
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> n1 = new HashSet<Integer>();
        Set<Integer> n2 = new HashSet<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        for(int i : nums1){
            if(!n1.contains(i)) n1.add(i);
        }
        for(int i : nums2){
            if(n1.contains(i) && !res.contains(i)) res.add(i);
        }
        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }
}