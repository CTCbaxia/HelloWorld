/*
M
900. RLE Iterator
*/
/*
Stack: new class CountNum

Time: O(n)
Space: O(1)
*/
class RLEIterator {
    int[] nums;
    int index;
    public RLEIterator(int[] A) {
        nums = A;
        index = 0;
    }
    
    public int next(int n) {
        while(n > 0 && index < nums.length){
            if(nums[index] >= n){
                nums[index] -= n;
                return nums[index + 1];                
            }else{
                n -= nums[index];
                index += 2;
            }
        }
        return -1;
    }
}

/*
Stack: new class CountNum

Time: O(n)
Space: O(n)
*/
class RLEIterator {
    Stack<CountNum> stack;
    
    public class CountNum{
        int count;
        int num;
        public CountNum(int _count, int _num){
            count = _count;
            num = _num;
        }
    }
    
    public RLEIterator(int[] A) {
        stack = new Stack<>();
        for(int i = A.length - 1; i >= 1; i = i - 2){//A.length -- even
            CountNum cn = new CountNum(A[i - 1], A[i]);
            stack.push(cn);
        }
        
    }
    
    public int next(int n) {
        while(n > 0 && !stack.isEmpty()){
            CountNum cn = stack.pop();
            if(cn.count >= n){
                cn.count -= n;
                if(cn.count > 0) stack.push(cn);
                return cn.num;
            }else{
                n -= cn.count;
            }
        }
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */
