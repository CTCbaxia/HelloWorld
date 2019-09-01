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
- map the nums1 (less space)
- Time: O(m + n)
- Space: O(m)

- sort
- use binary search for the longer one


3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

I think the goal of this question is to test whether the interview understands some of the data engineering techniques. From a data engineer's perspective, basically there are three ideas to solve the question:
1. Store the two strings in distributed system(whether self designed or not), then using MapReduce technique to solve the problem;
2. Processing the Strings by chunk, which fits the memory, then deal with each chunk of data at a time;
3. Processing the Strings by streaming, then check.
*/
/*
Sort + Two Pointers

Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] < nums2[j]) i++;
            else if(nums1[i] > nums2[j])j++;
            else{
                res.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[res.size()];
        for(int k = 0; k < res.size(); k++){
            result[k] = res.get(k);
        }
        return result;
    }
}




/*
Sort + Binary Search

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums1);//shorter
        Arrays.sort(nums2);//longer
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            int target = nums1[i++];
            int l = j, r = nums2.length - 1;
            while(l < r){//find largeOrEqual to get first num large or equal
                int m = l + (r - l)/2;
                if(nums2[m] < target){
                    l = m + 1;
                    j = m;//update lower bound
                } 
                else r = m;
            }//l == r
            if(nums2[l] == target){
                res.add(nums2[l]);
                j = l + 1;//should skip the current one as already used
            } 
        }
        int[] result = new int[res.size()];
        for(int k = 0; k < res.size(); k++){
            result[k] = res.get(k);
        }
        return result;
    }
}


/*
two for loop
1 loop to build the map for nums1
2 loop to find the element in nums2 in the map and decrease the value of that key

1.build map - counting keys
2.find

Time: O(m + n)
Space: O(m | n)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();//key, count
        List<Integer> list = new ArrayList<Integer>();
        for(int i : nums1){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(int j : nums2){
            if(map.containsKey(j) && map.get(j) > 0){
                list.add(j);
                map.put(j, map.get(j) - 1);
            }
        }
        int[] result = new int[list.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = list.get(i);
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