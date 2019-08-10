/*
1. map all users record:

    Map<user, List<int[]>> 
        ||          ||
    each user   [timestamp value, index]
                         |           |
                     to sort     to get web name

2. find all possible combination and build:

    Map<String, Integer> "home,about,career" as key, count how many users* saw that 
    * one user can only be counted once, so a seen set will be used for each user

u: number of distinct users
w: number of webs for one users
Time: O(N + u(wlogw + w^3) + w^3)
Space: O(uw + w^3)
*/
class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int N = username.length;
        
        // build users map
        Map<String, List<int[]>> users = new HashMap<>();
        for(int i = 0; i < N; i++){
            String un = username[i];
            if(!users.containsKey(un)) users.put(un, new ArrayList<>());
            users.get(un).add(new int[]{timestamp[i], i});
        }
        // create a comparator to sort webs according to the timestamp
        Comparator<int[]> comparator = new Comparator<int[]>(){
            public int compare(int[] n1, int[] n2){
                return n1[0] - n2[0];
            }
        };
        Map<String, Integer> count = new HashMap<>();
        for(Map.Entry<String, List<int[]>> entry : users.entrySet()){ // loop for each user to try all combinations for a sequence
            List<int[]> webs = entry.getValue();
            Collections.sort(webs, comparator);  // sort the webs according to the time
            Set<String> seen = new HashSet<>(); // one user can only be counted once
            for(int i = 0; i < webs.size(); i++){
                for(int j = i + 1; j < webs.size(); j++){
                    for(int k = j + 1; k < webs.size(); k++){
                        String sequence = website[webs.get(i)[1]] + ","+ website[webs.get(j)[1]] + "," + website[webs.get(k)[1]];
                        if(seen.add(sequence)){
                            count.put(sequence, count.getOrDefault(sequence, 0) + 1);
                        }
                    }
                }
            }
        }
        //compare to get result 
        int maxSeen = 0;
        String res = "";
        for(Map.Entry<String, Integer> entry : count.entrySet()){
            String web = entry.getKey();
            int n = entry.getValue();
            if(n > maxSeen){
                maxSeen = n;
                res = web;
            }else if(n == maxSeen){
                if(web.compareTo(res) < 0) res = web; //lexicographically min
            }           
        }

        return Arrays.asList(res.split(","));
    }
}



// Build user map so that each user has a list of webs they visited.
// Map<String, List<int[]>>
// I use List here because I can sort it and build all combination of the 3 sequence easily using a list. Since the website is a string, I use an array [timestamp, index] to represent the timestamp and the corresponding web. By doing this, we can get the web name easily using website[index] later. An alternative way is to create a class.

// Then just loop every user to try all combinations of the web for that user, build a count map to count for each sequence
// String sequence
// Use comma to seperate the webs in a sequence so it can be used as a key in hashmap.
// Also we just need to compare the whole string to find the lexicographically min result
// Comparator
// Need to sort the web list
// Set seen
// For a specific sequence, each user can only be counted once
// Map<String, Integer>
// Count for each sequence

// Finally just to compare to get the result. Keep in mind that if the count of the sequence is equal to the max count, we need to compare the sequence string to keep the lexicographically min result

