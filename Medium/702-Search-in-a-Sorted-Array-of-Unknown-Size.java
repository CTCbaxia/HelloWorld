/*
M
702. Search in a Sorted Array of Unknown Size
*/
/*
Binary Search - 找到搜索范围

Time: O(n), 确定搜索范围 O(logn), 最大的搜索范围 2^x (其中 2^x < n), 搜索时间 log(2^x) = x <= logn
Space: O(1)
*/
class Solution {
    public int search(ArrayReader reader, int target) {
        int right = 1;
        while(reader.get(right) < target){//确定要 binary search 的区间
            right = right << 1;
        }
        int left = right >> 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            int num = reader.get(mid);
            if(num < target) left = mid + 1;
            else if(num > target) right = mid - 1;
            else return mid;
        }
        return -1;
    }
}


//❌行不通，因为不能确定初始 target 的值是比 left 大的
class Solution {
    public int search(ArrayReader reader, int target) {
        if(reader.get(0) == target) return 0;
        
        int left = 1, right = target;
        while(reader.get(right) < target){
            left = right;
            right = right << 1;
        }
        while(left <= right){
            int mid = left + (right - left)/2;
            int num = reader.get(mid);
            if(num < target) left = mid + 1;
            else if(num > target) right = mid - 1;
            else return mid;
        }
        return -1;
    }
}
