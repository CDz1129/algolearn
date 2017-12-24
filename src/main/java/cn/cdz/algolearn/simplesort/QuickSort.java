package cn.cdz.algolearn.simplesort;

import cn.cdz.algolearn.CommonUtils;

import java.util.Random;

/**
 * User: Cdz
 * Date: 2017/10/31
 * Time: 11:05
 * 快速排序
 */
public class QuickSort<T extends Comparable> {


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
    private static int partition(Comparable[] arr, int l, int r) {
        Random random = new Random();
        int rand = l + random.nextInt(r - l + 1);
        CommonUtils.swap(arr, l, rand);
        //标记元素
        Comparable p = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(p) < 0) {
                j++;
                CommonUtils.swap(arr, i, j);
            }
        }
        CommonUtils.swap(arr, l, j);
        return j;
    }

    /**
     * 快速排序算法
     *
     * @param arr 数组
     * @param l   左边边界
     * @param r   右边边界
     */
    private static void quickSort(Comparable[] arr, int l, int r) {
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


    /**
     * 快速排序算法优化方法1
     * 两路寻找
     *
     * @param arr 数组
     * @param l   左边边界
     * @param r   右边边界
     */
    private static void quickSort2Ways(Comparable[] arr, int l, int r) {
        //递归的结束条件
        if (r - l < 16) {
            SimpleSort.insertSortInt(arr, l, r);
            return;
        }
        //返回已经排好序的index位子
        int partition = partition2Ways(arr, l, r);
        //左 右同时继续 操作
        quickSort2Ways(arr, l, partition - 1);
        quickSort2Ways(arr, partition + 1, r);
    }

    /**
     * 两路寻找，将相等的那一部分分布大于其，和小于其部分。
     * <p>
     * 思路，从数组开始的地方查找，同时也从后向前查找
     *
     * @param arr 当前数组
     * @param l   左边边界
     * @param r   右边边界
     * @return
     */
    private static int partition2Ways(Comparable[] arr, int l, int r) {
        Random random = new Random();
        int rand = l + random.nextInt(r - l + 1);
        CommonUtils.swap(arr, l, rand);
        //标记元素
        Comparable p = arr[l];
        //arr[l+1 ~ j) <= p;  arr(j ~ r] >= p
        int i = l + 1;
        int j = r;
        while (true) {
            while (i < r && arr[i].compareTo(p) < 0) {
                i++;
            }
            while (j > l + 1 && arr[j].compareTo(p) > 0) {
                j--;
            }

            if (i > j) {
                break;
            }

            CommonUtils.swap(arr, i, j);
            i++;
            j--;
        }
        CommonUtils.swap(arr, l, j);
        return j;
    }

    public static void quickSort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort2Ways(Comparable[] arr) {
        quickSort2Ways(arr, 0, arr.length - 1);
    }


    public static void main(String[] args) {
        Integer[] arr = CommonUtils.getArr(1_000_000, 0, 20);
        Integer[] nearlyIntArray = CommonUtils.getNearlyIntArray(1_000_000, 20);
//        CommonUtils.testArr("quickSort", QuickSort::quickSort, arr);
        CommonUtils.testArr("quickSort2Ways", QuickSort::quickSort2Ways, arr);
        CommonUtils.testArr("quickSort2Ways", QuickSort::quickSort2Ways, nearlyIntArray);
        CommonUtils.testArr("quickSort", QuickSort::quickSort, nearlyIntArray);

    }
}
