package Testpro;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {-1,2,5,3,9,7,1,8,6,4};
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = i; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i] + " ");
        }
    }
}
