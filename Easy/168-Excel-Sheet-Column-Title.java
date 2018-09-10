/*
EASY
168. Excel Sheet Column Title
https://leetcode.com/problems/excel-sheet-column-title/description/

TIME: 0909 - 1h
RESULT: 100% - 0ms
THOUGHTS:
1. 善用 char + 位移 来表示 26 个字母
2. 善于总结数学规律：
	- 最末一位每次都是 26 一循环
	- 倒数第二位每次都是 26 * 26 一循环
	- 倒数第三位每次都是 26 * 26 * 26 一循环
	...

	所以每次往前一位就直接 n / 26。而每次往前一位，就会从 1 - 26 开始。这时需要再 - 1
*/
//SOLUTION REFERENCE:
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            n--;//为了调到 0 - 25 的范围 or 多一个 level 空出来的位置
            sb.insert(0, (char)('A' + n % 26));
            n = n / 26;
        }
        return sb.toString();
    }
}