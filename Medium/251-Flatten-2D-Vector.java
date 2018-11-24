/*
MEDIUM
251. Flatten 2D Vector

TIME: 
RESULT: 
NOTES:

*/
/*
Iterator
注意要一直循环到 it 有 next，或者到结尾真的没有next

Time: O(n)
Space: O(1)
*/
public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> list;
    Iterator<Integer> it;
    public Vector2D(List<List<Integer>> vec2d) {
        list = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        while((it == null || !it.hasNext()) && list.hasNext()){//while for this case [[],[3]]
            it = list.next().iterator();
        }
        return it != null && it.hasNext();      
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */




/*
only use hasNext + next 

Time: O(1)
Space: O(1)
*/
public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> list;
    Iterator<Integer> it;
    public Vector2D(List<List<Integer>> vec2d) {
        //if(vec2d == null) return;
        this.list = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        while((it == null || !it.hasNext()) && list.hasNext()){
            it = list.next().iterator();
        }
        return it != null && it.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

public class Vector2D implements Iterator<Integer> {
    int i, j;
    int len;
    List<List<Integer>> input;
    public Vector2D(List<List<Integer>> vec2d) {
        this.i = 0;
        this.j = 0;
        input = vec2d;
    }

    @Override
    public Integer next() {
        return input.get(i).get(j++);
    }

    @Override
    public boolean hasNext() {
        if(i >= input.size()) return false;
        else if(j < input.get(i).size()) return true;
        else{
            j = 0; 
            i++;
            return hasNext();
        }
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */