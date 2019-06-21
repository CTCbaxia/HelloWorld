/*
EASY
359. Logger Rate Limiter

*/


/*
Map

Time: O(1)
Space: O(n) ---❗️需要 keep all of the old messages，空间占有高
*/
class Logger {

    /** Initialize your data structure here. */
    Map<String, Integer> map;
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)){
            map.put(message, timestamp);
            return true;
        }else{
            if(map.get(message) > timestamp - 10) return false;
            else{
                map.put(message, timestamp);
                return true;
            }
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */