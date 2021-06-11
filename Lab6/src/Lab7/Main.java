package Lab7;

import java.util.*;
import java.lang.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Main
{
    private static List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
    public static void main(String[] args)
    {
        getSumNeighbors(arr);
    }
    private static void getSumNeighbors(List<Integer> nums)
    {
        if (nums.size() == 2) {
            System.out.println("Intermediate Sum Values: " + nums);
            System.out.println("Final Sum = " + (nums.get(0) + nums.get(1)));
            return;
        }
        System.out.println("Intermediate Values: " + nums);

        int j = 0;
        int c = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            c = nums.get(j) + nums.get(j + 1);
            nums.remove(j);
            nums.remove(j);
            nums.add(c);
            j = 0;
            c = 0;
        }

        getSumNeighbors(nums);
    }
}
