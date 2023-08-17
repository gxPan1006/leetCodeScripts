import java.lang.reflect.Method;
import java.util.Arrays;

public class NormalStructure {

    public static void main(String[] args) throws NoSuchMethodException {
        int[] nums = new int[]{1, 3, 2, 0, 9, 10};

        HH.quickSort(nums, 0, nums.length - 1);

        System.out.println(Arrays.toString(nums));
    }
}


class HH {
    // TODO: 快排
    public static void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = partition(nums, low, high);
            quickSort(nums, low, mid - 1);
            quickSort(nums, mid + 1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        int mid = nums[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && nums[j] >= mid) {
                j--;
            }
            while (i < j && nums[i] <= mid) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, low, i);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // TODO: 堆排序

}
