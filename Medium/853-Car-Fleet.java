/*
MEDIUM
853. Car Fleet

*/
/*
Keep track of the slowest time: 数组增减关系对比：
Calculate time needed to arrive the target, sort by the start position.
Loop on each car from the end to the beginning. arriveTime = current biggest time (the slowest).

1. If the car needs less or equal time than arriveTime, it can catch up this car.
2. Otherwise it will become the new slowest car, that is new lead of a car fleet.


Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        TreeMap<Integer, Double> map = new TreeMap<>();
        int n = position.length;
        for(int i = 0; i < n; i++){
            double time = (double) (target - position[i]) / speed[i];
            map.put(position[i], time); 
        }
        
        int result = 0;
        double arriveTime = -1;
        for(int p : map.descendingKeySet()){
            if(map.get(p) > arriveTime){//new car fleet
                arriveTime = map.get(p);
                result++;
            }
        }
        return result;
    }
}
/*
数组增减关系对比：
先转化成 position 关系。
算每个 car 要到达 target 的原有时间 time
从后往前（因为不能 pass a car）
arriveTime = 当前的 carfleet 到达时间
如果一个车所需时间 <= arriveTime，那么他会在到达终点前追上这个 car fleet，被融合进去，同样时间到达。
如果一个车所需时间 > arriveTime，那么他会产生一个新的 car fleet，他后面的车不可能比他更快到达终点
*/
