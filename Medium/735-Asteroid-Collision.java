/*
MEDIUM
735. Asteroid Collision
https://leetcode.com/problems/asteroid-collision/description/

TIME: 0806 - 1H
RESULT: 85% - 19ms
NOTES:
1. Deque<Integer> tmpstack = new ArrayDeque<Integer>();
2. 关于速度：
    1. foreach 迭代比 while pop 慢一些？？https://stackoverflow.com/questions/18923021/why-is-queue-poll-faster-than-iteration-java-util-concurrent-concurrentlinkedq
    2. stack 比 deque 慢一些？


THOUGHT:
只要正数的右边有负数，就会有 collision 

REFERENCE:
1. iterate-through-stack-java
http://www.techiedelight.com/iterate-through-stack-java/
*/

// 19ms
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> tmpstack = new ArrayDeque<Integer>();
        for(int i = 0; i < asteroids.length; i++){
            int n = asteroids[i];
            if(tmpstack.size() == 0) tmpstack.offer(n);
            else{
                if(tmpstack.peekLast() > 0 && n < 0){
                    if(tmpstack.peekLast() > Math.abs(n)) continue;
                    else if(tmpstack.peekLast() == Math.abs(n)) tmpstack.pollLast();
                    else if(tmpstack.peekLast() < Math.abs(n)){
                        tmpstack.pollLast();
                        i--;
                    }
                }else{
                    tmpstack.offer(n);
                }
            }
        }
        int[] result = new int[tmpstack.size()];
        int index = 0;
        while(tmpstack.size() != 0) result[index++] = tmpstack.pollFirst();// while pop
        return result;
    }

}

// 25ms
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> tmpstack = new ArrayDeque<Integer>();
        for(int i = 0; i < asteroids.length; i++){
            int n = asteroids[i];
            if(tmpstack.size() == 0) tmpstack.offer(n);
            else{
                if(tmpstack.peekLast() > 0 && n < 0){
                    if(tmpstack.peekLast() > Math.abs(n)) continue;
                    else if(tmpstack.peekLast() == Math.abs(n)) tmpstack.pollLast();
                    else if(tmpstack.peekLast() < Math.abs(n)){
                        tmpstack.pollLast();
                        i--;
                    }
                }else{
                    tmpstack.offer(n);
                }
            }
        }
        int[] result = new int[tmpstack.size()];
        int index = 0;
        for(Integer i: tmpstack) result[index++] = i;// foreach iterator
        
        return result;
    }

}





/*
USING STACK 
TIME: 0806 - 40min
RESULT: 43% - 26ms
*/

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> tmpstack = new Stack<Integer>();
        for(int i = 0; i < asteroids.length; i++){
            stackHelper(tmpstack, asteroids[i]);
        }
        int[] result = new int[tmpstack.size()];
        int index = 0;
        for(Integer i: tmpstack) result[index++] = i;
        return result;
    }
    private void stackHelper(Stack<Integer> tmpstack, int n){
        if(tmpstack.empty()) tmpstack.push(n);
        else{
            if(tmpstack.peek() > 0 && n < 0){
                if(tmpstack.peek() > Math.abs(n)) return;
                else if(tmpstack.peek() == Math.abs(n)) tmpstack.pop();
                else if(tmpstack.peek() < Math.abs(n)){
                    tmpstack.pop();
                    stackHelper(tmpstack, n);
                }
            }else{
                tmpstack.push(n);
            }
        }
        return;
    }
}




class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> tmpstack = new Stack<Integer>();
        for(int i = 0; i < asteroids.length; i++){
            int n = asteroids[i];
            if(tmpstack.empty()) tmpstack.push(n);
            else{
                if(tmpstack.peek() > 0 && n < 0){
                    if(tmpstack.peek() > Math.abs(n)) continue;
                    else if(tmpstack.peek() == Math.abs(n)) tmpstack.pop();
                    else if(tmpstack.peek() < Math.abs(n)){
                        tmpstack.pop();
                        i--;//下次循环再比对一遍
                    }
                }else{
                    tmpstack.push(n);
                }
            }
        }
        int[] result = new int[tmpstack.size()];
        int index = 0;
        for(Integer i: tmpstack) result[index++] = i;
        return result;
    }

}
