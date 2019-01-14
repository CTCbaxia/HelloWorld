/*
EASY
929. Unique Email Addresses

TIME: 
RESULT: 
NOTES:
*/
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for(String email : emails){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < email.length(); i++){
                char c = email.charAt(i);
                if(c == '+'){
                    while(i + 1 < email.length() && email.charAt(i + 1) != '@'){
                        i++;
                    }
                }else if(c == '.'){
                    continue;
                }else if(c == '@'){
                    sb.append(email.substring(i));
                    break;
                }else{
                    sb.append(c);
                }
            }
            //got the email
            set.add(sb.toString());
        }
        return set.size();
    }
}