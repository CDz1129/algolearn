package cn.cdz.algolearn.simplesort;

import cn.cdz.algolearn.CommonUtils;

import java.util.Random;

/**
 * User: Cdz
 * Date: 2017/10/31
 * Time: 11:05
 * 快速排序
 */
public class QuickSort {


    /**
     * 快速排序的核心思想
     * <p>
     * 遍历一次就找到当前元素 排序好的结果下 所在的数组的位子
     *
     * @param arr 当前数组
     * @param l   左边边界
     * @param r   右边边界
     * @return 返回 arr[p] 使得在数组 arr[l ~ p-1] < arr[p] < arr[p+1 ~ r]
     */
    private static int partition(int[] arr, int l, int r) {
        Random random = new Random();
        int rand = l+random.nextInt(r - l + 1);
        CommonUtils.swap(arr,l,rand);
        //标记元素
        int p = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i]<p){
                j++;
                CommonUtils.swap(arr,i,j);
            }
        }
        CommonUtils.swap(arr,l,j);
        return j;
    }

    /**
     * 快速排序算法
     *
     * @param arr 数组
     * @param l   左边边界
     * @param r   右边边界
     */
    private static void quickSort(int[] arr, int l, int r) {
        //递归的结束条件
        if (l >= r) {
            return;
        }
        //返回已经排好序的index位子
        int partition = partition(arr, l, r);
        //左 右同时继续 操作
        quickSort(arr, l, partition - 1);
        quickSort(arr, partition + 1, r);
    }

    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }


    public static void main(String[] args) {
        int[] arr = CommonUtils.getArr(1_000_000, 0, 20);
//        int[] nearlyIntArray = CommonUtils.getNearlyIntArray(10_000, 20);
        CommonUtils.testArr("quickSort",QuickSort::quickSort,arr);
//        CommonUtils.testArr("quickSort",QuickSort::quickSort,nearlyIntArray);

    }
}
