package com.google.algorithm.sort;

public class Merge {
    // 归并所需要的辅助数组
    private static Comparable[] assist;

    /**
     * 比较v元素是否小于w元素
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        // 初始化辅助数组assist
        assist = new Comparable[a.length];
        // 定义一个lo变量和hi变量 分别记录数组中最小的索引和最大的索引
        int lo = 0;
        int hi = a.length - 1;
        // 调用数组重载方法完成数组a中，从索引lo到hi的元素的排序
        sort(a, lo, hi);
    }

    /**
     * 对数组a中从lo到hi的元素进行排序
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        // 做安全性检查
        if (hi <= lo) {
            return;
        }
        // 对lo到hi之间的数组进行分为两个组
        int mid = lo + (hi - lo) / 2;
        // 分别对每一个组进行排序

        sort(a, lo, mid);
        sort(a, mid + 1, hi);

        merge(a, lo, mid, hi);
    }

    /**
     * 对数组中，从lo到mid为一组，从mid+1到hi为一组，对这两组数据进行归并
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 定义三个指针
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;

        // 遍历，移动p1指针和p2指针，比较对应索引处的值，找出小的那个，放到辅助数组的对应索引处
        while (p1 <= mid && p2 <= hi) {
            // 比较对应索引处的值
            if (less(a[p1], a[p2])) {
                assist[i++] = a[p1++];
            } else {
                assist[i++] = a[p2++];
            }
        }

        // 遍历，如果p1的指针没有走完，那么顺序移动p1指针，把对应的元素放到辅助数组的对应索引处
        while (p1 <= mid) {
            assist[i++] = a[p1++];
        }
        // 遍历，如果p2的指针没有走完，那么顺序移动p2指针，把对应的元素放到辅助数组的对应索引处
        while (p2 <= hi) {
            assist[i++] = a[p2++];
        }
        //把辅助数组中的元素拷贝到原数组中
        for (int index = lo; index <= hi; index++) {
            a[index] = assist[index];
        }
    }
}
