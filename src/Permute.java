import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Permute {
    public static void main(String[] args) {

        List<List<Integer>> result = permute(new int[]{1, 2, 3});
        System.out.println(result);
    }

    public static List<List<Integer>> permute(int[] nums){
        List<Integer> param = new ArrayList<>();
        for(int num: nums){
            param.add(num);
        }
        List<List<Integer>> result = new ArrayList<>();
        result = permuteSolution(result, param, 0);
        return result;
    }

    public static List<List<Integer>> permuteSolution(List<List<Integer>> result, List<Integer> list, int index){
        //加入集合中。
        if(index + 1 == list.size()) {
            result.add(new ArrayList<>(list));
        }
        for(int i = index; i < list.size(); i++){
            //进行选择
            int tmp = list.get(i);
            list.set(i, list.get(index));
            list.set(index, tmp);
            //递归选择后面的操作
            permuteSolution(result, list, index+1);

            //还原
            tmp = list.get(i);
            list.set(i, list.get(index));
            list.set(index, tmp);
        }
        HashMap map = new HashMap();
        return result;
    }


}
