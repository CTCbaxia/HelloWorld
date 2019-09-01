/*
EASY
349. Intersection of Two Arrays
https://leetcode.com/problems/intersection-of-two-arrays/description/

TIME: 0907 - 30min
RESULT: 90% - 3ms

*/
/*
sort + two pointers

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<Integer>();
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] < nums2[j]) i++;
            else if(nums1[i] > nums2[j]) j++;
            else{
                list.add(nums1[i]);
                i++;
                j++;
                while(i < nums1.length && nums1[i] == nums1[i - 1]) i++;
                while(j < nums2.length && nums2[j] == nums2[j - 1]) j++;
            }
        }
        int[] result = new int[list.size()];
        for(int k = 0; k < result.length; k++){
            result[k] = list.get(k);
        }
        return result;
    }
}
/*
Set

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> interset = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for(int n : nums1){
            set.add(n);
        }
        for(int n : nums2){
            if(set.contains(n)) interset.add(n);
        }
        int[] result = new int[interset.size()];
        int i = 0;
        for(int n : interset){
            result[i++] = n;
        }
        return result;
    }
}


/*
Sort + Binary Search the longer one

Time: O(nlogn)
Space: O(1)

1. Two Set
2. Sort + Two Pinters
3. Sort + Binary Search
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);//short
        Arrays.sort(nums2);//long
        int i = 0, j = 0;
        while(i < nums1.length){
            if(i != 0 && nums1[i] == nums1[i - 1]){//skip duplicate
                i++;
                continue;
            } 
            int target = nums1[i];
            //binary search to find in nums2
            int l = j, r = nums2.length - 1;
            while(l <= r){
                int m = l + (r - l)/2;
                if(nums2[m] > target) r = m - 1;
                else if(nums2[m] < target){
                    l = m + 1;//l holds smallest first possible 
                    j = m + 1;
                } 
                else{
                    list.add(nums2[m]);
                    j = m + 1;//update lower bound
                    break;
                }
            }
            i++;
        }
        int[] res = new int[list.size()];
        for(int k = 0; k < res.length; k++){
            res[k] = list.get(k);
        }
        return res;
    }
}












/*
hashset + two for loop
**如果一个很小 一个很大，用很小的搭 set 可以省空间
Time: O(m + n)
Space: O(n)
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> intersect = new HashSet<Integer>();
        for(int i : nums1) set.add(i);
        for(int j : nums2){
            if(set.contains(j)) intersect.add(j);
        }
        
        int[] result = new int[intersect.size()];
        int index = 0;
        for(int n : intersect){
            result[index++] = n;
        }
        return result;
    }
}













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