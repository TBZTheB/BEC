package fileCloned;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Finds all permutations of given array
 *
 * @author Alan Piao (<a href="https://github.com/cpiao3">git-Alan Piao</a>)
 */
public class Combination {

    private static int length;

    /**
     * Find all combinations of given array using backtracking
     *
     * @param arr the array.
     * @param n   length of combination
     * @param <T> the type of elements in the array.
     * @return a list of all combinations of length n. If n == 0, return null.
     */
    public static <T> List<TreeSet<T>> combination(T[] arr, int n) {
        Mark.print("statement${#line-of-block-in-function$8#opening-function$true");
        Mark.print("line-in-function$9#offset$351#surrounding-control-block$if");
        if ((Mark.print("line-in-function$9#offset$355#statement$n == 0") && n == 0)) {
            Mark.print("statement${#line-of-block-in-function$9");
            Mark.print("line-in-function$10#offset$377#statement$return null;");
            Mark.print("statement$}#line-of-block-in-function$9");
            Mark.print("statement$}#line-of-block-in-function$8");
            return null;
        }
        Mark.print("line-in-function$12#offset$408#statement$length=n;");
        length = n;
        Mark.print("line-in-function$13#offset$428#statement$T[] array=arr.clone();");
        T[] array = arr.clone();
        Mark.print("line-in-function$14#offset$461#statement$Arrays.sort(array);");
        Arrays.sort(array);
        Mark.print("line-in-function$15#offset$489#statement$List<TreeSet<T>> result=new LinkedList<>();");
        List<TreeSet<T>> result = new LinkedList<>();
        Mark.print("line-in-function$16#offset$543#statement$backtracking(array,0,new TreeSet<T>(),result);");
        backtracking(array, 0, new TreeSet<T>(), result);
        Mark.print("line-in-function$17#offset$601#statement$return result;");
        Mark.print("statement$}#line-of-block-in-function$8");
        return result;
    }

    /**
     * Backtrack all possible combinations of a given array
     *
     * @param arr     the array.
     * @param index   the starting index.
     * @param currSet set that tracks current combination
     * @param result  the list contains all combination.
     * @param <T>     the type of elements in the array.
     */
    private static <T> void backtracking(T[] arr, int index, TreeSet<T> currSet, List<TreeSet<T>> result) {
        Mark.print("statement${#line-of-block-in-function$9#opening-function$true");
        Mark.print("line-in-function$10#offset$424#surrounding-control-block$if");
        if ((Mark.print("line-in-function$10#offset$428#statement$index + length - currSet.size() > arr.length") && index + length - currSet.size() > arr.length)) {
            Mark.print("line-in-function$10#offset$474#statement$return;");
            return;
        }
        Mark.print("line-in-function$11#offset$490#surrounding-control-block$if");
        if ((Mark.print("line-in-function$11#offset$494#statement$length - 1 == currSet.size()") && length - 1 == currSet.size())) {
            Mark.print("statement${#line-of-block-in-function$11");
            Mark.print("line-in-function$12#offset$538#surrounding-control-block$for");
            Mark.print("line-in-function$12#offset$543#statement$int i=index");
            for (int i = index; (Mark.print("line-in-function$12#offset$558#statement$i < arr.length") && i < arr.length); i++) {
                Mark.print("statement${#line-of-block-in-function$12");
                Mark.print("line-in-function$13#offset$597#statement$currSet.add(arr[i]);");
                currSet.add(arr[i]);
                Mark.print("line-in-function$14#offset$634#statement$result.add((TreeSet<T>)currSet.clone());");
                result.add((TreeSet<T>) currSet.clone());
                Mark.print("line-in-function$15#offset$692#statement$currSet.remove(arr[i]);");
                currSet.remove(arr[i]);
                Mark.print("statement$}#line-of-block-in-function$12");
                Mark.print("line-in-function$12#offset$574#statement$i++");
            }
            Mark.print("statement$}#line-of-block-in-function$11");
        }
        Mark.print("line-in-function$18#offset$748#surrounding-control-block$for");
        Mark.print("line-in-function$18#offset$753#statement$int i=index");
        for (int i = index; (Mark.print("line-in-function$18#offset$768#statement$i < arr.length") && i < arr.length); i++) {
            Mark.print("statement${#line-of-block-in-function$18");
            Mark.print("line-in-function$19#offset$803#statement$currSet.add(arr[i]);");
            currSet.add(arr[i]);
            Mark.print("line-in-function$20#offset$836#statement$backtracking(arr,i + 1,currSet,result);");
            backtracking(arr, i + 1, currSet, result);
            Mark.print("line-in-function$21#offset$891#statement$currSet.remove(arr[i]);");
            currSet.remove(arr[i]);
            Mark.print("statement$}#line-of-block-in-function$18");
            Mark.print("line-in-function$18#offset$784#statement$i++");
        }
        Mark.print("statement$}#line-of-block-in-function$9");
    }
}
