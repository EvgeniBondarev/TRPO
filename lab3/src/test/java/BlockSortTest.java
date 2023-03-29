import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BlockSortTest {
    @Test
    public void nullTest(){
        int[] arr = null;
        BlockSort.blockSort(arr, 5);
        assertArrayEquals(null, arr);
    }
    @Test
    public void Test1(){
        int[] arr = {};
        BlockSort.blockSort(arr, 5);
        assertArrayEquals(new int[]{}, arr);
    }
    @Test
    public void Test2(){
        int[] arr = {5, 2, 8, 3, 9, 1};
        BlockSort.blockSort(arr, -5);
        assertArrayEquals(new int[]{5, 2, 8, 3, 9, 1}, arr);
    }
    @Test
    public void Test3(){
        int[] arr = {5, 2, 8, 3, 9, 1};
        BlockSort.blockSort(arr, 10);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, arr);
    }
    @Test
    public void Test4(){
        int[] arr = {1, 2, 3, 5, 8, 9};
        BlockSort.blockSort(arr, 2);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, arr);
    }
    @Test
    public void Test5(){
        int[] arr = {5, 2, 8, 3, 9, 1, 8, 3, 5};
        BlockSort.blockSort(arr, 2);
        assertArrayEquals(new int[]{1, 2, 3, 3, 5, 5, 8, 8, 9}, arr);
    }



}
