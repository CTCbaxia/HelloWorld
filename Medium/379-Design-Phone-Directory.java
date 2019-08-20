/*
Set

Time: 
get(): O(1)
check(): O(1)
release(): O(1)

Space: 
O(n)
*/
class PhoneDirectory {
    Set<Integer> available;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        available = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){
            available.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        Iterator<Integer> it = available.iterator();
        if(it.hasNext()){
            int number = it.next();
            available.remove(number);
            return number;
        } 
        else return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return available.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        available.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */




class PhoneDirectory {
    boolean[] used;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        used = new boolean[maxNumbers];
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        for(int i = 0; i < used.length; i++){
            if(!used[i]){
                used[i] = true;
                return i;
            } 
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number > used.length) return false;
        else return !used[number];
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(number < used.length) used[number] = false;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */