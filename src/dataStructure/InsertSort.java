package dataStructure;

public class InsertSort {
    public static void main(String[] args) {
        int[] randomNums = new int[]{8, 7, 5, 1, 3, 2, 4};
        insertSort(randomNums);
        for (int i : randomNums) {
            System.out.println(i);
        }
    }

    public static void insertSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int j = i - 1;
            int key = nums[i];
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }
}
