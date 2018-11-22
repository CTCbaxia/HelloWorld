/*
MEDIUM
277. Find the Celebrity

TIME: 
RESULT: 75% - 10ms
NOTES:
如果要求尽量少的调用 API, 就注意最大化利用 API 信息的不同结果
*/
/*
调用一次knows(a, b)就要尽可能多得到信息
knows(a, b) can tell you who cannot be the celebrity
*/
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int pending = 0;
        for(int i = 1; i < n; i++){
             if(knows(pending, i)){
                 pending = i;
             }
        }
        //check if pending is the real celebrity：是否满足双向关系
        //knows(pending, i) 其实可以只查 pending前面的，因为后面的都在上面查过了
        for(int i = 0; i < n; i++){
            if(i == pending) continue;
            if(knows(pending, i) || !knows(i, pending)) return  -1;
        }
        return pending;
    }
}

//更优化的解法（调用 knows(a,b)更少）
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int pending = 0;
        for(int i = 1; i < n; i++){
             if(knows(pending, i)){
                 pending = i;
             }
        }
        for(int i = 0; i < pending; i++){//pending 之后的都在上面的loop里面查过
            if(knows(pending, i)) return -1;
        }
        //check if pending is the real celebrity
        for(int i = 0; i < n; i++){
            if(i == pending) continue;
            if(!knows(i, pending)) return  -1;
        }
        return pending;
    }
}


/*
第一遍选出候选的 celebrity，只可能有一个？
第二遍核实他是不是 celebrity
knows(a, b)
if true, then a cannot be celebrity
if false, then b cannot be celebrity



Time: O(n)
Space: O(1)
RESULT: 75% - 10ms
*/

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int pending = 0;
        //find the possible celebrity
        for(int i = 1; i < n; i++){
            if(knows(pending, i)) pending = i;
        }
        //check if it is real
        for(int i = 0; i < n; i++){
            if(i == pending) continue;
            if(!(knows(i, pending) && !knows(pending, i))) return -1;
        }
        return pending;
    }
}

/*
查每个人，如果所有人都认识 i，就查 i 认不认识别人。注意prunning

Time: O(n^2)
Space: O(n^2)
*/
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Set<Integer> notCelebrity = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            map.put(i, new ArrayList<Integer>());
        }
        
        for(int i = 0; i < n; i++){
            if(notCelebrity.contains(i)) continue;
            for(int j = 0; j < n; j++){
                if(j == i) continue;
                if(knows(j, i)){
                    map.get(i).add(j);
                    notCelebrity.add(j);
                } 
            }
            if(map.get(i).size() == n - 1){
                //check if i knows anybody
                int count = 0;
                for(int k = 0; k < n; k++){
                    if(k == i) continue;
                    else if(knows(i, k)){
                        map.get(k).add(i);
                        notCelebrity.add(i);
                        count++;
                        break;
                    }
                }
                if(count == 0) return i;
            }
            
        }
        return -1;
    }
}