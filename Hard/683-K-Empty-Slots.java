/*
HARD
683. K Empty Slots

https://leetcode.com/problems/k-empty-slots/discuss/107948/Iterate-over-time-vs.-iterate-over-position

*/
/*
Loop over position

1. transfer time array to position array: for every position, when will they get lighted
2. use a fixed sliding window [left, right] that for every i inside the window:
    lighted[i] < lighted[left] && lighted[i] < lighted[right] 中间部分点亮时间晚于两边
    1)if not:
    left 大 大 小 ？？？right，则 小 之前的都不会构成 valid window 的左边界，直接移到 left = i
    2)if valid window (i == right):
    left 大 大 大 大 right, 则 right 之前都不会再构成 valid window，因为 right 小于左边部分

Time: O(n)
Space: O(n)
*/
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        int[] lighted = new int[n];
        
        //for every position, get the lighted time
        for(int i = 0; i < n; i++) lighted[flowers[i] - 1] = i;
        
        //sliding window to check possible window
        int i = 0, left = 0, right = k + 1;
        int result = n + 1;//impossible to be n + 1 (should be <= n)
        while(right < n){
            if(i == right){
                //window success
                result = Math.min(result, Math.max(lighted[left], lighted[right]) + 1);
                left = i;
                right = i + k + 1;
            }else if(lighted[i] < lighted[left] || (i != left) && (lighted[i] < lighted[right])){
                //window fail
                left = i;
                right = i + k + 1;
            }
            i++;
        }
        return result == n + 1 ? -1 : result;
    }
}




/*
Check for every lighted position
Time: O(nk)
Space: O(n)
*/
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        boolean[] light = new boolean[n];
        
        for(int i = 0; i < n; i++){
            int pos = flowers[i] - 1;
            light[pos] = true;
            
            int count = 0;
            //check left
            int tmp = pos;
            while(--tmp >= 0 && tmp > pos - k - 1){
                if(light[tmp]) break;
            }
            if(tmp == pos - k - 1 && tmp >= 0 && light[tmp]) return i + 1;
            
            //check right
            tmp = pos;
            while(++tmp < n && tmp < pos + k + 1){
                if(light[tmp]) break;
            }
            if(tmp == pos + k + 1 && tmp < n && light[tmp]) return i + 1;
                        
            
        }
        
        return -1;
    }
}
// [6,5,8,9,7,1,10,2,3,4]
//     0 1 2 3 4 5 6 7 8 9 
// lig t t     t t t t t t

// count = 2

/*
Loop over time: BST
Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        TreeSet<Integer> set = new TreeSet<>();
        
        for(int i = 0; i < n; i++){
            int pos = flowers[i];
            
            Integer l = set.lower(pos);
            Integer r = set.higher(pos);
            if(l != null && l == pos - k - 1 || r != null && r == pos + k + 1) return i + 1;
            
            set.add(pos);
        }
        return -1;
    }
}

