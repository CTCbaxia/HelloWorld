/*
Google Onsite Interview
You have N storage Unit
you need to implement two methods:
1. markAsUsed(index)
2. findNextFree(index)


Time:
1. markAsUsed(index) - O(1)
2. findNextFree(index) - O(n)
Space: 
1. markAsUsed(index) - O(n)
2. findNextFree(index) - O(n)

Follow up: How to improve the findNextFree?
Instead of boolean array, We can use a self balanced tree, like Java's TreeSet. Then we can use map.ceiling(index) to get the next free
the tradeoff is that 
markAsUsed(index) will take O(logn)
findNextFree(index) will take O(logn)
*/
public class Get_Storage_Unit {
    static class UnitManipulation{
        boolean[] used;
        int N;
        public UnitManipulation(int N){
            used = new boolean[N];
            this.N = N;
        }
        public void markAsUsed(int index){
            //check bound
            if(index < N){
                used[index] = true;
            }
            
        }
        public int findNextFree(int index){
            while(index < N && used[index]){
                index++;
            }
            return index;
        }
    }
    
    
    public static void main(String[] args) {
        UnitManipulation um = new UnitManipulation(10);
        um.markAsUsed(1);
        um.markAsUsed(4);
        um.markAsUsed(2);
        
        System.out.println(um.findNextFree(3));//3
        System.out.println(um.findNextFree(1));//3
        System.out.println(um.used);
    }

}
