/*
HARD
269. Alien Dictionary

TIME: 
RESULT: 
*/
/*
Topological Sort
map: <char A, set of chars after charA>
degree: has every char, and its degree 

1. first put every char into degree
w: 0
r: 0
...

2. then compare neighbor words, their first differen char at same index have order information
 "wrt",
 "wrf",
    first different character is 3rd letter, so t comes before f
 -update map and degree
 -break after found difference
 
3. Topo BFS
put every char with degree 0 into queue
poll char from queue and update char's map set's degree

Time: O(n) n is total len of every word
Space: O(key)
*/
class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();//char A, set of chars after A
        Map<Character, Integer> degree = new HashMap<>();//degree of each char
        String result = "";
        if(words.length == 0) return result;
        for(String s : words){//put every char in dictionary into degree
            for(char c : s.toCharArray()){
                degree.put(c, 0);
            }
        }
        //put chars to map and degree
        for(int i = 1; i < words.length; i++){
            String s1 = words[i - 1];
            String s2 = words[i];
            
            //compare the first different char between s1 and s2
            int len = Math.min(s1.length(), s2.length());
            for(int j = 0; j < len; j++){
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if(c1 != c2){
                    /*
                    get order information here: c1 < c2
                    1. update map: put c2 into c1's map set
                    2. update degree: increase c2's degree
                    */
                    if(!map.containsKey(c1)) map.put(c1, new HashSet<Character>());
                    Set<Character> after = map.get(c1);
                    if(!after.contains(c2)){
                        after.add(c2);//add to c1's after set
                        degree.put(c2, degree.get(c2) + 1);//increase degree as we found one more before
                    }
                    break;
                }
            }
        }
        //Topological Level Order Traversal
        Queue<Character> q = new LinkedList<>();
        for(char c : degree.keySet()){
            if(degree.get(c) == 0) q.offer(c);//don't have char that should be before them
        }
        
        while(!q.isEmpty()){
            char c = q.poll();
            result += c;
            if(map.containsKey(c)){//if c has "after" set, every c2 in "after" should have degree-1
                for(char c2 : map.get(c)){
                    degree.put(c2, degree.get(c2) - 1);
                    if(degree.get(c2) == 0) q.offer(c2);//if degree is now 0, can put in queue
                }
            }
        }
        return result.length() == degree.size() ? result : "";//有些成环的不会degree=0,所以不可能
    }
}









/*
topological sort
pre set indegree of all char in the dict to be 0
check word with its next in sorted dictonary to get the order between two char this sort based, and recored degree for second char
#for example, compare "wrt" and "wrf" we know that order of 't' should be before 'f', indegree['f']++;

then topolgical sort
    1) put char to queue if its indegree is 0
    2) while queue is not empty, pop front out as c
        # save this c to res
        # for each c, decrease the indegree of post saved in pre map
        # if post's degree become 0, push it to queue
    3) if size of res is not equal to size of indegree, we can not get order, return ""
    
time complexity(O(N)) n is total number of char of all words
*/

class Solution {
public:
    string alienOrder(vector<string>& words) {
        if (words.size() == 1) return words[0];
        unordered_map<char, unordered_set<char>> pre;
        unordered_map<char, int> indegree;
        
        for (string word : words) {
            for (char c : word) 
                indegree[c] = 0; // set each char appeared to be 0 in indegree map
        }
        string  &first  = words[0];
        for (int i = 1; i < words.size(); i++) { // check adjacent words pair by pair
            string &second = words[i];
            int length = min(first.size(), second.size());
            for (int j = 0; j < length; j++) { // searching for the reason for order between word first and second
                if (first[j] != second[j]) { // found it
                    if (!pre[first[j]].count(second[j])) {
                        pre[first[j]].insert(second[j]);
                        //if (!indegree.count(first[j]))
                            //indegree[first[j]] = 0;
                        indegree[second[j]]++;
                    }
                    break;
                }
            }
            first = second;
        }
        
        queue<char> q;
        
        for (auto it : indegree) { // put char to queue if its indegree is 0
            if (it.second == 0) q.push(it.first);
        }
        string res = "";
        while(!q.empty()) { // topological sort
            char c = q.front();
            q.pop();
            res += c;
            for(auto post : pre[c]) {
                indegree[post]--;
                if (indegree[post] == 0) q.push(post); // all pre char has been dealt with
            }
        }
        
        if (res.size() != indegree.size()) return "";// if size of res is not equal to size of indegree, we can not get order, return ""
        return res;
        
        
        
    }
};