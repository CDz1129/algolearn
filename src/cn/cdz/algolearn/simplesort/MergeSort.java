package cn.cdz.algolearn.simplesort;

import cn.cdz.algolearn.CommonUtils;

import java.util.Arrays;

/**
 * User: Cdz
 * Date: 2017/10/26
 * Time: 15:35
 */
public class MergeSort {

    /**
     * 二分法排序
     * 需要操作、三个游标
     * <p>
     * 二分法，就是将一个数组 平分两份
     * 然后两份再排序 然后两个再排序
     * 再合拼
     *
     * @return
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, mid, left, right);

    }

    /**
     * 将数组arr[left,mid] 和数组arr[mid+1,right]归并
     *
     * @param arr
     * @param mid
     * @param left
     * @param right
     */
    private static void merge(int[] arr, int mid, int left, int right) {


        int[] aux = new int[right - left + 1];

        for (int i = left; i <= right; i++) {
            aux[i - left] = arr[i];
        }
        //i表示 第一个数组的开始索引
        //j表示 第二个数组的开始索引
        //k表示 整合数组的开始开始索引
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {

            if (i > mid) {
                arr[k] = aux[j - left];
                j++;
            } else if (j > right) {
                arr[k] = aux[i - left];
                i++;
            } else if (aux[i - left] < aux[j - left]) {
                arr[k] = aux[i - left];
                i++;
            } else {
                arr[k] = aux[j - left];
                j++;
            }

        }
    }

    public static void main(String[] args) {

        int[] arr = CommonUtils.getArr(100_0000, 0, 10000);

        int[] ints = Arrays.copyOfRange(arr, 10, 20);
        CommonUtils.printArr(ints);
        CommonUtils.testArr("mergeSort", MergeSort::mergeSort, arr);
    }
}
