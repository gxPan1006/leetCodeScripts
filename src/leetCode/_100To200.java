package leetCode;

import dataStructure.InsertSort;
import dataStructure.basic.ListNode;
import dataStructure.basic.TreeNode;

import java.util.*;

public class _100To200 {
    // 104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 105. 从前序与中序遍历序列构造二叉树
    int preIdx = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idxMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // Build a hashmap to store value -> its index relations
        int idx = 0;
        for (Integer val : inorder) {
            idxMap.put(val, idx++);
        }
        return constructTree(0, inorder.length);
    }

    private TreeNode constructTree(int inLeft, int inRight) {
        // if there is no elements to construct subtrees
        if (inLeft == inRight)
            return null;

        // pick up preIdx element as a root
        int rootVal = preorder[preIdx];
        TreeNode root = new TreeNode(rootVal);

        // root splits inorder list into left and right subtrees
        int index = idxMap.get(rootVal);

        // recursion
        preIdx++;
        // build left subtree
        root.left = constructTree(inLeft, index);
        // build right subtree
        root.right = constructTree(index + 1, inRight);
        return root;
    }

    // 106. 从中序与后序遍历序列构造二叉树
    int postIndex;
    int[] inOrder, postOrder;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        this.inOrder = inorder;
        this.postOrder = postorder;
        int len = postorder.length;
        postIndex = len - 1;

        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return constructByInAndPostOrder(0, len );
    }

    private TreeNode constructByInAndPostOrder(int start, int end) {
        if (start == end) return null;

        int rootValue = postOrder[postIndex];
        int idxInInOrder = map.get(rootValue);
        TreeNode root = new TreeNode(rootValue);
        postIndex--;
        constructByInAndPostOrder(idxInInOrder + 1, end);
        constructByInAndPostOrder(start, idxInInOrder);
        return root;
    }

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

    // 128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int longest = 0;
        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curLen = 1;
                while (set.contains(curNum + 1)) {
                    curNum++;
                    curLen++;
                }
                longest = Math.max(longest, curLen);
            }
        }
        return longest;
    }

    // 134. 加油站
    // 总油量大于总消耗量，唯一起点，前置点作为起点一定过不来
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];

            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        if (totalGas < totalCost) return -1;
        return start;
    }

    // 146. LRU缓存
    static class LRUCache {
        private int capacity;
        private Map<Integer, Node> map = new HashMap<>();
        private LinkedList<Node> cache = new LinkedList<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            int val = map.get(key).val;
            // 利用 put 方法把该数据提前
            put(key, val);
            return val;
        }

        public void put(int key, int value) {
            Node x = new Node(key, value);
            if (map.containsKey(key)) {
                // 删除旧的节点，新的插到头部
                cache.remove(map.get(key));
                cache.addFirst(x);
                // 更新 map 中对应的数据
                map.put(key, x);
            } else {
                if (cache.size() == capacity) {
                    // 删除链表最后一个数据
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
                // 直接添加到头部
                cache.addFirst(x);
                map.put(key, x);
            }
        }
    }

    static class Node {
        int key, val;
        Node prev, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

    }

    // 150. 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+" -> stack.push(num1 + num2);
                    case "-" -> stack.push(num1 - num2);
                    case "*" -> stack.push(num1 * num2);
                    case "/" -> stack.push(num1 / num2);
                    default -> {
                    }
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
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

    // 167. 两数之和 II [双指针]
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
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


























