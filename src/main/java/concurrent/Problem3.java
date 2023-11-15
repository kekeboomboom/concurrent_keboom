package concurrent;

import java.util.Arrays;

public class Problem3 {

    public static void main(String[] args) {
        int[] input = new int[100];

        // 随机拿两个索引，把这两个位置上的数字做调换
        for (int i = 0; i < 1000; i++) {
            int indexA = (int) (Math.random() * input.length);
            int indexB = (int) (Math.random() * input.length);

            int tmp = input[indexA];
            input[indexA] = input[indexB];
            input[indexB] = tmp;
        }

        System.out.println(Arrays.toString(input));

    }
}
