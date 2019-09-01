/*
Google Conversion Onsite Interview
*Hard
Given input, [5, 10, 20] means the car drive in at 5, drive out at 10, with speed 20
output the average speed for every interval
[3,8,10],[6,11,20],[8,11,30] should return 
3 6 10
6 8 15
8 11 25
*/
/*
Line Sweep using active set
Time: O(nlogn)
Space: O(n)
*/
public class Main {

    public static List<int[]> avgSpeed(List<int[]> input) {
        List<int[]> list = new ArrayList<>();
        for(int[] i : input){
            list.add(new int[]{i[0], i[2]});
            list.add(new int[]{i[1], -i[2]});//so we can just use sum += i[1] to update the current sum of speed
        }
        Collections.sort(list, new Comparator<int[]>(){//ascending
           public int compare(int[] i1, int[] i2){
               return i1[0] - i2[0];
           } 
        });
        
        int size = 0;
        List<int[]> result = new ArrayList<>();
        int sum = 0, preIndex = 0;
        for(int[] i: list){
            if(size > 0 && preIndex != i[0]){//we don't put [5,5,10] into res
                result.add(new int[]{preIndex, i[0], sum / size});
            }
            if(i[1] > 0) size++;
            else size--;
            sum += i[1];//负数的速度就被减去
            preIndex = i[0];
        }
        return result;
    }
    public static void main(String[] args) {
        int[] n1 = new int[]{3,8,18};
        int[] n2 = new int[]{6,11,10};
        int[] n3 = new int[]{8,11,30};
        List<int[]> input = new ArrayList<>();
        input.add(n2);
        input.add(n3);
        input.add(n1);
        List<int[]> res = avgSpeed(input);
        for(int[] r : res){
            System.out.println(r[0] + " "+r[1] + " "+r[2]);
        }
        
    }


}
