package leetCode;

import dataStructure.InsertSort;
import dataStructure.basic.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class _100To200 {
    // 121. 买卖股票 [动态规划] // TODO 动态规划
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if ((prices[i] - minPrice) > maxProfit) {
                maxProfit = (prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    // 122. 买股票的最佳时期2 [动态规划 OR 贪心]
//    public int maxProfit2(int[] prices) {
//
//    }

    // 125. 验证回文串
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    // 160. 相交链表 [hashList，]
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        HashSet<ListNode> hashSet = new HashSet<>();

        while (headA != null) {
            hashSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (hashSet.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }

    // 169. 多数元素
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            if (e.getValue() > nums.length / 2) {
                return e.getKey();
            }
        }
        return 0;
    }

    // 189. 轮转数组
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] newNums = new int[len];
        for (int i = 0; i < len; i++) {
            newNums[i + (i + k) / len] = nums[i];
        }
        System.arraycopy(newNums, 0, nums, 0, len);
    }
}


























