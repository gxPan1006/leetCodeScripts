package dataStructure;

import static dataStructure.BubbleSort.swap;

public class ShellSort {
    public static void shellSort(int[] arr) {
        int len = arr.length;

        // 初始增量为数组长度的一半，并逐渐减小
        for (int gap = len / 2; gap > 0; gap /= 2) {

            // 以下的代码实质上是一个插入排序，但是在比较和交换时，我们使用的间隔是gap而不是1
            for (int i = gap; i < len; i++) {

                // 记住当前的元素
                int temp = arr[i];

                // 初始化一个新的指针
                int j;

                // 内部循环，实质上是插入排序的部分，但使用的间隔是gap
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    // 当前元素太大，使其向后移动
                    arr[j] = arr[j - gap];
                }

                // 找到正确的位置来插入当前元素
                arr[j] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 7, 6, 5};
        shellSort(nums);
    }
}
