package cn.cdz.algolearn.simplesort;

import cn.cdz.algolearn.CommonUtils;

/**
 * User: Cdz
 * Date: 2017/10/26
 * Time: 14:23
 */
public class SimpleSort {

    /**
     * 选择排序
     *
     * @param arr
     * @return
     */
    public static void selectSortInt(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    CommonUtils.swap(arr, i, j);
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static void insertSortInt(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            //复制一个对比 元素
            int e = arr[i];
            int j;
            for (j = i; j > 0 && e < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /**
     * 插入排序 重载
     * @param arr 数组
     * @param left 左边index
     * @param right 右边index
     */
    public static void  insertSortInt(int[] arr,int left,int right){
        for (int i = left+1; i <= right; i++) {
            //复制一个对比 元素
            int e = arr[i];
            int j;
            for (j = i; j > left && e < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /**
     * 希尔排序
     * <p>
     * 插入排序的一种优化。
     * 用采用
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {


        int d = arr.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < arr.length; i = i + d) {
                    int temp = arr[i];
                    int j;
                    for (j = i - d; j >= 0 && arr[j] > temp; j = j - d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSortInt(int[] arr) {
//        boolean flag = false;//优化内循环
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    CommonUtils.swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 优化 冒泡排序
     * 优化外循环
     * 思路，每一次内循环交互完后，最后一次交互的位子，一定之后都是有序的。
     * 故记录最后一次交互的位子，就可以减少循环次数
     *
     * @param arr
     */
    public static void betterBubbleSortInt(int[] arr) {
        int i, j, pos = 0;//i 内循环下标 、j 内循环下标 pos最后一次交互的下标
        int k = arr.length;//内循环初始值

        boolean flag = false;
        for (i = 0; i < arr.length; i++) {
            flag = true;
            for (j = 1; j < k; j++) {
                if (arr[j] < arr[j - 1]) {
                    CommonUtils.swap(arr, j, j - 1);
                    //确定每次交互的位子
                    pos = j;
                    flag = false;
                }
            }
            //将内循环中最后一次交换的位子赋值给 下一次循环的限制条件k
            k = pos;

            if (flag){
                break;
            }
        }
    }






    public static void main(String[] args) {

        int[] arr = CommonUtils.getArr(10_000, 0, 10_000);
        int[] ints = CommonUtils.copeInt(arr);
        int[] ints1 = CommonUtils.copeInt(arr);
        int[] ints2 = CommonUtils.copeInt(arr);
        int[] ints3 = CommonUtils.copeInt(arr);
        CommonUtils.testArr("selectSort",SimpleSort::selectSortInt,ints3);
        CommonUtils.testArr("insertSort",SimpleSort::selectSortInt,ints2);
        CommonUtils.testArr("bubbleSortInt",SimpleSort::bubbleSortInt,ints);
        CommonUtils.testArr("betterBubbleSortInt",SimpleSort::betterBubbleSortInt,arr);
        CommonUtils.testArr("shellSort",SimpleSort::shellSort,ints1);
    }
}
