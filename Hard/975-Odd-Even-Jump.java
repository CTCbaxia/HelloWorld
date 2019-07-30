/*
H
975. Odd Even Jump
*/
/*
Dynamic Programming + TreeMap

Use array to log the next number for the odd and even jump for the current index
Use treemap<number, index> to find the next number in O(1) by ceil and floor function

Then Dynamic Programming idea to check from right to left, mark if a number is good and how(odd, even) from this number
(Because there are a lot of duplicate check)

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int oddEvenJumps(int[] A) {
        int n = A.length;
        boolean[] lower = new boolean[n];
        boolean[] higher = new boolean[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int res = 0;
        
        //initialization: last one is always good
        map.put(A[n - 1], n - 1);
        higher[n - 1] = true;
        lower[n - 1] = true;
        
        for(int i = n - 1; i >= 0; i--){
            //you can get the value directly from the entry, 
            //and no need to call map.get(key) which also costs log(n) query time.
            Map.Entry<Integer, Integer> l = map.floorEntry(A[i]);
            if(l != null){//lower to the next
                int nextIndex = l.getValue();
                if(higher[nextIndex]) lower[i] = true;
            }
            
            Map.Entry<Integer, Integer> h = map.ceilingEntry(A[i]);
            if(h != null){//higher to the next
                int nextIndex = h.getValue();
                if(lower[nextIndex]) higher[i] = true;
            }
            if(higher[i]) res++;//the first jump at this position should be higher
            map.put(A[i], i);//update map
        }
        return res;
    }
}
