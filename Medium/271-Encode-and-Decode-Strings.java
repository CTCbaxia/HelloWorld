/*
M
271. Encode and Decode Strings
*/
/*
Using len as head of each string part, so we can know how long is the string, and save the whole string

Time: O(n)
Space: O(1)
*/
public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.size(); i++){
            String s = strs.get(i);
            sb.append(s.length()).append(":").append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            int end = s.indexOf(':', i);
            int len = Integer.valueOf(s.substring(i, end));
            i = end + 1 + len;
            res.add(s.substring(end + 1, i));
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));


/*
Using len as head of each string part, so we can know how long is the string, and save the whole string

Time: O(n)
Space: O(1)
*/
public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.size(); i++){
            String s = strs.get(i);
            sb.append(s.length()).append(":").append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            int len = 0;
            while(i < s.length() && s.charAt(i) != ':'){
                len = len * 10 + (s.charAt(i++) - '0');
            }
            i++;//skip colon
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < len; k++){
                sb.append(s.charAt(i++));
            }
            res.add(sb.toString());
            i--;//move back
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
