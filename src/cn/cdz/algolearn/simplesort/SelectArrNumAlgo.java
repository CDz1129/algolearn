package cn.cdz.algolearn.simplesort;

import cn.cdz.algolearn.CommonUtils;

import java.util.Random;

/**
 * User: Cdz
 * Date: 2017/11/9
 * Time: 15:12
 * 算法衍生问题，找到数组中第n位的 元素
 */
public class SelectArrNumAlgo {
    public static int selectArrNum(int[] arr, int index) {
        return quickSort3Ways(arr, 0, arr.length - 1, index);
    }


    /**
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition3Ways(int[] arr, int l, int r, int index) {
        Random random = new Random();
        int rand = l + random.nextInt(r - l + 1);
        CommonUtils.swap(arr, l, rand);
        //标记元素
        int p = arr[l];

        //设置三数组边界，使得其初始化时为空数组。

        //int[l+1...lt] < p
        int lt = l;
        //int[gt...r] < p
        int gt = r + 1;
        //int[lt+1...i) = p
        int i = l + 1;

        while (gt > i) {
            if (arr[i] > p) {
                gt--;
                CommonUtils.swap(arr, i, gt);
            } else if (arr[i] < p) {
                lt++;
                CommonUtils.swap(arr, i, lt);
                i++;
            } else {
                i++;
            }
        }
        CommonUtils.swap(arr, l, lt);
        //此时 lt-1 是因为第一个元素交换后，将arr[l+1...lt]变成了--> arr[l,lt-1]
        //arr[l...lt-1] < p ; arr[lt...gt) = p ; arr[gt...r] > p
        if (lt > index) {

            return quickSort3Ways(arr, l, lt - 1, index);

        } else if (lt <= index && index < gt) {

            return arr[index];

        } else if (gt <= index) {

            return quickSort3Ways(arr, gt, r, index);

        } else {

            return 0;
        }
    }

    private static int quickSort3Ways(int[] arr, int l, int r, int index) {
        //递归的结束条件
        if (r - l <= 0) {
            return 0;
        }
        return partition3Ways(arr, l, r, index);
    }

    public static void main(String[] args) {
        int[] arr = CommonUtils.getArr(1_000_000, 100, 1_000_000);
        int[] ints = CommonUtils.copeInt(arr);
        QuickSort.quickSort3Ways(ints);
        int index = 1_000;
        int i = selectArrNum(arr, index);
        System.out.println(ints[index]);
        System.out.println(i);
    }
}
