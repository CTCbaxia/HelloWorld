/*
EASY
496. Next Greater Element I

*/
/*
Map + Stack
Stack: 保持一个递减的队列 [4,2], 下一个数 x 进去弹出所有小于他的队列数，弹出来的数的对应 next 就是 x
Map: 记录每个数的 next

Time: O(len2) while 里面操作的总量加起来不会超过 nums2
Space: O(nums2)
*/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for(int n : nums2){
            while(!stack.isEmpty() && stack.peek() < n){
                map.put(stack.pop(), n);
            }
            stack.push(n);
        }
        for(int i = 0; i < nums1.length; i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}



/*
Map
Time: O(len2 ^ 2)
Space: O(len(nums2))
*/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums2.length; i++){
            boolean find = false;
            for(int j = i + 1; j < nums2.length; j++){
                if(nums2[j] > nums2[i]){
                    map.put(nums2[i], nums2[j]);
                    find = true;
                    break;
                }
            }
            if(!find) map.put(nums2[i], -1);
        }
        for(int i = 0; i < nums1.length; i++){
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}



/*
Two Pointers

Time: O(len1 * len2)
Space: O(1)
*/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            boolean find = false;
            for(int j = nums2.length - 1; j >= 0; j--){
                if(nums2[j] == nums1[i]) break;
                if(nums2[j] > nums1[i]){
                    res[i] = nums2[j];
                    find = true;
                }
            }
            if(!find) res[i] = -1;
        }
        return res;
    }
}