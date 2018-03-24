package cn.cdz.algolearn.simplesort;

import cn.cdz.algolearn.CommonUtils;

import java.util.Arrays;

/**
 * User: Cdz
 * Date: 2017/11/9
 * Time: 14:02
 * 排序算法衍生问题，求出逆序对
 */
public class InverseCountAlgo {

    public static long inverseCount(Comparable[] arr) {
        return inverse(arr, 0, arr.length - 1);
    }

    private static long inverse(Comparable[] arr, int left, int right) {

        if (left - right >= 0) {
            return 0;
        }

        int mid = (left + right) / 2;


        long leftNum = inverse(arr, left, mid);
        long rightNum = inverse(arr, mid + 1, right);
        long num = merge(arr, left, mid, right);
        return leftNum + rightNum + num;
    }


    /**
     * 对于arr[left...mid] 与 arr[mid + 1 ... right],进行归并过程 并找出 逆序对
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static long merge(Comparable[] arr, int left, int mid, int right) {

        Comparable[] aux = Arrays.copyOfRange(arr, left, right + 1);

        long inverseNum = 0;
        int k;
        int i = left;
        int j = mid + 1;
        for (k = left; k <= right; k++) {
            if (i > mid) {
                // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - left];
                j++;
            } else if (j > right) {
                // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - left];
                i++;
            } else if (aux[i - left].compareTo(aux[j - left]) <= 0){
                // 左半部分所指元素 <= 右半部分所指元素
                arr[k] = aux[i - left];
                i++;
            } else {   // 右半部分所指元素 < 左半部分所指元素
                arr[k] = aux[j - left];
                j++;
                // 此时, 因为右半部分k所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对
                // 左半部分此时未处理的元素个数为 mid - i + 1
                inverseNum += (long) (mid - i + 1);
            }
        }
        return inverseNum;
    }


    public static void main(String[] args) {
        Integer[] arr = CommonUtils.getArr(1_000_000, 0, 1_000_000);
        Integer[] nearlyIntArray = CommonUtils.getNearlyIntArray(100_000, 0);
        long i = inverseCount(arr);
        CommonUtils.testArr("inverseCountAlgo",InverseCountAlgo::inverseCount,arr);
        System.out.println(i);

    }
}
