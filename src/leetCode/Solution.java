package leetCode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    // 1 2 12 15 14 3 6 7 5         5 7 1 2 3 4
    Map<Integer, Integer> map = new HashMap<>();
    int len;

    public int findMinCost(int[] nums) {
        len = nums.length;
        for (int i = 0; i < len; i++) {
            map.put(nums[i], i);
        }
        int startPos = 0;
        int middleValueIdx = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] == nums[i + 1] - 1) {
                startPos++;
            } else {
                middleValueIdx = findMiddleVal(startPos + 1);
            }
        }
        return middleValueIdx - startPos;
    }

    private int findMiddleVal(int pos) {
        for (int i = pos - 1; i < len; i++) {
            if (i == pos) {
                return map.get(i);
            }
        }
        return len;
    }
}
