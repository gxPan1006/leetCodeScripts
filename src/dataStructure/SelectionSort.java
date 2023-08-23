package dataStructure;

public class SelectionSort {
    public static void main(String[] args) {
        int[] randomNums = new int[]{8, 7, 5, 1, 3, 2, 4};
        selectionSort(randomNums);
        for (int i : randomNums) {
            System.out.println(i);
        }
    }

    public static void selectionSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int roundMin = nums[i];
            int roundMinIndex = i;
            for (int j = i; j < len; j++) {
                if (nums[j] < roundMin) {
                    roundMin = nums[j];
                    roundMinIndex = j;
                }
            }
            BubbleSort.swap(nums, i, roundMinIndex);
        }
    }
}
