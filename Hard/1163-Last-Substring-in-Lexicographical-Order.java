/*
H
1069. Last Substring in Lexicographical Order
*/
/*
BFS
Collect first level candidate using the max char
the for these candidate, compare the second char

!!! need pruning for "zzzzzzzzzz..."
How?:
for the first level, just put the first index, skip the rest of z


Time: O(n^2)
say we have m after the first filter, then max len of the candidate substring is still O(n)
so it is mO(n), where m < n
so the upper bound is O(n^2)

Space: O(n)
worst case is we put n/2 index into the maxIndex
*/
class Solution {
    public String lastSubstring(String s) {
        //initialization
        List<Integer> maxIndex = new ArrayList<>();
        char max = 'a';
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) > max) max = s.charAt(i);
        }
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == max){
                maxIndex.add(i);
                // Important pruning
                // for zzzzzzzzz, if we have the first index which can indicate zzzzzzzzz, 
                // we don't need to compare the rest substring here after the first index, such as zzzzzzzz, zzzzzzz, zzzzzz
                // because they must be smaller than the first substring zzzzzzzzz.
                while(i + 1 < s.length() && s.charAt(i + 1) == max){
                    i++;
                }
            } 
        }
        
        
        int k = 0;
        while(maxIndex.size() > 1){
            max = 'a';
            List<Integer> nextIndex = new ArrayList<>();
            
            // find max char
            for(int i : maxIndex){
                if(i + k < s.length() && s.charAt(i + k) > max) max = s.charAt(i + k);
            }
            // filter the substring that has the (i + k) index as max char
            for(int i : maxIndex){
                if(i + k < s.length() && s.charAt(i + k) == max) nextIndex.add(i);
            }
            k++;
            maxIndex = nextIndex;
        }
        return s.substring(maxIndex.get(0));
    }
}



/*
Brute Force: Two String Comparison
default set the first index as resIndex, then move the pointer i to the rest of the string,
and compare these two string
update the resIndex if there is a bigger substring

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public String lastSubstring(String s) {
        int resIndex = 0, len = 0;
        for(int i = 1; i < s.length(); i++){
            for(len = 0; i + len < s.length(); len++){//make sure the later index in the bound
                if(s.charAt(resIndex + len) == s.charAt(i + len)) continue;
                if(s.charAt(i + len) > s.charAt(resIndex + len)){
                    resIndex = i;
                }
                break;
            }
            // if we arrive here, it means the two string are completely same 
            // except for the last string, which means they are all same char
            if (i + len == s.length()) break;
        }
        return s.substring(resIndex);
    }
}


/*
❌TLE
BFS
*/
class Solution {
    public String lastSubstring(String s) {
        //initialization
        List<Integer> maxIndex = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            maxIndex.add(i);
        }
        int k = 0;
        while(maxIndex.size() > 1){
            char max = 'a';
            List<Integer> nextIndex = new ArrayList<>();
            
            //find max char
            for(int i : maxIndex){
                if(i + k < s.length() && s.charAt(i + k) > max) max = s.charAt(i + k);
            }            
            for(int i : maxIndex){
                if(i + k < s.length() && s.charAt(i + k) == max) nextIndex.add(i);
            }
            k++;
            maxIndex = nextIndex;
        }
        return s.substring(maxIndex.get(0));
    }
}
