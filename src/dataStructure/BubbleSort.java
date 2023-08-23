package dataStructure;

// 冒泡排序
public class BubbleSort {
    public static void main(String[] args) {
        int[] randomNums = new int[]{8, 7, 5, 1, 3, 2, 4};
        bubbleSort(randomNums);
        for (int i: randomNums) {
            System.out.println(i);
        }
    }

    public static void bubbleSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            boolean sorted = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    sorted = true;
                }
            }
            if (!sorted) break;
        }
    }

    public static void swap(int[] nums, int m, int n) {
        int temp;
        temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }
}
