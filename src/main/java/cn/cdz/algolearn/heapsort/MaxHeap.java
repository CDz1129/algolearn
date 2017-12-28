package cn.cdz.algolearn.heapsort;

import cn.cdz.algolearn.CommonUtils;

import java.util.Random;

/**
 * @author CDz_
 * @create 2017-12-23 21:21
 * 最大堆类
 **/
public class MaxHeap<T extends Comparable> {
    //堆中元素个数
    protected int count;
    //堆容器
    protected T[] data;

    public MaxHeap(int size) {
        //这里不因为范型不能直接 初始化为一个数组，顾将object[]数组强转为T[]
        //size+1是因为，我们从1开始作为根元素，index[0]没有用到
        data = (T[]) new Comparable[size + 1];
    }

    public MaxHeap(T[] arr) {
        int length = arr.length;
        data = (T[])new Comparable[length+1];
        //将数组arr中元素，复制到data中并从index 1开始
        System.arraycopy(arr,0,data,1,length);
        this.count = length;
        heapify();
    }

    private void heapify() {
        int k = count/2;
        for (int i = k; i > 0; i--) {
            shfitDown(i);
        }
    }

    //是否为空堆
    public boolean isEmpty() {
        return count == 0;
    }

    //返回堆的长度
    public int size() {
        return count;
    }

    //返回添加成功与否
    public boolean insert(T t) {
        if (data.length <= count + 1) {
            return false;
        }
//      这里需要计算机先count++ 然后再data[count]赋值
        data[++count] = t;
        shfitUp(count);
        return true;
    }

    /**
     * 取出heap中最大元素，并删除
     *
     * @return
     */
    public T extractMax() {
        if (!isEmpty()) {
            if (count == 1) {
                return data[count--];
            }
            T item = data[1];
            CommonUtils.swap(data, 1, count--);
            shfitDown(1);
            return item;
        }
        return null;
    }

    /**
     * 获取heap中最大元素
     *
     * @return
     */
    public T getMax() {
        if (!isEmpty()) {
            return data[1];
        } else {
            return null;
        }
    }

    /**
     * 下浮操作
     * 将位于k位置的元素进行下浮操作
     *
     * @param k
     */
    private void shfitDown(int k) {
//        int left;
//        int right;
//        while ((left = 2 * k) < count) {
//            if ((right=left+1)<count
//                    &&data[left].compareTo(data[right])<=0
//                    &&data[k].compareTo(data[right])<=0){
//                CommonUtils.swap(data,right,k);
//                k = right;
//            }else if (data[right].compareTo(data[left])<=0
//                    &&data[k].compareTo(data[left])<=0){
//                CommonUtils.swap(data,left,k);
//                k = left;
//            }else {
//                break;
//            }
//        }

        /**
         * 第二种写法
         */
//        j表示，（2*k）与（2*k+1）中值最大的那个
        int j;
        while ((j = 2 * k) <= count) {
            if (j + 1 <= count
                    && data[j + 1].compareTo(data[j]) > 0)
                j++;

            if (data[j].compareTo(data[k]) <= 0) {
                break;
            }
            CommonUtils.swap(data, k, j);
            k = j;
        }
    }

    /**
     * 数组index[count]元素，进行上浮操作
     *
     * @param k 需要上浮操作的index
     */
    private void shfitUp(int k) {
        int father;
        while (k > 1
                && data[father = k / 2].compareTo(data[k]) < 0) {
            CommonUtils.swap(data, k, father);
            k = father;
        }
    }

    public static void main(String[] args) {
        PrintableMaxHeap integerMaxHeap = new PrintableMaxHeap(99);
        Random random = new Random();
        //堆中添加的个数
        int n = 30;
        for (int i = 0; i < n; i++) {
            integerMaxHeap.insert(random.nextInt(100));
        }
        integerMaxHeap.treePrint();
        int[] ints = new int[n];
        int i = 0;
        while (!integerMaxHeap.isEmpty()) {
            Integer integer = (Integer) integerMaxHeap.extractMax();
            System.out.print(integer+" ");
            ints[i++] = integer;
        }
        for (int j = 0; j < ints.length-1; j++) {
            if (ints[j]<ints[j+1]){
                System.out.println("排序失败");
            }
        }
    }

}
