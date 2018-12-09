/*
HARD
239. Sliding Window Maximum

TIME: 
RESULT: 
*/
/*
Sliding Window + Mono Dequeue
用 mono deque 维持一个pending index 序列，是的里面对应的 nums[index] 递减，每次移除不可能使用的index
1) keep range: 最前面的 index 对应的就是现在 window 里面的最大值，如果 window 移出了当前最大值，就把首项弹出
2) 维护 mono desc: 每次 nums[end]，移除 deque 里面比 nums[end] 小的 index，然后把当前index 加上去

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k){
        if(nums.length == 0) return new int[0];

        Deque<Integer> mono = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        for(int end = 0; end < nums.length; end++){
            int start = end - k + 1;//for result

            if(mono.size() > 0 && start > mono.getFirst()) mono.pollFirst();//remove out of range
            while(mono.size() > 0 && nums[mono.getLast()] < nums[end]) mono.pollLast();//keep it mono desc

            mono.offerLast(end);
            if(start >= 0) result[start] = nums[mono.getFirst()];
        }

        return result;
    } 
}