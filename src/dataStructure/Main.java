package dataStructure;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        int[] nums = new int[]{1, 3, 2, 0, 9, 10};

        QuickSort.quickSort(nums, 0, nums.length - 1);

        System.out.println(Arrays.toString(nums));
    }
}



