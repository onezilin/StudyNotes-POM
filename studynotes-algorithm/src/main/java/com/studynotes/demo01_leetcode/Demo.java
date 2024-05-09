package com.studynotes.demo01_leetcode;

import com.studynotes.entity.ListNode;

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
}
