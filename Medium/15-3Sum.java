/*
Solution 0 : time exceed
暴力三层循环，代码应该是没问题了，但是肯定得优化

Notes:
1.List.hashCode() 对于元素相同但是顺序不同的 List 返回的值是不一样的
2.这种情况可以用 Collection.sort() 先排序一下。（ Array 对应 Arrays.sort(); List 对应 Collections.sort())
3.List.size() 
4.List.get(n)
5.Arrays.asList()

*/
/*
Array 组合去重 --- 排序，略过相同的元素

Time: O(nlogn) + O(n^2)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || i > 0 && nums[i] != nums[i - 1]){//略过相同
                int target = 0 - nums[i];//揪出一个，比较剩下两个
                int lo = i + 1, hi = nums.length - 1;
                while(lo < hi){
                    if(nums[lo] + nums[hi] == target){
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        lo++;
                        hi--;
                        while(lo < hi && nums[lo] == nums[lo - 1]) lo++;//略过相同
                        while(hi > lo && nums[hi] == nums[hi + 1]) hi--;//略过相同
                    }else{
                        if(nums[lo] + nums[hi] > target) hi--;
                        else lo++;
                    }
                }
            }
        }
        return result;
    }
}








//---------2 ROUND FOR MS-----------------------
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
                int target = 0 - nums[i];
                int lo = i + 1, hi = nums.length - 1;
                while(lo < hi){
                    if(nums[lo] + nums[hi] == target){
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while(lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while(hi > lo && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }else if(nums[lo] + nums[hi] < target){
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


//---------1 ROUND ------------------------------
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                for(int k = j + 1; k < nums.length; k++){
                    if(nums[k] == 0 - nums[i] - nums[j]){
                        int m = 0;
                        List<Integer> tmp = Arrays.asList(nums[i],nums[j],nums[k]);
                        Collections.sort(tmp);
                        while(m < result.size()){
                            if (result.get(m).hashCode() == tmp.hashCode()){
                                break;
                            }
                            m++;
                        }
                        if(m == result.size()){
                           
                            result.add(tmp);
                        }
                        

                    }

                }
            }
        }
        return result;
    }
}



/*
Solution 1:time exceed / 41.49%
如果目前在 = 0 状态，接下来若只有一个指针移动，还能匹配，则肯定为一个重复解。所以匹配之后一定是两个指针一起挪动。
因为 nums 已经排序了，所以只有可能在连续匹配的 LIST 里出现重复相等的 LIST
所以每次匹配后，就拿当前 LIST 跟上一个 result 里面导入的 LIST 相比，如果一致，则不导入

Note:

这个代码的问题是，如果得到的是一串重复的数字，他会每次匹配后都创建一个 List，然后每次都 hashCode 来一次匹配。

*/


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int index = 0;
        for(int i = 0; i < nums.length - 2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
                int lo = i + 1;
                int hi = nums.length - 1;
            while (lo < hi) {
                if (nums[i] + nums[lo] + nums[hi] == 0) {
                    List<Integer> tmp = Arrays.asList(nums[i], nums[lo], nums[hi]);
                    if(index == 0 || (index != 0 && result.get(index-1).hashCode() != tmp.hashCode())){
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        index++;
                    }
                
                    lo++; 
                    hi--;
                } else if (nums[i] + nums[lo] + nums[hi] < 0) lo++;
                else hi--;
           }
            }


        }
        
        
        return result;
    }
}


[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,


/*
Solution 2:time exceed / 92.73 %
很奇怪诶，先就说人家不够快，现在有这么快

Notes:
这个思路的优秀之处在于，直接挪动指针来规避重复项，而不是等到匹配且形成 LIST 之后再来和之前的项比较。
因为既然你在 Solution 1 里面也只是和相邻匹配项比较，为什么不更早的就在匹配之前规避呢。
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < nums.length - 2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
                int lo = i + 1;
                int hi = nums.length - 1;
                while(lo < hi){
                    if(nums[i] + nums[lo] + nums[hi] == 0){
                        result.add(Arrays.asList(nums[i],nums[lo],nums[hi]));
                        while(lo < hi && nums[lo] == nums[lo + 1] ) {lo++;}//这里应该限制它不能无限网上增，否则若后面所有数都相等，则会超量
                        while(lo < hi && nums[hi] == nums[hi - 1] ) {hi--;}//另外注意顺序 nums[lo] == nums[lo + 1] && lo < hi，这样会先判断前面的，而这个时候就超空间了
                        lo++;
                        hi--;
                    }else if (nums[i] + nums[lo] + nums[hi] < 0){
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


[-2,0,0,2,2]
[0,0,0]

[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

/*
总结：
对于杂乱的对象，还是先能够让对象变得规律起来，这样才有算法的可行性，不然就真的只能暴力遍历
以及，如果要去除重复项，应该在更早的时候就移除重复的元素
*/


