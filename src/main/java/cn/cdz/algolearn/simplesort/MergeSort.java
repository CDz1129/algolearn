package cn.cdz.algolearn.simplesort;

import cn.cdz.algolearn.CommonUtils;

/**
 * User: Cdz
 * Date: 2017/10/26
 * Time: 15:35
 */
public class MergeSort<T extends Comparable> {

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
    public static void mergeSort(Comparable[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Comparable[] arr, int left, int right) {
        if (right - left <= 15) {
            SimpleSort.insertSortInt(arr, left, right);
            return;
        }

        if (left - right >= 0) {
            return;
        }
        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        if (arr[mid].compareTo(arr[mid+1])>0) {
            merge(arr, mid, left, right);
        }

    }

    /**
     * 将数组arr[left,mid] 和数组arr[mid+1,right]归并
     *
     * @param arr
     * @param mid
     * @param left
     * @param right
     */
    private static void merge(Comparable[] arr, int mid, int left, int right) {


        Comparable[] aux = new Comparable[right - left + 1];

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
            } else if (aux[i - left].compareTo(aux[j - left]) < 0) {
                arr[k] = aux[i - left];
                i++;
            } else {
                arr[k] = aux[j - left];
                j++;
            }

        }
    }

    /**
     * 自下而上 归并排序
     *
     * @param arr
     */
    public static void mergeSortBU(Comparable[] arr) {
        int n = arr.length;
        //外循环 找出需要归并的两个数组
        for (int sz = 1; sz < n; sz *= 2) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                //对于 arr[i ~ i+sz-1]和 arr[sz ~ i+sz+sz-1]这两个数组判断合并
                merge(arr, i + sz - 1, i, Math.min(i + 2 * sz - 1, n - 1));
            }
        }
    }

    /**
     * 自下而上 归并排序
     * 常规优化，对于小于一定数量的数组排序 用 插入排序
     *
     * @param arr
     */
    public static void betterMergeSortBU(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i += 16) {
            SimpleSort.insertSortInt(arr, i, Math.min(i + 15, n - 1));
        }

        //外循环 找出需要归并的两个数组
        for (int sz = 16; sz < n; sz += sz) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                //对于 arr[i ~ i+sz-1]和 arr[sz ~ i+sz+sz-1]这两个数组判断合并
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i + sz - 1, i, Math.min(i + 2 * sz - 1, n - 1));
                }
            }
        }
    }

    public static void main(String[] args) {

        Integer[] arr = CommonUtils.getArr(10_000, 0, 10_000);
        Integer[] ints1 = CommonUtils.copeInt(arr);
        Integer[] ints = CommonUtils.copeInt(arr);
        CommonUtils.testArr("mergeSort", MergeSort::mergeSort, arr);
        CommonUtils.testArr("mergeSortBU", MergeSort::mergeSortBU, ints1);
        CommonUtils.testArr("betterMergeSortBU", MergeSort::betterMergeSortBU, ints);
    }
}
