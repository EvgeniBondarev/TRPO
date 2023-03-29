import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] arr = {5, 2, 9, 1, 5, 6, 3};
        BlockSort.blockSort(arr, 2);
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 5, 5, 6, 9]


    }
}