/*
MEDIUM
792. Number of Matching Subsequences
https://leetcode.com/problems/number-of-matching-subsequences/description/

TIME: 0915 - 2h
RESULT: 75% - 80ms
NOTES: 
0. 想要快速解决问题，就应该用搜索的思维。
1. Collections.binarySearch(Collections c, item key)
	前提是有序
	- 若有，会返回 key 的位置
	- 若没有，会返回 (-(insertion point) - 1)，其中 insertion point 为应该插入的位置
2. for(char c = 'a'; c <= 'z'; c++) 

*/

/*
SOLUTION REFERENCE: 
TIME: 0916 - 2h
RESULT: 75% - 80ms
METHOD:
所有单词同时批量匹配
对所有的 word 建立一个正在等待的查询单词表 waiting.里面存放的就是正在等待的各个单词的队列（首字母是在等待的，后续字母是 substring）
*/
class Solution {
    public int numMatchingSubseq(String S, String[] words){
        Map<Character, Deque<String>> waiting = new HashMap<>();
        //this is necessary because you don't know how many different kinds of words in S
        for(char c = 'a'; c <= 'z'; c++){
            waiting.put(c, new LinkedList<String>());
        }
        
        //you only put the first char in all words and they may not cover all different kinds of words in words and S
        for(String str : words){
            waiting.get(str.charAt(0)).addLast(str);
        }
        
        int res = 0;
        for(char c : S.toCharArray()){
            Deque<String> dq = waiting.get(c);
            int size = dq.size();
            for(int i = 0; i < size; i++){
                String s = dq.pollFirst();
                if(s.length() == 1){
                    res++;
                }else{
                    waiting.get(s.charAt(1)).addLast(s.substring(1));
                }
            }
        }
        return res;
    }
}

/*
SOLUTION REFERENCE: 
TIME: 0915 - 2h
RESULT: 41% - 140ms
METHOD:
对 S 处理，存放 S 内每个单词所在的多个位置
对于每个 word，用 binary search 查找其每个 char 是否存在在 pre index 之后。每次找到该 char，就更新 pre index 为当前位置，然后继续往后二分法查询
*/
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        //preprocess S
        List<Integer>[] charS = new List[26];
        for(int i = 0; i < S.length(); i++){
            if(charS[S.charAt(i) - 'a'] == null) charS[S.charAt(i) - 'a'] = new ArrayList<Integer>();
            charS[S.charAt(i) - 'a'].add(i);
        }
        
        int res = 0;
        for(int i = 0; i < words.length; i++){
            char[] ch = words[i].toCharArray();
            int pre = 0;//previous index in String S
            int j = 0;
            while(j < ch.length){
                if(charS[ch[j] - 'a'] == null) break;
                List<Integer> list = charS[ch[j] - 'a'];//get the index list
                int index = Collections.binarySearch(list, pre);
                if(index < 0) index = - index - 1; //(-(insertion point) - 1)
                if(index == list.size()) break;//here should be the adjust index
                pre = list.get(index) + 1;
                j++;
            }
            if(j == ch.length) res++;//need to keep j to see if the all char has been checked
            
        }
        
        return res;
    }
}