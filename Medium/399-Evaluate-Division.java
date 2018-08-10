/*
MEDIUM
399. Evaluate Division
https://leetcode.com/problems/evaluate-division/description/

TIME: 0809 - 3h
RESULT: 75% - 2ms
NOTES: 
0. 可以只用一个 map 存所有节点的 link，再用一个 map 存对应的 value。
1. mapRight.getOrDefault(equations[i][1], new HashSet<Integer>()).add(i);   mapRight.getOrDefault 只是得到返回值，并没有把值添加到 map 里面去
2. 关于 == 和 equals:
    equations[index][1] == queries[i][1] 不能判断是否相等。因为比较的两个对象是 string，而非 primitive data。
    equations[index][1] == "c" 也不行。因为 String 类型还是存的地址，右边的 "c" 也是分配的临时地址。
3. 关于存储内容

    String a = "abc";
    String b = a; 
    b = b + "d";//将返回"abcd"对应的一个新的临时地址给 b
    System.out.println(a);//abc
    System.out.println(b);//abcd

    int[] a = {1,2}
    int[] b = a;
    b[0] = 3;//将 b[0] 对应的 int 值改变，而 a, b 对应的数组 reference 都不变
    System.out.println(a);//[3,2]
    System.out.println(b);//[3,2]   


THOUGHTS:
把左右都存成 map<Character, List>
找到从 left 出发的左右路径及其距离，看有没有可以指向 right 值的

*/
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String,Set> mapLeft = new HashMap<String,Set>();
        Map<String,Set> mapRight = new HashMap<String,Set>();
        double[] result = new double[queries.length];
        
        //save all routes in 2 maps
        for(int i = 0; i < equations.length; i++){
            Set<Integer> indexL = mapLeft.getOrDefault(equations[i][0], new HashSet<Integer>());
            indexL.add(i);
            mapLeft.put(equations[i][0], indexL);
            
            Set<Integer> indexR = mapRight.getOrDefault(equations[i][1], new HashSet<Integer>());
            indexR.add(i);
            mapRight.put(equations[i][1], indexR);
        }
        
        //find routes and calculate value for queries
        Map<String, Double> fromLeft = new HashMap<String, Double>();
        
        //for each query
        for(int i = 0; i < queries.length; i++){
            if(mapLeft.containsKey(queries[i][0]) || mapRight.containsKey(queries[i][0])){
                fromLeft.put(queries[i][0], 1.0);
                routeHelper(queries[i][0], queries[i][1], mapLeft, mapRight, fromLeft, equations, values);
            }
            if(fromLeft.containsKey(queries[i][1])) result[i] = fromLeft.get(queries[i][1]);
            else result[i] = -1.0;
            fromLeft.clear();
        }
        return result;
    }
    
    private void routeHelper(String s, String match, Map<String,Set> mapLeft, Map<String,Set> mapRight, Map<String, Double> fromLeft, String[][] equations, double[] values){
        if(!mapLeft.containsKey(s) && !mapRight.containsKey(s)) return;
        String nextMatch = s;
        //check left
        if(mapLeft.containsKey(s)){
            Iterator<Integer> it = mapLeft.get(s).iterator();
            while(it.hasNext()){
                int index = it.next();
                nextMatch = equations[index][1];
                if(fromLeft.containsKey(nextMatch)) continue;
                fromLeft.put(equations[index][1],fromLeft.get(equations[index][0]) * values[index]);
                if(nextMatch.equals(match)){
                    return;
                }else{
                    routeHelper(nextMatch, match, mapLeft, mapRight, fromLeft, equations, values);
                }
            }             
        }
        //check right
        if(mapRight.containsKey(s)){
            Iterator<Integer> it = mapRight.get(s).iterator();
            while(it.hasNext()){
                int index = it.next();
                nextMatch = equations[index][0];
                if(fromLeft.containsKey(nextMatch)) continue;
                fromLeft.put(equations[index][0],fromLeft.get(equations[index][1]) / values[index]);
                if(nextMatch.equals(match)){
                    return;
                }else{
                    routeHelper(nextMatch, match, mapLeft, mapRight, fromLeft, equations, values);
                }
            }             
        }
        return;

    }
}