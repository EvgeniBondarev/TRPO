import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockSort {
    public static void blockSort(int[] arr, int blockSize) {
        if (arr == null || arr.length == 0 || blockSize <= 0) {
            return;
        }

        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }
        //Вычисление количества блоков и создание массива списков для блоков
        int range = max - min + 1;
        int blockCount = (int) Math.ceil((double) range / blockSize);
        List<Integer>[] blocks = new ArrayList[blockCount];
        for (int i = 0; i < blockCount; i++) {
            blocks[i] = new ArrayList<Integer>();
        }
        //Разбиение исходного массива на блоки и добавление элементов в соответствующие блоки
        for (int i = 0; i < arr.length; i++) {
            int blockIndex = (arr[i] - min) / blockSize;
            blocks[blockIndex].add(arr[i]);
        }
        //Сортировка каждого блока по возрастанию и объединение отсортированных блоков в результирующий массив
        int index = 0;
        for (int i = 0; i < blockCount; i++) {
            Collections.sort(blocks[i]);
            for (int j = 0; j < blocks[i].size(); j++) {
                arr[index++] = blocks[i].get(j);
            }
        }
    }
}
