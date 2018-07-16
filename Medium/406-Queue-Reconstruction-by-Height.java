/*
MEDIUM
406. Queue Reconstruction by Height
https://leetcode.com/problems/queue-reconstruction-by-height/description/
TIME: 0716 - 3h
RESULT: 98%% - 11ms
NOTES:
1. ArrayList<int[]> 的使用：list 的灵活性就是可以初始化不限长度，并且根据需要插入到指定位置
2. 注意 Arrays.sort(Element, Comparator) 的使用（包括正序，逆序，等值情况下的规则）
3. 倒序的巧妙！！


思路：
Pick out tallest group of people and sort them in a subarray (S). 
Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.


REFERENCE:
1.Java solution using Arrays.sort() and "insert sorting" idea
https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89350/Java-solution-using-Arrays.sort()-and-%22insert-sorting%22-idea
2.Java solution using PriorityQueue and LinkedList
https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89439/Java-solution-using-PriorityQueue-and-LinkedList
*/

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][2];
        if(people.length == 0 || people.length == 1) return people;
        
        //descending order by height
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                if(p1[0] == p2[0]) return p1[1] - p2[1];
                else return p2[0] - p1[0];
            }
        });

        ArrayList<int[]> tmp_result = new ArrayList<Integer>();
        for(int i = 0; i < people.length; i++){
            int position = people[i][1];
            tmp_result.add(position, people[i]);
        }
        for(int i = 0; i < tmp_result.size(); i++){
            result[i] = tmp_result.get(i);
        }
        return result;
    }
}


/*
SOLUTION 1:
思路：
先排序。
从最小的 height 开始排位置。位置取决于 n = people[i][1]，即该 people 位置前面必须还有 n 个空位
用 allpositions 记录剩余的空位
用 allheight 来判断是否是同等高度（因为同等高度在 sort 之后已经满足组内座位顺序，所以同等高度的 people 在之前安插的座位应该抵消一个空位）

TIME: 0716 - 3h
RESULT: 43% - 55ms
NOTE:
1. 对于需要比较的问题，最好先排序

*/

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][2];
        if(people.length == 0 || people.length == 1) return people;
        
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                if(p1[0] == p2[0]) return p1[1] - p2[1];
                else return p1[0] - p2[0];
            }
        });

        Set<Integer> allpositions = new HashSet<Integer>();
        Set<Integer> allheight = new HashSet<Integer>();
        int same_count = 0;
        for(int i = 0; i < people.length; i++){
            allpositions.add(i);
        }
        for(int i = 0; i < people.length; i++){
            int p_height = people[i][0];
            int position = people[i][1];
            if(allheight.contains(p_height)){
                same_count++;
            }else{
                same_count = 0;
                allheight.add(p_height);
            }
            position = position - same_count;
            for(int j : allpositions){
                if(position == 0){
                    result[j] = people[i];
                    allpositions.remove(j);
                    break;
                } 
                position--;
            }
        }
        return result;
    }
}
