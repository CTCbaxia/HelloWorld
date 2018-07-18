/*
MEDIUM
134. Gas Station
https://leetcode.com/problems/gas-station/description/
TIME: 0718 - 30min
RESULT: 19% - 47ms
NOTE:
1. 
*/
/*
SOULTION 0:
TIME: 0718 - 60min
RESULT: 100% - 0ms

思路：
如果 A 不能到 C，那么 AC 中间任意一点 B 都不可能到 C
因为如果 A 能经过 B，则 A 到 B 的 gas 存储量大于 0
但是 A 到 B 的 gas 存储量小于 0
这说明 B 到 C 的 gas 存储量一定小于 0

所以从头开始 loop，一旦累计加和小于 0， 就重新找下一个点（这中间都不可能是答案）

*/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] gasleft = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++){
            gasleft[i] = gas[i] - cost[i];
            sum = sum + gasleft[i];
        }
        if(sum < 0) return -1;
        int accumulate = 0;
        int start = 0;
        for(int i = 0; i < n ; i++){
            if(accumulate == 0){
                if(gasleft[i] < 0) continue;
                else start = i;
            }
            accumulate = accumulate + gasleft[i];
            if(accumulate < 0){
                accumulate = 0;
            }  
        }
        return start;
    }
}
/*
SOULTION 1:
TIME: 0718 - 30min
RESULT: 19% - 47ms

思路：
先加和所有 gasleft，如果总加和 > 0，则一定有方法；
然后在每个节点走 loop，
一旦起点小于 0， 就换点，
一旦加和小于 0， 就换点
*/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] gasleft = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++){
            gasleft[i] = gas[i] - cost[i];
            sum = sum + gasleft[i];
        }
        if(sum < 0) return -1;
        
        for(int i = 0; i < n; i++){
            if(gasleft[i] < 0) continue;
            int gasnum = gasleft[i];
            int move = (i == n - 1) ? 0 : i + 1;
            while(move != i){
                gasnum = gasnum + gasleft[move];
                if(gasnum < 0) break;
                move = (move == n - 1) ? 0 : move + 1;
            }
            if(move == i) return i;
        }
        return -1;
    }
}