package leetCode;

import dataStructure.basic.ListNode;
import lombok.val;

import java.util.*;

class _1To100 {

    // 1. 两数之和 [hashMap]
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{
                        map.get(target - nums[i]), i
                };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException();
    }

    // 2. 两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        var cur = ans;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            cur.next = new ListNode((x + y + carry) % 10);
            cur = cur.next;
            carry = (x + y + carry) / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) cur.next = new ListNode(carry);
        return ans.next;
    }

    // 3. 无重复字符的最长字串
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        int i = 0;
        int j = 1;
        int maxLength = 1;
        while (i < s.length() && j < s.length()) {
            if (!s.substring(i, j).contains(s.substring(j, j + 1))) {
                j++;
                if (j - i > maxLength) {
                    maxLength = j - i;
                }
            } else {
                i = s.substring(i, j).indexOf(s.substring(j, j + 1)) + i + 1;
                j++;
            }
        }
        return maxLength;
    }

    // 5. 最长回文字符串
    public static String longestPalindrome_Stupid(String s) {
        int globalMaxLength = 1;
        String globalMaxStr = String.valueOf(s.charAt(0));

        for (int i = 0; i < s.length(); i++) {
            int localMaxLength = 1;
            String localMaxStr = String.valueOf(s.charAt(i));
            StringBuilder reverseStr = new StringBuilder("");

            for (int j = i + 1; j < s.length() + 1; j++) {
                String subStr = s.substring(i, j);
                if (subStr.contentEquals(reverseStr.replace(0, reverseStr.length(), subStr).reverse())
                        && j - i > localMaxLength) {
                    localMaxLength = j - i;
                    localMaxStr = subStr;
                }
            }
            if (localMaxLength > globalMaxLength) {
                globalMaxLength = localMaxLength;
                globalMaxStr = localMaxStr;
            }
        }
        return globalMaxStr;
    }

    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[1000][1000];
        int maxLength = 1;
        String maxStr = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i + 1) == s.charAt(i)) {
                dp[i][i + 1] = true;
                maxLength = 2;
                maxStr = s.substring(i, i + maxLength);
            }
        }
        for (int len = 3; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        maxStr = s.substring(i, j + 1);
                    }
                }
            }
        }
        return maxStr;
    }

    // 9. 回文数 [回文数，数学]
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int prev = x;
        int sum = 0;
        while (x != 0) {
            sum = sum * 10 + x % 10;
            x = x / 10;
        }
        return sum == prev;
    }

    // 11. 盛水最多的容器
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = Math.min(height[0], height[1]);
        while (i < j) {
            if (Math.min(height[i], height[j]) * Math.abs(j - i) > maxArea) {
                maxArea = Math.min(height[i], height[j]) * Math.abs(j - i);
            }
            if (height[i] <= height[j]) {
                i = i + 1;
            } else if (height[j] < height[i]) j = j - 1;
        }
        return maxArea;
    }

    // 13. 罗马数字转整数 [hashMap]
    public int romanToInt(String s) {
        HashMap<String, Integer> romanMap = new HashMap<>();
        int sum = 0;
        int i = 0;
        romanMap.put("I", 1);
        romanMap.put("IV", 4);
        romanMap.put("V", 5);
        romanMap.put("IX", 9);
        romanMap.put("X", 10);
        romanMap.put("XL", 40);
        romanMap.put("L", 50);
        romanMap.put("XC", 90);
        romanMap.put("C", 100);
        romanMap.put("CD", 400);
        romanMap.put("D", 500);
        romanMap.put("CM", 900);
        romanMap.put("M", 1000);

        while (i < s.length() - 1) {
            if (romanMap.containsKey(s.substring(i, i + 2))) {
                sum += romanMap.get(s.substring(i, i + 2));
                i += 2;
            } else {
                sum += romanMap.get(s.substring(i, i + 1));
                i++;
            }
        }
        if (i != s.length()) sum += romanMap.get(s.substring(i, i + 1));
        return sum;
    }

    // 14.最长公共前缀 [双指针]
    public String longestCommonPrefix(String[] strs) {
        String ans = "";
        if (strs.length == 0) return ans;

        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || !strs[0].substring(i, i + 1).equals(strs[j].substring(i, i + 1)))
                    return ans;
            }
            ans = ans.concat(strs[0].substring(i, i + 1));
        }
        return ans;
    }

    // 15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        // 三层遍历
        // 优化1: 三个数都往前遍历，不往后遍历，i, j=i, k=j;
        // 优化2: 不重复，排序，进入的前提是和前一个不一样;
        // 优化3: 用一个右指针一次性遍历，因为第二层前一个如果满足的话，后一个的第三层匹配值一定更小。
        return new ArrayList<>();
    }

    // 20. 有效的括号 [栈、hashMap]
    public boolean isValid(String s) {
//        Stack<Character> stack = new Stack<>();
        Deque<Character> stack = new LinkedList<>();

        HashMap<Character, Character> markPairs = new HashMap<>();
        markPairs.put(')', '(');
        markPairs.put(']', '[');
        markPairs.put('}', '{');

        for (int i = 0; i < s.length(); i++) {
            if (markPairs.containsKey(s.charAt(i)) && markPairs.get(s.charAt(i)) == stack.peek()) {
                stack.pop();
            } else stack.push(s.charAt(i));
        }
        return stack.isEmpty();
    }

    // 21. 合并两个有序链表 [递归]
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }

    // 26. 删除有序数组中的重复项 [双指针]
    // [0,1,2,3,1.,2,2,3',3,4]
    public int removeDuplicates(int[] nums) {
        int i = 1;
        int j = 1;
        while (i < nums.length) {
            while (nums[j] == nums[i - 1]) {
                if (j == nums.length - 1) {
                    return i;
                }
                j++;
            }
            nums[i] = nums[j];
            i++;
        }
        return i;
    }


    // 27. 移除元素
    public int DeprecatedRemoveElement(int[] nums, int val) {
        int temp;
        int right = nums.length - 1;
        int j = 0;
        for (int i = 0; i < nums.length - j; i++) {
            if (nums[i] == val) {
                if (i + j + 1 == nums.length) return nums.length - ++j;
                while (nums[right - j] == val) {
                    j++;
                }
                temp = nums[i];
                nums[i] = nums[right - j];
                nums[right - j] = temp;
                j++;
            }
        }
        return nums.length - j;
    }

    // TODO: KMP算法(先放弃了)
    // 28. 找出字符串中第一个匹配项的下标 [KMP算法]
    public int strStr(String haystack, String needle) {
        return 1;
    }

    // 70. 爬楼梯
    public int climbStairs(int n) {
        int[] dp = new int[46];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    // 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}








































































