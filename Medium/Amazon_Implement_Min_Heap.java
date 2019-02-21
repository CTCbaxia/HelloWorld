/*
MEDIUM
Amazon_Implement_Min_Heap
*/

class minHeap {
    int[] heap;
    int maxSize;
    int size;
    public minHeap(int maxSize) {
        heap = new int[maxSize];
        this.maxSize = maxSize;
        size = 0;
    }

    public void insert(int num){
        int n = size;
        heap[size++] = num;

        //compare with parent
        while(heap[n] < heap[parent(n)]){
            swap(n, parent(n));
            n = parent(n);
        }
    }
    public int extractMin(){
        if(size == 0) return -1;
        return heap[0];
    }
    public int remove(){
        if(size == 0) return -1;
        int min = heap[0];
        heap[0] = heap[--size];//把最后一位移到前面来
        heap[size] = 0;//去掉最后一位

        heapify(0);
        return min;
    }
    private void swap(int p1, int p2){
        int tmp = heap[p1];
        heap[p1] = heap[p2];
        heap[p2] = tmp;
    }
    private int parent(int n){//parent index
        if(n == 0) return 0;
        else return (n - 1)/2;
    }
    private int leftChild(int n){
        return 2 * n + 1;
    }
    private int rightChild(int n){
        return 2 * n + 2;
    }
    private void heapify(int n){//把 n 的点下移到合适的位置（跟 children 里面比较小的交换位置）
        int left = leftChild(n) < size ? heap[leftChild(n)] : Integer.MAX_VALUE;
        int right = rightChild(n) < size ? heap[rightChild(n)] : Integer.MAX_VALUE;
        if(heap[n] > left || heap[n] > right){
            if(left < right){
                swap(leftChild(n), n);
                heapify(leftChild(n));
            }else{
                swap(rightChild(n), n);
                heapify(rightChild(n));
            }
        }
    }


}