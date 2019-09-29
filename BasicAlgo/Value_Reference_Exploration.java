/*
https://www.baeldung.com/java-hashcode
https://stackoverflow.com/questions/1894377/understanding-the-workings-of-equals-and-hashcode-in-a-hashmap
*/
//List<Integer> can be as key if we don't change list content
Map<List<Integer>, Integer> map = new HashMap<>();
List<Integer> test = new ArrayList<>();    
        test.add(0);
        test.add(0);
        test.add(0);
        test.add(1);
List<Integer> start = Arrays.asList(0, 0, 0, 1);
List<Integer> start2 = Arrays.asList(0, 0, 0, 1);
System.out.println(start.hashCode() + " "+start2.hashCode() + " "+test.hashCode());//923522 923522 923522
map.put(start, 1);
map.put(start2, 2);
map.put(test, 3);    
System.out.println(map);//{[0, 0, 0, 1]=3}
test.add(2);
System.out.println(test + " "+test.hashCode() + " "+map.get(test));//[0, 0, 0, 1, 2] 28629184 null
test.remove(test.size() - 1);      
System.out.println(test + " "+test.hashCode() + " "+map.get(test));//[0, 0, 0, 1] 923522 3
        
        




//int[] cannot be key if the array is not exactly the previous array (not the copied one)
Map<int[], Integer> map2 = new HashMap<>();
int[] num = {0,0,0,1};
int[] num2 = {0,0,0,1};
System.out.println(num.hashCode() + " "+num2.hashCode());//225534817 1878246837
map2.put(num, 2);
map2.put(num2, 3);
System.out.println(map2);//{[I@d716361=2, [I@6ff3c5b5=3}



//treenode can be key for its reference
TreeNode node = new TreeNode(0);
TreeNode node2 = new TreeNode(2);
Map<TreeNode, Integer> map3 = new HashMap<>();
map3.put(node, 0);
map3.put(node2, 2);
System.out.println(map3.get(node) + " "+map3.get(node2));//0，2
node.val = 2;//change node's value
System.out.println(map3.get(node) + " "+map3.get(node2));//0，2
TreeNode node3 = new TreeNode(0);
System.out.println(map3.get(node3));//null