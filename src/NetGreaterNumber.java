import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 *
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 *     For number 1 in the first array, the next greater number for it in the second array is 3.
 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 */

public class NetGreaterNumber {
    //从后往前的做法
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //单调栈
        Stack<Integer> stack = new Stack<>();
        //结果数组
        //int[] res = new int[nums2.length];
        Map<Integer,Integer> resMap = new HashMap<>(nums2.length);

        //从前往后的话，弹出就放入就好，但是从后往前，可以直接拿到全部的顺序。
        //这里从后往前遍历。输出是要前小后大
        for(int i=nums2.length-1; i>=0; i--){
            //小于当前值，弹出。
            while(!stack.isEmpty() && nums2[i] >= stack.peek()){
                stack.pop();
            }
            //结果
//            res[i] = stack.isEmpty() ? -1 : stack.peek();
            resMap.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            //将当前值放入stack中。
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for(int i=0; i<nums1.length; i++) {
            result[i] = resMap.get(nums1[i]);
        }
        return result;
    }

    //从前往后的做法。
    public int[] nextGreaterElement2(int[] findNums, int[] nums) {
        Stack < Integer > stack = new Stack < > ();
        HashMap < Integer, Integer > map = new HashMap < > ();
        int[] res = new int[findNums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && nums[i] > stack.peek())
                map.put(stack.pop(), nums[i]);
            stack.push(nums[i]);
        }
        while (!stack.empty())
            map.put(stack.pop(), -1);
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.get(findNums[i]);
        }
        return res;
    }
}

