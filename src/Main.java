import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


    }


}


class Solution {

    // 1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // 20. 有效的括号
    public boolean isValid(String s) {
        Map<Character, Character> markPairs = new HashMap<>();
        markPairs.put(')', '(');
        markPairs.put(']', '[');
        markPairs.put('}', '{');

        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            if (markPairs.containsValue(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else if (markPairs.containsKey(s.charAt(i)) && markPairs.get(s.charAt(i)) == stack.peek()) {
                stack.pop();
            } else return false;
        }
        return stack.isEmpty();
    }

    //27. 移除元素
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
            } else if(target > nums[mid]) {
                left = mid + 1;
            }  else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 二叉树的中序遍历 ===================================
    // 递归实现
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    void inorder(TreeNode treeNode, List<Integer> res) {
        if (treeNode == null) {
            return;
        }
        inorder(treeNode.left, res);
        res.add(treeNode.value);
        inorder(treeNode.right, res);
    }
    // ========================================

    // 平衡二叉树  递归判定 -------------------------------
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return false;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode node) {
        if(node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }
    // -------------------------------
    public static void main(String[] args) {
        int i = 0;
        while (i < 3) {
            System.out.println(i++);
        }
        System.out.println(i);

        System.out.println(10 << 1);
    }


}








































































