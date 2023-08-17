package leetCode;

import dataStructure.basic.ListNode;

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

        if (list1.value <= list2.value) {
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
            while (nums[j] == nums[i-1]) {
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

    // TODO: KMP算法
    // 28. 找出字符串中第一个匹配项的下标 [KMP算法]
    public int strStr(String haystack, String needle) {
        return 1;
    }

    // TODO: 快速排序

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
    public static void main(String[] args) {
        int i = 0;
        while (i < 3) {
            System.out.println(i++);
        }
        System.out.println(i);

        System.out.println(10 << 1);
    }
}








































































