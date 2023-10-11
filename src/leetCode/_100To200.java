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

    // 151. 反转字符串中的单词
    // 先反转整个字符串。
    // 然后遍历反转后的字符串，使用双指针找到每一个单词。
    // 反转每一个单词。
    // 最后，根据需要拼接每个单词。
    // 删除多余的空格（双指针）
    // 我们使用两个指针i和j来遍历字符串。
    // 指针j用于查找非空格字符。
    // 当j找到非空格字符时，我们将这个字符复制到i的位置，并移动i。
    // 当j找到一个空格（而i指向一个单词的末尾）时，我们向i的位置复制一个空格，并移动i，而j继续移动直到找到下一个非空格字符。
    // 这个过程会将所有单词移动到最前面，并用单个空格分隔它们。
    public String reverseWords(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // Step 1: 反转整个字符串
        reverse(a, 0, n - 1);
        // Step 2: 反转每个单词
        reverseWords(a, n);
        // Step 3: 清理空格
        return cleanSpaces(a, n);
    }

    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    private void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // 跳过空格
            while (j < i || j < n && a[j] != ' ') j++; // 跳到下一个空格或者结尾
            reverse(a, i, j - 1);                      // 反转单词
        }
    }

    private String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // 跳过空格
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // 保留非空格字符
            while (j < n && a[j] == ' ') j++;             // 跳过空格
            if (j < n) a[i++] = ' ';                      // 保留一个空格
        }

        return new String(a).substring(0, i);
    }


    // 152. 乘积最大子数组
    /** 考虑到乘积可能会有负负得正的情况，我们需要保留前面的最大值maxDP和最小值minDP。
     从左到右迭代数组，对于每个位置，它的最大值和最小值只能从maxDP[i-1] * nums[i]、minDP[i-1] * nums[i]或nums[i]三者中产生。
     因此，我们可以使用动态规划的方法来记录到目前位置的最大值和最小值。
     最后的结果是所有maxDP中的最大值。*/
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] maxDP = new int[nums.length];
        int[] minDP = new int[nums.length];

        maxDP[0] = nums[0];
        minDP[0] = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                maxDP[i] = Math.max(nums[i], maxDP[i - 1] * nums[i]);
                minDP[i] = Math.min(nums[i], minDP[i - 1] * nums[i]);
            } else {
                maxDP[i] = Math.max(nums[i], minDP[i - 1] * nums[i]);
                minDP[i] = Math.min(nums[i], maxDP[i - 1] * nums[i]);
            }
            result = Math.max(result, maxDP[i]);
        }

        return result;
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


























