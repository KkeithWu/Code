package Search;

public class binarySearch {
    static  int left_bound(int[] nums, int target) {
        //边界判断。
        if(nums.length == 0){
            return -1;
        }
        //这里先用左闭右开 一样的操作
        int left = 0;
        int mid = 0;
        int right = nums.length;
        //注意下条件
        while(left < right) {
            mid = left + (right - left) / 2;
            if(nums[mid] == target){
                //注意。找左边界，这里一样继续找。
                right = mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        //这里我理解也要加边界判断left是否超过或者值不一样。
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }


    public static void main(String[] args) {
        System.out.println(left_bound(new int[]{1,2,2,2,3}, 2));
    }
}
