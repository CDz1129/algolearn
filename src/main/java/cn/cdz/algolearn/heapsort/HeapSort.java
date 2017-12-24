package cn.cdz.algolearn.heapsort;

import cn.cdz.algolearn.CommonUtils;

/**
 * @author CDz_
 * @create 2017-12-24 17:07
 **/
public class HeapSort {
    private HeapSort() {
    }

    public static void sort(Comparable[] arr){
        int size = arr.length;
        MaxHeap<Comparable> comparableMaxHeap = new MaxHeap<Comparable>(size);
        for (int i = 0; i < size; i++) {
            comparableMaxHeap.insert(arr[i]);
        }
        for (int i = size-1; i >= 0; i--) {
            arr[i] = comparableMaxHeap.extractMax();
        }
    }

    public static void heapifySort(Comparable[] arr){
        MaxHeap<Comparable> comparableMaxHeap = new MaxHeap<Comparable>(arr);
        for (int i = arr.length-1; i >= 0; i--) {
            arr[i] = comparableMaxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = CommonUtils.getArr(1_000_000, 0, 100);
        Integer[] integers = CommonUtils.copeInt(arr);
        CommonUtils.testArr("heapSort",HeapSort::sort,arr);
        CommonUtils.testArr("heapifySort",HeapSort::heapifySort,integers);
    }
}
