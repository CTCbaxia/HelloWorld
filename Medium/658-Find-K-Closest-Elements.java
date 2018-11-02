/*
MEDIUM
658. Find K Closest Elements

TIME: 1101
RESULT: 
NOTES:

*/
/*
Binary Search:
Find the last one that is smaller or equal than x --- lo
and use two pointers 
lo: arr[lo] <= x
hi: arr[hi] >= x (by hi++)

find the suitable value using two pointers and insert or add to the result list
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0; 
        int hi = arr.length - 1;
        //find the last one that is smaller or equal as x
        while(lo < hi){
            int mid = lo + (hi - lo)/2 + 1;
            if(arr[mid] > x) hi = mid - 1;
            else lo = mid;
        }
        
        
        hi++;//now arr[lo] <= x && arr[hi] >= x
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < k; i++){
            if(lo >= 0 && hi < arr.length){//if they are all in range
                if(x - arr[lo] <= arr[hi] - x){
                    result.add(0, arr[lo]);
                    lo--;
                }else{
                    result.add(arr[hi]);
                    hi++;
                }
            }else if(hi < arr.length){//if only hi is in range
                result.add(arr[hi]);
                hi++;
            }else{
                result.add(0, arr[lo]);
                lo--;
            }
        }
        return result;

    }
}
/*
PriorityQueue(Actually Don't use sorted array property) 没用到 sorted 性质，肯定不行
make int[]{number itself, the absolute value to target x}
sort firstly using "the absolute value to target x", if it is the same, then sort using number itself

extract k th 
sort again

Time: O(nlogn) + O(k) + O(klogk)
Space: O(n)
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                if(i1[1] != i2[1]) return i1[1] - i2[1];
                else return i1[0] - i2[0];
            }
        });
        
        for(int n : arr) pq.offer(new int[]{n, Math.abs(n - x)});//nlogn 其实也可以在这里只保留 k 个
        
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < k && i < arr.length; i++){
            result.add(pq.poll()[0]);
        }
        
        Collections.sort(result);
        return result;
    }
}
