package cn.cdz.algolearn;

import java.util.Random;
import java.util.function.Consumer;

/**
 * User: Cdz
 * Date: 2017/10/26
 * Time: 14:29
 */
public class CommonUtils {

    /**
     * 生成 数组n 在范围 left right
     *
     * @param n
     * @param left
     * @param right
     * @return
     */
    public static int[] getArr(int n, int left, int right) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = left + random.nextInt(Math.abs(left - right));
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ",");
            }
        }
    }

    /**
     * 拷贝一份数组
     *
     * @param arr
     * @return
     */
    public static int[] copeInt(int[] arr) {
        int[] ints = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = arr[i];
        }
        return ints;
    }

    /**
     * 交换数组中两个数
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 是否是一个有序的数组
     *
     * @param arr
     * @return
     */
    private static boolean isSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试 排序方法用了多少秒
     *
     * @param name
     * @param consumer
     * @param arr
     */
    public static void testArr(String name, Consumer<int[]> consumer, int[] arr) {
        long start = System.currentTimeMillis();
        consumer.accept(arr);
        long end = System.currentTimeMillis();
        if (!isSort(arr)) {
            System.out.println("排序失败");
        } else {
            System.out.println(name + "-->" + (double) (end - start) / 1000 + " s");
        }
    }
}
