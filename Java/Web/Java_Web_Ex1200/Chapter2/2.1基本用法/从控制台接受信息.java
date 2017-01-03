import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //System.in是来自控制台的输入
        //Scanner cin = new Scanner(System.in);

        //Scanner可以使用正则表达式来分析基本类型和字符串的简单文本扫描器,
        //        构造完毕你就可以从控制台输入一些东西给java了

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入你的身份证号");
        //输入信息
        String temp = scanner.nextLine();
        System.out.println("原来您的身份证是" + temp.length() + "位数字的啊");
        //InputSteam输入流以字节为单位来获取数据，而且需要复杂的判断并创建字节数组作为缓冲，
        //最主要的是字节转换为字符时容易出现中文乱码		
        //的情况。所以对字符数据的读取，应该使用扫描器进行封装，然后获取字符串类型的数据。
    }
}
