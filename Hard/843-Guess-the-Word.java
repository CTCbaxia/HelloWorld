/*
HARD
843. Guess the Word

Onsite: 限制条件比LC 843多一些，
长度是５，都是uppercase, 没有重复字母。
先实现guess function, 自己设计数据结构，返回有几个字母match（不需要位置也一样，存在于secret word里就算）和是否猜中。
这个就是基础的字符串比较，主要是注意对word进行validation。
然后就是问怎么猜了。就大概说了LC 843里的方法，preprocessing dictionary,　然后根据猜的结果缩小范围。代码没时间写完。

*/
/*
Random Guess + BFS
每次让搜索范围变小：
每次随机在 pending list 里面找一个 word，得到该 word 的匹配数 sameNum。
所有和这个 word 有相同匹配数的都是可能的答案，而不同的都不是答案。
所以在原有 wl 的基础上，可以更新检查范围。

Time: O(n)
Space: O(n)
*/
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> wl = Arrays.asList(wordlist);
        int sameNum = 0;
        int tries = 0;
        while(sameNum < 6 && tries++ < 10){
            String word = wl.get(new Random().nextInt(wl.size()));
            sameNum = master.guess(word);
            
            List<String> wl2 = new ArrayList<>();
            for(String s : wl){
                if(sameLetter(s, word) == sameNum){
                    wl2.add(s);
                }
            }
            wl = wl2;
        }
        return;
    }
    private int sameLetter(String s, String t){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == t.charAt(i)) count++;
        }
        return count;
    }
}
/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */




/*
MinMax: + BFS
同样的BFS缩小范围的方法，但是我们希望能每次都最大化的缩小范围。
所以每次选取 family 最大的 word 来进行测试

Anyone who doesn't know why checking 0 match instead of 1,2,3...6 matches, please take a look at this comment. 
The probability of two words with 0 match is (25/26)^6 = 80%. 
That is to say, for a candidate word, 
we have 80% chance to see 0 match with the secret word. 
In this case, we had 80% chance to eliminate the candidate word and its "family" words which have at least 1 match. 
Additionally, in order to delete a max part of words, we select a candidate who has a big "family" (fewest 0 match with other words).

我们希望找有最大家族的，因为这样有更大可能这个 word 会和 secret 有匹配。
不然很有可能这个 word 压根就跟 secret 不匹配，那么我们其实根本就删不了什么words

Time: O(n^2)
Space: O(n)
*/
/*
MinMax:
同样的BFS缩小范围的方法，但是我们希望能每次都最大化的缩小范围。
所以每次选取 family 最大的 word 来进行测试

Anyone who doesn't know why checking 0 match instead of 1,2,3...6 matches, please take a look at this comment. 
The probability of two words with 0 match is (25/26)^6 = 80%. 
That is to say, for a candidate word, 
we have 80% chance to see 0 match with the secret word. 
In this case, we had 80% chance to eliminate the candidate word and its "family" words which have at least 1 match. 
Additionally, in order to delete a max part of words, we select a candidate who has a big "family" (fewest 0 match with other words).

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> wl = Arrays.asList(wordlist);
        int sameNum = 0;
        int tries = 0;
        while(sameNum < 6 && tries++ < 10){
            //get minmax
            Map<String, Integer> count = new HashMap<>();
            for(String s : wl) count.put(s, 0);
            
            for(int i = 0; i < wl.size(); i++){
                for(int j = i + 1; j < wl.size(); j++){
                    String w1 = wl.get(i);
                    String w2 = wl.get(j);
                    if(sameLetter(w1, w2) == 0){
                        count.put(w1, count.get(w1) + 1);
                        count.put(w2, count.get(w2) + 1);
                    }
                }
            }
            String word = "";
            int min = Integer.MAX_VALUE;
            for(Map.Entry<String, Integer> entry : count.entrySet()){
                if(entry.getValue() < min){
                    min = entry.getValue();
                    word = entry.getKey();
                } 
            }
            sameNum = master.guess(word);
            
            List<String> wl2 = new ArrayList<>();
            for(String s : wl){
                if(sameLetter(s, word) == sameNum){
                    wl2.add(s);
                }
            }
            wl = wl2;
        }
        return;
    }
    private int sameLetter(String s, String t){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == t.charAt(i)) count++;
        }
        return count;
    }
}


















/*
不好的解法：
Build Graph + DFS
先构建图，得到每个 word， 所有匹配数量的 list
target : bbb
abd = 
[
	0 = [mmm, nnn, yyy]
	1 = [ann, nbn, nnc, bbb]
	2 = [abw, ajc, aoc]
]

ann = 
[
	0 = [mmm, yyy, bbb]
	1 = ...
]
随机搜索一个 word，guess 的结果为 sameNum，则答案在 map.get(word).get(sameNum)里面，假设等于 sameNum = 1
再去核查这个 [ann, nbn, nnc, bbb] 中的所有词
这里用 DFS 显然思路不对，因为上一步已经限定死了答案肯定在 [ann, nbn, nnc, bbb] 之中。
而下一步又会检查 ann 的对应 pending list = [mmm, yyy, bbb]。
而下一步又会检查 mmm 的对应 pending list = [nnn, yyy, bbb, abw, ...]。

这样会越查越多，即使 bbb 一直都在检查范围内，但是这种检查方式使得结果还不如 O(n) 的 Brute Force solution。
做题应该先考虑 Brute Force Solution 如何，在此基础之上再来想如何优化。
*/
/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    int limit = 0;
    public void findSecretWord(String[] wordlist, Master master) {
        if(wordlist.length == 0) return;
        List<String> visited = new ArrayList<>();
        Map<String, List<List<String>>> map = new HashMap<>();
        
        //build the graph
        for(int i = 0; i < wordlist.length; i++){
            map.put(wordlist[i], new ArrayList<>());
            for(int j = 0; j <= 6; j++){//build diff list template
                map.get(wordlist[i]).add(new ArrayList<String>());
            }
        }
        
        for(int i = 0; i < wordlist.length; i++){
            for(int j = i + 1; j < wordlist.length; j++){
                String s1 = wordlist[i];
                String s2 = wordlist[j];
                int sameNum = sameLetter(s1, s2);
                map.get(s1).get(sameNum).add(s2);
                map.get(s2).get(sameNum).add(s1);
            }
        }
        System.out.println(map);
        dfs(map, wordlist[0], visited, master);
        return;
        
    }
    private boolean dfs(Map<String, List<List<String>>> map, String word, List<String> visited, Master master){
        if(limit >= 10 || visited.contains(word)) return false;
        int sameNum = master.guess(word);
        if(sameNum == 6) return true;
        
        limit++;  
        visited.add(word);
        List<String> pending = map.get(word).get(sameNum);
        System.out.println(pending);
        for(int i = 0; i < pending.size(); i++){
            if(dfs(map, pending.get(i), visited, master)) return true;
        }
        return false;
    }
    private int sameLetter(String s, String t){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == t.charAt(i)) count++;
        }
        return count;
    }
}