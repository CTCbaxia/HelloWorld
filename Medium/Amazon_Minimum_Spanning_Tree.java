/*
Minimum Spanning Tree
https://www.jianshu.com/p/04ca0fc1afab

现在过节了，某一个地区有好多个城市（节点），这些城市都有电线互相连接，组成了一个电网（图）。
每一条电线都要花钱，而且每一条花的钱都不一样（权重）。
地方供电局想花费最小的给所有城市供电，请你给出一个解决方案。


给出一个List，Connection类有3个属性，String name1，String name2，int cost，表示在城市1和城市2之间有有个连接，费用为cost。要求在给出的List里面找出能以最小cost总和把所有城市连接起来而且没有环的Connection集合，返回一个List(要求先按城市1的名字排序，相同的按城市2)，如果找不到这样的环，或者说本来输入的List就不能把所有的城市连接起来的话，返回null。
例子1:
输入[[A,B,3],[A,C,3],[B,C,4]]，返回[[A,B,3],[A,C,3]]，因为能连起来且cost之和为6，比[[A,B,3],[B,C,4]]或[[A,C,3],[B,C,4]]的7小
例子2:
输入[[A,B,3],[A,C,3],[B,C,4],[D,E,4]]，返回null，因为不能找到把ABCDE都连起来的Connection集合，或者说连通块数量不为1
 */
/*
CTC
Time: O()
Space: O()
*/
import java.util.*;

class MST{
    public static void main(String[] args) {
        Connection c1 = new Connection("A", "D", 1);
        Connection c2 = new Connection("A", "B", 3);
        Connection c3 = new Connection("D", "B", 3);
        Connection c4 = new Connection("B", "C", 1);
        Connection c5 = new Connection("C", "D", 1);
        Connection c6 = new Connection("E", "D", 6);
        Connection c7 = new Connection("C", "E", 5);
        Connection c8 = new Connection("C", "F", 4);
        Connection c9 = new Connection("E", "F", 2);
        Connection c10 = new Connection("M", "N", 1);
        List<Connection> list1 = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
        List<Connection> list2 = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
        List<Connection> result1 = findMinimum(list1);
        for (Connection conn : result1) {
            System.out.println(conn.city1 + "-" + conn.cost + "-" + conn.city2);
        }

        List<Connection> result2 = findMinimum(list2);
        System.out.println("rs" + result2);
    }

    public static class Connection {
        String city1;
        String city2;
        int cost;
        public Connection(String a, String b, int c) {
            city1 = a;
            city2 = b;
            cost = c;
        }
    }

    public static List<Connection> findMinimum(List<Connection> list) {
        List<Connection> result = new ArrayList<>();
        if(list == null || list.size() == 0) return null;

        Collections.sort(list, new Comparator<Connection>(){
            public int compare(Connection c1, Connection c2){
                return c1.cost - c2.cost;
            }
        });
        UnionFind uf = new UnionFind();
        for(Connection c : list){
            if(uf.uf(c)){
                result.add(c);
                System.out.println(c.city1 + "-" + c.city2);

            }
        }

        Collection<Integer> numOfSet = uf.map.values();
        Set<Integer> set = new HashSet<>(numOfSet);
        if(set.size() > 1) return null; //if they cannot form a whole set

        Collections.sort(result, new Comparator<Connection>(){
            public int compare(Connection c1, Connection c2){
                if(c1.city1.equals(c2.city1)){
                    return c1.city2.compareTo(c2.city2);
                }else{
                    return c1.city1.compareTo(c2.city1);
                }
            }
        });
        return result;
    }
    public static class UnionFind{
        Map<String, Integer> map;
        int num;
        public UnionFind(){
            map = new HashMap<>();
            num = 0;
        }
        public boolean uf(Connection c){
            String city1 = c.city1;
            String city2 = c.city2;
            if(!map.containsKey(city1) && !map.containsKey(city2)){
                map.put(city1, num);
                map.put(city2, num);
                num++;
                return true;
            }else if(!map.containsKey(city1)){
                map.put(city1, map.get(city2));
                return true;
            }else if(!map.containsKey(city2)){
                map.put(city2, map.get(city1));
                return true;
            }else{
                if(map.get(city1) == map.get(city2)){//redundant connection
                    return false;
                }
                int num1 = map.get(city1);
                int num2 = map.get(city2);
                for(String city : map.keySet()){
                    if(map.get(city) == num1)
                        map.put(city, num2);
                }
                return true;
            }
        }
    }
}















//别人家的代码


public class MST {
    //给定的类
    public static class Connection {
        String city1;
        String city2;
        int cost;
        public Connection(String a, String b, int c) {
            city1 = a;
            city2 = b;
            cost = c;
        }
    }
    /**
     * 为了避免使用全局变量，声明一个UnionFind类
     */
    public static class UnionFind {
        Map<String, Integer> map; //map中装的是城市->城市所在的集合代号
        int setNum; //城市集合代号
        public UnionFind() {
            map = new HashMap<>();
            setNum = 0;
        }

        /**
         * 对每一个connection做union操作
         * 如果没有union操作，返回FALSE，如果有union操作，返回TRUE
         * 这里跟算法描述中不太一样：这里合并过的城市才会被分配集合代号
         */
        public boolean union(Connection conn) {
            // 两个城市都没有城市代号（都存在于单独的集合，都没有被合并过）
            if (!map.containsKey(conn.city1) && !map.containsKey(conn.city2)) {
                map.put(conn.city1, setNum);
                map.put(conn.city2, setNum);
                setNum++;
                return true;
            }
            // 有一个城市有代号（说明其中有一个是之前合并过的）
            if (map.containsKey(conn.city1) && !map.containsKey(conn.city2)) {
                map.put(conn.city2, map.get(conn.city1));
                return true;
            }
            if (!map.containsKey(conn.city1) && map.containsKey(conn.city2)) {
                map.put(conn.city1, map.get(conn.city2));
                return true;
            }
            // 两个都有代号（那么合并它们分别所在的集合中的所有城市）
            if (map.containsKey(conn.city1) && map.containsKey(conn.city2)) {
                int num1 = map.get(conn.city1);
                int num2 = map.get(conn.city2);
                if (num1 == num2) { //避免出现环
                    return false;
                }
                for (String city : map.keySet()) { //把city1在集合中的所有城市代号改为city2的代号
                    if (map.get(city) == num1) {
                        map.put(city, num2);
                    }
                }
                return true;
            }
            return false;
        }
    }

    /**
     * Time: O(ElogE + E) 后面的 "+E"是在union函数中，当两个城市都有代号的时候
     * Space: O(E)
     */
    public static List<Connection> findMinimum(List<Connection> list) {
        List<Connection> result = new ArrayList<>(); //最终结果，输出必须排序
        if (list == null || list.size() == 0) {
            return result;
        }
        UnionFind uf = new UnionFind();

        // 首先把边以权重来排序
        Collections.sort(list, new Comparator<Connection>() {
            @Override
            public int compare(Connection conn1, Connection conn2) {
                return conn1.cost - conn2.cost;
            }
        });

        // 遍历每一条边，进行处理
        for (Connection conn : list) {
            if (uf.union(conn)) {
                result.add(conn);
            }
        }

        // 最后把结果再排序一次
        Collections.sort(result, new Comparator<Connection>() {
            @Override
            public int compare(Connection conn1, Connection conn2) {
                if (conn1.city1.equals(conn2.city1)) {
                    return conn1.city2.compareTo(conn2.city2);
                }
                return conn1.city1.compareTo(conn2.city1);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        Connection c1 = new Connection("A", "D", 1);
        Connection c2 = new Connection("A", "B", 3);
        Connection c3 = new Connection("D", "B", 3);
        Connection c4 = new Connection("B", "C", 1);
        Connection c5 = new Connection("C", "D", 1);
        Connection c6 = new Connection("E", "D", 6);
        Connection c7 = new Connection("C", "E", 5);
        Connection c8 = new Connection("C", "F", 4);
        Connection c9 = new Connection("E", "F", 2);
        List<Connection> list = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
        List<Connection> result = findMinimum(list);
        for (Connection conn : result) {
            System.out.println(conn.city1 + "-" + conn.cost + "-" + conn.city2);
        }
    }
}
