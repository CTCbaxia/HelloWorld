/*
MEDIUM
165. Compare Version Numbers
https://leetcode.com/problems/compare-version-numbers/description/

TIME: 1006 - 30min
RESULT: 89% - 1ms
NOTES:
1. String 不能比较大小，char 和 int 都可以
2. split(".") 不行，因为 . 为保留字符，要用\\. 来escape
*/
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] part1 = version1.split("\\.");
        String[] part2 = version2.split("\\.");
        int len = Math.max(part1.length, part2.length);

        for(int i = 0; i < len; i++){
            int n1 = i < part1.length ? Integer.parseInt(part1[i]) : 0;
            int n2 = i < part2.length ? Integer.parseInt(part2[i]) : 0;
            if( n1 > n2) return 1;
            else if(n1 < n2) return -1;
        }

        return 0;
    }
}



class Solution {
    public int compareVersion(String version1, String version2) {
        String[] part1 = version1.split("\\.");
        String[] part2 = version2.split("\\.");
        int len = Math.min(part1.length, part2.length);

        for(int i = 0; i < len; i++){
            int n1 = Integer.parseInt(part1[i]);
            int n2 = Integer.parseInt(part2[i]);
            if( n1 > n2) return 1;
            else if(n1 < n2) return -1;
        }
        while(len < part1.length){
            if(Integer.parseInt(part1[len]) > 0) return 1;
            len++;
        }
        while(len < part2.length){
            if(Integer.parseInt(part2[len]) > 0) return -1;
            len++;
        }
        return 0;
    }
}



class Solution {
    public int compareVersion(String version1, String version2) {

        int i = 0, j = 0;
        while(i < version1.length() || j < version2.length()){
            int n1 = 0;
            int n2 = 0;
            while(i < version1.length() && version1.charAt(i) != '.'){
                n1 = n1 * 10 + version1.charAt(i++) - '0';
            }
            while(j < version2.length() && version2.charAt(j) != '.'){
                n2 = n2 * 10 + version2.charAt(j++) - '0';
            }
            if(n1 > n2) return 1;
            else if(n1 < n2) return -1;
            i++;
            j++;
        }

        return 0;
    }
}