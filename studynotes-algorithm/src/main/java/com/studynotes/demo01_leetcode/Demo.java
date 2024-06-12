package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;
import com.studynotes.entity.Node;
import com.studynotes.entity.TreeNode;

/**
 * Description:
 */
public class Demo {

    /**
     * Description: 【前缀和技巧】适用于快速、频繁地计算一个索引【区间内的元素之和】，快速、频繁是指在 O(1) 时间复杂度内完成计算。
     * <p>
     * 1、前缀和的计算：对于数组 arr，计算前缀和数组 prefixSum，其中 prefixSum[i] = arr[0] + arr[1] + ... + arr[i - 1]。
     * 2、前缀和的应用：计算索引区间 [i, j] 内元素之和，只需计算 prefixSum[j+1] - prefixSum[i] 即可。
     * <p>
     * {@link DemoArray_303} 区域和检索 - 数组不可变
     * {@link DemoArray_304} 二维区域和检索 - 矩阵不可变
     * {@link DemoArray_560#subarraySum(int[], int)} 和为K的子数组
     */
    public void prefixSum() {
    }

    /**
     * Description: 【差分数组技巧】的主要适用场景是频繁地对原始数组的某个【区间的元素进行增减】。
     * <p>
     * 1、构建差分数组（diff[i] = nums[i] - nums[i - 1]）
     * 2、对于原始数组的区间 [i, j] 的元素进行增减时，只需要对差分数组的两个端点进行修改即可
     * 3、由于区间内元素都增加 val，只需对前端点 i 增加 val，对后端点 j + 1 减少 val
     * 4、最后根据差分数组构建原始数组，即 nums[i] = diff[i] + nums[i - 1]
     * <p>
     * {@link DemoArray_370#getModifiedArray(int, int[][])} 区间加法
     * {@link DemoArray_1109#corpFlightBookings(int[][], int)} 航班预订统计
     * {@link DemoArray_1094#carPooling(int[][], int)} 拼车
     */
    public void diffArray() {
    }

    /**
     * Description: 【双指针技巧】是指在数组、链表等数据结构中，使用两个指针分别指向某个位置，从而协同完成任务的技巧。
     * <p>
     * 数组中双指针技巧的主要应用场景有两类：
     * 1、快慢指针：主要解决数组中不使用额外空间的问题，如去除重复元素、去除指定元素等。
     * 2、左右指针：主要解决数组（或字符串）中的问题，如反转数组、二分搜索、滑动窗口、两数之和等。
     * <p>
     * 快慢指针：
     * {@link DemoArray_26#removeDuplicates(int[])} 删除排序数组中的重复项
     * {@link DemoArray_27#removeElement(int[], int)} 移除元素
     * {@link DemoArray_283#moveZeroes(int[])} 移动零
     * 左右指针：
     * {@link DemoArray_167#twoSum(int[], int)} 两数之和 II - 输入有序数组
     * {@link DemoArray_344#reverseString(char[])} 反转字符串
     * {@link DemoArray_5#longestPalindrome(String)} 最长回文子串
     */
    public void arrayTwoPointers() {
    }

    /**
     * Description: 【二分搜索算法】是一种在【有序数组】中查找某一特定元素的搜索算法，通过每次将搜索区间减半来定位目标值，时间复杂度为 O(logN)。
     * <p>
     * 1、二分搜索的前提条件是有序数组，可以是升序或降序。
     * 2、二分搜索的基本思想是每次将搜索区间减半，通过比较中间元素与目标值的大小关系，确定下一步搜索的区间。
     * 3、二分搜索的关键是确定搜索区间的上下界 [left, right]，以及中间元素的索引 mid。
     * <p>
     * {@link DemoArray_704#search(int[], int)} 二分查找
     * {@link DemoArray_34#searchRange(int[], int)} 在排序数组中查找元素的第一个和最后一个位置
     * {@link DemoArray_875#minEatingSpeed(int[], int)} 爱吃香蕉的珂珂
     */
    public void binarySearch() {
    }

    /**
     * Description: 【滑动窗口技巧】是指在数组或字符串中维护一个窗口，通过移动窗口的两端来解决问题的技巧，通常用于在 O(N) 时间复杂度内解决数组/字符串的子串问题。
     * <p>
     * 1、滑动窗口的关键是确定窗口的左右边界，以及如何移动窗口的两端。
     * 2、先移动右边界，扩大窗口；直到满足某个条件后，再移动左边界，缩小窗口，直到不满足条件。
     * 3、在移动窗口的过程中，记录窗口内的一些信息，如最大值、最小值、元素之和等。
     * <p>
     * {@link DemoArray_76#minWindow(String, String)} 最小覆盖子串
     * {@link DemoArray_3#lengthOfLongestSubstring(String)} 无重复字符的最长子串
     */
    public void slidingWindow() {
    }

    /**
     * Description: 【链表双指针技巧】是指在链表中使用两个指针分别指向某个位置，从而协同完成任务的技巧。
     * <p>
     * 链表中双指针技巧的主要应用场景有两类：
     * 1、单链表只能从头到尾遍历，因此没有左右指针的概念。对于两个不同的链表，可以使用两个指针分别指向两个链表的头节点，从而协同完成任务。
     * 2、快慢指针：主要解决链表中的环路检测、中点查找等问题。
     * <p>
     * {@link DemoList_21#mergeTwoLists(ListNode, ListNode)} 合并两个有序链表
     * {@link DemoList_19#removeNthFromEnd(ListNode, int)} 删除链表的倒数第 N 个结点
     * 快慢指针：
     * {@link DemoList_876#middleNode(ListNode)} 链表的中间结点
     * {@link DemoList_141#hasCycle(ListNode)} 环形链表
     * {@link DemoList_142#detectCycle(ListNode)} 环形链表 II
     * {@link DemoList_160#getIntersectionNode(ListNode, ListNode)} 相交链表
     * {@link DemoList_206#reverseList(ListNode)} 反转链表
     */
    public void listTwoPointers() {
    }

    /**
     * Description: 【链表递归技巧】是指在链表中使用递归算法解决问题的技巧。
     * <p>
     * 链表中递归技巧的主要应用场景有两类：
     * 1、通过双指针技巧遍历链表，然后递归处理每个节点。参考 {@link DemoList_206#reverseList(ListNode)}
     * 2、递归反转链表的每个节点，直到链表末尾，然后逐层返回新链表的头节点。
     * <p>
     * {@link DemoList_206#reverseList0(ListNode)} 反转链表
     * {@link DemoList_92#reverseBetween(ListNode, int, int)} 反转链表 II
     */
    public void reserseList() {
    }

    /**
     * Description: 【栈】是一种遵循后进先出（LIFO）原则的有序集合，插入和删除操作都在栈顶进行。
     * <p>
     * Java 中提供 Stack 类来实现栈的功能
     * 栈的主要操作有：
     * 1、入栈（push）：将元素压入栈顶。
     * 2、出栈（pop）：弹出栈顶元素。
     * 3、获取栈顶元素（peek）：获取栈顶元素，不弹出。
     * <p>
     * {@link DemoStack_20#isValid(String)} 有效的括号
     * {@link DemoStack_496#nextGreaterElement(int[])} 下一个更大元素 I
     * {@link DemoStack_503#nextGreaterElements(int[])} 下一个更大元素 II
     * {@link DemoStack_739#dailyTemperatures(int[])} 每日温度
     */
    public void stack() {
    }

    /**
     * Description: 【队列】是一种遵循先进先出（FIFO）原则的有序集合，插入操作在队尾进行，删除操作在队首进行。
     * <p>
     * Java 中提供 Queue 接口来实现队列的功能，常用的实现类有 LinkedList、PriorityQueue 等。
     * 队列的主要操作有：
     * 1、入队（offer）：将元素插入队尾。
     * 2、出队（poll）：删除队首元素。
     * 3、获取队首元素（peek）：获取队首元素，不删除。
     * <p>
     * {@link DemoQueue_239#maxSlidingWindow(int[], int)} 滑动窗口最大值
     */
    public void queue() {
    }

    /**
     * Description:
     * LRU 是 Least Recently Used 的缩写，即最近最少使用。
     * {@link DemoLRU_146.LRUCache(int)} LRU 缓存机制
     * LFU 是 Least Frequently Used 的缩写，即最近最不经常使用。
     * {@link DemoLFU_460.LFUCache(int)} LFU 缓存机制
     */
    public void lruAndLfu() {
    }

    /**
     * Description: 【二叉树】是每个节点最多有两个子树的树结构，通常称为左子树和右子树。
     * <p>
     * 二叉树的遍历方式主要有三种：
     * 1、前序遍历（根左右）：根节点 -> 左子树 -> 右子树
     * 2、中序遍历（左根右）：左子树 -> 根节点 -> 右子树
     * 3、后序遍历（左右根）：左子树 -> 右子树 -> 根节点
     * <p>
     * 树的算法大多可以使用递归来解决，因为树的结构天然递归的特性。
     * 切记：不要脑子进入递归，只要相信每次递归函数会按照我们的期望工作即可。
     * <p>
     * {@link DemoTree_226#invertTree(TreeNode)} 翻转二叉树
     * {@link DemoTree_114#flatten(TreeNode)} 二叉树展开为链表
     * {@link DemoTree_116#connect(Node)} 填充每个节点的下一个右侧节点指针
     * {@link DemoTree_654#constructMaximumBinaryTree(int[])} 最大二叉树
     * {@link DemoTree_105#buildTree(int[], int[])} 从前序与中序遍历序列构造二叉树
     * {@link DemoTree_652#findDuplicateSubtrees(TreeNode)} 寻找重复的子树
     * {@link DemoTree_297#serialize(TreeNode)} 二叉树的序列化与反序列化
     */
    public void tree() {
    }

    /**
     * Description: 【二叉搜索树】是指一棵空树或者具有下列性质的二叉树：
     * 1、左子树上所有节点的值均小于它的根节点的值。
     * 2、右子树上所有节点的值均大于它的根节点的值。
     * 3、以此类推，左、右子树也分别为二叉搜索树。
     * <p>
     * 二叉搜索树的中序遍历是升序序列。
     * <p>
     * {@link DemoBST_98#isValidBST(TreeNode)} 验证二叉搜索树
     * {@link DemoBST_230#kthSmallest(TreeNode, int)} 二叉搜索树中第 K 小的元素
     * {@link DemoBST_538#convertBST(TreeNode)} 把二叉搜索树转换为累加树
     * {@link DemoBST_450#deleteNode(TreeNode, int)} 删除二叉搜索树中的节点
     * {@link DemoBST_700#searchBST(TreeNode, int)} 二叉搜索树中的搜索
     * {@link DemoBST_95#generateTrees(int)} 不同的二叉搜索树 II
     */
    public void bst() {
    }

    /**
     * Description: 【深度优先搜索/回溯算法】是一种搜索算法，它沿着树的深度遍历树的节点，尽可能深的搜索树的分支。
     * <p>
     * 1、深度优先搜索（DFS）：从根节点开始，沿着树的深度遍历树的节点，尽可能深的搜索树的分支。
     * 2、回溯算法：在搜索过程中，当搜索到某一步时，如果发现某一步不满足条件，就退回一步重新选择，直到找到满足条件的路径。
     * <p>
     * 回溯算法的基本思想是：
     * 1、从根节点开始，尝试所有可能的路径。
     * 2、如果路径不满足条件，就退回一步，重新选择。
     * 3、直到找到一条满足条件的路径，或者所有路径都试过了，算法结束。
     * <p>
     * {@link DemoDFS_51#solveNQueens(int)} 解 N 皇后问题
     * {@link DemoDFS_698#canPartitionKSubsets(int[], int)} 划分为 k 个相等的子集
     * {@link DemoDFS_200#numIslands(char[][])} 岛屿数量
     * {@link DemoDFS_1254#closedIsland(int[][])} 统计封闭岛屿的数目
     * {@link DemoDFS_695#maxAreaOfIsland(int[][])} 岛屿的最大面积
     * {@link DemoDFS_1905#countSubIslands(int[][], int[][])} 统计子岛屿
     * {@link DemoDFS_694#numDistinctIslands(int[][])} 不同的岛屿数量
     */
    public void dfs() {
    }

    /**
     * Description: 【广度优先搜索/层次遍历算法】是一种搜索算法，它沿着树的宽度遍历树的节点，尽可能广的搜索树的分支。
     * <p>
     * 1、广度优先搜索（BFS）：从根节点开始，沿着树的宽度遍历树的节点，尽可能广的搜索树的分支。
     * 2、层次遍历：从树的根节点开始，按照层次依次遍历树的节点。
     * <p>
     * 广度优先搜索算法的基本思想是：
     * 1、从根节点开始，将根节点加入队列。
     * 2、从队列中取出第一个节点，访问该节点，并将其所有未访问的子节点加入队列。
     * 3、重复步骤 2，直到队列为空。
     * <p>
     * {@link DemoBFS_111#minDepth(TreeNode)} 二叉树的最小深度
     * {@link DemoBFS_752#openLock(String[], String)} 打开转盘锁
     * {@link DemoBFS_773#slidingPuzzle(int[][])} 滑动谜题
     */
    public void bfs() {
    }

    /**
     * Description: DP 动态规划主要是用于【求最值】的题目中，通过【状态转移方程】来求解问题
     * <p>
     * 1、动态规划的核心思想是将原问题拆解成若干子问题，同时保存子问题的答案，使得每个子问题只求解一次，最终获得原问题的答案
     * 2、动态规划和递归不同点在于：动态规划是自底向上，从最简单的答案开始获取最终结果；递归是自顶向下，逐步分解问题，直到最简单的答案。
     * 3、动态规划一般脱离了递归，由循环迭代完成
     * <p>
     * 2、动态规划的一般步骤：
     * 1）找到问题的【状态】和【选择】
     * 2）明确【dp 数组/函数的定义】
     * 3）明确【base case】
     * 4）找到【状态转移方程】
     * 5）【实现】并【优化】
     * <p>
     * {@link DemoDP_509#fib(int)} 斐波那契数列
     * {@link DemoDP_322#coinChange(int[], int)} 零钱兑换
     * {@link DemoDP_931#minFallingPathSum(int[][])} 下降路径最小和
     * {@link DemoDP_300#lengthOfLIS(int[])} 最长上升子序列
     * {@link DemoDP_53#maxSubArray(int[])} 最大子序和
     * {@link DemoDP_1143#longestCommonSubsequence(String, String)} 最长公共子序列
     * {@link DemoDP_72#minDistance(String, String)} 编辑距离
     * {@link DemoDP_10#isMatch(String, String)} 正则表达式匹配
     * {@link DemoDP_2548#maxPrice(int[], int[], int)} 背包问题
     * {@link DemoDP_416#canPartition(int[])} 分割等和子集
     * {@link DemoDP_121#maxProfit(int[])} 买卖股票的最佳时机
     * {@link DemoDP_122#maxProfit(int[])} 买卖股票的最佳时机 II
     * {@link DemoDP_123#maxProfit(int[])} 买卖股票的最佳时机 III
     * {@link DemoDP_188#maxProfit(int, int[])} 买卖股票的最佳时机 IV
     * {@link DemoDP_309#maxProfit(int[])} 最佳买卖股票时机含冷冻期
     * {@link DemoDP_714#maxProfit(int[], int)} 买卖股票的最佳时机含手续费
     * {@link DemoDP_198#rob(int[])} 打家劫舍
     * {@link DemoDP_213#rob(int[])} 打家劫舍 II
     * {@link DemoDP_337#rob(TreeNode)} 打家劫舍 III
     */
    private void dynamicProgramming() {
    }
}
