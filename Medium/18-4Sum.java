/*
18. 4Sum

TIME: 1008 - 15min
RESULT: 77% - 37ms
NOTES:
其实还可以pruning
if there are less than 4 elements -> none
if the last num in nums has 4 * num < target -> none
if the first num in nums has 4 * num > target -> none


*/
//-------2 ROUND FOR MS-------------------------------------------------------------------------
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                int lo = j + 1, hi = nums.length - 1;
                while(lo < hi){
                    int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
                    if(sum == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        while(lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++; hi--;
                    }else if(sum < target) lo++;
                    else hi--;
                }
                while(j < nums.length - 1 && nums[j] == nums[j + 1]) j++;
            }
            while(i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }
}

//-------1 ROUND-------------------------------------------------------------------------
//Solution 0: 26%
//参照 3Sum 的解法
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            for(int j = i + 1; j < nums.length - 2; j++){
                int lo = j + 1;
                int hi = nums.length - 1;
                while (lo < hi){
                    if(nums[i] + nums[j] + nums[lo] + nums[hi] == target){
                        List<Integer> tmp = Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]);
                        int match = 0;
                        for(int k = 0; k < result.size(); k++){
                            if(result.get(k).hashCode() == tmp.hashCode()){ 
                            //得到结果之后再来一个个比较是否一致，但其实可以在之前就排除，因为，毕竟 nums 已经排序，即使相同，也肯定是在顺移的时候得到同样的值
                            //所以参考 Solution 1
                                match = 1;
                                break;
                            }
                        }
                        if(match == 0){
                            result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        }
                        
                        while(lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }else if(nums[i] + nums[j] + nums[lo] + nums[hi] < target){
                        lo++;
                    }else{
                        hi--;
                    }
                }
            }

        }
        return result;
    }
}

/*
Solution 1: 68.46%

Note:
其实还可以优化，在一开始就排除本次循环所有值都过大 / 过小的问题，把一些不可能的场景直接排除
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;//在匹配前就排除了一致的可能
            for(int j = i + 1; j < nums.length - 2; j++){
                if(j > i + 1 && nums[j] == nums[j-1]) continue;
                int lo = j + 1;
                int hi = nums.length - 1;
                while (lo < hi){
                    if(nums[i] + nums[j] + nums[lo] + nums[hi] == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        
                        while(lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }else if(nums[i] + nums[j] + nums[lo] + nums[hi] < target){
                        lo++;
                    }else{
                        hi--;
                    }
                }
            }

        }
        return result;
    }
}

[1,0,-1,0,0,-2,0,20,20,10,2,-20,-20]
0



/*
Solution 2: 72.69%
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;//在匹配前就排除了一致的可能
            if(checkbig(i, nums)){continue;}

            for(int j = i + 1; j < nums.length - 2; j++){
                if(j > i + 1 && nums[j] == nums[j-1]) continue;
                if(checkbig2(i, j, nums)){continue;}
                int lo = j + 1;
                int hi = nums.length - 1;
                while (lo < hi){
                    if(nums[i] + nums[j] + nums[lo] + nums[hi] == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        
                        while(lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }else if(nums[i] + nums[j] + nums[lo] + nums[hi] < target){
                        lo++;
                    }else{
                        hi--;
                    }
                }
            }

        }
        return result;
    }
    //去除每个循环极值都无法满足要求的情况
    private boolean checkbig(int i, int[] nums){
        int max = nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1];
        if(max < target){
            return 1;
        }else{
            return 0;
        }
    }
    private boolean checkbig2(int i, int j, int[] nums){
        int max = nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1];
        if(max < target){
            return 1;
        }else{
            return 0;
        }
    }


}