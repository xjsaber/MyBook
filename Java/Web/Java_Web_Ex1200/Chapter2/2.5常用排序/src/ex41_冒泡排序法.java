import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int array[] = {10, 23, 11, 56, 45, 29, 84, 79};
        Integer temp;

        for (int i = 0; i < array.length - 1; i++){
            for (Integer j = i; j < array.length - 1; j++){
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        for (int item
                : array) {
            System.out.print(item + ",");
        }
    }
}
