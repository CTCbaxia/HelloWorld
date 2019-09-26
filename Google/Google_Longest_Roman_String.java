/*
Given a string made up with ['I','V','X','L','C','D','M']
Greedyly parse the string to several longest roman char and return a list of int for the represented num

I: 1
V: 5
X: 10
L: 50
C: 100 
D: 500
M: 1000

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.

example:
input: "CIVIC"
output: [104, 1, 99]
CIV is the longest, 
IC is the next longest
*/

/*
Greedy + Map to transfer

Time: O(n)
Space: O(1)
*/
public List<Integer> findLongestRomanSubstring(String s){
  Map<Character, Integer> map = new HashMap<>();
  map.put('I', 1);
  map.put('V', 5);
  map.put('X', 10);
  map.put('L', 50);
  map.put('C', 100);
  map.put('D', 500);
  map.put('M', 1000);

  List<Integer> res = new ArrayList<>();
  int i = 0;
  while(i < s.length()){
    int num = 0, pre = 10000, countPre = 0;
    boolean reverse = false;
    while(i < s.length()){
      int cur = map.get(s.charAt(i));
      if(cur < pre){
        num += cur;
        countPre = 1;
        pre = cur;
        reverse = false;
      }else if(cur == pre){
        if(reverse) break;// "CMC" is not valid, "CM" cannot be followed by C
        else{
          countPre++;
          if(countPre < 4) num += cur;
          else break;//have more than 3 same char continuously
        }
      }else{
        if(!reverse && ((pre == 1 && (cur == 5 || cur == 10)) 
        || (pre == 10 & (cur == 50 || cur == 100)) 
        || (pre == 100 && (cur == 500 || cur == 1000)))){
          num += cur - 2 * pre;
          reverse = true;
        }else{
            break;
        }
      }
      i++;//can go to the next char
    }
    res.add(num);

  }
  return res.get(0);
}


