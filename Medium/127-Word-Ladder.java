/*
MEDIUM
127. Word Ladder
https://leetcode.com/problems/word-ladder/description/

TIME: 0712
RESULT:
NOTES:
1. 字符串的等于要用 string1.equals(string2)，而非 string1 == string2。因为是 String 引用类型，所以得到的比较其实可能是比较的 address，而非 address 对应的 content
2. wordListRemain 的移除方法可能会简化比较（减少重复比较），但是问题是会使得 wordListRemain.add(s) 的结果和还未比完的 wordListRemain 不一样
   比如上一个比较是 [a,b,c] 操作一番下来却是 [a,c,b]。
   因为 b 匹配而暂时移除了 b，进行下一轮比较，但是若退回上一层， wordListRemain.add(s) 之后
   wordListRemain 的顺序已经改变，原本该比较的第三位的 c，会变成 b，结果又比较了一遍 b 而跳过了 c。
3. 用 List 来传参的时候注意 List 的永久有效性。如果只想让 List 存每一条路径的结果作为最终的过度，就该在该路径结束的 list.clear(); - 错，这样会把所有的结果都清空的...
   想要各自路径自己保留，就应该用原始类型...比如 String 这种不会被其他路径影响的。

*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 0;
        List<Integer> length = new ArrayList<Integer>();
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals(endWord)) result = 1;
        }
        if(result == 0) return 0;
        else result = 0;
        
        nextladder(beginWord, endWord, wordList, length, 1);
        
        if(length.size() == 0){
            return result;
        }else{
            result = length.get(0);
            for(int i = 0; i < length.size(); i++){
                result = Math.min(result, length.get(i));
            }
        }
        return result;
    }
    private void nextladder(String beginWord, String endWord, List<String> wordListRemain, List<Integer> length, int count){
        for(int i = 0; i < wordListRemain.size(); i++){
            
            if(isladder(beginWord, wordListRemain.get(i))){  
                
                String s = wordListRemain.get(i);
                System.out.println(wordListRemain);
                System.out.println("s:  " + s + "   count: " + count);
                if(s.equals(endWord)){
                    if(!length.contains(count + 1)) length.add(count + 1);                    
                    return;
                }else{
                    wordListRemain.remove(i);
                    
                    nextladder(s, endWord, wordListRemain, length, count + 1);
                    wordListRemain.add(s);
                }
            }
        }
        return;
    }
    private boolean isladder(String word, String wordladder){
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == wordladder.charAt(i)) continue;
            else count ++;
            
            if(count > 1) return false;
        }
        return true;
    }
}
