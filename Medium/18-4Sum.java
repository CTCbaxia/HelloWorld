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
/*
Solving N-sum
1. Sort
2. Then reduce N
3. until N == 2, do two pointers from l and r

Time: O(nlogn + n^2 * n)
Space: O(1)

followup:有没有办法更少时间
先 preprocessing 生出 lookup table 就能达到 O(n^2):
排序之后，构建一个 map<Integer, List<int[]>>[] dic，
两层 for loop 顺序
for(int i = 0..)
    for(int j = 0...){
        sum = nums[i] + nums[j];
        dic[j + 1].get(sum).add(new int[]{nums[i], nums[j]});
    }
然后需要再一遍 两层 for loop，剩下的就在 dic 里面看就好了
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int t = target - nums[i] - nums[j];
                int l = j + 1, r = n - 1;
                while(l < r){
                    if(nums[l] + nums[r] == t){
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while(l + 1 < r && nums[l + 1] == nums[l]) l++;
                        while(r - 1 > l && nums[r - 1] == nums[r]) r--;
                        l++;
                        r--;
                    }
                    else if(nums[l] + nums[r] < t) l++;
                    else r--;
                }
                while(j + 1 < n && nums[j + 1] == nums[j]) j++;//be at the last same element
            }
            while(i + 1 < n && nums[i + 1] == nums[i]) i++;//be at the last same element
        }
        return res;
    }
}






/*
Sort + 固定两个数，然后 two sum
排序可以binary search

Time: O(nlogn) + O(n^3)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            for(int j = i + 1; j < nums.length - 2; j++){
                int lo = j + 1, hi = nums.length - 1;
                while(lo < hi){
                    int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
                    if(sum == target){
                        res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        lo++;
                        hi--;
                        while(lo < hi && nums[lo] == nums[lo - 1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi + 1]) hi--;
                    }
                    else if(sum < target) lo++;
                    else hi--;
                }
                while(j + 1 < nums.length - 2 && nums[j] == nums[j + 1]) j++;
            }
            while(i + 1 < nums.length - 3 && nums[i] == nums[i + 1]) i++;
        }
        return res;
    }
}

/*
固定两个数，然后 two sum
1. list can avoid duplicate
2. set will avoid duplicate

Time: O(nlogn) + O(n^3)
Space: O(n) - set
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            for(int j = i + 1; j < nums.length - 2; j++){
                int n = target - nums[i] - nums[j];
                Set<Integer> set = new HashSet<>();
                for(int k = j + 1; k < nums.length; k++){
                    if(set.contains(n - nums[k])){
                        List<Integer> r = Arrays.asList(nums[i], nums[j], n - nums[k], nums[k]);
                        if(!res.contains(r)) res.add(r);//list can avoid duplicate
                    }
                    set.add(nums[k]);//set will avoid duplicate
                }
            }
        }
        return res;
    }
}



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