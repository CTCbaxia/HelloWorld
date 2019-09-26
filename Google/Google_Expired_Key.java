/*
MEDIUM
Google 删除过期 key

题目：

Create a map with expiring entries:
Example
12:00:00 - put(10, 25, 5000)
12:00:04 - get(10) -> 25
12:00:06 - get(10) -> null
思路:两个hash map，一个记录key，value pair，一个记录key的过期时间，get的时候检查key 是否过期，如果过期了，删除key返回null
Put方法有三个参数，除了key，value还有个duration

Keep a concurrent hashmap where every key has a timestamp attached.

Now expiry can be done in two ways:

Run a thread that will take a lock on map and remove expired elements from the map at regular intervals.
Remove the key while calling get: If the key is expired, remove it from the map otherwise return it's value.
*/
/*
被动删除过期 key
每次 get 的时候检查是不是到了 expire 的时间

*/
class Google_Expired_Key{
    Map<Integer, Integer> mapKey;
    Map<Integer, Long> mapTime;
    public Google_Expired_Key(){
        mapKey = new HashMap<>();
        mapTime = new HashMap<>();
    }

    public int get(int key){
        if(!mapKey.containsKey(key)) return -1;

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        long curTime = ts.getTime();
        if(mapTime.get(key) > curTime) return mapKey.get(key);
        else{
            mapKey.remove(key);
            mapTime.remove(key);
            return -1;
        }
    }
    public void put(int key, int value, long expire){//put 的时候直接 overwrite
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        long curTime = ts.getTime();
        long expTime = curTime + expire;
        mapKey.put(key, value);
        mapTime.put(key, expTime);

    }
}


/*
主动删除过期 key
Rate Limiter

expired key value pair可以用min heap，就是根据expiration的time stamp以及相应的key放到heap里面，每次要清空的时候把heap里面expiration timestamp小于当前时间的都删掉就好了
字典里key就是正常的key，value除了普通的value还包含过期的时间，
每次存key，value的时候
如果key已经存在就update value和exp time，
如果不存在就直接把value和exp time放到字典，每次取value的时候看看现在的时间和exp时间比较，如果过期了就返回null，没过期就返回值
*/
class Google_Expired_Key{
    Map<Key, Value-ExpTime> map;
    PriorityQueue<ExpTime-Key> pq;//sorted by ExpTime

    public Google_Expired_Key(){

    }
}





//第一个是给一个数组，找到三个数字, 他们相乘的乘积最大。
//是O(n)啊，乘机最大只可能是三个最大的数的乘积或者最小的两个数和最大数的乘积啊，扫一遍就可以找到这些数字了