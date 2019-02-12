/*
MEDIUM？
String s = "I like the new hat" -> ["I","like", "the","new","hat"]
String s = "I like the "new hat"" -> ["I", "like", "the", "new hat"]



*/
class Solution{
	int i = 0;
	public List<String> parseString(String s){
		List<String> result = new ArrayList<>();
		for(; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == '"'){
				result.add(getString(s, c));
			}else if(c == " "){
				continue;
			}else{
				result.add(getString(s, " "));
			}
		}
	}
	public String getString(String s, Character stop){
		StringBuilder sb = new StringBuilder();
		while(i < s.length() && s.charAt(i) != stop){
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
}