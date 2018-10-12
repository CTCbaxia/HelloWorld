/*
MEDIUM
215. Kth Largest Element in an Array
https://leetcode.com/problems/kth-largest-element-in-an-array/description/

TIME: 0820 - 5min
RESULT: 57% - 9ms
NOTES: 

*/

//quick select（课上讲的）
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        k = nums.length - k;
        while(index != k){
            if(index < k) start = index + 1;
            else end = index - 1;
            index = partition(nums, start, end);
        }
        
        return nums[index];
    }
    private int partition(int[] nums, int start, int end){
        if(start >= end) return start;
        System.out.println(start + " "+end);
        int pivot = end;
        int index = start - 1;
        for(int i = start; i < end; i++){
            if(nums[i] < nums[pivot]){
                swap(nums, ++index, i);
            }
        }
        swap(nums, ++index, end);
        return index;
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        return;
    }
}
//O(N lg K) running time + O(K) memory
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int n : nums){
            if(pq.size() < k){
                pq.add(n);
            }else{
                pq.add(n);
                pq.poll();
            }
        }
        return pq.poll();
    }
}

//如果要的是第N大的
//PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer n1, Integer n2){
                return n2 - n1;
            }
        });
        for(int n : nums){
            if(pq.size() < k){
                pq.add(n);
            }else{
                pq.add(n);
                pq.poll();
            }
        }
        return pq.poll();
    }
}


//O(N lg N) running time + O(1) memory
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int index = nums.length - k;
        return nums[index];
    }
}
