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
        int rand = l + random.nextInt(r - l + 1);
        CommonUtils.swap(arr, l, rand);
        //标记元素
        int p = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < p) {
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


    /**
     * 快速排序算法优化方法1
     * 两路寻找
     *
     * @param arr 数组
     * @param l   左边边界
     * @param r   右边边界
     */
    private static void quickSort2Ways(int[] arr, int l, int r) {
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
     * 快速排序算法优化方法2
     * 三路查找
     *
     * @param arr 数组
     * @param l   左边边界
     * @param r   右边边界
     */
    private static void quickSort3Ways(int[] arr, int l, int r) {
        //递归的结束条件
        if (r - l < 16) {
            SimpleSort.insertSortInt(arr, l, r);
            return;
        }
        partition3Ways(arr, l, r);
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
    private static int partition2Ways(int[] arr, int l, int r) {
        Random random = new Random();
        int rand = l + random.nextInt(r - l + 1);
        CommonUtils.swap(arr, l, rand);
        //标记元素
        int p = arr[l];
        //arr[l+1 ~ j) <= p;  arr(j ~ r] >= p
        int i = l + 1;
        int j = r;
        while (true) {
            // 这里 i <= r 有等号 是保证扫描 [l+1...r]区间内所有的元素
            //arr[i] < p 不加等号，当加上等号时，若是出现 连续相等的情况，会使 生成 的树及其不平衡，进而退化到 O(n^2)
            while (i <= r && arr[i] < p) {
                i++;
            }
            // j >= l + 1 有等号 是保证扫描 [l+1...r]区间内所有元素
            // arr[j] > p 不加等号，与上同理
            while (j >= l + 1 && arr[j] > p) {
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

    /**
     * 三路查找核心，遍历一遍数组，其元素与比判断元素的比较
     * 大于 放入 arr[gt...r]中 既是 gr-- 交换(gt,i)，i 不动
     * 等于 放入 arr[lt+1...i)中 即不需要交换 但 i++
     * 小于 放入 arr[l+1...lt]中 既是 lt++ 交换(lt.i), i++
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static void partition3Ways(int[] arr, int l, int r) {
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
        quickSort3Ways(arr, l, lt-1);
        quickSort3Ways(arr, gt, r);

    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort2Ways(int[] arr) {
        quickSort2Ways(arr, 0, arr.length - 1);
    }

    private static void quickSort3Ways(int[] arr) {
        quickSort3Ways(arr, 0, arr.length - 1);
    }


    public static void main(String[] args) {
        int[] arr = CommonUtils.getArr(1_000_000, 0, 20);
        int[] nearlyIntArray = CommonUtils.getNearlyIntArray(1_000_000, 20);
        int[] arr1 = CommonUtils.getArr(1_000_000, 0, 1_000_000);
        int[] ints = CommonUtils.copeInt(arr);
        int[] ints1 = CommonUtils.copeInt(nearlyIntArray);
        int[] ints2 = CommonUtils.copeInt(arr1);
        CommonUtils.testArr("quickSort2Ways : 多数重复元素 数组", QuickSort::quickSort2Ways, arr);
        CommonUtils.testArr("quickSort2Ways : 接近有序 数组", QuickSort::quickSort2Ways, nearlyIntArray);
        CommonUtils.testArr("quickSort2Ways : 随机无序 数组", QuickSort::quickSort2Ways, arr1);
        CommonUtils.testArr("quickSort3Ways : 多数重复元素 数组", QuickSort::quickSort3Ways, ints);
        CommonUtils.testArr("quickSort3Ways : 接近有序 数组", QuickSort::quickSort3Ways, ints1);
        CommonUtils.testArr("quickSort3Ways : 随机无序 数组", QuickSort::quickSort3Ways, ints2);
    }
}
