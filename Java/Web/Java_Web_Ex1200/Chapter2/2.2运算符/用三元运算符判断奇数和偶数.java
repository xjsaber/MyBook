import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //BigDecimal类，它用于大数字的精确计算。
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        long number = scan.nextLong();
        String check = (number % 2 == 0) ? "这个数是偶数" : "这个数是奇数";
        System.out.println(check);
    }
}
