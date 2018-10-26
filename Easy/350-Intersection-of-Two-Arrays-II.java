/*
EASY
350. Intersection of Two Arrays II

RESULT: 98% - 2ms
NOTES: 
sorting problem - find the same

FOLLOW UP:
1. What if the given array is already sorted? How would you optimize your algorithm?
- two pointers
2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
- sorted and two pointers
3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
???
I think the goal of this question is to test whether the interview understands some of the data engineering techniques. From a data engineer's perspective, basically there are three ideas to solve the question:

1. Store the two strings in distributed system(whether self designed or not), then using MapReduce technique to solve the problem;

2. Processing the Strings by chunk, which fits the memory, then deal with each chunk of data at a time;

3. Processing the Strings by streaming, then check.


*/
/*
1.build map - counting keys
2.find

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();//<key, number of key>
        for(int i = 0; i < nums1.length; i++){
            map.put(nums1[i], map.getOrDefault(nums1[i],0) + 1);
        }
        List<Integer> l = new ArrayList<Integer>();
        for(int i = 0; i < nums2.length; i++){
            if(map.containsKey(nums2[i])){
                l.add(nums2[i]);
                if(map.get(nums2[i]) - 1 == 0) map.remove(nums2[i]);
                else map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        
        int[] result = new int[l.size()];
        for(int i = 0; i < l.size(); i++){
            result[i] = l.get(i);
        }
        return result;
    }
}


/*
1.sort
2.two pointers to compare

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        List<Integer> l = new ArrayList<Integer>();
        while(p1 < nums1.length && p2 < nums2.length){
            if(nums1[p1] < nums2[p2]) p1++;
            else if(nums1[p1] > nums2[p2]) p2++;
            else if(nums1[p1] == nums2[p2]){
                l.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        int[] result = new int[l.size()];
        for(int i = 0; i < l.size(); i++){
            result[i] = l.get(i);
        }
        return result;
    }
}