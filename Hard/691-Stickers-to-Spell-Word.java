/*
H
691. Stickers to Spell Word
*/
/*
Backtracking, try every card when we need a char
countMap = for each sticker, what chars they have

maintain a curAvailable array for current available chars for current stickers
then check for every char of the target
    if curAvailable can cover that char, use that char and do backtracking to check next char
    else, try every sticker to see if we add one more sticker


n = target.length()
m = stickers.length
k = sticker.length()

Time: O(mk + n*m)
Space: O(m + n)
*/

class Solution {
    int[][] countMap;
    int res;
    public int minStickers(String[] stickers, String target) {
        if (target == null) return -1;
        if (stickers == null || stickers.length == 0) return -1;
        if (target.length() == 0) return 0;
        
        int m = stickers.length;
        countMap = new int[m][26];
        res = Integer.MAX_VALUE;
        
        for(int i = 0; i < stickers.length; i++){
            for(char c : stickers[i].toCharArray()){
                countMap[i][c - 'a']++;
            }
        }
        backtracking(0, 0, new int[26], target, m);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private void backtracking(int curCount, int index, int[] curAvailable, String target, int stickerLen){
        if(curCount > res) return;//pruning for finding min
        if(index == target.length()){
            res = curCount;//curCount must be smaller than res if it can come here
            return;
        }
        //still need to match
        char c = target.charAt(index);
        if(curAvailable[c - 'a'] > 0){//can check for next index
            curAvailable[c - 'a']--;
            backtracking(curCount, index + 1, curAvailable, target, stickerLen);
            curAvailable[c - 'a']++;//this path may fail
        }else{//need try add one more card
            for(int i = 0; i < stickerLen; i++){//try every card since can use one for unlimited times
                if(countMap[i][c - 'a'] == 0) continue;//if sticker has no c
                for(int j = 0; j < 26; j++) curAvailable[j] += countMap[i][j];//add this sticker
                backtracking(curCount + 1, index, curAvailable, target, stickerLen);//no index+1, check after
                for(int j = 0; j < 26; j++) curAvailable[j] -= countMap[i][j];//remove this sticker
            }
            //we might come here without any successful match
        }
        
    }
    
}
