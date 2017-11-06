package com.xjsaber.java.core.advanced.io01.match;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author xjsaber
 */
public class RegexTest {

    public static void main(String[] args) throws PatternSyntaxException {
        Scanner in = new Scanner(System.in);
        System.out.println("输入：");
        String patternString = in.nextLine();

        Pattern pattern = Pattern.compile(patternString);

        while (true){
            System.out.println("输入正则表达式");
            String input = in.nextLine();
            if (input == null || "".equals(input)) {
                return;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Match");
                int g = matcher.groupCount();
                if (g > 0){
                    for (int i = 0; i < input.length(); i++){
                        for (int j = 1; j <= g; j++){

                        }
                    }
                }
            }
        }
    }
}
