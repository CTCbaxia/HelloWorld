//https://www.geeksforgeeks.org/in-place-conversion-of-sorted-dll-to-balanced-bst/
import java.util.*;
import java.math.*;
class Facebook_ConvertDDLToBST {
	public static void main(String[] args) {

	}
	//class definition
	public class DoubelLinkedList{
	    int val; 
	    Node next, prev; 
	    public DoubelLinkedList(int _val) 
	    { 
	        val = _val; 
	        next = null;
	        pre = null; 
	    } 
	}
	/*
	main function here:
	就是有一个 global 变量，跟着遍历一遍head, 
	每次convertHelper(n) 就是遍历重建节点数为 n 的子树
	遍历之后，
	1. subtree 建好，返回 subtree 的root
	2. node 到达现在 n + 1 个节点，也就是 subtree 的 root 的 root
	剩下的再遍历 n - n/2 - 1 个节点，构成 right subtree
	*/
	DoubelLinkedList node = null;
	public static DoubelLinkedList convertToBST(DoubelLinkedList head){
		int n = countNode(head);//先算一共有多少个node，方便调用根据 n 大小决定走多少步的helper
		node = head;
		//find the middle point
		return convertHelper(n);
	    
	}
	public static DoubelLinkedList convertHelper(int n){//how many node in this subBST
		if(n <= 0) return null;
		DoubelLinkedList left = convertHelper(n/2);
		DoubelLinkedList root = node;
		root.pre = left;
		node = node.next;
		root.next = convertHelper(n - n/2 - 1);//left + node 已经走了 n/2 + 1 步
		return root;
	}
	public int countNode(DoubelLinkedList head){
		int count = 0;
		DoubelLinkedList node = head;
		while(node != null){
			node = node.next;
			count++;
		}
		return count;
	}
}