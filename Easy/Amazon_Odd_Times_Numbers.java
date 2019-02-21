/*
EASY
find numbers appear odd times in an array

*/
public Solution{
    public List<Integer> oddTimeNumbers(int[] nums){
        Set<Integer> single = new HashSet<>();
        for(int n : nums){
            if(single.contains(n)) single.remove(n);
            else single.add(n);
        }
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> it = single.iterator();
        while(it.hasNext()){
            result.add(it.next());
        }
        return result;
    }    
}
