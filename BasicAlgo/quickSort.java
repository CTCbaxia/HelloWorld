/*
Quick Sort

Time: 
O(n^2) worst case when it is in descending order
O(nlogn) -- T(n) = 2T(n/2) + n --> T(n) = nlogn -- average case

Space: 
O(logn) - depth of the recursion
*/
public void quickSort(int[] arr, int begin, int end) {
    if (begin < end) {
        int partitionIndex = partition(arr, begin, end);
 
        quickSort(arr, begin, partitionIndex-1);
        quickSort(arr, partitionIndex+1, end);
    }
}
private int partition(int[] arr, int begin, int end) {
    int pivot = arr[end];
    int i = (begin - 1);
 
    for (int j = begin; j < end; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(arr, i, j);
        }
    }
 	swap(arr, i + 1, end);
    return i+1;
}
private void swap(int[] arr, int i, int j){
	int swapTemp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
    return;
}